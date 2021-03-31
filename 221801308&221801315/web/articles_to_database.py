# -*- coding: utf-8 -*-
import json
import os
from config import *


# 论文json所在的文件夹路径
# CVPR_PATH = "C:/Users/MI/Desktop/论文数据/CVPR"
# ECCV_PATH = "C:/Users/MI/Desktop/论文数据/ECCV"
# ICCV_PATH = "C:/Users/MI/Desktop/论文数据/ICCV"
CVPR_PATH = "C:/Users/Willa/Desktop/论文数据/CVPR"
ECCV_PATH = "C:/Users/Willa/Desktop/论文数据/ECCV"
ICCV_PATH = "C:/Users/Willa/Desktop/论文数据/ICCV"

def deal_data(path):
    """对助教爬取的论文json数据进行处理

    CVPR、ICCV论文的json字符串结尾多了; 该函数将结尾的;去掉

    Args:
        path: 包含文章json的文件夹名
    """
    for filename in os.listdir(path):
        f = open(path+'/'+filename, "r+", encoding="utf-8")
        string = f.read()
        string = string.rstrip(';')
        f.seek(0)
        f.truncate()
        f.write(string)


def add_data(meeting, path):
    """获取每篇论文的json插入数据库

    如果会议名为CVPR和ICCV则调用data_insert()方法
    如果会议名为ECCV则调用eccv_data_insert()方法

    Args:
        meeting: 会议名称
        path: 包含文章json的文件夹名
    """
    for filename in os.listdir(path):
        f = open(path+'/'+filename, "r", encoding="utf-8")
        context = json.load(f)
        if meeting == "ECCV":
            eccv_data_insert(context)
        else:
            data_insert(meeting, context)


SKIP = 0     # 统计因为格式缺失而跳过的文章数


def data_insert(meeting, context):
    """提取json中需要的数据插入数据库

    检索CVRP、ICCV论文json中的title、publicationYear、abstract、doiLink插入数据库
    检索Keywords中的IEEE Keyword插入数据库
    遇到元素不完整的论文就跳过，并打印出论文标题

    Args:
        meeting: 会议名称
        context: 爬取到的json内容
    """
    global SKIP

    try:
        article_info = Articles(meeting=meeting, title=context["title"], publicationYear=context["publicationYear"],
                                abstract=context["abstract"], doiLink=context["doiLink"])
        keywords = context["keywords"][0]["kwd"]     # 选择IEEE Keywords
    # 记录跳过的论文
    except KeyError as e:
        print("skip "+context["title"])
        SKIP += 1
    else:
        # json数据没有缺失时，插入数据库
        try:
            article_info.keywords = []
            for kwd in keywords:
                k = Keywords.query.filter(Keywords.keyword == kwd).first()
                if k is not None:
                    k.count+=1
                else:
                    k = Keywords(keyword=kwd)
                article_info.keywords.append(k)

                db.session.add(article_info)
            db.session.commit()
        except Exception as e:
            db.session.rollback()


def eccv_data_insert(context):
    """ECCV的json格式比较特别，另写函数存入数据库

    检索ECCV论文json中的会议和年份、论文名称、摘要、原文链接、关键词插入数据库
    会议和年份标签所含的信息拆分成：会议名+出版年份，然后插入数据库
    遇到元素不完整的论文就跳过，并打印出论文标题

    Args:
        context: 爬取到的json内容
    """
    global SKIP

    try:
        meet_year = context["会议和年份"].split()
        meeting = meet_year[0]
        year = meet_year[1]

        article_info = Articles(
            meeting=meeting, title=context["论文名称"], publicationYear=year, abstract=context["摘要"], doiLink=context["原文链接"])
        keywords = context["关键词"]
    # 记录跳过的论文
    except KeyError as e:
        print("skip "+context["论文名称"])
        SKIP += 1
    else:
        # json数据没有缺失时，插入数据库
        try:
            article_info.keywords = []
            for kwd in keywords:
                k = Keywords.query.filter(Keywords.keyword == kwd).first()
                if k is not None:
                    k.count += 1
                else:
                    k = Keywords(keyword=kwd)
                article_info.keywords.append(k)

                db.session.add(article_info)
            db.session.commit()
        except Exception as e:
            db.session.rollback()


if __name__ == "__main__":
    # deal_data(CVPR_PATH)
    # deal_data(ICCV_PATH)
    add_data("CVPR", CVPR_PATH)
    add_data("ECCV", ECCV_PATH)
    add_data("ICCV", ICCV_PATH)
    print("共跳过", SKIP, "篇")

