package com.siit.sbnz.sbnztim14.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FirstInteraction {
    private String allyTop;
    private String allyTopPrefer;
    private String allyJungle;
    private String allyJunglePrefer;
    private String allyMid;
    private String allyMidPrefer;
    private String allySupport;
    private String allySupportPrefer;
    private String allyADC;
    private String allyADCPrefer;

    private String enemyTop;
    private String enemyJungle;
    private String enemyMid;
    private String enemySupport;
    private String enemyADC;
}
