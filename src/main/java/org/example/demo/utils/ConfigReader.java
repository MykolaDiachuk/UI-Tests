package org.example.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public static final Properties properties = new Properties();
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);

    static {
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            logger.error("Failed to open file: ", e);
        }

    }

    private ConfigReader() {
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
