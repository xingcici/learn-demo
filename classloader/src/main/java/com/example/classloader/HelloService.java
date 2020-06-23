package com.example.classloader;

/**
 * @author haifeng.pang [panghaifeng@weidian.com]
 * @version 1.0 : HelloService v0.1 2020/6/23 17:13 haifeng.pang Exp $
 **/
public class HelloService {

    public void hello() {
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println("hello");
    }
}
