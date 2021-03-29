import os
import json
from flask import Blueprint
from sqlalchemy import create_engine

from app.exts import db
from app.models import *

blue = Blueprint('first_blue', __name__)


def init_blue(app):
    app.register_blueprint(blueprint=blue)


@blue.route('/')
def index():
    PAPER_PATH = os.path.join(os.getcwd(), "paper/")
    paper_class = os.listdir(PAPER_PATH)

    for i in paper_class:
        i = i + '/'
        paperlist_path = os.path.join(PAPER_PATH, i)
        paper_list = os.listdir(paperlist_path)
        j = 0
        if i == 'ECCV/':
            for k in paper_list:
                filename = paperlist_path + k
                # print(filename)
                f_obj = open(filename, "rb")
                paper_json = json.loads(f_obj.read().decode('utf-8'))
                f_obj.close()
                # p = Paper(title=paper_json['论文名称'], abstract=paper_json['摘要'], url=paper_json['原文链接'], time=paper_json['发布时间'], conference="ECCV")
                keyword = Paper
                # print(paper_json['论文名称']+"    "+paper_json['摘要']+"   "+paper_json['原文链接']+"    "+paper_json['发布时间'])
                db.session.add(p)
                j=0
                j+=1
                print(j)
        else:
            pass
    db.session.commit()
    return 'Hello Flask'
