package com.smalldemo.dubbo.api;

/**
 * @author Jim
 *
 * 定义服务接口 并利用maven工具打包 供服务提供者和消费者使用
 */
public interface DemoService {
    String sayHello(String name);
}
