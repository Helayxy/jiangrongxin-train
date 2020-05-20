#### `Java8`中日期相关操作

##### 一、背景

由于`Java8`之前的日期处理的相关接口设计的并不是很合理，且象`Date`类中很多方法已经启用。所以，在`Java8`中借鉴第三方优秀开源库`Joda-time`，重新设计了一套`API`，下面开始我们的学习。

##### 二、`Instant`VS`Date`

- `Instant`和`Date`一样，用于描述一个时刻，只不过较`Date`而言，`Instant`可以描述更加精确的时刻，并且 `Instant`与时区无关；
- `Date`最多可以表示毫秒级别的时刻，而`Instant`可以表示纳秒级别的时刻；

##### 三、`LocalDate`

###### 1）创建实例对象

`Calendar`既能处理日期又能处理时间，但在`Java8`中，分别用不同的类进行处理日期和时间。其中`LocalDate`就专注于处理日期；

`LocalDate`类被`final`关键字所修饰，是一个不可变类，它关注时间中**年月日**部分；

代码示例：

```java
public static void main(String[] args) {
    //截断当前系统时间的年月日信息并初始化一个实例对象
    LocalDate date1 = LocalDate.now();
    System.out.println(date1);

    //显式指定年月日信息
    LocalDate date2 = LocalDate.of(2020, 5, 20);
    System.out.println(date2);

    //根据dayOfYear可以推出month和dayOfMonth
    LocalDate date3 = LocalDate.ofYearDay(2020, 20);
    System.out.println(date3);

    //相对于格林零时区时间的日偏移量
    LocalDate date4 = LocalDate.ofEpochDay(10);
    System.out.println(date4);
}
/**
 * 输出结果：
 * 2020-05-20
 * 2020-05-20
 * 2020-01-20
 * 1970-01-11
 */
```

**注意：`LocalDate`会根据系统中当前时刻和默认时区计算出年月日的信息。**

###### 2）常用方法介绍

- `public int getYear()`：获取年份；
- `public int getMonthValue()`：获取月份；
- `public int getDayOfMonth()`：获取当前日是这个月的第几天；
- `public int getDayOfYear()`：获取当前日是这一年的第几天；
- `public boolean isLeapYear()`：判断是否是闰年；
- `public int lengthOfYear()`：获取这一年有多少天；
- `public DayOfWeek getDayOfWeek()`：返回星期信息。

##### 四、`LocalTime`

`LocalTime`专注于时间的处理，它提供小时，分钟，秒，毫微秒的各种处理；

创建实例对象和相关方法的使用和`LocalDate`类似，较为简单，这里不再赘述。

##### 五、`ZonedDateTime`

该类多用于与时区相关的操作。

##### 六、`DateTimeFormatter` 

`Java8`中，`DateTimeFormatter`作为格式化日期时间的主要类，它与之前的`DateFormat`类最大的不同就在于它是线程安全的，其他的使用上的操作基本类似。

##### 七、时间差的处理

实际项目中，可能经常会遇到计算时间差的情况，最粗暴的办法是，全部幻化成毫秒数并进行减法运算，最后转换回日期时间对象。

但是`java.time`包中提供了计算时间差的方法，关于时间差的计算，主要涉及到两个类：

- `Period`：处理两个日期之间的差值；
- `Duration`：处理两个时间之间的差值；

代码示例：

```java
public static void main(String[] args) {
    //指定日期
    LocalDate date1 = LocalDate.of(2018, 3, 18);
    //系统当前日期
    LocalDate date2 = LocalDate.now();
    //计算两个日期的差值
    Period period = Period.between(date1, date2);
    System.out.println(period.getYears() + "年" + period.getMonths() + "月" + period.getDays() + "天");

    //指定时间
    LocalTime time1 = LocalTime.of(20, 30);
    //指定时间
    LocalTime time2 = LocalTime.of(20, 59);
    //计算两个时间的差值
    Duration duration = Duration.between(time1, time2);
    System.out.println(duration.toMinutes() + "分钟");
}
/**
 * 输出结果：
 * 2年2月2天
 * 29分钟
 */
```

以上就是`Java8`中关于日期的相关操作，相比于`Java8`之前的日期时间操作，改进了许多，也更为合理和简单。

