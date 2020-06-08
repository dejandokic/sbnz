package com.siit.sbnz.sbnztim14.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MidLateGameRule {

    private String allyType;
    private String teamComp;
    private String skillsName;
    private boolean hardCC;
    private boolean hardEngage;
    private boolean poke;
    private boolean waveclear;
    private boolean disengage;
    private boolean sustain;
    private boolean utility;
    private boolean mobility;
    private boolean splitPush;
    private String advice;


}
