#### Java日期相关内容介绍

##### 一、背景知识

世界上所有的计算机内部存储时间都使用一个`long`类型的整数，而这个整数的值就是相对于`UNIX`纪元时间（1970年1月1日0时0分0秒）的毫秒数。

##### 二、`Date`类

###### 1）两个构造方法

```java
public Date() {
    this(System.currentTimeMillis());
}

public Date(long date) {
    fastTime = date;
}
```

由以上两个构造方法可以看到，如果使用无参构造，则返回的是当前系统时间；如果使用有参构造，这里的 `fastTime`属性存储的是该时刻所对应的毫秒数。

###### 2）代码示例

```java
public static void main(String[] args) {
    //1000的单位为毫秒，1000毫秒为1秒
    Date date = new Date(1000);
    System.out.println(date);
}
/**
 * 输出结果：Thu Jan 01 08:00:01 CST 1970 
 * 格式化后：1970-1-1 8:00:01
 */
```

问题：在这里，1000 毫秒为1 秒，但为什么时间却多走了 8个小时？

答：这和「时区」有关，因为中国位于东八区，时间要早八个小时。

###### 3）说明

`Date`中很多方法的设计并不是很合理。所以，现在的`Date`类中很多方法都已废弃，被标记为 `@Deprecated`；

目前我们通常使用`Date`表示时刻，即应该更多关注它内部毫秒值的使用，而不是其他年月、时区等方面。

##### 三、`Calendar`类

###### 1）获取实例对象

```java
public abstract class Calendar implements Serializable, Cloneable, Comparable<Calendar>{
	//......
}
```

由`Calendar`的源码可以看到，它是一个抽象类，所以一般通过以下四种方法获取它的实例对象：

```java
public static Calendar getInstance(){}

public static Calendar getInstance(TimeZone zone){}

public static Calendar getInstance(Locale aLocale){}

public static Calendar getInstance(TimeZone zone,Locale aLocale){}
```

上面四个方法其实都是调用同一个内部方法：

```java
private static Calendar createCalendar(TimeZone zone,Locale aLocale){}
```

以上方法均可以在`Calendar`类的源码中找到。

该方法需要两个参数，一个是时区，一个是国家语言，也就是说，构建一个`Calendar`实例对象最少需要提供这两个参数信息，否则将会使用系统默认的时区或语言信息。

###### 2）常用方法

①`add(int field,int amount)`：`field`表示要操作的类型，`amount`表示偏移量，正数表示加，负数表示减；

②`setTime(Date date)`：给此日历设置时间；

③`get(int field)`：返回给定日历字段的值；

##### 四、`DateFormat`格式化日期

###### 1）获取实例对象

从之前的例子中可以看到，如果日期不经过格式化就输出，那样的结果很不直观，不容易看懂。幸好`DateFormat`

这个类就是用来处理日期格式问题的，`DateFormat`和`Calendar`一样，也是一个抽象类，我们需要通过工厂方式产生其实例对象，主要有以下几种工厂方法：

```java
//只处理时间的转换 
public final static DateFormat getTimeInstance(){}

//只处理日期的转换 
public final static DateFormat getDateInstance(){} 

//既可以处理时间，也可以处理日期 
public final static DateFormat getDateTimeInstance(){}
```

###### 2）两类方法

```java
public final String format(Date date){}

public Date parse(String source){}
```

`format`用于将一个日期对象格式化为字符串，`parse`用于将一个指定格式的字符串转换为一个日期对象。

###### 3）`SimpleDateFormat` 

`DateFormat`实例对象不能够自定义日期格式，即输出的字符串格式不能满足某些情况下的特殊需求。一般我们会使用它的一个实现类`SimpleDateFormat`进行日期的格式化处理；

`SimpleDateFormat`可以在构造实例对象时传入一个`String`类型的`pattern`参数用来指定日期格式；

代码示例：

```java
public static void main(String[] args) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年YY月dd日");
    System.out.println(sdf.format(new Date()));
}
//输出结果：2020年20月20日
```

###### 4）相关日期格式

- `yyyy`：年份表示形式；
- `MM`：月份表示形式；
- `dd`：日表示形式；
- `HH`：小时表示形式；
- `mm`：分钟表示形式；
- `ss`：秒表示形式；
- `E`：表示周几，`Locale`在中国会输出`星期x`；
- `a`：表示上午或下午。

说明：对于字符串转日期允许自定义格式，但必须遵守自己制定的格式，否则程序将无法成功解析。

