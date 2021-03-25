# -*- coding: utf-8 -*-
from flask import Flask, jsonify, render_template, request
import pymysql
from functions import *


# 获取后端实例
app = Flask(__name__)


# 链接数据库
db = pymysql.connect(host="localhost", user="root",
                     password="123456", database="articles")
cursor = db.cursor()


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
        返回json格式的信息，格式见接口文档
        1. title不存在时，返回有关错误提示的json信息
        2. 查询不到文章时，返回没查到数据的json信息
        3. 查询成功时，返回论文有关内容的json信息
        example：
            见接口文档
    """
    title = request.args.get("title")
    if title is None:
        return jsonify(code=1, message="没有输入title", data=None)
    title = title.lower()

    cursor.execute(
        f"select * from article where lower(title) like '%{title}%'")
    articles = cursor.fetchall()
    if articles is None:
        return jsonify(code=-1, message="没有查询到数据", data=None)

    data = combine_keywords_and_articles(articles)

    return jsonify(code=0, message="查询成功", data=data)


@app.route("/a/search_by_keyword", methods=["GET"])
def search_by_keyword():
    """论文关键词模糊查询

    获取前端通过GET方式提交的keyword内容，根据keyword进行模糊查询，获得查询到的论文信息
    keyword可以有多个词组成，结果返回每个词查询到的论文并集
    keyword大小写输入均可

    Returns:
        返回json格式的信息，格式见接口文档
        1. keyword不存在时，返回有关错误提示的json信息
        2. 查询不到文章时，返回没查到数据的json信息
        3. 查询成功时，返回论文有关内容的json信息
        example：
            见接口文档
    """
    keyword = request.args.get("keyword")
    if keyword is None:
        return jsonify(code=1, message="没有输入keywords", data=None)
    keyword = keyword.lower()
    keywords = keyword.split(",")

    article_title = set()
    for key in keywords:
        cursor.execute(
            f"select title from keywords where lower(keyword) like '%{key}%'")
        titles = cursor.fetchall()
        if titles is None:
            return jsonify(code=-1, message="没有查询到数据", data=None)
        # 避免查询重复的论文标题
        for title in titles:
            article_title.add(title[0])

        articles = []
        for title in article_title:
            cursor.execute("select * from article where title=%s", title)
            for article in cursor.fetchall():
                articles.append(article)

        data = combine_keywords_and_articles(articles)

        return jsonify(code=0, message="查询成功", data=data)


if __name__ == "__main__":
    app.config['JSON_AS_ASCII'] = False
    app.run(port=5000, debug=True)
