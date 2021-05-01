package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.NewContact;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
    // protected final ApplicationManager app = new ApplicationManager(BrowserType.IE);
    // protected final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);


    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();

    }

    public ApplicationManager getApp() {
        return app;
    }


    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uIGroups = app.group().all();
            assertThat(uIGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uIContacts = app.contact().all();
            assertThat(uIContacts, equalTo(dbContacts.stream()
                    .map((c) -> new NewContact().withName(c.getName()).withMiddlename(c.getMiddlename())
                            .withSurname(c.getSurname()))
                    .collect(Collectors.toSet())));
        }
    }

}
