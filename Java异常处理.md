### `Java`异常处理

#### 一、异常处理机制介绍：

- `Java`的异常处理机制可以让程序在异常发生时，按照设定的逻辑针对性地处理异常，并让程序正常执行；
- `Java`中的异常可以是程序执行时引发的，也可以是程序员通过`throw`语句手动抛出的；
- `Java`使用对应类型的异常对象来封装异常；

- `Throwable`类是`Java`异常类型的顶层父类，一个对象只有是`Throwable`类的实例，它才是一个异常对象，
- `JDK`中内建了一些常用的异常类，我们也可以自定义异常。

#### 二、异常的继承关系

![](.\images\异常继承图.png)

  由上面的继承树可以看到，`Throwable`这一顶层父类派生出`Error`和`Exception`两个子类：

- 错误：`Error`表示错误，通常由`JVM`自身造成，不能通过代码处理；
- 异常：`Exception`代表异常，可以被`Java`异常处理机制所处理，是异常处理的核心。

#### 三、`Java`异常分类

我们又可以将异常类分为2类，分别是检查式异常和非检查式异常。

**检查式异常：**又叫非运行时异常，这种异常在编写代码的时候`IDE`会提示。程序员需要对这样的异常做处理工作（使用`try…catch…finally`或者`throws`）。在方法中要么用`try-catch`捕获处理，要么用`throws`关键字声明抛出，否则编译不会通过。如`IOException , SQLException`等。

**非检查异常：**又叫运行时异常。`javac`在编译时，不会提示这样的异常，不要求在程序处理这些异常。我们可以编写代码处理（使用`try…catch…finally`）这样的异常，也可以不处理。对于这些异常，我们应该修正代码，而不是去通过异常处理器处理 。这样的异常大多是代码写的有问题。如`ArithmeticException，ClassCastException，ArrayIndexOutOfBoundsException，NullPointerException`等。

#### 四、异常示例  

使用下面这一例子演示算术运算异常：

```java
public class TestArithmeticException {
    public static void main(String[] args) {
        System.out.println("----欢迎使用命令行除法计算器----");
        CMDCalculate();
    }
    public static void CMDCalculate() {
        Scanner scanner = new Scanner(System.in);
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        int result = divide(num1, num2);
        System.out.println(result);
        scanner.close();
    }
    public static int divide(int num1, int num2) {
        return num1 / num2;
    }
}
/**
 * 当除数为0时，发生以下异常：
 * Exception in thread "main" java.lang.ArithmeticException: / by zero
 */
```

具体看下面这张图：

![](.\images\异常分析.png)

**分析过程：**

从上面的例子可以看出，当`divide`函数发生除0异常时，`divide`函数将抛出`ArithmeticException`异常，进而调用他的`CMDCalculate`函数也发生异常，而`CMDCalculate`函数的调用者`main`函数因为`CMDCalculate`抛出异常，也发生了异常，这样一直向调用栈的栈底回溯。这种行为叫做异常的冒泡，异常的冒泡是为了在当前发生异常的函数或者这个函数的调用者中找到最近的异常处理程序。由于这个例子中没有使用异常处理语句，因此异常最终由`main`函数抛给`JVM`，最终导致程序终止。

#### 五、异常处理方式

在编写代码处理异常时，对于检查式异常，有2种不同的处理方式：

- 使用`try…catch…finally`语句块处理；
- 在函数签名中使用`throws`关键字声明交给函数调用去解决。

##### 1、`try…catch…finally`语句块处理

```java
try{
    /**
     1)try块中放可能发生异常的代码;
     2)如果try块不发生异常，则执行finally块;
     3)如果发生异常，则执行catch块。
    */
}catch(SQLException e){
    /**
     1)每一个catch块用于捕获并处理一个异常,Java7中可以将多个异常声明在一个catch块中；
     2）catch后面的括号定义了异常类型和参数；
     3）在catch块中可以使用这个块的异常参数来获取异常的相关信息；
     4）如果try块中发生的异常在所有catch中都没捕获到，则去执行finally。
     */
}finally{
      /**
       1）无论是否发生异常，finally代码块总会执行；
       2）try不能单独使用，至少要有一个catch或finally块；
       3）finally块通常用来释放资源，如流的关闭，数据库连接的关闭等。 
       */
}
```

**注意点：**

1）`try`、`catch`以及`finally`中的局部变量，他们之间不可共享使用；

