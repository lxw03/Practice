package com.example.cw.practice.practice.annotaion;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

/**
 * Created by cw on 2017/5/12.
 */

public class IceCreamFlavourManager {

    private int flavour;

    public static final int VANILLA = 0;
    public static final int CHOCOLATE = 1;
    public static final int STRAWBERRY = 2;

    @IntDef({VANILLA, CHOCOLATE, STRAWBERRY})
    public @interface Flavour {

    }

    @StringDef({"0","1","2"})
    public @interface FlavourString{

    }

    @Flavour
    public int getFlavour(){
        return flavour;
    }

    public void setFlavour(@Flavour int flavour){
        this.flavour = flavour;
    }
}
