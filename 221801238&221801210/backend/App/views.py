from flask import Blueprint, render_template, make_response
from sqlalchemy import func

from App.models import *
from flask import request, jsonify, current_app
from itsdangerous import TimedJSONWebSignatureSerializer as Serializer
import functools

blue = Blueprint('first_blue', __name__)


def init_blue(app):
    app.register_blueprint(blueprint=blue)


def create_token(user_id):
    '''
    生成token
    :param user_id:用户id
    :return: token
    '''

    # 第一个参数是内部的私钥，这里写在共用的配置信息里了，如果只是测试可以写死
    # 第二个参数是有效期(秒)
    s = Serializer(current_app.config["SECRET_KEY"], expires_in=3600)
    # 接收用户id转换与编码
    token = s.dumps({"id": user_id}).decode("ascii")
    return token


def verify_token(token):
    '''
    校验token
    :param token:
    :return: 用户信息 or None
    '''

    # 参数为私有秘钥，跟上面方法的秘钥保持一致
    s = Serializer(current_app.config["SECRET_KEY"])
    try:
        # 转换为字典
        data = s.loads(token)
    except Exception:
        return None
    # 拿到转换后的数据，根据模型类去数据库查询用户信息
    user = User.query.get(data["id"])
    return user


def login_required(view_func):
    @functools.wraps(view_func)
    def verify_token(*args, **kwargs):
        try:
            # 在请求头上拿到token
            token = request.headers["Authorization"]
        except Exception:
            # 没接收的到token,给前端抛出错误
            # 这里的code推荐写一个文件统一管理。这里为了看着直观就先写死了。
            return jsonify(code=403, msg='缺少参数token')

        s = Serializer(current_app.config["SECRET_KEY"])
        try:
            s.loads(token)
        except Exception:
            return jsonify(code=401, msg="登录已过期")

        return view_func(*args, **kwargs)

    return verify_token


'''
PAPER_PATH = os.path.join(os.getcwd(), "paper/")
    paper_class = os.listdir(PAPER_PATH)

    for i in paper_class:
        i = i + '/'
        paperlist_path = os.path.join(PAPER_PATH, i)
        paper_list = os.listdir(paperlist_path)
        num = 0
        if i == 'ICCV/':
            key_id = 62684
            a_id = 14114
            for k in paper_list:
                filename = paperlist_path + k
                # print(filename)
                f_obj = open(filename, "rb")
                paper_json = json.loads(f_obj.read().decode('utf-8'))
                f_obj.close()
                an = 0
                ab=""
                isbn=""
                if 'accessionNumber' in paper_json:
                    an = paper_json['accessionNumber']
                if 'isbn' in paper_json:
                    isbn = paper_json['isbn'][0]['value']
                if 'abstract' in paper_json:
                    ab = paper_json['abstract']
                p = Paper(title=paper_json['title'], abstract=ab, url=paper_json['doiLink'], time=k[0:4], \
                          isbn=isbn, accessionNumber=int(an), conference_id=3)
                print(k+"  ||  "+ab)
                db.session.add(p)
                # 关键词存入
                if 'keywords' in paper_json:
                    for kwd_dic in paper_json['keywords']:
                        for kw in kwd_dic['kwd']:
                            # print(kw)
                            rel=db.session.query(Keyword).filter(Keyword.content == kw).first()
                            # num += 1
                            # 关键词内容不存在时
                            if not rel:
                                keyword = Keyword(keyword_id=key_id, content=kw)
                                db.session.add(keyword)
                                ktoc = KeywordToConference(keyword_id=key_id, conference_id=3)
                                db.session.add(ktoc)
                                ktop = KeywordToPaper(keyword_id=key_id, paper_id=p.paper_id)
                                db.session.add(ktop)
                                key_id += 1
                            else:
                                # 来源会议不一样时
                                rel1 = db.session.query(KeywordToConference).filter(KeywordToConference.keyword_id == rel.keyword_id, \
                                                                                    KeywordToConference.conference_id == 3).first()
                                # print(rel.keyword_id)
                                if not rel1:
                                    ktoc =KeywordToConference(keyword_id=rel.keyword_id, conference_id=3)
                                    db.session.add(ktoc)
                                    ktop =KeywordToPaper(keyword_id=rel.keyword_id, paper_id=p.paper_id)
                                    db.session.add(ktop)
                                else:
                                    ktop = KeywordToPaper(keyword_id=rel.keyword_id, paper_id=p.paper_id)
                                    db.session.add(ktop)
                                    
                # 作者存入
                if 'authors' in paper_json:
                    for author_dic in paper_json['authors']:
                        name=author_dic['name']
                        # print(name)
                        rel = db.session.query(Author).filter(Author.name == name).first()
                        if not rel:
                            author = Author(author_id=a_id, name=name)
                            db.session.add(author)
                            atp = AuthorToPaper(author_id=a_id, paper_id=p.paper_id)
                            db.session.add(atp)
                            a_id += 1
                        else:
                            # 作者存在时
                            atp = AuthorToPaper(author_id=rel.author_id, paper_id=p.paper_id)
                            db.session.add(atp)
                num+=1
                print(num)

    db.session.commit()
'''

