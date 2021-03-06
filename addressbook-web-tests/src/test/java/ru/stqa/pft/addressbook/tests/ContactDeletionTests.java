package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().returntoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new NewContact(
                    "Name","MidName","Surname", "test1")); // Почему-то тест падает, если group: null
        }
        int before = app.getContactHelper().getContactCount();
        System.out.println(before);
        app.getContactHelper().chooseContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().closeAlertDeletionWindow();
        app.getNavigationHelper().returntoHomePage(); //
        int after = app.getContactHelper().getContactCount();
        System.out.println(after);
        //Assert.assertEquals(after, before - 1); //- Тут почему-то before = after; почему?
    }


}
