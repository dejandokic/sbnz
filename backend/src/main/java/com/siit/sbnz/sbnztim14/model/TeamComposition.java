package com.siit.sbnz.sbnztim14.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamComposition {

    private String composition = "";

    public TeamComposition() {
        //Default value
        composition = "attack";
    }

    public TeamComposition(String composition) {
        super();
        this.composition = composition;
    }

    @Override
    public String toString() {
        return "TeamComposition [composition=" + composition + "]";
    }


}
