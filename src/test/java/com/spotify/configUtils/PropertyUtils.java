package com.spotify.configUtils;
import java.io.*;
import java.util.Properties;

public class PropertyUtils {
    static Properties properties;

    public static Properties propertryLoader(String filePath) {
        properties  = new Properties();
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            try {

                properties.load(bufferedReader);
            } catch (IOException e) {

                e.printStackTrace();
                throw new RuntimeException("Failed to load properties file " + filePath);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("properties file not found " + filePath);
        }


        return properties;

    }

    public static void setProperties(String filePath,String key,String value,boolean append) {
        try{
            FileOutputStream fileOutputStream=new FileOutputStream(filePath,append);
            if(properties==null){
                properties=new Properties();
            }
            properties.setProperty(key,value);
            try {
                properties.store(fileOutputStream,key);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException("properties file not found ");
        }


    }





}
