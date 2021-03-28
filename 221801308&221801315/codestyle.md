# 前端
## 命名
### 文件资源命名
+ 文件名不得含有空格
+ 文件名建议只使用小写字母，不使用大写字母。( 为了醒目，某些说明文件的文件名，可以使用大写字母，比如README、LICENSE。 )
+ 文件名包含多个单词时，单词之间建议使用半角的连词线 ( - ) 分隔。

### 变量命名
+ 命名方式 : 小驼峰式命名方法
+ 命名规范 : 类型+对象描述的方式，如果没有明确的类型，就可以使前缀为名词。
+ eg: `var tableTitle = "LoginTable"` 

### 函数命名
+ 命名方式 : 小驼峰方式 ( 构造函数使用大驼峰命名法 )
+ 命名规则 : 前缀为动词
+ eg: `function canRead()`

### 常量
+ 命名方法 : 全部大写
+ 命名规范 : 使用大写字母和下划线来组合命名，下划线用以分割单词。
+ eg: `var MAX_COUNT = 10;`

### 类的成员
+ 公共属性和方法 : 同变量命名方式
+ 私有属性和方法 : 前缀为下划线(_)后面跟公共属性和方法一样的命名方式
+ eg: ` var _name = name;`

### 注释规范
#### 单行注释
+ 单独一行：//(双斜线)与注释文字之间保留一个空格
+ 在代码后面添加注释：//(双斜线)与代码之间保留一个空格，并且//(双斜线)与注释文字之间保留一个空格。
+ 注释代码：//(双斜线)与代码之间保留一个空格。
#### 多行注释
+ 若开始(/*和结束(*/)都在一行，推荐采用单行注释
+ 若至少三行注释时，第一行为/*，最后行为*/，其他行以*开始，并且注释文字与*保留一个空格
#### 函数 ( 方法 ) 注释
```
/** 
* 函数说明 
* @关键字 
*/
```
## HTML规范
### 文档规范
+ 使用 HTML5 的文档声明类型 : <!DOCTYPE html>
### 脚本加载
+ 兼容所有浏览器
```
<html>
  <head>
    <link rel="stylesheet" href="main.css">
  </head>
  <body>
    <!-- body goes here -->

    <script src="main.js" async></script>
  </body>
</html>
```
+ 兼容现代浏览器
```
<html>
  <head>
    <link rel="stylesheet" href="main.css">
    <script src="main.js" async></script>
  </head>
  <body>
    <!-- body goes here -->
  </body>
</html>
```
### 语义化
+ 正确合理使用nav, header, article, section 等语义标签，不要全篇div
### alt标签不为空
### 结构、表现、行为三者分离
+ 尽量在文档和模板中只包含结构性的 HTML；
+ 将所有表现代码，移入样式表中；
+ 将所有动作行为，移入脚本之中。
### HTML只关注内容
+ HTML只显示展示内容信息
+ 不要引入一些特定的 HTML 结构来解决一些视觉设计问题，多考虑使用伪元素:before、:after
+ 不要将 img 元素当做专门用来做视觉设计的元素
+ 样式上的问题应该使用css解决
## CSS规范
### id和class的命名
+ ID和class的名称总是使用可以反应元素目的和用途的名称，或其他通用的名称，代替表象和晦涩难懂的名称
### 合理的使用ID
+ ID一般不用于样式，使用class解决样式问题
### css选择器中避免使用标签名
### 使用子选择器
```
.content > .title {
  font-size: 2rem;
}
```
### 尽量使用缩写属性
```
border-top: 0;
font: 100%/1.6 palatino, georgia, serif;
padding: 0 1em 2em;
```
### 0后面不带单位
### 属性格式
+ 为了保证一致性和可扩展性，每个声明应该用分号结束，每个声明换行。
+ 属性名的冒号后使用一个空格。出于一致性的原因，属性和值（但属性和冒号之间没有空格）的之间始终使用一个空格。
+ 每个选择器和属性声明总是使用新的一行。
+ 属性选择器或属性值用双引号（””），而不是单引号（”）括起来。
+ URI值（url()）不要使用引号。
### 属性顺序
+ 布局相关(display, position, float, overflow, clear)，因为元素的布局会对对相邻元素产生影响，自身甚至会脱离原来的文档流，所以比较重要。
+ 盒模型相关(width, height, margin, padding)
+ 外观 (color, background, border, box-shadow)
+ 文字排版 (font-size, font-family, text-align, text-transform)
+ 其他 (cursor, z-index)
## js规范
### 变量声明
+ 总是使用 var 来声明变量。如不指定 var，变量将被隐式地声明为全局变量
### js声明提前
+ 只是提前声明，赋值还是在原处

