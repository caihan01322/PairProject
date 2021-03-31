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

#用户注册接口
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


#论文列表查看
#无传递值
#返回值如下
# {'count':counts,
#         'page':page,
#         'data': {
#             "id": id,
#             "title": title,
#             "datetime": datetime,
#             "classify": classify
#         }
#  }
@app.route('/paper/list',methods=['GET'])
def getPaper():
    paper1 = request.get_json()
    page = int (paper1.get("page"))
    # 第几页 每页几条
    page_item = int(paper1.get("item"))
    m = int(page * page_item)
    print('select * from paper limit ' + str(m) + ',' + str(page_item) + ';')
    paper = db.session.execute('select * from paper limit ' + str(m) + ',' + str(page_item) + ';')
    Infos = []
    for p in paper:
        id = p.id
        title = p.title
        datetime = p.datetime
        classify = p.classify
        Infos.append({
            "id": id,
            "title": title,
            "datetime": datetime,
            "classify": classify
        })
    print(Infos)
    items = db.session.execute('select count(*) count from paper;')
    for i in items:
        counts = i.count
    # 总共几条
    counts = int(counts)
    page = int(int(counts)/int(page_item))+1

    return jsonify(
        {'count':counts,
        'page':page,
        'data': Infos}
    )


#论文详情
#url传递int型整数numberid
#返回值如下
# {       'numberID': paper.id,
#         'title': paper.title,
#         'abstract': paper.abstract,
#         'keyword': paper.keyWord,
#         'datetime': paper.datetime,
#         'href': paper.href,
#         'classify': paper.classify
#     }
@app.route('/paper/<int:numberid>',methods=['GET','POST'])
def paper_show(numberid):
    paper = db.session.query(Paper).filter(Paper.id == numberid).first()
    # print(paper.title)
    return jsonify({
        'numberID': paper.id,
        'title': paper.title,
        'abstract': paper.abstract,
        'keyword': paper.keyWord,
        'datetime': paper.datetime,
        'href': paper.href,
        'classify': paper.classify
    })


# 论文模糊查询
# 传递值：
# {
#     "page":"",
#     "item":"",
#     "KeyWord":""
# }
#返回值：
# {
#         'count': counts,
#         'page': page,
#         'data': {
#             "id": id,
#             "title": title,
#             "datetime": datetime,
#             "classify": classify
#         }
#     }
@app.route('/paper/search/Keyword',methods=['POST'])
def paper_search_keyword():
    paper1 = request.get_json()
    page = int(paper1.get("page")) - 1
    # 第几页 每页几条
    page_item = int(paper1.get("item"))
    key = str(paper1.get("keyword"))
    str1 = '%' + key + '%'
    m = int(page * page_item)
    print(m)
    # papers = db.session.query(Paper).filter(Paper.keyWord.like(str1))[int(m):int(page_item)]
    papers = db.session.execute(
        'select * from paper where keyWord like \'' + str1 + '\' limit ' + str(m) + ',' + str(page_item) + ';')
    # print(papers)
    # print('select count(*) count from paper where keyWord like \'%' + key + '%\';')
    items = db.session.execute('select count(*) count from paper where keyWord like \'%' + key + '%\';')
    for i in items:
        counts = i.count
    counts = int(counts)  # 总共几条
    print(counts)
    Infos = []
    for p in papers:
        id = p.id
        title = p.title
        datetime = p.datetime
        classify = p.classify
        Infos.append({
            "id": id,
            "title": title,
            "datetime": datetime,
            "classify": classify
        })

    page = int(int(counts) / int(page_item)) + 1
    return jsonify({
        'count': counts,
        'page': page,
        'data': Infos
    })


#论文模糊查询
# 传递值：
# {
#     "page":"",
#     "item":"",
#     "abstract":""
# }
#返回值：
# {
#         'count': counts,
#         'page': page,
#         'data': {
#             "id": id,
#             "title": title,
#             "datetime": datetime,
#             "classify": classify
#         }
#     }
@app.route('/paper/search/abstract',methods=['POST'])
def paper_search_abstract():
    paper1 = request.get_json()
    page = int(paper1.get("page")) - 1
    # 第几页 每页几条
    page_item = int(paper1.get("item"))
    key = str(paper1.get("abstract"))
    str1 = '%' + key + '%'
    m = int(page * page_item)

    papers = db.session.execute(
        'select * from paper where abstract like \'' + str1 + '\' limit ' + str(m) + ',' + str(page_item) + ';')
    print('select count(*) count from paper where abstract like \'%' + key + '\'%;')
    items = db.session.execute('select count(*) count from paper where abstract like \'%' + key + '%\';')
    for i in items:
        counts = i.count
    counts = int(counts)  # 总共几条
    print(counts)
    Infos = []
    for p in papers:
        id = p.id
        title = p.title
        datetime = p.datetime
        classify = p.classify
        Infos.append({
            "id": id,
            "title": title,
            "datetime": datetime,
            "classify": classify
        })

    page = int(int(counts) / int(page_item)) + 1
    return jsonify({
        'count': counts,
        'page': page,
        'data': Infos
    })

