---
title: JUnit5學習(八)-Composed Annotations
date: 2021-03-04 17:00:19
category: JUnit5
tags: [JUnit5,Java]
index_img: /img/JUnit/JUnit5.png
---
![](/seawaterfoods/img/JUnit/CustomizeAnnotation.jpg)
## 前言
JUnit5也是支持自定義註釋，可以創建自定義組成的註釋命名@Easy然後用來替代@Tag("easy")，甚至可以進一步採取自定義的@EasyTest，可以用來同時替代@Tag("easy")和@Test。
<!-- more -->
## 實際範例
自定義註解
```java
import java.lang.annotation.*;
import org.junit.jupiter.api.*;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Tag("hard")
@Test
public @interface HardTest {
}

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Tag("easy")
@Test
public @interface EasyTest {
}
```

實際應用
```java
import customizeAnnotation.EasyTest;
import customizeAnnotation.HardTest;
import org.junit.jupiter.api.*;

import java.util.logging.Logger;

public class CustomizeAnnotationTest {
    Logger log = Logger.getLogger(CustomizeAnnotationTest.class.getName());

    @HardTest
    @DisplayName("hard-1")
    void first1Test() {
        log.info("hardTest");
        Assertions.assertEquals(2, Math.addExact(1, 1));
    }
    @EasyTest
    @DisplayName("easy-1")
    void second1Test() {
        log.info("easyTest");
        Assertions.assertEquals(2, Math.addExact(1, 1));
    }
}
```

### 執行結果
如圖:
![](/seawaterfoods/img/JUnit/CustomizeAnnotationTest.png)

## 參考資料
[junit5官方網站](https://rumenz.com/java-topic/junit5/expected-exception-example/index.html "junit5")<br/>
[JUnit5学习之五：标签(Tag)和自定义注解](https://blog.csdn.net/boling_cavalry/article/details/108914091 "Tag & DisplayName")<br/>