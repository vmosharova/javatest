package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.NewContact;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void newContactCreationWithNameMidnameSurname(NewContact newContact) {
        type(By.name("firstname"), newContact.getName());
        type(By.name("middlename"), newContact.getMiddlename());
        type(By.name("lastname"), newContact.getSurname());
        click(By.name("submit"));
    }

    public void chooseContact() {
        click(By.name("selected[]")); //или id=3
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']")); // или //input[@value='Delete']
    }

    public void closeAlertDeletionWindow() {
        wd.switchTo().alert().accept();
    }

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void editingContactWithNameMidnameSurname (NewContact newContact) {
        type(By.name("firstname"), newContact.getName());
        type(By.name("middlename"), newContact.getMiddlename());
        type(By.name("lastname"), newContact.getSurname());
    }
}
