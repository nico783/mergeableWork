package com.mergeable.generic;

import java.util.Comparator;

public class MergeableComparator implements Comparator<Mergeable> {

	@Override
	public int compare(Mergeable o1, Mergeable o2) {

		if (o1.getPeriod().getDateStart().before(o2.getPeriod().getDateStart())) {
			return -1;
		} else if (o1.getPeriod().getDateStart().after(o2.getPeriod().getDateStart())) {
			return 1;
		}

		return 0;
	}

}
