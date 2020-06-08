package com.siit.sbnz.sbnztim14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JSONRole {
    private boolean hardCC;
    private boolean hardEngage;
    private boolean disengage;
    private boolean poke;
    private boolean waveclear;
    private boolean sustain;
    private boolean utility;
    private boolean mobility;
    private boolean aoeDamage;
    private boolean split;
}