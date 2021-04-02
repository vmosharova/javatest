package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsForContacts() {
        app.goTo().returntoHomePage();
        if (! app.contact().isThereAContact()) {
            app.contact().createContact(new NewContact(
                    "Name1","MidName1","Surname1", "[none]")); // Почему-то тест падает, если group: null
        }
    }

    @Test
    public void testContactDeletion() {
        List<NewContact> before = app.contact().getContactList();
        int index = before.size() - 1;
        app.contact().chooseContact(index);
        app.contact().deleteContact();
        app.contact().closeAlertDeletionWindow();
        app.goTo().returntoHomePage(); //
        List<NewContact> after = app.contact().getContactList();
        Assert.assertEquals(after.size(), before.size()  - 1); //- Тут почему-то before = after; почему?

        before.remove(index);
        Assert.assertEquals(before, after);
    }


}
