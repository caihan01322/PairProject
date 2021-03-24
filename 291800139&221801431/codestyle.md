# 代码规范

**参考来源：https://github.com/chjw8016/alibaba-java-style-guide**

---
[toc]

## 命名规范
### 变量命名

1. 代码中的命名均不能以下划线或美元符号开始，也不能以下划线或美元符号结束

   ```
   反例： _name / __name / $Object / name_ / name$ / Object$
   ```

2. 代码中的命名严禁使用拼音与英文混合的方式，更不允许直接使用中文拼音的方式。

   > 说明：正确的英文拼写和语法可以让阅读者易于理解，避免歧义。 注意，即使纯拼音命名方式也要避免采用。

   ```
   反例： DaZhePromotion [打折] / getPingfenByName()  [评分] / int某变量 = 3
   正例： alibaba / taobao / youku / hangzhou等国际通用的名称，可视同英文。
   ```

3. 参数名、成员变量、局部变量都统一使用**lowerCamelCase**风格，必须遵从驼峰形式。

   ```
   localValue /  inputUserId
   ```

4. 中括号是数组类型的一部分，数组定义如下：`String[] args`;

   ```
   反例：请勿使用String  args[]的方式来定义。
   ```

5. POJO类中布尔类型的变量，都不要加is，否则部分框架解析会引起序列化错误。

   ```
   反例：定义为基本数据类型boolean isSuccess；的属性，
   它的方法也是isSuccess()，RPC框架在反向解析的时候，“以为”对应的属性名称是success，导致属性获取不到，进而抛出异常。
   ```



***

### 函数、类命名

**函数最大行数：25-30**

1. 方法、函数使用**lowerCamelCase**风格，必须遵从驼峰形式。

   ```
   正例： getHttpMessage() 
   ```

2. 类名使用**UpperCamelCase**风格，必须遵从驼峰形式，但以下情形例外：（领域模型的相关命名）DO / BO / DTO / VO等。

   ```
   正例：MarcoPolo / UserDO / XmlService / TcpUdpDeal /   TaPromotion
   反例：macroPolo / UserDo / XMLService / TCPUDPDeal /   TAPromotion
   ```

3. 抽象类命名使用**Abstrac**或**Base**开头；异常类命名使用**Exception**结尾；测试类命名以它要测试的类的名称开始，以**Test**结尾。

4. 接口类中的方法和属性不要加任何修饰符号（public也不要加），保持代码的简洁性，并加上有效的Javadoc注释。尽量不要在接口里定义变量，如果一定要定义变量，肯定是与接口方法相关，并且是整个应用的基础常量。

   ```
   正例：接口方法签名：void f();
   接口基础常量表示：String COMPANY = "alibaba";
   反例：接口方法定义：public abstract void f();
   说明：JDK8中接口允许有默认实现，那么这个default方法，是对所有实现类都有价值的默认实现。
   ```



***

