package com.sample.model;

public class JungleEarlyPlayType {
	private String lane;
	private String playType;

	public JungleEarlyPlayType(String lane, String playType) {
		super();
		this.lane = lane;
		this.playType = playType;
	}

	public JungleEarlyPlayType() {
		super();
	}

	public String getLane() {
		return lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}

	public String getPlayType() {
		return playType;
	}

	public void setPlayType(String playType) {
		this.playType = playType;
	}
}
