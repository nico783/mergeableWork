package com.mergeable.generic;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

public class MergeableList extends ForwardingList<Mergeable> {

	public MergeableList(List<Mergeable> list) {
		super(list);
		Collections.sort(list, new MergeableComparator());
	}

	@Override
	public boolean add(Mergeable nouveau) {
		
		for (Mergeable old : list) {
			// old: X-X-X-O-O-O
			// new: N-N-X-X-X-X
			if (nouveau.getPeriod().getDateEnd().before(DateUtils.addDays(old.getPeriod().getDateStart(), -1))) {
				super.add(nouveau);
				break;

				// old: X-X-X-O-O-O
				// new: N-N-N-X-X-X
			} else if (nouveau.getPeriod().getDateEnd().equals(DateUtils.addDays(old.getPeriod().getDateStart(), -1))) {
				if (nouveau.getType().equals(old.getType())) {
					old.setPeriod(new DayPeriod(nouveau.getPeriod().getDateStart(), old.getPeriod().getDateEnd()));
					break;
				} else {
					super.add(nouveau);
					break;
				}

				// old: X-X-X-O-O-O or old: X-X-X-O-O-O
				// new: N-N-N-N-X-X	   new: X-X-X-N-X-X	
			} else if (nouveau.getPeriod().getDateEnd().before(old.getPeriod().getDateEnd())
					&& !nouveau.getPeriod().getDateEnd().before(old.getPeriod().getDateStart())
					&& !nouveau.getPeriod().getDateStart().after(old.getPeriod().getDateStart())) {
				if (nouveau.getType().equals(old.getType())) {
					old.setPeriod(new DayPeriod(nouveau.getPeriod().getDateStart(), old.getPeriod().getDateEnd()));
					break;
				} else {
					old.setPeriod(new DayPeriod(DateUtils.addDays(nouveau.getPeriod().getDateEnd(), 1),
							old.getPeriod().getDateEnd()));
					super.add(nouveau);
					break;
				}
				
				// old: X-X-X-O-O-O 
				// new: X-X-X-X-N-X	
			} else if (nouveau.getPeriod().getDateStart().after(old.getPeriod().getDateStart())
					&& nouveau.getPeriod().getDateEnd().before(old.getPeriod().getDateEnd())) {
				if (nouveau.getType().equals(old.getType())) {
					break;
				} else {
					Mergeable newMergeable = old.buildNew();
					newMergeable.setType(old.getType());
					newMergeable.setPeriod(new DayPeriod(old.getPeriod().getDateStart(),
							DateUtils.addDays(nouveau.getPeriod().getDateStart(), -1)));
					old.setPeriod(new DayPeriod(DateUtils.addDays(nouveau.getPeriod().getDateEnd(), 1),
							old.getPeriod().getDateEnd()));
					super.add(nouveau);
					super.add(newMergeable);
					break;
				}
				
				// old: X-X-X-O-O-O or old: X-X-X-O-O-O or old: X-X-X-O-O-O
				// new: N-N-N-N-N-N	   new: X-X-X-N-N-N	   new: X-X-X-X-N-N
			} else if (nouveau.getPeriod().getDateEnd().equals(old.getPeriod().getDateEnd())) {
				old.setPeriod(new DayPeriod(nouveau.getPeriod().getDateStart(), old.getPeriod().getDateEnd()));
				old.setType(nouveau.getType());
				break;
				
				// old: X-X-X-O-O-O-X-X-O
				// new: N-N-N-N-N-N-N-X-X
			} else if (nouveau.getPeriod().getDateEnd().after(old.getPeriod().getDateEnd())
					&& (!nouveau.getPeriod().getDateStart().after(old.getPeriod().getDateStart())
							|| nouveau.getType().equals(old.getType()))) {
				Mergeable oldNext;

				Date oldDateStart = min(nouveau.getPeriod().getDateStart(), old.getPeriod().getDateStart());
				Date oldDateEnd;
				if (list.indexOf(old) < list.size() - 1) {
					oldNext = list.get(list.indexOf(old) + 1);
					oldDateEnd = min(nouveau.getPeriod().getDateEnd(),
									DateUtils.addDays(oldNext.getPeriod().getDateStart(), -1));
				} else {
					oldNext = null;
					oldDateEnd = nouveau.getPeriod().getDateEnd();
				}
				old.setPeriod(new DayPeriod(oldDateStart, oldDateEnd));
				old.setType(nouveau.getType());
				nouveau.setPeriod(new DayPeriod(min(DateUtils.addDays(oldDateEnd, 1), nouveau.getPeriod().getDateEnd()),
						nouveau.getPeriod().getDateEnd()));
				if (oldNext == null || nouveau.getPeriod().getDateEnd().equals(old.getPeriod().getDateEnd())) {
					break;
				}
				
				// old: X-X-X-O-O-O-X-X-O
				// new: X-X-X-X-N-N-N-X-X (+ types différents)
			} else if (nouveau.getPeriod().getDateEnd().after(old.getPeriod().getDateEnd())
					&& nouveau.getPeriod().getDateStart().after(old.getPeriod().getDateStart())
					&& !nouveau.getType().equals(old.getType())) {
				Date oldDateStart = old.getPeriod().getDateStart();
				Date oldDateEnd = DateUtils.addDays(nouveau.getPeriod().getDateStart(), -1);
				old.setPeriod(new DayPeriod(oldDateStart, oldDateEnd));
			}
		}

		Collections.sort(list, new MergeableComparator());
		
		if(list.isEmpty()){
			super.add(nouveau);
		}
		
		return true;
	}
	
	@Override
	public boolean addAll(Collection<? extends Mergeable> c) {
		for (Mergeable mergeable : c) {
			this.add(mergeable);
		}
		return true;
	}

	private Date min(Date date1, Date date2){
		if(date2.before(date1)){
			return date2;
		}
		return date1;
	}

}
