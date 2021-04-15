package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.NewContact;

import java.util.List;

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

    public void modify(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s'", id))).click();
    }

    public void delete(NewContact contact) {
        chooseById(contact.getId());
        deleteSelectedContact();
        closeAlertDeletionWindow();
        contactCache = null;
    }

    public void submitModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void edit(NewContact newContact, boolean b) {
        type(By.name("firstname"), newContact.getName());
        type(By.name("middlename"), newContact.getMiddlename());
        type(By.name("lastname"), newContact.getSurname());
        contactCache = null;
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(NewContact newContact) {
        click(By.linkText("add new"));
        newContactCreationWithNameMidnameSurname(newContact, true);
        wd.findElement(By.linkText("home page")).click();
        contactCache = null;
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size(); // return wd.findElements(By.xpath("//td/input")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath(".//*[@id='maintable']/tbody/tr"));
        elements.remove(0);
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstName = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            String allPhones = cells.get(5).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new NewContact()
                    .withId(id).withName(firstName).withSurname(lastName).withGroup("[none]")
                    .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));
        }
        return new Contacts(contactCache);
    }

    public NewContact infoFromEditForm(NewContact contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String middlename = wd.findElement(By.name("middlename")).getAttribute("value");
        String surname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String firstEmail = wd.findElement(By.name("email")).getAttribute("value");
        String secondEmail = wd.findElement(By.name("email2")).getAttribute("value");
        String thirdEmail = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new NewContact()
                .withId(contact.getId())
                .withName(firstname)
                .withMiddlename(middlename)
                .withSurname(surname)
                .withHomePhone(home)
                .withMobilePhone(mobile)
                .withWorkPhone(work)
                .withAddress(address)
                .withFirstEmail(firstEmail)
                .withSecondEmail(secondEmail)
                .withThirdEmail(thirdEmail);
    }

    private void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }
}
