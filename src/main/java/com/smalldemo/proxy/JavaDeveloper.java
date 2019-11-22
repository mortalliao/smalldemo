package com.smalldemo.proxy;

/**
 * @author Jim
 */
public class JavaDeveloper implements Developer {

    private String name;

    JavaDeveloper(String name) {
        this.name = name;
    }

    @Override
    public void code() {
        System.out.println(this.name + "is coding java");
    }

    @Override
    public void debug() {
        System.out.println(this.name + "is debugging java");
    }
}
