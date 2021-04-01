引用：html+css规范来自腾讯，php规范来自w3c

## 命名

1.项目名称全部采用小写，以下划线分割。如my_project
2.目录命名全部采用小写，以下划线分割。有复数结构时，要采用复数命名法。

## html

缩进使用soft tab（4个空格）；
嵌套的节点应该缩进；
在属性上，使用双引号，不要使用单引号；
属性名全小写，用中划线做分隔符；
不要在自动闭合标签结尾处使用斜线（HTML5 规范 指出他们是可选的）；
不要忽略可选的关闭标签。
自动闭合标签：
```html
<meta>标签:设置页面元信息的
<base>:设置网页所有链接的相对目录(如根目录)的
<br>:换行
<hr>:水平线
<img>:图像
<input>:表单元素
<col>:在表格table中定义一个或多个列的属性
<frame>:定义框架的一个窗口（已遗弃）
<link>:定义文档与外部资源的关系的链接
<area>: 标签定义图像映射内部的区域（图像映射指的是带有可点击区域的图像）。
<param>:元素允许您为插入 XHTML 文档的对象规定 run-time 设置，也就是说，此标签可为包含它的
<object> 或者<applet> 标签提供参数。
<embed>: HTML5 中新增的,标签定义了一个容器，用来嵌入外部应用或者互动程序（插件）。
<keygen>:该对象提供了一个安全的方式来验证用户。
<source>: 标签为媒体元素（比如 和 ）定义媒体资源。
```


在页面开头使用这个简单地doctype来启用标准模式，使其在每个浏览器中尽可能一致的展现；按照惯例使用大写，<!DOCTYPE html>
应在html标签加上lang属性。这会给语音工具和翻译工具帮助，告诉它们应当怎样去发音和翻译。中文:zh-cn,英文:en-us；
 字符编码：通过声明一个明确的字符编码，让浏览器轻松、快速的确定适合网页内容的渲染方式，通常指定为'UTF-8'。
IE兼容模式：用 <meta> 标签可以指定页面应该用什么版本的IE来渲染；

<meta http-equiv="X-UA-Compatible" content="IE=Edge">
 以上代码告诉IE浏览器，IE8/9及以后的版本都会以最高版本IE来渲染页面。

根据HTML5规范，通常在引入CSS和JS时不需要指明type，因为text/css和text/javascript分别时它们的默认值。
<link rel="stylesheet" href="code_guide.css">

<script src="code_guide.js"></script>

属性顺序应该按照特定的顺序出现以保证易读性：

    class
    
    id
    
     name
    
     data-*
    
     src, for, type, href, value , max-length, max, min, pattern
    
     placeholder, title, alt
    
     aria-*, role
    
     required, readonly, disabled

HTML5并不需要声明取值的属性，元素上存在布尔属性表示真值，缺少属性表示false值。布尔属性不允许使用值“true”和“false”。要表示错误值，必须完全省略该属性。
<input type="text" disabled>
 在JS文件中生成标签让内容变得更难查找，更难编辑，性能更差。应该尽量避免这种情况的出现。
在编写html代码时，需要尽量避免多余的父节点，很多时候需要通过迭代和重构来使HTML变得更少。


尽量遵循HTML标准和语义，但是不应该以浪费实用性作为代价；任何时候都要用尽量小的复杂度和尽量少的标签来解决问题。

 

CSS，SCSS
以下几种情况不需要空格：

属性名后
多个规则的分隔符','前
!important '!'后
属性值中'('后和')'前
行末不要有多余的空格
以下几种情况需要空格：

#### 空行

属性值前
选择器'>', '+', '~'前后
'{'前
!important '!'前
@else 前后
属性值中的','后
注释'/*'后和'*/'前
需要换行的情况：

'{'后和'}'前
每个属性独占一行
多个规则的分隔符','后
/* not good */
.element, .dialog {
    ...
}
/* good */
.element,
.dialog {
    ...
}

#### 注释

注释统一用'/* */'（scss中也不要用'//'），具体参照右边的写法；

缩进与下一行代码保持一致；

可位于一个代码行的末尾，与代码间隔一个空格。

/* Modal header */
.modal-header {
    ...
}
/*

 * Modal header
   */

#### 引号

最外层统一使用双引号；
url的内容要用引号；
属性选择器中的属性值需要引号。
命名

