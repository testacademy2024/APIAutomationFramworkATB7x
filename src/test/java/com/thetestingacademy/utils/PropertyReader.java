package com.thetestingacademy.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {
    //Responsibility of this Class is to give the value of by Key
    public static String readKey(String key){
        Properties properties =new Properties();
        try{
//Read the data.properties file and give the key->value
            FileInputStream fileInputStream=new FileInputStream("src/test/resources/data.properties");
            properties.load(fileInputStream);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return properties.getProperty(key);
    }
}
