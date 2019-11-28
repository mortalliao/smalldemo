package com.smalldemo.classloader;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Jim
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class FileDefine {

//    public static String WATCH_PACKAGE = System.getProperty("user.home") + "/git/SpringBoot-Practice/classLoader/out/production/classes/com/example/watchfile";
    public static String WATCH_PACKAGE = "D:\\Java\\smalldemo\\src\\main\\java\\com\\smalldemo\\classloader\\watchfile";

    private String fileName;

    private Long lastDefine;

}
