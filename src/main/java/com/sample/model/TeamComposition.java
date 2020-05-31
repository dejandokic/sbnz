package com.sample.model;

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

	public String getComposition() {
		return composition;
	}
	
	public void setComposition(String comp) {
		this.composition = comp;
	}

}
