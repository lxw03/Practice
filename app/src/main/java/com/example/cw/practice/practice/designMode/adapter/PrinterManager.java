package com.example.cw.practice.practice.designMode.adapter;

/**
 * Created by cw on 2017/5/11.
 */

public class PrinterManager implements IPrint{

    private IPrint mIPrint;
    private static PrinterManager instance;

    private PrinterManager(int postType){
        switch (postType){
            case 1:{
                mIPrint = new PosPrinter1();
                break;
            }
            case 2:{
                mIPrint = new PosPrinter2();
                break;
            }
            case 3:{
                mIPrint = new PosPrinter3();
                break;
            }
            default:
                break;
        }
    }

    public static PrinterManager getInstance(int postType){
        if (instance == null){
            synchronized (PrinterManager.class){
                if (instance == null){
                    instance = new PrinterManager(postType);
                }
            }
        }
        return instance;
    }

    @Override
    public void printOrder() {
        mIPrint.printOrder();
    }

    @Override
    public void printMoney() {
        mIPrint.printMoney();
    }

    @Override
    public void printDate() {
        mIPrint.printDate();
    }
}
