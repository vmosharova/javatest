package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        NewContact contact = app.contact().all().iterator().next();
        NewContact contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    }
}
