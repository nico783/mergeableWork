package com.mergeable.implementation;

import com.mergeable.generic.MergeableType;

public enum StandbyDutyType implements MergeableType {

	BLACK,

	RED,

	GREEN;

	public boolean isBlack() {
		return this.equals(BLACK);
	}

	public boolean isRed() {
		return this.equals(RED);
	}

	public boolean isGreen() {
		return this.equals(GREEN);
	}

}
