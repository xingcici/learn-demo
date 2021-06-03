package com.example.classloader;

/**
 * @author haifeng.pang [panghaifeng@weidian.com]
 * @version 1.0 : ClassLoader v0.1 2020/6/23 16:56 haifeng.pang Exp $
 **/
public class ClassLoaderApplication {

    public static void main(String[] args) throws Exception{
        Thread.currentThread().getContextClassLoader();
        Class<?> c = CustomClassLoader.getCustomClassLoader().loadClass("com.example.classloader.HelloService", true);
        System.out.println(c.getClassLoader());
        Class<?> re = Class.forName("com.example.classloader.HelloService", true, CustomClassLoader.getCustomClassLoader());
        c.getDeclaredMethod("hello").invoke(c.newInstance(), new Object[]{});
    }
}
