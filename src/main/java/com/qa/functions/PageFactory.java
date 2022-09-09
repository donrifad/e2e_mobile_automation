package com.qa.functions;

import com.qa.common.ConfigPropertReader;
import org.reflections.Reflections;

import java.util.Set;


public class PageFactory {

    public static <T> T getInstance(Class pageClass) {
        String platform = ConfigPropertReader.getProperty("platform");
        if (platform == null || platform.toLowerCase().equalsIgnoreCase("browser")) platform = "Web";
        else if (platform.toLowerCase().equalsIgnoreCase("android")) platform = "Android";
        else if (platform.toLowerCase().equalsIgnoreCase("ios")) platform = "IOS";

        try {
            Reflections reflections = new Reflections("com.hd.qa.pages");
            Set<Class<?>> pageClasses = reflections.getSubTypesOf(pageClass);
            for (Class pgClass : pageClasses) {
                if (pgClass.getSimpleName().equalsIgnoreCase("" + platform + pageClass.getSimpleName())) {
                    System.out.println("Found the page: " + pgClass.getSimpleName());
                    return (T) pgClass.getConstructor().newInstance();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("No child class found for the page: " +
                pageClass.getSimpleName() + " for the platform. " + platform +
                "Make sure child class name should be: " + platform + pageClass.getSimpleName());
        return null;
    }
}
