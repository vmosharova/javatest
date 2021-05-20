package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.NewContact;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsForContacts() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new NewContact()
                    .withName(app.getProperty("web.firstName")).withMiddlename(app.getProperty("web.middleName"))
                    .withSurname(app.getProperty("surname")));
        }
    }

    @Test
    public void testContactModification() {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        NewContact modifiedContact = before.iterator().next();
        app.contact().modify(modifiedContact.getId());
        NewContact contact = new NewContact()
                .withId(modifiedContact.getId())
                .withName(app.getProperty("web.firstNameEdited")).withMiddlename(app.getProperty("web.middleNameEdited"))
                .withSurname(app.getProperty("web.surnameEdited"));
        app.contact().edit(contact, false);
        app.contact().submitModification();
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }
}
