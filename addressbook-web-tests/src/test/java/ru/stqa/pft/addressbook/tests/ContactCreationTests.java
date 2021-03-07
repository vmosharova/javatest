package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().returntoHomePage();
        List<NewContact> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().gotoCreateNewContactPage();
        app.getContactHelper().newContactCreationWithNameMidnameSurname(new NewContact("Name1", "MidName2", "Surname3", "[none]"), true);
        app.getNavigationHelper().returntoHomePage();
        List<NewContact> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
        app.getSessionHelper().logOut();
    }

}
