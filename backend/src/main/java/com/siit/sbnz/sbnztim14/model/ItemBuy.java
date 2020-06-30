package com.siit.sbnz.sbnztim14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
