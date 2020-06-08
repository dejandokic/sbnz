package com.siit.sbnz.sbnztim14.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EarlyGameRule {

    private String allyType;
    private String enemyType;
    private String skillsName;
    private String playType;
    private boolean hardCC;
    private boolean hardEngage;
    private boolean poke;
    private boolean waveclear;
    private boolean disengage;
    private boolean sustain;
    private boolean utility;
    private String advice;

}