### 常量

  1. 常量命名全部大写，单词间用下划线隔开，力求语义表达完整清楚，不要嫌名字长。

     ```
     正例： MAX_STOCK_COUNT
     反例： MAX_COUNT
     ```

  2. 枚举类名建议带上**Enum**后缀，枚举成员名称需要全大写，单词间用下划线隔开。

     > 说明：枚举其实就是特殊的常量类，且构造方法被默认强制是私有。

     ```
     正例：枚举名字：DealStatusEnum，成员名称：SUCCESS / UNKNOWN_REASON。
     ```

  3. 不允许出现任何魔法值（即未经定义的常量）直接出现在代码中。

     ```
     反例： String key="Id#taobao_"+tradeId；
     cache.put(key,  value);
     ```

  4. long或者Long初始赋值时，必须使用大写的L，不能是小写的l，小写容易跟数字1混淆，造成误解。

     > 说明：Long a = 2l;写的是数字的21，还是Long型的2?

  5. 不要使用一个常量类维护所有常量，应该按常量功能进行归类，分开维护。

     如：缓存相关的常量放在类：CacheConsts下；系统配置相关的常量放在类：ConfigConsts下。

     > 说明：大而全的常量类，非得使用查找功能才能定位到修改的常量，不利于理解和维护。

  6. 如果变量值仅在一个范围内变化用Enum类。如果还带有名称之外的延伸属性，必须使用Enum类，下面正例中的数字就是延伸信息，表示星期几。

     ```
     正例：publicEnum{MONDAY(1),TUESDAY(2),WEDNESDAY(3),THURSDAY(4),FRIDAY(5),SATURDAY(6), SUNDAY(7);}
     ```

  7. 【参考】常量的复用层次有五层：跨应用共享常量、应用内共享常量、子工程内共享常量、包内共享常量、类内共享常量。

     - 跨应用共享常量：放置在二方库中，通常是client.jar中的constant目录下。

     - 应用内共享常量：放置在一方库的modules中的constant目录下。

     ```
     反例：易懂变量也要统一定义成应用内共享常量，两位攻城师在两个类中分别定义了表示“是”的变量：
     类A中：public  static final String YES = "yes";
     类B中：public  static final String YES = "y";
     A.YES.equals(B.YES)，预期是true，但实际返回为false，导致产生线上问题。
     ```

     - 子工程内部共享常量：即在当前子工程的constant目录下。

     - 包内共享常量：即在当前包下单独的constant目录下。

     - 类内共享常量：直接在类内部private static final定义。

       

***

### 其他命名规则

  1. 包名统一使用小写，点分隔符之间有且仅有一个自然语义的英语单词。包名统一使用单数形式，但是类名如果有复数含义，类名可以使用复数形式。

     ```
     正例：应用工具类包名为com.alibaba.open.util、类名为MessageUtils（此规则参考spring的框架结构）
     ```

  2. 杜绝完全不规范的缩写，避免望文不知义。

     ```
     反例： AbstractClass“缩写”命名成AbsClass；condition“缩写”命名成 condi，此类随意缩写严重降低了代码的可阅读性。
     ```

  3. 如果使用到了设计模式，建议在类名中体现出具体模式。

     > 说明：将设计模式体现在名字中，有利于阅读者快速理解架构设计思想。

     ```
     正例：public class OrderFactory;
     public class LoginProxy;
     public class ResourceObserver;
     ```

  4. 接口和实现类的命名有两套规则：

     - 对于Service和DAO类，基于SOA的理念，暴露出来的服务一定是接口，内部的实现类用Impl的后缀与接口区别。

       ```
       正例：CacheServiceImpl实现CacheService接口。
       ```

     - 如果是形容能力的接口名称，取对应的形容词做接口名（通常是–able的形式）。

       ```
       正例：AbstractTranslator实现 Translatable。
       ```

  5. 各层命名规约：

     - **Service/DAO层**方法命名规约
       - 获取单个对象的方法用get做前缀。
       - 获取多个对象的方法用list做前缀。
       - 获取统计值的方法用count做前缀。
       - 插入的方法用save（推荐）或insert做前缀。
       - 删除的方法用remove（推荐）或delete做前缀。
       - 修改的方法用update做前缀。

     - **领域模型**命名规约
       - 数据对象：xxxDO，xxx即为数据表名。
       - 数据传输对象：xxxDTO，xxx为业务领域相关的名称。
       - 展示对象：xxxVO，xxx一般为网页名称。
       - POJO是DO/DTO/BO/VO的统称，禁止命名成xxxPOJO。

