package com.siit.sbnz.sbnztim14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllyChampion extends Champion {
    private String playType = "aggro";

    public AllyChampion(Champion cha, String playType) {
        super(cha.getName(), cha.getLane(), cha.getRole(), cha.getChampionType(), cha.isGoodEarly(), cha.isGoodMid(),
                cha.isGoodLate(), cha.isHardCC(), cha.isHardEngage(), cha.isDisengage(), cha.isPoke(), cha.isWaveclear(),
                cha.isSustain(), cha.isUtility(), cha.isMobility(), cha.isAoeDamage(), cha.isSplitPush(),
                cha.getGoodAgainst(), cha.getBadAgainst(), cha.getAllPossibleLanes(), cha.getGolds(), cha.getGoldsForKill(),
                cha.getRecommendedItems(), cha.getWantedItem(), cha.getToBuy(), cha.getBought());
        this.playType = playType;
    }

    @Override
    public String toString() {
        return "=>  [" + this.getLane() + "] " + this.getName() + ", Golds: " + this.getGolds() + ", GoldForKill: " + this.getGoldsForKill();
    }
}
