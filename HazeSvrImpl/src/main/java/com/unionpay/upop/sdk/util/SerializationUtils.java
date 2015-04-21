package com.unionpay.upop.sdk.util;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Set;

import com.unionpay.upop.sdk.PayRequest;

public class SerializationUtils {
    
    public static <T extends Serializable> T readObject(String filename, Class<T> clazz) {
        T instance = null;
        PropLoader propLoader = PropManager.getInstance(filename);
        Set<String> propertyNames = propLoader.propertyNames();
        try {
            instance = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot initialized instance of " + clazz);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot initialized instance of " + clazz);
        }

        for (String property : propertyNames) {
            try {
                String methodName = "set" + PayUtils.capitalize(property);
                Method method = clazz.getMethod(methodName, String.class);
                method.invoke(instance, propLoader.getProperty(property));
            } catch (Throwable t) {
                t.printStackTrace();
                continue;
            }
        }

        return instance;
    }
    
    public static void main(String[] args) {
        System.out.println(SerializationUtils.readObject("QuickPayQuery.properties", PayRequest.class).getMerAbbr());
    }
}
