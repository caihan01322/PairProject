from Team_work.app import db
from datetime import datetime
from werkzeug.security import generate_password_hash, check_password_hash

class BaseModel:
    # id
    id = db.Column(db.Integer, primary_key=True)
    # 账号
    account = db.Column(db.String(120), nullable=False, unique=True)
    # 姓名
    username = db.Column(db.String(60), nullable=False)
    # 密码
    password = db.Column(db.String(100), nullable=False)
    # 手机
    mobile = db.Column(db.String(100), default="未填写", unique=True)
    # 邮箱
    email = db.Column(db.String(100), default="未填写", unique=True)
    # 学校
    college = db.Column(db.String(100), nullable=False,default="未填写")
    # 简介
    info = db.Column(db.String(255), default="该用户未填写简介~")
    # 性别,True为男
    gender = db.Column(db.Boolean, nullable=False,default=True)
    # 创建时间
    create_time = db.Column(db.DateTime, default=datetime.utcnow)
    # 用户头像
    avatar = db.Column(db.String(255), default="default")





class User(db.Model,BaseModel):
    __table_args__ = {"useexisting": True}

    note = db.relationship('Note', back_populates="from_user", cascade="all")
    article = db.relationship('User_article', back_populates="user", cascade="all")

    """
        :param password
        :return void
    """
    def set_password(self, password):
        self.password = generate_password_hash(password)

    """
        :param  password
        :return boolean
    """
    def check_password(self, password):
        return check_password_hash(self.password, password)

class User_article(db.Model):
    __table_name__='user_article'
    __table_args__ = {"useexisting": True}
    # id
    id = db.Column(db.Integer, primary_key=True)
    #发布日期
    create_time=db.Column(db.DateTime,nullable=False)
    #修改日期
    update_time=db.Column(db.DateTime, default=datetime.utcnow)
    #论文会议名称
    meeting_name=db.Column(db.String(20),nullable=False)
    #论文标题
    title=db.Column(db.String(255),nullable=False)
    #论文作者
    author=db.Column(db.Text,nullable=False,default="暂无相关作者")
    #论文简介
    abstract=db.Column(db.Text)
    #论文关键词
    keyword=db.Column(db.Text,nullable=False)
    #地址
    address=db.Column(db.String(255),nullable=False)
    #用户收藏
    user = db.relationship("User", back_populates="article")
    user_id=db.Column(db.Integer,db.ForeignKey("user.id"))

    note = db.relationship('Note', back_populates="article", cascade="all")


class Note(db.Model):
    __table_args__ = {"useexisting": True}
    # id
    id = db.Column(db.Integer, primary_key=True)
    #论文笔记
    note=db.Column(db.Text,default="暂无笔记")
    #一个用户有多个笔记
    from_user = db.relationship("User", back_populates="note")
    user_id=db.Column(db.Integer,db.ForeignKey("user.id"))

    #多份笔记对应一篇论文
    article=db.relationship('User_article',back_populates="note")
    article_id = db.Column(db.Integer, db.ForeignKey('user_article.id'))

class Meeting_article(db.Model):
    __table_name__ = 'meeting_article'
    __table_args__ = {"useexisting": True}
    # id
    id = db.Column(db.Integer, primary_key=True)
    # 发布日期
    create_time = db.Column(db.DateTime,nullable=False)
    # 论文会议名称
    meeting_name = db.Column(db.String(20), nullable=False)
    # 论文标题
    title = db.Column(db.String(255), nullable=False)
    # 论文作者
    author = db.Column(db.Text, nullable=False,default="暂无相关作者")
    # 论文简介
    abstract = db.Column(db.Text)
    # 论文关键词
    keyword = db.Column(db.Text, nullable=False)
    # 地址
    address = db.Column(db.String(255), nullable=False)



db.drop_all()
db.create_all()