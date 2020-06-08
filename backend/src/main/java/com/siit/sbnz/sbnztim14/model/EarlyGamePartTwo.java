package com.siit.sbnz.sbnztim14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EarlyGamePartTwo {
    private String lane;
    // laneState = 1 => lane situation is extremely bad
    // laneState = 2 => lane situation is okay, but we can try something new to take the lead
    // laneState = 3 => lane situation is extremely good, keep going!
    private int laneState;
    // enemyInfo = 1 => shove
    // enemyInfo = 2 => freeze
    // enemyInfo = 3 => block
    private int enemyInfo;
    // enemyJunglerInfo = 1 => rotate
    // enemyJunglerInfo = 2 => counter jungle
    // enemyJunglerInfo = 3 => only farm
    // enemyJunglerInfo = 4 => camp top
    // enemyJunglerInfo = 5 => camp mid
    // enemyJunglerInfo = 6 => camp bottom
    private int enemyJunglerInfo;
    private int laneWarningCount;
    private boolean adviceGiven;

    public EarlyGamePartTwo(String lane, int laneState, int enemyInfo, int enemyJunglerInfo) {
        this.lane = lane;
        this.laneState = laneState;
        this.enemyInfo = enemyInfo;
        this.enemyJunglerInfo = enemyJunglerInfo;
        this.laneWarningCount = 0;
        this.adviceGiven = false;
    }
}
