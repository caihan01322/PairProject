from flask import Blueprint, session, request, url_for
from flask_dropzone import random_filename

from Team_work.app import db
from Team_work.globals.models import User, Note, User_article
from Team_work.globals.responses import Responses
from Team_work.globals.utils import responseBody, responseError, generate_token, responseSuccess, token_check_required
import base64
import datetime

# 处理用户认证的蓝图
auth = Blueprint("auth", __name__, url_prefix="/auth")


# 登录
@auth.route("/login", methods=["POST"])
def login():
    data = request.get_json()
    account = data.get("account")
    password = data.get("password")
    user = User.query.filter_by(account=account).first()
    # 用户不存在
    if not user:
        return responseError(Responses.NO_USER_FOUND)
    # 密码错误
    if not user.check_password(password):
        return responseError(Responses.INCORRECT_PASSWORD)
    # 生成token
    token = generate_token(user_id=user.id)
    session["token"] = token
    session["user_id"] = user.id
    # 获取用户头像路径
    user_avatar = "default.jpg"
    if user.avatar != "default":
        user_avatar = user.avatar
    avatar_path = url_for('static', filename="avatars/%s" % user_avatar)
    # 用户账号，姓名，当前时间
    account = user.account
    username = user.username
    date = datetime.datetime.now()
    # 保存登录时间
    session["login_date"] = date
    # 获取加入天数
    day = date.day - user.create_time.day
    # 获得头像base64编码
    with open(avatar_path, 'rb') as f:
        stream = base64.b64encode(f.read())
        avatar_base64_str = str(stream, encoding='utf-8')
        # 返回数据
        response_info = {
            "avadar": avatar_base64_str,
            "name": username,
            "userid": account,
            "date": date.strftime("%Y-%m-%d %H:%M:%S"),
            "token": token,
            "day": day
        }
        return responseBody(data=response_info)

#登出
@auth.route("/logout", methods=["POST"])
# @token_check_required
def logout():
    try:
        # 清空session
        session["token"] = None
        session["user_id"] = None
        data = request.get_json()
        user_id = int(data.get("user_id"))
        return responseSuccess(Responses.OPERATION_SUCCESS)
    except Exception as e:
        print(e)
        return responseError(Responses.PARAMETERS_ERROR)

#修改除头像外其他信息
@auth.route("/update_infos", methods=["POST"])
#@token_check_required
def update_infos():
    try:
        data = request.get_json()
        user_id = int(data.get("user_id"))
        # 列表分别为user_name,user_password,user_mobile,user_email,user_school,user_info,user_gender
        user_list = (data.get("user_list"))

        user = User.query.filter_by(id=user_id).first()

        user.username = user_list[0]
        user.set_password(user_list[1])
        user.mobile = user_list[2]
        user.email = user_list[3]
        user.college = user_list[4]
        user.info = user_list[5]
        user.gender = user_list[6]
        db.session.commit()

        return responseSuccess(Responses.OPERATION_SUCCESS)
    except Exception as e:
        print(e)
        db.session.rollback()
        return responseError(Responses.PARAMETERS_ERROR)

#修改头像
@auth.route("/update_face",methods=["POST"])
#@token_check_required
def update_face():
    try:
        data = request.get_json()
        user_id=data.get("user_id")
        user_face=data.get("user_face")
        user = User.query.filter_by(id=user_id).first()
        user_avatar = "default.jpg"
        if user.avatar != "default":
            user_avatar = user.avatar
        avatar_filename = random_filename(user_avatar)

        user.avatar = avatar_filename
        db.session.commit()
        try:
            avatar_path = url_for('static', filename="avatars/%s" % avatar_filename)
            # 写入图片并保存
            with open(avatar_path, 'wb') as w:
                w.write(base64.b64decode(user_face))
            # 获得修改后的头像的base64编码
            with open(avatar_path, 'rb') as f:
                stream = base64.b64encode(f.read())
                avatar_base64_str = str(stream, encoding='utf-8')

            return responseBody(data=[{'avatar': avatar_base64_str}])
        except Exception as e:
            print(e)
            db.session.rollback()
            return responseError(Responses.SAVE_FILE_FAIL)

    except Exception as e:
        print(e)
        db.session.rollback()
        return responseError(Responses.PARAMETERS_ERROR)


