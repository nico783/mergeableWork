package com.mergeable.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;

public class MergeableListTest {

	private Date baseDate = new Date();

	// old: X-X-X-O-O-O
	// new: N-N-X-X-X-X
	// *******************************
	@Test
	public void testNewBeforeBdd() {
		// arrange
		List<Mergeable> mergeables = createMergeables();

		// act
		MergeableList mergeableList = new MergeableList(mergeables);
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -5), DateUtils.addDays(baseDate, -4),
				MergeableTypeImplMock.RED);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(3, mergeableList.size());
		// RepresentationUtils.print(mergeables);
	}
	// *******************************

	// old: X-X-X-O-O-O
	// new: N-N-N-X-X-X
	// *******************************
	@Test
	public void testNewDateEndEqualsOldDateStartAndSameType() {
		// arrange
		List<Mergeable> mergeables = createMergeables();

		// act
		MergeableList mergeableList = new MergeableList(mergeables);
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -5), DateUtils.addDays(baseDate, -3),
				MergeableTypeImplMock.RED);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(2, mergeableList.size());

		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -5), baseDate), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(1).getPeriod());
		// RepresentationUtils.print(mergeables);
	}

	@Test
	public void testNewDateEndEqualsOldDateStartAndDifferentType() {
		// arrange
		List<Mergeable> mergeables = createMergeables();

		// act
		MergeableList mergeableList = new MergeableList(mergeables);
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -5), DateUtils.addDays(baseDate, -3),
				MergeableTypeImplMock.GREEN);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(3, mergeableList.size());

		Assert.assertEquals(MergeableTypeImplMock.GREEN, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -5), DateUtils.addDays(baseDate, -3)),
				mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -2), baseDate), mergeableList.get(1).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(2).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(2).getPeriod());
		// RepresentationUtils.print(mergeables);
	}
	// *******************************

	// old: X-X-X-O-O-O
	// new: N-N-N-N-X-X
	// *******************************
	@Test
	public void testNewDateEndAfterOldDateStartAndBeforeOldDateEndAndSameType() {

		// CAS 1
		// X-X-X-O-O-O
		// N-N-N-N-X-X
		// arrange
		List<Mergeable> mergeables = createMergeables();
		MergeableList mergeableList = new MergeableList(mergeables);

		// act
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -5), DateUtils.addDays(baseDate, -2),
				MergeableTypeImplMock.RED);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(2, mergeableList.size());
		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -5), baseDate), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(1).getPeriod());
		// RepresentationUtils.print(mergeables);

		// CAS 2
		// X-X-X-O-O-O
		// N-N-N-N-N-X
		// act
		mergeables = createMergeables();
		mergeableList = new MergeableList(mergeables);
		nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -5), DateUtils.addDays(baseDate, -1),
				MergeableTypeImplMock.RED);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(2, mergeableList.size());
		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -5), baseDate), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(1).getPeriod());
		// RepresentationUtils.print(mergeables);
	}

	@Test
	public void testNewDateEndAfterOldDateStartAndBeforeOldDateEndAndDifferentType() {
		// CAS 1
		// X-X-X-O-O-O
		// N-N-N-N-X-X
		// arrange
		List<Mergeable> mergeables = createMergeables();

		// act
		MergeableList mergeableList = new MergeableList(mergeables);
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -5), DateUtils.addDays(baseDate, -2),
				MergeableTypeImplMock.GREEN);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(3, mergeableList.size());
		Assert.assertEquals(MergeableTypeImplMock.GREEN, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -5), DateUtils.addDays(baseDate, -2)),
				mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -1), baseDate), mergeableList.get(1).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(2).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(2).getPeriod());
		// RepresentationUtils.print(mergeables);

		// CAS 2
		// X-X-X-O-O-O
		// N-N-N-N-N-X
		// arrange
		mergeables = createMergeables();

		// act
		mergeableList = new MergeableList(mergeables);
		nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -5), DateUtils.addDays(baseDate, -1),
				MergeableTypeImplMock.GREEN);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(3, mergeableList.size());
		Assert.assertEquals(MergeableTypeImplMock.GREEN, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -5), DateUtils.addDays(baseDate, -1)),
				mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(baseDate, baseDate), mergeableList.get(1).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(2).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(2).getPeriod());
		// RepresentationUtils.print(mergeables);
	}
	// *******************************

	// old: X-X-X-O-O-O
	// new: X-X-X-N-N-X
	// *******************************
	@Test
	public void testNewDateStartEqualsOldDateStartAndNewDateEndBeforeOldDateEndAndSameType() {

		// CAS 1
		// X-X-X-O-O-O
		// X-X-X-N-X-X
		// arrange
		List<Mergeable> mergeables = createMergeables();
		MergeableList mergeableList = new MergeableList(mergeables);

		// act
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -2), DateUtils.addDays(baseDate, -2),
				MergeableTypeImplMock.RED);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(2, mergeableList.size());
		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -2), baseDate), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(1).getPeriod());
		// RepresentationUtils.print(mergeables);

		// CAS 2
		// X-X-X-O-O-O
		// X-X-X-N-N-X
		// act
		mergeables = createMergeables();
		mergeableList = new MergeableList(mergeables);
		nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -2), DateUtils.addDays(baseDate, -1),
				MergeableTypeImplMock.RED);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(2, mergeableList.size());
		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -2), baseDate), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(1).getPeriod());
		// RepresentationUtils.print(mergeables);
	}

	@Test
	public void testNewDateStartEqualsOldDateStartAndNewDateEndBeforeOldDateEndAndDifferentType() {
		// CAS 1
		// X-X-X-O-O-O
		// X-X-X-N-X-X
		// arrange
		List<Mergeable> mergeables = createMergeables();

		// act
		MergeableList mergeableList = new MergeableList(mergeables);
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -2), DateUtils.addDays(baseDate, -2),
				MergeableTypeImplMock.GREEN);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(3, mergeableList.size());
		Assert.assertEquals(MergeableTypeImplMock.GREEN, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -2), DateUtils.addDays(baseDate, -2)),
				mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -1), baseDate), mergeableList.get(1).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(2).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(2).getPeriod());
		// RepresentationUtils.print(mergeables);

		// CAS 2
		// X-X-X-O-O-O
		// X-X-X-N-N-X
		// arrange
		mergeables = createMergeables();

		// act
		mergeableList = new MergeableList(mergeables);
		nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -2), DateUtils.addDays(baseDate, -1),
				MergeableTypeImplMock.GREEN);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(3, mergeableList.size());
		Assert.assertEquals(MergeableTypeImplMock.GREEN, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -2), DateUtils.addDays(baseDate, -1)),
				mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(baseDate, baseDate), mergeableList.get(1).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(2).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(2).getPeriod());
		// RepresentationUtils.print(mergeables);
	}
	// *******************************
	
	// old: X-X-X-O-O-O
	// new: X-X-X-X-N-X
	// *******************************
	@Test
	public void testNewDateStartAfterOldDateStartAndNewDateEndBeforeOldDateEndAndSameType() {

		// X-X-X-O-O-O
		// X-X-X-X-N-X
		// arrange
		List<Mergeable> mergeables = createMergeables();
		MergeableList mergeableList = new MergeableList(mergeables);

		// act
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -1), DateUtils.addDays(baseDate, -1),
				MergeableTypeImplMock.RED);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(2, mergeableList.size());
		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -2), baseDate), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(1).getPeriod());
		// RepresentationUtils.print(mergeables);
	}

	@Test
	public void testNewDateStartAfterOldDateStartAndNewDateEndBeforeOldDateEndAndDifferentType() {
		// X-X-X-O-O-O
		// X-X-X-X-N-X
		// arrange
		List<Mergeable> mergeables = createMergeables();

		// act
		MergeableList mergeableList = new MergeableList(mergeables);
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -1), DateUtils.addDays(baseDate, -1),
				MergeableTypeImplMock.GREEN);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(4, mergeableList.size());
		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -2), DateUtils.addDays(baseDate, -2)),
				mergeableList.get(0).getPeriod());
		
		Assert.assertEquals(MergeableTypeImplMock.GREEN, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -1), DateUtils.addDays(baseDate, -1)),
				mergeableList.get(1).getPeriod());
		
		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(2).getType());
		Assert.assertEquals(new DayPeriod(baseDate, baseDate),
				mergeableList.get(2).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(3).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(3).getPeriod());
		// RepresentationUtils.print(mergeables);
	}
	// *******************************
	
	
	// old: X-X-X-O-O-O
	// new: N-N-N-N-N-N
	// *******************************
	@Test
	public void testNewDateEndEqualsOldDateEndAndNewDateStartBeforeOldDateStartAndSameType() {

		// old: X-X-X-O-O-O
		// new: N-N-N-N-N-N
		// arrange
		List<Mergeable> mergeables = createMergeables();
		MergeableList mergeableList = new MergeableList(mergeables);

		// act
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -5), baseDate,
				MergeableTypeImplMock.RED);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(2, mergeableList.size());
		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -5), baseDate), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(1).getPeriod());
		// RepresentationUtils.print(mergeables);

	}

	@Test
	public void testNewDateEndEqualsOldDateEndAndNewDateStartBeforeOldDateStartAndDifferentType() {
		// X-X-X-O-O-O
		// N-N-N-N-X-X
		// arrange
		List<Mergeable> mergeables = createMergeables();

		// act
		MergeableList mergeableList = new MergeableList(mergeables);
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -5), baseDate,
				MergeableTypeImplMock.GREEN);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(2, mergeableList.size());
		Assert.assertEquals(MergeableTypeImplMock.GREEN, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -5), baseDate), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(1).getPeriod());
		// RepresentationUtils.print(mergeables);

	}
	// *******************************

	// old: X-X-X-O-O-O
	// new: X-X-X-N-N-N
	// *******************************
	@Test
	public void testNewDateEndEqualsOldDateEndAndNewDateStartEqualsOldDateStartAndSameType() {

		// arrange
		List<Mergeable> mergeables = createMergeables();
		MergeableList mergeableList = new MergeableList(mergeables);

		// act
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -2), baseDate,
				MergeableTypeImplMock.RED);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(2, mergeableList.size());
		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -2), baseDate), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(1).getPeriod());
		// RepresentationUtils.print(mergeables);

	}

	@Test
	public void testNewDateEndEqualsOldDateEndAndNewDateStartEqualsOldDateStartAndDifferentType() {
		// arrange
		List<Mergeable> mergeables = createMergeables();

		// act
		MergeableList mergeableList = new MergeableList(mergeables);
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -2), baseDate,
				MergeableTypeImplMock.GREEN);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(2, mergeableList.size());
		Assert.assertEquals(MergeableTypeImplMock.GREEN, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -2), baseDate), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(1).getPeriod());
		// RepresentationUtils.print(mergeables);

	}
	// *******************************
	
	// old: X-X-X-O-O-O
	// new: X-X-X-X-N-N
	// *******************************
	@Test
	public void testNewDateEndEqualsOldDateEndAndNewDateStartAfterOldDateStartAndSameType() {

		// arrange
		List<Mergeable> mergeables = createMergeables();
		MergeableList mergeableList = new MergeableList(mergeables);

		// act
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -1), baseDate,
				MergeableTypeImplMock.RED);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(2, mergeableList.size());
		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -1), baseDate), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(1).getPeriod());
		// RepresentationUtils.print(mergeables);

	}

	@Test
	public void testNewDateEndEqualsOldDateEndAndNewDateStartAfterOldDateStartAndDifferentType() {
		// arrange
		List<Mergeable> mergeables = createMergeables();

		// act
		MergeableList mergeableList = new MergeableList(mergeables);
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -1), baseDate,
				MergeableTypeImplMock.GREEN);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(2, mergeableList.size());
		Assert.assertEquals(MergeableTypeImplMock.GREEN, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -1), baseDate), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(1).getPeriod());
		// RepresentationUtils.print(mergeables);

	}
	// *******************************
	
	// old: X-X-X-O-O-O-X-X-X
	// new: X-X-N-N-N-N-N-N-X
	// *******************************
	@Test
	public void testNewDateEndAfterOldDateEndAndNewDateStartBeforeOldDateStartAndSameType() {

		// arrange
		List<Mergeable> mergeables = createMergeables();
		MergeableList mergeableList = new MergeableList(mergeables);

		// act
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -4), DateUtils.addDays(baseDate, 1),
				MergeableTypeImplMock.RED);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(2, mergeableList.size());
		
		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -4), DateUtils.addDays(baseDate, 1)), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(1).getPeriod());
		// RepresentationUtils.print(mergeables);

	}

	@Test
	public void testNewDateEndAfterOldDateEndAndNewDateStartBeforeOldDateStartAndDifferentType() {
		// arrange
		List<Mergeable> mergeables = createMergeables();

		// act
		MergeableList mergeableList = new MergeableList(mergeables);
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -4), DateUtils.addDays(baseDate, 1),
				MergeableTypeImplMock.GREEN);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(2, mergeableList.size());
		Assert.assertEquals(MergeableTypeImplMock.GREEN, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -4), DateUtils.addDays(baseDate, 1)), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(1).getPeriod());
		// RepresentationUtils.print(mergeables);

	}
	
	@Test
	public void testNewDateEndAfterOldDateEndAndNewDateStartBeforeOldDateStartAndSameTypeWithOverlapOldNext() {

		// arrange
		List<Mergeable> mergeables = createMergeables();
		MergeableList mergeableList = new MergeableList(mergeables);

		// act
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -4), DateUtils.addDays(baseDate, 3),
				MergeableTypeImplMock.RED);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(3, mergeableList.size());
		
		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -4), DateUtils.addDays(baseDate, 1)), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 3)), mergeableList.get(1).getPeriod());
		
		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(2).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 4), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(2).getPeriod());
		// RepresentationUtils.print(mergeables);

	}

	@Test
	public void testNewDateEndAfterOldDateEndAndNewDateStartBeforeOldDateStartAndDifferentTypeWithOverlapOldNext() {
		// arrange
		List<Mergeable> mergeables = createMergeables();

		// act
		MergeableList mergeableList = new MergeableList(mergeables);
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -4), DateUtils.addDays(baseDate, 3),
				MergeableTypeImplMock.GREEN);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(3, mergeableList.size());
		
		Assert.assertEquals(MergeableTypeImplMock.GREEN, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -4), DateUtils.addDays(baseDate, 1)), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.GREEN, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 3)), mergeableList.get(1).getPeriod());
		
		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(2).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 4), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(2).getPeriod());
		// RepresentationUtils.print(mergeables);
	}
	
	@Test
	public void testNewDateEndAfterOldDateEndAndNewDateStartBeforeOldDateStartAndSameTypeWithFullOverlapOldNext() {

		// arrange
		List<Mergeable> mergeables = createMergeables();
		MergeableList mergeableList = new MergeableList(mergeables);

		// act
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -4), DateUtils.addDays(baseDate, 6),
				MergeableTypeImplMock.RED);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(2, mergeableList.size());
		
		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -4), DateUtils.addDays(baseDate, 1)), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 6)), mergeableList.get(1).getPeriod());
		
		// RepresentationUtils.print(mergeables);

	}
	
	@Test
	public void testNewDateEndAfterOldDateEndAndNewDateStartBeforeOldDateStartAndDifferentTypeWithFullOverlapOldNext() {
		// arrange
		List<Mergeable> mergeables = createMergeables();

		// act
		MergeableList mergeableList = new MergeableList(mergeables);
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -4), DateUtils.addDays(baseDate, 6),
				MergeableTypeImplMock.GREEN);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(2, mergeableList.size());
		
		Assert.assertEquals(MergeableTypeImplMock.GREEN, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -4), DateUtils.addDays(baseDate, 1)), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.GREEN, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 6)), mergeableList.get(1).getPeriod());
		
		// RepresentationUtils.print(mergeables);
	}
	
	@Test
	public void testNewDateEndAfterOldDateEndAndNewDateStartBeforeOldDateStartAndSameTypeWithMultipleFullOverlapOldNext() {

		// arrange
		List<Mergeable> mergeables = createMergeables();
		
		Mergeable mergeable3 = new MergeableImplMock(3, DateUtils.addDays(baseDate, 5), DateUtils.addDays(baseDate, 6),
				MergeableTypeImplMock.GREEN);
		mergeables.add(mergeable3);
		MergeableList mergeableList = new MergeableList(mergeables);

		// act
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -4), DateUtils.addDays(baseDate, 6),
				MergeableTypeImplMock.RED);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(3, mergeableList.size());
		
		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -4), DateUtils.addDays(baseDate, 1)), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)), mergeableList.get(1).getPeriod());
		
		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(2).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 5), DateUtils.addDays(baseDate, 6)), mergeableList.get(2).getPeriod());
		
		// RepresentationUtils.print(mergeables);

	}
	
	@Test
	public void testNewDateEndAfterOldDateEndAndNewDateStartBeforeOldDateStartAndDifferentTypeWithMultipleFullOverlapOldNext() {
		// arrange
		List<Mergeable> mergeables = createMergeables();
		
		Mergeable mergeable3 = new MergeableImplMock(3, DateUtils.addDays(baseDate, 5), DateUtils.addDays(baseDate, 6),
				MergeableTypeImplMock.GREEN);
		mergeables.add(mergeable3);
		MergeableList mergeableList = new MergeableList(mergeables);

		// act
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -4), DateUtils.addDays(baseDate, 6),
				MergeableTypeImplMock.GREEN);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(3, mergeableList.size());
		
		Assert.assertEquals(MergeableTypeImplMock.GREEN, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -4), DateUtils.addDays(baseDate, 1)), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.GREEN, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)), mergeableList.get(1).getPeriod());
		
		Assert.assertEquals(MergeableTypeImplMock.GREEN, mergeableList.get(2).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 5), DateUtils.addDays(baseDate, 6)), mergeableList.get(2).getPeriod());
		
		// RepresentationUtils.print(mergeables);

	}
	// *******************************
	
	// old: X-X-X-O-O-O-X-X-X
	// new: X-X-X-N-N-N-N-N-X
	// *******************************
	@Test
	public void testNewDateEndAfterOldDateEndAndNewDateStartEqualsOldDateStartAndSameType() {

		// arrange
		List<Mergeable> mergeables = createMergeables();
		MergeableList mergeableList = new MergeableList(mergeables);

		// act
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -2), DateUtils.addDays(baseDate, 1),
				MergeableTypeImplMock.RED);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(2, mergeableList.size());
		
		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -2), DateUtils.addDays(baseDate, 1)), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(1).getPeriod());
		// RepresentationUtils.print(mergeables);

	}
	
	@Test
	public void testNewDateEndAfterOldDateEndAndNewDateStartEqualsOldDateStartAndDifferentType() {

		// arrange
		List<Mergeable> mergeables = createMergeables();
		MergeableList mergeableList = new MergeableList(mergeables);

		// act
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -2), DateUtils.addDays(baseDate, 1),
				MergeableTypeImplMock.GREEN);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(2, mergeableList.size());
		
		Assert.assertEquals(MergeableTypeImplMock.GREEN, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -2), DateUtils.addDays(baseDate, 1)), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(1).getPeriod());
		// RepresentationUtils.print(mergeables);

	}
	// *******************************
	
	// old: X-X-X-O-O-O-X-X-X
	// new: X-X-X-N-N-N-N-N-X
	// *******************************
	@Test
	public void testNewDateEndAfterOldDateEndAndNewDateStartAfterOldDateStartAndSameType() {

		// arrange
		List<Mergeable> mergeables = createMergeables();
		MergeableList mergeableList = new MergeableList(mergeables);

		// act
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -1), DateUtils.addDays(baseDate, 1),
				MergeableTypeImplMock.RED);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(2, mergeableList.size());
		
		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -2), DateUtils.addDays(baseDate, 1)), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(1).getPeriod());
		// RepresentationUtils.print(mergeables);

	}
	
	@Test
	public void testNewDateEndAfterOldDateEndAndNewDateStartAfterOldDateStartAndDifferentType() {

		// arrange
		List<Mergeable> mergeables = createMergeables();
		MergeableList mergeableList = new MergeableList(mergeables);

		// act
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -1), DateUtils.addDays(baseDate, 1),
				MergeableTypeImplMock.GREEN);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(3, mergeableList.size());
		
		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -2), DateUtils.addDays(baseDate, -2)), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.GREEN, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -1), DateUtils.addDays(baseDate, 1)), mergeableList.get(1).getPeriod());
		
		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(2).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4)),
				mergeableList.get(2).getPeriod());
		// RepresentationUtils.print(mergeables);

	}
	
	@Test
	public void testNewDateEndAfterOldDateEndAndNewDateStartAfterOldDateStartAndDifferentTypeWithMultipleOverlap() {

		// arrange
		List<Mergeable> mergeables = createMergeables();
		Mergeable mergeable3 = new MergeableImplMock(3, DateUtils.addDays(baseDate, 5), DateUtils.addDays(baseDate, 6),
				MergeableTypeImplMock.GREEN);
		mergeables.add(mergeable3);
		MergeableList mergeableList = new MergeableList(mergeables);

		// act
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -1), DateUtils.addDays(baseDate, 5),
				MergeableTypeImplMock.BLACK);
		mergeableList.add(nouveau);

		// assert
		Assert.assertEquals(4, mergeableList.size());
		
		Assert.assertEquals(MergeableTypeImplMock.RED, mergeableList.get(0).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -2), DateUtils.addDays(baseDate, -2)), mergeableList.get(0).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(1).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -1), DateUtils.addDays(baseDate, 4)), mergeableList.get(1).getPeriod());

		Assert.assertEquals(MergeableTypeImplMock.BLACK, mergeableList.get(2).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 5), DateUtils.addDays(baseDate, 5)), mergeableList.get(2).getPeriod());
		
		Assert.assertEquals(MergeableTypeImplMock.GREEN, mergeableList.get(3).getType());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 6), DateUtils.addDays(baseDate, 6)),
				mergeableList.get(3).getPeriod());
		// RepresentationUtils.print(mergeables);

	}
	// *******************************
	
	// Tests fonctionnels
	// *******************************
	@Test
	public void testFusionMergeablesUnitaires(){
		// arrange
		Mergeable mergeable1 = new MergeableImplMock(1,  baseDate, baseDate, MergeableTypeImplMock.RED);
		Mergeable mergeable2 = new MergeableImplMock(2,  DateUtils.addDays(baseDate, 1), DateUtils.addDays(baseDate, 1),
				MergeableTypeImplMock.BLACK);
		Mergeable mergeable3 = new MergeableImplMock(3,  DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 2),
				MergeableTypeImplMock.GREEN);
		Mergeable mergeable4 = new MergeableImplMock(5,  DateUtils.addDays(baseDate, 3), DateUtils.addDays(baseDate, 3),
				MergeableTypeImplMock.RED);
		Mergeable mergeable5 = new MergeableImplMock(6,  DateUtils.addDays(baseDate, 4), DateUtils.addDays(baseDate, 4),
				MergeableTypeImplMock.GREEN);
		Mergeable mergeable6 = new MergeableImplMock(7,  DateUtils.addDays(baseDate, 5), DateUtils.addDays(baseDate, 5),
				MergeableTypeImplMock.BLACK);
		List<Mergeable> mergeables = new MergeableList(Arrays.asList(mergeable1,mergeable2, mergeable3, mergeable4, mergeable5, mergeable6));
		// RepresentationUtils.print(mergeables);
		
		// act
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -1), DateUtils.addDays(baseDate, 5),
				MergeableTypeImplMock.BLACK);
		mergeables.add(nouveau);
		
		// assert
		Assert.assertEquals(6, mergeables.size());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, -1), DateUtils.addDays(baseDate, 0)), mergeables.get(0).getPeriod());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 1), DateUtils.addDays(baseDate, 1)), mergeables.get(1).getPeriod());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 2)), mergeables.get(2).getPeriod());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 3), DateUtils.addDays(baseDate, 3)), mergeables.get(3).getPeriod());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 4), DateUtils.addDays(baseDate, 4)), mergeables.get(4).getPeriod());
		Assert.assertEquals(new DayPeriod(DateUtils.addDays(baseDate, 5), DateUtils.addDays(baseDate, 5)), mergeables.get(5).getPeriod());

		// RepresentationUtils.print(mergeables);

	}
	
	@Test
	public void testSimpleAdd(){
		List<Mergeable> mergeables = new MergeableList(new ArrayList<>());
		Mergeable nouveau = new MergeableImplMock(4, DateUtils.addDays(baseDate, -1), DateUtils.addDays(baseDate, 5),
				MergeableTypeImplMock.BLACK);
		mergeables.add(nouveau);
		Assert.assertEquals(1, mergeables.size());
	}
	
	@Test
	public void testAddAll(){
		// arrange
		List<Mergeable> mergeables = createMergeables();
		List<Mergeable> news = new ArrayList<>();
		news.add(new MergeableImplMock(3, DateUtils.addDays(baseDate, -3), DateUtils.addDays(baseDate, -3), MergeableTypeImplMock.RED));
		news.add(new MergeableImplMock(3, DateUtils.addDays(baseDate, 1), DateUtils.addDays(baseDate, 3), MergeableTypeImplMock.GREEN));

		MergeableList mergeableList = new MergeableList(mergeables);
		
		// act
		mergeableList.addAll(news);
		
		// assert
		Assert.assertEquals(3, mergeableList.size());
	}
	
	// *******************************
	
	private List<Mergeable> createMergeables() {
		Mergeable mergeable1 = new MergeableImplMock(1, DateUtils.addDays(baseDate, -2), baseDate,
				MergeableTypeImplMock.RED);
		Mergeable mergeable2 = new MergeableImplMock(2, DateUtils.addDays(baseDate, 2), DateUtils.addDays(baseDate, 4),
				MergeableTypeImplMock.BLACK);
		List<Mergeable> mergeables = new ArrayList<>();
		mergeables.add(mergeable2);
		mergeables.add(mergeable1);
		// RepresentationUtils.print(mergeables);
		return mergeables;
	}

}
