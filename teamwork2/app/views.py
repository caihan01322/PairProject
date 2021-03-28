import os
import json
from flask import Blueprint
from app.exts import db
from app.models import Paper

blue = Blueprint('first_blue', __name__)


def init_blue(app):
    app.register_blueprint(blueprint=blue)


@blue.route('/')
def index():
    # s = db.Session()
    # p = Paper()
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
                print(paper_json)
                j += 1
                if j == 1:
                    break
        else:
            pass
    return 'Hello Flask'
