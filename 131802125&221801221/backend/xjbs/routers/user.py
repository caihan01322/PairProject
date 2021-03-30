from flask import *
from ..models import *
from ..util import *

user = Blueprint('user', __name__)


@user.route('/login', methods=['POST'])
def login():
    data = request.form
    username = data.get("username")
    password = data.get("password")
    user = User.query.filter(User.username == username, User.password == password).first()
    if user is not None:
        token = create_token(user.user_id)
        res = ResponseData(200, "success", token)
        return json.dumps(res.__dict__)
    else:
        res = ResponseData(1, "用户名或密码错误", None)
        return json.dumps(res.__dict__)

