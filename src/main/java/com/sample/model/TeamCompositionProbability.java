package com.sample.model;

import org.kie.api.definition.type.PropertyReactive;

@PropertyReactive
public class TeamCompositionProbability {
    private String team;
    private int attackComp;
    private int catchComp;
    private int protectComp;
    private int siegeComp;
    private int splitComp;

    public TeamCompositionProbability(String team) {
        this.team = team;
        this.attackComp = 0;
        this.catchComp = 0;
        this.protectComp = 0;
        this.siegeComp = 0;
        this.splitComp = 0;
    }

    public TeamCompositionProbability() {
        this.team = "";
        this.attackComp = 0;
        this.catchComp = 0;
        this.protectComp = 0;
        this.siegeComp = 0;
        this.splitComp = 0;
    }

    public TeamCompositionProbability(String team, int attackComp, int catchComp, int protectComp, int siegeComp, int splitComp) {
        this.team = team;
        this.attackComp = attackComp;
        this.catchComp = catchComp;
        this.protectComp = protectComp;
        this.siegeComp = siegeComp;
        this.splitComp = splitComp;
    }
    
    public void incAttackComp(int inc) {
    	this.attackComp += inc;
    	System.out.println(this.toString());
    }
    
    public void incCatchComp(int inc) {
    	this.catchComp += inc;
    	System.out.println(this.toString());
    }
    
    public void incProtectComp(int inc) {
    	this.protectComp += inc;
    	System.out.println(this.toString());
    }
    
    public void incSiegeComp(int inc) {
    	this.siegeComp += inc;
    	System.out.println(this.toString());
    }
    
    public void incSplitComp(int inc) {
    	this.splitComp += inc;
    	System.out.println(this.toString());
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getAttackComp() {
        return attackComp;
    }

    public void setAttackComp(int attackComp) {
        this.attackComp = attackComp;
    }

    public int getCatchComp() {
        return catchComp;
    }

    public void setCatchComp(int catchComp) {
        this.catchComp = catchComp;
    }

    public int getProtectComp() {
        return protectComp;
    }

    public void setProtectComp(int protectComp) {
        this.protectComp = protectComp;
    }

    public int getSiegeComp() {
        return siegeComp;
    }

    public void setSiegeComp(int siegeComp) {
        this.siegeComp = siegeComp;
    }

    public int getSplitComp() {
        return splitComp;
    }

    public void setSplitComp(int splitComp) {
        this.splitComp = splitComp;
    }

	@Override
	public String toString() {
		return "TeamCompositionProbability [team=" + team + ", attackComp=" + attackComp + ", catchComp=" + catchComp
				+ ", protectComp=" + protectComp + ", siegeComp=" + siegeComp + ", splitComp=" + splitComp + "]";
	}
}
