package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.NewContact;

public class ContactHelper {

    private WebDriver wd;
    public ContactHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void newContactCreationWithNameMidnameSurname(NewContact newContact) {
      wd.findElement(By.name("firstname")).click();
      wd.findElement(By.name("firstname")).clear();
      wd.findElement(By.name("firstname")).sendKeys(newContact.getName());
      wd.findElement(By.name("middlename")).clear();
      wd.findElement(By.name("middlename")).sendKeys(newContact.getMiddlename());
      wd.findElement(By.name("lastname")).clear();
      wd.findElement(By.name("lastname")).sendKeys(newContact.getSurname());
      wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

}