### 使用严格等
+ 总是使用 === 精确的比较操作符，避免在判断的过程中，由 JavaScript 的强制类型转换所造成的困扰。
#### 等同== 和严格等===的区别
+ ==， 两边值类型不同的时候，要先进行类型转换，再比较。
+ ===，不做类型转换，类型不同的一定不等。

### 真假判断
#### js中以下内容为假：
+ false
+ null
+ undefined
+ 0
+ '' (空字符串)
+ NaN
### 不使用eval()函数
### this关键字使用场景
+ 在构造函数中
+ 在对象的方法中（包括由此创建出的闭包内）
### 三元条件判断（if 的快捷方法）
+ 比较简单的情况下，用三元操作符分配或返回语句。
### 使用ES6编码规范
+ 定义变量使用let ,定义常量使用const
+ 静态字符串一律使用单引号或反引号，动态字符串使用反引号
+ 解构赋值 eg: `const arr = [1, 2, 3, 4];const [first, second] = arr;`
+ 使用扩展运算符（...）拷贝数组。

## Vue规范
### Vue 项目规范
#### 结构化规范（webpack）
``` 
  ├── index.html                      入口页面
   ├── favicon.ico                     页面图标
   ├── .babelrc                        babel规则
   ├── .editorconfig                   编辑器配置
   ├── .eslintignore                   eslint忽略规律
   ├── .eslintrc.js                    eslint规则
   ├── .gitignore                      git忽略规则
   ├── build                           构建脚本目录
   │   ├── build-server.js                 运行本地构建服务器，可以访问构后的页面
   │   ├── build.js                        生产环境构建脚本
   │   ├── dev-client.js                   开发服务器热重载脚本，主要用来实现开发阶段的页面自动刷新
   │   ├── dev-server.js                   运行本地开发服务器
   │   ├── utils.js                        构建相关工具方法
   │   ├── webpack.base.conf.js            wabpack基础配置
   │   ├── webpack.dev.conf.js             wabpack开发环境配置
   │   └── webpack.prod.conf.js            wabpack生产环境配置
   │   └── webpack.cdn.conf.js             wabpack cdn配置
   │   └── webpack.dll.conf.js             wabpack dll配置
   ├── config                          项目配置
   │   ├── dev.env.js                      开发环境变量
   │   ├── index.js                        项目配置文件
   │   ├── prod.env.js                     生产环境变量
   │   └── test.env.js                     测试环境变量
   ├── mock                            mock数据目录
   │   └── hello.js
   ├── package.json                    npm包配置文件，里面定义了项目的npm脚本，依赖包等信息
   ├── readmd.md                       项目描述文件
   ├── src                                项目源码目录
   │   ├── main.js                            入口js文件
   │   ├── App.vue                            根组件
   │   ├── components                         公共组件目录
   │   │   └── ComponentItem.vue
   │   ├── assets                         静态资源目录，这里的资源会被wabpack构建
   │   │   ├── css                            公共样式文件目录
   │   │   ├── js                             公共js文件目录（如帮助方法）
   │   │   └── img                            图片存放目录
   |   |── lib                            外部引用的插件存放及修改文件
   |   |—— datas                          模拟数据，临时存放
   │   ├── routes                         前端路由
   │   │   └── index.js
   │   ├── apis                           接口，统一管理
   │   │   └── index.js
   │   ├── store                          vuex, 统一管理
   │   │   └── index.js
   │   └── views                          视图模块名
   │       ├── view-module                视图模块
   |            └── index.vue             视图模块的主页面
   │       ├── hello.vue
   │       └── notfound.vue
   ├── static                             纯静态资源，不会被wabpack构建。
   └── test                               测试文件目录（unit&e2e）
       └── unit                               单元测试
           ├── index.js                           入口脚本
           ├── karma.conf.js                      karma配置文件
           └── specs                           单测case目录
               └── Hello.spec.js
```
#### 命名规范
+ views下的文件
  + 尽量是名词,且使用驼峰命名法
  + 开头的单词就是所属模块名字（workbenchIndex、workbenchList、workbenchEdit）
