package com.siit.sbnz.sbnztim14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdviceStorage {
    private ArrayList<String> advices = new ArrayList<>();
    private ArrayList<String> event = new ArrayList<>();

    public void addAdvice(String lane, String period, String adviceText) {
        advices.add("[" +  lane + ", " + period + "] " + adviceText);
    }

    public void addEventAdvice(String champion, String advice){
        event.add("As " + champion + " you should " + advice);
    }

}
