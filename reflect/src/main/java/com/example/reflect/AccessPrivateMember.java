package com.example.reflect;

import java.lang.reflect.Method;

/**
 * @author haifeng.pang [panghaifeng@weidian.com]
 * @version 1.0 : AccessPrivateMember v0.1 2020/6/28 09:24 haifeng.pang Exp $
 **/
public class AccessPrivateMember {

    public static void main(String[] args) {
        try {
            Class c = Class.forName("com.example.reflect.HelloService");
            //能获取所有有访问权限的方法，包括父类中继承的
            Method publicMethod = c.getMethod("publicHello", String.class);

            Method saySomething = c.getMethod("publicHello", String.class);
            //获取所有方法 本方法中
            Method thisClassMethod = c.getDeclaredMethod("privateHello", String.class);
            //设置权限
            thisClassMethod.setAccessible(true);

            publicMethod.invoke(c.newInstance(), "publicMethod");
            saySomething.invoke(c.newInstance(), "saySomething");

            thisClassMethod.invoke(c.newInstance(), "thisClassMethod");

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
