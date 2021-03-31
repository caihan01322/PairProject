from flask_wtf import FlaskForm
from wtforms import StringField, SubmitField, PasswordField


class Login(FlaskForm):
    username = StringField('用户名')
    password = PasswordField('密码')
    submit = SubmitField('登录')


class Signup(FlaskForm):
    username = StringField('用户名')
    password = PasswordField('密码')
    password2 = PasswordField('确认密码')
    submit = SubmitField('注册')