#注册
@auth.route("/register", methods=["POST"])
def register():
    try:
        data = request.get_json()
        user_name = data.get("user_name")
        user_account = data.get("user_account")
        user_password = data.get("user_password")
        user_repassword = data.get("user_repassword")
        #验证两次密码是否相同
        if user_password != user_repassword:
            return responseError(Responses.NOT_SAME_PASSWORD)
        #验证用户名是否已存在
        if User.query.filter_by(username=user_name).count() == 0:
            return responseError(Responses.EXIST_NAME)
        #验证用户账号是否已存在
        if User.query.filter_by(account=user_account).count() == 0:
            return responseError(Responses.EXIST_ACCOUNT)

        user=User(account=user_account,username=user_name)
        User.set_password(password=user_password)
        db.session.add(user)
        db.session.commit()

    except Exception as e:
        print(e)
        db.session.rollback()
        return responseError(Responses.PARAMETERS_ERROR)

@auth.route("/update_note",methods=["GET"])
#@token_check_required
def update_note():
    try:
        data = request.get_json()
        user_id = data.get("user_id")
        article_id = data.get("article_id")
        note_info=data.get("note_info")
        note = Note.query.filter_by(user_id=user_id, article_id=article_id).first()
        if not note:
            return responseError(Responses.NO_RECORD_FOUND)
        note.note=note_info
        db.session.commit()
        return responseSuccess(Responses.OPERATION_SUCCESS)
    except Exception as e:
        print(e)
        db.session.rollback()
        return responseError(Responses.PARAMETERS_ERROR)

@auth.route("/del_article",methods=["GET"])
#@token_check_required
def del_article():
    try:
        data=request.get_json()
        user_id=data.get("user_id")
        article_id=data.get("article_id")

        article=User_article.query.filter_by(user_id=user_id,id=article_id).first()
        if not article:
            return responseError(Responses.NO_RECORD_FOUND)
        db.session.delete(article)
        db.session.commit()
        return responseSuccess(Responses.OPERATION_SUCCESS)
    except Exception as e:
        print(e)
        db.session.rollback()
        return responseError(Responses.PARAMETERS_ERROR)

@auth.route("/upload_file",methods=["POST"])
#@token_check_required
def upload_file():
    try:
        data=request.get_json()
        file=data.get("file")
        title = data.get("title")
        filename = random_filename(file.filename)
        # 生成文件保存路径
        save_path = url_for('/static', filename='userupload/' + filename)
        # 保存文件
        file.save(save_path)
    except Exception as e:
        print(e)
        return responseError(Responses.PARAMETERS_ERROR)

@auth.route("/get_star",methods=["POST"])
def get_star():
    try:
        data=request.get_json()
        user_id=data.get("user_id")
        page_info = data.get("page_info")
        page_size = page_info.get("page_size")
        page_num = page_info.get("page_num")
        allPageNum=User_article.query.filter_by(
            user_id=user_id) \
            .order_by(User_article.create_time.desc()).count()
        pagination = User_article.query.filter_by(
            user_id=user_id) \
            .order_by(User_article.create_time.desc()).paginate(per_page=page_num, page=page_size)
        articles = pagination.items
        response_info = []
        for i in articles:
            response_info.append({
                'id': i.id,
                'title': i.title,
                'create_time': i.create_time,
                'meeting_name': i.meeting_name,
                'authors': i.author,
                'abstract': i.abstract,
                'keyword': i.keyword,
                'address': i.address
            })
        return responseBody(data={'data': response_info, 'allPageNum': allPageNum})



    except Exception as e:
        print(e)
        return responseError(Responses.PARAMETERS_ERROR)