***
## 代码风格
### 缩进规范

  1. 使用tab字符。若用空格：缩进采用4个空格

     > 说明：如果使用tab缩进，必须设置1个tab为4个空格。IDEA设置tab为4个空格时，请勿勾选Use tab character；而在eclipse中，必须勾选insert spaces for tabs。

     ```
     正例：（涉及1-5点）
     public static void main(String args[]) {
     	//缩进4个空格
     	String say = "hello";
     	//运算符的左右必须有一个空格
     	int flag = 0;
     	//关键词if与括号之间必须有一个空格，括号内的f与左括号，0与右括号不需要空格
     	if (flag == 0) {
     		System.out.println(say);
     	}
     	//左大括号前加空格且不换行；左大括号后换行
     	if (flag == 1) {
     		System.out.println("world");
     		//右大括号前换行，右大括号后有else，不用换行
     	} else {
     		System.out.println("ok");
     		//在右大括号后直接结束，则必须换行
     	}
     }
     ```

  2. 单行字符数限制不超过 120个，超出需要换行，换行时遵循如下原则：

     - 第二行相对第一行缩进 4个空格，从第三行开始，不再继续缩进，参考示例。
     - 运算符与下文一起换行。
     - 方法调用的点符号与下文一起换行。
     - 在多个参数超长，逗号后进行换行。
     - 在括号前不要换行，见反例。

     ```
     正例：
     StringBuffer sb = new StringBuffer();
     //超过120个字符的情况下，换行缩进4个空格，并且方法前的点符号一起换行
     sb.append("zi").append("xin")...
     	.append("huang")...
     	.append("huang")...
     	.append("huang");
     反例：
     StringBuffer sb = new StringBuffer();
     //超过120个字符的情况下，不要在括号前换行
     sb.append("zi").append("xin")...append
     	("huang");
     //参数很多的方法调用可能超过120个字符，不要在逗号前换行
     method(args1, args2, args3, ...
     	, argsX);
     ```

  3. 没有必要增加若干空格来使某一行的字符与上一行的相应字符对齐。

     ```
     正例：
     int a = 3;
     long b = 4L;
     float c = 5F;
     StringBuffer sb = new StringBuffer();
     说明：增加sb这个变量，如果需要对齐，则给a、b、c都要增加几个空格。
     ```



***
### 空行规则

  1. 大括号的使用约定。如果是大括号内为空，则简洁地写成{}即可，不需要换行；如果是非空代码块则：
     - 左大括号前不换行。
     - 左大括号后换行。
     - 右大括号前换行。
     - 右大括号后还有else等代码则不换行；表示终止右大括号后必须换行。

  2. DE的text file encoding设置为UTF-8; IDE中文件的换行符使用Unix格式，不要使用windows格式。 

  3. 方法体内的执行语句组、变量的定义语句组、不同的业务逻辑之间或者不同的语义之间插入一个空行。相同业务逻辑和语义之间不需要插入空行。

     > 说明：没有必要插入多行空格进行隔开。

  

***
### 操作符前后空格

  1. 左括号和后一个字符之间不出现空格；同样，右括号和前一个字符之间也不出现空格。（见例5）

  2. **if/for/while/switch/do**等保留字与左右括号之间都必须加空格。（见例5）

  3. 任何运算符左右必须加一个空格。（见例5）

     > 说明：运算符包括赋值运算符=、逻辑运算符&&、加减乘除符号、三目运行符等。

     ```
     正例：（涉及1-5点）
     public static void main(String args[]) {
     	//缩进4个空格
     	String say = "hello";
     	//运算符的左右必须有一个空格
     	int flag = 0;
     	//关键词if与括号之间必须有一个空格，括号内的f与左括号，0与右括号不需要空格
     	if (flag == 0) {
     		System.out.println(say);
     	}
     	//左大括号前加空格且不换行；左大括号后换行
     	if (flag == 1) {
     		System.out.println("world");
     		//右大括号前换行，右大括号后有else，不用换行
     	} else {
     		System.out.println("ok");
     		//在右大括号后直接结束，则必须换行
     	}
     }
     ```

  4. 方法参数在定义和传入时，多个参数逗号后边必须加空格。

     ```
     正例：下例中实参的"a",后边必须要有一个空格。
     method("a", "b", "c");
     ```
***



