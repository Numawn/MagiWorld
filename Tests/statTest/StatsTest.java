package statTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exceptions.LevelValueIsInvalid;
import exceptions.StatisticPointsAreInvalid;
import stat.Stat;
import stat.StatNames;
import stat.Stats;

class StatsTest {
	
	// Allows to test equality with Println
	private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final static PrintStream originalOut = System.out;
	
	
	Stats stats = new Stats(
			new Stat(StatNames.LVL, 20),
			new Stat(StatNames.STR, 20), 
			new Stat(StatNames.INT, 0),
			new Stat(StatNames.AGI, 0));
	
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
		assertEquals(20, stats.getStat(StatNames.LVL));
		assertEquals(100, stats.getStat(StatNames.HP));
		assertEquals(20, stats.getStat(StatNames.STR));
		assertEquals(0, stats.getStat(StatNames.INT));
		assertEquals(0, stats.getStat(StatNames.AGI));
	}
	
	@Test
	void setNewValueOfAskedStatAndDoesNotThrowsExeption() {
		stats.update(StatNames.HP, -50);
		stats.update(StatNames.AGI, 10);
		
		assertEquals(50, stats.getStat(StatNames.HP));
		assertEquals(10, stats.getStat(StatNames.AGI));
	}
	
	@Test
	void displayStatOfACharacterCorrectly() {
		stats.display();
		assertEquals("niveau 20 je possède 100 de vitalité, "
				+ "20 de force, "
				+ "0 d'agilité et "
				+ "0 d'intelligence !" 
				+ "\n", outContent.toString());
	}
	
	@Test
	void SetPVsAtDefaultValueIfGainedTooMuch() {
		stats.update(StatNames.HP, 150);
		assertEquals(100, stats.getStat(StatNames.HP));
	}
	
	@Test
	void SetPVsAtZeroIfLosenTooMuch() {
		stats.update(StatNames.HP, -110);
		assertEquals(0, stats.getStat(StatNames.HP));
	}
	
	@Test
	void throwsExceptionIfStatisticPointsAreDifferentThanLevelAtCreation() {
		assertThrows(StatisticPointsAreInvalid.class, () -> new Stats(
				new Stat(StatNames.LVL, 20),
				new Stat(StatNames.HP, 100), 
				new Stat(StatNames.STR, 10), 
				new Stat(StatNames.INT, 10),
				new Stat(StatNames.AGI, 10)));
	}
	
	@Test
	void throwsExceptionIfLevelIsNotAValidNumber() {
		assertThrows(LevelValueIsInvalid.class, () -> new Stats(
				new Stat(StatNames.LVL, 0)));
	}
	

}
