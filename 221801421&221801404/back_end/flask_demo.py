from flask import Flask
app = Flask(__name__)

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

if __name__ == '__main__':
    app.run(app.run(debug=True))