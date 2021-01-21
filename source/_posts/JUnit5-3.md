
---
title: JUnit5入門(三)-斷言
date: 2021-01-21 13:39:50
category: JUnit5
tags: [JUnit5,Java]
index_img: /img/JUnit/JUnit5.png
---
![](/seawaterfoods/img/JUnit/AssertionsTest.jpg)

## 斷言(Assertions)
斷言簡單來說就是驗證執行該程式是否為預期的效果，若是有錯誤則會fail；    
在JUnit4中斷言套件需導入org.assertj，而在JUnit5則是直接內建於org.junit.jupiter.api。
<!-- more -->

## Assertions基本原則
在撰寫單元測試的程式碼時，有個3A原則全名為：Arrange Act Assert 這三個步驟是寫單元測試的 pattern，來輔助設計測試程式，可以讓測試程式更好懂。
>**3A原則(Arrange、Act、Assert)**
> 
>**1. Arrange**: 初始化目標物件、相依物件、方法參數、預期結果(excepted)    
>**2. Act**: 調用目標物件及欲測試的方法    
>**3. Assert**: 驗證結果(actual)是否符合預期    

![](/seawaterfoods/img/JUnit/A.jpg)
### 實踐3A原則
```java
public class ThreeATest {

    @Test
    public void ThreeACase() {
		// 1. Arrange 先初始化
        AssertionCalculator calculator = new AssertionCalculator();
        int num1 = 2;
        int num2 = 2;
        int excepted = 4;
		
        // 2. Act 調用方法
        int actual = calculator.add(number1, number2);

        // 3. Assert 驗證結果
        Assertions.assertEquals(actual, excepted, "calculator.add test failed: "+num1+"+"+num2+"="+actual+" ,excepted: "+excepted);
    }
}
```


## Assertions基本方法
1. assertEquals() & assertNotEquals() : 斷言物件(參數)是否與預期結果。    
2. assertTrue() & assertFalse() : 斷言該boolean是否為預期結果。    
3. assertNull() & assertNotNull() : 斷言該物件null狀態。    
4. assertSame() & assertNotSame() : 斷言物件引用對象是否為預期結果(相同與否，類似判斷參考記憶體是否相同)。    
5. assertTimeout() & assertTimeoutPreemptively() : 斷言是否超時。    
6. assertThrows() & assertDoesNotThrow() : 斷言拋出錯誤正確與否或斷言沒有拋錯。    
7. assertArrayEquals() : 斷言Array中物件與順序是否完全一致。    
8. assertIterableEquals : 斷言迭代中物件與順序是否完全一致。    
9. assertLinesMatch : 斷言<String>列表是否完全一致。    
10. assertAll : 可以多重斷言，使用Java8 Lambda寫法，跟JUnit4的SoftAssert類似。    
11. fail : 無論斷言結果如何，都算執行斷言失敗。    


### 實踐Assertions基本方法
先應用上一章所講的@BeforeAll，初始化部分參數。
```java
public class AssertionsTest {

    static int[] actualIntArr;
    static int[] expectedIntArr;
    static List<String> actualStrList;
    static List<String> expectedStrList;

    @BeforeAll
    public static void init() {
        actualIntArr = new int[]{1, 2, 3, 4};
        expectedIntArr = new int[]{1, 2, 3, 4};

        actualStrList = new ArrayList<>();
        expectedStrList = new ArrayList<>();
        actualStrList.add("1");
        actualStrList.add("2");
        actualStrList.add("3");
        actualStrList.add("4");

        expectedStrList.add("1");
        expectedStrList.add("2");
        expectedStrList.add("3");
        expectedStrList.add("4");
    }
```
### 1. assertEquals() & assertNotEquals()
>- assertEquals() : 斷言物件(或基本參數)相同。
>- assertNotEquals() : 斷言物件(或基本參數)不同。
```java
    @Test
    public void objectComparisonCase() {
//        public static void assertEquals(Object expected, Object actual)
//        public static void assertEquals(Object expected, Object actual, String message)
//        public static void assertEquals(Object expected, Object actual, Supplier<String< messageSupplier)
//        public static void assertNotEquals(Object expected, Object actual)
//        public static void assertNotEquals(Object expected, Object actual, String message)
//        public static void assertNotEquals(Object expected, Object actual, Supplier<String> messageSupplier)

        int num1 = 2;
        int num2 = 2;
        int expected1 = 4;
        int expected2 = 3;

        int actual = AssertionCalculator.add(num1, num2);

        //the following will succeed
        Assertions.assertEquals(expected1, actual, "AssertionCalculator.add(" + num1 + "," + num2 + ") not equal " + expected1);
        
        //the following will succeed
        Assertions.assertNotEquals(expected2, actual, "AssertionCalculator.add(" + num1 + "," + num2 + ") equal " + expected2);

    }

```
### 2. assertTrue() & assertFalse()
>- assertTrue() : 斷言該boolean為true。
>- assertFalse() : 斷言該boolean為false。

