package com.siit.sbnz.sbnztim14.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecondInteraction {
    private int topLaneState;
    private int topEnemyInfo;

    private int midLaneState;
    private int midEnemyInfo;

    private int bottomLaneState;
    private int bottomEnemyInfo;

    private int enemyJunglerInfo;
}
