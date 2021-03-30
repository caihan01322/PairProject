from flask import current_app
from itsdangerous import TimedJSONWebSignatureSerializer as Serializer
from sqlalchemy import String, Integer, Column, Text

from App.ext import db


class User(db.Model):
    __tablename__ = 'user'

    user_id = Column(Integer, primary_key=True, autoincrement=True)
    email = Column(String(32), unique=True, nullable=False)
    username = Column(String(16), nullable=False)
    password = Column(String(16), nullable=False)


class Conference(db.Model):
    __tablename__ = 'conference'

    conference_id = Column(Integer, primary_key=True)
    name = Column(String(8), unique=True, nullable=False)


class Paper(db.Model):
    __tablename__ = 'paper'

    paper_id = Column(Integer, primary_key=True, autoincrement=True)
    isbn = Column(String(32))
    title = Column(String(255), nullable=False)
    url = Column(String(255))
    time = Column(String(32))
    conference_id = Column(Integer, nullable=False)
    abstract = Column(Text)
    favorite_id = Column(Integer)
    accessionNumber = Column(Integer)


class Favorite(db.Model):
    __tablename__ = 'favorite'

    favorite_id = Column(Integer, primary_key=True, autoincrement=True)
    name = Column(String(255), default="收藏夹", nullable=False)
    user_id = Column(Integer, nullable=False)


class Author(db.Model):
    __tablename__ = 'author'

    author_id = Column(Integer, primary_key=True, autoincrement=True)
    name = Column(String(64), nullable=False)


class AuthorToPaper(db.Model):
    __tablename__ = 'author_to_paper'

    id = Column(Integer, primary_key=True, autoincrement=True)
    author_id = Column(Integer, nullable=False)
    paper_id = Column(Integer, nullable=False)


class Keyword(db.Model):
    __tablename__ = 'keyword'
    keyword_id = Column(Integer, primary_key=True, autoincrement=True)
    content = Column(String(128), unique=True, nullable=False)


class KeywordToConference(db.Model):
    __tablename__ = 'keyword_to_conference'

    id = Column(Integer, primary_key=True, autoincrement=True)
    keyword_id = Column(Integer, nullable=False)
    conference_id = Column(Integer, nullable=False)

    @staticmethod
    def getTopTen():
        return [446,1887,6,10708,342,10895,8511,81,3835,476]

class KeywordToPaper(db.Model):
    __tablename__ = 'keyword_to_paper'
    id = Column(Integer, primary_key=True, autoincrement=True)
    keyword_id = Column(Integer, nullable=False)
    paper_id = Column(Integer, nullable=False)


class PaperToFavorite(db.Model):
    __tablename__ = 'paper_to_favorite'
    id = Column(Integer, primary_key=True, autoincrement=True)
    paper_id = Column(Integer, nullable=False)
    favorite_id = Column(Integer, nullable=False)


