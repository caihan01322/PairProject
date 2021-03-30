import json
import pymysql
from flask import Flask, request
from flask import render_template
app = Flask(__name__)

def name():
    page = request.args.get('page')
    if not page or int(page) == 0:
        page = 1
    db = Mysql()
    keyword = request.args.get('keyword')
    items = db.getItems(keyword)
    # keyhot = db.getECCV()
    key = db.getCVPR()
    key1 = db.getECCV()
    key2 = db.getICCV()
    # print(keyhot)
    # print(items)
    page_range = range(int(page) - 3, int(page) + 2)
    if int(page) < 4:
        page_range = range(1, int(page) + 4)
    return render_template('articles.html', items=items, page=int(page), prange=page_range)

@app.route('/')
class Mysql(object):
    def __init__(self):
        try:
            self.conn = pymysql.connect(host='localhost', user='root', password='123456', database='article',
                                        charset="utf8")
            self.cursor = self.conn.cursor()  # 游标对象
            print("连接数据库成功")
        except:
            print("连接失败")

            def getItems(self, keyword):
                sql = "select * from paper "
                if keyword:
                    sql = sql + " where title like '%" + keyword + "%'"
                self.cursor.execute(sql)
                items = self.cursor.fetchall()
                # print(items)
                # items=json.dumps(items, ensure_ascii=False)

                #转化成json格式，对值进行命名
                papers = []
                for r in items:
                    paper = {}
                    paper['id'] = r[0]
                    paper['title'] = r[1]
                    paper['abstractContext'] = r[2]
                    paper['typeandyear'] = r[3]
                    paper['keyword'] = r[4]
                    paper['releasetime'] = r[5]
                    paper['link'] = r[6]

                    papers.append(paper)
                items = json.dumps(paper)
                return  items

        # def getECCV(self):
        #         global ECCV2016
        #         global ECCV2018
        #         global ECCV2020
        #         global keyECCV
        #         sql1 = "SELECT keyword FROM paper where typeandyear='ECCV 2016' "
        #         sql2 = "SELECT keyword FROM paper where typeandyear='ECCV 2018' "
        #         sql3 = "SELECT keyword FROM paper where typeandyear='ECCV 2020' "
        #         self.cursor.execute(sql1)
        #         datalist = []
        #         alldata = self.cursor.fetchall()
        #         for s in alldata:
        #             if s[0] is not None:
        #                 datalist.append(s[0])
        #                 str = ''.split(datalist) #将元组列表转化成字符串
        #
        #         self.cursor.execute(sql2)
        #         datalist1 = []
        #         alldata1 = self.cursor.fetchall()
        #         for s1 in alldata1:
        #             if s1[0] is not None:
        #                 datalist1.append(s1[0])
        #
        #                 str1 = ''.join(datalist1)
        #
        #     return str,str2018

    def getCVPR(self):
        global CVPR
        datalist =[]
        a="2018"
        b="2019"
        c="2020"
        CVPR=[]
        CVPR.append(a)
        CVPR.append(b)
        CVPR.append(c)

        for index in range(len(CVPR)):

            SQL = "SELECT keyword,frequency,publishyear FROM `article`.`keywordanalysis`" \
                  " WHERE `type` LIKE '%CVPR%' AND `publishyear` LIKE '%"+CVPR[index]+"%' ORDER BY `frequency` DESC LIMIT 1"
            # print(SQL)
            self.cursor.execute(SQL)
            alldata = self.cursor.fetchall()
            datalist.append(alldata[0])
            print(datalist)
            # print(index)
            continue

        papers = []
        for r in datalist:
            paper = {}
            paper['year'] = r[2]
            paper['keyword'] = r[0]
            paper['count'] = r[1]
            papers.append(paper)
            continue

        paperJSON = json.dumps(papers)
        print(paperJSON)
        return paperJSON

    def getECCV(self):
        global ECCV
        datalist =[]
        a="2016"
        b="2018"
        c="2020"
        ECCV=[]
        ECCV.append(a)
        ECCV.append(b)
        ECCV.append(c)

        for index in range(len(ECCV)):

            SQL = "SELECT keyword,frequency,publishyear FROM `article`.`keywordanalysis`" \
                  " WHERE `type` LIKE '%ECCV%' AND `publishyear` LIKE '%"+ECCV[index]+"%' ORDER BY `frequency` DESC LIMIT 1"
            # print(SQL)
            self.cursor.execute(SQL)
            alldata = self.cursor.fetchall()
            datalist.append(alldata[0])
            print(datalist)
            # print(index)
            continue

        papers = []
        for r in datalist:
            paper = {}
            paper['year'] = r[2]
            paper['keyword'] = r[0]
            paper['count'] = r[1]
            papers.append(paper)
            continue

        paperJSON = json.dumps(papers)
        print(paperJSON)
        return paperJSON

    def getICCV(self):
        global ICCV
        datalist = []
        a = "2015"
        b = "2017"
        c = "2019"
        ICCV = []
        ICCV.append(a)
        ICCV.append(b)
        ICCV.append(c)

        for index in range(len(ICCV)):
            SQL = "SELECT keyword,frequency,publishyear FROM `article`.`keywordanalysis`" \
                  " WHERE `type` LIKE '%ICCV%' AND `publishyear` LIKE '%" + ICCV[
                      index] + "%' ORDER BY `frequency` DESC LIMIT 1"
            # print(SQL)
            self.cursor.execute(SQL)
            alldata = self.cursor.fetchall()
            datalist.append(alldata[0])
            print(datalist)
            # print(index)
            continue

        papers = []
        for r in datalist:
            paper = {}
            paper['year'] = r[2]
            paper['keyword'] = r[0]
            paper['count'] = r[1]
            papers.append(paper)
            continue

        paperJSON = json.dumps(papers)
        print(paperJSON)
        return paperJSON


if __name__ == '__main__':
 app.run(app.run(debug=True))


