# 接口文档

[toc]

## 根据论文标题检索论文：search_by_title
### 接口功能
> 根据论文标题在论文列表**模糊**检索

### URL
> [http://127.0.0.1:5000/a/search_by_title](http://127.0.0.1:5000/a/search_by_title)

### 支持格式
> 模板渲染

### HTTP请求方式
> GET

### 请求参数
|参数|必选|类型|说明|
|-----  |-------|-----|----- |
|condition   |true    |string|请求检索的论文部分标题名，大小写均可，表单应给出提示
|page   |false    |int|分页器指示当前是第几页，该参数**不用表单传入**，默认值为1

### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|html文件名   |String    |结果要渲染的模板 |
|pagination   |object    | 分页器 |
|condition   |String    |与请求参数中的condition一致 |
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
<span id="search_by_title"></span>
> 地址：[http://127.0.0.1:5000/a/search_by_title?page=2&condition=3D](http://127.0.0.1:5000/a/search_by_title?page=2&condition=3D)
![图片示例](https://images.cnblogs.com/cnblogs_com/blogs/664343/galleries/1953060/o_210326120257example.PNG)

## 根据论文关键词检索论文：search_by_keyword
### 接口功能
> 根据论文关键词在论文列表**模糊**检索，可同时输入多个关键字，将返回查询结果的并集

### URL
> [http://127.0.0.1:5000/a/search_by_keyword](http://127.0.0.1:5000/a/search_by_keyword)

### 支持格式
> 模板渲染

### HTTP请求方式
> GET

### 请求参数
|参数|必选|类型|说明|
|-----  |-------|-----|----- |
|condition   |ture    |string|请求检索的关键词，多个关键词用 **英文逗号（,）** 分割，大小写均可，表单应给出输入提示
|page   |false    |int|分页器指示当前是第几页，该参数**不用表单传入**，默认值为1

### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|html文件名   |String    |结果要渲染的模板 |
|pagination   |object    | 分页器 |
|condition   |String    |与请求参数中的condition一致 |
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
> 地址：[http://127.0.0.1:5000/a/search_by_keyword?page=2&condition=layOut%2CComputer+science](http://127.0.0.1:5000/a/search_by_keyword?page=2&condition=layOut%2CComputer+science)
>
> 搜索结果如[search_by_title](#search_by_title)