## 注释规约

   1. 类、类属性、类方法的注释必须使用Javadoc规范，使用内容格式，不得使用//xxx方式。

      > 说明：在IDE编辑窗口中，Javadoc方式会提示相关注释，生成Javadoc可以正确输出相应注释；在IDE中，工程调用方法时，不进入方法即可悬浮提示方法、参数、返回值的意义，提高阅读效率。

  2. 所有的抽象方法（包括接口中的方法）必须要用Javadoc注释、除了返回值、参数、异常说明外，还必须指出该方法做什么事情，实现什么功能。

     > 说明：对子类的实现要求，或者调用注意事项，请一并说明。

  3. 所有的类都必须添加创建者信息。

  4. 方法内部单行注释，在被注释语句上方另起一行，使用//注释。方法内部多行注释使用/* */注释，注意与代码对齐。

  5. 所有的枚举类型字段必须要有注释，说明每个数据项的用途。

  6. 与其“半吊子”英文来注释，不如用中文注释把问题说清楚。专有名词与关键字保持英文原文即可。

     ```
     反例：“TCP连接超时”解释成“传输控制协议连接超时”，理解反而费脑筋。
     ```

  7. 代码修改的同时，注释也要进行相应的修改，尤其是参数、返回值、异常、核心逻辑等的修改。

     > 说明：代码与注释更新不同步，就像路网与导航软件更新不同步一样，如果导航软件严重滞后，就失去了导航的意义。

  8. 注释掉的代码尽量要配合说明，而不是简单的注释掉。

     >说明：代码被注释掉有两种可能性：

     > 1）后续会恢复此段代码逻辑。

     > 2）永久不用。

     > 前者如果没有备注信息，难以知晓注释动机。后者建议直接删掉（代码仓库保存了历史代码）。

  9. 对于注释的要求：
     - 第一、能够准确反应设计思想和代码逻辑；
     - 第二、能够描述业务含义，使别的程序员能够迅速了解到代码背后的信息。完全没有注释的大段代码对于阅读者形同天书，注释是给自己看的，即使隔很长时间，也能清晰理解当时的思路；注释也是给继任者看的，使其能够快速接替自己的工作。

  10. 好的命名、代码结构是自解释的，注释力求精简准确、表达到位。避免出现注释的一个极端：过多过滥的注释，代码的逻辑一旦修改，修改注释是相当大的负担。

      ```
      反例：
      // put elephant into fridge
      put(elephant, fridge);
      方法名put，加上两个有意义的变量名elephant和fridge，已经说明了这是在干什么，语义清晰的代码不需要额外的注释。
      ```

  11. 【参考】特殊注释标记，请注明标记人与标记时间。注意及时处理这些标记，通过标记扫描，经常清理此类标记。线上故障有时候就是来源于这些标记处的代码。

      - 待办事宜（TODO）:（标记人，标记时间，[预计处理时间]）表示需要实现，但目前还未实现的功能。这实际上是一个Javadoc的标签，目前的Javadoc还没有实现，但已经被广泛使用。只能应用于类，接口和方法（因为它是一个Javadoc标签）。

      - 错误，不能工作（FIXME）:（标记人，标记时间，[预计处理时间]）在注释中用FIXME标记某代码是错误的，而且不能工作，需要及时纠正的情况。
        

***
## 编程规约

