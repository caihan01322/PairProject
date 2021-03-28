from os import abort
import random
import encodings.idna
import json
from os import listdir
from flask import Flask, render_template
from flask import Flask, jsonify
from flask import make_response
from flask import request
from flask_sqlalchemy import SQLAlchemy
from sqlalchemy import ForeignKey, Table, func
from sqlalchemy.orm import relationship
from sqlalchemy import create_engine,Column,Integer,String
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
import pymysql

app = Flask(__name__)

DIALECT = 'mysql'
DRIVER = 'pymysql'
USERNAME = 'root'
PASSWORD = '1234'

HOST = '127.0.0.1'
PORT = '3306'
DATABASE = 'analywords'

SQLALCHEMY_DATABASE_URI = "{}+{}://{}:{}@{}:{}/{}?charset=utf8".format(DIALECT, DRIVER, USERNAME, PASSWORD, HOST, PORT, DATABASE)
#'mysql+pymysql://user1:123@127.0.0.1:3306/api?charset=utf8'
SQLALCHEMY_TRACK_MODIFICATIONS = True
SQLALCHEMY_COMMIT_TEARDOWN = True

app.config['SQLALCHEMY_DATABASE_URI'] = SQLALCHEMY_DATABASE_URI
app.config['SQLALCHEMY_COMMIT_ON_TEARDOWN'] = True

db = SQLAlchemy(app)

class User(db.Model):
    __tablename__ = 'user'
    id = db.Column(db.Integer,primary_key=True,autoincrement=True)
    telephone = db.Column(db.String(11))
    password = db.Column(db.String(30))
    # def __str__(self):
    #     return "<User(telephone:%s,password:%s)>" % (self.telephone,self.password)

class Paper(db.Model):
    __tablename__ = 'paper'
    id = db.Column(db.Integer,primary_key=True,autoincrement=True)
    numberID = db.Column(db.String(200))
    title = db.Column(db.String(300))
    keyWord = db.Column(db.Text)
    abstract = db.Column(db.Text)
    datetime = db.Column(db.String(100))
    href = db.Column(db.String(500))
    classify = db.Column(db.String(100))

# db.drop_all()
db.create_all()

#用户注册
# {
#     "telephone":"",
#     "password":"",
#     "repassword":""
# }
# {
#     "error":""
# }
# {
#     "status":"ok"
# }
@app.route('/user/register',methods=['POST'])
def user_register():
    user1 = request.get_json()
    print(user1)
    user = User()
    tele = user1.get("telephone")
    pass1 = user1.get("password")
    pass2 = user1.get("repassword")
    if (len(tele)!=11):
        return jsonify({'error': '电话号码错误'})
    elif (pass1 != pass2):
        return jsonify({'error': '两次密码不相同'})
    else:
        user.telephone = tele
        user.password = pass1
    db.session.add(user)
    db.session.commit()
    return jsonify({'status':'ok'})

if __name__ == '__main__':
    app.run(debug=True)