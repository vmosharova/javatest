package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.NewContact;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void newContactCreationWithNameMidnameSurname(NewContact newContact, boolean creation) { //creation=true:creation; creation=false:modification
        type(By.name("firstname"), newContact.getName());
        type(By.name("middlename"), newContact.getMiddlename());
        type(By.name("lastname"), newContact.getSurname());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(NewContact.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

        click(By.name("submit"));
    }

    public void chooseContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void closeAlertDeletionWindow() {
        wd.switchTo().alert().accept();
    }

    public void initContactModification(int index) {
        click(By.xpath("//img[@alt='Edit']")); // (//img[@alt='Edit'])[2]
    }

    public void submitContactModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void editingContactWithNameMidnameSurname(NewContact newContact, boolean b) {
        type(By.name("firstname"), newContact.getName());
        type(By.name("middlename"), newContact.getMiddlename());
        type(By.name("lastname"), newContact.getSurname());
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createContact(NewContact newContact) {
        click(By.linkText("add new"));
        newContactCreationWithNameMidnameSurname(newContact, true);
        wd.findElement(By.linkText("home page")).click();
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size(); // return wd.findElements(By.xpath("//td/input")).size();
    }
}