### 集合处理

  1. 除常用方法（如 getXxx/isXxx）等外，不要在条件判断中执行其它复杂的语句，将复杂逻辑判断的结果赋值给一个有意义的布尔变量名，以提高可读性。

     > 说明：很多 if语句内的逻辑相当复杂，阅读者需要分析条件表达式的最终结果，才能明确什么样的条件执行什么样的语句，那么，如果阅读者分析逻辑表达式错误呢？

     ```
     正例：
     //伪代码如下
     boolean existed = (file.open(fileName, "w") != null) && (...) || (...);
     if (existed) {
     ...
     }
     反例：
     if ((file.open(fileName, "w") != null) && (...) || (...)) {
     ...
     }
     ```

  2. 【强制】ArrayList的subList结果不可强转成ArrayList，否则会抛出ClassCastException异常：java.util.RandomAccessSubList cannot be cast to java.util.ArrayList ;

     > 说明：subList返回的是 ArrayList的内部类 SubList，并不是 ArrayList，而是ArrayList的一个视图，对于SubList子列表的所有操作最终会反映到原列表上。

  3. 【强制】使用集合转数组的方法，必须使用集合的toArray(T[] array)，传入的是类型完全一样的数组，大小就是list.size()。

     ```
     反例：直接使用toArray无参方法存在问题，此方法返回值只能是Object[]类，若强转其它类型数组将出现ClassCastException错误。
     正例：
     List<String> list = new ArrayList<String>(2);
     list.add("guan");
     list.add("bao");
     String[] array = new String[list.size()];
     array = list.toArray(array);
     说明：使用toArray带参方法，入参分配的数组空间不够大时，toArray方法内部将重新分配
     内存空间，并返回新数组地址；如果数组元素大于实际所需，下标为[ list.size() ]的数组
     元素将被置为null，其它数组元素保持原值，因此最好将方法入参数组大小定义与集合元素
     个数一致。
     ```

  4. 【强制】使用工具类Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方法，它的add/remove/clear方法会抛出UnsupportedOperationException异常。

     > 说明：asList的返回对象是一个Arrays内部类，并没有实现集合的修改方法。Arrays.asList体现的是适配器模式，只是转换接口，后台的数据仍是数组。

     ```
     String[] str = new String[] { "a", "b" };
     List list = Arrays.asList(str);
     第一种情况：list.add("c");运行时异常。
     第二种情况：str[0]= "gujin";那么list.get(0)也会随之修改。
     ```

  5. 【强制】不要在foreach循环里进行元素的remove/add操作。remove元素请使用Iterator方式，如果并发操作，需要对Iterator对象加锁。

     ```
     反例：
     List<String> a = new ArrayList<String>();
     	a.add("1");
     	a.add("2");
     	for (String temp : a) {
     		if("1".equals(temp)){
     		a.remove(temp);
     		}
     	}
     	说明：以上代码的执行结果肯定会出乎大家的意料，那么试一下把“1”换成“2”，会是同样的
     	结果吗？
     	正例：
     	Iterator<String> it = a.iterator();
     	while(it.hasNext()){
     		String temp = it.next();
     		if(删除元素的条件){
     		it.remove();
     	}
     }
     ```

  6. 【推荐】集合初始化时，尽量指定集合初始值大小。

     > 说明：ArrayList尽量使用ArrayList(int initialCapacity)初始化。

  7. 【推荐】使用entrySet遍历Map类集合KV，而不是keySet方式进行遍历。

     > 说明：keySet其实是遍历了2次，一次是转为Iterator对象，另一次是从hashMap中取出key所对应的value。而entrySet只是遍历了一次就把key和value都放到了entry中，效率更高。如果是JDK8，使用Map.foreach方法。

     ```
     正例：values()返回的是V值集合，是一个list集合对象；keySet()返回的是K值集合，是一个Set集合对象；entrySet()返回的是K-V值组合集合。
     ```

  8. 【推荐】高度注意Map类集合K/V能不能存储null值的情况，如下表格：

     | 集合类            | Key          | Value        | Super       | 说明       |
     | ----------------- | ------------ | ------------ | ----------- | ---------- |
     | Hashtable         | 不允许为null | 不允许为null | Dictionary  | 线程安全   |
     | ConcurrentHashMap | 不允许为null | 不允许为null | AbstractMap | 分段锁技术 |
     | TreeMap           | 不允许为null | 允许为null   | AbstractMap | 线程不安全 |
     | HashMap           | 允许为null   | 允许为null   | AbstractMap | 线程不安全 |

     ```
     反例：由于HashMap的干扰，很多人认为ConcurrentHashMap是可以置入null值，注意存储null值时会抛出NPE异常。
     ```

  9. 【参考】合理利用好集合的有序性(sort)和稳定性(order)，避免集合的无序性(unsort)和不稳定性(unorder)带来的负面影响。

     > 说明：稳定性指集合每次遍历的元素次序是一定的。有序性是指遍历的结果是按某种比较规则依次排列的。如：ArrayList是order/unsort；HashMap是unorder/unsort；TreeSet是order/sort。

  10. 【参考】利用Set元素唯一的特性，可以快速对一个集合进行去重操作，避免使用List的contains方法进行遍历、对比、去重操作。

***

