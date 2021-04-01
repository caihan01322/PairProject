**代码规范参考来源：阿里巴巴编码规范（Java）** 

# 代码规范

- **缩进**

  按照IDEA的自然缩进，使用四个空格，而不能使用tab缩进。

  ```java
      public static void writeToFile(String filePath, String str) throws IOException {
          File file = new File(filePath);
          BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
          bufferedWriter.write(str);
          bufferedWriter.close();
      }
  ```

  

- **变量命名**

  方法名、参数名、成员变量、局部变量统一采用小驼峰式命名规则，即除第一个单词之外，其他单词首字母大写。

  ```java
  firstName
  secondName
  ```

  

- **每行最多字符数**
  - 每一行代码字符数不超过 `120`，超出需要换行，这样写出来的代码看起来舒服简洁。
  - 注释行可以超过 `120` 个字符，但最大不超过 `150。`



- **函数最大行数**

  最大行数为50，行数太多会增加复杂程度和增加阅读难度和增加维护难度。



- **函数名、类命名**

  - 函数名命令采用小驼峰法，类命名使用大驼峰法，命名要具有含义，能指示出函数或类的功能。

  - 抽象类命名使用Abstract或Base开头；异常类命名使用Exception结尾；测试类命名以它要测试的类的名称开始，以Test结尾。

  ```java
  	public static void printWords(Map<String, Integer> map, FileWriter writer) throws IOException {
          int i = 0;
          for (Map.Entry<String, Integer> entry : map.entrySet()) {
              writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
              if (i++ >= 9) {//打印频率前十的单词
                  break;
              }
          }
      }
  
  	public class WordCount {
      	public static void main(String[] args) throws IOException {
          	int characters = Lib.charactersCount(args[0], args[1]);
          	int words = Lib.wordsCount(args[0], args[1]);
          	int lines = Lib.linesCount(args[0], args[1]);
          	Map<String, Integer> wordsMap = Lib.wordNum(args[0], args[1]);
      	}
  	}
  ```

  

- **常量**

  - 常量命名全部大写，单词间用下划线隔开；不要使用一个常量类维护所有常量，应该按常量功能进行归类，分开维护。
  - long或者Long初始赋值时，必须使用大写的L，不能是小写的l，小写容易跟数字1混淆，造成误解。

  

- **空行规则**

  - 定义变量后要空行。

  - 每个函数定义结束之后都要加空行。

  - 不同的业务逻辑之间或者不同的语义之间插入一个空行。

    ```java
            writer.close();
            return result;
        }
    
        //打印出频率前十的单词
        public static void printWords(Map<String, Integer> map, FileWriter writer) throws IOException {
            int i = 0;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
    ```

    

- **注释规则**

  - 在方法体前使用单行注解//...注明方法体的功能。

  - 代码较长时，特别是有多重嵌套的时候，在段落的结束处加注释，便于阅读。

  - 对于难以达意的变量在其后（同一行） 进行注解解释。

  - 对于一些比较难理解的常量，在常量的后面标志他的含义。

    ```java
    	//打印出频率前十的单词
        public static void printWords(Map<String, Integer> map, FileWriter writer) throws IOException {}
    
    	int wordNum;//单词的总数
    ```

    

- **操作符前后空格**

  任何运算符左右必须加一个空格。

  ```java
  int i = (3 + 1) & (5 / 2);
  ```

- **oop规约**

  - 构造方法里面禁止加入任何业务逻辑，如果有初始化逻辑，请放在init方法中。
  - 当一个类有多个构造方法，或者多个同名方法，这些方法应该按顺序放置在一起，便于阅读。

- **异常处理**

  - 对大段代码进行try-catch，这是不负责任的表现。catch时请分清稳定代码和非稳定代码，稳定代码指的是无论如何不会出错的代码。对于非稳定代码的catch尽可能进行区分异常类型，再做对应的异常处理。
- finally块必须对资源对象、流对象进行关闭，有异常也要做try-catch。
  
- **SQL语句** 

  - 不能使用count（列名）或者count（常量）代替count（*）
- 使用sum()返回数据时，必须要使用ifnull(sum())代替计算
  - 不得使用外键和级联（在主键表更新一条id后，涉及外键的表需要同步更新）
  - 数据订正，先使用select再进行更新、删除
  
- **其他规则**

  - 方法参数在定义和传入时，多个参数逗号后边必须加空格。

    ```java
    public static void printWords(Map<String, Integer> map, FileWriter writer) throws IOException {
    ```

  - if/for/while/switch/do 等保留字与括号之间都必须加空格。

  - 包名统一使用小写，点分隔符之间有且仅有一个自然语义的英语单词。包名统一使用单数形式，但是类名如果有复数含义，类名可以使用复数形式。

    ```java
    com.jia.pojo
    com.jia.service
    com.jia.dao
    ```

  - 一个函数做一件事，函数要保持精简、精练、简练、简洁。
  - 避免在子父类的成员变量之间、或者不同代码块的局部变量之间采用完全相同的命名，使可读性降低。
  - 杜绝完全不规范的缩写。