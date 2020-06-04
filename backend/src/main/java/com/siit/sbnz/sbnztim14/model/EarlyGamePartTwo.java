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
    private int laneState;
    private int enemyInfo;
    private int enemyJunglerInfo;
    private int laneWarningCount;
}
