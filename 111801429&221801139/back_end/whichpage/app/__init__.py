#!/usr/bin/env python3
# -*- coding: utf-8 -*-

from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from config import Config
from logging.handlers import RotatingFileHandler
import logging

app = Flask(__name__)
app.config.from_object(Config)

# 数据库
db = SQLAlchemy(app)

# 注册蓝图
from .pages import pages

app.register_blueprint(pages, url_prefix='/api/pages')

# 设置日志的相关信息(记录日志信息)
handler_main = RotatingFileHandler("logs/log",
                                   maxBytes=1024 * 1024 * 100,
                                   backupCount=10,
                                   encoding='utf-8')
formatter = logging.Formatter('%(levelname)s:  %(message)s, %(asctime)s')
handler_main.setFormatter(formatter)
logging.getLogger().addHandler(handler_main)
