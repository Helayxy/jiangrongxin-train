### Java集合框架介绍

#### 一、Java集合框架概述

集合可以看作是一种容器，用来存储对象。所有集合类都位于`java.util`包下，但支持多线程的集合类位于`java.util.concurrent`包下。

**数组与集合的区别**：

- 数组长度不可变而且无法保存具有映射关系的数据；集合长度可变，以可以保存具有映射关系的数据；
- 数组既可以存储基本数据类型和对象；集合只能存储对象。

#### 二、`Java`集合继承体系（Collection）

![](.\images\Collection集合.png)

**说明：**

- 上图中淡绿色背景表示的是一些重要实现类；
- `List、Queue、Set`三个子接口继承自`Collection`顶层接口。

##### 1、相关接口及重要实现类详解：

###### 1）`List`集合

`List`集合中存储的元素有序、可重复，集合中每个元素都有其对应的索引。`List`集合默认按照元素的添加顺序设置元素的索引，可以通过索引（类似数组的下标）来访问指定位置的集合元素；

该接口的重要实现类有：`ArrayList、LinkedList`。

**①`ArrayList`（重要）**

- 底层数据结构为数组；
- 查询快，增删慢；
- 线程不安全，效率高。

**②`LinkedList`（重要）**

`LinkedList`同时实现了`List`和`Deque`两个接口，使用`LinkedList`可以实现“栈”和“队列”数据结构。

- 底层数据结构为链表；
- 查询慢，增删快；
- 线程不安全，效率高。

**③`Vector`**

`Vector`其实就是一个线程安全的`ArrayList`，其中的方法被`synchronized`关键字所修饰，线程同步，效率低，不推荐使用。

###### 2）`Set`集合

Set集合最大的特点就是存储的元素唯一，为了帮助理解，请看下面代码示例：

```java
public class TestSet {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("hello world");
        set.add("江融信科技");
        set.add("江融信科技");
        System.out.println("集合中元素个数：" + set.size());
        System.out.println("集合中元素为：" + set.toString());
    }
}
输出结果：
集合中元素个数：2
集合中元素为：[hello world, 江融信科技]
```

由运行结果可见相同的元素“江融信科技”只存储了一个。

**①`HashSet`**

`HashSet`是`Set`接口最常用的实现类。使用hash算法存储元素，具有很好的存取和查找性能。

- 底层数据结构为哈希表；
- 存储元素无序，唯一；
- 依赖`hashCode()`和`equals()`两个方法保证元素唯一；
- 线程不安全，高效。

**②`TreeSet`**

`TreeSet`实现了`SortedSet`接口，顾名思义，`TreeSet`可以保证元素处于排序状态。

`TreeSet`支持两种排序方法：自然排序和定制排序，默认采用自然排序。

**自然排序：**`TreeSet`调用`compareTo(Object obj)`方法来比较元素的大小关系，然后将元素按照升序排列，这就是自然排序。如果将一个对象添加到`TreeSet`集合中，则该对象必须实现`Comparable`接口，否则会抛出异常。当一个对象调用方法与另一个对象比较时，例如`obj1.compareTo(obj2)`，如果该方法返回0，则两个对象相等；如果返回一个正数，则`obj1大于obj2`；如果返回一个负数，则`obj1小于obj2`。

**定制排序：**想要实现定制排序，需要在创建`TreeSet`集合对象时，提供一个`Comparator`对象与该`TreeSet`集合相关联，由`Comparator`对象负责集合元素的排序逻辑。

**综上：**自然排序实现的是`Comparable`接口，定制排序实现的是`Comparator`接口。

**`TreeSet`特点**

- 唯一，有序；
- 底层数据结构为红黑树；
- 利用自然排序和定制排序保证元素有序；
- 根据返回值是否为0来决定元素的唯一性。

##### 2、应用场景

```java
元素唯一吗？  	
	唯一：Set  		
		有序吗？  			
			有序：TreeSet或LinkedHashSet  			
			无序：HashSet  			
	不唯一：List  		
		线程安全吗？  			
			安全：Vector  			
			不安全：ArrayList或LinkedList  				
				查询多：ArrayList（最常用）			
				增删多：LinkedList
```

#### 三、`Map`集合介绍

`Map`采用键值对`K-V`的方式存储元素，保存具有映射关系的数据。因此，`Map`集合里保存两组值，一组值保存`key`，另外一组值保存`value`，`key`和`value`可以是任意引用数据类型。`key`值不允许重复，可以为`null`。如果添加`key-value`键值对时已有重复的`key`，则新添加的`value`会覆盖该`key`原来对应的`value`。

常用实现类有`HashMap、LinkedHashMap、TreeMap`等。

#### 四、`Map`集合继承体系

![](.\images\Map集合.png)

**说明：**Map集合是一个独立的集合分支，与Collection接口属于同一级别。

#### 五、常用方法（`API`文档）

![](.\images\Map-Api.png)

#### 六、重要实现类介绍

##### 1、`HashMap`与`Hashtable`

`HashTable`自`JDK1.0`就有，其中的方法都是同步的，效率低下，目前基本不用了。

`HashMap`与`Hashtable`主要存在以下两个典型区别：

- `HashMap`线程不安全，效率高；`HashTable`线程安全，效率低下；
- `HashMap`可以使用`null`值最为`key`或`value`；`Hashtable`不允许使用`null`值作为`key`和`value`，如果把`null`值放进`HashTable`中，将会发生空指针异常。

`HashMap`是最常使用的一个`Map`实现类，不能保证线程安全；如果要求线程安全，使用`ConcurrentHashMap`。

关于`HashMap`的详细介绍参考[这篇文章](https://juejin.im/post/5e817b886fb9a03c7d3cee7c)

##### 2、`TreeMap`

`TreeMap`是`SortedMap`的实现类，底层数据结构为红黑树，每个`key-value`键值对对作为红黑树的一个节点。

`TreeMap`存储元素时，需要根据`key`对节点进行排序，排序方式为自然排序和定制排序。

- 自然排序：要求`TreeMap`的所有`key`必须实现`Comparable`接口，而且所有的`key`应该是同一个类的对象，否则会抛出`ClassCastException`异常。
- 定制排序：要求创建`TreeMap`时，传入一个Comparator对象，该对象负责对TreeMap中所有`key`进行排序。

#### 七、各Map实现类性能比较

- `HashMap`比`Hashtable`要快；
- `TreeMap`通常比`HashMap、Hashtable`要慢，因为`TreeMap`底层采用红黑树来管理`key-value`；
- `LinkedHashMap`比`HashMap`慢一点，因为它需要维护链表来保证`key-value`的插入顺序。

