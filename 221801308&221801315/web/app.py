# -*- coding: utf-8 -*-
from flask import abort, flash, jsonify, render_template, redirect, request, url_for
from flask_login import login_user, login_required, current_user, logout_user
from config import *
from login_manager import get_login_manager

login_manager = get_login_manager()
login_manager.init_app(app)

PER_PAGE = 10
CURRENT_YEAR = 2021
YEAR_LIMIT = 10
BEGIN_YEAR = CURRENT_YEAR - YEAR_LIMIT


@app.route("/")
def index():
    """未登录的主页"""
    return render_template("index.html")


@app.route("/index_logined")
def index_logined():
    """登陆后的主页"""
    return render_template("index_logined.html")


@app.route("/login_view", methods=["GET", "POST"])
def login_view():
    """登录视图"""
    # 如果已经有登陆用户，就不允许进入登录界面
    if current_user.is_authenticated:
        return redirect(url_for("index_logined"))
    return render_template("login.html")


@app.route("/contact_view", methods=["GET"])
def contact_view():
    return render_template("contact.html")


@app.route("/login", methods=["POST"])
def login():
    """登录

    获取前端通过POST方式提交的email、password，判断是否登陆成功

    Args:
        email: 邮箱账号
        password: 密码
        remember: 是否记住我，勾选则cookie的保存时间变长

    Returns:
        flash提示信息
        登录成功时返回登陆后的首页视图
    """
    email = request.form.get("email")
    password = request.form.get("password")
    remember = bool(request.form.get("remember"))
    next_url = request.form.get("next")

    if email == "":
        flash("未输入邮箱账号")
        return render_template("login.html")
    if password == "":
        flash("未输入密码")
        return render_template("login.html")
    user = Users.query.filter(Users.email == email).first()

    if user is None:
        flash("该账号不存在")
        return render_template("login.html")

    if user.password == password:
        login_user(user, remember=remember)
        flash("登陆成功")
        if (next_url is None) or (not next_url.startswith("%2F")):
            return redirect("/index_logined")
        else:
            next_url = next_url.replace("%2F", "/")
            return redirect(next_url)
    else:
        flash("密码错误")
        return render_template("login.html")


@app.route("/logout", methods=["GET"])
def logout():
    """登出"""
    logout_user()
    flash("已登出")
    return redirect("/")


@app.route("/register_view", methods=["GET"])
def register_view():
    """"注册视图"""
    return render_template("register.html")


@app.route("/register", methods=["POST"])
def register():
    """注册

    获取前端通过POST方式提交的email、password、repetition，判断是否注册成功

    Returns:
        flash消息提示
        注册成功时返回登录视图
    """
    email = request.form.get("email")
    password = request.form.get("password")
    repetition = request.form.get("repetition")

    if email == "":
        flash("未输入邮箱账号")
        return render_template("register.html")

    user = Users.query.filter(Users.email == email).first()
    if user is not None:
        flash("该账号已注册")
        return render_template("register.html")

    if password == "":
        flash("请输入密码")
        return render_template("register.html")

    if password != repetition:
        flash("密码不一致")
        return render_template("register.html")

    try:
        user = Users(email=email, password=password)
        db.session.add(user)
        db.session.commit()
        flash("注册成功")
        return redirect("/login_view")
    except Exception as e:
        db.session.rollback()
        print(e)
        flash("未知错误")
        return render_template("register.html")


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
        return render_template("search.html", pagination_func="search", pagination=pagination, condition=condition,
                               search_way=search_way)
    else:
        pagination = search_by_keyword(keyword=condition, page=page)
        return render_template("search.html", pagination_func="search", pagination=pagination, condition=condition,
                               search_way=search_way)


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
        "%" + title + "%")).paginate(page, per_page=PER_PAGE, error_out=False)

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
            Keywords.keyword.ilike("%" + key + "%")).all()
        if tuples is None:
            return render_template("search.html")
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


@app.route("/view", methods=["GET"])
def view():
    """查看论文

    在查询列表，每篇论有一个查看按钮，点击查看后看到论文详细内容

    Args:
        title: 在列表视图中点击查看按钮时会自动获取title

    Return:
        返回模板渲染
    """
    title = request.args.get("title")
    condition = request.args.get("condition")
    page = request.args.get("page")
    search_way = request.args.get("search_way")
    pagination_func = request.args.get("pagination_func")

    article = Articles.query.filter(Articles.title == title).first()

    return render_template("view.html", article=article, pagination_func=pagination_func, page=page,
                           condition=condition, search_way=search_way)


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
    condition = request.args.get("condition")
    page = request.args.get("page")
    search_way = request.args.get("search_way")
    pagination_func = request.args.get("pagination_func")

    article = Articles.query.filter(Articles.title == title).first()

    db.session.delete(article)
    db.session.commit()
    flash("已删除")
    return redirect(url_for(pagination_func, page=page, condition=condition, search_way=search_way))


@app.route("/hot_keywords_cake")
@login_required
def hot_keywords_cake():
    """热词饼图"""
    return render_template("hot_keywords_cake.html")


@app.route("/get_cake", methods=["GET"])
def get_cake():
    """热词饼图获取数据

    获取频率最高的前10个关键词，返回json格式

    Return:
        json格式
        code: 0正常
        data: 含有10个关键词的list
            keyword: 关键词
            url: 查询跟该关键词相关的论文的路由
            total: 每个词的总数
    """
    keyword = Keywords.query.order_by(Keywords.count.desc()).limit(10).all()

    data = []
    for key in keyword:
        per_key = {"keyword": key.keyword, "total": key.count,
                   "url": url_for("search", condition=key.keyword, search_way="keyword")}
        data.append(per_key)

    return jsonify(code=0, data=data)


@app.route("/hot_keywords_trend")
@login_required
def hot_keywords_trend():
    """热词走势图"""
    return render_template("hot_keywords_trend.html")


@app.route("/get_trend", methods=["GET"])
def get_trend():
    """热词走势图获取数据

    获取频率最高的前10个关键词，返回json格式

    Return:
        json格式
        code: 0正常
        data: 含有10个关键词的list
            keyword: 关键词
            CVPR: 近10年间在每年在该会议出现的次数
            ECCV: 近10年间在每年在该会议出现的次数
            ICCV: 近10年间在每年在该会议出现的次数
        """
    keyword = Keywords.query.order_by(Keywords.count.desc()).limit(10).all()

    data = []
    for key in keyword:
        CVPR = [0 for i in range(10)]
        ECCV = [0 for i in range(10)]
        ICCV = [0 for i in range(10)]

        for article in key.articles:
            # total += 1
            year = int(article.publicationYear)
            if year in range(BEGIN_YEAR, CURRENT_YEAR):
                if article.meeting == "CVPR":
                    CVPR[year - BEGIN_YEAR] += 1
                elif article.meeting == "ECCV":
                    ECCV[year - BEGIN_YEAR] += 1
                else:
                    ICCV[year - BEGIN_YEAR] += 1

        per_key = {"keyword": key.keyword, "CVPR": CVPR, "ECCV": ECCV, "ICCV": ICCV}
        data.append(per_key)
    return jsonify(code=0, data=data)


if __name__ == "__main__":
    app.run(port=8000, debug=True)
