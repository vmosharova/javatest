package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().returntoHomePage();
        List<NewContact> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().gotoCreateNewContactPage();
        NewContact contact = new NewContact("Name1", "MidName2", "Surname3", "[none]");
        app.getContactHelper().newContactCreationWithNameMidnameSurname(contact, true);
        app.getNavigationHelper().returntoHomePage();
        List<NewContact> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super NewContact> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

        app.getSessionHelper().logOut();
    }

}
