package statTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import stat.Stat;

class StatTest {

	Stat stat = new Stat("PV", 100);
	
	@Test
	void getValueOfStat() {

		assertEquals(100, stat.getValue());
	}
	
	@Test
	void setNewValueToStat() {
		stat.setValue(250);
		
		assertEquals(250, stat.getValue());
	}
	
	// A negative value is not possible to give at the creation of the stat
	// this value will be checked before, but a negative value can occure
	// during the battle.
	@Test
	void setValueZeroIfGivenValueIsNegative() {
		stat.setValue(-10);
		
		assertEquals(0, stat.getValue());
	}


}
