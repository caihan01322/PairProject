# 接口文档

[toc]

## 根据论文标题检索论文：search_by_title
### 接口功能
> 根据论文标题在论文列表**模糊**检索

### URL
> [http://127.0.0.1:8080/a/search_by_title](http://127.0.0.1:8080/a/search_by_title)

### 支持格式
> JSON

### HTTP请求方式
> GET

### 请求参数
|参数|必选|类型|说明|
|-----  |-------|-----|----- |
|title   |ture    |string|请求检索的论文部分标题名（大小写均可）     

### 返回字段
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|code   |int    |返回结果代码。0：正常；-1：错误；1：没有输入  |
|message   |string    |返回信息的解释  |
|data   |json    |返回查询结果 |
#### data
|返回字段|字段类型|说明                              |
|:-----   |:------|:-----------------------------   |
|meeting  |string |所属会议名                      |
|title |string |论文完整标题                        |
|year |string |出版年份                     |
|keywords |string[] |IEEE关键词                 |
|abstract |string |摘要                        |
|doiLink |url |原文链接                        |

### 接口示例
> 地址：[http://127.0.0.1:8080/a/search_by_title?title=3D](http://127.0.0.1:8080/a/search_by_title?title=3D)
``` javascript
{
  "code": 0, 
  "data": [
    {
      "abstract": ..., 
      "doiLink": "https://doi.org/10.1109/CVPR.2000.854948", 
      "keywords": [
        "Layout", 
        "Image reconstruction", 
        "Filters", 
        "Cameras", 
        "Military computing", 
        "Linear systems", 
        "Information technology", 
        "Mathematics", 
        "Image sensors", 
        "Image sequences"
      ], 
      "meeting": "CVPR", 
      "title": "A batch/recursive algorithm for 3D scene reconstruction", 
      "year": "2000"
    },
    ...
  ],  
  "message": "查询成功"
}
```
