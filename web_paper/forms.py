from flask_wtf import FlaskForm
from wtforms import StringField, SubmitField, PasswordField, validators
from wtforms.validators import DataRequired, Length,EqualTo,length,equal_to


class Login(FlaskForm):
    username = StringField('用户名', validators=[DataRequired(message='用户名不能为空')], render_kw={'placeholder': '请输入用户名'})
    password = PasswordField('密码', validators=[DataRequired(message='密码不能为空')], render_kw={'placeholder': '请输入密码'})
    submit = SubmitField('登录')


class Signup(FlaskForm):
    username = StringField('用户名', validators=[DataRequired(message='用户名不能为空')], render_kw={'placeholder': '请输入用户名'})
    password = PasswordField('密码', validators=[DataRequired(),length(min=6, max=20, message='至少六个字符，最多二十个字符')],
                             render_kw={'placeholder': '请输入密码'})
    password2 = PasswordField('确认密码', validators=[DataRequired(),equal_to('password2', message='前后密码不一致')],
                              render_kw={'placeholder': '请确认密码'})
    submit = SubmitField('注册')
