from __init__ import *
from models import *
from forms import *


@app.route('/', methods=['GET', 'POST'])
def index():
    if request.form.get('search') == '爬一下!':
        if len(request.form.get('text')) > 2:
            session['text'] = request.form.get('text')
            return redirect(url_for('list'))
        else:
            text = "请输入三个以上字符"
            return render_template('index.html', text=text)
    else:
        return render_template('index.html')


@app.route('/login', methods=['GET', 'POST'])
def login():
    forms = Login()
    if request.method == 'POST':
        if request.form.get('submit') == '还没有账号？':
            return redirect(url_for('signup'))
        elif request.form.get('submit') == '登录':
            if len(request.form.get('username')) <= 3 or len(request.form.get('username')) > 20:
                return render_template('login.html', forms=forms, text1='用户名不能少于三个字符，最多不超过20个')
            if User.query.filter(User.username == request.form.get('username'),
                                 User.password == request.form.get('password')).count() == 0:
                return render_template('login.html', forms=forms, text2='用户名或密码错误！')
            session['username'] = request.form.get('username')
            return render_template('temp.html', text="登录成功")
        elif request.form.get('submit') == '退出登录' and 'username' in session:
            session.pop('username')
            return render_template('login.html', forms=forms)
    if 'username' in session:
        user = User.query.filter(User.username == session['username']).first()
        user_id = user.id
        password = user.password
        paper_number = UserCollection.query.filter(UserCollection.user_id == user_id).count()
        return render_template('user.html', username=session['username'], user_id=user_id, paper_number=paper_number,
                               password=password)
    return render_template('login.html', forms=forms)


@app.route('/signup', methods=['POST', 'GET'])
def signup():
    forms = Signup()
    if request.method == 'POST':
        if len(request.form.get('username')) <= 3 or len(request.form.get('username')) > 20:
            return render_template('signup.html', forms=forms, text1='用户名不能少于三个字符，最多不超过20个')
        if len(request.form.get('password')) < 6 or len(request.form.get('password')) > 20:
            return render_template('signup.html', forms=forms, text2='请输入6-20个字符')
        if request.form.get('password') != request.form.get('password2'):
            return render_template('signup.html', forms=forms, text3='上下文密码不一致')
        if User.query.filter(User.username == request.form.get('username')).count() != 0:
            return render_template('signup.html', forms=forms, text1='用户名已被注册')
        user = User(username=request.form.get('username'), password=request.form.get('password'))
        db.session.add(user)
        db.session.commit()
        session['username'] = request.form.get('username')
        return render_template('temp.html', text="注册成功，已登录")
    return render_template('signup.html', forms=forms)


