
from .base import db


class Keyword_count(db.Model):
    __tablename__ = 'keyword_count'
    keyword_count_id = db.Column(db.Integer, primary_key=True)
    keyword = db.Column(db.String(255))
    count = db.Column(db.Integer)

    def __repr__(self):
        return '<keyword %r>' % self.keyword
