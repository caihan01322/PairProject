# -*- coding: utf-8 -*-
from flask import jsonify, render_template, request
from config import *


@app.route("/")
def root():
    """网站首页

    Returns:
        首页模板index.html
    """
    return render_template("index.html")


@app.route("/a/search_by_title", methods=["GET"])
def search_by_title():
    """论文标题模糊查询

    获取前端通过GET方式提交的title内容，根据title进行模糊查询，获得查询到的论文信息
    title大小写均可

    Returns:
        有查询结果：返回模板文本和condition参数，并用分页器pagination填充模板
        无结果：返回模板文本
        example：
            见接口文档
    """
    title = request.args.get("condition")
    page = int(request.args.get("page", 1))

    if title is None:
        return render_template("index.html")

    pagination = Articles.query.filter(Articles.title.ilike(
        "%"+title+"%")).paginate(page, per_page=10, error_out=False)

    return render_template("index.html", pagination=pagination, condition=title)


@app.route("/a/search_by_keyword", methods=["GET"])
def search_by_keyword():
    """论文关键词模糊查询

    获取前端通过GET方式提交的keyword内容，根据keyword进行模糊查询，获得查询到的论文信息
    keyword可以有多个词组成，结果返回每个词查询到的论文并集
    keyword大小写输入均可

    Returns:
        有查询结果：返回模板文本和condition参数，并用分页器pagination填充模板
        无结果：返回模板文本
        example：
            见接口文档
    """
    keyword = request.args.get("condition")
    page = int(request.args.get("page", 1))

    if keyword is None:
        return render_template("index.html")
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

    pagination = all_articles.paginate(page, per_page=10, error_out=False)

    return render_template("index.html", pagination=pagination, condition=keyword)


if __name__ == "__main__":
    app.config['JSON_AS_ASCII'] = False
    app.run(port=5000, debug=True)
