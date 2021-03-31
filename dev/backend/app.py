from flask import Flask,request,json
from deal.Datadeal import DataBase

app = Flask(__name__)
data = DataBase()

@app.route('/')
def hello_world():
    return 'Hello World!'

@app.route('/api/user/search',methods=["GET"])
def search_ar():
    title = request.args.get("title")
    keywords = request.args.getlist("keywords")
    page = request.args.get("page")
    page = int(page)
    status = request.args.get("status")
    status = int(status)
    return json.dumps(data.Search(title,keywords,page,status))

@app.route('/api/user/show',methods=["GET"])
def showa():
    page = request.args.get("page")
    page = int(page)
    return json.dumps(data.ShowIndex(page))

@app.route('/api/user/title/tip',methods=["GET"])
def titletip():
    keyword = request.args.get("keyword")
    return json.dumps(data.TitleTip(keyword))

@app.route('/api/user/keyword/tip',methods=["GET"])
def keytip():
    keyword = request.args.get("keyword")
    return json.dumps(data.KeywordTip(keyword))

@app.route('/api/user/task/addone',methods=["POST"])
def addone():
    title = request.form.get("title")
    return json.dumps(data.AddTaskOne(title))

@app.route('/api/user/task/addall',methods=["POST"])
def addall():
    titlelist = request.form.getlist("titlelist")
    return json.dumps(data.AddTaskAll(titlelist))

@app.route('/api/user/task/delete',methods=["POST"])
def deletet():
    tasklist = request.form.getlist("task")
    return json.dumps(data.DeleteTask(tasklist))

@app.route('/api/user/title/delete',methods=["POST"])
def deletea():
    ids = request.form.getlist("id")
    return json.dumps(ids)

@app.route('/api/user/hotword/show',methods=["GET"])
def gethot():
    return json.dumps(data.ReHot())

@app.route('/api/user/hotword/caculate',methods=["GET"])
def cahot():
    meeting = request.args.get("meeting")
    page = request.args.get("page")
    page = int(page)
    meeting = int(meeting)
    return json.dumps(data.CaculateHot(meeting,page))

@app.route('/api/user/hotword/search',methods=["GET"])
def searchhot():
    keyword = request.args.get("keyword")
    stime = int(request.args.get("stime"))
    etime = int(request.args.get("etime"))
    return json.dumps(data.SearchHot(keyword,stime,etime))

@app.route('/api/user/spider/tasklist',methods=["GET"])
def gainspider():
    page = request.args.get("page")
    page = int(page)
    return json.dumps(data.ReSpider(page))

@app.route('/api/user/spider',methods=["POST"])
def spid():
    tasks = request.form.getlist("ids")
    return json.dumps(data.Spider(tasks))


if __name__ == '__main__':
    app.run()
