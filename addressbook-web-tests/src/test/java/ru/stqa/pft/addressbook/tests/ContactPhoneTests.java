package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContact;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        NewContact contact = app.contact().all().iterator().next();
        NewContact contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergeEmails(NewContact contact) {
        return Arrays.asList(contact.getFirstEmail(),
                contact.getSecondEmail(),
                contact.getThirdEmail())
                .stream().filter((s) -> !s.equals(""))
                // Следующая строка почему-то не работала как .map(ContactPhoneTests::cleaned)
                // Пришлось заменить её на то, что подсказывала среда разработки
                .map((Object phone) -> cleaned((String) phone))
                .collect(Collectors.joining("\n"));
    }

    private String mergePhones(NewContact contact) {
       return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
               .stream().filter((s) -> ! s.equals(""))
               .map(ContactPhoneTests::cleaned)
               .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]","");
    }

}
