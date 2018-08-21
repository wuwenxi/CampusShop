package com.wwx.ssm.o2o.utils;


public class ValidateUtil {

    public static boolean checkShopImage(String fileName){
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        return ".png".equals(suffix) || ".jpg".equals(suffix);
    }
}
