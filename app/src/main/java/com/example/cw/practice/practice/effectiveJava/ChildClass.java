package com.example.cw.practice.practice.effectiveJava;

import java.util.Set;

/**
 * Created by cw on 2017/3/5.
 */

//类层次优于便签类
public class ChildClass {
    abstract class Figure{
        abstract double area();
    }

    //杜绝switch case
    class Rectangular extends Figure{

        final double length,width;
        public Rectangular(double length, double width) {
            this.length = length;
            this.width = width;
        }
        @Override
        double area() {
            return length*width;
        }
    }

    class Circle extends Figure{
        final double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        @Override
        double area() {
            return Math.PI * radius* radius;
        }
    }

    //无限制通配符类型Set<?>
    //范型信息可以在运行时被擦除
    static int numElementsInCommon(Set<?> s1, Set<?> s2){
        int result = 0;
        for (Object o1: s1){
            if (s2.contains(o1)){
                result ++;
            }
        }
        return result;
    }
}

//匿名类的常见用法： 动态地创建函数对象   创建过程对象Runnable,Thread

//如果成员类的每个实例都需要一个指向其外围的实例的引用，就要把成员类做成非静态的。否则申明为静态的.

//假如这个嵌套类属于一个方法的内部，如果你只需要在一个地方创建实例，并且已经有一个预置的类型可以说明这个类的特征，
//就把它做成匿名类，否则做成局部类.

//数组是协变的covariant,泛型则是invariant，数组提供运行时的类型安全没有编译时的安全
//擦除就是使泛型可以与没有使用泛型的代码随意进行互用
