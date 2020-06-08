package com.siit.sbnz.sbnztim14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TeamComposition {

    private String composition = "";

    public TeamComposition() {
        //Default value
        composition = "attack";
    }


}
