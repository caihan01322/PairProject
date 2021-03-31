from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from werkzeug.middleware.dispatcher import DispatcherMiddleware

app = Flask(__name__)


app.config["SQLALCHEMY_DATABASE_URI"] = 'mysql://username:password!@ip:port/xjbs'

app.config['SQLALCHEMY_COMMIT_ON_TEARDOWN'] = True
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = True

app.config['SQLALCHEMY_ECHO'] = True
db = SQLAlchemy(app)
