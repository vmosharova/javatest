package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsForContacts() {
        app.getNavigationHelper().returntoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new NewContact(
                    "Name1","MidName1","Surname1", "[none]")); // Почему-то тест падает, если group: null
        }
    }

    @Test
    public void testContactDeletion() {
        List<NewContact> before = app.getContactHelper().getContactList();
        app.getContactHelper().chooseContact(before.size() - 1);
        app.getContactHelper().deleteContact();
        app.getContactHelper().closeAlertDeletionWindow();
        app.getNavigationHelper().returntoHomePage(); //
        List<NewContact> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size()  - 1); //- Тут почему-то before = after; почему?

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }


}
