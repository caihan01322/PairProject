# 1.峰会，每年的关键词数量
# #每个峰会开始-》关键词
# 获取每个峰会的论文列表
# id=1.1 id=1.2 id=1.3
# 分取年份 :近五年
# id=2.11->id=1.15
# id=2.21->id=1.25
# id=2.31->id=2.35
# 获取近几年的论文列表
# 通过论文id获取关键词列表
# 每一个模块获取成功构建
# {
#     'id': '0.0',
#     'parent': '2.11',
#     'name': '论文一览'
#     'value':key_number
# }


from __init__ import *
from models import *
import json
f=open('chart3.txt','w')
f.truncate()
if __name__ == '__main__':
    data=""
    ICCV_number4 = Paper.query.filter(Paper.date.like('2016%'), Paper.meeting == 'ICCV').all()
    ICCV_number5 = Paper.query.filter(Paper.date.like('2017%'), Paper.meeting == 'ICCV').all()
    ICCV_number6 = Paper.query.filter(Paper.date.like('2018%'), Paper.meeting == 'ICCV').all()
    ICCV_number7 = Paper.query.filter(Paper.date.like('2019%'), Paper.meeting == 'ICCV').all()
    ICCV_number8 = Paper.query.filter(Paper.date.like('2020%'), Paper.meeting == 'ICCV').all()
    ICCV_list = [ICCV_number4, ICCV_number5, ICCV_number6, ICCV_number7, ICCV_number8]

    ECCV_number4 = Paper.query.filter(Paper.date.like('2016%'), Paper.meeting == 'ECCV').all()
    ECCV_number5 = Paper.query.filter(Paper.date.like('2017%'), Paper.meeting == 'ECCV').all()
    ECCV_number6 = Paper.query.filter(Paper.date.like('2018%'), Paper.meeting == 'ECCV').all()
    ECCV_number7 = Paper.query.filter(Paper.date.like('2019%'), Paper.meeting == 'ECCV').all()
    ECCV_number8 = Paper.query.filter(Paper.date.like('2020%'), Paper.meeting == 'ECCV').all()
    ECCV_list = [ECCV_number4, ECCV_number5, ECCV_number6, ECCV_number7, ECCV_number8]

    CVPR_number4 = Paper.query.filter(Paper.date.like('2016%'), Paper.meeting == 'CVPR').all()
    CVPR_number5 = Paper.query.filter(Paper.date.like('2017%'), Paper.meeting == 'CVPR').all()
    CVPR_number6 = Paper.query.filter(Paper.date.like('2018%'), Paper.meeting == 'CVPR').all()
    CVPR_number7 = Paper.query.filter(Paper.date.like('2019%'), Paper.meeting == 'CVPR').all()
    CVPR_number8 = Paper.query.filter(Paper.date.like('2020%'), Paper.meeting == 'CVPR').all()
    CVPR_list = [CVPR_number4, CVPR_number5, CVPR_number6, CVPR_number7, CVPR_number8]
    # 每个峰会论文列表获取完成
    # 通过论文列表检索关键词
    for h in range(0,5):
        key_list = []
        for paper in ICCV_list[h]:
            key_list+=PaperToKeyword.query.filter(paper.id==PaperToKeyword.paper_id).all()
        dit = dict()
        for k in key_list:
            dit[k.keyword] = dit.get(k.keyword, 1) + 1
        key_name = list(dit.keys())
        key_value = list(dit.values())
        x=10
        if len(key_value)>10:
            x=10
        else:
            x=len(key_value)
        for i in range(0, x):
            index = i
            max = key_value[i]
            for j in range(i + 1, len(key_value)):
                if max < key_value[j]:
                    max = key_value[j]
                    index = j
            temp = key_value[i]
            key_value[i] = key_value[index]
            key_value[index] = temp
            temp2 = key_name[i]
            key_name[i] = key_name[index]
            key_name[index] = temp2
        #已获取某一年ICCV的关键词字典，开始构造json数据
        for i in range(0,x):
            key_data=',{\'id\': \'3.1'+str(h+1)+str(i)+'\',\'parent\': \'2.1'+str(h+1)+'\',\'name\': \''+str(key_name[i])+'\',\'value\':'+str(key_value[i])+'}'
            f.write(key_data)


    for h in range(0,5):
        key_list = []
        for paper in ECCV_list[h]:
            key_list+=PaperToKeyword.query.filter(paper.id==PaperToKeyword.paper_id).all()
        dit = dict()
        for k in key_list:
            dit[k.keyword] = dit.get(k.keyword, 1) + 1
        key_name = list(dit.keys())
        key_value = list(dit.values())
        x=10
        if len(key_value)>10:
            x=10
        else:
            x=len(key_value)
        for i in range(0, x):
            index = i
            max = key_value[i]
            for j in range(i + 1, len(key_value)):
                if max < key_value[j]:
                    max = key_value[j]
                    index = j
            temp = key_value[i]
            key_value[i] = key_value[index]
            key_value[index] = temp
            temp2 = key_name[i]
            key_name[i] = key_name[index]
            key_name[index] = temp2
        #已获取某一年ICCV的关键词字典，开始构造json数据
        for i in range(0,x):
            key_data=',{\'id\': \'3.2'+str(h+1)+str(i)+'\',\'parent\': \'2.2'+str(h+1)+'\',\'name\': \''+str(key_name[i])+'\',\'value\':'+str(key_value[i])+'}'
            f.write(key_data)


    for h in range(0,5):
        key_list = []
        for paper in CVPR_list[h]:
            key_list+=PaperToKeyword.query.filter(paper.id==PaperToKeyword.paper_id).all()
        dit = dict()
        for k in key_list:
            dit[k.keyword] = dit.get(k.keyword, 1) + 1
        key_name = list(dit.keys())
        key_value = list(dit.values())
        x=10
        if len(key_value)>10:
            x=10
        else:
            x=len(key_value)
        for i in range(0, x):
            index = i
            max = key_value[i]
            for j in range(i + 1, len(key_value)):
                if max < key_value[j]:
                    max = key_value[j]
                    index = j
            temp = key_value[i]
            key_value[i] = key_value[index]
            key_value[index] = temp
            temp2 = key_name[i]
            key_name[i] = key_name[index]
            key_name[index] = temp2
        #已获取某一年ICCV的关键词字典，开始构造json数据
        for i in range(0,x):
            key_data=',{\'id\': \'3.3'+str(h+1)+str(i)+'\',\'parent\': \'2.3'+str(h+1)+'\',\'name\': \''+str(key_name[i])+'\',\'value\':'+str(key_value[i])+'}'
            f.write(key_data)
    f.close()