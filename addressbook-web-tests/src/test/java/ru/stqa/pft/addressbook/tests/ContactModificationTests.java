package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsForContacts() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new NewContact(
                    "Name1","MidName1","Surname1", "[none]")); // Почему-то тест падает, если group: null
        }
    }

    @Test
    public void testContactModification() {
        List<NewContact> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().modify(index);
        NewContact contact = new NewContact(before.get(index).getId(),
                "Name1Edited", "MidName2Edited", "Surname3Edited", "[none]");
        app.contact().edit(contact, false);
        app.contact().submitModification();
        app.goTo().homePage();
        List<NewContact> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super NewContact> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
