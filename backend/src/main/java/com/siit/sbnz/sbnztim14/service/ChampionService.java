package com.siit.sbnz.sbnztim14.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siit.sbnz.sbnztim14.model.Champion;
import com.siit.sbnz.sbnztim14.model.JSONChampList;
import com.siit.sbnz.sbnztim14.model.JSONChampion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ChampionService {

    public ArrayList<Champion> getAllChampions() {
        ArrayList<Champion> champions = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JSONChampList champList = null;
        try {
             champList = objectMapper.readValue(new File("src/main/resources/data.json"), JSONChampList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (JSONChampion jsonChampion : champList.getChampions()) {
            champions.add(jsonChampion.convertToEntity());
        }

        return champions;
    }

    public Champion getChampionByName(String name) {
        ArrayList<Champion> allChampions = getAllChampions();
        Champion wantedChamp = null;
        for (Champion champion : allChampions) {
            if (champion.getName().equals(name)) {
                wantedChamp = champion;
            }
        }
        return wantedChamp;
    }
}
