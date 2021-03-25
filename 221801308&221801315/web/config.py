# -*- coding: utf-8 -*-
from flask import Flask, jsonify, render_template, request
from flask_sqlalchemy import SQLAlchemy
import pymysql


# 获取后端实例
app = Flask(__name__)


class Config(object):
    """配置参数"""
    # 设置连接数据库的URL
    user = 'root'
    password = '123456'
    database = 'articles'
    app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://%s:%s@localhost:3306/%s' % (
        user, password, database)

    # 设置sqlalchemy自动跟踪数据库
    SQLALCHEMY_TRACK_MODIFICATIONS = False

    # 查询时会显示原始SQL语句
    app.config['SQLALCHEMY_ECHO'] = False

    # 禁止自动提交数据处理
    app.config['SQLALCHEMY_COMMIT_ON_TEARDOWN'] = True

    # python3  连接数据库驱动是   pymysql
    # python2 连接数据驱动可以用  pymysql、MySQLdb
    # sqlalchemy 默认使用 MySQLdb 连接数据库
    pymysql.install_as_MySQLdb()


# 读取配置
app.config.from_object(Config)


# 创建数据库sqlalchemy工具对象
db = SQLAlchemy(app)


class Articles(db.Model):
    """对应数据库中的表articles"""
    # 定义表名
    __tablename__ = "articles"

    # 定义字段
    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    meeting = db.Column(db.String(255))
    title = db.Column(db.String(255))
    publicationYear = db.Column(db.String(255))
    abstract = db.Column(db.Text)
    doiLink = db.Column(db.String(255))


class Keywords(db.Model):
    """对应数据库中的表keywords"""
    # 定义表名
    __tablename__ = "keywords"

    # 定义字段
    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    title = db.Column(db.String(255))
    keyword = db.Column(db.String(255))


if __name__ == "__main__":
    db.drop_all()     # 删除所有表
    db.create_all()    # 创建所有表
