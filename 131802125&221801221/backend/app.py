from flask import Flask
from xjbs.routers.user import user
from xjbs.routers.paper import paper
from xjbs.models.base import app

app.register_blueprint(user, url_prefix="/xjbs/api/v1/user")
app.register_blueprint(paper, url_prefix="/xjbs/api/v1/paper")


if __name__ == '__main__':
    app.run()
