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
}
