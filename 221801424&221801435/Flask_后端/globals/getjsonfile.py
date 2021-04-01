#因为数据库操作使用flask-sqlachemy直接操作所以需要注释掉避免冲突



# import os
#
# import json
# from Team_work.globals.models import Meeting_article
# from Team_work.app import db
# import datetime
# #文章类
# class Article:
#     meeting = ['CVPR', 'ECCV', 'ICCV']
#
#     def __init__(self):
#         self.title = "暂无"
#         self.time = "xxxx-xx-xx"
#         self.meeting_name = "暂无会议名称"
#         self.auth_name = "暂无作者"
#         self.abstract = "暂无摘要"
#         self.keyword = "暂无关键词"
#         self.address = "暂无论文地址"
#         self.pretime="2000"
#
#     def set_title(self, title):
#         self.title = title
#
#     def set_time(self, time):
#         if(list(time)[0]!='2'):
#             time=self.pretime
#         self.time=time+"-01-01"
#         self.time = datetime.datetime.strptime(self.time, '%Y-%m-%d')
#         self.pretime=time
#
#     def set_meeting_name(self, meeting_index):
#         self.meeting_name = self.meeting[meeting_index]
#
#     def set_auth_name(self, auth_name):
#         self.auth_name = auth_name
#
#     def set_abstract(self, abstract):
#         self.abstract = abstract
#
#     def set_keyword(self, keyword):
#         self.keyword = keyword
#
#     def set_address(self, address):
#         self.address = address
#
#
# #判断是否存在键
# def judge_in(key,Json):
#     if(key in Json):
#         return True
#     else:
#         return False
#
# #获取CVPR论文数据保存至数据库
# def get_CVPR():
#     # 获取目标文件夹的路径
#     filedir = 'F:\\电脑桌面文件\\论文' + '\\CVPR'
#     #存储对象数组
#     All_object=[]
#     # 获取文件夹中的文件名称列表
#     filenames = os.listdir(filedir)
#     # 遍历文件名
#     Infos = []
#     for filename in filenames:
#         filepath = filedir + '/' + filename
#         # 打开文件取出数据，然后存入对象
#         with open(filepath, 'rb') as f:
#             all_str=f.read()
#             Infos.append(all_str[:-1])
#             f.close()
#     for i in Infos:
#         temp=json.loads(i.decode('utf-8'))
#         new_article= Article()
#         new_article.set_meeting_name(0)
#         #获取论文作者
#         name=''
#         all_name=temp['authors']
#         for j in all_name:
#             name+=(j['name']+';')
#         if(name!=''):
#             new_article.set_auth_name(name)
#         #获取论文关键词
#         keyword=''
#         temp_kwd = []
#         if (judge_in('keywords', temp)):
#             all_key=temp['keywords']
#             for j in all_key:
#                 all_kwd=j['kwd']
#                 for k in all_kwd:
#                     if(k not in temp_kwd):
#                         temp_kwd.append(k)
#                         keyword+=(k+';')
#             if(keyword!=''):
#                 new_article.set_keyword(keyword)
#         #获取论文摘要
#         if (judge_in('abstract', temp)):
#             abstract=temp['abstract']
#             if(abstract!=''):
#                 new_article.set_abstract(abstract)
#         #获取论文地址
#         if(judge_in('doiLink',temp)):
#             address = temp['doiLink']
#             if (address != ''):
#                 new_article.set_address(address)
#         #获取论文标题
#         if (judge_in('title', temp)):
#             title=temp['title']
#             if(title!=''):
#                 new_article.set_title(title)
#         #获取论文年份
#         if (judge_in('chronOrPublicationDate', temp)):
#             time=temp['chronOrPublicationDate'][-4:]
#             if(time!=''):
#                 new_article.set_time(time)
#             All_object.append(new_article)
#
#     for i in All_object:
#         try:
#             new_article=Meeting_article(create_time=i.time,meeting_name=i.meeting_name,title=
#                                         i.title,author=i.auth_name,abstract=i.abstract,
#                                         keyword=i.keyword,address=i.address)
#             db.session.add(new_article)
#             db.session.commit()
#         except Exception as e:
#             print(e)
#             db.session.rollback()
#
#
# #获取ECCV论文数据保存至数据库
# def get_ECCV():
#     # 获取目标文件夹的路径
#     filedir = 'F:\\电脑桌面文件\\论文' + '\\ECCV'
#     #存储对象数组
#     All_object=[]
#     # 获取文件夹中的文件名称列表
#     filenames = os.listdir(filedir)
#     # 遍历文件名
#     Infos = []
#     for filename in filenames:
#         filepath = filedir + '/' + filename
#         # 打开文件取出数据，然后存入对象
#         with open(filepath, 'rb') as f:
#             all_str=f.read()
#             Infos.append(all_str)
#             f.close()
#     for i in Infos:
#         temp=json.loads(i.decode('utf-8'))
#         new_article= Article()
#         new_article.set_meeting_name(1)
#
#         #获取论文关键词
#         keyword=''
#         temp_kwd = []
#         if (judge_in('关键词', temp)):
#             all_key=temp['关键词']
#             for j in all_key:
#                 if (j not in temp_kwd):
#                     temp_kwd.append(j)
#                     keyword += (j + ';')
#             if(keyword!=''):
#                 new_article.set_keyword(keyword)
#         #获取论文摘要
#         if (judge_in('摘要', temp)):
#             abstract=temp['摘要']
#             if(abstract!=''):
#                 new_article.set_abstract(abstract)
#         #获取论文地址
#         if(judge_in('原文链接',temp)):
#             address = temp['原文链接']
#             if (address != ''):
#                 new_article.set_address(address)
#         #获取论文标题
#         if (judge_in('论文名称', temp)):
#             title=temp['论文名称']
#             if(title!=''):
#                 new_article.set_title(title)
#         #获取论文年份
#         if (judge_in('发布时间', temp)):
#             time=temp['发布时间'][-4:]
#             if(time!=''):
#                 new_article.set_time(time)
#             else:
#                 if (judge_in('会议和年份', temp)):
#                     time = temp['会议和年份'][-4:]
#                     if(time!=''):
#                         new_article.set_time(time)
#             All_object.append(new_article)
#
#     for i in All_object:
#         try:
#             new_article=Meeting_article(create_time=i.time,meeting_name=i.meeting_name,title=
#                                         i.title,author=i.auth_name,abstract=i.abstract,
#                                         keyword=i.keyword,address=i.address)
#             db.session.add(new_article)
#             db.session.commit()
#         except Exception as e:
#             print(e)
#             db.session.rollback()
#
#
# #获取ICCV论文数据保存至数据库
# def get_ICCV():
#     # 获取目标文件夹的路径
#     filedir = 'F:\\电脑桌面文件\\论文' + '\\ICCV'
#     #存储对象数组
#     All_object=[]
#     # 获取文件夹中的文件名称列表
#     filenames = os.listdir(filedir)
#     # 遍历文件名
#     Infos = []
#     for filename in filenames:
#         filepath = filedir + '/' + filename
#         # 打开文件取出数据，然后存入对象
#         with open(filepath, 'rb') as f:
#             all_str=f.read()
#             Infos.append(all_str[:-1])
#             f.close()
#     for i in Infos:
#         temp=json.loads(i.decode('utf-8'))
#         new_article= Article()
#         new_article.set_meeting_name(2)
#         #获取论文作者
#         name=''
#         all_name=temp['authors']
#         for j in all_name:
#             name+=(j['name']+';')
#         if(name!=''):
#             new_article.set_auth_name(name)
#         #获取论文关键词
#         keyword=''
#         temp_kwd = []
#         if (judge_in('keywords', temp)):
#             all_key=temp['keywords']
#             for j in all_key:
#                 all_kwd=j['kwd']
#                 for k in all_kwd:
#                     if(k not in temp_kwd):
#                         temp_kwd.append(k)
#                         keyword+=(k+';')
#             if(keyword!=''):
#                 new_article.set_keyword(keyword)
#         #获取论文摘要
#         if (judge_in('abstract', temp)):
#             abstract=temp['abstract']
#             if(abstract!=''):
#                 new_article.set_abstract(abstract)
#         #获取论文地址
#         if(judge_in('doiLink',temp)):
#             address = temp['doiLink']
#             if (address != ''):
#                 new_article.set_address(address)
#         #获取论文标题
#         if (judge_in('title', temp)):
#             title=temp['title']
#             if(title!=''):
#                 new_article.set_title(title)
#         #获取论文年份
#         if (judge_in('chronOrPublicationDate', temp)):
#             time=temp['chronOrPublicationDate'][-4:]
#             if(time!=''):
#                 new_article.set_time(time)
#             All_object.append(new_article)
#
#     for i in All_object:
#         try:
#             new_article=Meeting_article(create_time=i.time,meeting_name=i.meeting_name,title=
#                                         i.title,author=i.auth_name,abstract=i.abstract,
#                                         keyword=i.keyword,address=i.address)
#             db.session.add(new_article)
#             db.session.commit()
#         except Exception as e:
#             print(e)
#             db.session.rollback()
#
#
#
#
# if __name__=="__main__":
#     get_CVPR()
#     get_ECCV()
#     get_ICCV()
