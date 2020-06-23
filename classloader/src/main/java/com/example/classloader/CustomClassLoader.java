package com.example.classloader;

/**
 * @author haifeng.pang [panghaifeng@weidian.com]
 * @version 1.0 : CustomClassLoader v0.1 2020/6/23 16:59 haifeng.pang Exp $
 **/
public class CustomClassLoader extends ClassLoader{

    private String path;
    private String classLoaderName;

    public CustomClassLoader(String path, String classLoaderName) {
        this.path = path;
        this.classLoaderName = classLoaderName;
    }
}
