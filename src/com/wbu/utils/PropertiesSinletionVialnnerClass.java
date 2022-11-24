package com.wbu.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesSinletionVialnnerClass {

    public PropertiesSinletionVialnnerClass(){
        readConfig();
    }

    //内部Static
    public static class PropertiesInnerClass{
        private static PropertiesSinletionVialnnerClass SingleInstance=new PropertiesSinletionVialnnerClass();
    }

    //单例
    public static PropertiesSinletionVialnnerClass getInstance(){
        return PropertiesInnerClass.SingleInstance;
    }

    

    private Properties properties;

    public String getValue(String key){
        return properties.getProperty(key);
    }
    
    public void readConfig(){
        properties =new Properties();
        InputStream ins=null;
        try {
            ins=new FileInputStream("D:\\作业\\JavaProject\\LogApplication\\LogApplication\\config\\jdbc.properties");
            properties.load(ins);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
