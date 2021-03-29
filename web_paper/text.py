from flask import Flask, render_template, flash
from flask import request
from flask_sqlalchemy import SQLAlchemy
from config import Config
from flask_wtf import FlaskForm
from wtforms import StringField, SubmitField, PasswordField
from wtforms.validators import DataRequired

app = Flask(__name__)

@app.route('/test',methods=['GET','POST'])
def test():
    flash("成功了!")
    return render_template('test.html')