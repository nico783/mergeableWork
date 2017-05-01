package com.mergeable.generic;

import java.util.Date;

public class MergeableImplMock implements Mergeable {

	private long id;

	private Date dateStart;

	private Date dateEnd;

	private MergeableTypeImplMock type;

	public MergeableImplMock(long id, Date dateStart, Date dateEnd, MergeableTypeImplMock type) {
		super();
		this.id = id;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		MergeableImplMock other = (MergeableImplMock) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MergeableImplMock [id=" + id + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", type=" + type
				+ "]";
	}

	@Override
	public DayPeriod getPeriod() {
		return new DayPeriod(dateStart, dateEnd);
	}

	@Override
	public MergeableType getType() {
		return this.type;
	}

	@Override
	public void setPeriod(DayPeriod period) {
		this.dateStart = period.getDateStart();
		this.dateEnd = period.getDateEnd();
	}

	@Override
	public void setType(MergeableType type) {
		this.type = (MergeableTypeImplMock) type;
	}

	@Override
	public Mergeable buildNew() {
		return new MergeableImplMock(-1, dateStart, dateEnd, type);
	}

}
