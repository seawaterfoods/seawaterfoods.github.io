---
title: Java:toString小技巧
category: Java
tags: [小技巧,Java]
index_img: /img/hello.jpg
---
![](/img/hello.jpg)
在開發Java專案時，時常會有需要查看撈出來的資料是否正確，但因為各種原因無法在class中實作抑或是常常更新toString，這時候就可以使用匿名內部類別（Anonymous inner class）來重新定義類別的toString方便在console打印。
<!-- more -->
## 範例 ##
```
public abstract class Role {
    private String name;
    private int level;
    private int blood;

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
public class Magician extends Role {}
public class SwordsMan extends Role {}
public class RPGTest {
    public static void main(String[] args) {

        SwordsMan swordsMan = new SwordsMan(){
            @Override
            public String toString(){
                String toString = "Name : "+getName()+"; Level : "+getLevel()+"; Blood : "+getBlood();
             return toString;
            }
        };
        swordsMan.setName("Justin");
        swordsMan.setLevel(1);
        swordsMan.setBlood(200);

        Magician magician = new Magician();
        magician.setName("Monica");
        magician.setLevel(1);
        magician.setBlood(100);

        System.out.println(swordsMan);
        System.out.println(magician);
    }
}
```

### 結果
```angular2html
Name : Justin; Level : 1; Blood : 200
com.joe.basic.anonymous.entry.Magician@4edde6e5
```