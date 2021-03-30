import base64
import datetime

from flask import Blueprint, request, url_for
from flask_dropzone import random_filename

from Team_work.app import db
from Team_work.globals.utils import responseBody, responseError, responseSuccess
from Team_work.globals.models import Meeting_article
from Team_work.globals.responses import Responses

functions = Blueprint("functions", __name__)

@functions.route("/search_keyword",methods=["POST"])
def search_keyword():
    try:
        data=request.get_json()
        keyword=data.get("keyword")
        page_info=data.get("page_info")
        page_size = page_info.get("page_size")
        page_num = page_info.get("page_num")
        print(1)
        pagination=Meeting_article.query.filter(
            Meeting_article.keyword.like("%"+keyword+"%"))\
            .order_by(Meeting_article.create_time.desc()).paginate(per_page=page_num,page=page_size)
        print(3)
        articles=pagination.items
        print(2)
        response_info=[]
        for i in articles:
            response_info.append({
                'id':i.id,
                'title':i.title,
                'create_time':i.create_time,
                'meeting_name':i.meeting_name,
                'authors':i.author,
                'abstract':i.abstract,
                'keyword':i.keyword,
                'address':i.address
            })
        return responseBody(data=response_info)

    except Exception as e:
        print(e)
        return responseError(Responses.PARAMETERS_ERROR)

@functions.route("/search_author",methods=["POST"])
def search_author():
    try:
        data=request.get_json()
        author=data.get("author")
        page_info=data.get("page_info")
        page_size = page_info.get("page_size")
        page_num = page_info.get("page_num")
        pagination=Meeting_article.query.filter(
            Meeting_article.author.like("%"+author+"%"))\
            .order_by(Meeting_article.create_time.desc()).paginate(per_page=page_num,page=page_size)
        articles=pagination.items
        response_info=[]
        for i in articles:
            response_info.append({
                'id':i.id,
                'title':i.title,
                'create_time':i.create_time,
                'meeting_name':i.meeting_name,
                'authors':i.author,
                'abstract':i.abstract,
                'keyword':i.keyword,
                'address':i.address
            })
        return responseBody(data=response_info)

    except Exception as e:
        print(e)
        return responseError(Responses.PARAMETERS_ERROR)


@functions.route("/search_title",methods=["POST"])
def search_title():
    try:
        data=request.get_json()
        title=data.get("title")
        page_info=data.get("page_info")
        page_size = page_info.get("page_size")
        page_num = page_info.get("page_num")
        pagination=Meeting_article.query.filter(
            Meeting_article.title.like("%"+title+"%"))\
            .order_by(Meeting_article.create_time.desc()).paginate(per_page=page_num,page=page_size)
        articles=pagination.items
        response_info=[]
        for i in articles:
            response_info.append({
                'id':i.id,
                'title':i.title,
                'create_time':i.create_time,
                'meeting_name':i.meeting_name,
                'authors':i.author,
                'abstract':i.abstract,
                'keyword':i.keyword,
                'address':i.address
            })
        return responseBody(data=response_info)

    except Exception as e:
        print(e)
        return responseError(Responses.PARAMETERS_ERROR)


@functions.route("/getLineChart",methods=["POST"])
def getLineChart():
    try:
        Iccv_meeting=Meeting_article.query.filter_by(meeting_name='ICCV').all()
        ICCV_list=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        for i in Iccv_meeting:
            date=datetime.datetime.strftime(i.create_time,'%Y-%m-%d')
            if( int(2010) <= int(date[0:4]) and int( 2021)>=int(date[0:4]) ):
                ICCV_list[(int(date[0:4])-2010)]=ICCV_list[(int(date[0:4])-2010)]+1

        Cvpr_meeting=Meeting_article.query.filter_by(meeting_name='CVPR').all()
        CVPR_list = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        for i in Cvpr_meeting:
            date=datetime.datetime.strftime(i.create_time,'%Y-%m-%d')
            if( int(2010) <= int(date[0:4]) and int( 2021)>=int(date[0:4]) ):
                CVPR_list[(int(date[0:4])-2010)]=CVPR_list[(int(date[0:4])-2010)]+1


        ECCV_list=[0,0,0,0,0,0,0,0,0,0,0,0]
        Eccv_meeting = Meeting_article.query.filter_by(meeting_name='ECCV').all()
        for i in Eccv_meeting:
            date=datetime.datetime.strftime(i.create_time,'%Y-%m-%d')
            if( int(2010) <= int(date[0:4]) and int( 2021)>=int(date[0:4]) ):
                # print(int(date[0:4]))
                ECCV_list[(int(date[0:4])-2010)]=ECCV_list[(int(date[0:4])-2010)]+1

        return responseBody(data=[{'ECCV':ECCV_list,'CVPR':CVPR_list,'ICCV':ICCV_list}])


    except Exception as e:
        print(e)
        return responseError(Responses.PARAMETERS_ERROR)


@functions.route('/getTop10',methods=['POST'])
def getTop10():
    try:
        date=datetime.datetime.strptime('2020-01-01', '%Y-%m-%d')
        Articles=Meeting_article.query.filter_by(create_time=date).all()
        keywordDict={}
        for i in Articles:
            temp_list=list(i.keyword.split(';'))
            for j in temp_list:
                if (j not in keywordDict.keys()):
                    keywordDict[j]=1
                else:
                    keywordDict[j]+=1
        keywordDict['']=0
        keywordDict['暂无关键词']=0
        keyInfo=[]
        valueInfo=[]
        num=0
        i=0
        for k in sorted(keywordDict, key=keywordDict.__getitem__, reverse=True):
            if(i<10):
                keyInfo.append(k)
                valueInfo.append(keywordDict[k])
                num+=keywordDict[k]
                i+=1
        return responseBody(data=[{'key':keyInfo,'value':valueInfo,'num':num}])

    except Exception as e:
        print(e)
        return responseError(Responses.PARAMETERS_ERROR)

