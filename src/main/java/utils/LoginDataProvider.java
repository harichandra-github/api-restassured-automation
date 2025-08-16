package utils;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {


    @DataProvider(name = "loginData")
    public static Object[][] loginData() {
        return new Object[][]{
                {"emilys", "emilyspass"},
                {"michaelw", "michaelwpass"},
                {"sophiab", "sophiabpass"},
                {"jamesd", "jamesdpass"},
                {"emmaj", "emmajpass"},
                {"oliviaw", "oliviawpass"},
                {"alexanderj", "alexanderjpass"},
                {"avat", "avatpass"},
                {"ethanm", "ethanmpass"}
        };
    }
}
