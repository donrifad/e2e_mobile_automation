package com.qa.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigPropertReader {
    private static Properties properties = null;

    private static void loadProperties(String relFilePath) {
        properties = new Properties();
        InputStream input;
        try {
            input = ConfigPropertReader.class.getResourceAsStream(relFilePath);
            properties.load(input);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        if (properties == null)
            loadProperties("/config.properties");

        String p = System.getProperty(key);
        //System.out.println("Property Key: "+key+ " and its value: "+ p);
        return p != null ? p : properties.getProperty(key);
    }
}
