package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    Properties prop=new Properties();
    public ConfigReader() {
        try {
            FileInputStream fileInputStream=new FileInputStream("src/test/resources/configuration/config.properties");
            prop.load(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading configuration file: ", e);
        }
    }


    public static String getProperty(String key){
        String value= new ConfigReader().prop.getProperty(key);
        if(value== null){
            throw new RuntimeException("Property not found for key: " + key);
        }
        return value;
    }
}
