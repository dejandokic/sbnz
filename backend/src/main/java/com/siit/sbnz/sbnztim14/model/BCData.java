package com.siit.sbnz.sbnztim14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BCData {
    private List<String> champNames;

    private int numJuggernaut;
    private int numDiver;
    private int numAssassin;
    private int numCatcher;
    private int numBurster;
    private int numMarksman;
    private int numArtillery;
    private int numBattlemage;
    private int numSkirmisher;

    private int numHardEngage;
    private int numHardCC;
    private int numDisengage;
    private int numPoke;
    private int numWaveclear;
    private int numSustain;
    private int numUtility;
    private int numMobility;
    private int numAoeDamage;
    private int numSplitPush;

}
