package com.mergeable.generic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;

import com.mergeable.implementation.StandbyDuty;
import com.mergeable.implementation.StandbyDutyType;

public class RepresentationTest {

	@Test
	public void testGetFirstDate() {
		Date dateStart = new Date();
		List<MergeableImplMock> standbyDuties = createStandbyDutiesList(dateStart);

		Assert.assertEquals(dateStart, RepresentationUtils.getFirstDate(standbyDuties));
	}

	@Test
	public void testGetLastDate() {
		Date dateStart = new Date();
		List<MergeableImplMock> standbyDuties = createStandbyDutiesList(dateStart);
		Assert.assertEquals(DateUtils.addDays(dateStart, 15), RepresentationUtils.getLastDate(standbyDuties));
	}

	@Test
	public void testPrint() {
		List<MergeableImplMock> standbyDuties = createStandbyDutiesList(new Date());
		RepresentationUtils.print(standbyDuties);
	}

	private List<MergeableImplMock> createStandbyDutiesList(Date dateStart) {
		MergeableImplMock standbyDuty1 = new MergeableImplMock(1, dateStart, DateUtils.addDays(dateStart, 0),
				MergeableTypeImplMock.RED);
		MergeableImplMock standbyDuty2 = new MergeableImplMock(2, DateUtils.addDays(dateStart, 3),
				DateUtils.addDays(dateStart, 4), MergeableTypeImplMock.BLACK);
		MergeableImplMock standbyDuty3 = new MergeableImplMock(3, DateUtils.addDays(dateStart, 5),
				DateUtils.addDays(dateStart, 6), MergeableTypeImplMock.BLACK);
		MergeableImplMock standbyDuty4 = new MergeableImplMock(4, DateUtils.addDays(dateStart, 8),
				DateUtils.addDays(dateStart, 10), MergeableTypeImplMock.BLACK);
		MergeableImplMock standbyDuty5 = new MergeableImplMock(5, DateUtils.addDays(dateStart, 12),
				DateUtils.addDays(dateStart, 15), MergeableTypeImplMock.BLACK);

		List<MergeableImplMock> standbyDuties = new ArrayList<>();
		standbyDuties.add(standbyDuty3);
		standbyDuties.add(standbyDuty2);
		standbyDuties.add(standbyDuty4);
		standbyDuties.add(standbyDuty5);
		standbyDuties.add(standbyDuty1);
		return standbyDuties;
	}

}
