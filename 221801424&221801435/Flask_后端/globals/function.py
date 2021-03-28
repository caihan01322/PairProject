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
