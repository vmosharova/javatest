package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.NewContact;

import java.io.IOException;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class AddContactToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsForAddingContactInGroup() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        if (app.db().contacts().size() == 0) {
            app.contact().create(new NewContact().withName(app.getProperty("web.firstName"))
            .withMiddlename(app.getProperty("web.middleName")).withSurname(app.getProperty("web.surname")));
        }
    }

    @Test
    public void testAddContactToGroup() throws IOException {
        app.goTo().homePage();
        NewContact contact = app.contact().selectContact();
        GroupData groupToAddContactTo = app.contact().selectGroup(contact);
        Groups before = contact.getGroups();
        app.goTo().homePage();
        app.contact().addContactToGroup(contact, groupToAddContactTo);
        app.goTo().homePage();
        NewContact contactAfter = app.contact().selectContactById(contact);
        Groups after = contactAfter.getGroups();
        assertThat(after, equalTo(before.withAdded(groupToAddContactTo)));
    }

    }

