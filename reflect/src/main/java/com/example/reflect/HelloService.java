package com.example.reflect;

/**
 * @author haifeng.pang [panghaifeng@weidian.com]
 * @version 1.0 : HelloService v0.1 2020/6/28 09:24 haifeng.pang Exp $
 **/
public class HelloService extends SayService{

    public void publicHello(String value) {
        System.out.println("i'am public hello " + value);
    }

    private void privateHello(String value) {
        System.out.println("i'am private hello " + value);
    }
}