```java
    @Test
    public void directJudgmentCase() {
//        public static void assertTrue(boolean condition)
//        public static void assertTrue(boolean condition, String message)
//        public static void assertTrue(boolean condition, Supplier<String> messageSupplier)
//        public static void assertTrue(BooleanSupplier booleanSupplier)
//        public static void assertTrue(BooleanSupplier booleanSupplier, String message)
//        public static void assertTrue(BooleanSupplier booleanSupplier, Supplier<String> messageSupplier)
//        public static void assertFalse(boolean condition)
//        public static void assertFalse(boolean condition, String message)
//        public static void assertFalse(boolean condition, Supplier<String> messageSupplier)
//        public static void assertFalse(BooleanSupplier booleanSupplier)
//        public static void assertFalse(BooleanSupplier booleanSupplier, String message)
//        public static void assertFalse(BooleanSupplier booleanSupplier, Supplier<String> messageSupplier)

        int num1 = 2;
        int num2 = 2;
        int expected1 = 4;
        int expected2 = 3;

        int actual = AssertionCalculator.add(num1, num2);

        //the following will succeed
        Assertions.assertTrue(expected1 == actual, "AssertionCalculator.add(" + num1 + "," + num2 + ") == " + expected1 + "is false");

        //the following will succeed 
        Assertions.assertFalse(expected2 == actual, "AssertionCalculator.add(" + num1 + "," + num2 + ") == " + expected2 + "is true");

    }

```
### 3. assertNull() & assertNotNull()
>- assertNull() : 斷言該物件為Null。
>- assertNotNull() : 斷言該物件不為Null。
```java
    @Test
    public void nullJudgmentCase() {
//        public static void assertNotNull(Object actual)
//        public static void assertNotNull(Object actual, String message)
//        public static void assertNotNull(Object actual, Supplier<String> messageSupplier)
//        public static void assertEquals(Object actual)
//        public static void assertEquals(Object actual, String message)
//        public static void assertEquals(Object actual, Supplier<String> messageSupplier)

        int num1 = 2;
        int num2 = 2;

        int actual = AssertionCalculator.add(num1, num2);

        //the following will succeed
        Assertions.assertNotNull(actual, "AssertionCalculator.add(" + num1 + "," + num2 + ") is null");

        //the following will succeed
        Object nullActual = null;
        Assertions.assertNull(nullActual, "nullActual is not null");
    }

```
### 4. assertSameCase() & assertNotSame()
>- assertSame() : 斷言所預期引用對象的和實際引用的完全相同。
>- assertNotSame() : 斷言預期引用對象和實際引用不是同一對象。
```java
    @Test
    public void assertSameCase() {
//        public static void assertNotSame(Object unexpected,　Object actual)
//        public static void assertNotSame(Object unexpected,　Object actual, String message)
//        public static void assertNotSame(Object unexpected,　Object actual, Supplier<> messageSupplier)
//        public static void assertSame(Object expected, Object actual)
//        public static void assertSame(Object expected, Object actual, String message)
//        public static void assertSameObject expected, (Object actual, Supplier<String> messageSupplier)

        List<String> cloneStrList = actualStrList;

        //the following will succeed
        Assertions.assertSame(cloneStrList, actualStrList, "actualStrList & cloneStrList refer to the not same object");

        //the following will succeed
        Assertions.assertNotSame(expectedStrList, actualStrList, "actualStrList & expectedStrList refer to the same object");
    }

```
### 5. assertTimeout() & assertTimeoutPreemptively()
>- assertTimeout() 和 assertTimeoutPreemptively() : 用於測試長時間運行的任務。    
  如果測試用例中的給定任務花費的時間超過指定的持續時間，則測試將失敗。    
  這兩種方法之間唯一的區別在於如果超時，assertTimeoutPreemptively()會搶先中止Executable或ThrowingSupplier。    
  而assertTimeout()，則不會終止Executable或ThrowingSupplier。
