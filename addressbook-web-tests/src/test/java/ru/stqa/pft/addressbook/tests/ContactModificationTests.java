package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().returntoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
                app.getContactHelper().createContact(new NewContact(
                        "Name1","MidName1","Surname1", "[none]")); // Почему-то тест падает, если group: null
        }
        List<NewContact> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size() - 1);
        NewContact contact = new NewContact(before.get(before.size() - 1).getId(),
                "Name1Edited", "MidName2Edited", "Surname3Edited", "[none]");
        app.getContactHelper().editingContactWithNameMidnameSurname(contact, false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returntoHomePage();
        List<NewContact> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super NewContact> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