'''
        if i == 'CVPR/':
            key_id = 10700
            a_id = 1
            for k in paper_list:
                filename = paperlist_path + k
                # print(filename)
                f_obj = open(filename, "rb")
                paper_json = json.loads(f_obj.read().decode('utf-8'))
                f_obj.close()
                an = 0
                ab=""
                isbn=""
                if 'accessionNumber' in paper_json:
                    an = paper_json['accessionNumber']
                if 'isbn' in paper_json:
                    isbn = paper_json['isbn'][0]['value']
                if 'abstract' in paper_json:
                    ab = paper_json['abstract']
                p = Paper(title=paper_json['title'], abstract=ab, url=paper_json['doiLink'], time=k[0:4], \
                          isbn=isbn, accessionNumber=int(an), conference_id=1)
                # print(k+"  ||  "+ab +" || "+paper_json['doiLink']+" || "+\
                #     isbn +" || "+str(an))
                db.session.add(p)

                # 关键词存入
                if 'keywords' in paper_json:
                    for kwd_dic in paper_json['keywords']:
                        for kw in kwd_dic['kwd']:
                            # print(kw)
                            rel=db.session.query(Keyword).filter(Keyword.content == kw).first()
                            # num += 1
                            # 关键词内容不存在时
                            if not rel:
                                keyword = Keyword(keyword_id=key_id, content=kw)
                                db.session.add(keyword)
                                ktoc = KeywordToConference(keyword_id=key_id, conference_id=1)
                                db.session.add(ktoc)
                                ktop = KeywordToPaper(keyword_id=key_id, paper_id=p.paper_id)
                                db.session.add(ktop)
                                key_id += 1
                            else:
                                # 来源会议不一样时
                                rel1 = db.session.query(KeywordToConference).filter(KeywordToConference.keyword_id == rel.keyword_id, \
                                                                                    KeywordToConference.conference_id == 1).first()
                                # print(rel.keyword_id)
                                if not rel1:
                                    ktoc =KeywordToConference(keyword_id=rel.keyword_id, conference_id=1)
                                    db.session.add(ktoc)
                                    ktop =KeywordToPaper(keyword_id=rel.keyword_id, paper_id=p.paper_id)
                                    db.session.add(ktop)
                                else:
                                    ktop = KeywordToPaper(keyword_id=rel.keyword_id, paper_id=p.paper_id)
                                    db.session.add(ktop)


                # 作者存入
                if 'authors' in paper_json:
                    for author_dic in paper_json['authors']:
                        name=author_dic['name']
                        # print(name)
                        rel = db.session.query(Author).filter(Author.name == name).first()
                        if not rel:
                            author = Author(author_id=a_id, name=name)
                            db.session.add(author)
                            atp = AuthorToPaper(author_id=a_id, paper_id=p.paper_id)
                            db.session.add(atp)
                            a_id += 1
                        else:
                            # 作者存在时
                            atp = AuthorToPaper(author_id=rel.author_id, paper_id=p.paper_id)
                            db.session.add(atp)
'''

