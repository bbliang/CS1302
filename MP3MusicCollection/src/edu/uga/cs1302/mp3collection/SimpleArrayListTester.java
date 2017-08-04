package edu.uga.cs1302.mp3collection;

import static org.junit.Assert.*;

import java.util.ListIterator;

import org.junit.Before;
import org.junit.Test;


public class SimpleArrayListTester {
	private SimpleArrayList<String> sList;
	private ListIterator<String> iter;
	
	//set up the test
	@Before
	public void setUp()
	{
		sList = new SimpleArrayList<String>();
		sList.add( "testObj1" );
	    sList.add( "testObj2" );
	    sList.add( "testObj3" );
	    sList.add( "testObj4" );
	    sList.add( "testObj5" );
	    sList.add( "testObj6" );
	    iter = sList.listIterator( 0 );
	}
	
	@Test
	public void test()
	{
		//test case: iter is at the beginning of array list
		assertTrue(iter.hasNext());
		assertFalse(iter.hasPrevious());
		assertEquals(iter.nextIndex(),1);
		
		//test case: iter is at the middle of the array list
		iter.next();
		assertEquals(iter.nextIndex(),2);
		assertEquals(iter.previousIndex(),0);
		assertTrue(iter.hasPrevious());
		assertTrue(iter.hasNext());
		iter.previous();
		assertTrue(iter.hasNext());
		assertFalse(iter.hasPrevious());
		
		//tests case: iter as at the end of the array list
		for(int i = 0; i < sList.size(); i++)
			iter.next();
		assertTrue(iter.hasPrevious());
		assertFalse(iter.hasNext());
		assertEquals(iter.previousIndex(),5);
		
	}
}