2）每一个`catch`块用于处理一个异常。异常匹配是按照`catch`块的顺序从上往下寻找的，只有第一个匹配的`catch`会得到执行。匹配时，支持父类匹配，因此，如果同一个`try`块下的多个`catch`异常类型有父子关系，应该将子类异常放在前面，父类异常放在后面，这样保证每个`catch`块都有存在的意义。

3）`java`中，异常处理的任务就是将执行控制流从异常发生的地方转移到能够处理这种异常的地方去。也就是说：当一个函数的某条语句发生异常时，这条语句的后面的语句不会再执行，它失去了焦点。执行流跳转到最近的匹配的异常处理`catch`代码块去执行，异常被处理完后，执行流会接着在“处理了这个异常的`catch`代码块”后面接着执行。

- 有的语言当异常被处理后，控制流会恢复到异常抛出点接着执行，这种策略叫做：恢复式异常处理模式 ；
- 而`Java`则是让执行流恢复到处理了异常的`catch`块后接着执行，这种策略叫做：终结式异常处理模式。

##### 2、`throws`声明异常

如果一个方法内部的代码会抛出检查式异常，而方法本身又没有完全处理掉，则必须在方法上使用`throws`关键字声明这些可能抛出的异常，否则编译不通过。

`throws`是另一种处理异常的方式，它不同于`try…catch…finally`，`throws`仅仅是将函数中可能出现的异常向调用者声明，而自己则不具体处理。

##### 3、`finally`代码块

`finally`块不管异常是否发生，只要对应的`try`执行了，则它一定也执行。只有一种方法让`finally`块不执行：`System.exit()`。因此`finally`块通常用来做资源释放操作：关闭文件，关闭数据库连接等。

良好的编程习惯是：在`try`块中打开资源，在`finally`块释放资源。

##### 4、`throw`抛出异常

程序员可以通过`throw`语句手动抛出一个异常，`throw`语句后面必须是一个具体的异常对象；

`throw`语句必须写在方法中，执行`throw`语句的地方就是一个异常抛出点。

```java
public void save(User user) {
        if (user == null) {
            //手动抛出不合法的参数异常
            throw new IllegalArgumentException("User对象为空");
        }
    }
```

#### 六、自定义异常

在实际的项目开中，可能会遇到一些特有的问题，而这些问题并未被`Java`封装成对象。所以对于这些特有的问题，可以按照`Java`封装的思想，进行自定义异常。

如果使用自定义异常类，继承`Exception`类即可。

**代码示例：**

需求：对于除数是负数的情况也视为异常。由于Java中没有针对这种问题的异常对象，这时就要使用自定义异常。

##### 1、自定义异常类，使其继承`Exception`类

```java
public class NegativeException extends Exception {
    //value用于保存发生异常的数
    private int value;
    //无参构造
    public NegativeException() {
        super();
    }
    //有参构造
    public NegativeException(String message, int value) {
        super(message);
        this.value = value;
    }
    //获取发生异常的值
    public int getValue() {
        return value;
    }
}
```

##### 2、编写程序，使用自定义异常类

```java
public class NegativeExceptionDemo {
    //除法运算，当除数小于0时，抛出异常
    public int div(int a, int b) throws NegativeException {
        if (b < 0) {
            throw new NegativeException("出现了除数为负数的情况", b);
        }
        return a / b;
    }
}
```

##### 3、测试

```java
public class TestNegativeException {
    public static void main(String[] args) {
        NegativeExceptionDemo d = new NegativeExceptionDemo();
        try {
            //调用div()方法，设置除数为负数
            int result = d.div(4, -1);
            System.out.println("result=" + result);
        } catch (NegativeException e) {
            System.out.println(e.toString() + "，异常的除数为：" + e.getValue());
        }
    }
}
```

**分析说明：**

1）使用`throw`抛出异常对象时，需要进行处理。要么`try...catch`处理，要么在函数上`throws`声明。

2）一般情况在，函数内出现异常，函数上需要声明；

3）自定义异常必须继承`Exception`类；

4）继承`Exception`原因：异常类和异常对象都具备可抛性；

5）`throws`使用在函数上，`throw`使用在函数内；

6）`throws`后的是异常类，可以跟多个，用逗号隔开；`throw`后跟的是异常对象。

#### 七、异常应用场景

参考[这篇文章](https://blog.csdn.net/youanyyou/article/details/106045355)

