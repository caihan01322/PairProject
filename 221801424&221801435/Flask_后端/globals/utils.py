from flask import jsonify, session
from itsdangerous import TimedJSONWebSignatureSerializer, BadSignature, SignatureExpired

from Team_work.globals.responses import Responses
from faker import Faker
from functools import wraps
secret_key = "TOKEN"


# 返回格式
def responseBody(status=200, data=None, message=""):
    return jsonify(status=status, data=data, message=message)


def responseError(info_dict):
    return jsonify(status=info_dict.get("status"),
                   message=info_dict.get("message"))


def responseSuccess(info_dict):
    return jsonify(status=info_dict.get("status"),
                   message=info_dict.get("message"))


def token_check_required(func):
    @wraps(func)
    def wrap_function():
        try:
            token = session.get("token")
            if token is None:
                return responseError(Responses.NO_TOKEN)
            user_id = validate_token(token)
            session["user_id"] = user_id
            func()
        except BadSignature:
            return responseError(Responses.INVALID_TOKEN)
        except SignatureExpired:
            return responseError(Responses.TOKEN_EXPRIRED)

    return wrap_function


def generate_token(user_id, expire=60 * 60):
    serializer = TimedJSONWebSignatureSerializer(expires_in=expire, secret_key=secret_key)
    return serializer.dumps(user_id)


def validate_token(token):
    serializer = TimedJSONWebSignatureSerializer(secret_key=secret_key)
    user_id = serializer.loads(token)
    return user_id



