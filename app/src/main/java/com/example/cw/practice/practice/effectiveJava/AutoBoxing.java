package com.example.cw.practice.practice.effectiveJava;

/**
 * Created by cw on 2017/3/4.
 */

public class AutoBoxing {

    public static void main(){
        Long sum = 0L;
        //程序将会构造多余的2^31个多余的Long实例
        //要优先使用基本数据类型，避免不必要的自动装箱
        for (long i=0; i<Integer.MAX_VALUE;i++){
            sum += i;
        }
        System.out.println(sum);
    }
}
