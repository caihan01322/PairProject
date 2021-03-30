from flask import *
from ..models import *
from ..util import *

paper = Blueprint('paper', __name__)


@paper.route('/search', methods=['POST'])
def search():
    data = request.form
    token = data.get("token")
    pay, msg = validate_token(token)
    if msg is not None:
        res = ResponseData(2, "无效token", None)
        return json.dumps(res.__dict__)
    page = data.get("page")
    key = data.get("key")
    papers = Paper.query.filter(Paper.title.like("%" + key + "%")).order_by(
        Paper.paper_id).limit(5).offset((int(page) - 1) * 5).all()
    list = []
    for paper in papers:
        item = paper.__dict__
        del item["_sa_instance_state"]
        list.append(item)
    return json.dumps(res.__dict__)
