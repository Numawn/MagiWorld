package statTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.StatValueIsInvalid;
import stat.Stat;
import stat.StatNames;
import stat.Stats;

class StatTest {

	Stat stat = new Stat(StatNames.HP, 100);
	
	@Test
	void getValueOfStat() {

		assertEquals(100, stat.getValue());
	}
	
	
	@Test
	void returnsItsName() {
		assertEquals("HealthPoints", stat.getName());
	}
	
	@Test
	void throwsExceptionIfStatIsNotAValidNumber() {
		assertThrows(StatValueIsInvalid.class, () -> new Stats(
				new Stat(StatNames.STR, -1)));
	}
	

}
