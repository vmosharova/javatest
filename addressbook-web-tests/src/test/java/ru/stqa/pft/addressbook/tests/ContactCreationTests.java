package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().returntoHomePage();
        int before = app.getContactHelper().getContactCount();
        app.getNavigationHelper().gotoCreateNewContactPage();
        app.getContactHelper().newContactCreationWithNameMidnameSurname(new NewContact("Name1", "MidName2", "Surname3", "test1"), true);
        app.getNavigationHelper().returntoHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before + 1);
        app.getSessionHelper().logOut();
    }

}
