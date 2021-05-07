package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.NewContact;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
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

        /* if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(NewContact.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        } */

        if (creation) {
            if (newContact.getGroups().size() > 0) {
                Assert.assertTrue(newContact.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group")))
                        .selectByVisibleText(newContact.getGroups().iterator().next().getName());
            }
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
        type(By.name(app.getProperty("web.firstName")), newContact.getName());
        type(By.name(app.getProperty("web.middleName")), newContact.getMiddlename());
        type(By.name(app.getProperty("web.surname")), newContact.getSurname());
        contactCache = null;
    }

    public void create(NewContact newContact) {
        app.goTo().homePage();
        click(By.linkText("add new"));
        fillContactForm(newContact, true);
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
                    .withId(id).withName(firstName).withSurname(lastName)
                    .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));
        }
        return new Contacts(contactCache);
    }

    public NewContact infoFromEditForm(NewContact contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name(app.getProperty("web.firstName"))).getAttribute("value");
        String middlename = wd.findElement(By.name(app.getProperty("web.middleName"))).getAttribute("value");
        String surname = wd.findElement(By.name(app.getProperty("web.surname"))).getAttribute("value");
        String home = wd.findElement(By.name(app.getProperty("web.homePhone"))).getAttribute("value");
        String mobile = wd.findElement(By.name(app.getProperty("web.mobilePhone"))).getAttribute("value");
        String work = wd.findElement(By.name(app.getProperty("web.workPhone"))).getAttribute("value");
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

    // Проверка для добавления теста в группу: мб контакт уже добавлен во все возможные группы:
    public NewContact selectContact(boolean adding) throws IOException {

        Contacts allContacts = app.db().contacts();
        if (adding) {
            Groups allGroups = app.db().groups();
            for (NewContact contact : allContacts) {
                if (contact.getGroups().size() < allGroups.size()) {
                    return contact;
                }
            }
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("header1").withFooter("footer1"));
            return allContacts.iterator().next();
        } else {
            for (NewContact contact : allContacts) {
                if (contact.getGroups().size() > 0) {
                    return contact;
                }
            }
            NewContact contact = app.db().contacts().iterator().next();
            app.contact().addContactToGroup(contact, app.db().groups().iterator().next());
            return contact;
        }
   }

    public GroupData selectGroup(NewContact contact, boolean adding) {
        if (adding) {
            Groups allGroups = app.db().groups();
            Groups contactsInGroups = contact.getGroups();

            Collection<GroupData> contactGroups = new HashSet<>(contactsInGroups);
            Collection<GroupData> avaliableGroups = new HashSet<>(allGroups);
            avaliableGroups.removeAll(contactGroups);
            return avaliableGroups.iterator().next();
        } else {
            NewContact contactToRemove = selectContactById(contact);
            Groups groupsInRemovedContact = contact.getGroups();
            return groupsInRemovedContact.iterator().next();
        }
    }



    public NewContact selectContactById(NewContact addContact) {
        Contacts contactsById = app.db().contacts();
        return contactsById.iterator().next().withId(addContact.getId());
    }

    public void selectContactCheckboxById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void addToGroupButton() {
        wd.findElement(By.name("add")).click();
    }

    public void goToGroupPageAfterAddingContactToGroup() {
        wd.findElement(By.partialLinkText("group page")).click();
    }

    public void selectGroupFromListToAdd(int groupId) {
        new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(groupId));
    }

    public void addContactToGroup(NewContact contactData, GroupData groupData) {

        selectContactCheckboxById(contactData.getId());
        selectGroupFromListToAdd(groupData.getId());
        addToGroupButton();
        goToGroupPageAfterAddingContactToGroup();
        contactCache = null;
    }

    public void removeContactFromGroup(NewContact contact, GroupData groupData) {
        selectContactCheckboxById(contact.getId());
        wd.findElement(By.name("remove")).click();
        goToGroupPageAfterAddingContactToGroup();
        contactCache = null;
    }

    public void selectGroupFromList(int groupId) {
        new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(groupId));
    }
}

