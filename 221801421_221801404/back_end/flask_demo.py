from flask import Flask
import pymysql
from flask import render_template
app = Flask(__name__)

def name():
    page = request.args.get('page')
    if not page or int(page) == 0:
        page = 1
    db = Mysql()
    keyword = request.args.get('keyword')
    items = db.getItems(keyword)
    key = db.getECCV()
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

        def getECCV(self):
                global ECCV2016
                global ECCV2018
                global ECCV2020
                global keyECCV
                sql1 = "SELECT keyword FROM paper where typeandyear='ECCV 2016' "
                sql2 = "SELECT keyword FROM paper where typeandyear='ECCV 2018' "
                sql3 = "SELECT keyword FROM paper where typeandyear='ECCV 2020' "
                self.cursor.execute(sql1)
                datalist = []
                alldata = self.cursor.fetchall()
                for s in alldata:
                    if s[0] is not None:
                        datalist.append(s[0])
                        str = ''.split(datalist) #将元组列表转化成字符串

                self.cursor.execute(sql2)
                datalist1 = []
                alldata1 = self.cursor.fetchall()
                for s1 in alldata1:
                    if s1[0] is not None:
                        datalist1.append(s1[0])

                        str1 = ''.join(datalist1)

            return str,str2018

if __name__ == '__main__':
 app.run(app.run(debug=True))


