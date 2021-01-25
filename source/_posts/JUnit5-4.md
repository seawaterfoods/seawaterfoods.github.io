---
title: JUnit5入門(四)-假設
date: 2021-01-25 15:45:02
category: JUnit5
tags: [JUnit5,Java]
index_img: /img/JUnit/JUnit5.png
---
![](/seawaterfoods/img/JUnit/AssumptionsTest.jpg)

## 假設(Assumptions)
在開發過程中，有時會臨時取決於某些環境條件，導致可能會使一些測試失敗。
例如，金流SDK功能應可在任何操作系統上使用，但當前只能在Windows上使用。
為了允許開發人員像上面的功能一樣運行測試code，JUnit 5向我們提供Assumptions功能。
<!-- more -->

## Assumptions基本方法
```java
public class AssumptionsTest {
    Logger log =Logger.getLogger(AssumptionsTest.class.getName());
    
    @Test
    public void doScheduleLocaleNonUS() {

        // Assume that the current locale is US(假設locale是為US)
        Locale currentLocale = Locale.getDefault();
        Assumptions.assumeFalse(currentLocale.equals(Locale.TAIWAN), "Locale not need to set TAIWAN!");
        
        // Test Calculator(跑斷言)

        int num1 = 2;
        int num2 = 2;
        int expected1 = 4;
        int expected2 = 3;

        int actual = AssertionCalculator.add(num1, num2);

        Assertions.assertEquals(expected1, actual, "AssertionCalculator.add(" + num1 + "," + num2 + ") not equal " + expected1);
        Assertions.assertNotEquals(expected2, actual, "AssertionCalculator.add(" + num1 + "," + num2 + ") equal " + expected2);
    }
    @Test
    public void doScheduleLocaleNonTAIWAN() {

        // Assume that the current locale is TAIWAN(假設locale是為TAIWAN)
        Locale currentLocale = Locale.getDefault();
        Assumptions.assumeTrue(currentLocale.equals(Locale.TAIWAN), "Locale need to set TAIWAN!");

        // Test Calculator (跑斷言) 
        int num1 = 2;
        int num2 = 2;
        int expected1 = 4;
        int expected2 = 4;

        int actual = AssertionCalculator.add(num1, num2);

        Assertions.assertEquals(expected1, actual, "AssertionCalculator.add(" + num1 + "," + num2 + ") not equal " + expected1);
        Assertions.assertNotEquals(expected2, actual, "AssertionCalculator.add(" + num1 + "," + num2 + ") equal " + expected2);
    }
    @Test
    public void whenLocaleIsTAIWANLogMsgAndDoCalculator() {
//        如果assumingThat第一個參數為true就會執行Executable的execute方法，但是要注意assumingThat方法的特點：不拋出異常，因此其所在的方法不會被跳過，
//        這是和assumeTrue相比最大的區別(assumeTrue一旦參數為false就會拋出異常，其所在方法就被標記為跳過)：
        Locale currentLocale = Locale.getDefault();
        Assumptions.assumingThat(currentLocale.equals(Locale.TAIWAN),
                () -> {
                    log.info("當Locale是TAIWAN時才打印。");
                }
        );
        // Test Calculator
        int num1 = 2;
        int num2 = 2;
        int expected1 = 4;
        int expected2 = 3;

        int actual = AssertionCalculator.add(num1, num2);

        Assertions.assertEquals(expected1, actual, "AssertionCalculator.add(" + num1 + "," + num2 + ") not equal " + expected1);
        Assertions.assertNotEquals(expected2, actual, "AssertionCalculator.add(" + num1 + "," + num2 + ") equal " + expected2);
    }
}
```
![](/seawaterfoods/img/JUnit/AssumptionsResult.png)
## 結論
Assumptions主要是應用在設置前置條件的情況下，當前置條件不允許時，會直接中止這段測試程式。
Assumptions與Assertions(斷言)最大的不同在於Assumptions時失敗是測試直接中止，而Assertions時失敗是測試結果會跑出該項失敗。
## 參考資料
[junit5官方網站](https://junit.org/junit5/docs/current/user-guide/#writing-tests "junit5")<br/>
[JUnit5教程-简介](https://tonydeng.github.io/2017/10/09/junit-5-tutorial-introduction/ "JUnit5-1")<br/>
[JUnit 5 Assumptions Examples](https://rumenz.com/java-topic/junit5/junit-5-assumptions-examples/index.html "JUnit5–Assumptions")<br/>
