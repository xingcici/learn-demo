package com.example.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author haifeng.pang [panghaifeng@weidian.com]
 * @version 1.0 : Application v0.1 2020/6/30 18:31 haifeng.pang Exp $
 **/
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        try {
            SpringApplication.run(Application.class, args);
        } catch (Exception e) {
            // Never do this
        }
    }
}
