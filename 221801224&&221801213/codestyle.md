
> （SUN的标准Java代码规范为基础）

[toc]

## 1. 标识符命名规范
### 1.1 概述
标识符的命名力求做到统一、达意和简洁。

#### 1.1.1 统一
统一是指，对于同一个概念，在程序中用同一种表示方法，比如对于供应商，既可以用supplier，也可以用provider，但是我们只能选定一个使用，至少在一个Java项目中保持统一。统一是作为重要的，如果对同一概念有不同的表示方法，会使代码混乱难以理解。即使不能取得好的名称，但是只要统一，阅读起来也不会太困难，因为阅读者只要理解一次。

#### 1.1.2 达意

达意是指，标识符能准确的表达出它所代表的意义，比如： newSupplier, OrderPaymentGatewayService等；而 supplier1, service2，idtts等则不是好的命名方式。准确有两成含义，一是正确，二是丰富。如果给一个代表供应商的变量起名是 order，显然没有正确表达。同样的，supplier1, 远没有targetSupplier意义丰富。

#### 1.1.3 简洁

简洁是指，在统一和达意的前提下，用尽量少的标识符。如果不能达意，宁愿不要简洁。比如：theOrderNameOfTheTargetSupplierWhichIsTransfered 太长， transferedTargetSupplierOrderName则较好，但是transTgtSplOrdNm就不好了。省略元音的缩写方式不要使用，我们的英语往往还没有好到看得懂奇怪的缩写。

#### 1.1.4 骆驼法则

Java中，除了包名，静态常量等特殊情况，大部分情况下标识符使用骆驼法则，即单词之间不使用特殊符号分割，而是通过首字母大写来分割。比如: supplierName, addNewContract，而不是 supplier_name, add_new_contract。

#### 1.1.5 英文 vs 拼音

尽量使用通俗易懂的英文单词，如果不会可以向队友求助，实在不行则使用汉语拼音，避免拼音与英文混用。比如表示归档，用archive比较好, 用pigeonhole则不好，用guiDang尚可接受。

### 1.2 包名
使用小写字母如 com.xxx.settlment，不要 com.xxx.Settlement
单词间不要用字符隔开，比如 com.xxx.settlment.jsfutil，而不要com.xxx.settlement.jsf_util

### 1.3 类名

#### 1.3.1 首字母大写

类名要首字母大写，比如 SupplierService, PaymentOrderAction；不要 supplierService, paymentOrderAction.

#### 1.3.2 后缀

类名往往用不同的后缀表达额外的意思

### 1.4 方法名

首字母小写，如 addOrder() 不要 AddOrder()
动词在前，如 addOrder()，不要orderAdd()
动词前缀往往表达特定的含义。
find方法在业务层尽量表达业务含义，比如 findUnsettledOrders()，查询未结算订单，而不要findOrdersByStatus()。 数据访问层，find,update等方法可以表达要执行的sql，比如findByStatusAndSupplierIdOrderByName(Status.PAID, 345)

### 1.5 域名

#### 1.5.1静态变量

全大写用下划线分割，如

public static find String ORDER_PAID_EVENT = “ORDER_PAID_EVENT”;

#### 1.5.2 枚举

全大写，用下划线分割，如

public enum Events {
ORDER_PAID,
ORDER_CREATED

}

#### 1.5.3 其他

首字母小写，骆驼法则，如：

public String orderName;

### 1.6 局部变量名

参数和局部变量名首字母小写，骆驼法则。尽量不要和域冲突，尽量表达这个变量在方法中的意义。

## 2. 代码格式

用空格字符缩进源代码，不要用tab，每个缩进4个空格。

### 2.1 源文件编码

源文件使用utf-8编码，结尾用unix n 分格。

### 2.2 行宽

行宽度不要超过80。Eclipse标准

### 2.3 包的导入

删除不用的导入，尽量不要使用整个包的导入。在eclipse下经常使用快捷键 ctrl+shift+o 修正导入。

### 2.4 域格式

每行只能声明一个域。
域的声明用空行隔开。

### 2.5 代码块格式

#### 2.5.1 缩进风格
大括号的开始在代码块开始的行尾，闭合在和代码块同一缩进的行首

#### 2.5.2 空格的使用

##### 2.5.2.1 表示分割时用一个空格

不能这样：

if       (               a >        b   )            {

    //do something here

};

##### 2.5.2.2 二元三元运算符两边用一个空格隔开

如下：

a + b = c;

b - d = e;

return a == b ? 1 : 0;

不能如下：

a+b=c;

b-d=e;

return a==b?1:0;

##### 2.5.2.3 逗号语句后如不换行，紧跟一个空格

如下：

call(a, b, c);

不能如下：

call(a,b,c);

#### 2.5.3 空行的使用

空行可以表达代码在语义上的分割，注释的作用范围，等等。将类似操作，或一组操作放在一起不用空行隔开，而用空行隔开不同组的代码， 如下：

order = orderDao.findOrderById(id);

 

//update properties

order.setUserName(userName);

order.setPrice(456);

order.setStatus(PAID);

orderService.updateTotalAmount(order);

session.saveOrUpdate(order);

上例中的空行，使注释的作用域很明显.

连续两行的空行代表更大的语义分割。
方法之间用空行分割
域之间用空行分割
超过十行的代码如果还不用空行分割，就会增加阅读困难
