package ru.stqa.pft.addressbook.model;

public class NewContact {
    private static String group;
    private final String name;
    private final String middlename;
    private final String surname;

    public NewContact(String name, String middlename, String surname, String group) {
        this.name = name;
        this.middlename = middlename;
        this.surname = surname;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getSurname() {
        return surname;
    }

    public static String getGroup() {
        return group;
    }
}
