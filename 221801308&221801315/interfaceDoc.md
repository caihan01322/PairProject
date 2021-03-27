# 接口文档

[TOC]

## 登录：
### 接口功能
> 登录，登陆后返回登陆后的主页

### URL
> [http://127.0.0.1:5000/login_view](http://127.0.0.1:5000/login_view)

### HTTP请求方式
> POST

### 请求参数
|参数|必选|类型|说明|
|-----  |-------|-----|----- |
|email   |true    |string|用于登陆的邮箱账号
|password   |true    |String|用于登陆的密码
|remember|false |String  |复选框的name

### 返回字段
#### 登陆成功
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|html文件名   |String    |登陆后的主页模板 |
#### 登陆失败
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|code   |int    | -1：出错 |
|message   |String    |出错的情况|

### 接口示例
输入：
> email="784536133@qq.com"  password="123456" 
>
返回：
> 地址：[http://127.0.0.1:5000/index_logined.html](http://127.0.0.1:5000/index_logined.html)


## 注册：
### 接口功能
> 注册，注册后返回登陆界面

### URL
> [http://127.0.0.1:5000/register_view](http://127.0.0.1:5000/register_view)

### HTTP请求方式
> POST

### 请求参数
|参数|必选|类型|说明|
|-----  |-------|-----|----- |
|email   |true    |string|用于登陆的邮箱账号
|password   |true    |String|用于登陆的密码

### 返回字段
#### 登陆成功
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|html文件名   |String    |登录页面 |
#### 登陆失败
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|code   |int    | -1：出错 |
|message   |String    |出错的情况|

### 接口示例
输入：
> email="test@qq.com"  password="123456" 
>
返回：
> 地址：[http://127.0.0.1:5000/login_view](http://127.0.0.1:5000/login_view)


## 检索论文：search
### 接口功能
> 根据论文标题或关键词组在论文列表**模糊**检索

### URL
> [http://127.0.0.1:5000/search_view](http://127.0.0.1:5000/search_view)

### HTTP请求方式
> GET

### 请求参数
|参数|必选|类型|说明|
|-----  |-------|-----|----- |
|condition   |true    |string|请求检索的调节，大小写均可，表单应给出提示
|page   |false    |int|分页器指示当前是第几页，该参数**不用表单传入**，默认值为1
|search_way|true  |String  |单选按钮组name=search_way选择查询方式，标题value=title，关键词value=keyword

### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|html文件名   |String    |结果要渲染的模板名 |
|pagination   |object    | 分页器 |
|pagination_func   |String    | 提供分页器的函数名 |
|condition   |String    |与请求参数中的condition一致 |
|search_way |String    |与请求参数中单选按钮组的name=search_way一致 |
#### pagination
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|items  |list |分页后每一页的内容                      |
|has_prev |boolean |当前页是否有上一页                       |
|prev_num |int |上一页的页码                    |
|has_next |boolean |当前页是否有下一页                 |
|next_num |int |下一页的页码                     |
|page |int |当前页的页码                       |
|pages |int |总页数                       |

### 接口示例
输入：
> condition="3D"  单选框：标题 
>
返回：
> 地址：[http://127.0.0.1:5000/search?page=2&condition=3D&search_way=title](http://127.0.0.1:5000/search?page=2&condition=3D&search_way=title)
![图片示例](https://images.cnblogs.com/cnblogs_com/blogs/664343/galleries/1953060/o_210327124154example.png)