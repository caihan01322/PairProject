# 前端


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