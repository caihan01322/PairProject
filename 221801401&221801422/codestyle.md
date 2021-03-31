# 编程规范

##（一）命名规范
1. 代码中的命名不能以下划线或美元符号开始，也不能以下划线或美元符号结束。
> 反例：_name / __name / $Object / name_ / name$ /Object$
2. 命名不能用拼音，只能用单词或者单词拼接的形式。
3. 类名的命名使用UpperCamelCase风格，必须遵从驼峰形式。
4. 方法名、参数名、成员变量、局部变量的命名使用lowerCamelCase风格，必须遵从驼峰形式。
> 正例:localValue / getHttpMessage() / inputUserId
5. 常量的命名需要大写，单词之间用下划线隔开。
> 正例: MAX_STOCK_COUNT
> 反例：MAX_COUNT
6. 抽象类命名使用Abstract或Base开头；异常类命名用Exception结尾；测试类用Test结尾。
7. 在命名数组时，中括号需包括到数组类型中。
> 反例：String args[]
8. 在采用单词的缩写时，避免产生异议。
9.  POJO类中布尔类型的变量，都不要加is。
> 反例：定义为基本数据类型boolean isSuccess；的属性，它的方法也是isSuccess（）。
10. 包名统一使用小写，点分隔符之间有且仅有一个自然语义的英语单词。包名统一使用单数形式，但是类名如果有复数含义，类名可以使用复数形式。
> 正例：应用工具类包名为com.open.util、类名为MessageUtils。
11. 各层命名规范：
 * Service/DAO层方法命名规范
 1. 获取单个对象的方法用get做前缀。
 2. 获取多个对象的方法用list做前缀。
 3. 获取统计值的方法用count做前缀。
 4. 插入的方法用save或insert做前缀。
 5. 删除的方法用remove或delete做前缀。
 6. 修改的方法用update做前缀。
 * 领域模型命名规范
 1. 数据对象：xxxDO，xxx即为数据表明。
 2. 数据传输对象：xxxDTO，xxx为业务领域相关的名称。
 3. 展示对象：xxxVO，xxx一般为网页名称。
 4. POJP是DO/DTO/BO/VO的统称，进制命名成xxxPOJO。

## （二）常量定义
1. long或者Long初始赋值时，必须使用大写的L，不能是小写的l。
2. 尽量不要使用常量类维护所有常量，应该按常量功能进行归类，分开维护。如：缓存相关的常量放在类：CacheConsts下；系统配置相关的常量放在类：ConfigConsts下。

## （三）格式规范
1. 大括号的使用规范：如果是大括号内为空，则简洁地写成{}即可，不需要换行；如果是非空代码块则：
* 左大括号前不换行。
* 左大括号后换行。
* 右大括号前换行。
2. 任何运算符左右必须加一个空格。
> 说明：运算符包括赋值运算符=、逻辑运算符&&、加减乘除符号、三目运行符。
3. 单行字符数限制不超过 120个，超出需要换行，换行时遵循如下原则：

    * 第二行相对第一行缩进 4个空格，从第三行开始，不再继续缩进，参考示例。
    * 运算符与下文一起换行。
    * 方法调用的点符号与下文一起换行。
    * 在多个参数超长，逗号后进行换行。
    * 在括号前不要换行。
4. 方法参数在定义和传入时，多个参数逗号后边必须加空格。
> 正例：method("a", "b", "c");
5. 方法体内的执行语句组、变量的定义语句组、不同的业务逻辑之间或者不同的语义之间插入一个空行。相同业务逻辑和语义之间不需要插入空行。

## （四）控制语句
1. 在一个switch块内，每个case要么通过break/return等来终止，要么注释说明程序将继续执行到哪一个case为止；在一个switch块内，都必须包含一个default语句并且放在最后，即使它什么代码也没有。
2. 在if/else/for/while/do语句中必须使用大括号，即使只有一行代码，避免使用下面的形式：if (condition) statements;
3. 除常用方法（如 getXxx/isXxx）等外，不要在条件判断中执行其它复杂的语句，将复杂逻辑判断的结果赋值给一个有意义的布尔变量名，以提高可读性。
4. 循环体中的语句要考量性能，以下操作尽量移至循环体外处理，如定义对象、变量、获取数据库连接，进行不必要的try-catch操作（这个try-catch是否可以移至循环体外）。

