from flask import Flask
from flask_sqlalchemy import SQLAlchemy
import models

app = Flask(__name__)

app.config["SQLALCHEMY_DATABASE_URI"] = 'mysql://tempUser:tempPass123!@47.98.152.179:3306/xjbs-test'

app.config['SQLALCHEMY_COMMIT_ON_TEARDOWN'] = True
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = True

app.config['SQLALCHEMY_ECHO'] = True
db = SQLAlchemy(app)


@app.route('/')
def hello_world():
    paper = models.Paper.query.filter_by(paper_id=100).first()
    return str(models.Paper.query.filter_by(paper_id=100).all())


if __name__ == '__main__':
    app.run()
