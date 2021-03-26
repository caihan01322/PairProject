import requests
from bs4 import BeautifulSoup
import re

class Spider(object):
    def __init__(self):
        self.mon = {
            "January":1,
            "February":2,
            "March":3,
            "April":4,
            "May":5,
            "June":6,
            "July":7,
            "August":8,
            "September":9,
            "October":10,
            "November":11,
            "December":12
        }
        self.url = {
    "ECCV2018":"https://openaccess.thecvf.com/ECCV2018_search",
    "CVPR2018":"https://openaccess.thecvf.com/CVPR2018_search",
    "CVPR2019":"https://openaccess.thecvf.com/CVPR2019_search",
    "CVPR2020":"https://openaccess.thecvf.com/CVPR2020_search",
    "ICCV2017":"https://openaccess.thecvf.com/ICCV2017_search",
    "ICCV2019":"https://openaccess.thecvf.com/ICCV2019_search",
    "ICCV2015":"https://openaccess.thecvf.com/ICCV2015_search"
    }
        self.urle2018 = self.url["ECCV2018"]
        self.urlc2018 = self.url["CVPR2018"]
        self.urlc2019 = self.url["CVPR2019"]
        self.urlc2020 = self.url["CVPR2020"]
        self.urli2017 = self.url["ICCV2017"]
        self.urli2019 = self.url["ICCV2019"]
        self.urli2015 = self.url["ICCV2015"]
        self.origin = "https://openaccess.thecvf.com/"

    def SpiderAll(self,titlelist):
        #存入任务
        data = []
        for title in titlelist:
            result = self.SpiderOne(title)
            data.append(result)
        return data

    def SpiderOne(self,title):
        #存入任务
        eresult = self.SpiderOneE(title)
        iresult = self.SpiderOneI(title)
        cresult = self.SpiderOneC(title)
        data = {
            "1":eresult,
            "2":iresult,
            "3":cresult
        }
        return data

    def SpiderOneE(self,title):
        data = {
            "query":title
        }
        result = []
        response = requests.post(self.urle2018,data=data)
        if response is not None:
            result = self.ParseResponse(response1.text)
        return result

    def SpiderOneI(self,title):
        data = {
            "query":title
        }
        result = []
        response1 = requests.post(self.urli2015,data=data)
        response2 = requests.post(self.urli2017, data=data)
        response3 = requests.post(self.urli2019, data=data)
        if response1 is not None:
            result1 = self.ParseResponse(response1.text)
            result.append(result1)
        if response2 is not None:
            result2 = self.ParseResponse(response2.text)
            result.append(result2)
        if response3 is not None:
            result3 = self.ParseResponse(response3.text)
            result.append(result3)
        return result

    def SpiderOneC(self,title):
        data = {
            "query":title
        }
        result = []
        response1 = requests.post(self.urlc2018,data=data)
        response2 = requests.post(self.urlc2019, data=data)
        response3 = requests.post(self.urlc2020, data=data)
        if response1 is not None:
            result1 = self.ParseResponse(response1.text)
            result.append(result1)
        if response2 is not None:
            result2 = self.ParseResponse(response2.text)
            result.append(result2)
        if response3 is not None:
            result3 = self.ParseResponse(response3.text)
            result.append(result3)
        return result

    def ParseResponse(self,text):
        soup = BeautifulSoup(text, 'html.parser')
        nodelist = soup.find_all("dt")
        result = []
        for node in nodelist:
            sublink = node.a["href"]
            link = self.origin + sublink
            subres = requests.get(link)
            if subres is not None:
                subsoup = BeautifulSoup(subres.text,"html.parser")
                title = subsoup.find(id="papertitle").contents[0]
                abstract = subsoup.find(id="abstract").contents[0]
                titlelink = self.origin + subsoup.find("dd").a["href"][6:]
                tag = str(subsoup.find("div","bibref").text)
                month_key = tag.find("month") + 9
                year_key = tag.find("year") + 8
                month_end = tag.find("}",month_key)
                year_end = tag.find("}",year_key)
                month = tag[month_key:month_end]
                year = tag[year_key:year_end]
                sub = {
                    "title":title,
                    "abstract":abstract,
                    "titlelink":titlelink,
                    "year":int(year),
                    "month":self.mon[month]
                }
                result.append(sub)
        return result


if __name__ == "__main__":
    e = Spider()
    print(e.SpiderOneE("object"))

