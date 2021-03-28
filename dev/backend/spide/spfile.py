import requests
from bs4 import BeautifulSoup
import re

class Spider(object):
    def __init__(self):
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

    def SpiderOne(self,title):

        return

    def SpiderOneE(self,title):
        data = {
            "query":title
        }
        response1 = requests.post(self.urle2018,data=data)
        result1 = self.ParseResponse(response1.text)
        #写入数据库

        return

    def ParseResponse(self,text):
        soup = BeautifulSoup(text, 'html.parser')
        nodelist = soup.find_all("dt")
        for node in nodelist:
            sublink = node.a["href"]
            link = self.origin + sublink
            subres = requests.get(link)
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
            break
        return


if __name__ == "__main__":
    e = Spider()
    print(e.SpiderOneE("object"))

