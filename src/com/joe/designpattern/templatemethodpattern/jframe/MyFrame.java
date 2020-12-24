package com.joe.designpattern.templatemethodpattern.jframe;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public MyFrame(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(300,300);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        String msg = "I rule!!";
        graphics.drawString(msg,100,100);
    }

    public static void main(String[] args) {
        MyFrame myFrame= new MyFrame("深入淺出設計模式");
    }

}
