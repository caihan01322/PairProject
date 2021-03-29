#!/usr/bin/env python3
# -*- coding: utf-8 -*-

from flask import Blueprint

# 创建蓝图
pages = Blueprint('pages', __name__)
from . import view
