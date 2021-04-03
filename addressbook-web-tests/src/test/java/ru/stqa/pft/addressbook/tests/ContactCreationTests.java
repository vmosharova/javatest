package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        Set<NewContact> before = app.contact().all();
        app.goTo().createNewContactPage();
        NewContact contact = new NewContact().withName("Name1").withGroup("[none]"); // "MidName2", "Surname3", "[none]");
        app.contact().newContactCreationWithNameMidnameSurname(contact, true);
        app.goTo().homePage();
        Set<NewContact> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()); // максимальный среди всех идентификаторов
        before.add(contact);
        Assert.assertEquals(before, after);

        app.session().logOut();
    }

}
