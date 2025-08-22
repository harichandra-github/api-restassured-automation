package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class CommonFunctions {

    public static String getTimeStamp() {
        return new SimpleDateFormat("dd_MMMM_yyyy_HHmmss").format(new Date());
    }


    public static Properties loadTestConfig() throws IOException {
        Properties testConfig = new Properties(System.getProperties());

        try (FileInputStream fileInput = new FileInputStream("src/test/resources/configuration/config.properties")) {
            testConfig.load(fileInput);
        }

        return testConfig;
    }
}
