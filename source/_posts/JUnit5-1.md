---
title: JUnit5入門(一)-前言
date: 2021-01-18 10:56:50
category: JUnit5
tags: [JUnit5,Java]
index_img: /img/JUnit/JUnit5.png
---
![](/seawaterfoods/img/JUnit/JUnit5.png)

## 前言
寫測試(Testing)在開發程式中一直是非常重要的一環，測試主要是驗證程式的正確性、完整性、安全性等等許多品質最簡單的方式 ，畢竟總不可能為了測試後端程式邏輯修改後，在前端呈現是否正確而不斷重新編譯後重啟伺服器來驗證； 
在Java中JUnit是最為常用的測試框架，它可以做單元測試(Unit Testing)、整合測試(Integrated Testing)、系統測試(System Testing)、壓力測試(Stress Testing)、使用者接受度測試 (User Acceptance Testing) ...等等；
而這系列文章主要是學習JUnit於2017年9月發布的JUnit5，從單元測試開始學習如何寫測試。
<!-- more -->

## JUnit5入門準備
- 版本配置
>+ JDK版本：1.8
>+ IDE：Intellij IDEA 2020.3.2
>+ maven：3.6.2    

![](/seawaterfoods/img/JUnit/FolderConfiguration.png)    
<br />
- pom.xml
```xml
<!-- ... -->
<dependencies>
    <!-- ... -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.7.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.7.0</version>
        <scope>test</scope>
    </dependency>
    <!-- ... -->
</dependencies>
<!-- ... -->
```

## 建立測試範例
1. 建立測試類別
   一般而言測試檔案會建立於專案的路徑src/test/java中分門別類整理好，那我們先直接建立一個測試類別：
``` java
import org.junit.jupiter.api.Test;

public class firstTest {
    @Test
    public void Case1(){
        System.out.println("print Case1.");
    }
}
```
測試程式必須為public且測試類別路徑一般與待測類別路件相同。
- 待測類別src/main/java：com/joe/basic/First.java
- 測試類別src/test/java：com/joe/basic/FirstTest.java

2. 加入測試案例(Test Case)
   將@Test標註在public void方法上
``` java
import org.junit.jupiter.api.Test;

public class FirstTest {
    @Test
    public void Case1(){
        System.out.println("print Case1.");
    }
    @Test
    public void Case2(){
        System.out.println("print Case2.");
    }
}
```
## 執行測試
![](/seawaterfoods/img/JUnit/RunFirstTest.png)
在Intellij中可以直接在類別上執行其中所有方法。

![](/seawaterfoods/img/JUnit/RunCase1.png)
或是在單一案例上測試執行

![](/seawaterfoods/img/JUnit/FirstResult.png)
Run可查看所有結果。

## 參考資料
[第一天 JUnit入門](https://ithelp.ithome.com.tw/articles/10192367 "JUnit4-1")<br/>