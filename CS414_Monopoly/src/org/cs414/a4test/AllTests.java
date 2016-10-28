package org.cs414.a4test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BankerTest.class, BlockTest.class, BoardTest.class, PlayerTest.class, PropertyTest.class,
		TitleDeedCardRentTest.class, TitleDeedCardTest.class, UtilityTest.class })
public class AllTests {

}
