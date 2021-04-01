# 前端代码规范
> 来源：[京东代码规范](https://guide.aotu.io/)
## HTML 规范
### 书写风格
#### HTML代码大小写
> HTML标签名、类名、标签属性和大部分属性值统一用小写。<br />
> HTML文本、CDATA、JavaScript、meta标签某些属性等内容可大小写混合。
#### 类型属性
> 不需要为 CSS、JS 指定类型属性，HTML5 中默认已包含。
#### 元素属性
> 元素属性值使用双引号语法。<br />
> 元素属性值可以写上的都写上。
#### 特殊字符引用
> 文本可以和字符引用混合出现。这种方法可以用来转义在文本中不能合法出现的字符。<br />
> 小于号 “<” 和大于号 “>”在 HTML 源代码中使用字符实体。
#### 代码缩进
> 统一使用四个空格进行代码缩进，使得各编辑器表现一致（各编辑器有相关配置）。
#### 纯数字输入框
> 使用 type="tel" 而不是 type="number"。
#### 代码嵌套
> 元素嵌套规范，每个块状元素独立一行，内联元素可选。
## CSS 规范
### 代码风格
#### 代码格式化
> 样式书写统一使用展开格式（Expanded）。
#### 代码大小写
> 样式选择器，属性名，属性值关键字全部使用小写字母书写，属性字符串允许使用大小写。
#### 选择器
> 尽量少用通用选择器 *。<br />
> 少使用 ID 选择器。<br />
> 不使用无具体语义定义的标签选择器。
#### 代码缩进
> 统一使用四个空格进行代码缩进，使得各编辑器表现一致（各编辑器有相关配置）。
#### 分号
> 每个属性声明末尾都要加分号。
#### 代码易读性
> 左括号与类名之间一个空格，冒号与属性值之间一个空格。<br />
> 逗号分隔的取值，逗号之后一个空格。<br />
> 为单个css选择器或新申明开启新行。<br />
> 颜色值 rgb() rgba() hsl() hsla() rect() 中不需有空格，且取值不要带有不必要的 0。<br />
> 属性值十六进制数值能用简写的尽量用简写。<br />
> 不要为 0 指明单位。
#### 属性值引号
> css属性值需要用到引号时，统一使用单引号。
#### 属性书写顺序
> 建议遵循以下顺序：<br />
> 布局定位属性：display / position / float / clear / visibility / overflow<br />
> 自身属性：width / height / margin / padding / border / background<br />
> 文本属性：color / font / text-decoration / text-align / vertical-align / white- space / break-word<br />
> 其他属性（CSS3）：content / cursor / border-radius / box-shadow / text-shadow / background:linear-gradient …
#### CSS3浏览器私有前缀写法
> CSS3 浏览器私有前缀在前，标准前缀在后。