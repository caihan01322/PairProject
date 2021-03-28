# -*- coding: utf-8 -*-
from flask import abort, flash, jsonify, render_template, redirect, request, url_for
from flask_login import login_user, login_required, current_user, logout_user
from flask_wtf import FlaskForm
from config import *
from login_manager import get_login_manager


login_manager = get_login_manager()
login_manager.init_app(app)


PER_PAGE = 10


@app.route("/")
def index():
    """未登录的主页"""
    return render_template("index.html")


@app.route("/index_logined.html")
def index_logined():
    """登陆后的主页"""
    return render_template("index_logined.html")


@app.route("/login_view", methods=["GET"])
def login_view():
    """登录视图"""
    # 如果已经有登陆用户，就不允许进入登录界面
    if current_user.is_authenticated:
        return redirect(url_for("index_logined"))
    return render_template("login.html")


@app.route("/login", methods=["POST"])
def login():
    """登录

    获取前端通过POST方式提交的email、password，判断是否登陆成功

    Args:
        email: 邮箱账号
        password: 密码
        remember: 是否记住我，勾选则cookie的保存时间变长

    Returns:
        登陆出错时返回结果的json格式
        code: -1为登录过程中出错，0为登陆成功
        message: 解释code
        登录成功时返回首页视图
        example：
            见接口文档
    """
    email = request.form.get("email")
    password = request.form.get("password")
    remember = bool(request.form.get("remember"))

    if email == "":
        return jsonify(code=-1, message="未输入邮箱账号")
    if password == "":
        return jsonify(code=-1, message="未输入密码")

    user = Users.query.filter(Users.email == email).first()

    if user is None:
        return jsonify(code=-1, message="该账号不存在")

    if user.password == password:
        login_user(user, remember=remember)
        next_url = request.args.get("next")
        if not next_url or not next_url.startswith('/'):
            next_url = url_for("index_logined")
        return redirect(next_url)
    else:
        return jsonify(code=-1, message="密码错误")

    return render_template("index_logined.html")


@app.route('/logout', methods=["GET"])
def logout():
    """登出"""
    # 如果是未登录用户访问，直接抛出 404 错误
    if not current_user.is_authenticated:
        abort(404)
    logout_user()
    flash('已登出', 'info')
    return redirect(url_for("login_view"))


@app.route("/register_view", methods=["GET"])
def register_view():
    """"注册视图"""
    return render_template("register.html")


@app.route("/register", methods=["POST"])
def register():
    """注册

    获取前端通过POST方式提交的email、password，判断是否注册成功

    Returns:
        出错时返回结果的json格式
        code: -1为注册过程中出错，0为登陆成功
        message: 解释code
        注册成功时返回登录视图
        example：
            见接口文档
    """
    email = request.form.get("email")
    password = request.form.get("password")

    if email == "":
        return jsonify(code=-1, message="未输入邮箱账号")
    if password == "":
        return jsonify(code=-1, message="未输入密码")

    user = Users.query.filter(Users.email == email).first()

    if user is not None:
        return jsonify(code=-1, message="该账号已注册")

    user = Users(email=email, password=password)
    db.session.add(user)
    db.session.commit()

    return redirect(url_for("login_view"))


@app.route("/search_view", methods=["GET"])
@login_required
def search_view():
    """查询视图"""
    return render_template("search.html")


@app.route("/search", methods=["GET"])
def search():
    """查询

    根据前端输入的条件和选择的查询方式查询论文

    Returns:
        返回查询界面模板
        pagination_func: 查找函数名
        pagination: 分页器
        condition: 查询条件（前端传来的数据）
        search_way: 查询方式（前端传来的数据）
        无结果：返回模板文本
        example：
            见接口文档
    """
    condition = request.args.get("condition")
    page = int(request.args.get("page", 1))
    search_way = request.values.get("search_way")

    if condition == "":
        return render_template("search.html")

    if search_way == "title":
        pagination = search_by_title(title=condition, page=page)
        return render_template("search.html", pagination_func="search", pagination=pagination, condition=condition, search_way=search_way)
    else:
        pagination = search_by_keyword(keyword=condition, page=page)
        return render_template("search.html", pagination_func="search", pagination=pagination, condition=condition, search_way=search_way)


def search_by_title(title, page):
    """论文标题模糊查询

    根据title和page查询数据并返回分页器

    Returns:
        返回分页器pagination
        无结果：返回模板文本
        example：
            见接口文档
    """
    pagination = Articles.query.filter(Articles.title.ilike(
        "%"+title+"%")).paginate(page, per_page=PER_PAGE, error_out=False)

    return pagination


def search_by_keyword(keyword, page):
    """论文关键词模糊查询

    根据keyword和page查询数据并返回分页器

    Returns:
        返回分页器pagination
        无结果：返回模板文本
        example：
            见接口文档
    """
    keywords = keyword.split(",")

    article_title = set()
    all_articles = None

    for key in keywords:
        tuples = Keywords.query.filter(
            Keywords.keyword.ilike("%"+key+"%")).all()
        if tuples is None:
            return render_template("index.html")
        # 避免查询重复的论文标题
        for t in tuples:
            for article in t.articles:
                article_title.add(article.title)

        articles = Articles.query.filter(Articles.title.in_(article_title))
        if all_articles is None:
            all_articles = articles
        else:
            all_articles = all_articles.union(articles)

    pagination = all_articles.paginate(
        page, per_page=PER_PAGE, error_out=False)

    return pagination


@app.route("/delete", methods=["GET"])
def delete():
    """删除论文

    在查询列表，每篇论有一个删除按钮，点击删除后数据库将删除该文章，同时更新当前的查询列表视图

    Args:
        title: 在列表视图中点击删除按钮时会自动获取title

    Return:
        返回模板渲染
    """
    title = request.args.get("title")

    article = Articles.query.filter(Articles.title == title)

    db.session.delete(article)
    db.session.commit()
    return render_template("search.html")


@app.route("/top_10_keyword")
def top_10_keyword():
    """获取Top10的关键词

    获取频率最高的前10个关键词，返回json格式

    Return:
        json格式
        code: 0正常
        data: 含有10个关键词的list
            keyword: 关键词
            url: 查询跟该关键词相关的论文的路由
    """
    keyword = Keywords.query.order_by(Keywords.count.desc()).limit(10).all()

    data = []
    for key in keyword:
        per_key = {"keyword":key.keyword, "url":url_for("search", condition=key.keyword, search_way="keyword")}
        data.append(per_key)

    return jsonify(code=0, data=data)


if __name__ == "__main__":
    app.config['JSON_AS_ASCII'] = False
    app.run(port=5000, debug=True)
