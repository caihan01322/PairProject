from flask import *
from ..models import *
from ..util import *

paper = Blueprint('paper', __name__)


@paper.route('/search', methods=['GET'])
def search():
    data = request.args
    token = data.get("token")
    pay, msg = validate_token(token)
    if msg is not None:
        res = ResponseData(2, "无效token", None)
        return json.dumps(res.__dict__)
    page = data.get("page")
    key = data.get("key")
    papers = Paper.query.filter(Paper.title.like("%" + key + "%")).order_by(
        Paper.paper_id).limit(10).offset((int(page) - 1) * 10).all()
    list = []
    for paper in papers:
        item = paper.__dict__
        del item["_sa_instance_state"]
        list.append(item)
    res = ResponseData(200, "success", list)
    return json.dumps(res.__dict__)


@paper.route('/paperNum', methods=['GET'])
def paperNum():
    data = request.args
    token = data.get("token")
    pay, msg = validate_token(token)
    if msg is not None:
        res = ResponseData(2, "无效token", None)
        return json.dumps(res.__dict__)
    key = data.get("key")
    paperNum = Paper.query.filter(Paper.title.like("%" + key + "%")).count()
    res = ResponseData(200, "success", paperNum)
    return json.dumps(res.__dict__)

@paper.route('/delete', methods=['POST'])
def delete():
    data = request.form
    token = data.get("token")
    pay, msg = validate_token(token)
    if msg is not None:
        res = ResponseData(2, "无效token", None)
        return json.dumps(res.__dict__)
    paper_id = data.get("paper_id")
    paper = Paper.query.filter(Paper.paper_id == paper_id).first()
    session.delete(paper)
    session.commit()
    res = ResponseData(200, "success", "删除成功")
    return json.dumps(res.__dict__)