'''     
        if i == 'ECCV/':
            key_id = 1
            for k in paper_list:
                filename = paperlist_path + k
                # print(filename)
                f_obj = open(filename, "rb")
                paper_json = json.loads(f_obj.read().decode('utf-8'))
                f_obj.close()
                print(k[0:4])
                p = Paper(title=paper_json['论文名称'], abstract=paper_json['摘要'], url=paper_json['原文链接'], time=k[0:4], conference_id=2)
                db.session.add(p)
                for kw in paper_json['关键词']:
                    rel=db.session.query(Keyword).filter(Keyword.content == kw).first()
                    num += 1
                    # 关键词内容不存在时
                    if not rel:
                        keyword = Keyword(keyword_id=key_id, content=kw)
                        db.session.add(keyword)
                        ktoc = KeywordToConference(keyword_id=key_id, conference_id=2)
                        db.session.add(ktoc)
                        ktop = KeywordToPaper(keyword_id=key_id, paper_id=p.paper_id)
                        db.session.add(ktop)
                        key_id += 1
                    else:
                        # print(rel.keyword_id)
                        rel1 = db.session.query(KeywordToConference).filter(KeywordToConference.keyword_id == rel.keyword_id).one()
                        ktop = KeywordToPaper(keyword_id=rel.keyword_id, paper_id=p.paper_id)
                        db.session.add(ktop)
                    # print(paper_json['论文名称']+"    "+paper_json['摘要']+"   "+paper_json['原文链接']+"    "+paper_json['发布时间'])
'''


@blue.route('/user/register', methods=["POST"])
def user_register():
    res_dir = request.get_json()
    # print(res_dir)
    user = db.session.query(User).filter(User.email == res_dir['email']).first()
    if not user:
        user = User(email=res_dir['email'], username=res_dir['username'], password=res_dir['password'])
        db.session.add(user)
        # print(user)
        db.session.commit()
        return jsonify(code=200, msg="注册成功",
                       data={'token': create_token(user.user_id), 'email': user.email, 'name': user.username})

    return "注册失败", 400


@blue.route('/user/login', methods=["POST"])
def user_login():
    res_dir = request.get_json()
    # print(res_dir)
    user = db.session.query(User).filter(User.email == res_dir['email'], User.password == res_dir['password']).first()
    if not user:
        return "登录失败", 400

    return jsonify(code=200, msg="登录成功",
                   data={'token': create_token(user.user_id), 'email': user.email, 'name': user.username})


@blue.route('/')
def index():
    a = request.args.get('abc')
    if not a:
        return "not papr"

    return "success"


@blue.route('/paper/getList')
def paper_getlist():
    k_content = request.args.get("keyword")
    page = request.args.get("currentPage")
    # print(k_content)
    # 关键词模糊搜索
    kwds = db.session.query(Keyword).filter(Keyword.content.like('%' + k_content + '%')).all()
    paperlist = []
    Paper.currentNum = 0
    Paper.finish = False
    for kkk in kwds:
        k_id = kkk.keyword_id
        paper = dict()
        ktops = db.session.query(KeywordToPaper).filter(KeywordToPaper.keyword_id == k_id).all()
        for ktop in ktops:
            paper = Paper.getPaperDict(ktop.paper_id)
        # print(paper['title'])
        paperlist.append(paper)
        Paper.currentNum += 1
        print(Paper.currentNum)
    Paper.setCache(paperlist)
    return jsonify(code=200, msg="返回成功", data={'total': len(paperlist), 'paperList': Paper.getPage(0)})


@blue.route('/paper/page')
def paper_getpage():
    return jsonify(code=200, msg="换页成功", data={'paperList': Paper.getPage(request.args.get('currentPage'))})


@blue.route('/paper/getProcess')
def paper_getProgress():
    return jsonify(code=200, msg="", data={'currentFindNums': Paper.currentNum, 'finish': Paper.finish})


@blue.route('/favorites/get')
@login_required
def favorites_get():
    token = request.headers['Authorization']
    user = verify_token(token)
    favorite_id_list = db.session.query(Favorite).filter(Favorite.user_id == user.user_id).all()
    dicts = []
    for i in favorite_id_list:
        dic = {'favorite_id': i.favorite_id, 'name': i.name}
        dicts.append(dic)
    return jsonify(code=200, msg="获取成功", data={'favorites': dicts})


