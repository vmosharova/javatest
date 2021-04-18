package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.NewContact;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<NewContact> contacts = generateContacts(count);
        save(contacts, file);
    }

    private static void save(List<NewContact> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (NewContact contact : contacts) {
            writer.write(String.format("%s;%s;%s\n", contact.getName(), contact.getMiddlename(),
                    contact.getSurname()));
        }
        writer.close();
    }

    private static List<NewContact> generateContacts(int count) {
        List<NewContact> contacts = new ArrayList<NewContact>();
        for (int i = 0; i < count; i++) {
            contacts.add(new NewContact().withName(String.format("name %s", i))
                    .withMiddlename(String.format("midname %s", i))
                    .withSurname(String.format("surname %s", i)));
        }
        return contacts;
    }
}
