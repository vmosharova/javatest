package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new NewContact(
                    "Name","MidName","Surname", "[none]")); // Почему-то тест падает, если group: null
        }
        app.getContactHelper().chooseContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().closeAlertDeletionWindow();
    }


}
