# 编码规范

[golang uber's codestyle](https://github.com/uber-go/guide/blob/master/style.md)

[javascript's codestyle](https://github.com/airbnb/javascript)

## 后端(golang)

1. 缩进

   4个空格

   ``` go
   func Search() {
   	code := http.StatusBadRequest
   	var msg string
   	data := make(map[string]interface{})
   }
   ```

2. 变量命名

   CamelCase风格

   ``` go
   var FooBar = 0 // public
   var barFoo = 0 // private
   func Search() {
       fooBar := 0 // local
   }
   ```

3. 每行最多字符数

   单行字符数限制不超过 120个，超出需要换行，换行时遵循如下原则：

   - 同一代码语句第二行相对于第一行缩进4个空格，从第三行开始，不再继续缩进
   - 运算符（包括调用符.）保留在上一行，此为语法要求
   - 括号运算符作为结尾

   ``` go
   str := "abc" +
       "abc"
   bo := (a / b + c
       * d)
   ```

4. 函数最大行数

   根据实际内容

5. 函数、类型命名

   CamelCase风格

   ``` go
   func fooBar() {} // private
   func FooBar() {} // public
   type fooBar struct {} // private
   type FooBar int // public
   ```

6. 常量

   与变量命名规则一样

7. 空行规则

   - 函数之间1空行
   - 注释与被注释代码段间不空行

   ``` go
   func f1() {
       // comment
       println()
   }
   
   func f2() {
       
   }
   ```

8. 注释规则

   统一使用`//`单行注释

9. 操作符前后空格

   - 取子slice，前后没有空格，比如`sub := s[1:4]`
   - 其它计算运算符均前后一个空格，如`a := c + b`

10. 其它规则

    [uber's codestyle](https://github.com/uber-go/guide/blob/master/style.md)

## 前端

所有规范均来自：https://github.com/airbnb/javascript

