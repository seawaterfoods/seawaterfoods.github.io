---
title: JUnit5入門(二)-時序Annotations
date: 2021-01-19 11:15:53
category: JUnit5
tags: [JUnit5,Java]
index_img: /img/JUnit/JUnit5.png
---
![](/seawaterfoods/img/JUnit/JUnit5.png)
## 測試順序的Annotations
在JUnit中執行 @Test 時可以配置測試類別執行前、測試方法執行前、測試方法執行後以及測試類別執行後等等，資源事先配置與事後釋放之類用途的方法。
<!-- more -->
在下面同時列出JUnit5與JUnit4的Annotations:    

執行時機|JUnit5|JUnit4
------|-------|-------
測試類別執行前|@BeforeAll|@BeforeClass
測試方法執行前|@BeforeEach|@Before
測試方法執行後|@AfterEach|@After
測試類別執行後|@AfterAll|@AfterClass

## 實際測試
配置測試類別及方法，並實際測試如下:
```java
import org.junit.jupiter.api.*;

public class SequenceTest {
    
//    BeforeAll及AfterAll皆為static!
    @BeforeAll
    public static void beforeAll(){
        System.out.println("Sequence test beforeAll.");
    }

    @BeforeEach
    public void beforeEach(){
        System.out.println("-------------------------");
        System.out.println("Sequence test beforeEach.");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("Sequence test afterEach.");
        System.out.println("------------------------");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("Sequence test afterAll.");
    }

    @Test
    public void case1(){
        System.out.println("Case1.");
    }

    @Test
    public void case2(){
        System.out.println("Case2.");
    }
}
```

實際執行結果如下:
![](/seawaterfoods/img/JUnit/SequenceTest.png)

## 時序Annotation流程圖
執行的時間順序可以參考下圖

JUnit5|JUnit4
:-----:|:-----:
![](/seawaterfoods/img/JUnit/JUnit5SequenceFlowChart.png)|![](/seawaterfoods/img/JUnit/JUnit4SequenceFlowChart.png)


## 參考資料
[junit5官方網站](https://junit.org/junit5/docs/current/user-guide/#writing-tests "junit5")<br/>
[第二天 JUnit生命週期/執行順序](https://ithelp.ithome.com.tw/articles/10192896 "JUnit4-2")<br/>