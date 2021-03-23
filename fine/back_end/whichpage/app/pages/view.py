#!/usr/bin/env python3
# -*- coding: utf-8 -*-

from . import pages
from app import db, models
from flask import request, jsonify
import re


# 查询全部论文
@pages.route('/', methods=['GET'])
def showAllPages():
    result = models.Page_list.query.all()
    data = []
    for r in result:
        data.append(r.to_json())

    return jsonify(errno=0, data=data)
