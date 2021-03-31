#论文导入ECCV
#传递值无
#返回值{"status":"ok"}

@app.route('/paper/add',methods=['POST'])
def addPaper():
    path = "C:\paper"
    fileList = listdir(path)
    fileIndex = []
    for i in range(0,len(fileList)):
        index = fileList[i].split(".json")[0]
        path1 = path + '\\' + index + '.json'
        resolveJson(path1)
    return jsonify({"status":"ok"})

def judge(key,Json):
    if (key in Json):
        return True
    else:
        return False

#ECCV
def resolveJson(path):
    file = open(path,'rb')
    paper = Paper()
    fileJson = json.loads(file.read().decode('utf-8'))
    abstract = fileJson["摘要"]
    keyword = fileJson["关键词"]
    key = ",".join(map(str,keyword))
    datetime = fileJson["发布时间"]
    href = fileJson["原文链接"]
    title = fileJson["论文名称"]

    paper.abstract = abstract
    paper.keyWord = key
    paper.datetime = datetime
    paper.href = href
    paper.title = title
    paper.classify = 'ECCV'
    db.session.add(paper)
    db.session.commit()

    file.close()
    return jsonify({"status":"ok"})



#关键词导入ECCV
@app.route('/keyword/add',methods=['POST'])
def addWord():
    path = "C:\paper"
    fileList = listdir(path)
    fileIndex = []
    for i in range(0,len(fileList)):
        index = fileList[i].split(".json")[0]
        path1 = path + '\\' + index + '.json'
        resolveJson_word(path1)
    return jsonify({"status":"ok"})

def resolveJson_word(path):
    file = open(path,'rb')

    fileJson = json.loads(file.read().decode('utf-8'))
    keywords = fileJson["关键词"]
    title = fileJson["论文名称"]
    for i in keywords:
        keywords = KeyWords()
        keywords.keyword = i
        keywords.title = title
        db.session.add(keywords)
        db.session.commit()

    file.close()
    return jsonify({"status":"ok"})
