package com.siit.sbnz.sbnztim14;

import com.siit.sbnz.sbnztim14.model.*;
import com.siit.sbnz.sbnztim14.service.ItemService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemsTest {

    @Autowired
    private ItemService itemService;

    static KieContainer kContainer;
    KieSession kSession;

    @BeforeClass
    public static void beforeClass() {
        KieServices kieServices = KieServices.Factory.get();
        kContainer = kieServices.getKieClasspathContainer();
    }

    @Before
    public void beforeEach() {
        kSession = kContainer.newKieSession("ksession-rules");
    }

//    @Test
//    public void splitCompBasic() {
//
//       itemService.getAllItems().forEach(item -> {System.out.println(item.getName() + " --- " + item.getItems().size());});
//    }

    @Test
    public void getItemFactTreeTest() {

        Item item = itemService.getItemByName("Thorn Mail");
        System.out.println(item.getName());
        ArrayList<FactTreeItem> treeItems = FactTreeItem.getFactTree(item);
        for (FactTreeItem fti: treeItems) {
            System.out.println(fti);
            kSession.insert(fti);
        }
        System.out.println("\n");

//        QueryResults results = kSession.getQueryResults("backwardsChainCall", "Cloth Armor", "Thorn Mail");
//        for (QueryResultsRow row : results) {
//            System.out.println(row.get("$retItem"));
//        }
        kSession.insert(itemService.getItemByName("Wardens Mail"));
        kSession.insert(new Item(itemService.getItemByName("Cloth Armor")));

        kSession.setGlobal("toLookFor", item.getName());
        List<FactTreeItem> remainingItems = new ArrayList<>();
        kSession.setGlobal("remainingItems", remainingItems);
        kSession.getAgenda().getAgendaGroup("backward-chaining").setFocus();
        kSession.fireAllRules();
        System.out.println("\n");
        System.out.println(remainingItems);
    }
}

