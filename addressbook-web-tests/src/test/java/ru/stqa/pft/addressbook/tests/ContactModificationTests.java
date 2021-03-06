package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().returntoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
                app.getContactHelper().createContact(new NewContact(
                        "Name1","MidName1","Surname1", "[none]")); // Почему-то тест падает, если group: null
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().initContactModification(before - 1);
        app.getContactHelper().editingContactWithNameMidnameSurname(new NewContact(
                "Name1Edited", "MidName2Edited", "Surname3Edited", "[none]"), false); //Почему-то тест падает, если group: null !
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returntoHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
    }
}
