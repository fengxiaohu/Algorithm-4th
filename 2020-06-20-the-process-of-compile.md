---
title: the process of compile
date: 2020-06-20 21:57:53
categories : CSAPP

---

# c程序的编译过程

[TOC]



## Overview

计算机语言可以分为机器语言，汇编语言，高级语言三种

- 机器语言：顾名思义，机器语言是一系列二进制所组成的文件。可以直接被硬件所执行。
- 汇编语言 汇编语言是处于机器语言与高级语言中间的一层。他的抽象程度比机器语言高，但是却比高级语言要低。
- 高级语言 抽象层次比较高，方便阅读

<!--more-->

## 安装GCC编译器

我们在下面过程中使用的gcc编译器属于GNU项目的一部分。GNU项目最初的目的是为了开发一个完整的类似于Unix的系统。
GNU项目包括了EMACS编辑器，GCC编译器 GDB调试器 汇编器，链接器，等等。GNU环境与我们所熟知的linux内核一起组成了我们熟悉的linux。
windows下安装GCC https://www.jianshu.com/p/ff24a81f3637
Linux 下安装GCC https://www.linuxidc.com/Linux/2019-06/159059.htm




## 编译过程

{% asset_img compile.jpg compile system %}

程序的编译过程通常有可以分为以下四个步骤，我们会以helloworld作为例子来向大家展示

```c
include <stdio.h>
int main()
{
    printf("Hello world");
    return 0;
}
```



- 预处理阶段

  预处理器(cpp) 根据以#开头的字符来修改原始的C程序，主要会有以下的处理过程

  - 删除#define，展开相应的宏定义。处理一些预编译指令
  - 处理广为人知的#include指令。如上面所示，这里会将读取#include <stdio.h> ，告诉预处理器来读取系统的stdio.h头文件，并且将其直接插入到程序中进行扩展
  - 删除注释，添加行号与文件标注

- 编译阶段

  编译器将文件hello.i 翻译为hello.s ，进一步地它包含了一个汇编语言程序

  ```assembly
  	.file	"hello.c"
  	.text
  	.section	.rodata
  .LC0:
  	.string	"Hello world"
  	.text
  	.globl	main
  	.type	main, @function
  main:
  .LFB0:
  	.cfi_startproc
  	pushq	%rbp
  	.cfi_def_cfa_offset 16
  	.cfi_offset 6, -16
  	movq	%rsp, %rbp
  	.cfi_def_cfa_register 6
  	leaq	.LC0(%rip), %rdi
  	movl	$0, %eax
  	call	printf@PLT
  	movl	$0, %eax
  	popq	%rbp
  	.cfi_def_cfa 7, 8
  	ret
  	.cfi_endproc
  .LFE0:
  	.size	main, .-main
  	.ident	"GCC: (Ubuntu 7.5.0-3ubuntu1~18.04) 7.5.0"
  	.section	.note.GNU-stack,"",@progbits
  
  ```

  其中主要被机器所识别的部分是

  ```assembly
  main:
  .LFB0:
  	.cfi_startproc
  	pushq	%rbp
  	.cfi_def_cfa_offset 16
  	.cfi_offset 6, -16
  	movq	%rsp, %rbp
  	.cfi_def_cfa_register 6
  	leaq	.LC0(%rip), %rdi
  	movl	$0, %eax
  	call	printf@PLT
  	movl	$0, %eax
  	popq	%rbp
  	.cfi_def_cfa 7, 8
  	ret
  	.cfi_endproc
  ```

  大家可能会疑惑了，为什么从高级语言跳转到机器语言呢，这样中间省略了一部分不是会让程序的运行速度更快么？实际上这里采用汇编语言作为中间层可以向上兼容许多不同的高级语言如同C++，Java等。这些高级语言经过不同的编译器后实际上会输出相同的汇编语言。

- 汇编阶段

  汇编器将hello.s 翻译为机器语言。由于每条机器指令几乎都对应了相应的汇编指令，因此我们可以通过类似查字典的方式将它逐条翻译为机器指令。通过一种可重定位目标程序（relocatable object program）的格式打包在一起，最终将结果存在hello.o

- 链接阶段