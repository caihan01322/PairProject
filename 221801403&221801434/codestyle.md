
[toc]

#[HTML][1]
## 第一部分：HTML整体结构
**1.HTML基础设施**

文件应以`<!DOCTYPE.....>`首行顶格开始，这句话告诉浏览器这是一个什么文件，我们推荐使用`<!DOCTYPE html>。`

必须在head元素内部的meta标签内声明文档的字符编码charset, 如：`<metacharset="UTF-8">`，这句代码告诉浏览器应该此HTML文件使用的字符集是什么，如果不加此行代码，那么在浏览器中可能显示为乱码。

页面的title是极为重要的不可缺少的一项。

**2.HTML代码结构和视觉顺序基本保持一致**

按照从上之下，从左到右的视觉顺序书写HTML结构。

有时候为了便于搜索引擎抓取，我们也会将重要内容在HTML结构顺序上提前，以为搜索引擎抓取网页内容是自上而下的，所以将重要内容在HTML结构顺序上提前可便于抓取重要的内容。

不要使用table布局，现在基本上被淘汰了，而应该代之以div来布局，方便控制。

**3.结构、表现、行为三者分类，避免内联。**

使用link引入外部css文件到head中。注意：一般我们不适用@import来引入外部css文件。 
使用script将js文件引入，并置于body底部，这时js文件会最后加载，html会最先加载，用户体验会更好。(注意：并不是所有的js文件都要放置于body的底部，如当我们需要使用js文件动态修改meta元素内容时，需要将js文件引入到head标签中。

**4.保持良好的树形结构**

每一个块级元素都另起一行，每一行都是用tab缩进对齐。如果不是块级元素，比如几个行内元素，我们把他写在一行即可。注意：html、 head、 body 以及body下的第1级标签(即直接子元素)不缩进，其他的都正常缩进。如下图所示：

![此处输入图片的描述][https://ask.qcloudimg.com/http-save/yehe-4140184/hxo3q65pey.jpeg?imageView2/2/w/1620]

当然，我们也可以在大的模块之间用空行空开，在模块内不要使用多余的空行。

**5.其他需要注意的问题**

一个标签上引用的className不要过多，越少越好。
对于一个语义化的内部标签，应该尽量避免使用className。

## 第二部分：HTML代码格式
**1.说明文案的注释方法**

 - 开始注释：`<!-- 注释文案 -->`  
 - 结束注释：`<!-- /注释文案 -->`   
 - 允许只有开始注释。

![此处输入图片的描述][3]

**2.严格嵌套**

应当以最严格的xhtml strict标准来嵌套，不如内联元素不能包含块级元素等等。
正确闭合标签且必须闭合。

**3.严格的属性**

属性和值全部小写，每个属性都必须有一个值，每个值必须加双引号。
没有值的属性必须使用自己的名称做为值(checked、disabled、readonly、selected等等)。
可以省略style标签和script标签的type属性。

##第三部分：HTML内容语义

 - 加强资源型内容的可访问性和可用性。

比如在img标签内加入alt属性，在audio内加入文案和链接等等。

 - 加强不可见内容的可访问性

比如背景图片的文字应该同时卸载HTML中，并使用css使其不可见，有利于搜索引擎抓取你的内容，也可在css失效的情况下看到内容。

 - 适当使用实体

以实体代替与HTML语法相同的字符，避免浏览器解析错误。

 - 常用的HTML字符实体(建议使用实体)：
 
 ![此处输入图片的描述][4]
 - 常用的字符实体(不建议使用实体)：
 
 ![此处输入图片的描述][5]

#[PHP][6]
## 1. 前言

源码文件必须采用UTF-8编码，且不得有BOM头，某些历史遗留的GBK模块除外。
编码风格没有太多的好坏之分, 最重要的是风格保持一致，编码规范有助于规范我们编码的风格，使代码具有更好的可读性。
PHP在百度内部应用得越来越广泛，但是却缺乏相应的编码规范支持，编码风格百家齐放，不利于我们代码的维护和传承， 根据大家平时的开发情况，制定了此PHP编码规范。
每项规范前面的[强制]代表该规范需要强制执行,[建议]代表推荐执行但不强制。
注: 文中所有的变量名前面为了方便没有加”$”, 示意即可。
本文档风格约定部分可能跟你的喜好有冲突，请尽量用包容的心态来阅读。有任何问题或建议，欢迎跟我们讨论: php-styleguide@baidu.com
## 2. 排版
**2.1. [强制][PHP002] 程序块要采用缩进风格编写，缩进的空格数建议为4个，单模块内必须统一。**

**解释**
不同的缩进风格对代码的可读性影响很大，以tab为缩进单位在不同的tab step 下可读性也相差很多，所以将缩进定为一个soft tab即4个空格，这样在所有环境下缩进都会保持一致。

**2.2. [建议]关键字与其后的左括号之间有一个空格，而函数名与左括号之间不应有任何字符包括空格。**

**解释**
虽然很多情况下编辑器的highlight已经做了区分，但是从格式上区分关键字和函数适用于所有的情况。

示例

    关键字    if (a > b)
    函数名    funcA()

**2.3. [建议]开始的大括号位于一行的末尾，结束的括号位于最末一行后，且独占一行。首括号也可另起一行，但一个模块内必须统一。**

示例

    if (a > b) {
    
    }

**2.4. [强制] [PHP003] if/while等结构体，即使只有一行，也必须加上花括号，不得写成一行。**

**解释**
这样做可读性更好，并且方便修改。

示例

    if (a > b) {
        a = 1;
    }

**2.5. [建议]一行代码不得超过120个字节，建议控制在80字节内；一个函数不得超过500行，建议控制在100行以内。**

**解释**
代码更美观， 可读性更好

**2.6. [建议]else-if语句使用else if形式，不使用elseif形式。**

**2.7. [建议]函数名与其后的左括号之间不应有任何字符(包括空格) 函数调用的左括号与其第一个参数之间不应有任何字符(包括空格) 最后一个参数与右括号之间不应有任何字符(包括空格) 参数列表的逗号后面应有一个空格**

示例

    funcA(a, b, c) {
    
    }

**2.8. [建议]避免由于对错误的条件做判断带来if的嵌套。**

**解释**
减少if/else嵌套， 更利于代码逻辑的理解。

示例

    不推荐的方式:
    if (a === false) {
        // error handle
    } else {
        if (b === false) {
            // handle
        }
    }
    推荐的方式:
    if (a === false) {
        // error handle
    }
    
    if (b === false) {
        // handle
    }

**2.9. [建议]如果过长的话需要另起一行。if 语句的条件若较多较长，应折行；新行以逻辑运算符起始，与第一行 if 左括号后的第一个字符对齐；折行后，每行条件具有独立而明确的语义**

**解释**
这样做逻辑更一目了然。

示例

    if (a > b && c > d
        && e > f && h > j
        && z > x) {
    
    }

**2.10. [建议]多行的”=”可能的话尽量用空格对齐。**

示例

    a   = 1;
    ab  = 2;
    abc = 3;

**2.11. [强制] [PHP009] Switch语句中每个case的break必须和case间有缩进。**

示例

    case ‘A’:
        a  = 2;
        break;

**2.12. [强制] [PHP008] 初始化array如果采用多行结构时，数据项部分需要缩进，且最后一个数据项后面的逗号不可省略。**

**解释**
这样做在修改代码增加数据项的时候不容易出现语法错误。

示例

    $a = array(
        'a' => 'b',
        'b' => 'c',
        'c' => 'd',
    );

**2.13. [建议] 复杂的表达式, 使用括号表明优先级, 而不完全依赖运算符优先级。**

示例

    不推荐方式:
    if ($a && $b || $c + $b && $e) {
    }
    推荐方式：
    if (($a && $b) || (($c + $b) && $e)){
    }

**2.14. [建议] 同一个代码块的变量定义, 应该尽可能集中在块开始位置，提高可读性。**

**2.15. [建议] 除模板外，不允许使用?>标记结尾, 避免其后误加的字符干扰页面渲染。**

**2.16. [建议] 产品线内必须统一换行符的使用, 推荐“n”。**
## 3. 命名
**3.1. [强制] [PHP025] 全局变量以g_开头。**

**解释**
全局变量对代码影响很大，以g_开头便能在代码中一眼看出是全局变量。
示例

    g_count;

**3.2. [强制] [PHP004] 常量命名使用全部大写字符，单词之间以’_’连接。
示例**

    PAGE_NUM

**3.3. [建议]对于代码中的常量，建议用常量或define表示，不应直接写在代码中。**

示例

    define('PAGE_NUM', 3);

**3.4. [强制] [PHP010] 关键字true、false、null必须小写**

**3.5. [强制] [PHP026] 类method命名采用驼峰命名, 普通function采用过程函数风格命名。**

示例

    类method：
    public function getName() {
    }
    普通function：
    function show_me_the_money() {
    
    }

**3.6. [强制·]类成员变量和局部变量必须采用驼峰命名法，建议增加三字节的类型前缀：arr、str、int、bol、obj等**

示例

    $strName, $intAge

**3.7. [建议]文件(除了类)命名使用小写字母，单词之间以’_’连接。**

示例

    show_lemma.php

**3.8. [建议]配置文件的名称为配置文件名 + .conf.php, 不涉及类的都小写通过”_”连接。**

示例

    good_version.conf.php

**3.9. [建议]类名应以大写字母开头，每个单词的首字母大写。**

示例

    ActionController

**3.10. [建议]final放在访问控制符的前面、访问控制符放在static的前面**

示例

    final public static function getInstance(){
    }

## 4. 注释
**4.1. [建议]文件、函数、类以及成员变量都应包含注释，关键代码必须有注释。
类文件/普通文件的注释, 说明该文件的主要作用**。

示例

    "A simple class describing employees" 说明类文件的主要作用。
    "@package Employee" 说明namespace(如果有)
    "@author George Schlossnagle" 说明作者信息
    
    /**
     * A simple class describing employees
     *
     * @package Employee
     * @author George Schlossnagle
     */

**类的注释, 说明该类的主要作用。**

示例

    "An example of documenting a class" 说明类的主要作用。
    "The employees annual salary" 说明变量的作用。
    "@var number" 说明变量的类型。
    "The class constructor" 说明方法的作用。
    "@param" 说明参数类型。
    "@access" 说明访问权限。
    "@return" 说明返回值。
    
    /**
     * An example of documenting a class
     */
    class Employee
    {
        /**
         * @var string
         */
        private $name;
    
        /**
         * The employees annual salary
         * @var number
         */
        private $salary;
    
        /**
         * @var number
         */
        private $employee_id;
    
        /**
         * The class constructor
         * @param number
         */
        public function Employee($employee_id = false) {
            if ($employee_id) {
                $this->employee_id = $employee_id;
                $this->_fetchInfo();
            }
        }
    
        /**
         * Fetches info for employee
         *
         * @access private
         */
        private function fetchInfo() {
              $query = "SELECT name,
                           salary
                        FROM employees
                        WHERE employee_id = $this->employee_id";
              $result = mysql_query($query);
              list($this->name, $this->department_id) = mysql_fetch_row($result);
        }
    
        /**
         * Returns the monthly salary for the employee
         * @return number Monthly salary in dollars
         */
        public function monthlySalary() {
              return $this->salary/12;
        }
    }

**4.2. [强制] [PHP027] 不能使用#作为单行注释, 多行注释/ * **/不能出现在同一行。**

**4.3. [强制] [PHP028] 函数必须通过param和return标记指明其参数和返回值。**

**4.4. [建议] 注释需要遵守phpDocumentor等注释规范，同一团队内部必须保持一致。**

**4.5. [建议] 必要的地方使用非文档性注释，提高代码易读性。**
## 5. 编码原则
**5.1. [建议]对传入或返回的参数进行类型检查和显式转换。**

示例

    $intSalary = (int) $salary;

**5.2. [强制]对于函数返回值的判断，特别是true/false, 必须用===或!==。**

**5.3. [强制] [PHP029] 生成对象时，必须使用new Classname()，不能用new Classname。**

**5.4. [强制]所有文件路径都需要利用框架提供的宏写成绝对路径。**

**5.5. [建议]对于长时间运行的CLI程序，需要及时unset无用变量，尤其是PHP5.2上。**

**5.6. [强制]对于一些系统操作，使用php内置的函数例如rename、touch等即可。尽量避免使用exec调用shell命令。**

**5.7. [建议]除非特殊情况，否则不允许使用require和include,而使用对应的require_once/include_once。**

**5.8. [建议]配置项与PHP代码分离，不随CVS/SVN发布**

**5.9. [强制]预定义变量一律使用短格式，即：$_POST、$_GET、$_SERVER、$_ENV等，不再使用长格式：$_HTTP_POST_VARS、$_HTTP_GET_VARS。**

**5.10. [强制]类文件名必须符合所用框架自动加载规范，常见的是PSR-0。**

**5.11. [建议]除模板外，尽量不要在php代码中出现html标签。**

**5.12. [建议]能用foreach的就不要用for,能用for的就不要用while。**

**5.13. [强制]每个前端访问请求必须有且仅有一条notice日志。**

**5.14. [建议]数据库写操作必须有日志记录；记录条数应与操作一一对应。**

**5.15. [强制]文件更新操作，必须使用临时文件+mv的方式，切忌直接写在原文件。**

**5.16. [建议]字符串尽量用’ ‘而不是” “进行引用，一个是效率问题，一个是安全问题。**

**5.17. [强制] [PHP031] 所有的define语句，常量必须用’‘包括起来。**

示例

    define('PAGE_NUM', 3);

**5.18. [建议]require/include后面不使用括号。**

示例

    require_once "a.php";

**5.19. [强制] [PHP020] 函数允许使用默认参数,但是默认参数需要放到参数列表最后面。**

**5.20. [强制] [PHP032] 所有的全局变量应该写在函数的最开头，并且和后面的代码以空行隔开。**

示例

    function a() {
        global g_count;
        global g_time;
    
        a = 1;
    }

**5.21. [强制] [PHP033] 禁止使用and, or, 而是使用&&, ||**

**5.22. [建议]避免使用$i, $j这样无意义的变量名, 除非是用作循环计数变量。**

**5.23. [建议]避免使用php逻辑代码作为配置, 以降低改配置的危险性。**

**5.24. [建议]进行==判断时，建议把常量放在前面, 避免误写成赋值操作。**

示例

    不推荐形式：
    if ($a == 1){
    }
    推荐形式：
    if (1 == $a){
    }

**5.25. [建议]使用变量前赋初值，提高可读性，也可避免误用别处定义的同名变量。**

**5.26. [建议]错误码使用统一文件集中配置，并且使用常量，而不应裸写数字**

**5.27. [建议]对于无需子类化的实体类以及不应重载的方法使用final关键字限定。**

**5.28. [强制]对于不应实例化的父类使用abstract关键字限定。**

**5.29. [建议]避免重载父类的static成员，这在5.2.x存在问题。**

**5.30. [建议]对于仅用于某个函数或类的全局变量，使用static的局部变量或者类成员变量代替。**

**5.31. [建议]在头文件中用$GLOBALS定义全局变量，避免局部包含导致的作用域问题。**
## 6. 代码性能
**6.1. [强制] [PHP034] 把重复调用放在循环体外。**
示例

    不推荐形式：
    for($i = 0; $i < count($arr); $i++)
    推荐形式：
    $arrCount = count($arr);
    for($i = 0; $i < $arrCount; $i++)


  [1]: https://cloud.tencent.com/developer/information/html%E4%BB%A3%E7%A0%81%E7%BC%96%E7%A0%81%E8%A7%84%E8%8C%83
  [2]: https://ask.qcloudimg.com/http-save/yehe-4140184/hxo3q65pey.jpeg?imageView2/2/w/1620
  [3]: https://ask.qcloudimg.com/http-save/yehe-4140184/lu843mr5bb.jpeg?imageView2/2/w/1620
  [4]: https://ask.qcloudimg.com/http-save/yehe-4140184/kyucjmn7w1.jpeg?imageView2/2/w/1620
  [5]: https://ask.qcloudimg.com/http-save/yehe-4140184/wl4ujkcl4w.jpeg?imageView2/2/w/1620
  [6]: https://blog.csdn.net/gb4215287/article/details/69940060