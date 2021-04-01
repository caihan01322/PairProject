import pymysql
import requests
from spide.spfile import Spider
import json,datetime,time
from spide.spfile import Spider

class DataBase(object):
    def __init__(self):
        self.db = pymysql.connect(host="localhost",user="root",password="Fxn03166",db="spider",port=3306)

    def ShowIndex(self,page):
        self.db.ping(reconnect=True)
        cursor = self.db.cursor()
        limit = 10
        offset = (page-1)*10
        sql = "SELECT title,id,meeting,author,link FROM article "
        cursor.execute(sql)
        result = cursor.fetchall()
        data = []
        for rex in result[offset:offset+limit]:
            re = {
                "id": result[1],
                "title": result[0],
                "link": result[4],
                "meeting": result[2],
                "author": result[3]
            }
            data.append(re)
        res = {
            "error":0,
            "total":len(result),
            "result":data
        }
        return res

    def Search(self,title,keywords,page,status):   #论文检索  测试
        self.db.ping(reconnect=True)
        cursor = self.db.cursor()
        limit = 10
        offset = (page - 1)*10
        if title != "":
            if status != 0:
                sql = "SELECT title,id,meeting,author,link FROM article WHERE title like %s and meeting=%s "
                cursor.execute(sql, ('%'+title+'%', status))
            else:
                sql = "SELECT title,id,meeting,author,link FROM article WHERE title like %s "
                cursor.execute(sql,('%'+title+'%'))
            result = cursor.fetchall()
            if result is not None:
                dax = []
                for res in result[offset:offset+limit]:
                    sql = "SELECT keyword FROM k_a WHERE article=%s "
                    cursor.execute(sql,res[0])
                    key = cursor.fetchall()
                    keyword = []
                    for i in key:
                        keyword.append(i[0])
                    da = {
                        "id":res[1],
                        "title":res[0],
                        "link":res[4],
                        "keyword":keyword,
                        "meeting":res[2],
                        "author":res[3]
                    }
                    dax.append(da)
            else:
                dax = []
            data = {
                "error": 0,
                "total": len(result),
                "result": dax
            }
        else:
            titleset = set()
            for key in keywords:
                sql = "SELECT article FROM k_a WHERE keyword=%s"
                cursor.execute(sql,key)
                result = cursor.fetchall()
                for i in result:
                    titleset.add(i[0])
            rex = []
            tset = []
            for tit in titleset:
                tset.append(tit)
            tset = tset[offset:offset+limit]
            for tit in tset:
                if status == 0:
                    sql = "SELECT title,id,meeting,author,link FROM article WHERE title=%s "
                    cursor.execute(sql, (tit))
                else:
                    sql = "SELECT title,id,meeting,author,link FROM article WHERE title=%s AND meeting=%s "
                    cursor.execute(sql, (tit, status))
                result = cursor.fetchone()
                if result is not None:
                    sql = "SELECT keyword FROM k_a WHERE article=%s "
                    cursor.execute(sql, tit)
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

    def TitleTip(self,keyword):              #文章提示  测试
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

    def KeywordTip(self,keyword):                  #关键词提示  测试
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

    def DeleteArticle(self,ids):                        #删除文章   测试
        self.db.ping(reconnect=True)
        cursor = self.db.cursor()
        for i in ids:
            sql = "SELECT keyword FROM k_a,article WHERE id=%s AND article.title=k_a.article "
            cursor.execute(sql,i)
            kset = cursor.fetchall()
            for key in kset:
                sql = "SELECT count FROM hotword WHERE hotword=%s "
                cursor.execute(sql,key[0])
                if cursor.fetchone()[0] == 1:
                    sql = "DELETE FROM hotword WHERE hotword=%s "
                else:
                    sql = "UPDATE hotword SET count=count-1 WHERE hotword=%s "
                cursor.execute(sql,key[0])
                self.db.commit()
            sql = "SELECT title FROM article WHERE id=%s "
            cursor.execute(sql,i)
            title = cursor.fetchone()[0]
            sql = "DELETE FROM k_a WHERE article=%s "
            cursor.execute(sql, title)
            self.db.commit()
            sql = "DELETE FROM article WHERE id=%s "
            cursor.execute(sql,i)
            self.db.commit()
        data = {
            "error_code":0
        }
        cursor.close()
        self.db.close()
        return data

    def SearchHot(self,keyword,stime,etime):                   #热词查询  测试
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
                "date":stime,
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

    def CaculateHot(self,meeting,page):     #热门关键词统计   测试
        self.db.ping(reconnect=True)
        cursor = self.db.cursor()
        start = (page-1)*10
        sql = "SELECT DISTINCT hotword.hotword,hotword.count FROM hotword,k_a WHERE k_a.meeting=%s AND k_a.keyword=hotword.hotword ORDER BY hotword.count DESC "
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
            "total":len(result),
            "result":data
        }
        cursor.close()
        self.db.close()
        return res

    def ReHot(self):               #热词图谱  测试
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

    def DeleteTask(self,tasklist):     #删除任务  测试
        self.db.ping(reconnect=True)
        cursor = self.db.cursor()
        for task in tasklist:
            sql = "DELETE FROM task WHERE task_key=%s "
            cursor.execute(sql,task)
            self.db.commit()
            sql = "DELETE FROM task_title WHERE task_key=%s "
            cursor.execute(sql, task)
            self.db.commit()
        data = {
            "error":0
        }
        cursor.close()
        self.db.close()
        return data

    def AddTaskAll(self,titlelist):      #添加任务 多 测试
        for i in titlelist:
            self.AddTaskOne(i)
        data = {
            "error": 0
        }
        return data

    def AddTaskOne(self,title):            #添加任务 单  测试
        self.db.ping(reconnect=True)
        cursor = self.db.cursor()
        start = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        time.sleep(2)
        sql = "INSERT INTO task (datetime) VALUES (%s) "
        cursor.execute(sql,start)
        self.db.commit()
        cursor.close()
        self.db.close()
        self.db.ping(reconnect=True)
        cursor = self.db.cursor()
        sql = "SELECT task_key FROM task WHERE datetime=%s "
        cursor.execute(sql,start)
        key = cursor.fetchone()[0]
        sql = "INSERT INTO task_title (task_key, title) VALUES (%s,%s) "
        cursor.execute(sql,(key,title))
        self.db.commit()
        cursor.close()
        self.db.close()
        data = {
            "error":0
        }
        return data

    def InsertHot(self,keyword):            #插入关键词   测试
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

    def ReSpider(self,page):                #返回爬虫任务  测试
        self.db.ping(reconnect=True)
        cursor = self.db.cursor()
        start = (page-1)*10
        sql = "SELECT task.task_key,task.datetime,task_title.title FROM task,task_title WHERE task.task_key=task_title.task_key "
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

    def InsertArticle(self,title, abstract, name, link, year, meeting):        #插入文章  测试
        self.db.ping(reconnect=True)
        cursor = self.db.cursor()
        sql = "INSERT INTO article (title, abstract, author, link, year,meeting) values (%s,%s,%s,%s,%s,%s) "
        cursor.execute(sql, (title, abstract, name, link, year, meeting))
        self.db.commit()
        cursor.close()
        self.db.close()

    def Spider(self,tasks):          #爬虫
        self.db.ping(reconnect=True)
        cursor = self.db.cursor()
        titles = []
        for task in tasks:
            sql = "SELECT title FROM task_title WHERE task_key=%s "
            cursor.execute(sql,task)
            titles.append(cursor.fetchone()[0])
        cursor.close()
        self.db.close()
        sp = Spider()
        for title in titles:
            data = sp.SpiderOne(title)
            eccv = data["1"]
            iccv = data["2"]
            cvpr = data["3"]
            for ec in eccv:
                self.InsertArticle(ec["title"],ec["abstract"],"NO",ec["titlelink"],ec["year"],1)
            for icc in iccv:
                for ic in icc:
                    self.InsertArticle(ic["title"], ic["abstract"], "NO", ic["titlelink"], ic["year"], 2)
            for cvp in cvpr:
                for cv in cvp:
                    self.InsertArticle(cv["title"], cv["abstract"], "NO", cv["titlelink"], cv["year"], 3)
        result = {
            "error":0
        }
        return result









if __name__ == "__main__":
    d = DataBase()
    #print(d.TitleTip("3"))
    #print(d.InsertHot("0"))
    #print(d.Search("",["Temporal coherence"],1,1))
    #print(d.KeywordTip('object'))
    #print(d.SearchHot('Surface alignment',2000,2020))
    #print(d.CaculateHot(1,1))
    #print(d.ReHot())
    #print(d.DeleteArticle([15520,15521]))
    #print(d.AddTaskAll(["A Benchmark and Simulator for UAV Tracking"]))
    #print(d.DeleteTask([7]))
    #print(d.ReSpider(1))
    #print(d.CaculateHot(1,2))
    #print(d.test())
    #print(d.find())
    #print(d.ShowIndex(1))
    #print(d.Spider([9]))
    #print(d.DeleteTask([8,9]))
    #print(d.I())