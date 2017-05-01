package com.mergeable.generic;

public interface Mergeable {

	DayPeriod getPeriod();

	void setPeriod(DayPeriod period);

	MergeableType getType();

	void setType(MergeableType type);

	Mergeable buildNew();

}
