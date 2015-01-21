package org.geduino.ros.core.util;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class CollectionFIlterTest extends TestCase {

	public void test() {

		// Create list
		List<Integer> list = new ArrayList<Integer>();
		list.add(new Integer(0));
		list.add(new Integer(1));
		list.add(new Integer(2));
		list.add(new Integer(3));
		list.add(new Integer(4));
		list.add(new Integer(5));
		list.add(new Integer(6));
		list.add(new Integer(7));
		list.add(new Integer(8));
		list.add(new Integer(9));

		List<Integer> filteredList = new ArrayList<Integer>();

		// Apply filter
		new CollectionFilter<Integer>(list).filter(filteredList,
				new IntegerFilter());

		// Check target list size
		assertEquals(5, filteredList.size());
		assertFalse(filteredList.contains(1));
		assertFalse(filteredList.contains(2));
		assertFalse(filteredList.contains(3));
		assertFalse(filteredList.contains(4));
		assertTrue(filteredList.contains(5));
		assertTrue(filteredList.contains(6));
		assertTrue(filteredList.contains(7));
		assertTrue(filteredList.contains(8));
		assertTrue(filteredList.contains(9));

	}

	class IntegerFilter implements Filter<Integer> {

		public boolean accept(Integer integer) {
			return integer.intValue() > 4;
		}

	}

}
