---
title: JUnit5入門(六)-按環境條件執行
date: 2021-02-03 13:45:58
category: JUnit5
tags: [JUnit5,Java]
index_img: /img/JUnit/JUnit5.png
---
![](/seawaterfoods/img/JUnit/TestConditions.png)
## 前言
JUnit5中的允許開發人員根據特定註解用來啟用或禁用測試。
這種情況的最簡單示例就是上一篇的@Disabled。
除了@Disabled之外，JUnit5還支持其他幾個基於註解的條件，這些條件允許開發時以聲明方式啟用或禁用測試。
<!-- more -->
限制條件的Annotations:

註解|描述
------|-------
@EnabledOnOs|指定1到多個作業系統，只有在當前作業系統為其中一個系統時，測試方法才會執行。
@DisabledOnOs|指定1到多個作業系統，當在作業系統為其中一個系統時，測試方法不執行。
@EnabledOnJre|指定1到多個JRE版本，只有在當前JRE版本為其中一個版本時，測試方法才會執行。
@DisabledOnJre|指定1到多個JRE版本，當JRE版本為其中一個版本時，測試方法不執行。
@EnabledForJreRange|指定JRE版本範圍，只有當前JRE版本在範圍中時，測試方法才會執行。
@DisabledForJreRange|指定JRE版本範圍，當JRE版本在範圍中時，測試方法不執行。
@EnabledIfSystemProperty|指定系統屬性的key和期望值(可模糊搜尋)，當key為期望值時，測試方法才可以執行。
@DisabledIfSystemProperty|指定系統屬性的key和期望值(可模糊搜尋)，當key為期望值時，測試方法不會執行。
@EnabledIfEnvironmentVariable|指定環境變數的key和期望值(可模糊搜尋)，當key為期望值時，測試方法才可以執行。
@DisabledIfEnvironmentVariable|指定環境變數的key和期望值(可模糊搜尋)，當key為期望值時，測試方法不會執行。
@EnabledIf|指定一個會回傳boolean的static類型，當該static類型回傳true時，測試方法才可以執行。JUnit 5.6版本已捨棄 @EnabledIf & @DisabledIf。
@DisabledIf|指定一個會回傳boolean的static類型，當該static類型回傳false時，測試方法才可以執行。JUnit 5.6版本已捨棄 @EnabledIf & @DisabledIf。

## 實際範例
```java
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

public class ConditionTest {
    //    當OS為WINDOWS或MAC時，測試方法才會執行。
    @Test
    @EnabledOnOs({OS.WINDOWS, OS.MAC})
    public void OSEqualsTest() {
        System.out.println(System.getProperty("os.name"));
    }

    //    當OS為MAC時，測試方法不執行。
    @Test
    @DisabledOnOs(OS.MAC)
    public void OSNotEqualsTest() {
        Assertions.assertNotEquals(OS.MAC, System.getProperty("os.name"), "OS is MAC!");
    }

    //    當JRE版本為8 or 9，測試方法才會執行。
    @Test
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9})
    public void JreEqualsTest() {
        System.out.println(System.getProperty("java.version"));
    }

    //  當JRE版本為9，測試方法不執行。
    @Test
    @DisabledOnJre(JRE.JAVA_9)
    public void JreNotEqualsTest() {
        Assertions.assertNotEquals(JRE.JAVA_9, System.getProperty("java.version"), "JRE is 9!");
    }

    //　當前JRE版本為8~9的範圍時，測試方法才會執行。
    @Test
    @EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_9)
    public void JreRangeEqualsTest() {
        System.out.println("JRE : " + System.getProperty("java.version"));
    }

    //  當前JRE版本為9~11的範圍中時，測試方法不執行。
    @Test
    @DisabledForJreRange(min = JRE.JAVA_9, max = JRE.JAVA_11)
    public void JreRangeNotEqualsTest() {
        System.out.println("JRE : " + System.getProperty("java.version"));
    }

    //    指定系統屬性:os.arch的期望值(可模糊搜尋)為".*64.*"，測試方法才可以執行。
    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    public void systemPropertyTest() {
        System.out.println("OS arch : " + System.getProperty("os.arch"));
    }

    //     指定系統屬性:java.vm.name的期望值(可模糊搜尋)為".*HotSpot.*"，測試方法不會執行。
    @Test
    @DisabledIfSystemProperty(named = "java.vm.name", matches = ".*HotSpot.*")
    public void systemPropertyNotEqualsTest() {
        System.out.println("java.vm.name : " + System.getProperty("java.vm.name"));
    }

    //    當環境變數:JAVA_HOME的期望值(可模糊搜尋)為 ".*"(非NULL)，測試方法才可以執行。
    @Test
    @EnabledIfEnvironmentVariable(named = "JAVA_HOME", matches = ".*")
    public void ifEnvironmentVariableHaveJAVA_HOMETest() {
        System.out.println("JAVA_HOME : " + System.getenv("USERNAME"));
    }

    //    當環境變數:GOPATH的期望值(可模糊搜尋)為 ".*"(非NULL)，測試方法不會執行。
    @Test
    @DisabledIfEnvironmentVariable(named = "GOPATH", matches = ".*")
    public void ifEnvironmentVariableHaveNotGOPATHTest() {
        System.out.println("GOPATH : " + System.getenv("GOPATH"));
    }

    //    指定trueCustomCondition的static方法，當該方法回傳true時，測試方法才可以執行。
    //     JUnit 5.6版本已捨棄 @EnabledIf & @DisabledIf
    @Test
    @EnabledIf("trueCustomCondition")
    public void staticTrueMethodTest() {
        System.out.println("trueCustomCondition : " + trueCustomCondition());
    }

    public static boolean trueCustomCondition() {
        return true;
    }

    //    指定falseCustomCondition的static方法，當該方法回傳false時，測試方法才可以執行。
    //     JUnit 5.6版本已捨棄 @EnabledIf & @DisabledIf
    @Test
    @DisabledIf("falseCustomCondition")
    public void staticFalseMethodTest() {
        System.out.println("falseCustomCondition : " + falseCustomCondition());
    }

    public static boolean falseCustomCondition() {
        return false;
    }
}
```
### 執行結果
![](/seawaterfoods/img/JUnit/ConditionTest.png)

## 結論
在實際測試時常常會有因為電腦環境不同或是有程式版本區分等等原因，導致測試失敗，而這些Annotation可以很快速的篩選出可以(或不可以)執行測試，方便排除因環境因素所造成的失敗。
## 參考資料
[junit5官方網站](https://rumenz.com/java-topic/junit5/expected-exception-example/index.html "junit5")<br/>
[JUnit5学习之四：按条件执行](https://blog.csdn.net/boling_cavalry/article/details/108909107 "Conditional Test Execution")<br/>