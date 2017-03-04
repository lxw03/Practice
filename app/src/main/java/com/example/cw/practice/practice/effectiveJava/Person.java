package com.example.cw.practice.practice.effectiveJava;


import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by cw on 2017/3/4.
 */

public class Person {
    private final Date birthDate;

    public Person(Date birthDate) {
        this.birthDate = birthDate;
    }

    private static final Date START_DATE;
    private static final Date END_DATE;

    //避免创建不必要的对象
    static {
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY,1,0 ,0,0);
        START_DATE = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1,0,0,0);
        END_DATE = gmtCal.getTime();
    }

    public boolean isBirthBoom(){
        return birthDate.compareTo(START_DATE)>=0 && birthDate.compareTo(END_DATE)<0;
    }
}
