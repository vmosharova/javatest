package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {      //если группы нет, создаём её
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {      //если группы нет, создаём её
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returntoGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
    /*Или вот такой assertion:
    for (int i = 0; i < after.size(); i++) {
      Assert.assertEquals(before.get(i), after.get(i));
    }*/
  }


}
