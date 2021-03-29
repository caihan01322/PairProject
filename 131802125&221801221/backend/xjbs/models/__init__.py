from .base import db
from .paper import Paper
import pymysql
pymysql.install_as_MySQLdb()


def init_app(app):
    db.init_app(app)

