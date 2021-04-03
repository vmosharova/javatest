package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

import java.util.Set;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsForContacts() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new NewContact().withName("Name1").withGroup("[none]"));
        }
    }

    @Test
    public void testContactDeletion() {
        Set<NewContact> before = app.contact().all();
        NewContact deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().homePage();
        Set<NewContact> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size()  - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }

}
