package com.example.cw.j2v8.v8_utils;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Object;

import java.lang.reflect.Method;

/**
 * Created by cw on 2017/5/10.
 */

public class V8Class extends V8Object {

    public V8Class(V8 v8, final Class<?> iface) {
        this(v8, iface, null);
    }

    public V8Class(V8 v8, final Class<?> iface, Object object) {
        super(v8, object);

    }

    private void _registerMthod(Class<?> iface){
        if (null == iface){
            return;
        }
        Method[] methods = iface.getDeclaredMethods();
        for (Method method: methods){
            String name = method.getName();
            super.registerJavaMethod(this, name, name, method.getParameterTypes());
        }

        Class<?>[] superInterface = iface.getInterfaces();
        for (Class<?> t: superInterface){
            _registerMthod(t);
        }
    }
}
