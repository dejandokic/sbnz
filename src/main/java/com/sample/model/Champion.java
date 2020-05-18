package com.sample.model;

import java.util.List;

public class Champion {

	private String name;
	private String lane;
	private String role;

	private boolean goodEarly;
	private boolean goodMid;
	private boolean goodLate;

	private boolean hardCC;
	private boolean hardEngage;
	private boolean disengage;
	private boolean poke;
	private boolean waveclear;
	private boolean sustain;
	private boolean utility;
	private boolean mobility;
	private boolean aoeDamage;
	private boolean splitPush;

	private List<String> goodAgainst;
	private List<String> badAgainst;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLane() {
		return lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean getGoodEarly() {
		return goodEarly;
	}

	public void setGoodEarly(boolean goodEarly) {
		this.goodEarly = goodEarly;
	}

	public boolean getGoodMid() {
		return goodMid;
	}

	public void setGoodMid(boolean goodMid) {
		this.goodMid = goodMid;
	}

	public boolean getGoodLate() {
		return goodLate;
	}

	public void setGoodLate(boolean goodLate) {
		this.goodLate = goodLate;
	}

	public boolean getHardCC() {
		return hardCC;
	}

	public void setHardCC(boolean hardCC) {
		this.hardCC = hardCC;
	}

	public boolean getHardEngage() {
		return hardEngage;
	}

	public void setHardEngage(boolean hardEngage) {
		this.hardEngage = hardEngage;
	}

	public boolean getDisengage() {
		return disengage;
	}

	public void setDisengage(boolean disengage) {
		this.disengage = disengage;
	}

	public boolean getPoke() {
		return poke;
	}

	public void setPoke(boolean poke) {
		this.poke = poke;
	}

	public boolean getWaveclear() {
		return waveclear;
	}

	public void setWaveclear(boolean waveclear) {
		this.waveclear = waveclear;
	}

	public boolean getSustain() {
		return sustain;
	}

	public void setSustain(boolean sustain) {
		this.sustain = sustain;
	}

	public boolean getUtility() {
		return utility;
	}

	public void setUtility(boolean utility) {
		this.utility = utility;
	}

	public boolean getMobility() {
		return mobility;
	}

	public void setMobility(boolean mobility) {
		this.mobility = mobility;
	}
	
	public boolean getAoeDamage() {
		return aoeDamage;
	}

	public void setAoeDamage(boolean aoeDamage) {
		this.aoeDamage = aoeDamage;
	}
	
	public boolean getSplitPush() {
		return splitPush;
	}

	public void setSplitPush(boolean splitPush) {
		this.splitPush = splitPush;
	}

	public List<String> getGoodAgainst() {
		return goodAgainst;
	}

	public void setGoodAgainst(List<String> goodAgainst) {
		this.goodAgainst = goodAgainst;
	}

	public List<String> getBadAgainst() {
		return badAgainst;
	}

	public void setBadAgainst(List<String> badAgainst) {
		this.badAgainst = badAgainst;
	}

}
