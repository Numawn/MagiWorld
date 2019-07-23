package statTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exceptions.LevelValueIsInvalid;
import exceptions.StatisticPointsAreInvalid;
import stat.Stat;
import stat.Stats;

class StatsTest {
	
	// Allows to test equality with Println
	private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final static PrintStream originalOut = System.out;
	
	
	Stats stats = new Stats(
			new Stat("Level", 20),
			new Stat("PV", 100), 
			new Stat("Strenght", 20), 
			new Stat("Intel", 0),
			new Stat("Agility", 0));
	
	// Allow to test equality with Println
	@BeforeAll
	public static void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}

	@AfterAll
	public static void restoreStreams() {
	    System.setOut(originalOut);
	}
	
	
	@Test
	void getTheAskedStatValue() {
		assertEquals(20, stats.getStat("Level"));
		assertEquals(100, stats.getStat("PV"));
		assertEquals(20, stats.getStat("Stenght"));
		assertEquals(0, stats.getStat("Intel"));
		assertEquals(0, stats.getStat("Agility"));
	}
	
	@Test
	void setNewValueOfAskedStatAndDoesNotThrowsExeption() {
		stats.setStat("PV", 50);
		stats.setStat("Agility", 10);
		
		assertEquals(50, stats.getStat("PV"));
		assertEquals(10, stats.getStat("Agility"));
	}
	
	@Test
	void displayStatOfACharacterCorrectly() {
		stats.display();
		assertEquals("niveau 20 je possède 100 de vitalité, "
				+ "20 de force, "
				+ "0 d'agilité et "
				+ "0 d'intelligence !", outContent.toString());
	}
	
	@Test
	void SetPVsAtDefaultValueIfGainedTooMuch() {
		stats.setStat("PV", 150);
		assertEquals(100, stats.getStat("PV"));
	}
	
	@Test
	void SetPVsAtZeroIfLosenTooMuch() {
		stats.setStat("PV", -10);
		assertEquals(0, stats.getStat("PV"));
	}
	
	@Test
	void throwsExceptionIfStatisticPointsAreDifferentThanLevelAtCreation() {
		assertThrows(StatisticPointsAreInvalid.class, () -> new Stats(new Stat("Level", 20),
			new Stat("PV", 100), 
			new Stat("Strenght", 10), 
			new Stat("Intel", 10),
			new Stat("Agility", 10)));
	}
	
	@Test
	void throwsExceptionIfLevelIsNotAValidNumber() {
		assertThrows(LevelValueIsInvalid.class, () -> new Stats(new Stat("Level", -10)));
	}
	

}
