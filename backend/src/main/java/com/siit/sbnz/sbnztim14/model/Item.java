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
    private int attackDamage;
    private int abilityPower;
    private int magicResist;
    private int armor;
    private ArrayList<Item> items = new ArrayList<>();
}
