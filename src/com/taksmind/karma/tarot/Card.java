package com.taksmind.karma.tarot;

public class Card {
	private String name;
	private String properties;
	
	public Card(String name, String properties) {
		this.name = name;
		this.properties = properties;
	}
	
	public String getName() {
		return(name);
	}
	
	public String getProperties() {
		return(properties);
	}
	
	public String toString() {
		return(name + ": " + properties);
	}
}
