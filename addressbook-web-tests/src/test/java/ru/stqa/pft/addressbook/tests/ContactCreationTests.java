package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.NewContact;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() {
        List<Object[]> list = new ArrayList<Object[]>();
        File photo = new File("src/test/resources/stru.png");
        list.add(new Object[]{new NewContact().withName("name1")
                .withMiddlename("midname1").withSurname("surname 1")
                .withAddress("address 1").withFirstEmail("mail1")
                .withSecondEmail("mail12").withThirdEmail("mail13")
                .withHomePhone("phone1").withMobilePhone("phone12").withWorkPhone("phone13")
                .withGroup("test1").withPhoto(photo)});
        list.add(new Object[]{new NewContact().withName("name2")
                .withMiddlename("midname2").withSurname("surname 2")
                .withAddress("address 2").withFirstEmail("mail2")
                .withSecondEmail("mail22").withThirdEmail("mail23")
                .withHomePhone("phone2").withMobilePhone("phone22").withWorkPhone("phone33")
                .withGroup("test2").withPhoto(photo)});
        list.add(new Object[]{new NewContact().withName("name3")
                .withMiddlename("midname3").withSurname("surname 3")
                .withAddress("address 3").withFirstEmail("mail3")
                .withSecondEmail("mail32").withThirdEmail("mail33")
                .withHomePhone("phone3").withMobilePhone("phone32").withWorkPhone("phone33")
                .withGroup("test3").withPhoto(photo)});
        return list.iterator();
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(NewContact contact) throws Exception {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().createNewContactPage();
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        app.goTo().homePage();

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    /* Тест, проверяющий путь к файлу и наличие файоа:
    @Test
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/stru.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    } */

}
