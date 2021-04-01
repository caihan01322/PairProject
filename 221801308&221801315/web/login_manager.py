from flask_login import LoginManager
from config import Users


def get_login_manager():
    """配置并返回 LoginManager 实例"""
    login_manager = LoginManager()

    @login_manager.user_loader
    def get_user(uid):
        """处理访问控制"""
        return Users.query.filter(Users.id == uid).first()

    login_manager.login_view = "login_view"                # 登录视图的 endpoint
    login_manager.login_message = '无权访问此页面，请先登录'  # 重新向 Flash 信息
    login_manager.login_message_category = 'error'        # 重定向 Flask 信息分类
    return login_manager
