package com.siit.sbnz.sbnztim14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Item {

    private String name;
    private int attackDamage = 0;
    private int abilityPower = 0;
    private int magicResist = 0;
    private int armor = 0;
    private ArrayList<Item> items = new ArrayList<>();
    private int gold;

    private boolean hasBeenInBackwards = false;

    public Item(Item item){
        this(item.getName(),item.getAttackDamage(),item.getAbilityPower(),item.getMagicResist(),item.getArmor(),new ArrayList<Item>(item.getItems()), item.getGold(), false);
    }

    public Item(String name, int attackDamage, int abilityPower, int magicResist, int armor, ArrayList<Item> items, int gold) {
        this.name = name;
        this.attackDamage = attackDamage;
        this.abilityPower = abilityPower;
        this.magicResist = magicResist;
        this.armor = armor;
        this.items = items;
        this.gold = gold;
        this.hasBeenInBackwards = false;
    }
}
