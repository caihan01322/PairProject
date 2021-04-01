# coding:utf-8
import re
import urllib
from bs4 import BeautifulSoup
import urllib.request,urllib.error
import xlwt
import sqlite3

findLink = re.compile(r'"doiLink":"(.*?)",')
findKeywords = re.compile(r',"kwd":\[(.*?)\]}')
findtime = re.compile(r',"conferenceDate":"(.*?)",')
findabstract = re.compile(r',"abstract":"(.*?)","')
findtitle = re.compile(r'<h1 _ngcontent-hmt-c23="" class="document-title"><span _ngcontent-hmt-c23="">(.*?)</span>')

def main():
    baseurl = "https://doi.org/10.1109/CVPR.2017."
    #1.爬取网页
    datalist = getData(baseurl)
    savepath = "c:\paper_2017.xls"

    #3.保存数据
    saveData(datalist,savepath)
    # getData(baseurl)

#爬取网页
def getData(baseurl):
    datalist = []
    for i in range(9,109):
        # 00009-01083
        # print(str(int(i)))
        # if (len(str(i))==1): url = baseurl+'0000'+ str(int(i))
        # elif (len(str(i))==2): url = baseurl+'000'+ str(int(i))
        # elif (len(str(i))==3):   url = baseurl+'00'+ str(int(i))
        # else: url = baseurl+'0'+ str(int(i))
        url = baseurl +str(int(i))
        print(url)
        html = askURL(url)
        #2.逐一解析数据
        soup = BeautifulSoup(html,"html.parser")
        data = []
        items = soup.find_all('title')
        if (len(items)!=0):
            title = items[0]
        title = str(title)[7:-8]
        # print(title)
        titles = soup.select("body script")[5]
        # print(titles)
        titles = str(titles)
        data.append(title)
        keyWord = re.findall(findKeywords, titles)
        data.append(keyWord)
        abstract = re.findall(findabstract, titles)[0]
        data.append(abstract)
        datetime = re.findall(findtime,titles)[0]
        data.append(datetime)
        href = re.findall(findLink, titles)[0]
        data.append(href)
        classify = 'ICCV'
        data.append(classify)
        # data = [
        #     {
        #         'title':title,
        #         'keyWord':keyWord,
        #         'abstract':abstract,
        #         'datetime':datetime,
        #         'href':href,
        #         'classify':classify
        #     }
        # ]
        print(data)
        datalist.append(data)

    return datalist

def askURL(url):
    head = {
        "User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36"
    }
    request = urllib.request.Request(url,headers = head)
    html = ""
    try:
        response = urllib.request.urlopen(request)
        html = response.read().decode("utf-8")
        # print(html)
    except urllib.error.URLError as e:
        if hasattr(e,"code"):
            print(e.code)
        if hasattr(e,"reason"):
            print(e.reason)
    return html

#保存数据
def saveData(datalist,savepath):
    book = xlwt.Workbook(encoding='utf-8',style_compression=0)
    sheet = book.add_sheet('CVPR_2017',cell_overwrite_ok=True)
    col = ("title","keyWord","abstract","datetime","href","classify")

    for i in range(0,6):
        sheet.write(0,i,col[i])
    for i in range(0,100):
        #107x
        print("第%d条" %(i+1) )
        data = datalist[i]
        for j in range(0,6):
            sheet.write(i+1,j,data[j])
    book.save('paper_2017_CVPR.xls')

def savaData(datalist,savepath):
    pass

if __name__ == '__main__':
    main()
    print("爬取完毕")

