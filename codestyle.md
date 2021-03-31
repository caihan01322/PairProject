## 引用：html+css规范来自腾讯，Vue规范文档来自官方,Node.js来自官方

## 命名

1.项目名称全部采用小写，以下划线分割。如my_project 2.目录命名全部采用小写，以下划线分割。有复数结构时，要采用复数命名法。

## html

缩进使用soft tab（4个空格）； 嵌套的节点应该缩进； 在属性上，使用双引号，不要使用单引号； 属性名全小写，用中划线做分隔符； 不要在自动闭合标签结尾处使用斜线（HTML5 规范 指出他们是可选的）； 不要忽略可选的关闭标签。 自动闭合标签：

```
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

在页面开头使用这个简单地doctype来启用标准模式，使其在每个浏览器中尽可能一致的展现；按照惯例使用大写， 应在html标签加上lang属性。这会给语音工具和翻译工具帮助，告诉它们应当怎样去发音和翻译。中文:zh-cn,英文:en-us； 字符编码：通过声明一个明确的字符编码，让浏览器轻松、快速的确定适合网页内容的渲染方式，通常指定为'UTF-8'。 IE兼容模式：用 标签可以指定页面应该用什么版本的IE来渲染；

以上代码告诉IE浏览器，IE8/9及以后的版本都会以最高版本IE来渲染页面。

根据HTML5规范，通常在引入CSS和JS时不需要指明type，因为text/css和text/javascript分别时它们的默认值。

<script src="code_guide.js"></script>

属性顺序应该按照特定的顺序出现以保证易读性：

```
class

id

 name

 data-*

 src, for, type, href, value , max-length, max, min, pattern

 placeholder, title, alt

 aria-*, role

 required, readonly, disabled
```

HTML5并不需要声明取值的属性，元素上存在布尔属性表示真值，缺少属性表示false值。布尔属性不允许使用值“true”和“false”。要表示错误值，必须完全省略该属性。 在JS文件中生成标签让内容变得更难查找，更难编辑，性能更差。应该尽量避免这种情况的出现。 在编写html代码时，需要尽量避免多余的父节点，很多时候需要通过迭代和重构来使HTML变得更少。

尽量遵循HTML标准和语义，但是不应该以浪费实用性作为代价；任何时候都要用尽量小的复杂度和尽量少的标签来解决问题。

CSS，SCSS 以下几种情况不需要空格：

属性名后 多个规则的分隔符','前 !important '!'后 属性值中'('后和')'前 行末不要有多余的空格 以下几种情况需要空格：

#### 空行

属性值前 选择器'>', '+', '~'前后 '{'前 !important '!'前 @else 前后 属性值中的','后 注释'/*'后和'*/'前 需要换行的情况：

'{'后和'}'前 每个属性独占一行 多个规则的分隔符','后 /* not good */ .element, .dialog { ... } /* good */ .element, .dialog { ... }

#### 注释

注释统一用'/* */'（scss中也不要用'//'），具体参照右边的写法；

缩进与下一行代码保持一致；

可位于一个代码行的末尾，与代码间隔一个空格。

/* Modal header */ .modal-header { ... } /*

- Modal header */

#### 引号

最外层统一使用双引号； url的内容要用引号； 属性选择器中的属性值需要引号。 命名

类名使用小写字母，以中划线分隔 id采用驼峰式命名 scss中的变量、函数、混合、placeholder采用驼峰式命名 属性声明顺序 相关的属性声明按右边的顺序做分组处理，组之间需要有一个空行。

```
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

属性简写 属性简写需要你非常清楚属性值的正确顺序，而且在大多数情况下并不需要设置属性简写中包含的所有值，所以建议尽量分开声明会更加清晰；

margin 和 padding 相反，需要使用简写；

