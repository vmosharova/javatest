package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.NewContact;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<NewContact> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
        ;
    }

    private void saveAsJson(List<NewContact> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsXml(List<NewContact> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(NewContact.class);
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private static void saveAsCsv(List<NewContact> contacts, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (NewContact contact : contacts) {
                writer.write(String.format("%s;%s;%s\n", contact.getName(), contact.getMiddlename(),
                        contact.getSurname()));
            }
        }
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
