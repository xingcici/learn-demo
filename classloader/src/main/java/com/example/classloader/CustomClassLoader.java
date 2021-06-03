package com.example.classloader;

import java.io.*;
import java.net.URL;

/**
 * @author haifeng.pang [panghaifeng@weidian.com]
 * @version 1.0 : CustomClassLoader v0.1 2020/6/23 16:59 haifeng.pang Exp $
 **/
public class CustomClassLoader extends ClassLoader {

    private final static CustomClassLoader instance = new CustomClassLoader();

    public static synchronized CustomClassLoader getCustomClassLoader() {
        return instance;
    }

    public CustomClassLoader() {
        super(ClassLoader.getSystemClassLoader());
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (name.equals("com.example.classloader.HelloService")) { //防止委派加载这个类
            return findClass(name);
        }
        return super.loadClass(name, resolve);
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        BufferedInputStream bis = null;
        try {
            //加载类路径下的类
            URL url = ClassLoader.getSystemClassLoader().getResource("");
            String path = url.getPath();
            if (path.endsWith("/")) {
                path = path.substring(0, path.length() - 1);
            }
            String packagePath = name.replaceAll("\\.", "/");
            path = path + "/" + packagePath + ".class";
            bis = new BufferedInputStream(new FileInputStream(path));
            byte[] data = new byte[bis.available()];
            bis.read(data);
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
            return super.findClass(name);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
