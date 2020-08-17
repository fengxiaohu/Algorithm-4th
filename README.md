# Algorithm-4th

Algorithm 4th（算法第四版）读书笔记

## 愿景

此项目是为了记录一些在学习Algorithm-4th 这本书中的学习心得与体会。其中的文章也会同步发布到我的Blog中。 

Blog ： https://fengxiaohu.github.io/





## 语言

demo均使用Java，有关java的基础用法，可以参考https://www.runoob.com/java/java-tutorial.html



## 

## 


## 目录

- 快速开始

  ######  Java环境搭建

  此书有一个相应的配套网站https://algs4.cs.princeton.edu，
  内附了完整的搭建教程：https://algs4.cs.princeton.edu/code/。
  这份指南覆盖了macOS Linux Windows 三种操作系统。

  -  macOS：https://lift.cs.princeton.edu/java/mac/
  - Linux：https://lift.cs.princeton.edu/java/windows/
  - Win：https://lift.cs.princeton.edu/java/windows/

  这里以Windows 10 为例子，一步步说明如何配置学习环境。

  ###### Step0：一键安装

  https://lift.cs.princeton.edu/java/windows/lift-java-installer.exe

  可以使用官方提供的一键安装的工具，可以一键安装jdk，以及 IntelliJ IDEA 

  

  ###### Step1：配置Java 环境：

  首先确保你的电脑已经安装有正确的jdk，并将其加入环境变量。

  如果你还没有安装Java ，可以参考下面这篇教程：

  https://www.runoob.com/java/java-environment-setup.html

  为了测试是否正确安装了Java ，按下Win+R，输入cmd，打开命令行后分别输入

  ```
  java -version
  java version "1.8.0_241"
  Java(TM) SE Runtime Environment (build 1.8.0_241-b07)
  Java HotSpot(TM) 64-Bit Server VM (build 25.241-b07, mixed mode)
  
  javac -version
  javac 1.8.0_241
  ```

  如果正常安装了Java 则会显示相应的jre与jdk版本号

  ###### Step2 ：导入外部jar包

  下载algs4.jar:https://algs4.cs.princeton.edu/code/algs4.jar

  打开IDEA->Project structure->Modules->Dependencies  点击+号,导入下载的包

  ###### Step3 ：测试

  新建一个class 文件，命名为HelloWorld.java。运行下面测试程序。

  ```
  import edu.princeton.cs.algs4.StdOut;
  public class HelloWorld {
      public static void main(String[] args) {  
          Stdout.println("Hello, World");
      }
  }
  ```

  

  

