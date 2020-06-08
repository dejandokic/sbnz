package com.siit.sbnz.sbnztim14.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThirdInteraction {
    private int topAllyKills;
    private int topEnemyKills;
    private int topAllyTowers;
    private int topEnemyTowers;
    private int midAllyKills;
    private int midEnemyKills;
    private int midAllyTowers;
    private int midEnemyTowers;
    private int bottomAllyKills;
    private int bottomEnemyKills;
    private int bottomAllyTowers;
    private int bottomEnemyTowers;
    private int allyObjectives;
    private int enemyObjectives;
}