@app.route('/list', methods=['GET', 'POST'])
def list():
    session['search_type'] = 1
    f = open('chart2.txt')
    data = f.readline()
    data = data[2:len(data) - 3]
    key_show_list = data.split("\', \'")
    key_show_list = key_show_list[0:7]
    f.close()
    print(key_show_list[0])
    if request.method == 'POST':
        if request.form.get("submit") == "收藏":
            message=''
            message_show =''
            if 'username' in session:
                message_show = 'display_alert()'
                paper_id = int(request.form.get("collect"))
                user_id = User.query.filter(User.username == session['username']).first().id
                if UserCollection.query.filter(UserCollection.user_id == user_id,
                                               UserCollection.paper_id == paper_id).count() == 0:
                    us = UserCollection(user_id=user_id, paper_id=paper_id)
                    db.session.add(us)
                    db.session.commit()
                    message='收藏成功'
                else:
                    message='收藏失败，该文章已经收藏'
            return redirect(url_for('list'))
        elif request.form.get("submit") == "跳转":
            session['list_page'] = int(request.form.get("select"))
            page=session['list_page']
            text = session['text']
            paper_list = []
            if session['search_type'] == 1:
                paper_list = Paper.query.filter(Paper.title.like('%' + text + '%')).all()
            if session['search_type'] == 2:
                paper_list = Paper.query.filter(Paper.paper_abstract.like('%' + text + '%')).all()
            if session['search_type'] == 3:
                keyword_list = PaperToKeyword.query.filter(PaperToKeyword.keyword.like('%' + text + '%')).all()
                for k in keyword_list:
                    paper_list += Paper.query.filter(Paper.id == k.paper_id).all()
            all_paper = len(paper_list)
            all_page = int(len(paper_list) / 6) + 1
            show_list = paper_list[6 * (page - 1):6 * page]
            paper_map = []
            page_show = "当前为第" + str(page) + "页"
            for p in show_list:
                key_list = PaperToKeyword.query.filter(PaperToKeyword.paper_id == p.id).all()
                paper_map.append([p, key_list])

            return render_template('list.html', paper_map=paper_map, all_page=range(0, all_page),
                                   search_word='text', page=page_show, all_paper=all_paper,key_show_list=key_show_list)
        else:
            if request.form.get('submit') == '爬标题':
                session['search_type']=1
            if request.form.get('submit') == '爬摘要':
                session['search_type'] = 2
            if request.form.get('submit') == '爬关键词':
                session['search_type'] = 3
            if len(request.form.get('text')) > 2:
                text = request.form.get('text')
                session['text'] = text
                session['list_page']=1
                paper_list=[]
                if session['search_type']==1:
                    paper_list = Paper.query.filter(Paper.title.like('%' + text + '%')).all()
                if session['search_type'] == 2:
                    paper_list = Paper.query.filter(Paper.paper_abstract.like('%' + text + '%')).all()
                if session['search_type'] == 3:
                    keyword_list=PaperToKeyword.query.filter(PaperToKeyword.keyword.like('%' + text + '%')).all()
                    for k in keyword_list:
                        paper_list += Paper.query.filter(Paper.id==k.paper_id).all()
                all_paper = len(paper_list)
                all_page = int(len(paper_list) / 6) + 1
                show_list = paper_list[0:6]
                paper_map = []
                page_show = "当前为第1页"
                for p in show_list:
                    key_list = PaperToKeyword.query.filter(PaperToKeyword.paper_id == p.id).all()
                    paper_map.append([p, key_list])
                return render_template('list.html', paper_map=paper_map, all_page=range(0, all_page),
                                       search_word='text', page=page_show, all_paper=all_paper,key_show_list=key_show_list)
            else:
                text = "请输入三个以上字符"
                page_show = "当前为第1页"
                return render_template('list.html', text=text,page=page_show, all_paper=0)
    else:
        if 'text' in session:
            if len(session.get('text')) > 2:
                text = session.get('text')
                paper_list = Paper.query.filter(Paper.title.like('%' + text + '%')).all()
                all_paper = len(paper_list)
                all_page = int(len(paper_list) / 6) + 1
                show_list = paper_list[0:6]
                paper_map = []
                page_show = "当前为第1页"
                for p in show_list:
                    key_list = PaperToKeyword.query.filter(PaperToKeyword.paper_id == p.id).all()
                    paper_map.append([p, key_list])
                return render_template('list.html', paper_map=paper_map, all_page=range(0, all_page),
                                       search_word=session['text'], page=page_show, all_paper=all_paper,key_show_list=key_show_list)
            else:
                text = "还没搜索哦"
                return render_template('list.html', text=text)
        session['text'] = 'text'
        session['search_type'] = 1
        return redirect('list')