```
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

媒体查询 尽量将媒体查询的规则靠近与他们相关的规则，不要将他们一起放到一个独立的样式文件中，或者丢在文档的最底部，这样做只会让大家以后更容易忘记他们。

SCSS相关 提交的代码中不要有 @debug；

声明顺序：

@extend 不包含 @content 的 @include 包含 @content 的 @include 自身属性 嵌套规则 @import 引入的文件不需要开头的'_'和结尾的'.scss'；

嵌套最多不能超过5层；

@extend 中使用placeholder选择器；

去掉不必要的父级引用符号'&'。

#### 杂项

不允许有空的规则； 元素选择器用小写字母； 去掉小数点前面的0； 去掉数字中不必要的小数点和末尾的0； 属性值'0'后面不要加单位； 同个属性不同前缀的写法需要在垂直方向保持对齐，具体参照下边的写法； 无前缀的标准属性应该写在有前缀的属性后面； 不要在同个规则里出现重复的属性，如果重复的属性是连续的则没关系； 不要在一个文件里出现两个相同的规则； 用 border: 0; 代替 border: none;； 选择器不要超过4层（在scss中如果超过4层应该考虑用嵌套的方式来写）； 发布的代码中不要有 @import； 尽量少用'

```
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

分号 以下几种情况后需加分号：

变量声明 表达式 return throw break continue do-while

#### 空格

以下几种情况不需要空格：

对象的属性名后 前缀一元运算符后

以下几种情况需要空格：

二元运算符前后 三元运算符'?:'前后 代码块'{'前 下列关键字前：else, while, catch, finally 下列关键字后：if, else, for, while, do, switch, case, try, catch, finally, with, return, typeof 单行注释'//'后（若单行注释和代码同行，则'//'前也需要），多行注释'*'后 对象的属性值前 for循环，分号后留有一个空格，前置条件如果有多个，逗号后留一个空格 无论是函数声明还是函数表达式，'{'前一定要有空格 函数的参数之间 后缀一元运算符前 函数调用括号前 无论是函数声明还是函数表达式，'('前不要空格 数组的'['后和']'前 对象的'{'后和'}'前 运算符'('后和')'前

以下几种情况需要空行：

变量声明后（当变量声明在代码块的最后一行时，则无需空行） 注释前（当注释在代码块的第一行时，则无需空行） 代码块后（在函数调用、数组、对象中则无需空行） 文件最后保留一个空行

#### 换行

换行的地方，行末必须有','或者运算符；

以下几种情况不需要换行：

下列关键字后：else, catch, finally 代码块'{'前

以下几种情况需要换行：

代码块'{'后和'}'前 变量赋值后 单行注释 双斜线后，必须跟一个空格；

缩进与下一行代码保持一致；

可位于一个代码行的末尾，与代码间隔一个空格。

#### 多行注释

最少三行, '*'后跟一个空格，具体参照右边的写法；

建议在以下情况下使用：

难于理解的代码段 可能存在错误的代码段 浏览器特殊的HACK代码 业务逻辑强相关的代码 引号 最外层统一使用单引号。

// not good var x = "test";

// good var y = 'foo' 变量命名

标准变量采用驼峰式命名（除了对象的属性外，主要是考虑到cgi返回的数据） 'ID'在变量名中全大写 'URL'在变量名中全大写 'Android'在变量名中大写第一个字母 'iOS'在变量名中小写第一个，大写后两个字母 常量全大写，用下划线连接 构造函数，大写第一个字母 jquery对象必须以'$'开头命名

变量声明 一个函数作用域中所有的变量声明尽量提到函数首部，用一个var声明，不允许出现两个连续的var声明。

```
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

变量声明 一个函数作用域中所有的变量声明尽量提到函数首部，用一个var声明，不允许出现两个连续的var声明。

```
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

函数 无论是函数声明还是函数表达式，'('前不要空格，但'{'前一定要有空格；

函数调用括号前不需要空格；

立即执行函数外必须包一层括号；

不要给inline function命名；

参数之间用', '分隔，注意逗号后有一个空格。

```
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

数组、对象 对象属性名不需要加引号；

对象以缩进的形式书写，不要写在一行；

数组、对象最后不要有逗号。

括号 下列关键字后必须有大括号（即使代码块的内容只有一行）：if, else, for, while, do, switch, try, catch, finally, with。

null 适用场景：

初始化一个将来可能被赋值为对象的变量 与已经初始化的变量做比较 作为一个参数为对象的函数的调用传参 作为一个返回对象的函数的返回值 不适用场景：

不要用null来判断函数调用时有无传参 不要与未初始化的变量做比较 undefined 永远不要直接使用undefined进行变量判断；

使用typeof和字符串'undefined'对变量进行判断。

```
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

