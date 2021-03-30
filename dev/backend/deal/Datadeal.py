import pymysql
import requests
from spide.spfile import Spider
import json,datetime

class DataBase(object):
    def __init__(self,):
        self.db = pymysql.connect(host="localhost",user="root",password="Fxn03166",db="spider",port=3306)

    def Search(self,title,keywords,page,status):   #论文检索
        self.db.ping(reconnect=True)
        cursor = self.db.cursor()
        limit = 10
        offset = (page - 1)*10
        if title != "":
            if status != 0:
                sql = "SELECT title,id,meeting,author,link FROM article WHERE title=%s and meeting=%s "
                cursor.execute(sql, (title, status))
            else:
                sql = "SELECT title,id,meeting,author,link FROM article WHERE title=%s "
                cursor.execute(sql,(title))
            result = cursor.fetchone()
            sql = "SELECT keyword FROM k_a WHERE article=%s "
            cursor.execute(sql,title)
            key = cursor.fetchall()
            keyword = []
            for i in key:
                keyword.append(i[0])
            data = {
                "error":0,
                "total":1,
                "result":[
                    {
                        "id":result[1],
                        "title":result[0],
                        "link":result[4],
                        "keyword":keyword,
                        "meeting":result[2],
                        "author":result[3]
                    }
                ]
            }
        else:
            titleset = {}
            for key in keywords:
                sql = "SELECT article FROM k_a WHERE keyword=%s"
                cursor.execute(sql,key)
                result = cursor.fetchall()
                for i in result:
                    titleset.add(result[0])
            rex = []
            for tit in titleset[offset:offset+limit]:
                if status == 0:
                    sql = "SELECT title,id,meeting,author,link FROM article WHERE title=%s "
                    cursor.execute(sql, (title))
                else:
                    sql = "SELECT title,id,meeting,author,link FROM article WHERE title=%s AND meeting=%s "
                    cursor.execute(sql, (title, status))
                result = cursor.fetchone()
                sql = "SELECT keyword FROM k_a WHERE article=%s "
                cursor.execute(sql, title)
                key = cursor.fetchall()
                keyword = []
                for i in key:
                    keyword.append(i[0])
                subkey = {
                    "id": result[1],
                    "title": result[0],
                    "link": result[4],
                    "keyword": keyword,
                    "meeting": result[2],
                    "author": result[3]
                }
                rex.append(subkey)
            data = {
                "error": 0,
                "total": len(titleset),
                "result": rex
            }
        cursor.close()
        self.db.close()
        return data

    def TitleTip(self,keyword):              #文章提示
        self.db.ping(reconnect=True)
        cursor = self.db.cursor()
        sql = "SELECT title FROM article WHERE title LIKE %s "
        cursor.execute(sql,'%'+keyword+'%')
        result = cursor.fetchall()
        if len(result) > 10:
            rex = result[:10]
        else:
            rex = result
        data = []
        for i in rex:
            data.append(i[0])
        resultx = {
            "error_code":0,
            "result":data
        }
        cursor.close()
        self.db.close()
        return resultx

    def KeywordTip(self,keyword):                  #关键词提示
        self.db.ping(reconnect=True)
        cursor = self.db.cursor()
        sql = "SELECT keyword FROM k_a WHERE keyword LIKE %s "
        cursor.execute(sql, '%' + keyword + '%')
        result = cursor.fetchall()
        if len(result) > 10:
            rex = result[:10]
        else:
            rex = result
        data = []
        for i in rex:
            data.append(i[0])
        resultx = {
            "error_code": 0,
            "result": data
        }
        cursor.close()
        self.db.close()
        return resultx

    def DeleteArticle(self,ids):                        #删除文章
        self.db.ping(reconnect=True)
        cursor = self.db.cursor()
        for i in ids:
            sql = "DELETE FROM article WHERE id=%s "
            cursor.execute(sql,id)
            self.db.commit()
        data = {
            "error_code":0
        }
        cursor.close()
        self.db.close()
        return data

    def SearchHot(self,keyword,stime,etime):                   #热词查询
        self.db.ping(reconnect=True)
        cursor = self.db.cursor()
        result = []
        while stime <= etime:
            sql = "SELECT count(article.title) FROM article,k_a WHERE article.year=%s AND article.title=k_a.article AND k_a.keyword=%s AND article.meeting=1 "
            cursor.execute(sql,(stime,keyword))
            eccv = cursor.fetchone()[0]
            sql = "SELECT count(article.title) FROM article,k_a WHERE article.year=%s AND article.title=k_a.article AND k_a.keyword=%s AND article.meeting=2 "
            cursor.execute(sql, (stime, keyword))
            iccv = cursor.fetchone()[0]
            sql = "SELECT count(article.title) FROM article,k_a WHERE article.year=%s AND article.title=k_a.article AND k_a.keyword=%s AND article.meeting=3 "
            cursor.execute(sql, (stime, keyword))
            cvpr = cursor.fetchone()[0]
            data = {
                "data":stime,
                "CVPR":cvpr,
                "ICCV":iccv,
                "ECCV":eccv
            }
            result.append(data)
            stime += 1
        rex = {
            "error_code":0,
            "result":result
        }
        cursor.close()
        self.db.close()
        return rex

    def CaculateHot(self,meeting,page):     #热门关键词统计
        self.db.ping(reconnect=True)
        cursor = self.db.cursor()
        start = (page-1)*10
        sql = "SELECT DISTINCT hotword.hotword,hotword.count FROM hotword,k_a,article WHERE article.meeting=%s AND k_a.article=article.title AND k_a.keyword=hotword.hotword "
        cursor.execute(sql,meeting)
        result = cursor.fetchall()
        data = []
        for rex in result[start:start+10]:
            da = {
                "keyword":rex[0],
                "pages":rex[1],
                "riserate":0.25
            }
            data.append(da)
        res = {
            "error_code":0,
            "result":data
        }
        cursor.close()
        self.db.close()
        return res

    def ReHot(self):               #热词图谱
        self.db.ping(reconnect=True)
        cursor = self.db.cursor()
        sql = "SELECT hotword,count FROM hotword "
        cursor.execute(sql)
        result = cursor.fetchall()
        data = []
        for rex in result:
            x = {
                "x":rex[0],
                "value":rex[1]
            }
            data.append(x)
        rexa = {
            "error_code":0,
            "result":data
        }
        cursor.close()
        self.db.close()
        return rexa

    def DeleteTask(self,task):
        self.db.ping(reconnect=True)
        cursor = self.db.cursor()
        sql = "DELETE FROM task WHERE task_key=%s "
        cursor.execute(sql,task)
        self.db.commit()
        sql = "DELETE FROM task_title WHERE task=%s "
        cursor.execute(sql, task)
        self.db.commit()
        data = {
            "error":0
        }
        cursor.close()
        self.db.close()
        return data

    def AddTaskAll(self,titlelist):
        for i in titlelist:
            self.AddTaskOne(i)
        data = {
            "error": 0
        }
        return data

    def AddTaskOne(self,title):
        self.db.ping(reconnect=True)
        cursor = self.db.cursor()
        start = datetime.datetime.now()
        sql = "INSERT INTO task (stime) VALUES (%s) "
        cursor.execute(sql,start)
        self.db.commit()
        cursor.close()
        self.db.close()
        self.db.ping(reconnect=True)
        cursor = self.db.cursor()
        sql = "SELECT task_key FROM task WHERE stime=%s "
        cursor.execute(sql,start)
        key = cursor.fetchone()[0]
        sql = "INSERT INTO task_title (task, title) VALUES (%s,%s) "
        cursor.execute(sql,(key,title))
        self.db.commit()
        cursor.close()
        self.db.close()
        data = {
            "error":0
        }
        return data

    def InsertHot(self,keyword):
        self.db.ping(reconnect=True)
        cursor = self.db.cursor()
        sql = "SELECT * FROM hotword WHERE hotword=%s "
        cursor.execute(sql,keyword)
        re = cursor.fetchone()
        if re is None:
            sql = "INSERT INTO hotword (hotword, `count`) VALUES (%s,%s)"
            cursor.execute(sql,(keyword,1))
            self.db.commit()
        else:
            sql = "UPDATE hotword SET count=count+1 WHERE hotword=%s "
            cursor.execute(sql,keyword)
            self.db.commit()
        data = {
            "error_code": 0
        }
        cursor.close()
        self.db.close()
        return data

    def ReSpider(self,page):
        self.db.ping(reconnect=True)
        cursor = self.db.cursor()
        start = (page-1)*10
        sql = "SELECT task.task_key,task.stime,task_title.title FROM task,task_title WHERE task.task_key=task_title.task "
        cursor.execute(sql)
        result = []
        data = cursor.fetchall()
        for da in data[start:start+10]:
            p = {
                "title":da[2],
                "id":da[0],
                "time":da[1]
            }
            result.append(p)
        rex = {
            "error":0,
            "total":len(data),
            "result":result
        }
        return rex



if __name__ == "__main__":
    d = DataBase()
    #print(d.TitleTip("3"))
    #print(d.InsertHot("0"))
    print(d.test())