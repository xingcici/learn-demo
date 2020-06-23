package com.example.classloader;

/**
 * @author haifeng.pang [panghaifeng@weidian.com]
 * @version 1.0 : ClassLoader v0.1 2020/6/23 16:56 haifeng.pang Exp $
 **/
public class ClassLoader {

    public static void main(String[] args) {
        Thread.currentThread().getContextClassLoader();
    }
}
