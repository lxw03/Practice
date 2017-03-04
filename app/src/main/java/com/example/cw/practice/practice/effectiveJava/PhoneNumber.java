package com.example.cw.practice.practice.effectiveJava;

/**
 * Created by cw on 2017/3/4.
 */

public class PhoneNumber {

    private final int areaCode;
    private final int prefix;
    private final int lineNumber;

    public PhoneNumber(int areaCode, int prefix, int lineNumber) {
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.lineNumber = lineNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if (!(obj instanceof PhoneNumber)){
            return false;
        }
        PhoneNumber phoneNumber = (PhoneNumber) obj;
        return phoneNumber.areaCode == areaCode && phoneNumber.prefix == prefix && phoneNumber.lineNumber == lineNumber;
    }


    //必须要复写hashCode方法
    //约定：
    // 1。在程序执行过程中，只要对象equals操作所用到的信息没有被修改，那么对这个对象多次调用hashCode，返回同样的整数
    // 2。如果2个对象equals比较是相等的，那么调用这两个对象中任意一个对象的hashCode必定产生相等的整数。
    // 3。如果2个对象equals比较是不等的，那么调用这两个对象中任意一个对象的hashCode必定产生不等的整数。
    // 习惯使用素数来计算散列结果,31有个很好的特性，可以使用移位和减法来代替乘法 31*i == (i<<5) -i;
    @Override
    public int hashCode() {
        int result = 17;
        result = 31*result +areaCode;
        result = 31*result +prefix;
        result = 31*result +lineNumber;
        return result;
    }

    //如果一个类是不可变的，并且计算散列码的开销比较大，就应该考虑把散列码缓存在对象内部
//    private volatile int hashCode;
//
//    @Override
//    public int hashCode() {
//        int result = hashCode;
//        if (result == 0){
//            int result = 17;
//            result = 31*result +areaCode;
//            result = 31*result +prefix;
//            result = 31*result +lineNumber;
//            return result;
//        }
//        return result;
//    }

    //防止子类话 一般的做法是使这个类成为 final的
    //超类的构造器在子类的构造器之前完成
}
