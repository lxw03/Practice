package com.example.cw.practice.practice.designMode.adapter;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by cw on 2017/5/11.
 */

public class PosActivity extends AppCompatActivity {

    private void printOrder(int type){
        PrinterManager.getInstance(type).printOrder();
    }

    private void printMoney(int type){
        PrinterManager.getInstance(type).printMoney();
    }

    private void printDate(int type){
        PrinterManager.getInstance(type).printDate();
    }
}
