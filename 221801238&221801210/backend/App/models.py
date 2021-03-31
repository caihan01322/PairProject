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
    conference=['','CVPR','ECCV','ICCV']
    conference_id = Column(Integer, primary_key=True)
    name = Column(String(8), unique=True, nullable=False)

    @staticmethod
    def getConference(id):
        return Conference.conference[id]


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
    cache=[]
    PAGE_NUM=5
    currentNum=0
    finish=False

    @staticmethod
    def setCache(paperlist):
        Paper.cache=paperlist
        Paper.finish=True

    @staticmethod
    def getPage(page):
        page=int(page)
        return Paper.cache[page*Paper.PAGE_NUM:(page+1)*Paper.PAGE_NUM]

    @staticmethod
    def getPaperDict(p_id):
        paper = dict()
        p = db.session.query(Paper).filter(Paper.paper_id == p_id).first()
        paper['title'] = p.title
        paper['abstract'] = p.abstract
        paper['url'] = p.url
        paper['isbn'] = p.isbn
        paper['time'] = p.time
        paper['id'] = str(p.paper_id)
        paper['conference'] = Conference.getConference(p.conference_id)
        ptoks = db.session.query(KeywordToPaper).filter(KeywordToPaper.paper_id == p.paper_id).all()
        keywords = []
        for ptok in ptoks:
            keyword = db.session.query(Keyword).filter(Keyword.keyword_id == ptok.keyword_id).first()
            keywords.append(keyword.content)

        # 作者
        ptoas = db.session.query(AuthorToPaper).filter(AuthorToPaper.paper_id == p.paper_id).all()
        authors = []
        for ptoa in ptoas:
            author = db.session.query(Author).filter(Author.author_id == ptoa.author_id).first()
            authors.append(author.name)
        paper['authors'] = authors
        paper['keywords'] = keywords
        return paper

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


