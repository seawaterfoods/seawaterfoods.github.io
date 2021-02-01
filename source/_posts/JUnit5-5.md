---
title: JUnit5入門(五)-測試Annotations
date: 2021-02-01 15:17:13
category: JUnit5
tags: [JUnit5,Java]
index_img: /img/JUnit/JUnit5.png
---
![](/seawaterfoods/img/JUnit/TestAnnotations.png)
## 用於各種測試的Annotations
JUnit5中除了最基本的@Test可以執行測試操作，還有其他Annotation可以使用，像是@ParameterizedTest(參數化測試)或是@RepeatedTest(重複測試)...等等，在下面會伴隨著範例去了解使用方法。
<!-- more -->
在下面同時列出JUnit5與JUnit4的Annotations:

註解|描述
------|-------
@Test|表示方法是測試方法。
@Disabled|禁用該測試或禁用特定某項測試方法，類似於JUnit4的@Ignore。
@RepeatedTest|表示方法是重複測試的測試模板。通過@RepeatedTest並指定所需的重複總數，從而提供了將測試重複指定次數的功能。每次重複測試的行為都視同@Test方法的執行。
@ParameterizedTest|表示方法是參數化測試。通過@ParameterizedTest並聲明至少一個source，該source將會為每次的調用提供參數，可以使用不同的參數已用了進行多次測試。

## 實際範例
配置測試類別及方法，並實際測試如下:
```java
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

//@Disabled //如果使用@Disabled的話，這一類別都不會顯示於測試中。
public class TestAnnotationsTest {
    @Disabled
    @Test
    void DisabledCase()
    {
        System.out.println("You should not see the test");
    }

    //	RepeatedTest(重複次數)
    @RepeatedTest(5)
    public void repeatedTestCase1() {
        System.out.println("Repeated Test Case1.");
    }

    /*	RepeatedTest(value=重複次數, name="自定義顯示名稱")
     *  {displayName}:設定顯示的名稱, {totalRepetitions}:重複的總數, {currentRepetition}:當前重複的計數
     */
    @DisplayName("Repeated Test Case2")
    @RepeatedTest(value = 5, name = "{displayName} - repetition {currentRepetition} of {totalRepetitions}")
    public void repeatedTestCase2() {
        System.out.println("Repeated Test Case2.");
    }


    /*	當輸入@ParameterizedTest時可以用name自訂測試名稱EX:@ParameterizedTest(name="執行序號 [{index}], String參數 [{0}],int參數 [{1}]")，
     *   之後必須使用以下的數據源註解來導入參數:
     *	@NullSource可以調用null當一次輸入參數。
     *  @EmptySource可以調用空字串當一次輸入參數。
     *	@ValueSource可以直接輸入單一類別參數。EX:strings:String, shorts:short, bytes:byte, ints:int, longs:long, floats:float, doubles:double, chars:char, booleans:boolean,classeses:classes
     *	@MethodSource來指定一個方法名後用該方法的回傳來輸入參數，該回傳必須為Steam類型。
     *	@EnumSource來指定Enum當輸入參數，必須在方法的輸入參數類型中知道要調用哪個enum。EX:enum名稱為Types則在測試方法中為public void ParameterizedTest(Types type)
     *  @CsvSource使用Csv來當輸入參數。EX:@CsvSource({"apple1, 11", "banana1, 12","'lemon1, lime1', 0x0A"})
     *	@CsvFileSource調用外部csv文件來當輸入參數，並且可以用numLinesToSkip指定跳過的行數。EX:@CsvFileSource(files = "src/test/resources/two-column.csv", numLinesToSkip = 1)
     *	@ArgumentsSource自定義參數來源 @ArgumentsSource(MyArgumentsProvider.class)。
     */
    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = { "a", "b", "c" } )
    public void ParameterizedTest(String testString) {
        System.out.printf("Parameterized Test Basic Case. string : %s" ,testString);
        System.out.println();
    }
}
```

實際執行結果如下:
![](/seawaterfoods/img/JUnit/TestAnnotationsTest.png)

## 結論
這上面舉例的部分都是當要調用許多重複測試的方法，比較要注意的是 @ValueSource **只能** 輸入單一類別的參數，當想要同時輸入多個不同參數類別的話可以多多使用 @CsvSource 以csv格式的不同欄位來實現測試需求。

## 參考資料
[junit5官方網站](https://junit.org/junit5/docs/current/user-guide/#writing-tests "junit5")<br/>
[@RepeatedTest Annotation Example](https://rumenz.com/java-topic/junit5/repeated-test-annotation-example/index.html "RepeatedTest")<br/>
[JUnit5学习之六：参数化测试(Parameterized Tests)基础](https://blog.csdn.net/boling_cavalry/article/details/108930987 "ParameterizedTest Basic")<br/>
[JUnit5学习之七：参数化测试(Parameterized Tests)进阶](https://blog.csdn.net/boling_cavalry/article/details/108930987 "ParameterizedTest Advanced")<br/>