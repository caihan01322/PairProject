from .base import db
from .paper import Paper
from .user import User
from .response_data import ResponseData
import pymysql
pymysql.install_as_MySQLdb()


def init_app(app):
    db.init_app(app)