类名使用小写字母，以中划线分隔
id采用驼峰式命名
scss中的变量、函数、混合、placeholder采用驼峰式命名
属性声明顺序
相关的属性声明按右边的顺序做分组处理，组之间需要有一个空行。

```css
.declaration-order {
    display: block;
    float: right;
    
position: absolute;
top: 0;
right: 0;
bottom: 0;
left: 0;
z-index: 100;
 
border: 1px solid #e5e5e5;
border-radius: 3px;
width: 100px;
height: 100px;
 
font: normal 13px "Helvetica Neue", sans-serif;
line-height: 1.5;
text-align: center;
 
color: #333;
background-color: #f5f5f5;
 
opacity: 1;
```

}

##### 颜色

颜色16进制用小写字母；

颜色16进制尽量用简写。

属性简写
属性简写需要你非常清楚属性值的正确顺序，而且在大多数情况下并不需要设置属性简写中包含的所有值，所以建议尽量分开声明会更加清晰；

margin 和 padding 相反，需要使用简写；

```css
/* not good */
.element {
    transition: opacity 1s linear 2s;
}

/* good */
.element {
    transition-delay: 2s;
    transition-timing-function: linear;
    transition-duration: 1s;
    transition-property: opacity;
}
```

媒体查询
尽量将媒体查询的规则靠近与他们相关的规则，不要将他们一起放到一个独立的样式文件中，或者丢在文档的最底部，这样做只会让大家以后更容易忘记他们。

SCSS相关
提交的代码中不要有 @debug；

声明顺序：

@extend
不包含 @content 的 @include
包含 @content 的 @include
自身属性
嵌套规则
@import 引入的文件不需要开头的'_'和结尾的'.scss'；

嵌套最多不能超过5层；

@extend 中使用placeholder选择器；

去掉不必要的父级引用符号'&'。

#### 杂项

不允许有空的规则；
元素选择器用小写字母；
去掉小数点前面的0；
去掉数字中不必要的小数点和末尾的0；
属性值'0'后面不要加单位；
同个属性不同前缀的写法需要在垂直方向保持对齐，具体参照下边的写法；
无前缀的标准属性应该写在有前缀的属性后面；
不要在同个规则里出现重复的属性，如果重复的属性是连续的则没关系；
不要在一个文件里出现两个相同的规则；
用 border: 0; 代替 border: none;；
选择器不要超过4层（在scss中如果超过4层应该考虑用嵌套的方式来写）；
发布的代码中不要有 @import；
尽量少用'

```css
/* not good */
.element {
    border-radius: 3px;
    -webkit-border-radius: 3px;
    -moz-border-radius: 3px;

    background: linear-gradient(to bottom, #fff 0, #eee 100%);
    background: -webkit-linear-gradient(top, #fff 0, #eee 100%);
    background: -moz-linear-gradient(top, #fff 0, #eee 100%);

}

/* good */
.element {
    -webkit-border-radius: 3px;
       -moz-border-radius: 3px;
            border-radius: 3px;

    background: -webkit-linear-gradient(top, #fff 0, #eee 100%);
    background:    -moz-linear-gradient(top, #fff 0, #eee 100%);
    background:         linear-gradient(to bottom, #fff 0, #eee 100%);

}
```

## JavaScript

#### 缩进

使用soft tab（4个空格）。

#### 单行长度

不要超过80，但如果编辑器开启word wrap可以不考虑单行长度。

分号
以下几种情况后需加分号：

 

变量声明
表达式
return
throw
break
continue
do-while

#### 空格

以下几种情况不需要空格：

对象的属性名后 前缀一元运算符后

以下几种情况需要空格：

二元运算符前后
三元运算符'?:'前后
代码块'{'前
下列关键字前：else, while, catch, finally
下列关键字后：if, else, for, while, do, switch, case, try, catch, finally, with, return, typeof
单行注释'//'后（若单行注释和代码同行，则'//'前也需要），多行注释'*'后
对象的属性值前
for循环，分号后留有一个空格，前置条件如果有多个，逗号后留一个空格
无论是函数声明还是函数表达式，'{'前一定要有空格
函数的参数之间
后缀一元运算符前
函数调用括号前
无论是函数声明还是函数表达式，'('前不要空格
数组的'['后和']'前
对象的'{'后和'}'前
运算符'('后和')'前

