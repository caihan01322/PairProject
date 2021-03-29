#!/usr/bin/env python3
# -*- coding: utf-8 -*-

from app import db


# page_list表的内容
class Page_list(db.Model):

    __tablename__ = 'page_list'

    id = db.Column(db.Integer, primary_key=True)  # id
    isbn = db.Column(db.String(45), nullable=False, unique=True)  # 论文编号
    title = db.Column(db.String(288), nullable=False)  # 论文名
    tag = db.Column(db.String(288))  # 论文关键词
    link = db.Column(db.String(288), nullable=False)  # 原文链接
    year = db.Column(db.String(45), nullable=False)  # 论文年份
    abstract = db.Column(db.Text)  # 论文摘要

    def to_json(self):
        dict = self.__dict__
        if "_sa_instance_state" in dict:
            del dict["_sa_instance_state"]

        return dict


# user_page表的内容
class User_page(db.Model):

    __tablename__ = 'user_page'

    id = db.Column(db.Integer, primary_key=True)  # id
    isbn = db.Column(db.String(45), nullable=False, unique=True)  # 论文编号
    title = db.Column(db.String(288), nullable=False)  # 论文名
    tag = db.Column(db.String(288))  # 论文关键词
    link = db.Column(db.String(288), nullable=False)  # 原文链接
    year = db.Column(db.String(45), nullable=False)  # 论文年份
    abstract = db.Column(db.Text)  # 论文摘要

    def to_json(self):
        dict = self.__dict__
        if "_sa_instance_state" in dict:
            del dict["_sa_instance_state"]

        return dict


# tag_list表的内容
class Tag_list(db.Model):

    __tablename__ = 'tag_list'

    id = db.Column(db.Integer, primary_key=True)  # id
    name = db.Column(db.String(288), nullable=False, unique=True)  # tag名
    num = db.Column(db.Integer, nullable=False)  # tag数量
    belong = db.Column(db.String(45), nullable=False)  # tag所在顶会
    year = db.Column(db.String(45), nullable=False)  # 年份

    def to_json(self):
        dict = self.__dict__
        if "_sa_instance_state" in dict:
            del dict["_sa_instance_state"]

        return dict


# db.create_all()
# db.drop_all()
