from sqlalchemy import Integer, Column, String, Text
from app.exts import db


class User(db.model):
    __table__ = 'user'

    user_id = Column(Integer, primary_key=True, autoincrement=True)
    email = Column(String(32), unique=True, nullable=False)
    username = Column(String(16), nullable=False)
    password = Column(String(16), nullable=False)


class Favorite(db.model):
    __table__ = 'favorite'

    favorite_id = Column(Integer, primary_key=True, autoincrement=True)
    name = Column(String(255), default="收藏夹", nullable=False)
    user_id = Column(Integer, nullable=False)
    password = Column(String(16), nullable=False)


class Paper(db.model):
    __table__ = 'paper'

    paper_id = Column(Integer, primary_key=True, autoincrement=True)
    isbn = Column(String(32))
    title = Column(String(255), nullable=False)
    url = Column(String(255))
    time = Column(String(32))
    conference = Column(String(8))
    abstract = Column(Text)
    favorite_id = Column(Integer)


class Author(db.model):
    __table__ = 'author'

    author_id = Column(Integer, primary_key=True, autoincrement=True)
    name = Column(String(32), nullable=False)
    paper_id = Column(Integer)


class Keyword(db.model):
    __table__ = 'keyword'

    keyword_id = Column(Integer, primary_key=True, autoincrement=True)
    content = Column(String(32), nullable=False)


class KeywordToPaper(db.model):
    __table__ = 'keyword_to_paper'
    keyword_id = Column(Integer, nullable=False)
    paper_id = Column(Integer, nullable=False)