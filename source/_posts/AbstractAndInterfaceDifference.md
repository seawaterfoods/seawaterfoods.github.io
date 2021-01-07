---
title: Abstract Class vs Interface
date: 2021-01-07 14:15:06
category: Java
tags: [基礎,Java]
index_img: /img/interface-vs-abstract-class-java.jpg
---
![](/seawaterfoods/img/interface-vs-abstract-class-java.jpg)

在初學java時常搞混抽象類別(Abstract Class)跟介面(Interface)，在這以簡易表格呈現兩者之間的區別。
<!-- more -->
### Abstract Class vs Interface
|  | 	抽象類別 Abstract Class | 介面 Interface |
| :-----:| :----: | :----: |
| 聲明                    | public abstract class XXX                 | public interface OOO      |
| 能否直接使用new來產生實體  | 不能                                      | 不能                       |
| 繼承/實作數量            | 只能使用extends單一繼承                      | 可以implements實作多個介面   |
| 能否有建構子             | 可以                                       | 不能                       |
| 訪問修飾符               | 與正常class無異                             | 僅可使用public             |
| 新增方法                | 當父類別建立新方法時，子類別無須實作父類別的新方法 | 當父類別建立新方法時，子類別一定要實作父類別的新方法 |
| 設計理念 | 表示"is-a"的關係 | 表示"like-a"的關係 |

### 舉些栗子

1. Abstract Class

```java
public abstract class Role {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void fight();
}
```

```java
public class Magician extends Role {
    public void fight() {
        System.out.println("魔法攻擊");
    }
}
```
2. Interface
```java
public interface Rider {
    public abstract void ride();  
}
public interface Swimmer {
    void swim();  // 預設就是public abstract
}
```
```java
public class Traveler implements Swimmer, Rider {
//   一定要將interface中的方法實做出來，不然會編譯失敗
    @Override
    public void ride() {
        System.out.println("駕駛馬車");
    }
    @Override
    public void swim() {
        System.out.println("自由式");
    }
}
```
當然可以同時應用抽象類別跟介面。
```java
public class SwordsMan extends Role implements Swimmer, Rider {
    @Override
    public void fight() {
        System.out.println("揮劍攻擊");
    }
    @Override
    public void ride() {
        System.out.println("騎馬");
    }
    @Override
    public void swim() {
        System.out.println("蛙式");
    }
}
```
```java
public class RPGTest {
    public static void main(String[] args) {

        SwordsMan swordsMan = new SwordsMan();
        swordsMan.setName("Justin");

        System.out.println("swordsMan :"+swordsMan.getName+" 會 ");
        swordsMan.fight();
        swordsMan.ride();
        swordsMan.swim();
    }
}
```