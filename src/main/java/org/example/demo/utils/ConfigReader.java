package org.example.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);

    public static final Properties properties = new Properties();

    static {
        try (FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            logger.error("Failed to open file: ", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getXPath(String key, String... params) {
        String xpath = properties.getProperty(key);

        if (params.length > 0) {
            xpath = xpath.replace("{label}", params[0]);
        }
        return xpath;
    }
}
