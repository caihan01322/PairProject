import json,os,pymysql

def parse_json(filex):
    file = open(filex, encoding='utf-8')
    rex = file.read()[:len(file.read()) - 1]
    js = json.loads(rex)
    print(file.name)
    if "title" in js.keys():
        title = js["title"]
        if "keywords" in js.keys():
            if "kwd" in js["keywords"][0].keys():
                keywords = js["keywords"][0]["kwd"]
            else:
                keywords = []
        else:
            keywords = []
        if "abstract" in js.keys():
            abstract = js["abstract"]
        else:
            abstract = "NO"
        if "authors" in js.keys():
            authors = js["authors"]
            name = ""
            for author in authors:
                if "name" in author:
                    name += author["name"]
                    name += " "
        else:
            name = "NO"
        if "doiLink" in js.keys():
            link = js["doiLink"]
        else:
            link = "NO"
        if "publicationYear" in js.keys():
            year = js["publicationYear"]
        else:
            year = 0
        insert(keywords, title, abstract, name, link, year, 2)
    else:
        title = "NO"
    print(title)
    return title

def fileopen():
    dirlist1 = os.listdir('F:\\papers\\DATA\\ICCV')
    for filename in dirlist1:
        file = 'F:\\papers\\DATA\\ICCV\\' + filename
        parse_json(file)


def find():
    conn = pymysql.connect(host="localhost", user="root", password="Fxn03166", db="spider", port=3306)
    cursor = conn.cursor()
    sql = "select * from article where meeting=3"
    cursor.execute(sql)
    print(cursor.fetchone())
    cursor.close()
    conn.close()

def eccv(filex):
    file = open(filex, encoding='utf-8')
    rex = file.read()
    js = json.loads(rex)
    print(file.name)
    if "论文名称" in js.keys():
        title = js["论文名称"]
        if "关键词" in js.keys():
            keywords = js["关键词"]
        else:
            keywords = []
        if "摘要" in js.keys():
            abstract = js["摘要"]
        else:
            abstract = "NO"
        if "authors" in js.keys():
            authors = js["authors"]
            name = ""
            for author in authors:
                if "name" in author:
                    name += author["name"]
                    name += " "
        else:
            name = "NO"
        if "原文链接" in js.keys():
            link = js["原文链接"]
        else:
            link = "NO"
        if "发布时间" in js.keys():
            year = js["发布时间"][-4:]
        else:
            year = 0
        insert(keywords, title, abstract, name, link, year, 1)
    else:
        title = "NO"
    print(title)
    return title



find()



