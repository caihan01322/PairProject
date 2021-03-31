import json

from .base import db


class Paper(db.Model):
    __tablename__ = 'paper'
    paper_id = db.Column(db.Integer, primary_key=True)
    title = db.Column(db.String(255))
    link = db.Column(db.String(255))
    magazine = db.Column(db.String(255))
    publication_year = db.Column(db.String(255))
    abstract = db.Column(db.Text)

    def __init__(self,paper_id,title,link,magazine,publication_year,abstract):
        self.paper_id = paper_id
        self.title = title
        self.link = link
        self.magazine = magazine
        self.publication_year = publication_year
        self.abstract = abstract

    def __str__(self):
        return json.dumps({
            "paper_id": self.paper_id,
            "title": self.title,
            "link": self.link,
            "magazine": self.magazine,
            "publication_year": self.publication_year,
            "abstract": self.abstract
        })

    def __repr__(self):
        return repr(self.paper_id, self.title, self.link, self.magazine, self.publication_year,self.abstract)

    # return 'Paper {\n\"paper_id\": \"%r\",\n\"title\": \"%r\",\n\"link\": \"%r\",\n\"magazine\": \"%r\",' \
    #                '\n\"publication_year\": \"%r\",\n\"abstract\": \"%r\"\n}' % self.paper_id % self.title % self.link % self.magazine % self.publication_year % self.abstract

