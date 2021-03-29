from .base import db


class Paper(db.Model):
    __tablename__ = 'paper'
    paper_id = db.Column(db.Integer, primary_key=True)
    title = db.Column(db.String(255))
    link = db.Column(db.String(255))
    magazine = db.Column(db.String(255))
    publication_year = db.Column(db.String(255))
    abstract = db.Column(db.Text)

    def __repr__(self):
        return '<Paper %r>' % self.title
