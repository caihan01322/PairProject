from __init__ import *
from models import *
from forms import *


@app.route('/', methods=['GET', 'POST'])
def index():
    if request.form.get('search') == '搜索':
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
    froms = Login()
    if request.method == 'POST':
        if request.form.get('submit') == '还没有账号？':
            return redirect(url_for('signup'))
        else:
            return render_template('login.html', forms=froms)
    return render_template('login.html', forms=froms)


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
        flash('成功创建')
        db.session.commit()
        return render_template('signup.html', forms=forms)
    return render_template('signup.html', forms=forms)


@app.route('/list', methods=['GET', 'POST'])
def list():
    if request.method == 'POST':
        if request.form.get('submit') == '点我搜说':
            if len(request.form.get('text')) > 2:
                session['text'] = request.form.get('text')
                text = session['text']
                paper_list = Paper.query.filter(Paper.title.like('%' + text + '%'))
                return render_template('list.html', paper_list=paper_list)
            else:
                text = "请输入三个以上字符"
                return render_template('list.html', text=text)
        else:
            return redirect(url_for('login'))
    else:
        if 'text' in session:
            if len(session['text']) > 2:
                text = session['text']
                paper_list = Paper.query.filter(Paper.title.like('%' + text + '%'))
                return render_template('list.html', paper_list=paper_list)
            else:
                text = "还没搜索哦"
                return render_template('list.html', text=text)
        return render_template('list.html')
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
