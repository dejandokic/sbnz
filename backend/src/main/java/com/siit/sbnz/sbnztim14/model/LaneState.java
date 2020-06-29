package com.siit.sbnz.sbnztim14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LaneState {
    private String lane;
    private Champion allyChampion;
    private Champion enemyChampion;
    private Champion allySupport;
    private Champion enemySupport;
    private int allyKills = 0;
    private int enemyKills = 0;
    private int allyDestroyedTowers = 0;
    private int enemyDestroyedTowers = 0;
}
