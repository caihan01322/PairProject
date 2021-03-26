import pymysql


class DataBase(object):
    def __init__(self,):
        self.db = pymysql.connect("localhost",)

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