```java
    @Test
    public void assertTimeoutJudgmentCase() {
//        public static void assertTimeout(Duration timeout, Executable executable)
//        public static void assertTimeout(Duration timeout, Executable executable, String message)
//        public static void assertTimeout(Duration timeout, Executable executable, Supplier<String> messageSupplier)
        
        int num3;
        //the following will succeed
        num3 = Assertions.assertTimeout(Duration.ofMinutes(1), () -> {
        return -1;
        }, "Timeout Duration.ofMinutes(1)");
        System.out.println("num3:"+num3);

        //the following will fail 還是會return，主要用在assertAll上讓他所用到return繼續使用
        num3 = Assertions.assertTimeout(Duration.ofMillis(100), () -> {
        Thread.sleep(200);
        return 1;
        }, "Timeout Duration.ofMillis(100)");
        System.out.println("num3:"+num3);

        //the following will fail 不會return
        num3 = Assertions.assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
        Thread.sleep(200);
        return 2;
        }, "Timeout Duration.ofMillis(100)");
        System.out.println("num3:"+num3);
    }

```
### 6. assertThrowsCase() & assertDoesNotThrow()
>- assertThrows() : 可以對方法所拋出的Exception做斷言測試，看他是不是拋出正確的Exception，如果引發的Exception與設定的Exception相同，則這一步的斷言成功並且還可以返回一個Exception。
>- assertDoesNotThrow() : 斷言方法不會拋出任何Exception，如果有拋Exception則為fail。
```java
    @Test
    public void assertThrowsCase() {
//        public static <T extends Throwable> T assertThrows(Class<T> expectedType, Executable executable)
//        public static <T extends Throwable> T assertThrows(Class<T> expectedType, Executable executable, String message)
//        public static <T extends Throwable> T assertThrows(Class<T> expectedType, Executable executable, Supplier<String> messageSupplier)
        
        //the following will succeed
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("error message");
        });
    }

    @Test
    public void assertDoesNotThrowCase() {
//        public static void assertDoesNotThrow(Executable executable)
//        public static void assertDoesNotThrow(Executable executable, String message)
//        public static void assertDoesNotThrow(Executable executable, Supplier<String> messageSupplier)
//        public static <T> T assertDoesNotThrow(ThrowingSupplier<T> supplier)
//        public static <T> T assertDoesNotThrow(ThrowingSupplier<T> supplier, String message)
//        public static <T> T assertDoesNotThrow(ThrowingSupplier<T> supplier, Supplier<String> messageSupplier)

        //the following will succeed
        Assertions.assertDoesNotThrow(() -> methodMustNotThrow(2,2), "fail with methodMustNotThrow message: Exception");
        //the following will fail
        Assertions.assertDoesNotThrow(() -> {
            methodMustNotThrow(2,0);
        }, "Fail: must not trow");
    }

//    舉例用的方法
    void methodMustNotThrow(int num1, int num2) {
        try {
            int result = num1 / num2;
        } catch (ArithmeticException e) {
            throw new ArithmeticException("The numB not allowed to '0'!!");
        }
    }

```
### 7. arrayEqualsCase()
>- arrayEqualsCase() : 斷言數組是否與期望數組相同。
```java
    @Test
    public void arrayEqualsCase() {
//        public static void assertArrayEquals(int[] expected, int[] actual)
//        public static void assertArrayEquals(int[] expected, int[] actual, String message)
//        public static void assertArrayEquals(int[] expected, int[] actual, Supplier<String> messageSupplier)

        //the following will succeed
        Assertions.assertArrayEquals(expectedIntArr, actualIntArr, "actualArray not Equals expectedArray.");
    }

```
### 8. assertIterableEquals()
>- assertIterableEquals() : 斷言可跌代項(Iterable)與預期完全相等(包括數量與順序)。
```java
    @Test
    public void assertIterableEqualsCase() {
//        public static void assertIterableEquals(Iterable<?> expected, Iterable> actual)
//        public static void assertIterableEquals(Iterable<?> expected, Iterable> actual, String message)
//        public static void assertIterableEquals(Iterable<?> expected, Iterable> actual, Supplier<String> messageSupplier)

        //the following will succeed
        Assertions.assertIterableEquals(expectedStrList, actualStrList, "actualStrList's Iterable not equals expectedStrList's Iterable.");

    }

```
### 9. assertLinesMatch()
>- assertLinesMatch() : 斷言預期的String list與實際String list是否匹配。
```java
    @Test
    public void assertLinesMatchCase() {
//        1.檢查是否expected.equals(actual)如果是，則繼續下一對
//        2.否則將expected作為正則表達式來檢查看看，如果應用正則表達式後相等，則繼續下一對
//        3.否則，檢查expected行是否為fast-forward，如果適用相應地fast-forward實際行並轉到1。

        //the following will succeed
        Assertions.assertLinesMatch(expectedStrList, actualStrList, "fail");
    }

```
### 10. assertAll()
>- assertAll() : 多重斷言，無論所有斷言有無fail都會跑完，最後再給出所有斷言結果。
```java
    @Test
    public void assertAllCase() {
        int num1 = 2;
        int num2 = 2;
        int expected1 = 4;
        int expected2 = 3;

        int actual = AssertionCalculator.add(num1, num2);

        Assertions.assertAll(
                //the following will succeed
                () -> Assertions.assertTrue(expected1 == actual, "AssertionCalculator.add(" + num1 + "," + num2 + ") == " + expected1 + "is false"),
        //the following will fail
                () -> Assertions.assertTrue(expected2 == actual, "AssertionCalculator.add(" + num1 + "," + num2 + ") == " + expected2 + "is false"),
        //the following will fail
                () -> Assertions.assertFalse(expected1 == actual, "AssertionCalculator.add(" + num1 + "," + num2 + ") == " + expected1 + "is true"),
        //the following will succeed
                () -> Assertions.assertFalse(expected2 == actual, "AssertionCalculator.add(" + num1 + "," + num2 + ") == " + expected2 + "is true")
        );
    }

```
### 11. fail()
>- fail() : 就是直接讓測試失敗。
```java
    @Test
    public void failCase() {
//        public static void fail(String message)
//        public static void fail(Throwable cause)
//        public static void fail(String message, Throwable cause)
//        public static void fail(Supplier<String> messageSupplier)

        //the following will fail
        Assertions.fail("test failed");
    }
}
```
### 注意事項
- **一個測試案例只測一件事!!**    
在編寫Unit Test中時原則上不會在同一Test驗證多件事件，這樣才可以精準的測試出哪個地方有問題，方便辨別哪個方法錯誤，所以除非有特殊需求，不然盡量別使用assertAll()。    
    
- **可以編輯斷言fail message!**    
基本上在編輯@Test時，可以在斷言後自定義失敗訊息，像是assertEquals("expected", "actual","Fail")之類的，可以在最後的參數項自訂失敗訊息。
## 參考資料
[第三天 AssertJ入門與用3A原則寫測試案例](https://ithelp.ithome.com.tw/articles/10193175, "JUnit4-3-1")<br/>
[第四天 硬斷言、軟斷言、一個測試案例只測一件事](https://ithelp.ithome.com.tw/articles/10193404, "JUnit4-3-2")<br/>
[Class Assertions](https://junit.org/junit5/docs/5.0.1/api/org/junit/jupiter/api/Assertions.html, "JUnit4-3-3")<br/>