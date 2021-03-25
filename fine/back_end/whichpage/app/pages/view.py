#!/usr/bin/env python3
# -*- coding: utf-8 -*-

from . import pages
from app import db, models
from flask import request, jsonify
from sqlalchemy import func, text
import re


# 查询全部论文
@pages.route('/page/<num>', methods=['GET'])
def showAllPages(num):
    pages = models.User_page.query.paginate(int(num), 6)
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

    pages = models.User_page.query.filter(
        models.User_page.isbn.like('%{isbn}%'.format(isbn=isbn)),
        models.User_page.title.like('%{title}%'.format(title=title)),
        models.User_page.tag.like('%{tag}%'.format(tag=tag))).paginate(
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


# 插入页面，搜索论文
@pages.route('/insert/search', methods=['POST'])
def searchInsertPages():
    re_dict = request.get_json()
    title = re_dict.get('title')

    pages = models.Page_list.query.filter(
        models.Page_list.title.like('%{title}%'.format(title=title))).limit(6)

    result = {}
    data = []
    for p in pages:
        data.append(p.to_json())

    result['pages'] = data

    return jsonify(errno=0, data=result)


# 插入页面，导入论文
@pages.route('insert/add/<isbn>', methods=['GET'])
def insertPage(isbn):
    insert = models.Page_list.query.filter(
        models.Page_list.isbn == isbn).first()
    page = models.User_page()

    page.isbn = insert.isbn
    page.title = insert.title
    page.tag = insert.tag
    page.year = insert.year
    page.link = insert.link
    page.abstract = insert.abstract

    db.session.add(page)
    db.session.commit()

    return jsonify(errno=0)


# 删除选中论文
@pages.route('/page/<isbn>', methods=['DELETE'])
def deletePage(isbn):
    page = models.User_page.query.filter_by(isbn=isbn).first()
    db.session.delete(page)
    db.session.commit()

    return jsonify(errno=0)


# 显示选中论文详细
@pages.route('/page/detail/<isbn>', methods=['GET'])
def showPageDetail(isbn):
    page = models.User_page.query.filter_by(isbn=isbn).first()
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

    page = models.User_page.query.filter_by(isbn=isbn).first()
    page.title = title
    page.link = link
    db.session.commit()

    return jsonify(errno=0)


# 获取top10热词
@pages.route('/tag', methods=['GET'])
def showTopTags():
    result = db.session.query(
        func.sum(models.Tag_list.num).label('total_num'),
        models.Tag_list.name).group_by(models.Tag_list.name).order_by(
            text("total_num desc")).limit(10).all()

    data = []
    for r in result:
        data.append(r.name)

    return jsonify(errno=0, data=data)


# 获取top5热词及其数量
@pages.route('/tag/pie', methods=['GET'])
def showTopTagsByPie():
    data = db.session.query(
        func.sum(models.Tag_list.num).label('total_num'),
        models.Tag_list.name).group_by(models.Tag_list.name).order_by(
            text("total_num desc")).limit(5).all()

    result = {}
    pie_data = []
    radar_data = []
    for r in data:
        p = {}
        ra = {}
        p['value'] = str(r.total_num)
        p['name'] = r.name
        ra['name'] = r.name

        pie_data.append(p)
        radar_data.append(ra)

    result['pie_data'] = pie_data
    result['radar_data'] = radar_data

    return jsonify(errno=0, data=result)


# 获取近十年的年份以及本年热词的不同顶会数据
@pages.route('/tag/line', methods=['GET'])
def showTopTagsByLine():
    year_list = models.Tag_list.query.with_entities(
        models.Tag_list.year).distinct().order_by(
            models.Tag_list.year.desc()).limit(10).all()

    hot_word = db.session.query(
        func.sum(models.Tag_list.num).label('total_num'),
        models.Tag_list.name).group_by(models.Tag_list.name).order_by(
            text("total_num desc")).limit(1)[0].name

    result = {}
    year = []
    cv_data = []
    ic_data = []
    ec_data = []
    for y in year_list:
        y = y.year
        year.append(y)
        cv = models.Tag_list.query.filter(
            models.Tag_list.belong == "CVPR",
            models.Tag_list.year == y,
            models.Tag_list.name == hot_word,
        ).first()

        if cv is None:
            cv_data.append(0)
        else:
            cv_data.append(cv.num)

        ic = models.Tag_list.query.filter(
            models.Tag_list.belong == "ICCV",
            models.Tag_list.year == y,
            models.Tag_list.name == hot_word,
        ).first()

        if ic is None:
            ic_data.append(0)
        else:
            ic_data.append(ic.num)

        ec = models.Tag_list.query.filter(
            models.Tag_list.belong == "ECCV",
            models.Tag_list.year == y,
            models.Tag_list.name == hot_word,
        ).first()

        if ec is None:
            ec_data.append(0)
        else:
            ec_data.append(ec.num)

    result['year'] = year
    result['cv_data'] = cv_data
    result['ic_data'] = ic_data
    result['ec_data'] = ec_data

    return jsonify(errno=0, data=result)


# 获取本年不同顶会top5热词数量
@pages.route('tag/radar', methods=['GET'])
def showTopTagsByRadar():
    year = models.Tag_list.query.with_entities(
        models.Tag_list.year).distinct().limit(1)[0].year

    word = db.session.query(
        func.sum(models.Tag_list.num).label('total_num'),
        models.Tag_list.name).group_by(models.Tag_list.name).order_by(
            text("total_num desc")).limit(5).all()

    result = []
    cv_data = {}
    ic_data = {}
    ec_data = {}
    cv_data['name'] = 'CVPR'
    ic_data['name'] = 'ICCV'
    ec_data['name'] = 'ECCV'

    cv = []
    ic = []
    ec = []
    for w in word:
        cv_num = models.Tag_list.query.filter(
            models.Tag_list.belong == "CVPR",
            models.Tag_list.year == year,
            models.Tag_list.name == w.name,
        ).first()

        if cv_num is None:
            cv.append(0)
        else:
            cv.append(cv_num.num)

        ic_num = models.Tag_list.query.filter(
            models.Tag_list.belong == "ICCV",
            models.Tag_list.year == year,
            models.Tag_list.name == w.name,
        ).first()

        if ic_num is None:
            ic.append(0)
        else:
            ic.append(ic_num.num)

        ec_num = models.Tag_list.query.filter(
            models.Tag_list.belong == "ECCV",
            models.Tag_list.year == year,
            models.Tag_list.name == w.name,
        ).first()

        if ec_num is None:
            ec.append(0)
        else:
            ec.append(ec_num.num)

    cv_data['value'] = cv
    ic_data['value'] = ic
    ec_data['value'] = ec

    result.append(cv_data)
    result.append(ic_data)
    result.append(ec_data)

    return jsonify(errno=0, data=result)