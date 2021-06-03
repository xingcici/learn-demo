package com.example.rocketmq;

/**
 * @author haifeng.pang [panghaifeng@weidian.com]
 * @version 1.0 : LifeCyle v0.1 2020/6/30 19:13 haifeng.pang Exp $
 **/
public interface LifeCycle {

    public void init() throws Exception;

    public void startup() throws Exception;

    public void shutdown();
}
