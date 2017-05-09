package com.example.cw.practice.practice.effectiveJava;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by cw on 2017/5/9.
 */

public class Lambda extends AppCompatActivity{

    //Lambda和匿名内部类写法对比
    private void startThread(){

        new Thread(() -> System.out.print("new Thread")).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.print("new Thread");
            }
        }).start();
    }

    private void addClickListener(){

        Button button = new Button(this);
        button.setOnClickListener((v) -> System.out.print(v.getId()));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getId();
            }
        });
    }

    @TargetApi(24)
    private void collection(){

        List<String> feature = Arrays.asList("111","222","333");
        feature.forEach(System.out::print);

        for (String e : feature){
            System.out.print(e);
        }
    }


    @TargetApi(24)
    private void filter(List names, Predicate condition){
        names.stream().filter((name) -> condition.test(name)).forEach((name) -> System.out.print(name));
    }

    private void predicate(){
        List<String> languages = Arrays.asList("Java", "C", "Javascript","Scala", "Kotlin");

        filter(languages, (language) -> language.toString().startsWith("J"));

        filter(languages, (language) -> language.toString().endsWith("n"));

        filter(languages, (language) -> true);

        filter(languages, (language) -> false);

        filter(languages, (language) -> language.toString().length()>4);
    }

    private void map(){
        List<Integer> costBeforeTax = Arrays.asList(111,112,113);

        costBeforeTax.stream().map((e) -> e *0.75).forEach((e) -> System.out.print(e));

        double bill = costBeforeTax.stream().map((e) -> e*0.75).reduce((sum, cost) -> sum +cost).get();
    }

    @TargetApi(24)
    private void filter(){
        List<String> filterBefore = Arrays.asList("aaa", "bb", "c");
        if (Build.VERSION.SDK_INT >= 24) {
            List<String> filterAfter = filterBefore.stream().filter(x -> x.length() > 2).collect(Collectors.toList());
            List<String> upCaseChange = filterBefore.stream().map(x -> x.toUpperCase()).collect(Collectors.toList());
        }else {
            //....
        }
    }

    @TargetApi(24)
    private void distinct(){
        List<Integer> list = Arrays.asList(1,2,3,1);
        if (Build.VERSION.SDK_INT >=24){
            List<Integer> filterAfter = list.stream().distinct().collect(Collectors.toList());
        }else {
            //...
        }
    }

    @TargetApi(24)
    private void summaryStatistics(){
        List<Integer> list = Arrays.asList(1,2,3,1);
        IntSummaryStatistics intSummaryStatistics = list.stream().mapToInt(e -> e).summaryStatistics();
        intSummaryStatistics.getMax();
        intSummaryStatistics.getMin();
        intSummaryStatistics.getAverage();
    }

    //Lambda vs Anonymous Inner Class
    // 1. 匿名内部内 this 指向匿名类，而Lambda指向包围Lambda的类
    // 2. Lambda被编译成包围它的类的私有方法

    //Lambda表达式仅能放入如下代码：预定于使用了@Functional注释的函数式接口，自带抽象函数的方法，或者单个抽象方法
    //Lambda可以使用方法引用，当且仅当不修改Lambda提供的参数

}
