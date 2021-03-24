#!/usr/bin/env python3
# -*- coding: utf-8 -*-

from . import pages
from app import db, models
from flask import request, jsonify
from sqlalchemy import func
import re


# 查询全部论文
@pages.route('/page/<num>', methods=['GET'])
def showAllPages(num):
    pages = models.Page_list.query.all().paginate(int(num), 6)
    result = {}
    data = []
    for p in pages.items:
        d = {}
        d['isbn'] = p.isbn
        d['title'] = p.title
        d['tag'] = p.tag
        data.append(d)

    result['total_num'] = pages.pages
    result['pages'] = data

    return jsonify(errno=0, data=result)


# 搜索论文
@pages.route('/page/search/<num>', methods=['POST'])
def searchPages(num):
    re_dict = request.get_json()
    isbn = re_dict.get('isbn')
    title = re_dict.get('title')
    tag = re_dict.get('tag')

    pages = models.Page_list.query.filter(
        models.Page_list.isbn.like('%{isbn}%'.format(isbn=isbn)),
        models.Page_list.title.like('%{title}%'.format(title=title)),
        models.Page_list.tag.like('%{tag}%'.format(tag=tag))).paginate(
            int(num), 6)

    result = {}
    data = []
    for p in pages.items:
        d = {}
        d['isbn'] = p.isbn
        d['title'] = p.title
        d['tag'] = p.tag
        data.append(d)

    result['total_num'] = pages.pages
    result['pages'] = data

    return jsonify(errno=0, data=result)


# 删除选中论文
@pages.route('/page/<isbn>', methods=['DELETE'])
def deletePage(isbn):
    page = models.Page_list.query.filter_by(isbn=isbn)
    db.session.delete(page)
    db.session.commit()

    return jsonify(errno=0)


# 显示选中论文详细
@pages.route('/page/detail/<isbn>', methods=['GET'])
def showPageDetail(isbn):
    page = models.Page_list.query.filter_by(isbn=isbn)
    data = {}
    data['title'] = page.title
    data['year'] = page.year
    data['link'] = page.link
    data['tag'] = page.tag
    data['abstract'] = page.abstract

    return jsonify(errno=0, data=data)


# 修改选中论文
@pages.route('/page/detail/<isbn>', methods=['PUT'])
def updatePageDetail(isbn):
    re_dict = request.get_json()
    title = re_dict.get('title')
    link = re_dict.get('link')

    page = models.Page_list.query.filter_by(isbn=isbn)
    page.title = title
    page.link = link
    db.session.commit()


# 获取top10热词
@pages.route('/tag/', methods=['GET'])
def showTopTags():
    result = models.Tag_list.query(
        func.sum(models.Tag_list.num).label('total_num')).group_by(
            models.Tag_list.name).order_by('total_num'.desc()).limit(10).all()
    data = []
    for r in result.items:
        data.append(r.name)

    return jsonify(errno=0, data=data)


# 获取top5热词及其数量
@pages.route('/tag/pie', methods=['GET'])
def showTopTagsByPie():
    result = models.Tag_list.query(
        func.sum(models.Tag_list.num).label('total_num')).group_by(
            models.Tag_list.name).order_by('total_num'.desc()).limit(5).all()
    data = []
    for r in result.items:
        d = {}
        d['value'] = r.total_num
        d['name'] = r.name
        data.append(d)

    return jsonify(errno=0, data=data)


# 获取近十年的年份以及本年热词的不同顶会数据
@pages.route('/tag/line', methods=['GET'])
def showTopTagsByLine():
    y = models.Tag_list.query.with_entities(
        models.Tag_list.year).distinct().limit(10).all()

    result = {}
    year = []
    cv_data = []
    ic_data = []
    ec_data = []
    for y in y.items:
        year.append(y)

    result['year'] = year

    return jsonify(errno=0, data=result)
