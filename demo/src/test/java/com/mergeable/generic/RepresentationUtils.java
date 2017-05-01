package com.mergeable.generic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

@Deprecated
public class RepresentationUtils {

	public static void print(List<? extends Mergeable> mergeables) {

		List<MergeableImplMock> mocks = new ArrayList<>();
		for (Mergeable m : mergeables) {
			mocks.add((MergeableImplMock) m);
		}

		List<Date> window = getWindow(mocks);

		String[][] representation = new String[window.size()][2];
		int i = 0;
		for (Date d : window) {

			MergeableImplMock s = getMergeableImplMock(d, mocks);

			if (s != null) {
				if (((MergeableTypeImplMock) s.getType()).isBlack()) {
					representation[i][1] = "[ B ]";
				} else if (((MergeableTypeImplMock) s.getType()).isGreen()) {
					representation[i][1] = "[ G ]";
				} else {
					representation[i][1] = "[ R ]";
				}
			} else {
				representation[i][1] = "[   ]";
			}

			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(new Date(d.getTime()));
			int jour = calendar.get(Calendar.DAY_OF_MONTH);
			representation[i][0] = "  " + Integer.toString(jour) + (Integer.toString(jour).length() == 1 ? "  " : " ");
			i++;
		}

		for (int j = 0; j < window.size(); j++) {
			System.out.print(representation[j][0]);
		}
		System.out.println();
		for (int j = 0; j < window.size(); j++) {
			System.out.print(representation[j][1]);
		}
		System.out.println();
		System.out.println();
	}

	private static MergeableImplMock getMergeableImplMock(Date d, List<MergeableImplMock> standbyDuties) {
		for (MergeableImplMock s : standbyDuties) {
			if (!d.after(DateUtils.truncate(s.getDateEnd(), Calendar.DATE))
					&& !d.before(DateUtils.truncate(s.getDateStart(), Calendar.DATE))) {
				return s;
			}
		}
		return null;
	}

	private static List<Date> getWindow(List<MergeableImplMock> standbyDuties) {
		Date first = DateUtils.truncate(getFirstDate(standbyDuties), Calendar.DATE);
		Date last = DateUtils.truncate(getLastDate(standbyDuties), Calendar.DATE);

		List<Date> window = new ArrayList<>();
		Date date = DateUtils.addDays(new Date(first.getTime()), -1);
		while (date.before(last)) {
			date = DateUtils.addDays(date, 1);
			window.add(new Date(date.getTime()));
		}
		return window;
	}

	static Date getFirstDate(List<MergeableImplMock> standbyDuties) {
		Date first = standbyDuties.get(0).getDateStart();
		for (MergeableImplMock s : standbyDuties) {
			if (s.getDateStart().before(first)) {
				first = s.getDateStart();
			}
		}
		return first;
	}

	static Date getLastDate(List<MergeableImplMock> standbyDuties) {
		Date last = standbyDuties.get(0).getDateEnd();
		for (MergeableImplMock s : standbyDuties) {
			if (s.getDateEnd().after(last)) {
				last = s.getDateEnd();
			}
		}
		return last;
	}

}
