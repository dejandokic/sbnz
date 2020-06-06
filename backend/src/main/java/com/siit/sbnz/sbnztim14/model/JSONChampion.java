package com.siit.sbnz.sbnztim14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class JSONChampion {
    private String name;
    private JSONClass champClass;
    private JSONLane lane;
    private JSONRole role;
    private JSONGamePhase game;
    private ArrayList<String> goodAgainst;
    private ArrayList<String> badAgainst;

    public Champion convertToEntity() {
        Champion champ = new Champion();
        champ.setName(this.getName());
        ArrayList<String> allPossibleLanes = new ArrayList<>();
        if(lane.isAdc()) {
            allPossibleLanes.add("adc");
        }
        if(lane.isJungle()) {
            allPossibleLanes.add("jungle");
        }
        if(lane.isMid()) {
            allPossibleLanes.add("mid");
        }
        if(lane.isTop()) {
            allPossibleLanes.add("top");
        }
        if(lane.isSupport()) {
            allPossibleLanes.add("support");
        }
        champ.setAllPossibleLanes(allPossibleLanes);
        champ.setLane(allPossibleLanes.get(0));

        String champClass = "";

        if(this.getChampClass().isJuggernaut()) {
            champClass = "juggernaut";
        }
        if(this.getChampClass().isDiver()) {
            champClass = "diver";
        }
        if(this.getChampClass().isAssassin()) {
            champClass = "assassin";
        }
        if(this.getChampClass().isSkirmisher()) {
            champClass = "skirmisher";
        }
        if(this.getChampClass().isMarksman()) {
            champClass = "marksman";
        }
        if(this.getChampClass().isBurst()) {
            champClass = "burst";
        }
        if(this.getChampClass().isBattlemage()) {
            champClass = "battlemage";
        }
        if(this.getChampClass().isArtillery()) {
            champClass = "artillery";
        }
        if(this.getChampClass().isVanguard()) {
            champClass = "vanguard";
        }
        if(this.getChampClass().isWarden()) {
            champClass = "warden";
        }
        if(this.getChampClass().isCatcher()) {
            champClass = "catcher";
        }
        if(this.getChampClass().isEnchanter()) {
            champClass = "enchanter";
        }

        champ.setRole(champClass);

        champ.setGoodEarly(this.getGame().isEarly());
        champ.setGoodMid(this.getGame().isMid());
        champ.setGoodLate(this.getGame().isLate());

        champ.setHardCC(this.getRole().isHardCC());
        champ.setHardEngage(this.getRole().isHardEngage());
        champ.setDisengage(this.getRole().isDisengage());
        champ.setPoke(this.getRole().isPoke());
        champ.setWaveclear(this.getRole().isWaveclear());
        champ.setSustain(this.getRole().isSustain());
        champ.setUtility(this.getRole().isUtility());
        champ.setMobility(this.getRole().isMobility());
        champ.setAoeDamage(this.getRole().isAoeDamage());
        champ.setSplitPush(this.getRole().isSplit());

        champ.setGoodAgainst(this.getGoodAgainst());
        champ.setBadAgainst(this.getBadAgainst());

        return champ;
    }
}
