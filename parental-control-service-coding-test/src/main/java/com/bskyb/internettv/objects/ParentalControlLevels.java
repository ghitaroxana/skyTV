package com.bskyb.internettv.objects;

public enum ParentalControlLevels {

	U(5), PG(4), P12(3), P15(2), P18(1);
	private final int val;

	ParentalControlLevels(int restrictiveVal) {
		val = restrictiveVal;
	}

	public int getVal() {
		return val;
	}
}
