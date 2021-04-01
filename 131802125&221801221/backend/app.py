from flask import Flask
from xjbs.routers.user import user
from xjbs.routers.paper import paper
from xjbs.routers.keyword_count import keyword_count
from xjbs.models.base import app
from flask_cors import CORS

CORS(app, supports_credentials=True)
app.register_blueprint(user, url_prefix="/xjbs/api/v1/user")
app.register_blueprint(paper, url_prefix="/xjbs/api/v1/paper")
app.register_blueprint(keyword_count, url_prefix="/xjbs/api/v1/keyword")


if __name__ == '__main__':
    app.run()
