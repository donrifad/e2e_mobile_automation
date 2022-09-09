package com.qa.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestRailPropertyReader {
    private static Properties properties = null;

    private static void loadProperties() {
        properties = new Properties();
        InputStream input;
        try {
            input = TestRailPropertyReader.class.getResourceAsStream("/testrail.properties");
            properties.load(input);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        if (properties == null)
            loadProperties();

        String p = System.getProperty(key);
        return p != null ? p : properties.getProperty(key);
    }
}
