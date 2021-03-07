package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

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
        app.getContactHelper().editingContactWithNameMidnameSurname(new NewContact(
                "Name1Edited", "MidName2Edited", "Surname3Edited", "[none]"), false); //Почему-то тест падает, если group: null !
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returntoHomePage();
        List<NewContact> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
    }
}
