package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.NewContact;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void chooseById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void closeAlertDeletionWindow() {
        wd.switchTo().alert().accept();
    }

    public void modify() {
        wd.findElement(By.xpath("//img[@alt='Edit']")).click();
    }

    public void delete(NewContact contact) {
        chooseById(contact.getId());
        deleteSelectedContact();
        closeAlertDeletionWindow();
    }


    public void submitModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void edit(NewContact newContact, boolean b) {
        type(By.name("firstname"), newContact.getName());
        type(By.name("middlename"), newContact.getMiddlename());
        type(By.name("lastname"), newContact.getSurname());
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(NewContact newContact) {
        click(By.linkText("add new"));
        newContactCreationWithNameMidnameSurname(newContact, true);
        wd.findElement(By.linkText("home page")).click();
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size(); // return wd.findElements(By.xpath("//td/input")).size();
    }

    public Set<NewContact> all() {
        Set<NewContact> contacts = new HashSet<NewContact>();
        List<WebElement> elements = wd.findElements(By.xpath(".//*[@id='maintable']/tbody/tr"));
        elements.remove(0);
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstName = cells.get(2).getText();
            String lastName = cells.get(1).getText();

            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            NewContact contact = new NewContact()
                    .withId(id).withName(firstName).withSurname(lastName).withGroup("[none]");
            contacts.add(contact);
        }
        return contacts;
    }

}
