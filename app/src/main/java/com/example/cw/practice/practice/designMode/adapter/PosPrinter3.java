package com.example.cw.practice.practice.designMode.adapter;

/**
 * Created by cw on 2017/5/11.
 */

public class PosPrinter3 implements IPrint {

    @Override
    public void printOrder() {
        System.out.println("post3 order");
    }

    @Override
    public void printMoney() {
        System.out.println("post3 money");
    }

    @Override
    public void printDate() {
        System.out.println("post3 date");
    }
}
