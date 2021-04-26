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

    private final ApplicationManager app;

    public ContactHelper(ApplicationManager app) {
        super(app.wd);
        this.app = app;
    }

    public void fillContactForm(NewContact newContact, boolean creation) { //creation=true:creation; creation=false:modification
        type(By.name(app.getProperty("web.firstName")), newContact.getName());
        type(By.name(app.getProperty("web.middleName")), newContact.getMiddlename());
        type(By.name(app.getProperty("web.surname")), newContact.getSurname());
        attach(By.name("photo"), newContact.getPhoto());

        if (creation) {
            new Select(wd.findElement(By.name(app.getProperty("web.newGroup")))).selectByVisibleText(NewContact.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name(app.getProperty("web.newGroup"))));
        }

        click(By.name(app.getProperty("web.submit")));
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
        type(By.name(app.getProperty("web.firstName")), newContact.getName());
        type(By.name(app.getProperty("web.middleName")), newContact.getMiddlename());
        type(By.name(app.getProperty("web.surname")), newContact.getSurname());
        contactCache = null;
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(NewContact newContact) {
        click(By.linkText(app.getProperty("web.addNewContact")));
        fillContactForm(newContact, true);
        wd.findElement(By.linkText(app.getProperty("web.homePage"))).click();
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
        String firstname = wd.findElement(By.name(app.getProperty("web.firstName"))).getAttribute("value");
        String middlename = wd.findElement(By.name(app.getProperty("web.middleName"))).getAttribute("value");
        String surname = wd.findElement(By.name(app.getProperty("web.surname"))).getAttribute("value");
        String home = wd.findElement(By.name(app.getProperty("web.home"))).getAttribute("value");
        String mobile = wd.findElement(By.name(app.getProperty("web.mobilePhone"))).getAttribute("value");
        String work = wd.findElement(By.name(app.getProperty("web.work"))).getAttribute("value");
        String address = wd.findElement(By.name(app.getProperty("web.address"))).getAttribute("value");
        String firstEmail = wd.findElement(By.name(app.getProperty("web.firstEmail"))).getAttribute("value");
        String secondEmail = wd.findElement(By.name(app.getProperty("web.secondEmail"))).getAttribute("value");
        String thirdEmail = wd.findElement(By.name(app.getProperty("web.thirdEmail"))).getAttribute("value");
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
