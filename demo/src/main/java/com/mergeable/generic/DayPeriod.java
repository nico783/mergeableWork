package com.mergeable.generic;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class DayPeriod {

	private Date dateStart;

	private Date dateEnd;

	public DayPeriod(Date dateStart, Date dateEnd) {
		super();
		this.dateStart = DateUtils.truncate(dateStart, Calendar.DATE);
		this.dateEnd = DateUtils.truncate(dateEnd, Calendar.DATE);
		if(dateStart.after(dateEnd)){
			throw new RuntimeException("La date de début doit être inférieure à la date de fin.");
		}
	}

	public Date getDateStart() {
		return dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateEnd == null) ? 0 : dateEnd.hashCode());
		result = prime * result + ((dateStart == null) ? 0 : dateStart.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DayPeriod other = (DayPeriod) obj;
		if (dateEnd == null) {
			if (other.dateEnd != null)
				return false;
		} else if (!dateEnd.equals(other.dateEnd))
			return false;
		if (dateStart == null) {
			if (other.dateStart != null)
				return false;
		} else if (!dateStart.equals(other.dateStart))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DayPeriod [dateStart=" + dateStart + ", dateEnd=" + dateEnd + "]";
	}

}