- [基础](#基础)

  - [基础编程模型](#基础编程模型)
  - [数据抽象](#数据抽象)
  - [背包，队列，栈](#背包，队列，栈)
  - [基础算法分析](#算法分析)
  - [并查集（Union-find）算法](#并查集（Union-find）算法)
  - [编程规范](#编程规范)

- [排序](#排序)

  - [冒泡排序（Bubbling Sort）](#冒泡排序)
  - [插入排序（Insertion Sort）](#插入排序)
  - [选择排序（Selectio Sort）](#选择排序)
  - [冒泡排序（Bubbling Sort）](#冒泡排序)
  - [冒泡排序（Bubbling Sort）](#冒泡排序)
- [查找](#查找)
  - [符号表(Symbol table)](#符号表)
  - [二叉查找树(Binary search tree](#二叉查找树)
  - [平衡查找树(Balanced search tree)](#平衡查找树)
  - [散列表(Hash table)](#哈希表)
  - [相关应用(related application)](#应用) 
  
## 内容

### 查找：

- #### 符号表：
符号表主要是为了处理key-value（键值-数据）这样的数据存放结构存在的。这种数据结构就类似于字典一样，key代表了相关字符的索引，value则代表了字符的含义。在虚拟内存与操作系统的任务调度中有着广泛的应用。以下我们将遵循 测试，编码，分析算法这样的步骤进行。

###### API设计

| public class ST<Key extends Comparable<key>,Value> |                                    |      |
| -------------------------------------------------- | ---------------------------------- | ---- |
| ST()                                               | 初始化表                           |      |
| void put(Key key,Value value)                      | 将键值放入表中                     |      |
| void get(Key key)                                  | 通过相应的Key获取value的值         |      |
| void delete(Key key)                               | 删除相应的key及其value的值         |      |
| boolean contains(Key key)                          | key是否存在于表中                  |      |
| boolean isEmpty()                                  | 表是否为空                         |      |
| int size()                                         | 表中的键值对数量 阿斯顿            |      |
| Key min()                                          | 返回最小键                         |      |
| Key max()                                          | 返回最大键                         |      |
| Key floor(Key key)                                 | 向上取整（小于等于key的最大键）    |      |
| Key ceiling(Key key)                               | 向下取整（大于等于key的最小键）    |      |
| int rank(Key key)                                  | 小于key的键的数量                  |      |
| Key select(int k)                                  | 第k小的键                          |      |
| void deleteMin()                                   | 删除最小键                         |      |
| void deleteMax()                                   | 删除最大键                         |      |
| int size(Key lo,Key hi)                            | [lo,hi]之间键                      |      |
| Iterable<Key>  keys()                              | 表中所有的键，方便for-each循环使用 |      |
| Iterable<Key>  keys(lo,hi)                         | [lo,hi]之间键的集合                |      |

如上表所示，符号表中决定算法性能优劣的主要是在put(),与get()这两个函数的实现。各种方式实现符号表的成本总结如下表所示：

| 数据结构   | 算法       | 最坏情况下的算法复杂度（查找） | 最坏情况下的算法复杂度（插入） | 平均情况下的运行时间的增长数量级（查找） | 平均情况下的运行时间的增长数量级（插入） | 是否支持有序性相关的操作 |
| ---------- | ---------- | ------------------------------ | ------------------------------ | ---------------------------------------- | ---------------------------------------- | ------------------------ |
| 无序链表   | 顺序查找   | N                              | N                              | N/2                                      | N                                        | 否                       |
| 有序数组   | 二分查找   | lgN                            | N                              | lgN                                      | N/2                                      | 是                       |
| 二叉查找树 | 二叉树查找 | N                              | N                              | 1.39lgN                                  | 1.39lgN                                  | 是                       |

###### 测试用例设计

为了对算法的正确度与性能指标有统一的评估方式，我们在这里设计测试用例可以先从两方面来入手。

首先可以通过一个比较简单的demo来设计验证算法正确与否。

- ###### 算法行为评估用例设计

  ```java
      public static void main(String[] args) {
          ST<String, Integer> st = new ST<String, Integer>();
          for (int i = 0; !StdIn.isEmpty(); i++) {
              String key = StdIn.readString();
              st.put(key, i);
          }
          for (String s : st.keys())
              StdOut.println(s + " " + st.get(s));
      }
  }
  ```

  我们通过将输入的第i个字符与i关联起来，来构成一张最简单的符号表。

- ###### 算法性能测试用例设计

  众所周知，不同算法之间性能的差距会在输入数据量迅速增长的情况下呈现出天壤之别。我们通过下面这个程序首先来统计一段文字中单词出现的频率。

  ###### 数据链接

  ```
   *  Data files:   https://algs4.cs.princeton.edu/31elementary/tinyTale.txt
   *                https://algs4.cs.princeton.edu/31elementary/tale.txt
   *                https://algs4.cs.princeton.edu/31elementary/leipzig100K.txt
   *                https://algs4.cs.princeton.edu/31elementary/leipzig300K.txt
   *                https://algs4.cs.princeton.edu/31elementary/leipzig1M.txt
  ```

  ```java
  import edu.princeton.cs.algs4.ST;
  public class FrequencyCounter {
  
      private FrequencyCounter() { }
  
      public static void main(String[] args) {
          int distinct = 0, words = 0;
          int minlen = Integer.parseInt(args[0]);
          ST<String, Integer> st = new ST<String, Integer>();
  
          // compute frequency counts
          while (!StdIn.isEmpty()) {
              String key = StdIn.readString();
              if (key.length() < minlen) continue;
              words++;
              if (st.contains(key)) {
                  st.put(key, st.get(key) + 1);
              }
              else {
                  st.put(key, 1);
                  distinct++;
              }
          }
  
          // find a key with the highest frequency count
          String max = "";
          st.put(max, 0);
          for (String word : st.keys()) {
              if (st.get(word) > st.get(max))
                  max = word;
          }
  
          StdOut.println(max + " " + st.get(max));
          StdOut.println("distinct = " + distinct);
          StdOut.println("words    = " + words);
      }
  }
  ```

  ###### 执行方式

  在IDEA中run->edit configuration 中 分别在redirect input from  中输入重定向文件， program arguments中填写参数。 

  在命令行中可以使用

  ```bash
  java FrequencyCounter 10 < leipzig1M.txt
  ```

  如下图所示：
  ![image](https://github.com/fengxiaohu/Algorithm-4th-/blob/master/img/redirect img.PNG)

  ###### 输出

  ```java
  Connected to the target VM, address: '127.0.0.1:65245', transport: 'socket'
  government 262
  distinct = 7889
  words    = 16184
  Disconnected from the target VM, address: '127.0.0.1:65245', transport: 'socket'
  
  Process finished with exit code 0
  ```

  
  
