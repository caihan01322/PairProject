from flask import Flask

from app.exts import init_ext
from app.views import init_blue


def create_app():

    app = Flask(__name__)
    # 初始化配置
    from app import config
    app.config.from_object(config)

    # 注册蓝图，初始化蓝图
    init_blue(app)
    # 初始化第三方插件，库
    init_ext(app)

    return app