@blue.route('/favorites/getPaperList')
@login_required
def favorites_getpaperlist():
    f_id = request.args.get("favorite_id")
    ptofs = db.session.query(PaperToFavorite).filter(PaperToFavorite.favorite_id == f_id).all()
    paperlist = []
    for ptof in ptofs:
        paperlist.append(Paper.getPaperDict(ptof.paper_id))

    return jsonify(code=200, msg="获取成功", data={'paperlist': paperlist})


@blue.route('/favorites/delete', methods=['POST'])
@login_required
def favorite_delete():
    token = request.headers['Authorization']
    user = verify_token(token)
    f_id = request.get_json()['favorite_id']
    ptofs = db.session.query(PaperToFavorite).filter(PaperToFavorite.favorite_id == f_id).all()
    db.session.delete(ptofs)
    fs = db.session.query(Favorite).filter(Favorite.favorite_id == f_id).all()
    db.session.delete(fs)
    db.session.commit()
    return jsonify(code=200, msg="删除", data={})


@blue.route('/favorites/insert', methods=['POST'])
@login_required
def favorite_insert():
    token = request.headers['Authorization']
    user = verify_token(token)
    rel = request.get_json()
    f_id = rel['favorite_id']
    for p_id in rel['paper_ids']:
        ptof = PaperToFavorite(paper_id=p_id, favorite_id=f_id)
        db.session.add(ptof)
    db.session.commit()
    return jsonify(code=200, msg="添加成功", data={})


@blue.route('/favorites/create', methods=['POST'])
@login_required
def favorite_create():
    token = request.headers['Authorization']
    user = verify_token(token)
    rel = request.get_json()
    f_name = rel['name']
    f = Favorite(name=f_name, user_id=user.user_id)
    db.session.add(f)
    db.session.commit()
    return jsonify(code=200, msg="创建成功", data={})


@blue.route('/data/getTopTen')
def get_top_ten():
    topten_id = KeywordToConference.getTopTen()
    topten = []
    for i in topten_id:
        content = db.session.query(Keyword).filter(Keyword.keyword_id == i).first().content
        topten.append(content)
    return jsonify(code=200, msg="添加成功", data={'words_list': topten})


@blue.route('/data/getPaperSource')
def getPaperSource():
    beg=request.args.get('beg')
    end=request.args.get('end')
    # print(beg)
    # print(end)
    # papers= db.session.query(Paper).filter(Paper.time<=end, Paper.time>=beg).all()
    cvpr= db.session.query(func.count('*')).filter(Paper.time<=end, Paper.time>=beg, Paper.conference_id==1).all()
    eccv= db.session.query(func.count('*')).filter(Paper.time<=end, Paper.time>=beg, Paper.conference_id==2).all()
    iccv= db.session.query(func.count('*')).filter(Paper.time<=end, Paper.time>=beg, Paper.conference_id==3).all()
    if not cvpr:
        cvpr = 0
    else:
        cvpr = cvpr[0][0]
    if not eccv:
        eccv = 0
    else:
        eccv = eccv[0][0]
    if not iccv:
        iccv = 0
    else:
        iccv = iccv[0][0]
    return jsonify(code=200, msg="返回成功", data={'CVPR':cvpr, 'ICCV':iccv, 'ECCV':eccv})


# @blue.route('/data/getWordsTrend')
# def getWordsTrend():
#     limit

@blue.route('data/getWordsTrend')
def getWordsTrend():
    limit = request.args.get('limit')
    beg = request.args.get('beg')
    end = request.args.get('end')
    name = request.args.get('conference')
    conference = db.session.query(Conference).filter(Conference.name == name).first()
    c_id = 5
    if conference:
        c_id = conference.conference_id
    if end < beg:
        return jsonify(code=400, msg="年份错误", data={})

    words_list=[]
    # 单个会议
    year = beg
    if c_id != 5:
        kwd=dict()
        while year <= end:
            papers=db.session.query(Paper).filter(Paper.time==year,Paper.conference_id==c_id).all()
            for paper in papers:
                ktops=db.session.query(KeywordToPaper).filter(KeywordToPaper.paper_id==paper.paper_id).all()
                for ktop in ktops:
                    if

            year += 1
    return jsonify(code=200, msg="统计成功", data={})
    pass
