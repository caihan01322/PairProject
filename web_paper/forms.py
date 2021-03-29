from flask_wtf import FlaskForm
from wtforms import StringField, SubmitField, PasswordField,validators
from wtforms.validators import DataRequired

class Login(FlaskForm):
    username=StringField('用户名',validators=[DataRequired()])
    password=PasswordField('密码',validators=[DataRequired()])
    submit=SubmitField('登录')

class Signup(FlaskForm):
    username = StringField('用户名', validators=[DataRequired()])
    password = PasswordField('密码', validators=[DataRequired(),validators.EqualTo('password2', message='前后密码不一致')])
    password2 = PasswordField('密码', [validators.DataRequired()])
    submit = SubmitField('注册')