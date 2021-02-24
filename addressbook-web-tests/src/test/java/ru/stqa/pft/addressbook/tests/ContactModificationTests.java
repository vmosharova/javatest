package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getContactHelper().initContactModification();
        app.getContactHelper().editingContactWithNameMidnameSurname(new NewContact("Name1Edited", "MidName2Edited", "Surname3Edited", null), false);
        app.getContactHelper().submitContactModification();
    }
}
