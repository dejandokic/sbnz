package com.siit.sbnz.sbnztim14.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EarlyJungleRule {

    private String allyType;
    private String skillsName;
    private boolean hardCC;
    private boolean hardEngage;
    private boolean poke;
    private boolean waveclear;
    private boolean disengage;
    private boolean sustain;
    private boolean utility;
    private String advice;

}