### 控制语句

  1. 【强制】在一个switch块内，每个case要么通过break/return等来终止，要么注释说明程序将继续执行到哪一个case为止；在一个switch块内，都必须包含一个default语句并且放在最后，即使它什么代码也没有。

  2. 【强制】在if/else/for/while/do语句中必须使用大括号，即使只有一行代码，避免使用下面的形式：if (condition) statements;

  3. 【推荐】推荐尽量少用else， if-else的方式可以改写成：

     ```
     if(condition){
     	...
     	return obj;
     }
     //接着写else的业务逻辑代码;
     说明：如果非得使用if()...else if()...else...方式表达逻辑，【强制】请勿超过 3层，
     超过请使用状态设计模式。
     正例：逻辑上超过 3层的if-else代码可以使用卫语句，或者状态模式来实现。
     ```

  4. 【推荐】*除常用方法（如 getXxx/isXxx）等外，不要在条件判断中执行其它复杂的语句，将复杂逻辑判断的结果赋值给一个有意义的布尔变量名，以提高可读性。

     > 说明：很多 if语句内的逻辑相当复杂，阅读者需要分析条件表达式的最终结果，才能明确什么样的条件执行什么样的语句，那么，如果阅读者分析逻辑表达式错误呢？

     ```
     正例：
     //伪代码如下
     boolean existed = (file.open(fileName, "w") != null) && (...) || (...);
     if (existed) {
     ...
     }
     反例：
     if ((file.open(fileName, "w") != null) && (...) || (...)) {
     ...
     }
     ```

  5. 【推荐】循环体中的语句要考量性能，以下操作尽量移至循环体外处理，如定义对象、变量、获取数据库连接，进行不必要的try-catch操作（这个try-catch是否可以移至循环体外）。

  6. 【推荐】接口入参保护，这种场景常见的是用于做批量操作的接口。

  7. 【参考】方法中需要进行参数校验的场景：

     - 调用频次低的方法。

     - 行时间开销很大的方法，参数校验时间几乎可以忽略不计，但如果因为参数错误导致中间执行回退，或者错误，那得不偿失。
     - 需要极高稳定性和可用性的方法。
     - 对外提供的开放接口，不管是RPC/API/HTTP接口。
     - 敏感权限入口。

  8. 【参考】方法中不需要参数校验的场景：

     - 极有可能被循环调用的方法，不建议对参数进行校验。但在方法说明里必须注明外部参数检查。
     - 底层的方法调用频度都比较高，一般不校验。毕竟是像纯净水过滤的最后一道，参数错误不太可能到底层才会暴露问题。一般DAO层与Service层都在同一个应用中，部署在同一台服务器中，所以DAO的参数校验，可以省略。
     - 被声明成private只会被自己代码所调用的方法，如果能够确定调用方法的代码传入参数已经做过检查或者肯定不会有问题，此时可以不校验参数。



***

### 应用分层

  1. **【推荐】**图中默认上层依赖于下层，箭头关系表示可直接依赖，如：开放接口层可以依赖于Web层，也可以直接依赖于Service层，依此类推：

     - 开放接口层：可直接封装Service接口暴露成RPC接口；通过Web封装成http接口；网关控制层等。
     - 终端显示层：各个端的模板渲染并执行显示层。当前主要是velocity渲染，JS渲染，JSP渲染，移动端展示层等。
       - Web层：主要是对访问控制进行转发，各类基本参数校验，或者不复用的业务简单处理等。
       - Service层：相对具体的业务逻辑服务层。
       - Manager层：通用业务处理层，它有如下特征：
         - 1）对第三方平台封装的层，预处理返回结果及转化异常信息；
         - 2）对Service层通用能力的下沉，如缓存方案、中间件通用处理；
         - 3）与DAO层交互，对DAO的业务通用能力的封装。
       - DAO层：数据访问层，与底层MySQL、Oracle、Hbase进行数据交互。
     - 外部接口或第三方平台：包括其它部门RPC开放接口，基础平台，其它公司的HTTP接口。

  2. 【参考】（分层异常处理规约）在DAO层，产生的异常类型有很多，无法用细粒度异常进行catch，使用catch(Exceptione)方式，并thrownewDAOException(e)，不需要打印日志，因为日志在Manager/Service层一定需要捕获并打到日志文件中去，如果同台服务器再打日志，浪费性能和存储。在Service层出现异常时，必须记录日志信息到磁盘，尽可能带上参数信息，相当于保护案发现场。如果Manager层与Service同机部署，日志方式与DAO层处理一致，如果是单独部署，则采用与Service一致的处理方式。Web层绝不应该继续往上抛异常，因为已经处于顶层，无继续处理异常的方式，如果意识到这个异常将导致页面无法正常渲染， 那么就应该直接跳转到友好错误页面，尽量加上友好的错误提示信息。开放接口层要将异常处理成错误码和错误信息方式返回。

  3. 【参考】分层领域模型规约：

     - DO（Data Object）：与数据库表结构一一对应，通过DAO层向上传输数据源对象。

     - DTO（Data Transfer Object）：数据传输对象，Service和Manager向外传输的对象。

     - BO（Business Object）：业务对象。可以由Service层输出的封装业务逻辑的对象。

     - QUERY：数据查询对象，各层接收上层的查询请求。注：超过2个参数的查询封装，禁止使用Map类来传输。

     - VO（View Object）：显示层对象，通常是Web向模板渲染引擎层传输的对象。

       

