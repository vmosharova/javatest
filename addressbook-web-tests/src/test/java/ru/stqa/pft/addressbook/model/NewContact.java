package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;

@XStreamAlias("contacts")

public class NewContact {
    @XStreamOmitField
    private int id = Integer.MAX_VALUE;;
    @Expose
    private static String group;
    @Expose
    private String name;
    @Expose
    private String middlename;
    @Expose
    private String surname;
    @Expose
    private String homePhone;
    @Expose
    private String mobilePhone;
    @Expose
    private String workPhone;
    private String allPhones;
    @Expose
    private String address;
    @Expose
    private String firstEmail;
    @Expose
    private String secondEmail;
    @Expose
    private String thirdEmail;
    private String allEmails;
    @Expose
    private File photo;


    public NewContact() {
    }

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

    public File getPhoto() {
        return photo;
    }

    public NewContact withPhoto(File photo) {
        this.photo = photo;
        return this;
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

    public NewContact withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public NewContact withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public NewContact withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }
    public NewContact withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }
    public NewContact withAddress(String address) {
        this.address = address;
        return this;
    }

    public NewContact withFirstEmail(String firstEmail) {
        this.firstEmail = firstEmail;
        return this;
    }
    public NewContact withSecondEmail(String secondEmail) {
        this.secondEmail = secondEmail;
        return this;
    }
    public NewContact withThirdEmail(String thirdEmail) {
        this.thirdEmail = thirdEmail;
        return this;
    }

    public NewContact withAllEmails(String allEmails) {
        this.allEmails = allEmails;
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

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getAllPhones() {
        return allPhones;
    }
    public String getAllEmails() {
        return allEmails;
    }
    public String getAddress() {
        return address;
    }
    public String getFirstEmail() {
        return firstEmail;
    }
    public String getSecondEmail() {
        return secondEmail;
    }
    public String getThirdEmail() {
        return thirdEmail;
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