以下几种情况需要空行：

变量声明后（当变量声明在代码块的最后一行时，则无需空行）
注释前（当注释在代码块的第一行时，则无需空行）
代码块后（在函数调用、数组、对象中则无需空行）
文件最后保留一个空行

#### 换行

换行的地方，行末必须有','或者运算符；

以下几种情况不需要换行：

下列关键字后：else, catch, finally 代码块'{'前

以下几种情况需要换行：

代码块'{'后和'}'前
变量赋值后
单行注释
双斜线后，必须跟一个空格；

缩进与下一行代码保持一致；

可位于一个代码行的末尾，与代码间隔一个空格。

#### 多行注释

最少三行, '*'后跟一个空格，具体参照右边的写法；

建议在以下情况下使用：

难于理解的代码段
可能存在错误的代码段
浏览器特殊的HACK代码
业务逻辑强相关的代码
引号
最外层统一使用单引号。

// not good
var x = "test";

// good
var y = 'foo'
变量命名

标准变量采用驼峰式命名（除了对象的属性外，主要是考虑到cgi返回的数据）
'ID'在变量名中全大写
'URL'在变量名中全大写
'Android'在变量名中大写第一个字母
'iOS'在变量名中小写第一个，大写后两个字母
常量全大写，用下划线连接
构造函数，大写第一个字母
jquery对象必须以'$'开头命名

变量声明
一个函数作用域中所有的变量声明尽量提到函数首部，用一个var声明，不允许出现两个连续的var声明。

```javascript
var thisIsMyName;

var goodID;

var reportURL;

var AndroidVersion;

var iOSVersion;

var MAX_COUNT = 10;

function Person(name) {
    this.name = name;
}

// not good
var body = $('body');

// good
var $body = $('body');
```

变量声明
一个函数作用域中所有的变量声明尽量提到函数首部，用一个var声明，不允许出现两个连续的var声明。

```javascript
function doSomethingWithItems(items) {
    // use one var
    var value = 10,
        result = value + 10,
        i,
        len;

    for (i = 0, len = items.length; i < len; i++) {
        result += 10;
    }

}
```


函数
无论是函数声明还是函数表达式，'('前不要空格，但'{'前一定要有空格；

函数调用括号前不需要空格；

立即执行函数外必须包一层括号；

不要给inline function命名；

参数之间用', '分隔，注意逗号后有一个空格。

```javascript
// no space before '(', but one space before'{'
var doSomething = function(item) {
    // do something
};

function doSomething(item) {
    // do something
}

// not good
doSomething (item);

// good
doSomething(item);
```


数组、对象
对象属性名不需要加引号；

对象以缩进的形式书写，不要写在一行； 

数组、对象最后不要有逗号。

括号
下列关键字后必须有大括号（即使代码块的内容只有一行）：if, else, for, while, do, switch, try, catch, finally, with。

null
适用场景：

初始化一个将来可能被赋值为对象的变量
与已经初始化的变量做比较
作为一个参数为对象的函数的调用传参
作为一个返回对象的函数的返回值
不适用场景：

不要用null来判断函数调用时有无传参
不要与未初始化的变量做比较
undefined
永远不要直接使用undefined进行变量判断；

使用typeof和字符串'undefined'对变量进行判断。

```javascript
// not good
if (person === undefined) {
    ...
}

// good
if (typeof person === 'undefined') {
    ...
}
jshint
```

用'===', '!=='代替'==', '!='；
for-in里一定要有hasOwnProperty的判断；
不要在内置对象的原型上添加方法，如Array, Date；
不要在内层作用域的代码里声明了变量，之后却访问到了外层作用域的同名变量；
变量不要先使用后声明；
不要在一句代码中单单使用构造函数，记得将其赋值给某个变量；
不要在同个作用域下声明同名变量；
不要在一些不需要的地方加括号，例：delete(a.b)；
不要使用未声明的变量（全局变量需要加到.jshintrc文件的globals属性里面）；
不要声明了变量却不使用；
不要在应该做比较的地方做赋值；
debugger不要出现在提交的代码里；
数组中不要存在空元素；
不要在循环内部声明函数；
不要像这样使用构造函数，例：new function () { ... }, new Object；
杂项
不要混用tab和space；

不要在一处使用多个tab或space；

换行符统一用'LF'；

对上下文this的引用只能使用'_this', 'that', 'self'其中一个来命名；

