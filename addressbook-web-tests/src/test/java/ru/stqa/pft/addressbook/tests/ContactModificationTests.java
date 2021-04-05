package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.NewContact;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsForContacts() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new NewContact()
                    .withName("Name1").withMiddlename("MidName1")
                    .withSurname("Surname1").withGroup("[none]"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        NewContact modifiedContact = before.iterator().next();
        app.contact().modify();
        NewContact contact = new NewContact()
                .withId(modifiedContact.getId())
                .withName("Name1").withMiddlename("MidNameEdited")
                .withSurname("SurnameEdited").withGroup("[none]");
        app.contact().edit(contact, false);
        app.contact().submitModification();
        app.goTo().homePage();
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