+ 组件文件
  + 命名遵循`PascalCase`约定。
  + 组件名应该始终是多个单词的，根组件 App 除外
  + 使用遵循 `kebab-case` 约定
  + 导入及注册组件时，遵循 `PascalCase` 约定
  + 必须符合自定义元素规范: 切勿使用保留字。
+ method 方法命名命名规范
  + 驼峰式命名，统一使用动词或者动词+名词形式
  + 请求数据方法，以 data 结尾
  + 尽量使用常用单词开头（set、get、go、can、has、is）
+ props 命名规范
  + 声明prop的时候，其命名应该始终使用`camelCase`
  + 在模板中应该始终使用`kebab-case`
### Vue文件结构
+ 顺序：template -> script -> style。一个组件尽量不要超过200行，页面包含独立部分时尽量分离成子组件。
### Vue Router Path规范
+ router path采用kebab-case格式。
### 元素特性顺序
``` 
  - class
  - id,ref
  - name
  - data-*
  - src, for, type, href,value,max-length,max,min,pattern
  - title, alt，placeholder
  - aria-*, role
  - required,readonly,disabled
  - is
  - v-for
  - key
  - v-if
  - v-else-if
  - v-else
  - v-show
  - v-cloak
  - v-pre
  - v-once
  - v-model
  - v-bind,:
  - v-on,@
  - v-html
  - v-text
```
### 组件选项顺序
```
  - components
  - props
  - data
  - computed
  - created
  - mounted
  - metods
  - filter
  - watch
```

