import pymysql
import requests
from spide.spfile import Spider

class DataBase(object):
    def __init__(self,):
        self.db = pymysql.connect(host="localhost",user="root",password="Fxn03166",db="spider",port=3306)

    def Search(self,title,keywords,page,status):

        return

    def TitleTip(self,keyword):
        return
    def KeywordTip(self,keyword):
        return
    def DeleteArticle(self,title):
        return
    def SearchHot(self,keyword,stime,etime):
        return
    def CaculateHot(self,meeting,page):
        return
    def ReHot(self):
        return
    def DeleteTask(self,task):
        return

    def AddTask(self,keyword):
        return

    def InsertBySpiderI(self):
        ic1 = "https://openaccess.thecvf.com/ICCV2019?day=2019-10-29"
        ic2 = "https://openaccess.thecvf.com/ICCV2019?day=2019-10-30"
        ic3 = "https://openaccess.thecvf.com/ICCV2019?day=2019-10-31"
        ic4 = "https://openaccess.thecvf.com/ICCV2019?day=2019-11-01"
        ic5 = "https://openaccess.thecvf.com/ICCV2017"
        ic6 = "https://openaccess.thecvf.com/ICCV2015"
        r1 = requests.get(ic1)
        r2 = requests.get(ic2)
        r3 = requests.get(ic3)
        r4 = requests.get(ic4)
        r5 = requests.get(ic5)
        r6 = requests.get(ic6)
        s = Spider()
        d1 = s.ParseResponse(r1.text)
        d2 = s.ParseResponse(r2.text)
        d3 = s.ParseResponse(r3.text)
        d4 = s.ParseResponse(r4.text)
        d5 = s.ParseResponse(r5.text)
        d6 = s.ParseResponse(r6.text)
        cursor = self.db.cursor()
        for i in d1:
            sql = "insert into article (title,abstract,`year`,`month`,link,meeting) values ('%s','%s','%s','%s','%s','%s')" \
            % (i["title"],i["abstract"],i["year"],i["month"],i["titlelink"],2)
            cursor.execute(sql)
            self.db.commit()
        for i in d2:
            sql = "insert into article (title,abstract,`year`,`month`,link,meeting) values ('%s','%s','%s','%s','%s','%s')" \
            % (i["title"],i["abstract"],i["year"],i["month"],i["titlelink"],2)
            cursor.execute(sql)
            self.db.commit()
        for i in d3:
            sql = "insert into article (title,abstract,`year`,`month`,link,meeting) values ('%s','%s','%s','%s','%s','%s')" \
            % (i["title"],i["abstract"],i["year"],i["month"],i["titlelink"],2)
            cursor.execute(sql)
            self.db.commit()
        for i in d4:
            sql = "insert into article (title,abstract,`year`,`month`,link,meeting) values ('%s','%s','%s','%s','%s','%s')" \
            % (i["title"],i["abstract"],i["year"],i["month"],i["titlelink"],2)
            cursor.execute(sql)
            self.db.commit()
        for i in d5:
            sql = "insert into article (title,abstract,`year`,`month`,link,meeting) values ('%s','%s','%s','%s','%s','%s')" \
            % (i["title"],i["abstract"],i["year"],i["month"],i["titlelink"],2)
            cursor.execute(sql)
            self.db.commit()
        for i in d6:
            sql = "insert into article (title,abstract,`year`,`month`,link,meeting) values ('%s','%s','%s','%s','%s','%s')" \
            % (i["title"],i["abstract"],i["year"],i["month"],i["titlelink"],2)
            cursor.execute(sql)
            self.db.commit()
        cursor.close()
        return 1

    def InsertBySpiderE(self):
        ec = "https://openaccess.thecvf.com/ECCV2018"
        r = requests.get(ec)
        s = Spider()
        d = s.ParseResponse(r.text)
        cursor = self.db.cursor()
        for i in d:
            sql = "insert into article (title,abstract,`year`,`month`,link,meeting) values ('%s','%s','%s','%s','%s','%s')" \
            % (i["title"],i["abstract"],i["year"],i["month"],i["titlelink"],2)
            cursor.execute(sql)
            self.db.commit()
        cursor.close()
        return 1

    def InsertBySpiderC(self):
        cv1 = "https://openaccess.thecvf.com/CVPR2018?day=2018-06-19"
        cv2 = "https://openaccess.thecvf.com/CVPR2018?day=2018-06-20"
        cv3 = "https://openaccess.thecvf.com/CVPR2018?day=2018-06-21"
        cv4 = "https://openaccess.thecvf.com/CVPR2019?day=2019-06-18"
        cv5 = "https://openaccess.thecvf.com/CVPR2019?day=2019-06-19"
        cv6 = "https://openaccess.thecvf.com/CVPR2019?day=2019-06-20"
        cv7 = "https://openaccess.thecvf.com/CVPR2020?day=2020-06-16"
        cv8 = "https://openaccess.thecvf.com/CVPR2020?day=2020-06-17"
        cv9 = "https://openaccess.thecvf.com/CVPR2020?day=2020-06-18"
        r1 = requests.get(cv1)
        r2 = requests.get(cv2)
        r3 = requests.get(cv3)
        r4 = requests.get(cv4)
        r5 = requests.get(cv5)
        r6 = requests.get(cv6)
        r7 = requests.get(cv7)
        r8 = requests.get(cv8)
        r9 = requests.get(cv9)
        s = Spider()
        d1 = s.ParseResponse(r1.text)
        d2 = s.ParseResponse(r2.text)
        d3 = s.ParseResponse(r3.text)
        d4 = s.ParseResponse(r4.text)
        d5 = s.ParseResponse(r5.text)
        d6 = s.ParseResponse(r6.text)
        d7 = s.ParseResponse(r7.text)
        d8 = s.ParseResponse(r8.text)
        d9 = s.ParseResponse(r9.text)
        cursor = self.db.cursor()
        for i in d1:
            sql = "insert into article (title,abstract,`year`,`month`,link,meeting) values ('%s','%s','%s','%s','%s','%s')" \
            % (i["title"],i["abstract"],i["year"],i["month"],i["titlelink"],2)
            cursor.execute(sql)
            self.db.commit()
        for i in d2:
            sql = "insert into article (title,abstract,`year`,`month`,link,meeting) values ('%s','%s','%s','%s','%s','%s')" \
            % (i["title"],i["abstract"],i["year"],i["month"],i["titlelink"],2)
            cursor.execute(sql)
            self.db.commit()
        for i in d3:
            sql = "insert into article (title,abstract,`year`,`month`,link,meeting) values ('%s','%s','%s','%s','%s','%s')" \
            % (i["title"],i["abstract"],i["year"],i["month"],i["titlelink"],2)
            cursor.execute(sql)
            self.db.commit()
        for i in d4:
            sql = "insert into article (title,abstract,`year`,`month`,link,meeting) values ('%s','%s','%s','%s','%s','%s')" \
            % (i["title"],i["abstract"],i["year"],i["month"],i["titlelink"],2)
            cursor.execute(sql)
            self.db.commit()
        for i in d5:
            sql = "insert into article (title,abstract,`year`,`month`,link,meeting) values ('%s','%s','%s','%s','%s','%s')" \
            % (i["title"],i["abstract"],i["year"],i["month"],i["titlelink"],2)
            cursor.execute(sql)
            self.db.commit()
        for i in d6:
            sql = "insert into article (title,abstract,`year`,`month`,link,meeting) values ('%s','%s','%s','%s','%s','%s')" \
            % (i["title"],i["abstract"],i["year"],i["month"],i["titlelink"],2)
            cursor.execute(sql)
            self.db.commit()
        for i in d7:
            sql = "insert into article (title,abstract,`year`,`month`,link,meeting) values ('%s','%s','%s','%s','%s','%s')" \
            % (i["title"],i["abstract"],i["year"],i["month"],i["titlelink"],2)
            cursor.execute(sql)
            self.db.commit()
        for i in d8:
            sql = "insert into article (title,abstract,`year`,`month`,link,meeting) values ('%s','%s','%s','%s','%s','%s')" \
                  % (i["title"], i["abstract"], i["year"], i["month"], i["titlelink"], 2)
            cursor.execute(sql)
            self.db.commit()
        for i in d9:
            sql = "insert into article (title,abstract,`year`,`month`,link,meeting) values ('%s','%s','%s','%s','%s','%s')" \
                  % (i["title"], i["abstract"], i["year"], i["month"], i["titlelink"], 2)
            cursor.execute(sql)
            self.db.commit()
        cursor.close()
        return 1


if __name__ == "__main__":
    d = DataBase()
    print(d.InsertBySpiderI())
    print(d.InsertBySpiderC())
    print(d.InsertBySpiderE())