行尾不要有空白字符；

switch的falling through和no default的情况一定要有注释特别说明；

不允许有空的代码块。 

## PHP

#### 一、规范前言篇

标准化不是特殊的个人风格，它让程序员可以了解任何代码，弄清程序的状况；新人可以很快的适应环境；防止新接触php的人一次次的犯同样的错误；在一致的开发环境下，可以减少人们犯错的机会。本规范的标准在绝大多数应用上仿照java技术体系，因为java技术体系以其众多成功的案例成为大部分计算机应用层的工业标准，此外便于日后公司向java技术体系转型。

#### 二、命名定义篇

##### 局部变量命名

使用英文名词、动词，以大写字母作为单词的分隔，其他的字母均使用小写，单词的首个字母使用小写，不使用下划线，例：

```php
$repeatCount = '';
$delUserSql  = '';

```

##### 全局常量命名

使用英文名词、动词，所有字母都使用大写，以下划线分隔每个单词，例：

```php
define( 'WEBSITE_NAME', '名称' );
define( 'WEBSITE_URL',  '地址' )
```



##### 数组变量命名

使用英文名词、动词，以大写字母作为单词的分隔，其他的字母均使用小写，单词的首个字母使用小写，不使用下划线，以字符串Array为后缀，例：

```
$scopeArray  = array();
$bookIdArray = array();
```

##### 静态变量命名

使用英文名词、动词，以大写字母作为单词的分隔，其他的字母均使用小写，单词的首个字母使用小写，不使用下划线，以字符串Static为后缀，例：

```php
function getDirectoryFile()
{
    static $fileArrayStatic = '';
    static $fileNumStatic   = '';
    ...
}
```

##### 对象变量命名

使用类名称为变量前缀，所有字母都使用大写，以字符串_OBJECT为后缀，例：

```php
$USERACCOUNT_OBJECT   = new UserAccount();
$PAINTINGORDER_OBJECT = new PaintingOrder();
```

##### 类命名

使用英文名词，以大写字母作为词的分隔，其他的字母均使用小写，名词的首个字母使用大写，不使用下划线，例：

```php
class UserAccount
{
    ...
}
class PaintingOrder
{
    ...
}
```

##### 方法命名

使用英文名词、动词，以大写字母作为词的分隔，其他的字母均使用小写，单词的首个字母使用小写，不使用下划线，例：

```php
class UserAccount
{
    function isAccountOk()
    {
        ...
    }
    function addAccount()
    {
        ...
    }
}
```

##### 方法中参数命名

使用英文名词、动词，以大写字母作为词的分隔，其他的字母均使用小写，单词的首个字母使用小写，不使用下划线，例：

```php
class UserAccount
{
    function isAccountOk( $accountName )
    {
        $this->accountName = $accountName;
        ...
    }
    function addAccount( $inputDataArray )
    {
        $this->inputArray = $inputDataArray;
        ...
    }
    var $accuntName = '';
    var $inputArray = '';
}
```

##### 类属性命名

使用英文名词、动词，以大写字母作为词的分隔，其他的字母均使用小写，单词的首个字母。

使用大写，不使用下划线，对于类属性为某个对象变量，则以字符串Object为后缀，例：

```php
class UserAccount
{
    function IsAccountOk()
    {
        ...
    }
    function AddAccount()
    {
        ...
    }
    var $tableName      = '';
    var $databaseObject = '';
}
```



#### 三、语法书写篇

##### 大括号{}规则

将大括号放置在关键词下方的同列处，例：

```php
if ( $condition )
{
    ...
}
不使用此种方式：

if ( $condition ) 
{
    ...
}
```



##### 代码缩进规则

使用制表符缩进（TAB键）或四个空格。如果缩进层数大于四的时候，请重新设计该项业务逻辑的算法。

##### 小括号()规则

不要把小括号和关键词、方法名、方法参数紧贴在一起，要用一个空格分隔，例：

```php
if ( $condition )
{
    ...
}
function addAccount( $inputDataArray )
{
    ...
}
```


由于小括号与关键词等紧贴容易被看成是一体，因此不要使用以下方式，例：

```php
if ($condition) 
{
    ...
}
function addAccount($inputDataArray)
{
    ...
}
```



##### if .. else ... 规则

