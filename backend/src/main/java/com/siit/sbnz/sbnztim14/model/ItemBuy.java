package com.siit.sbnz.sbnztim14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.kie.api.definition.type.Expires;
//import org.kie.api.definition.type.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemBuy {
    private AllyChampion allyChampion;
    private EnemyChampion enemyChampion;
    private String lane;
    private int value;
}
