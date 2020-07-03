package com.siit.sbnz.sbnztim14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.kie.api.definition.type.Position;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FactTreeItem {
    @Position(0)
    private String parent = "";
    @Position(1)
    private String self = "";
    private List<String> children = new ArrayList<>();

    public FactTreeItem(String self) {
        this.self = self;
    }

    public String toString() {
        StringBuilder ret = new StringBuilder(self);
        ret.append(" of ").append(parent).append(", with children: ");
        for (int i=0; i < children.size(); i++) {
            ret.append(i == children.size() - 1 ? children.get(i) : children.get(i) + ", ");
        }
        return ret.toString();
    }

    public static ArrayList<FactTreeItem> getFactTree(Item item) {
        ArrayList<FactTreeItem> ret = new ArrayList<>();
        if (item == null)
            return ret;
        recursiveFactTreeCall(ret, item, "");
        return ret;
    }

    private static void recursiveFactTreeCall(ArrayList<FactTreeItem> ret, Item item, String parent) {
        FactTreeItem newItem = new FactTreeItem(item.getName());
        newItem.setParent(parent);
        for (Item child : item.getItems()) {
            newItem.getChildren().add(child.getName());
            recursiveFactTreeCall(ret, child, item.getName());
        }
        ret.add(newItem);
    }

}