***


## 其他规则
  1. 【强制】在使用正则表达式时，利用好其预编译功能，可以有效加快正则匹配速度。

     > 说明：不要在方法体内定义：Pattern pattern = Pattern.compile(规则);

  2. 【强制】velocity调用POJO类的属性时，建议直接使用属性名取值即可，模板引擎会自动按规范调用POJO的getXxx()，如果是boolean基本数据类型变量（boolean命名不需要加is前缀），会自动调用isXxx()方法。

     > 说明：注意如果是Boolean包装类对象，优先调用getXxx()的方法。

  3. 【强制】后台输送给页面的变量必须加$!{var}——中间的感叹号。

     > 说明：如果var=null或者不存在，那么${var}会直接显示在页面上。

  4. 【强制】注意 Math.random()这个方法返回是double类型，注意取值的范围 0≤x<1（能够取到零值，注意除零异常），如果想获取整数类型的随机数，不要将x放大10的若干倍然后取整，直接使用Random对象的nextInt或者nextLong方法。

  5. 【强制】获取当前毫秒数System.currentTimeMillis();而不是new Date().getTime();

     > 说明：如果想获取更加精确的纳秒级时间值，用System.nanoTime()。在JDK8中，针对统计时间等场景，推荐使用Instant类。

  6. 【推荐】尽量不要在vm中加入变量声明、逻辑运算符，更不要在vm模板中加入任何复杂的逻辑。

  7. 【推荐】任何数据结构的构造或初始化，都应指定大小，避免数据结构无限增长吃光内存。

  8. 【推荐】对于“明确停止使用的代码和配置”，如方法、变量、类、配置文件、动态配置属性等要坚决从程序中清理出去，避免造成过多垃圾。

  9. 除常用方法（如 getXxx/isXxx）等外，不要在条件判断中执行其它复杂的语句，将复杂逻辑判断的结果赋值给一个有意义的布尔变量名，以提高可读性。

     > 说明：很多 if语句内的逻辑相当复杂，阅读者需要分析条件表达式的最终结果，才能明确什么样的条件执行什么样的语句，那么，如果阅读者分析逻辑表达式错误呢？

     ```
     正例：
     //伪代码如下
     boolean existed = (file.open(fileName, "w") != null) && (...) || (...);
     if (existed) {
     ...
     }
     反例：
     if ((file.open(fileName, "w") != null) && (...) || (...)) {
     ...
     }
     ```

  10. 在**if/else/for/while/do**语句中必须使用大括号，即使只有一行代码，避免使用下面的形式：`if (condition) statements;`

  11. 【参考】循环体中的语句要考量性能，以下操作尽量移至循环体外处理，

     如定义对象、变量、获取数据库连接，进行不必要的try-catch操作（这个try-catch是否可以移至循环体外）。

***

