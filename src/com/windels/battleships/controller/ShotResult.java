package com.windels.battleships.controller;

public enum ShotResult {
	
	HIT("HIT! Good job Captain!"),
	MISS("MISS! What were you thinking?!"),
	SUNK("SUNK! Praise the gods of the seven seas!"),
	DUPLICATE("You've already shot there!"),
	INVALID("That shot wasn't valid");
	
	private final String message;
	
	ShotResult(String aMessage) {
		message = aMessage;
	}
	
	public String getMessage() {
		return message;
	}
}