通常最好有一个else块以用于处理未处理到的或未知的其他情况，即使条件处理语句只有一个也必须使用大括号{}，例：

```php
if ( $condition1 )
{
    ...
}
else if ( $condition2 )
{
    ...
}
else
{
    ...
}
```


尽可能避免以下使用方式，例：

```php
if ( $condition1 )
    ...
else
    ...
```



##### switch规则

每个case块结束处必须加上break，而default总应该存在处理未知情况，例：

```php
switch( $condition )
{
    case $value1:
        ...
        break;
    case $value2:
        ...
        break;
    default:
        ...
        break;
}
```



##### 声明定位规则

声明代码块需要对齐，且初次使用变量时需要初始化，例：

```php
var $tableName      = '';
var $databaseObject = '';
```


不使用以下方式，例：

```php
var $tableName;
var $accuntName = '';
var $databaseObject = '';
```



#### 四、其它说明篇

所有类方法必须有返回值，除结果简单外返回true或者false之外，其它方法应返回不同的值。

以交作流程进一步处理。

html的form表单统一不设置submit按钮的名称属性（name）。

html的form表单各个元素名称与数据库字段保持一致。

每行一个语句。

不要采用默认方法测试非零值，必须显式测试，例：

```php
if ( false != $this->IsAccountOk() )
{
    ...
}
else
{
    ...
}
```


不要使用以下方式，例：

```php
if ( $this->IsAccountOk() )
{
    ...
}
else
{
    ...
}
```


不要使用三元逻辑符 ? :，但对变量的赋值除外，例：

$_GET['act']   = !empty( $_GET['act'] ) ? $_GET['act'] : 'v_login';
统一使用<?php ?>，禁止使用<? ?>格式。

对于get、post、session类型变量，必须使用$_GET、$_POST、$_SESSION方式定义和调用。

尽可能使用单引号''而不是双引号''。

使用完毕后的数组变量、对象变量、查询集合必须马上使用unset()、free_result()释放资源。

一个php文件只能包含一个类定义编码，以类名称作为文件名称。

php文件中绝不能出现html语句，html文件中尽可能避免出现php语句。

html文件必须通过w3c的html4检测认证（http://validator.w3.org/）。

如果发觉您在程序中的命名只有少量能和其对应事物相匹配的话，请重新设计系统。

在为类命名前首先要知道它是什么。如果通过类名提供的线索，您还是想不起这个类是什么的话，那么您的设计是做得不够好。

超过三个单词组成的混合名是容易造成系统各个实体间的混淆，请重新设计类。

通常每个方法只执行一项逻辑动作事务，所以对它们的命名应该清楚的说明它们是做什么的：用checkForErrors()代替errorCheck()，用dumpDataToFile()代替dataFile()。

这么做使功能和数据成为更可区分的物体。

#### 五、数据库应用篇

数据库的设计必须符合三个范式（极端要求常用高速时考虑单独设置记录表除外）。

数据库名称应该由概述项目内容的小写英文名词组成。

数据表名称应该由物件对象名称的小写英文名词组成（尽可能对应系统中的业务类名称）。

以下划线分隔单词，避免跨平台时可能出现的大小写错误。

数据表的字段应避免使用varchar、text等不定长的类型，时间信息的字段使用unix tiemstamp类型存储。

查询数据时禁止使用*通配符避免占用资源加速处理速度，尽量避免使用临时表。

查询数据连接多表时各资源应该使用全名称，即tableName.fieldName，而不是fieldName。

SQL语句应尽可能符合ansi92标准，避免使用特定数据库对SQL语言的扩充特性。

开发结束后，必须针对SQL查询语句的条件语句部分（where）添加索引，须匹配多个条件的应该使用聚合索引。

索引的组成应由左至右匹配条件语句的顺序。

严禁盲目添加索引，避免减慢数据插入的速度、增大占用空间及减慢查询速度。

每当数据库（表）发生结构性变化时须登记保存；日常须定时（不超过三个工作日）备份数据库结构及其数据。

1.给php变量赋值为字符串，尽量用单引号。单引号速度要快很多。

2.给php变量赋值时，值中带变量，就的用双引号了，双引号能自动解析变量，方便很多。

如$b=blue; $a="php$b"; echo $a;输出phpblue （单双引号各有千秋啊）

3. html内尽量用双引号，无论多长拿到php中首尾加单引号就行，甭怕错。
