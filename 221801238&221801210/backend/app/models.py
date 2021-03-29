from sqlalchemy import Integer, Column, String, Text
from app.exts import db


class User(db.Model):
    __tablename__ = 'user'

    user_id = Column(Integer, primary_key=True, autoincrement=True)
    email = Column(String(32), unique=True, nullable=False)
    username = Column(String(16), nullable=False)
    password = Column(String(16), nullable=False)


class Favorite(db.Model):
    __tablename__ = 'favorite'

    favorite_id = Column(Integer, primary_key=True, autoincrement=True)
    name = Column(String(255), default="收藏夹", nullable=False)
    user_id = Column(Integer, nullable=False)
    password = Column(String(16), nullable=False)


class Paper(db.Model):
    __tablename__ = 'paper'

    paper_id = Column(Integer, primary_key=True, autoincrement=True)
    isbn = Column(String(32))
    title = Column(String(255), nullable=False)
    url = Column(String(255))
    time = Column(String(32))
    conference = Column(String(8))
    abstract = Column(Text)
    favorite_id = Column(Integer)


class Author(db.Model):
    __tablename__ = 'author'

    author_id = Column(Integer, primary_key=True, autoincrement=True)
    name = Column(String(32), nullable=False)
    paper_id = Column(Integer)


class Keyword(db.Model):
    __tablename__ = 'keyword'

    keyword_id = Column(Integer, primary_key=True, autoincrement=True)
    content = Column(String(32), nullable=False)


class KeywordToPaper(db.Model):
    __tablename__ = 'keyword_to_paper'
    id = Column(Integer, primary_key=True, autoincrement=True)
    keyword_id = Column(Integer, nullable=False)
    paper_id = Column(Integer, nullable=False)