用'===', '!=='代替'==', '!='； for-in里一定要有hasOwnProperty的判断； 不要在内置对象的原型上添加方法，如Array, Date； 不要在内层作用域的代码里声明了变量，之后却访问到了外层作用域的同名变量； 变量不要先使用后声明； 不要在一句代码中单单使用构造函数，记得将其赋值给某个变量； 不要在同个作用域下声明同名变量； 不要在一些不需要的地方加括号，例：delete(a.b)； 不要使用未声明的变量（全局变量需要加到.jshintrc文件的globals属性里面）； 不要声明了变量却不使用； 不要在应该做比较的地方做赋值； debugger不要出现在提交的代码里； 数组中不要存在空元素； 不要在循环内部声明函数； 不要像这样使用构造函数，例：new function () { ... }, new Object； 杂项 不要混用tab和space；

不要在一处使用多个tab或space；

换行符统一用'LF'；

对上下文this的引用只能使用'_this', 'that', 'self'其中一个来命名；

行尾不要有空白字符；

switch的falling through和no default的情况一定要有注释特别说明；

不允许有空的代码块。

## [优先级 A 的规则：必要的 (规避错误)](#优先级-A-的规则：必要的-规避错误)

### [组件名为多个单词必要](#组件名为多个单词必要)

**组件名应该始终是多个单词的，根组件 `App` 以及 `<transition>`、`<component>` 之类的 Vue 内置组件除外。**

