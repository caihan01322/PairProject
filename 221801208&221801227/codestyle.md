# 代码规范
|  代码规范参考链接  | 
|  ----  | 
| [阿里巴巴Java开发手册](https://github.com/chjw8016/alibaba-java-style-guide)  | 
| [腾讯alloyteam团队前端代码规范](https://www.w3cschool.cn/wematy/wematy-tizr3bsp.html)  |

## HTML规范

### （一）DOCTYPE
> &ensp;&ensp;&ensp;&ensp;1.一个 ASCII 字符串 “<!DOCTYPE>” ，大小写不敏感
> &ensp;&ensp;&ensp;&ensp;2.一个或多个空白字符
> &ensp;&ensp;&ensp;&ensp;3.一个 ASCII 字符串” html ”，大小写不敏感
> &ensp;&ensp;&ensp;&ensp;4.一个或多个空白字符

### （二）元素标签的闭合
> &ensp;&ensp;&ensp;&ensp;1.原始文本元素、RCDATA元素以及常规元素都有一个开始标签来表示开始，一个结束标签来表示结束。
> &ensp;&ensp;&ensp;&ensp;2.空元素只有一个开始标签，且不能为空元素设置结束标签。
> &ensp;&ensp;&ensp;&ensp;3.外来元素可以有一个开始标签和配对的结束标签，或者只有一个自闭合的开始标签，且后者情况下该元素不能有结束标签。

### （三）HTML代码大小写
> &ensp;&ensp;&ensp;&ensp;1.HTML标签名、类名、标签属性和大部分属性值统一用小写。
> &ensp;&ensp;&ensp;&ensp;2.HTML文本、CDATA、JavaScript、meta标签某些属性等内容可大小写混合。

### （四）代码缩进
> &ensp;&ensp;&ensp;&ensp;1.统一使用四个空格进行代码缩进。

## CSS规范

### （一）文档外链样式表编码
> 文档外链样式表的编码可以由以下各项按照由高到低的优先级顺序决定：
> &ensp;&ensp;&ensp;&ensp;1.HTTP “Content-Type”字段参数“charset”（或其它协议相似的参数）
> &ensp;&ensp;&ensp;&ensp;2.BOM（byte-order mark）和（或）@charset
> &ensp;&ensp;&ensp;&ensp;3.Link 中的元数据设置（如果有的话）
> &ensp;&ensp;&ensp;&ensp;4.引用样式表字符集或文档编码（如果有的话）
> &ensp;&ensp;&ensp;&ensp;5.假定为 UTF-8 编码

### （二）代码大小写
> &ensp;&ensp;&ensp;&ensp;1.样式选择器，属性名，属性值关键字全部使用小写字母书写。
> &ensp;&ensp;&ensp;&ensp;2.属性字符串允许使用大小写。

### （三）缩进
> &ensp;&ensp;&ensp;&ensp;1.统一使用四个空格进行代码。

### （四）属性值引号
> &ensp;&ensp;&ensp;&ensp;1.css 属性值需要用到引号时，统一使用单引号。

### （五）属性书写顺序
> 属性书写遵循以下顺序：
> &ensp;&ensp;&ensp;&ensp;1.布局定位属性：display / position / float / clear / visibility / overflow
> &ensp;&ensp;&ensp;&ensp;2.自身属性：width / height / margin / padding / border / background
> &ensp;&ensp;&ensp;&ensp;3.文本属性：color / font / text-decoration / text-align / vertical-align / white- space / break-word
> &ensp;&ensp;&ensp;&ensp;4.其他属性（CSS3）：content / cursor / border-radius / box-shadow / text-shadow / background:linear-gradient …

### （六）注释
> &ensp;&ensp;&ensp;&ensp;1.注释以字符 /* 开始，以字符 */ 结束。
> &ensp;&ensp;&ensp;&ensp;2.单行注释内容第一个字符和最后一个字符都是一个空格字符，单独占一行，行与行之间相隔一行。
> &ensp;&ensp;&ensp;&ensp;3.模块注释内容第一个字符和最后一个字符都是一个空格字符，/ * 与 模块信息描述占一行，多个横线分隔符-与*/占一行，行与行之间相隔两行。

## javascript规范

### （一）单行代码块
> &ensp;&ensp;&ensp;&ensp;1.在单行代码块中使用空格。

### （二）变量命名
> &ensp;&ensp;&ensp;&ensp;1.约定使用驼峰式命名。

### （三）逗号
> &ensp;&ensp;&ensp;&ensp;1.逗号前后的空格可以提高代码的可读性，约定在逗号后面使用空格，逗号前面不加空格。

### （四）函数调用
> &ensp;&ensp;&ensp;&ensp;1.为了避免语法错误，团队约定在函数调用时，禁止使用空格。

### （五）对象字面量的键值缩进
> &ensp;&ensp;&ensp;&ensp;1.对象字面量的键和值之间不能存在空格，且要求对象字面量的冒号和值之间存在一个空格。

### （六)变量声明
> &ensp;&ensp;&ensp;&ensp;1.JavaScript 允许在一个声明中，声明多个变量。团队约定在声明变量时，一个声明只能有一个变量。

## 后端规范

### （一）缩进

>1.一个tab，相当于四个空格

### （二）变量命名

>1.采用驼峰命名法

```
反例：macro_polo / User_do / XML_service / TCP_UDP_deal
正例：MarcoPolo / UserDO / XmlService / TcpUdpDeal 
```

>2.变量名应与本身含义密切相关，严禁使用拼音与英文混合的方式

```
反例：DaZhePromotion [打折] / Pingfen [评分]
正例：inputFile [输入文件] / WordNum [单词总数]
```

>3.命名均不能以下划线或美元符号开始

### （三）每行最多字符数

>1.每行最多100个字符，超出需要换行

### （四）函数最大行数

>1.一个函数不能超过50行

### （五）函数、类命名

>1.函数命名：驼峰命名，采用动宾短语、纯动词或名词-介词-名词的形式（要能体现函数意义），首字母小写

```
正例：wordToHashMap（）、countWord()
反例：Count_word()
```

>2.类命名：驼峰命名，首字母大写；抽象类命名使用Abstract或Base开头，异常类命名使用Exception结尾，测试类命名以它要测试的类的名称开始，以Test结尾

```
正例：MyDatabase
反例：my_database
```

### （六）常量

>1.单词间用下划线隔开
>2.常量命名全部大写

```
正例： MAX_STOCK_COUNT
反例： maxCount
```

### （七）空行规则

>1.一个语句独占一行
>2.左大括号不换行，右大括号换行

###（八）注释规则
>1.方法内部单行注释，在被注释语句上方另起一行，使用`//`注释。方法内部多行注释使用`/* */`注释，注意与代码对齐。
>2.类属性用`//`注释，类方法在头部上方用`/** */`注释

###（九）操作符前后空格
>1.仅单目操作符前后不需要空格，其他操作符前后都需要空格

### （十）其他规则

>1.在使用正则表达式时，利用好其预编译功能，可以有效加快正则匹配速度
>2.类内方法定义顺序依次是：公有方法或保护方法 >私有方法 > get/set方法，属性写在方法前面