## （五）注释规范
1. 方法内部单行注释，在被注释语句上方另起一行，使用//注释。方法内部多行注释使用/ */注释，注意与代码对齐。
2. 与其“半吊子”英文来注释，不如用中文注释把问题说清楚。专有名词与关键字保持英文原文即可。
3. 代码修改的同时，注释也要进行相应的修改，尤其是参数、返回值、异常、核心逻辑等的修改。
4. 注释掉的代码尽量要配合说明，而不是简单的注释掉。

## （六）OOP规约
1. 定义DO/DTO/VO等POJO类时，不要设定任何属性默认值。
> 反例：POJO类的gmtCreate默认值为new   Date();但是这个属性在数据提取时并没有置入具体值，在更新其它字段时又附带更新了此字段，导致创建时间被修改成当前时间。
2. 避免通过一个类的对象引用访问此类的静态变量或静态方法，无谓增加编译器解析成本，直接用类名来访问即可。
3. 所有的覆写方法，必须加@Override注解。
> 反例：getObject()与get0bject()的问题。一个是字母的O，一个是数字的0，加@Override可以准确判断是否覆盖成功。
另外，如果在抽象类中对方法签名进行修改，其实现类会马上编译报错。
4. 相同参数类型，相同业务含义，才可以使用Java的可变参数，避免使用Object。
> 说明：可变参数必须放置在参数列表的最后。（提倡同学们尽量不用可变参数编程）
> 正例：public User getUsers(String type, Integer... ids)
5. 对外暴露的接口签名，原则上不允许修改方法签名，避免对接口调用方产生影响。接口过时必须加@Deprecated注解，并清晰地说明采用的新接口或者新服务是什么。
6. Object的equals方法容易抛空指针异常，应使用常量或确定有值的对象来调用equals。
> 正例： "test".equals(object);
> 反例： object.equals("test");
> 说明：推荐使用java.util.Objects#equals（JDK7引入的工具类）
7. 不能使用过时的类或方法。
> 说明：java.net.URLDecoder中的方法decode(StringencodeStr)这个方法已经过时，应该使用双参数decode(String source, String encode)。接口提供方既然明确是过时接口，那么有义务同时提供新的接口；作为调用方来说，有义务去考证过时方法的新实现是什么。
8. 所有的相同类型的包装类对象之间值的比较，全部使用equals方法比较。
> 说明：对于Integer var=?在-128至127之间的赋值，Integer对象是在IntegerCache.cache产生，会复用已有对象，这个区间内的Integer值可以直接使用==进行判断，但是这个区间之外的所有数据，都会在堆上产生，并不会复用已有对象，这是一个大坑，推荐使用equals方法进行判断。
9. 关于基本数据类型与包装数据类型的使用标准如下：

  * 所有的POJO类属性必须使用包装数据类型。
  * RPC方法的返回值和参数必须使用包装数据类型。
  * 所有的局部变量【推荐】使用基本数据类型。
10. 序列化类新增属性时，请不要修改serialVersionUID字段，避免反序列失败；如果完全不兼容升级，避免反序列化混乱，那么请修改serialVersionUID值。
> 说明：注意serialVersionUID不一致会抛出序列化运行时异常。
11. 构造方法里面禁止加入任何业务逻辑，如果有初始化逻辑，请放在init方法中。
12. POJO类必须写toString方法。使用IDE的中工具：source> generate toString时，如果继承了另一个POJO类，注意在前面加一下super.toString。
> 说明：在方法执行抛出异常时，可以直接调用POJO的toString()方法打印其属性值，便于排查问题。

## （七）集合处理
1. 关于hashCode和equals的处理，遵循如下规则：

   * 只要重写equals，就必须重写hashCode。
   * 因为Set存储的是不重复的对象，依据hashCode和equals进行判断，所以Set存储的对象必须重写这两个方法。
   * 如果自定义对象做为Map的键，那么必须重写hashCode和equals。
> 正例：String重写了hashCode和equals方法，所以我们可以非常愉快地使用String对象作为key来使用。   
2. ArrayList的subList结果不可强转成ArrayList，否则会抛出ClassCastException异常：java.util.RandomAccessSubList cannot be cast to java.util.ArrayList ;
> 说明：subList返回的是 ArrayList的内部类 SubList，并不是 ArrayList，而是ArrayList的一个视图，对于SubList子列表的所有操作最终会反映到原列表上。
3. 在subList场景中，高度注意对原集合元素个数的修改，会导致子列表的遍历、增加、删除均产生ConcurrentModificationException异常。
4. 使用集合转数组的方法，必须使用集合的toArray(T[] array)，传入的是类型完全一样的数组，大小就是list.size()。
> 反例：直接使用toArray无参方法存在问题，此方法返回值只能是Object[]类，若强转其它类型数组将出现ClassCastException错误。
> 正例：
  List<String> list = new ArrayList<String>(2);
  list.add("guan");
  list.add("bao");
  String[] array = new String[list.size()];
  array = list.toArray(array);
