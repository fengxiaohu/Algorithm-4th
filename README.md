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
  
## 内容

### 查找：

- #### 符号表：

  
