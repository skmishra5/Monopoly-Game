package org.cs414.a4test;
import static org.junit.Assert.*;

import org.cs414.a4source.Block;
import org.cs414.a4source.PropUtil;
import org.junit.Before;
import org.junit.Test;

public class BlockTest {

	public static final String testdescription="Test Block1";
	public static final String testdescription1="Test Block2";
	private PropUtil testpropUtil;
	private Block testBlock;
	
	@Before
	public void setup(){
		testpropUtil = PropUtil.PROPERTY;
		testBlock= new Block(testdescription, testpropUtil);
	}
	@Test
	public void testBlock() {
	Block testBlock1= new Block(testdescription, testpropUtil);
	assertEquals(testBlock1.getClass(), testBlock.getClass());
	}

	@Test
	public void testSetGetDescription() {
		assertEquals(testdescription, testBlock.getDescription());
		testBlock.setDescription(testdescription1);
		assertNotEquals(testdescription, testBlock.getDescription());
		assertEquals(testdescription1, testBlock.getDescription());
		
	}

	
	@Test
	public void testSetGetPropUtil() {
		assertEquals(testBlock.getPropUtil(), testpropUtil);
		testBlock.setPropUtil(PropUtil.UTILITY);
		Block testBlock1= new Block(testdescription, testpropUtil);
		assertEquals(testBlock1.getPropUtil(), testpropUtil);
		testBlock1.setPropUtil(PropUtil.UTILITY);
		assertNotEquals(testBlock1.getPropUtil(), testpropUtil);
	}

	
}
