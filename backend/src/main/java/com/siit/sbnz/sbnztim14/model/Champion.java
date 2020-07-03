package com.siit.sbnz.sbnztim14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Champion {
    private String name;
    private String lane;
    private String role;
    private ChampionType championType;

    private boolean goodEarly;
    private boolean goodMid;
    private boolean goodLate;

    private boolean hardCC;
    private boolean hardEngage;
    private boolean disengage;
    private boolean poke;
    private boolean waveclear;
    private boolean sustain;
    private boolean utility;
    private boolean mobility;
    private boolean aoeDamage;
    private boolean splitPush;

    private List<String> goodAgainst;
    private List<String> badAgainst;
    private List<String> allPossibleLanes = new ArrayList<>();

    private int golds = 0;
    private int goldsForKill = 300;
    private ArrayList<Item> recommendedItems = new ArrayList<>();
    private Item wantedItem;
    private ArrayList<Item> toBuy = new ArrayList<>();
    private ArrayList<Item> bought = new ArrayList<>();


    @Override
    public String toString() {
        return this.getName() + " " + this.getLane() + " " + this.getRole();
    }

}
