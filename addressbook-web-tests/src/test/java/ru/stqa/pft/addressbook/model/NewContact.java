package ru.stqa.pft.addressbook.model;

public class NewContact {
    private int id;
    private static String group;
    private final String name;
    private final String middlename;
    private final String surname;

    public NewContact(String name, String middlename, String surname, String group) {
        this.id = Integer.MAX_VALUE;
        this.name = name;
        this.middlename = middlename;
        this.surname = surname;
        this.group = group;
    }

    public NewContact(int id, String name, String middlename, String surname, String group) {
        this.id = id;
        this.name = name;
        this.middlename = middlename;
        this.surname = surname;
        this.group = group;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "NewContact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", middlename='" + middlename + '\'' +
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
