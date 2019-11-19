package com.smalldemo.thirdpart.dubbo.provider.impl;

import com.smalldemo.thirdpart.dubbo.api.DemoService;

/**
 * @author Jim
 *
 * 接口服务实现
 */
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}

