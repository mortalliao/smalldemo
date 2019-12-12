package com.smalldemo.spi;

/**
 * @author Jim
 */
public class Dog implements IShout {
    @Override
    public void shout() {
        System.out.println("Dog shout: wang wang");
    }
}