@app.route('/collection', methods=['POST', 'GET'])
def collection():
    if 'username' in session:
        if request.form.get("submit") == '删除收藏':
            paper_id = int(request.form.get('collect'))
            user_id = User.query.filter(User.username == session['username']).first().id
            collect_paper = UserCollection.query.filter(UserCollection.user_id == user_id,
                                                        UserCollection.paper_id == paper_id).first()
            db.session.delete(collect_paper)
            db.session.commit()
            return redirect(url_for('collection'))
        else:
            user_id = User.query.filter(User.username == session['username']).first().id
            collection_list = UserCollection.query.filter(UserCollection.user_id == user_id).all()
            all_paper = len(collection_list)
            all_page = int(len(collection_list) / 6) + 1
            if request.method == 'GET':
                show_list = collection_list[0:6]
            else:
                page = int(request.form.get("select"))
                show_list = collection_list[6 * (page - 1):6 * page]
            paper_list = []
            history_list = []
            history_show = collection_list[0:7]
            for h in history_show:
                history_list.append(Paper.query.filter(Paper.id == h.paper_id).first())
            for s in show_list:
                paper_list.append(Paper.query.filter(Paper.id == s.paper_id).first())
            paper_map = []
            for p in paper_list:
                key_list = PaperToKeyword.query.filter(PaperToKeyword.paper_id == p.id).all()
                paper_map.append([p, key_list])
            return render_template('collection.html', paper_map=paper_map, all_page=range(0, all_page),
                                   all_paper=all_paper, history_list=history_list)
    return redirect(url_for('login'))


@app.route('/view', methods=['POST', 'GET'])
def view():
    if request.method=='POST':
        if request.form.get('submit')=='年度走势图':
            f = open('chart1.txt')
            data = f.read()
            chart = 'chart1.html'
            f.close()
            return render_template('view.html', data=data, chart=chart)
        if request.form.get('submit')=='历史热词榜':
            f = open('chart2.txt')
            data1=f.readline()
            data2=f.readline()
            chart='chart2.html'
            f.close()
            return render_template('view.html', data2=data2,data1=data1, chart=chart)
        else:
            f = open('chart3.txt')
            data = f.readline()
            chart='chart3.html'
            return render_template('view.html', chart=chart,data=data)
    else:
        f = open('chart1.txt')
        data = f.read()
        chart = 'chart1.html'
        f.close()
        return render_template('view.html', data=data,chart=chart)
@app.route('/us', methods=['POST', 'GET'])
def us():
    return render_template('us.html')
    # if request.method == 'POST':
    #     text = request.form.get('text')
    #     flash(text)
    #     return render_template('login.html', text=text)
    #     # flash需要加密
    # str = 'sharding'
    # login_Form = loginForm()
    # i = 0
    # my_list = [1, 2, 3, 4, 5]
    # # 前面是变量名，模板中使用。后面是定义的变量
    # # my_dict={'name':潘增滢 'url'='www.xxxx.com'}
    # #        这个是字典数据
    # #        使用时用my_dict.url   my_dict['url']
    # return render_template('index.html', str=str, my_list=my_list, loginForm=login_Form)


#
# db.drop_all()
# db.create_all()
# user=User(name='123')创建对象
# db.session.add(user)加入
# db.session.add_all([1,2,4])方括号代表以列表方式提交
# db.session.commit()提交
# db.session.delete(user)删除
# db.session.submit()提交
# User.query.all()
# User.query.count()
# User.query.filter_by(id=4).first()||User.query.get(4)查询id为4
# User.query.filter(User.id==4)

# @app.route('/',defaults={'name':'sb'})
# @app.route('/<name>')
# def hello_world(name):
#    return '<h1>Hello World!%s </h1>' % name


# @app.route('/login',methods=["POST"])
# def login():
#    try:
#        data=request.get_json()
#        user_name=data.get("user_name")


#    except Exception as e:
#        print(e)

# if request.form.get('转到login')=='点我':
#     return redirect('login')#重定向到路由
# else:
#     user_list=User.query.all()
#     print(user_list.id)

# class loginForm(FlaskForm):  # 自创表单函数
#     text = StringField('输入框:', validators=[DataRequired('啊这，你怎么不输入')])  # u用于转码
#     submit = SubmitField('提交:')


# @app.route('/form', methods=['GET', 'POST'])
# def login():
#     return render_template('index.html')
#
# app = Flask(__name__)
# app.config.from_object(Config)
# app.secret_key = '我是密码'
# db = SQLAlchemy(app)
# # db.init_app(app)


if __name__ == '__main__':
    app.run()
    db.drop_all()
    db.create_all()
