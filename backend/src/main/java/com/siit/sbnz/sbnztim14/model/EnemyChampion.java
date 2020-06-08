package com.siit.sbnz.sbnztim14.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EnemyChampion extends Champion {

    public EnemyChampion(Champion cha) {
        super(cha.getName(), cha.getLane(), cha.getRole(), cha.isGoodEarly(), cha.isGoodMid(), cha.isGoodLate(),
                cha.isHardCC(), cha.isHardEngage(), cha.isDisengage(), cha.isPoke(), cha.isWaveclear(),
                cha.isSustain(), cha.isUtility(), cha.isMobility(), cha.isAoeDamage(), cha.isSplitPush(),
                cha.getGoodAgainst(), cha.getBadAgainst(), cha.getAllPossibleLanes());
    }
}
