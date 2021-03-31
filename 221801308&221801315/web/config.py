# -*- coding: utf-8 -*-
from flask import Flask
from flask_login import UserMixin
from flask_sqlalchemy import SQLAlchemy
import pymysql

# 获取后端实例
app = Flask(__name__)
app.config['SEND_FILE_MAX_AGE_DEFAULT'] = 0
app.config["SECRET_KEY"] = "this is a secret key"
app.config['JSON_AS_ASCII'] = False


class Config(object):
    """配置参数"""
    # 设置连接数据库的URL
    user = 'root'
    password = '123456'
    database = 'paperweb'
    app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://%s:%s@localhost:3306/%s?charset=utf8' % (
        user, password, database)

    # 设置sqlalchemy自动跟踪数据库
    SQLALCHEMY_TRACK_MODIFICATIONS = False

    # 查询时会显示原始SQL语句
    app.config['SQLALCHEMY_ECHO'] = False

    # python3  连接数据库驱动是   pymysql
    # python2 连接数据驱动可以用  pymysql、MySQLdb
    # sqlalchemy 默认使用 MySQLdb 连接数据库
    pymysql.install_as_MySQLdb()


# 读取配置
app.config.from_object(Config)

# 创建数据库sqlalchemy工具对象
db = SQLAlchemy(app)

# 连接文章和对应的关键词
article_keyword = db.Table(
    "article_keyword",
    db.Column("article_id", db.Integer, db.ForeignKey(
        "articles.id", ondelete="cascade")),
    db.Column("keyword_id", db.Integer, db.ForeignKey(
        "keywords.id", ondelete="cascade"))
)

# 连接回收站文章和对应的关键词
recycle_keyword = db.Table(
    "recycle_keyword",
    db.Column("recycle_id", db.Integer, db.ForeignKey(
        "recycles.id", ondelete="cascade")),
    db.Column("keyword_id", db.Integer, db.ForeignKey(
        "keywords.id", ondelete="cascade"))
)

# 用户收藏文章，文章被多个用户收藏
article_user = db.Table(
    "article_user",
    db.Column("article_id", db.Integer, db.ForeignKey(
        "articles.id", ondelete="cascade")),
    db.Column("user_id", db.Integer, db.ForeignKey(
        "users.id", ondelete="cascade"))
)


class Articles(db.Model):
    # 定义表名
    __tablename__ = "articles"
    # 定义字段
    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    meeting = db.Column(db.String(255))
    title = db.Column(db.String(255))
    publicationYear = db.Column(db.String(255))
    abstract = db.Column(db.Text)
    doiLink = db.Column(db.String(255))
    keywords = db.relationship(
        "Keywords", secondary=article_keyword, backref=db.backref("articles", lazy='dynamic'), lazy='dynamic')


class Keywords(db.Model):
    # 定义表名
    __tablename__ = "keywords"
    # 定义字段
    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    keyword = db.Column(db.String(255))
    count = db.Column(db.Integer, default=1)


class Users(db.Model, UserMixin):
    # 定义表名
    __tablename__ = "users"
    # 定义字段
    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    email = db.Column(db.String(255))
    password = db.Column(db.String(255))
    nickname = db.Column(db.String(255))
    # 收藏夹
    articles = db.relationship(
        "Articles", secondary="article_user", backref=db.backref("users", lazy='dynamic'), lazy='dynamic')


class Recycles(db.Model):
    # 定义表名
    __tablename__ = "recycles"
    # 定义字段
    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    meeting = db.Column(db.String(255))
    title = db.Column(db.String(255))
    publicationYear = db.Column(db.String(255))
    abstract = db.Column(db.Text)
    doiLink = db.Column(db.String(255))
    keywords = db.relationship(
        "Keywords", secondary=recycle_keyword, backref=db.backref("recycles", lazy='dynamic'), lazy='dynamic')

    def __init__(self, article):
        self.id = article.id
        self.meeting = article.meeting
        self.title = article.title
        self.publicationYear = article.publicationYear
        self.abstract = article.abstract
        self.doiLink = article.doiLink
        self.keywords = article.keywords


if __name__ == "__main__":
    db.drop_all()  # 删除所有表
    db.create_all()  # 创建所有表
