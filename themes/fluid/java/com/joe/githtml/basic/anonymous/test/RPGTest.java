package com.joe.githtml.basic.anonymous.test;

import com.joe.githtml.basic.anonymous.entry.Magician;
import com.joe.githtml.basic.anonymous.entry.Role;
import com.joe.githtml.basic.anonymous.entry.SwordsMan;

public class RPGTest {
    public static void main(String[] args) {

        SwordsMan swordsMan = new SwordsMan(){
            @Override
            public String toString(){
                String toString = getClass().getSimpleName()+" Name : "+getName()+"; Level : "+getLevel()+"; Blood : "+getBlood();
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

        drawFight(swordsMan);
        drawFight(magician);
        System.out.println(swordsMan);
        System.out.println(magician);
    }

    static void drawFight(Role role) {
        System.out.print(role.getName());
        role.fight();
    }
}
