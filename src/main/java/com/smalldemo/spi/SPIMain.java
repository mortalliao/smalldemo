package com.smalldemo.spi;

import java.util.ServiceLoader;

/**
 * @author Jim
 */
public class SPIMain {

    public static void main(String[] args) {
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);
        for (IShout iShout : shouts) {
            iShout.shout();
        }
    }
}
