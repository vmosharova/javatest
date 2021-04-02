package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsForContacts() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new NewContact(
                    "Name1","MidName1","Surname1", "[none]")); // Почему-то тест падает, если group: null
        }
    }

    @Test
    public void testContactDeletion() {
        List<NewContact> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().choose(index);
        app.contact().delete();
        app.contact().closeAlertDeletionWindow();
        app.goTo().homePage(); //
        List<NewContact> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size()  - 1); //- Тут почему-то before = after; почему?

        before.remove(index);
        Assert.assertEquals(before, after);
    }


}
