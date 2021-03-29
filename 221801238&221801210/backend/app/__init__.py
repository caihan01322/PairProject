from flask import Flask

from app.exts import init_ext
from app.setting import envs
from app.views import init_blue
from app import setting


def create_app():
    # 初始化配置
    app = Flask(__name__, template_folder=setting.DevelopConfig)
    app.config.from_object(envs.get('develop'))

    # 初始化第三方插件，库
    init_ext(app)
    # 注册蓝图，初始化蓝图
    init_blue(app)

    return app
