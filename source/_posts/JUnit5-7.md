---
title: JUnit5學習(七)-@Tag與@DisplayName
date: 2021-02-17 15:40:49
category: JUnit5
tags: [JUnit5,Java]
index_img: /img/JUnit/JUnit5.png
---
![](/seawaterfoods/img/JUnit/Tags.png)
## 前言
可以通過@Tag註釋來標記測試類和方法。 這些標籤以後可以用來過濾測試執行。
<!-- more -->
Tag語法規則:
Tag不能為null或為空。
一個trimmed tag不能包含空格。
一個trimmed tag不得包含ISO控制字符。
一個trimmed tag不得包含以下任何保留字符。
> ,：逗號
> (：左括號
> )：右括號
> &：連字號
> |：豎線
> !：感嘆號

1. 可以在測試class或測試method(或兩者同時)使用@Tag。
2. 也可以在單一測試method中應用多個tag。

註解|描述
------|-------
@Tag|可用於從測試計劃中過濾測試用例。它可以幫助針對不同的環境，不同的用例或任何特定要求創建多個不同的測試計劃。您可以通過僅在測試計劃中包括那些標記的測試或通過從測試計劃中排除其他測試來執行測試集。
@Tags|可用於添加多個@Tag在其中。
@DisplayName|用於為測試類或測試方法提供任何自定義顯示名稱。

### Tag表達式
在以Tag來篩選時可以編輯複雜的篩選邏輯:

運算子|描述|舉例
:------:|:-------:|:-----:
\& | 和 | important & easy
\! | 非 | important ! easy
\| | 或 | important \| easy

## 實際範例
```java
//可以將Tag放置class上
@Tag("first")
public class TagFirstTest {
    Logger log = Logger.getLogger(TagFirstTest.class.getName());

//設置多個Tag時可以包在Tags中
    @Test
    @Tags({@Tag("easy"),@Tag("important")})
    @DisplayName("first-1")
    void first1Test() {
        log.info("first1Test");
        Assertions.assertEquals(2, Math.addExact(1, 1));
    }

    @Test
    @DisplayName("first-2")
    void first2Test() {
        log.info("first2Test");
        Assertions.assertEquals(2, Math.addExact(1, 1));
    }

    @Test
    @Tag("hard")
    @DisplayName("first-3")
    void first3Test() {
        log.info("first3Test");
        Assertions.assertEquals(2, Math.addExact(1, 1));
    }
}

@Tag("second")
public class TagSecondTest {
    Logger log = Logger.getLogger(TagSecondTest.class.getName());

    @Test
    @DisplayName("second-1")
    void second1Test() {
        log.info("second1Test");
        Assertions.assertEquals(2, Math.addExact(1, 1));
    }

    @Test
    @Tag("easy")
    @DisplayName("second-2")
    void second2Test() {
        log.info("second2Test");
        Assertions.assertEquals(2, Math.addExact(1, 1));
    }

//設置多個Tag時除了可以包在Tags中之外，也可以將@Tag直接設置
    @Test
    @Tag("hard")
    @Tag("important")
    @DisplayName("second-3")
    void second3Test() {
        log.info("second3Test");
        Assertions.assertEquals(2, Math.addExact(1, 1));
    }
}

//當然Tags也可以直接設置在class上
@Tags({@Tag("easy"),@Tag("hard")})
public class TagThirdTest {
    Logger log = Logger.getLogger(TagThirdTest.class.getName());

    @Test
    @Tag("important")
    @DisplayName("third-1")
    void third1Test() {
        log.info("third1Test");
        Assertions.assertEquals(2, Math.addExact(1, 1));
    }

    @Test
    @Tag("production")
    @DisplayName("third-2")
    void third2Test() {
        log.info("third2Test");
        Assertions.assertEquals(2, Math.addExact(1, 1));
    }
}

```

## IntelliJ(2020.3.2)自訂Tag測試執行
1. 先Edit Configurations編輯設置。
   ![](/seawaterfoods/img/JUnit/TagTestSetStep1.png)
   
2. 然後建立新的JUnit配置。
   ![](/seawaterfoods/img/JUnit/TagTestSetStep2.png)
   
3. 最後選擇設置測試類型為Tags，並且在表達式中填寫過濾規則。
   ![](/seawaterfoods/img/JUnit/TagTestSetStep3.png)

### 執行結果
所有"easy"的Tag都會執行，如圖:
![](/seawaterfoods/img/JUnit/TagTest.png)

## 參考資料
[junit5官方網站](https://rumenz.com/java-topic/junit5/expected-exception-example/index.html "junit5")<br/>
[JUnit5学习之五：标签(Tag)和自定义注解](https://blog.csdn.net/boling_cavalry/article/details/108914091 "Tag & DisplayName")<br/>