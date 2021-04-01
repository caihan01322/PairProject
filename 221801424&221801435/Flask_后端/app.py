from flask import Flask
from flask_sqlalchemy import SQLAlchemy
import os

from flask_cors import CORS

DATABASE = "teamwork"

app = Flask(__name__)


# app配置
class Config:
    SECRET_KEY = "I'm  a secret key"
    # 数据库相关配置
    SQLALCHEMY_DATABASE_URI = "mysql+pymysql://root:123456@127.0.0.1:3306/teamwork?charset=utf8mb4"
    SQLALCHEMY_TRACK_MODIFICATIONS = True
    # 搜索功能相关配置
    WHOOSHEE_MIN_STRING_LEN = 1


app.config.from_object(Config)
CORS(app)
db = SQLAlchemy(app)
# 注册蓝图

from Team_work.globals.function import functions
from Team_work.user.auth import auth

app.register_blueprint(functions)
app.register_blueprint(auth)