#论文模糊查询
# 传递值：
# {
#     "page":"",
#     "item":"",
#     "title":""
# }
#返回值：
# {
#         'count': counts,
#         'page': page,
#         'data': {
#             "id": id,
#             "title": title,
#             "datetime": datetime,
#             "classify": classify
#         }
#     }
@app.route('/paper/search/title',methods=['POST'])
def paper_search_title():
    paper1 = request.get_json()
    page = int(paper1.get("page")) - 1
    # 第几页 每页几条
    page_item = int(paper1.get("item"))
    key = str(paper1.get("titles"))
    str1 = '%' + key + '%'
    m = int(page * page_item)

    papers = db.session.execute(
        'select * from paper where title like \'' + str1 + '\' limit ' + str(m) + ',' + str(page_item) + ';')
    print('select count(*) count from paper where title like \'%' + key + '\'%;')
    items = db.session.execute('select count(*) count from paper where title like \'%' + key + '%\';')
    for i in items:
        counts = i.count
    counts = int(counts)  # 总共几条
    print(counts)
    Infos = []
    for p in papers:
        id = p.id
        title = p.title
        datetime = p.datetime
        classify = p.classify
        Infos.append({
            "id": id,
            "title": title,
            "datetime": datetime,
            "classify": classify
        })

    page = int(int(counts) / int(page_item)) + 1
    return jsonify({
        'count': counts,
        'page': page,
        'data': Infos
    })

#论文删除
# {
#     "numberid":"",
# }
# {
#     'abstract': paper.abstract,
#     'classify':paper.classify,
#     'datetime':paper.datetime,
#     'href':paper.href
#     'keyword':paper.keyWord,
#     'numberid':paper.numberid
#     'title':paper.title,
#
#     }
@app.route('/paper/delete/<string:numberid>',methods=['GET'])
def paper_delete(numberid):
    paper = db.session.query(Paper).filter(Paper.id == numberid).first()
    if (paper != None):
        print(jsonify({
            'numberID': paper.id,
            'title': paper.title,
            'abstract': paper.abstract,
            'keyword': paper.keyWord,
            'datetime': paper.datetime,
            'href': paper.href,
            'classify': paper.classify
        }))
        db.session.delete(paper)
        db.session.commit()
        return jsonify({"status": "ok"})
    else:
        return jsonify({"status": "error"})


#关键词查询
# 传递值：
# {
#     "page":"",
#     "item":"",
#     "title":""
# }
#返回值：
# {'data':
#      {
#          "name":"",
#          "value":""
#      }}
@app.route('/keyword',methods=['POST'])
def WordCount():
    # keyword = KeyWord()
    # keyword = db.session.query(KeyWord.keyword, func.count(KeyWord.id)).group_by(KeyWord.keyword).all()
    Infos = []
    m = 0
    keyword = db.session.execute('select keyword , count(*) as groupcount from keywords group by keyword order by count(*) desc').fetchall()
    for i in keyword:
        Infos.append({"name":i.keyword,"value":i.groupcount})
        m+=1
        if (m==10): break
        # print(i.keyword+" "+str(i.groupcount))
    return jsonify(data={'data':Infos})


#用户收藏页面
# 传递值：
# {
#     "paper_id":"",
#     "user_id":""
# }
# 返回值：
# {
# 'status':'1'
# }
@app.route('/collection',methods=['POST'])
def collect():
    collect1 = request.get_json()
    pid = collect1.get("paper_id")
    uid = collect1.get("user_id")

    paper1 = db.session.query(Paper).filter(Paper.id == pid).first()
    user1 = db.session.query(User).filter(User.id == uid).first()

    user1.papers.append(paper1)
    db.session.add(user1)
    db.session.commit()
    return jsonify({'status':'1'})


#个人中心
# 传递值：
# {
#     "user_id":"",
#     "page":"",
#     "item":""
# }
# 返回值：
# data={
#         'count': counts,
#         'page': page,
#         'telephone':username.telephone,
#         'data': {
#             "id": id,
#             "title": title,
#             "datetime": datetime,
#             "classify": classify
#         }
#     }
@app.route('/personal',methods=['POST'])
def personal():
    collect1 = request.get_json()
    uid = collect1.get("user_id")
    page = int(collect1.get("page"))-1
    # 第几页 每页几条
    page_item = int(collect1.get("item"))
    m = int(page * page_item)
    sel = db.session.execute('select paper_id,title,datetime,classify from user_paper,paper where user_paper.paper_id=paper.id and user_id = '+ uid+';')
    Infos = []
    for p in sel:
        id = p.paper_id
        title = p.title
        datetime = p.datetime
        classify = p.classify
        Infos.append({
            "id": id,
            "title": title,
            "datetime": datetime,
            "classify": classify
        })
    items = db.session.execute('select count(*) count from user_paper,paper where user_paper.paper_id=paper.id and user_id = '+ uid+';')
    for i in items:
        counts = i.count
    counts = int(counts)  # 总共几条
    page = int(int(counts) / int(page_item)) + 1
    username = db.session.query(User).filter(User.id==uid).first()

    return jsonify(data={
        'count': counts,
        'page': page,
        'telephone':username.telephone,
        'data': Infos
    })
#图表分析
@app.route('/analyword',methods=['GET'])
def analyword():
    date = ['2015','2016','2017','2018','2019','2020']
    names = ["ICCV","ECCV","CVPR"]
    series = [
        {
            "name":"ICCV",
            "type":"line",
            "stack":"总量",
            "data":[0,534,0,620,0,1034]
        },
        {
            "name": "ECCV",
            "type": "line",
            "stack": "总量",
            "data": [0,1354,0,1555,0,2500]
        },
        {
            "name": "CVPR",
            "type": "line",
            "stack": "总量",
            "data": [0,0,782,0,2205,3200]
        }
    ]

    return jsonify(
        {
            "date":date,
            "series":series,
            "names":names
        }
    )


if __name__ == '__main__':
    app.run(debug=True)
