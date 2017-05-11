package com.example.cw.practice.practice.designMode.adapter;

/**
 * Created by cw on 2017/5/11.
 */

public class PosPrinter2 implements IPrint {

    @Override
    public void printOrder() {
        System.out.println("post2 order");
    }

    @Override
    public void printMoney() {
        System.out.println("post2 money");
    }

    @Override
    public void printDate() {
        System.out.println("post2 date");
    }
}
