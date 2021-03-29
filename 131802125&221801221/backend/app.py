from flask import Flask
from xjbs.routers.user import user
from xjbs.models.base import app

app.register_blueprint(user, url_prefix="/pair/api/v1/user")


if __name__ == '__main__':
    app.run()
