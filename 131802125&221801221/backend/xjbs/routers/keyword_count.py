from flask import *
from ..models import *
from ..util import *

keyword_count = Blueprint('keyword_count', __name__)




@keyword_count.route('/top10', methods=['GET'])
def top10():
    data = request.args
    token = data.get("token")
    pay, msg = validate_token(token)
    if msg is not None:
        res = ResponseData(2, "无效token", None)
        return json.dumps(res.__dict__)
    top10 = Keyword_count.query.order_by(Keyword_count.count.desc()).limit(10)
    list = []
    for item in top10:
        item = item.__dict__
        del item["_sa_instance_state"]
        list.append(item)
    res = ResponseData(200, "success", list)
    return json.dumps(res.__dict__)