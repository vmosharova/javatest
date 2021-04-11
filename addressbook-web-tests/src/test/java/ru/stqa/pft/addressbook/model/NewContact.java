package ru.stqa.pft.addressbook.model;

public class NewContact {
    private int id = Integer.MAX_VALUE;;
    private static String group;
    private String name;
    private String middlename;
    private String surname;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewContact that = (NewContact) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return surname != null ? surname.equals(that.surname) : that.surname == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }

    public NewContact withId(int id) {
        this.id = id;
        return this;
    }

    public NewContact withGroup(String group) {
        NewContact.group = group;
        return this;
    }

    public NewContact withName(String name) {
        this.name = name;
        return this;
    }

    public NewContact withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public NewContact withSurname(String surname) {
        this.surname = surname;
        return this;
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

}