> 说明：使用toArray带参方法，入参分配的数组空间不够大时，toArray方法内部将重新分配
内存空间，并返回新数组地址；如果数组元素大于实际所需，下标为[ list.size() ]的数组
元素将被置为null，其它数组元素保持原值，因此最好将方法入参数组大小定义与集合元素
个数一致。
5. 使用工具类Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方法，它的add/remove/clear方法会抛出UnsupportedOperationException异常。
> 说明：asList的返回对象是一个Arrays内部类，并没有实现集合的修改方法。Arrays.asList体现的是适配器模式，只是转换接口，后台的数据仍是数组。
> String[] str = new String[] { "a", "b" };
  List list = Arrays.asList(str);
  第一种情况：list.add("c");运行时异常。
  第二种情况：str[0]= "gujin";那么list.get(0)也会随之修改。
6. 泛型通配符<? extends T>来接收返回的数据，此写法的泛型集合不能使用add方法。
> 说明：苹果装箱后返回一个<? extends Fruits>对象，此对象就不能往里加任何水果，包括苹果。
7. 不要在foreach循环里进行元素的remove/add操作。remove元素请使用Iterator方式，如果并发操作，需要对Iterator对象加锁。
> 反例：
List<String> a = new ArrayList<String>();
	a.add("1");
	a.add("2");
	for (String temp : a) {
		if("1".equals(temp)){
		a.remove(temp);
		}
	}
> 正例：
	Iterator<String> it = a.iterator();
	while(it.hasNext()){
		String temp = it.next();
		if(删除元素的条件){
		it.remove();
	}
}
8. 在 JDK7版本以上，Comparator要满足自反性，传递性，对称性，不然Arrays.sort，Collections.sort会报IllegalArgumentException异常。
> 反例：下例中没有处理相等的情况，实际使用中可能会出现异常：
new Comparator<Student>() {
	@Override
	public int compare(Student o1, Student o2) {
		return o1.getId() > o2.getId() ? 1 : -1;
	}
}

## （八）并发处理
1. 获取单例对象需要保证线程安全，其中的方法也要保证线程安全。
> 说明：资源驱动类、工具类、单例工厂类都需要注意。
2. 创建线程或线程池时请指定有意义的线程名称，方便出错时回溯。
> 正例：
public class TimerTaskThread extends Thread {
	public TimerTaskThread(){
		super.setName("TimerTaskThread");  ...
}
3. 线程资源必须通过线程池提供，不允许在应用中自行显式创建线程。
> 说明：使用线程池的好处是减少在创建和销毁线程上所花的时间以及系统资源的开销，解决资源不足的问题。如果不使用线程池，有可能造成系统创建大量同类线程而导致消耗完内存或者“过度切换”的问题。
4. 线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
5. SimpleDateFormat是线程不安全的类，一般不要定义为static变量，如果定义为static，必须加锁，或者使用DateUtils工具类。
> 正例：注意线程安全，使用DateUtils。亦推荐如下处理：
private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
	@Override
	protected DateFormat initialValue() {
		return new SimpleDateFormat("yyyy-MM-dd");
	}
};
> 说明：如果是JDK8的应用，可以使用Instant代替Date，LocalDateTime代替Calendar，DateTimeFormatter代替Simpledateformatter，
官方给出的解释：simple beautiful strong immutable thread-safe。
6. 高并发时，同步调用应该去考量锁的性能损耗。能用无锁数据结构，就不要用锁；能锁区块，就不要锁整个方法体；能用对象锁，就不要用类锁。
7. 对多个资源、数据库表、对象同时加锁时，需要保持一致的加锁顺序，否则可能会造成死锁。
> 说明：线程一需要对表A、B、C依次全部加锁后才可以进行更新操作，那么线程二的加锁顺序也必须是A、B、C，否则可能出现死锁。
8. 并发修改同一记录时，避免更新丢失，要么在应用层加锁，要么在缓存加锁，要么在数据库层使用乐观锁，使用version作为更新依据。
> 说明：如果每次访问冲突概率小于20%，推荐使用乐观锁，否则使用悲观锁。乐观锁的重试次数不得小于3次。
9. 多线程并行处理定时任务时，Timer运行多个TimeTask时，只要其中之一没有捕获抛出的异常，其它任务便会自动终止运行，使用ScheduledExecutorService则没有这个问题。