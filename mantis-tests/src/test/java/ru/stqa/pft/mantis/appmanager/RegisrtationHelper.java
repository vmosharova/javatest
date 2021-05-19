package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

public class RegisrtationHelper {
    private final ApplicationManager app;
    private WebDriver wd;

    public RegisrtationHelper(ApplicationManager app) {
    this.app = app;
    wd = app.getDriver();
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "signup_page.php");
    }
}
