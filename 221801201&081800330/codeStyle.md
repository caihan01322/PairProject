#来源 阿里云后端代码规范
##Java开发规范
###命名
【规范】类名使用UpperCamelCase 风格，必须遵从驼峰形式，但以下情形例外： （ 领域模型的相关命名 ）DO / BO / DTO / VO 等。

正例： MarcoPolo / UserDO / XmlService / TcpUdpDeal / TaPromotion

反例： macroPolo / UserDo / XMLService / TCPUDPDeal / TAPromotion

 

【规范】方法名、参数名、成员变量、局部变量都统一使用lowerCamelCase 风格，必须遵从驼峰形式。

正例： localValue / getHttpMessage() / inputUserId

 

【规范】常量命名全部大写，单词间用下划线隔开，力求语义表达完整清楚，不要嫌名字长。

 

【规范】抽象类命名使用 Abstract 或 Base 开头 ； 异常类命名使用 Exception 结尾 ； 测试类命名以它要测试的类的名称开始，以 Test 结尾。枚举类名建议带上 Enum 后缀，枚举成员名称需要全大写，单词间用下划线隔开。

 

【规范】POJO 类中布尔类型的变量，都不要加 is ，否则部分框架解析会引起序列化错误。

 

【规范】各层命名规约：

A) Service / DAO 层方法命名规约

1 ） 获取单个对象的方法用 get 做前缀。

2 ） 获取多个对象的方法用 list 做前缀（习惯：getXXXList）。

3 ） 获取统计值的方法用 count 做前缀。

4 ） 插入的方法用 save（ 推荐 ） 或 insert 做前缀。

5 ） 删除的方法用 remove（ 推荐 ） 或 delete 做前缀。

6 ） 修改的方法用 update 做前缀(或modify)。

B) 领域模型命名规约

1 ） 数据对象： xxxDO ， xxx 即为数据表名。

2 ） 数据传输对象： xxxDTO ， xxx 为业务领域相关的名称。

3 ） 展示对象： xxxVO ， xxx 一般为网页名称。

4 ） POJO 是 DO / DTO / BO / VO 的统称，禁止命名成 xxxPOJO 。

 

###常量
【规范】不允许任何魔法值（ 即未经定义的常量 ） 直接出现在代码中。

反例： String key =" Id # taobao _"+ tradeId；

cache . put(key , value);

 

###格式规约
【风格】单行太长需换行

 

【风格】方法体内的执行语句组、变量的定义语句组、不同的业务逻辑之间或者不同的语义之间插入一个空行。相同业务逻辑和语义之间不需要插入空行。

 

###OOP规约
【效率】避免通过一个类的对象引用访问此类的静态变量或静态方法，无谓增加编译器解析成本，直接用类名来访问即可。

 

【规范】所有的覆写方法，必须加@ Override 注解。

 

【规范】对外暴露的接口签名，原则上不允许修改方法签名，避免对接口调用方产生影响。接口过时必须加@Deprecated 注解，并清晰地说明采用的新接口或者新服务是什么。

 

【规范】Object 的 equals 方法容易抛空指针异常，应使用常量或确定有值的对象来调用equals。

正例： " test " .equals(object);

反例： object.equals( " test " );

 

【规范】所有的相同类型的包装类对象之间值的比较，全部使用 equals 方法比较。(注意空指针)

说明：对于 Integer var =?在-128 至 127 之间的赋值， Integer 对象是在IntegerCache . cache 产生，会复用已有对象，这个区间内的 Integer 值可以直接使用==进行判断，但是这个区间之外的所有数据，都会在堆上产生，并不会复用已有对象，这是一个大坑，推荐使用 equals 方法进行判断。

 

【规范】关于基本数据类型与包装数据类型的使用标准如下：

1 ） 所有的 POJO 类属性必须使用包装数据类型。

2 ） RPC 方法的返回值和参数必须使用包装数据类型。

3 ） 所有的局部变量【推荐】使用基本数据类型。

 

【强制】序列化类新增属性时，请不要修改 serialVersionUID 字段，避免反序列失败 ； 如果完全不兼容升级，避免反序列化混乱，那么请修改 serialVersionUID 值。

 

【规范】构造方法里面禁止加入任何业务逻辑，如果有初始化逻辑，请放在 init 方法中。

 

【规范】使用索引访问用 String 的 split 方法得到的数组时，需做最后一个分隔符后有无内容的检查，否则会有抛 IndexOutOfBoundsException 的风险。

说明：

String str = "a,b,c,,";

String[] ary = str.split(",");

//预期大于 3，结果是 3

System.out.println(ary.length);

 

【规范】当一个类有多个构造方法，或者多个同名方法，这些方法应该按顺序放置在一起，便于阅读。

 

【风格】类内方法定义顺序依次是：公有方法或保护方法 > 私有方法 > getter / setter方法。

 

【效率】final 可提高程序响应效率，声明成 final 的情况：

1 ） 不需要重新赋值的变量，包括类属性、局部变量。

2 ） 对象参数前加 final ，表示不允许修改引用的指向。

3 ） 类方法确定不允许被重写。

4 ）例子：final boolean existed = (file.open(fileName, "w") != null) && (...) || (...);

 

###集合处理
【强制】关于 hashCode 和 equals 的处理，遵循如下规则：

1） 只要重写 equals ，就必须重写 hashCode 。

2） 因为 Set 存储的是不重复的对象，依据 hashCode 和 equals 进行判断，所以 Set 存储的对象必须重写这两个方法。

3） 如果自定义对象做为 Map 的键，那么必须重写 hashCode 和 equals 。

 

【强制】不要在 foreach 循环里进行元素的 remove / add 操作。 remove 元素请使用 Iterator方式，如果并发操作，需要对 Iterator 对象加锁。