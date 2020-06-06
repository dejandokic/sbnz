package com.siit.sbnz.sbnztim14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllyChampion extends Champion {
    private String playType = "aggro";

    public AllyChampion(Champion cha, String playType) {
        super(cha.getName(), cha.getLane(), cha.getRole(), cha.isGoodEarly(), cha.isGoodMid(), cha.isGoodLate(),
                cha.isHardCC(), cha.isHardEngage(), cha.isDisengage(), cha.isPoke(), cha.isWaveclear(),
                cha.isSustain(), cha.isUtility(), cha.isMobility(), cha.isAoeDamage(), cha.isSplitPush(),
                cha.getGoodAgainst(), cha.getBadAgainst(), cha.getAllPossibleLanes());
        this.playType = playType;
    }
}
