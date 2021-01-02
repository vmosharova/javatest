package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String username, String password) {
        type(By.name("user"), username);
        type(By.name("pass"), password);
        wd.findElement(By.id("LoginForm")).submit();
        //click(By.id("LoginForm")); - не получается, потому что в этой функции в конце click(), а нам требуется submit(). Оставлена оригинальная строка (предыдущая)
    }

    public void logOut() {
        wd.findElement(By.linkText("Logout")).click();
    }

}
