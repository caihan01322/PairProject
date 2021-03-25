# -*- coding: utf-8 -*-
from flask import Flask, jsonify, render_template, request
import pymysql


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

    cursor.execute(f"select * from article where title like '%{title}%'")
    articles = cursor.fetchall()
    if articles is None:
        return jsonify(code=-1, message="没有查询到数据", data=None)

    data = []
    for article in articles:
        # 提取每篇论文的关键词组
        keywords = []
        cursor.execute(
            "select keyword from keywords where title=%s", article[1])
        keyword_list = cursor.fetchall()
        if keyword_list is not None:
            for keyword in keyword_list:
                keywords.append(keyword[0])

        content = {"meeting": article[0], "title": article[1], "year": article[2],
                   "keywords": keywords, "abstract": article[3],
                   "doiLink": article[4]}
        data.append(content)

    return jsonify(code=0, message="查询成功", data=data)


if __name__ == "__main__":
    app.config['JSON_AS_ASCII'] = False
    app.run(port=8080, debug=True)
