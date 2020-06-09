package com.siit.sbnz.sbnztim14.utilities;

import com.siit.sbnz.sbnztim14.model.Champion;
import com.siit.sbnz.sbnztim14.service.ChampionService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BackwardChainingHelper {

    private List<Champion> championList;
    private ChampionService championService;

    public BackwardChainingHelper() {
        this.championList = new ArrayList<Champion>();
        this.championService = new ChampionService();
    }

    public boolean checkChampionAttackComp(String championNames, int nHardEngage, int nHardCC, int nAoeDamage) {
        System.out.println(championNames+";\n"+nHardEngage+", "+nHardCC+", "+nAoeDamage);
        return false;
    }

    public boolean checkChampionNameInList(String championNames, String champName) {
        String[] names = championNames.substring(1).split(",");
        for(String name : names) {
            if(name.equals(champName)) {
                return true;
            }
        }
        return false;
    }

}
