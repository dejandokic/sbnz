package com.siit.sbnz.sbnztim14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MidGame {
    private String lane;
    private int allyKills;
    private int enemyKills;
    private int allyTowers;
    private int enemyTowers;
    private int allyObjectives;
    private int enemyObjectives;


}
