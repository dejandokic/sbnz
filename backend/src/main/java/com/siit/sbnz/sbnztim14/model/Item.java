package com.siit.sbnz.sbnztim14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private String name;
    private int attackDamage = 0;
    private int abilityPower = 0;
    private int magicResist = 0;
    private int armor = 0;
    private ArrayList<Item> items = new ArrayList<>();

    public Item(Item item){
        this(item.getName(),item.getAttackDamage(),item.getAbilityPower(),item.getMagicResist(),item.getArmor(),new ArrayList<Item>());
    }
}
