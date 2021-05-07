package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addressbook")
@XStreamAlias("contacts")

public class NewContact {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    @Expose
    @Column(name = "firstname")
    private String name;

    @Expose
    @Column(name = "middlename")
    private String middlename;

    @Expose
    @Column(name = "lastname")
    private String surname;

    @Expose
    @Transient
    //@Column(name = "home")
    //@Type(type = "text")
    private String homePhone;

    @Expose
    @Transient
    //@Column(name = "mobile")
    //@Type(type = "text")
    private String mobilePhone;

    @Expose
    @Transient
    //@Column(name = "work")
    //@Type(type = "text")
    private String workPhone;

    @Transient
    private String allPhones;

    @Expose
    @Transient
    //@Column(name = "address")
    //@Type(type = "text")
    private String address;

    @Expose
    @Transient
    //@Column(name = "email")
    //@Type(type = "text")
    private String firstEmail;

    @Expose
    @Transient
    //@Column(name = "email2")
    //@Type(type = "text")
    private String secondEmail;

    @Expose
    @Transient
    //@Column(name = "email3")
    //@Type(type = "text")
    private String thirdEmail;

    @Transient
    private String allEmails;

    @Expose
    @Transient
    //@Column(name = "photo")
    //@Type(type = "text")
    private String photo;


    public NewContact() {
    }

    public File getPhoto() {
        if (photo != null) {
            return new File(photo);
        } else {
            return null;
        }
    }

    public NewContact withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public NewContact withId(int id) {
        this.id = id;
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

    public Groups getGroups() {
        return new Groups(groups);
    }

    public NewContact inGroup(GroupData group) {
        groups.add(group);
        return this;
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

        NewContact contact = (NewContact) o;

        if (id != contact.id) return false;
        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
        if (middlename != null ? !middlename.equals(contact.middlename) : contact.middlename != null) return false;
        if (surname != null ? !surname.equals(contact.surname) : contact.surname != null) return false;
        return homePhone != null ? homePhone.equals(contact.homePhone) : contact.homePhone == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        return result;
    }
}
