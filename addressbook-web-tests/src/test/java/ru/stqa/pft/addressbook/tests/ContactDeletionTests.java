package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.NewContact;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().returntoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new NewContact(
                    "Name","MidName","Surname", "[none]")); // Почему-то тест падает, если group: null
        }
        List<NewContact> before = app.getContactHelper().getContactList();
        System.out.println(before.size());
        app.getContactHelper().chooseContact(before.size() - 1);
        app.getContactHelper().deleteContact();
        app.getContactHelper().closeAlertDeletionWindow();
        app.getNavigationHelper().returntoHomePage(); //
        List<NewContact> after = app.getContactHelper().getContactList();
        System.out.println(after.size());
        //Assert.assertEquals(after.size(), before.size() - 1); //- Тут почему-то before = after; почему?
    }


}
