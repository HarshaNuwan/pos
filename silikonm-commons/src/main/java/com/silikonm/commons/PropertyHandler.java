package com.silikonm.commons;


import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.silikonm.utilities.PathResolver;

import java.io.File;

/**
 * This class reads and writes properties
 *
 * @author harsha
 * @date 13/03/2012
 */


public class PropertyHandler {

    public PropertyHandler() {
    }

    public static void checkConfigurationFiles() {
        //check configuration directory and files
        File f;
        f = new File(PathResolver.getInstance().getPath() + "\\conf");

        if (!f.exists()) {
            System.out.println("Configuration directory not available...!");
            System.exit(0);
        }

        f = new File(PathResolver.getInstance().getPath() + "\\conf\\log4j.properties");
        if (!f.exists()) {
            System.out.println("log4j.properties file is missing...!");
            System.exit(0);
        }
        f = new File(PathResolver.getInstance().getPath() + "\\conf\\pos.properties");
        if (!f.exists()) {
            System.out.println("ofmex_mnu.properties file is missing...!");
            System.exit(0);
        }
    }

    public static void writeProperty(String key, String value) {

        try {
            System.out.println(key + " : " + value);
            PropertiesConfiguration config =
                    new PropertiesConfiguration(PathResolver.getInstance().getPath() + "\\conf\\pos.properties");
            config.setProperty(key, value);
            config.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readProperty(String key) {

        String property = null;
        {
            try {
                PropertiesConfiguration config =
                        new PropertiesConfiguration(PathResolver.getInstance().getPath() + "\\conf\\pos.properties");
                property = config.getString(key);
            } catch (ConfigurationException ce) {
                ce.printStackTrace();
            }
        }
        return property;
    }
}