这样做可以避免跟现有的以及未来的 HTML 元素[相冲突](http://w3c.github.io/webcomponents/spec/custom/#valid-custom-element-name)，因为所有的 HTML 元素名称都是单个单词的。

#### 反例

```
Vue.component('todo', {
  // ...
})
export default {
  name: 'Todo',
  // ...
}
```

#### 好例子

```
Vue.component('todo-item', {
  // ...
})
export default {
  name: 'TodoItem',
  // ...
}
```

### [组件数据必要](#组件数据必要)

**组件的 `data` 必须是一个函数。**

当在组件中使用 `data` property 的时候 (除了 `new Vue` 外的任何地方)，它的值必须是返回一个对象的函数。

<details><summary><h4>详解</h4> </summary> <p>当 <code>data</code> 的值是一个对象时，它会在这个组件的所有实例之间共享。想象一下，假如一个 <code>TodoList</code> 组件的数据是这样的：</p><pre><code class="hljs js">data: {
  <span class="hljs-attr">listTitle</span>: <span class="hljs-string">''</span>,
  <span class="hljs-attr">todos</span>: []
}</code></pre><p>我们可能希望重用这个组件，允许用户维护多个列表 (比如分为购物、心愿单、日常事务等)。这时就会产生问题。因为每个组件的实例都引用了相同的数据对象，更改其中一个列表的标题就会改变其它每一个列表的标题。增删改一个待办事项的时候也是如此。</p><p>取而代之的是，我们希望每个组件实例都管理其自己的数据。为了做到这一点，每个实例必须生成一个独立的数据对象。在 JavaScript 中，在一个函数中返回这个对象就可以了：</p><pre><code class="hljs js">data: <span class="hljs-function"><span class="hljs-keyword">function</span> (<span class="hljs-params"></span>) </span>{
  <span class="hljs-keyword">return</span> {
    <span class="hljs-attr">listTitle</span>: <span class="hljs-string">''</span>,
    <span class="hljs-attr">todos</span>: []
  }
}</code></pre></details>

#### 反例

```
Vue.component('some-comp', {
  data: {
    foo: 'bar'
  }
})
export default {
  data: {
    foo: 'bar'
  }
}
```

#### 好例子

```
Vue.component('some-comp', {
  data: function () {
    return {
      foo: 'bar'
    }
  }
})
// In a .vue file
export default {
  data () {
    return {
      foo: 'bar'
    }
  }
}
// 在一个 Vue 的根实例上直接使用对象是可以的，
// 因为只存在一个这样的实例。
new Vue({
  data: {
    foo: 'bar'
  }
})
```

### [Prop 定义必要](#Prop-定义必要)

**Prop 定义应该尽量详细。**

在你提交的代码中，prop 的定义应该尽量详细，至少需要指定其类型。

<details><summary><h4>详解</h4> </summary> <p>细致的 <a href="../guide/components-props.html#Prop-验证">prop 定义</a>有两个好处：</p><ul><li>它们写明了组件的 API，所以很容易看懂组件的用法；</li><li>在开发环境下，如果向一个组件提供格式不正确的 prop，Vue 将会告警，以帮助你捕获潜在的错误来源。</li></ul></details>

#### 反例

```
// 这样做只有开发原型系统时可以接受
props: ['status']
```

#### 好例子

```
props: {
  status: String
}
// 更好的做法！
props: {
  status: {
    type: String,
    required: true,
    validator: function (value) {
      return [
        'syncing',
        'synced',
        'version-conflict',
        'error'
      ].indexOf(value) !== -1
    }
  }
}
```

### [为 `v-for` 设置键值必要](#为-v-for-设置键值必要)

**总是用 `key` 配合 `v-for`。**

在组件上*总是*必须用 `key` 配合 `v-for`，以便维护内部组件及其子树的状态。甚至在元素上维护可预测的行为，比如动画中的[对象固化 (object constancy)](https://bost.ocks.org/mike/constancy/)，也是一种好的做法。

<details><summary><h4>详解</h4> </summary> <p>假设你有一个待办事项列表：</p><pre><code class="hljs js">data: <span class="hljs-function"><span class="hljs-keyword">function</span> (<span class="hljs-params"></span>) </span>{
  <span class="hljs-keyword">return</span> {
    <span class="hljs-attr">todos</span>: [
      {
        <span class="hljs-attr">id</span>: <span class="hljs-number">1</span>,
        <span class="hljs-attr">text</span>: <span class="hljs-string">'学习使用 v-for'</span>
      },
      {
        <span class="hljs-attr">id</span>: <span class="hljs-number">2</span>,
        <span class="hljs-attr">text</span>: <span class="hljs-string">'学习使用 key'</span>
      }
    ]
  }
}</code></pre><p>然后你把它们按照字母顺序排序。在更新 DOM 的时候，Vue 将会优化渲染把可能的 DOM 变更降到最低。即可能删掉第一个待办事项元素，然后把它重新加回到列表的最末尾。</p><p>这里的问题在于，不要删除仍然会留在 DOM 中的元素。比如你想使用 <code>&lt;transition-group&gt;</code> 给列表加过渡动画，或想在被渲染元素是 <code>&lt;input&gt;</code> 时保持聚焦。在这些情况下，为每一个项目添加一个唯一的键值 (比如 <code>:key="todo.id"</code>) 将会让 Vue 知道如何使行为更容易预测。</p><p>根据我们的经验，最好<em>始终</em>添加一个唯一的键值，以便你和你的团队永远不必担心这些极端情况。也在少数对性能有严格要求的情况下，为了避免对象固化，你可以刻意做一些非常规的处理。</p></details>

#### 反例

```
<ul>
  <li v-for="todo in todos">
    {{ todo.text }}
  </li>
</ul>
```

#### 好例子

```
<ul>
  <li
    v-for="todo in todos"
    :key="todo.id"
  >
    {{ todo.text }}
  </li>
</ul>
```

### [避免 `v-if` 和 `v-for` 用在一起必要](#避免-v-if-和-v-for-用在一起必要)

**永远不要把 `v-if` 和 `v-for` 同时用在同一个元素上。**

一般我们在两种常见的情况下会倾向于这样做：

- 为了过滤一个列表中的项目 (比如 `v-for="user in users" v-if="user.isActive"`)。在这种情形下，请将 `users` 替换为一个计算属性 (比如 `activeUsers`)，让其返回过滤后的列表。
- 为了避免渲染本应该被隐藏的列表 (比如 `v-for="user in users" v-if="shouldShowUsers"`)。这种情形下，请将 `v-if` 移动至容器元素上 (比如 `ul`、`ol`)。

<details><summary><h4>详解</h4> </summary> <p>当 Vue 处理指令时，<code>v-for</code> 比 <code>v-if</code> 具有更高的优先级，所以这个模板：</p><pre><code class="hljs html"><span class="hljs-tag">&lt;<span class="hljs-name">ul</span>&gt;</span>
  <span class="hljs-tag">&lt;<span class="hljs-name">li</span>
    <span class="hljs-attr">v-for</span>=<span class="hljs-string">"user in users"</span>
    <span class="hljs-attr">v-if</span>=<span class="hljs-string">"user.isActive"</span>
    <span class="hljs-attr">:key</span>=<span class="hljs-string">"user.id"</span>
  &gt;</span>
    {{ user.name }}
  <span class="hljs-tag">&lt;/<span class="hljs-name">li</span>&gt;</span>
<span class="hljs-tag">&lt;/<span class="hljs-name">ul</span>&gt;</span></code></pre><p>将会经过如下运算：</p><pre><code class="hljs js"><span class="hljs-keyword">this</span>.users.map(<span class="hljs-function"><span class="hljs-keyword">function</span> (<span class="hljs-params">user</span>) </span>{
  <span class="hljs-keyword">if</span> (user.isActive) {
    <span class="hljs-keyword">return</span> user.name
  }
})</code></pre><p>因此哪怕我们只渲染出一小部分用户的元素，也得在每次重渲染的时候遍历整个列表，不论活跃用户是否发生了变化。</p><p>通过将其更换为在如下的一个计算属性上遍历：</p><pre><code class="hljs js">computed: {
  <span class="hljs-attr">activeUsers</span>: <span class="hljs-function"><span class="hljs-keyword">function</span> (<span class="hljs-params"></span>) </span>{
    <span class="hljs-keyword">return</span> <span class="hljs-keyword">this</span>.users.filter(<span class="hljs-function"><span class="hljs-keyword">function</span> (<span class="hljs-params">user</span>) </span>{
      <span class="hljs-keyword">return</span> user.isActive
    })
  }
}</code></pre><pre><code class="hljs html"><span class="hljs-tag">&lt;<span class="hljs-name">ul</span>&gt;</span>
  <span class="hljs-tag">&lt;<span class="hljs-name">li</span>
    <span class="hljs-attr">v-for</span>=<span class="hljs-string">"user in activeUsers"</span>
    <span class="hljs-attr">:key</span>=<span class="hljs-string">"user.id"</span>
  &gt;</span>
    {{ user.name }}
  <span class="hljs-tag">&lt;/<span class="hljs-name">li</span>&gt;</span>
<span class="hljs-tag">&lt;/<span class="hljs-name">ul</span>&gt;</span></code></pre><p>我们将会获得如下好处：</p><ul><li>过滤后的列表<em>只</em>会在 <code>users</code> 数组发生相关变化时才被重新运算，过滤更高效。</li><li>使用 <code>v-for="user in activeUsers"</code> 之后，我们在渲染的时候<em>只</em>遍历活跃用户，渲染更高效。</li><li>解耦渲染层的逻辑，可维护性 (对逻辑的更改和扩展) 更强。</li></ul><p>为了获得同样的好处，我们也可以把：</p><pre><code class="hljs html"><span class="hljs-tag">&lt;<span class="hljs-name">ul</span>&gt;</span>
  <span class="hljs-tag">&lt;<span class="hljs-name">li</span>
    <span class="hljs-attr">v-for</span>=<span class="hljs-string">"user in users"</span>
    <span class="hljs-attr">v-if</span>=<span class="hljs-string">"shouldShowUsers"</span>
    <span class="hljs-attr">:key</span>=<span class="hljs-string">"user.id"</span>
  &gt;</span>
    {{ user.name }}
  <span class="hljs-tag">&lt;/<span class="hljs-name">li</span>&gt;</span>
<span class="hljs-tag">&lt;/<span class="hljs-name">ul</span>&gt;</span></code></pre><p>更新为：</p><pre><code class="hljs html"><span class="hljs-tag">&lt;<span class="hljs-name">ul</span> <span class="hljs-attr">v-if</span>=<span class="hljs-string">"shouldShowUsers"</span>&gt;</span>
  <span class="hljs-tag">&lt;<span class="hljs-name">li</span>
    <span class="hljs-attr">v-for</span>=<span class="hljs-string">"user in users"</span>
    <span class="hljs-attr">:key</span>=<span class="hljs-string">"user.id"</span>
  &gt;</span>
    {{ user.name }}
  <span class="hljs-tag">&lt;/<span class="hljs-name">li</span>&gt;</span>
<span class="hljs-tag">&lt;/<span class="hljs-name">ul</span>&gt;</span></code></pre><p>通过将 <code>v-if</code> 移动到容器元素，我们不会再对列表中的<em>每个</em>用户检查 <code>shouldShowUsers</code>。取而代之的是，我们只检查它一次，且不会在 <code>shouldShowUsers</code> 为否的时候运算 <code>v-for</code>。</p></details>

#### 反例

```
<ul>
  <li
    v-for="user in users"
    v-if="user.isActive"
    :key="user.id"
  >
    {{ user.name }}
  </li>
</ul>
<ul>
  <li
    v-for="user in users"
    v-if="shouldShowUsers"
    :key="user.id"
  >
    {{ user.name }}
  </li>
</ul>
```

#### 好例子

```
<ul>
  <li
    v-for="user in activeUsers"
    :key="user.id"
  >
    {{ user.name }}
  </li>
</ul>
<ul v-if="shouldShowUsers">
  <li
    v-for="user in users"
    :key="user.id"
  >
    {{ user.name }}
  </li>
</ul>
```

### [为组件样式设置作用域必要](#为组件样式设置作用域必要)

**对于应用来说，顶级 `App` 组件和布局组件中的样式可以是全局的，但是其它所有组件都应该是有作用域的。**

这条规则只和[单文件组件](../guide/single-file-components.html)有关。你*不一定*要使用 [`scoped` attribute](https://vue-loader.vuejs.org/zh-cn/features/scoped-css.html)。设置作用域也可以通过 [CSS Modules](https://vue-loader.vuejs.org/zh-cn/features/css-modules.html)，那是一个基于 class 的类似 [BEM](http://getbem.com/) 的策略，当然你也可以使用其它的库或约定。

**不管怎样，对于组件库，我们应该更倾向于选用基于 class 的策略而不是 `scoped` attribute。**

这让覆写内部样式更容易：使用了常人可理解的 class 名称且没有太高的选择器优先级，而且不太会导致冲突。

<details><summary><h4>详解</h4> </summary> <p>如果你和其他开发者一起开发一个大型工程，或有时引入三方 HTML/CSS (比如来自 Auth0)，设置一致的作用域会确保你的样式只会运用在它们想要作用的组件上。</p><p>不止要使用 <code>scoped</code> attribute，使用唯一的 class 名可以帮你确保那些三方库的 CSS 不会运用在你自己的 HTML 上。比如许多工程都使用了 <code>button</code>、<code>btn</code> 或 <code>icon</code> class 名，所以即便你不使用类似 BEM 的策略，添加一个 app 专属或组件专属的前缀 (比如 <code>ButtonClose-icon</code>) 也可以提供很多保护。</p></details>

#### 反例

```
<template>
  <button class="btn btn-close">X</button>
</template>

<style>
.btn-close {
  background-color: red;
}
</style>
```

#### 好例子

```
<template>
  <button class="button button-close">X</button>
</template>

<!-- 使用 `scoped` attribute -->
<style scoped>
.button {
  border: none;
  border-radius: 2px;
}

.button-close {
  background-color: red;
}
</style>
<template>
  <button :class="[$style.button, $style.buttonClose]">X</button>
</template>

<!-- 使用 CSS Modules -->
<style module>
.button {
  border: none;
  border-radius: 2px;
}

.buttonClose {
  background-color: red;
}
</style>
<template>
  <button class="c-Button c-Button--close">X</button>
</template>

<!-- 使用 BEM 约定 -->
<style>
.c-Button {
  border: none;
  border-radius: 2px;
}

.c-Button--close {
  background-color: red;
}
</style>
```

### [私有 property 名必要](#私有-property-名必要)

**使用模块作用域保持不允许外部访问的函数的私有性。如果无法做到这一点，就始终为插件、混入等不考虑作为对外公共 API 的自定义私有 property 使用 `$_` 前缀。并附带一个命名空间以回避和其它作者的冲突 (比如 `$_yourPluginName_`)。**

<details><summary><h4>详解</h4> </summary> <p>Vue 使用 <code>_</code> 前缀来定义其自身的私有 property，所以使用相同的前缀 (比如 <code>_update</code>) 有覆写实例 property 的风险。即便你检查确认 Vue 当前版本没有用到这个 property 名，也不能保证和将来的版本没有冲突。</p><p>对于 <code>$</code> 前缀来说，其在 Vue 生态系统中的目的是暴露给用户的一个特殊的实例 property，所以把它用于<em>私有</em> property 并不合适。</p><p>不过，我们推荐把这两个前缀结合为 <code>$_</code>，作为一个用户定义的私有 property 的约定，以确保不会和 Vue 自身相冲突。</p></details>

#### 反例

```
var myGreatMixin = {
  // ...
  methods: {
    update: function () {
      // ...
    }
  }
}
var myGreatMixin = {
  // ...
  methods: {
    _update: function () {
      // ...
    }
  }
}
var myGreatMixin = {
  // ...
  methods: {
    $update: function () {
      // ...
    }
  }
}
var myGreatMixin = {
  // ...
  methods: {
    $_update: function () {
      // ...
    }
  }
}
```

#### 好例子

```
var myGreatMixin = {
  // ...
  methods: {
    $_myGreatMixin_update: function () {
      // ...
    }
  }
}
// 甚至更好！
var myGreatMixin = {
  // ...
  methods: {
    publicMethod() {
      // ...
      myPrivateFunction()
    }
  }
}

function myPrivateFunction() {
  // ...
}

export default myGreatMixin
```

## Node.js

### 2个空格的缩进

使用2个空格进行缩进，永远也不要使用混合的tab和空格作为缩进。

### 换行

使用Unix风格的换行，每行结尾以(\n)结束，永远不要使用Windows的换行符（\r\n）。

### 无拖尾空白

永远也不要在一行后面留空格，在提交之前，你要像每顿饭刷牙一样清理你的JS文件。否则，腐烂的气味会驱走贡献者或同事。

### 使用分号

根据科学研究，分号的使用是我们社会的核心价值。考虑一下[反对派](http://blog.izs.me/post/2353458699/an-open-letter-to-javascript-leaders-regarding)的观点 ，但是我们需要传统，不要滥用纠错机制（省略分号）。

注* 在JavaScript中[前置逗号代码风格](http://ourjs.com/detail/53f7ffabc1afbc6e3000000a)和省略分号一直存在争论，下同。

### 每行最多80个字符

每行最多80个字符。是的，屏幕在最近几年越来越大，但是你的脑子没怎么变，你可以使用多余的空间用来分屏。

### 使用单引号

只有在JSON文件中才使用双引号

Right:

```
var foo = 'bar';
```

Wrong:

```
var foo = "bar";
```

注* 为什么？ JavaScript中包含双引号的字符串几乎到处都是，这样你就不需要转义了。

### 在同一行写大括号

Right:

```
if (true) {
  console.log('winning');
}
```

Wrong:

```
if (true)
{
  console.log('losing');
}
```

同样，注意在条件前后都加个空格。

### 方法链（调用链）

如果你使用方法链，确保每行只调用一个方法。

同时你要合理使用缩进来表示他们的父对象是一致的。

Right:

```
User
  .findOne({ name: 'foo' })
  .populate('bar')
  .exec(function(err, user) {
    return true;
  });
```

Wrong:

```
User.findOne({ name: 'foo' }).populate('bar').exec(function(err, user) {  return true;});
User.findOne({ name: 'foo' })  .populate('bar')  .exec(function(err, user) {    return true;  });
User.findOne({ name: 'foo' }).populate('bar').exec(function(err, user) {  return true;});
User.findOne({ name: 'foo' }).populate('bar')  .exec(function(err, user) {    return true;});
```

### 每行声明一个变量

每个var只声明一个变量，它可以更容易地重新排序。但是，并且变量应该在更有意义的地方声明。

Right:

```
var keys   = ['foo', 'bar'];var values = [23, 42];var object = {};
while (keys.length) {  var key = keys.pop();  object[key] = values.pop();}
```

Wrong:

```
var keys = ['foo', 'bar'],    values = [23, 42],    object = {},    key;
while (keys.length) {  key = keys.pop();  object[key] = values.pop();}
```

### 使用首字母小写给变量属性和函数命名

变量，属性和函数名应该使用lowerCamelCase（首字母小写）。他们也应该是描述性的。一般应避免单字符变量和不常见的缩写。

Right:

```
var adminUser = db.query('SELECT * FROM users ...');
```


Wrong:



```
var admin_user = db.query('SELECT * FROM users ...');
```

### 类名首字母大写

类名的首字母应该是大写的

Right:

```
function BankAccount() {
}
```

Wrong:

```
function bank_Account() {
}
```

### 常量大写

常量应该被声明为普通变量或静态类的属性，全部使用大写字母。

Node.js/V8实际上支持Mozilla的const的扩展，但遗憾的是不能用于类成员，也不是任何ECMA标准的一部分。

Right:

```
var SECOND = 1 * 1000;
function File() {}
File.FULL_PERMISSIONS = 0777;
```

Wrong:

```
const SECOND = 1 * 1000;
function File() {}
File.fullPermissions = 0777;
```

### Object / Array 声明

使用尾随逗号，把短的声明在一行：

Right:

```
var a = ['hello', 'world'];
var b = {
  good: 'code',
  'is generally': 'pretty'
};
```

Wrong:

```
var a = [
  'hello', 'world'
];
var b = {"good": 'code'
        , "is generally": 'pretty'
        };
```

### 使用 === 操作符

编写不应该只记事规则，还要学会使用。

Right:

```
var a = 0;
if (a !== '') {
  console.log('winning');
}
```

Wrong:

```
var a = 0;
if (a == '') {
  console.log('losing');
}
```

注* === 即会判断类型，又会判断结果。

### 使用多行三元运算符

三元运算符不应该用在一行。分割成多行来代替。

Right:

```
var foo = (a === b)
  ? 1
  : 2;
```


Wrong:

```
var foo = (a === b) ? 1 : 2;
```

### 不要扩展内置对象

不要扩展原生JavaScript对象的原型。以后会后悔的。

Right:

```
var a = [];
if (!a.length) {
  console.log('winning');
}
```

Wrong:

```
Array.prototype.empty = function() {  return !this.length;}
var a = [];if (a.empty()) {  console.log('losing');}
```

注* 扩展String.prototype是比较常见的，如format, trim。

### 使用描述性的条件

任何判断条件应该分配给一个描述性命名的变量或函数：

Right:

```
var isValidPassword = password.length >= 4 && /^(?=.*\d).{4,}$/.test(password);
if (isValidPassword) {  console.log('winning');}
```

Wrong:

```
if (password.length >= 4 && /^(?=.*\d).{4,}$/.test(password)) {
  console.log('losing');
}
```

### 写小而短的函数

保持你的函数短一点。对一个大房间的最后一排的人民群众而言可以轻松读取幻灯片上的代码是比较合适的。不要指望他们有的视力保持每个函数〜15行代码。

### 早点从函数返回

为了避免if语句的深层嵌套，总是尽可能早地在函数返回值。

Right:

```
function isPercentage(val) {  if (val < 0) {    return false;  }
  if (val > 100) {    return false;  }
  return true;}
```

Wrong:

```
function isPercentage(val) {
  if (val >= 0) {
    if (val < 100) {
      return true;
    } else {
      return false;
    }
  } else {
    return false;
  }
}
```
