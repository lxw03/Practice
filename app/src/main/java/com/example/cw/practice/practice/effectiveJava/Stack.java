package com.example.cw.practice.practice.effectiveJava;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Created by cw on 2017/3/5.
 */

//将类泛型化的第一个步骤是给它的声明添加一个或者多个类型参数
public class Stack<E> {
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_STACK_SIZE = 16;

    public Stack() {
        elements = (E[])new Object[DEFAULT_STACK_SIZE];
    }

    private void ensureCapacity(){
        if (elements.length == size){
            elements = Arrays.copyOf(elements,size*2+1);
        }
    }

    public boolean isEmpty(){
        return elements.length == 0;
    }

    public Object pop(){
        if (size == 0){
            throw new EmptyStackException();
        }
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

    public void push(E e){
        ensureCapacity();
        elements[size++] = e;
    }
}
