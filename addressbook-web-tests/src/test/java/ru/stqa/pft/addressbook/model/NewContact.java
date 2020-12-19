package ru.stqa.pft.addressbook.model;

public class NewContact {
    private final String name;
    private final String middlename;
    private final String surname;

    public NewContact(String name, String middlename, String surname) {
        this.name = name;
        this.middlename = middlename;
        this.surname = surname;
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
}
