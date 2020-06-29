package com.siit.sbnz.sbnztim14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    private String lane;
    private AllyChampion allyChampion;
    private EnemyChampion enemyChampion;
    private EventType eventType;
}