# 后端python
[来源：Python 编码规范(Google)](https://www.runoob.com/w3cnote/google-python-styleguide.html)

## 分号
不要在行尾加分号, 也不要用分号将两条命令放在同一行。

## 行长度
每行不超过80个字符

以下情况除外：

1. 长的导入模块语句
2. 注释里的URL

不要使用反斜杠连接行。

Python会将**圆括号,中括号和花括号中的行隐式的连接起来**, 你可以利用这个特点。如果需要，你可以在表达式外围增加一对额外的圆括号。
> ```python
> foo_bar(self, width, height, heightcolor='black', design=None,
>         x='foo', emphasis=None, highlight=0)
>
> if (width == 0 and height == 0 and 
>     color == 'red' and emphasis == 'strong'):
> ```

如果一个文本字符串在一行放不下，可以使用圆括号来实现隐式行连接：
> ```python
> x = ('这是一个非常长非常长非常长非常长 '
>     '非常长非常长非常长非常长非常长非常长的字符串')
> ```

在注释中，如果必要，将长的URL放在一行上。
> ```python
> Yes:
>     # See details at
>     # http://www.example.com/us/developer/documentation/api/content/v2.0/csv_file_name_extension_full_specification.html
> ```

> ```python
> No:
>     # See details at
>     # http://www.example.com/us/developer/documentation/api/content/\
>     # v2.0/csv_file_name_extension_full_specification.html
> ```

## 括号
宁缺毋滥的使用括号

除非是用于实现行连接，否则不要在返回语句或条件语句中使用括号。不过在元组两边使用括号是可以的。

> ```python
> Yes:
>     if x and y:
>         return foo
> ```

> ```python
> No:
>     if (x) and (y):
>        return (foo)
> ```

## 缩进
用4个空格来缩进代码

绝对不要用tab，也不要tab和空格混用。对于行连接的情况, 你应该要么垂直对齐换行的元素，或者使用4空格的悬挂式缩进(这时第一行不应该有参数)：

> ```python
> Yes:
>     # 与起始变量对齐
>     foo = long_function_name(var_one, var_two,
>                              var_three, var_four)
>
>     # 字典中与起始值对齐
>     foo = {
>         long_dictionary_key: value1 +
>                              value2,
>         ...
>     }
>
>     # 4 个空格缩进，第一行不需要
>     foo = long_function_name(
>         var_one, var_two, var_three,
>         var_four)
>
>     # 字典中 4 个空格缩进
>     foo = {
>         long_dictionary_key:
>             long_dictionary_value,
>         ...
>     }
> ```

> ```python
> No:
>     # 第一行有空格是禁止的
>     foo = long_function_name(var_one, var_two,
>         var_three, var_four)
>
>     # 2 个空格是禁止的
>     foo = long_function_name(
>       var_one, var_two, var_three,
>       var_four)
>
>     # 字典中没有处理缩进
>     foo = {
>         long_dictionary_key:
>             long_dictionary_value,
>             key2:
>             ...
>     }
> ```

## 空格
按照标准的排版规范来使用标点两边的空格

括号内不要有空格
> ```python
> spam(ham[1], {eggs: 2}, [])
> ```

不要在逗号、分号、冒号前面加空格，但应该在它们后面加（除了在行尾）
> ```python
> if x == 4:
>     print x, y
> ```

参数列表，索引或切片的左括号前不应加空格
> ```python
> dict['key'] = list[index]
> ```

在二元操作符两边都加上一个空格，比如赋值`=`，比较`==, <, >, !=, <>, <=, >=, in, not in, is, is not`，布尔`and, or, not`
> ```python
> x == 1
> ```

当`=`用于指示**关键字参数**或**默认参数值**时，不要在其两侧使用空格
> ```python
> def complex(real, imag=0.0):
>     return magic(r=real, i=imag)
> ```

不要用空格来垂直对齐多行间的标记，因为这会成为维护的负担（适用于：`,`、`#`、`=`等）
> ```python
> No:
>     foo       = 1000  # 注释
>     long_name = 2     # 注释不需要对齐
>
>     dictionary = {
>         "foo"      : 1,
>         "long_name": 2,
>         }
> ```

## 注释

### 函数和方法
一个函数必须要有文档字符串, 除非它满足以下条件:
1. 外部不可见
2. 非常短小
3. 简单明了

每节应该以一个标题行开始。标题行以冒号结尾。除标题行外，节的其他内容应被缩进2个空格。

Args：
> 列出每个参数的名字，并在名字后使用一个冒号和一个空格，分隔对该参数的描述。如果描述太长超过了单行80字符，使用2或者4个空格的悬挂缩进（与文件其他部分保持一致）。描述应该包括所需的类型和含义。如果一个函数接受*foo(可变长度参数列表)或者**bar (任意关键字参数), 应该详细列出*foo和**bar.
>

Returns：（或者 Yields：用于生成器）
> 描述返回值的类型和语义。如果函数返回None，这一部分可以省略。
>

Raises：
> 列出与接口有关的所有异常。
>

示例：
> ```python
> def fetch_bigtable_rows(big_table, keys, other_silly_variable=None):
>     """Fetches rows from a Bigtable.
>
>     Retrieves rows pertaining to the given keys from the Table instance
>     represented by big_table.  Silly things may happen if
>     other_silly_variable is not None.
>
>     Args:
>         big_table: An open Bigtable Table instance.
>         keys: A sequence of strings representing the key of each table row
>             to fetch.
>         other_silly_variable: Another optional variable, that has a much
>             longer name than the other args, and which does nothing.
>
>     Returns:
>         A dict mapping keys to the corresponding table row data
>         fetched. Each row is represented as a tuple of strings. For
>         example:
> 
>         {'Serak': ('Rigel VII', 'Preparer'),
>          'Zim': ('Irk', 'Invader'),
>          'Lrrr': ('Omicron Persei 8', 'Emperor')}
>
>         If a key from the keys argument is missing from the dictionary,
>         then that row was not found in the table.
>
>     Raises:
>         IOError: An error occurred accessing the bigtable.Table object.
>     """
>     pass
> ```

### 类
类应该在其定义下有一个用于描述该类的文档字符串。如果你的类有公共属性（Attributes），那么文档中应该有一个属性（Attributes）段。并且应该遵守和函数参数相同的格式。

> ```python
> class SampleClass(object):
>     """Summary of class here.
>
>     Longer class information....
>     Longer class information....
>
>     Attributes:
>         likes_spam: A boolean indicating if we like SPAM or not.
>         eggs: An integer count of the eggs we have laid.
>     """
>
>     def __init__(self, likes_spam=False):
>         """Inits SampleClass with blah."""
>         self.likes_spam = likes_spam
>         self.eggs = 0
> 
>     def public_method(self):
>         """Performs operation blah."""
> ```

### 块注释和行注释
最需要写注释的是代码中那些技巧性的部分。如果你在下次代码审查的时候必须解释一下，那么你应该现在就给它写注释。
- 对于复杂的操作，应该在其操作开始前写上若干行注释。
- 对于不是一目了然的代码，应在其行尾添加注释。
- 行尾注释应该至少离开代码2个空格。
> ```python
> # We use a weighted dictionary search to find out where i is in
> # the array.  We extrapolate position based on the largest num
> # in the array and the array size and then do binary search to
> # get the exact number.
>
> if i & (i-1) == 0:        # true iff i is a power of 2
> ```

## 字符串
避免在循环中用`+`和`+=`操作符来累加字符串。由于字符串是不可变的，这样做会创建不必要的临时对象，并且导致二次方而不是线性的运行时间。作为替代方案，你可以使用`append()`将每个子串加入列表，然后在循环结束后用`''.join()`连接列表。
> ```python
> items = ['<table>']
> for last_name, first_name in employee_list:
>     items.append('<tr><td>%s, %s</td></tr>' % (last_name, first_name))
> items.append('</table>')
> employee_table = ''.join(items)
> ```

在同一个文件中，保持使用字符串引号的一致性。使用单引号`'`或者双引号`"`之一用以引用字符串，并**在同一文件中沿用**。在字符串内可以使用另外一种引号。
> ```python
> Python('Why are you hiding your eyes?')
> Narrator('"Good!" thought a happy Python reviewer.')

## 导入格式
每个导入应该独占一行。

导入总应该放在文件顶部，位于模块注释和文档字符串之后，模块全局变量和常量之前。导入应该按照从最通用到最不通用的顺序分组：
1. 标准库导入
2. 第三方库导入
3. 应用程序指定导入

每种分组中，应该根据每个模块的完整包路径按字典序排序，忽略大小写。
> ```python
> import foo
> from foo import bar
> from foo.bar import baz
> from foo.bar import Quux
> from Foob import ar
> ```

## 命名
- `module_name`
- `package_name`
- `ClassName`
- `method_name`
- `ExceptionName`
- `function_name`
- `GLOBAL_VAR_NAME`
- `instance_var_name`
- `function_parameter_name`
- `local_var_name`

Python之父Guido推荐的规范

Type|Public|Internal
|----|-----|-----|
Modules|lower_with_under|_lower_with_under
Packages|lower_with_under|
Classes|CapWords|_CapWords
Exceptions|CapWords|	
Functions|lower_with_under()|_lower_with_under()
Global/Class Constants|CAPS_WITH_UNDER|_CAPS_WITH_UNDER
Global/Class Variables|lower_with_under|_lower_with_under
Instance Variables|lower_with_under|_lower_with_under (protected) or __lower_with_under (private)
Method Names|lower_with_under()|_lower_with_under() (protected) or __lower_with_under() (private)
Function/Method Parameters|lower_with_under|
Local Variables|lower_with_under|	

## Main
即使是一个打算被用作脚本的文件，也应该是可导入的。并且简单的导入不应该导致这个脚本的主功能（main functionality）被执行，这是一种副作用。主功能应该放在一个main()函数中。

在Python中，pydoc以及单元测试要求模块必须是可导入的。你的代码应该在执行主程序前总是检查`if __name__ == '__main__'`，这样当模块被导入时主程序就不会被执行。
> ```python
> def main():
>     ...
>
> if __name__ == '__main__':
>     main()