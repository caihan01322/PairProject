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
                return  items

if __name__ == '__main__':
app.run(app.run(debug=True))


