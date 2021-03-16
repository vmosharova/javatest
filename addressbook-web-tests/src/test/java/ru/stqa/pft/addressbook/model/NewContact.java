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

    @Override
    public String toString() {
        return "NewContact{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewContact that = (NewContact) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return surname != null ? surname.equals(that.surname) : that.surname == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }
}
