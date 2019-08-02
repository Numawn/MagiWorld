package characterTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import character.Player;
import character.Rover;
import character.Mage;
import character.Warrior;
import stat.Stat;
import stat.StatNames;
import stat.Stats;

class CharacterTest {
	
	// Allows to test equality with Println
		private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		private final static PrintStream originalOut = System.out;
	
	private Player warrior = new Warrior(
			"Gérald", 
			new Stats(
				new Stat(StatNames.LVL, 10),
				new Stat(StatNames.STR, 10), 
				new Stat(StatNames.INT, 0), 
				new Stat(StatNames.AGI, 0)));
	
	private Player mage = new Mage(
			"Arnold", 
			new Stats(
					new Stat(StatNames.LVL,10),
					new Stat(StatNames.STR,0), 
					new Stat(StatNames.INT,10), 
					new Stat(StatNames.AGI,0)));
	
	private Player rover = new Rover(
			"Archibald", 
			new Stats(
					new Stat(StatNames.LVL,10), 
					new Stat(StatNames.STR,0), 
					new Stat(StatNames.INT,0), 
					new Stat(StatNames.AGI,10)));
	
	
	// Allow to test equality with Println
		@BeforeEach
		public void setUpStreams() {
			System.setOut(new PrintStream(outContent));
		}

		@AfterEach
		public void revertStreams() {
		    System.setOut(originalOut);
		    outContent.reset();
		    
		    
		}
	
	
	@Test
	void ReturnTheAskedStatValue() {
		assertEquals(50, warrior.getStat(StatNames.HP));
		assertEquals(10, warrior.getStat(StatNames.STR));
		assertEquals(0, warrior.getStat(StatNames.INT));
		assertEquals(10, mage.getStat(StatNames.INT));
		assertEquals(10, rover.getStat(StatNames.AGI));
	}
	
	@Test
	void updateTheAskedStat() {
		warrior.update(StatNames.HP, -10);
		assertEquals(40, warrior.getStat(StatNames.HP));
	}
	
	@Test
	void updateAStatWithNegativeValueReturnsZero() {
		warrior.update(StatNames.HP, -100); // warrior ends up with -50 HP
		assertEquals(0, warrior.getStat(StatNames.HP));
		
		mage.update(StatNames.AGI, -10); // mage ends up with -10 AGI
		assertEquals(0, mage.getStat(StatNames.AGI));
	}
	
	@Test
	void updateHealthPointsTooHighAndReturnBasicHealthPoints() {
		warrior.update(StatNames.HP, 100); // warrior ends up with 150 HP
		assertEquals(50, warrior.getStat(StatNames.HP));
		
		mage.update(StatNames.AGI, 10); // mage ends up with 10 AGI (won't give 0)
		assertEquals(10, mage.getStat(StatNames.AGI));
	}
	
	@Test
	void GetTheDescriptionOfWarrior() {
		warrior.getDescription();
		assertEquals(
				"Woarg je suis le guerrier Gérald "
				+ "niveau 10 je possède 50 de vitalité, "
				+ "10 de force, "
				+ "0 d'agilité et "
				+ "0 d'intelligence !" 
				+ "\n", outContent.toString());
	}
	
	@Test
	void GetTheDescriptionOfMage() {
		mage.getDescription();
		assertEquals(
				"Abracadabra je suis le mage Arnold "
				+ "niveau 10 je possède 50 de vitalité, "
				+ "0 de force, "
				+ "0 d'agilité et "
				+ "10 d'intelligence !" 
				+ "\n", outContent.toString());
	}
	
	@Test
	void GetTheDescriptionOfRover() {
		rover.getDescription();
		assertEquals(
				"Shhhh je suis le vagabond Archibald "
				+ "niveau 10 je possède 50 de vitalité, "
				+ "0 de force, "
				+ "10 d'agilité et "
				+ "0 d'intelligence !" 
				+ "\n", outContent.toString());
	}
	
	
	@Test
	void WarriorAffectsOpponentWithBasicAttack() {
		warrior.basicAttack(mage);
		
		assertEquals("Gérald utilise Coup d'épée et inflige 10 dommages.\n"
				+ "Arnold perd 10 points de vie.\n",
				outContent.toString());
		
		assertEquals(40, mage.getStat(StatNames.HP));
	}
	
	@Test
	void MageAffectsOpponentWithBasicAttack() {
		mage.basicAttack(rover);
		
		assertEquals("Arnold utilise Boule de Feu et inflige 10 dommages.\n"
				+ "Archibald perd 10 points de vie.\n",
				outContent.toString());
		
		assertEquals(40, rover.getStat(StatNames.HP));
	}
	
	@Test
	void RoverAffectsOpponentWithBasicAttack() {
		rover.basicAttack(warrior);
		
		assertEquals("Archibald utilise Tir à l'Arc et inflige 10 dommages.\n"
				+ "Gérald perd 10 points de vie.\n",
				outContent.toString());
		
		assertEquals(40, warrior.getStat(StatNames.HP));
	}
	
	@Test
	void WarriorAffectsOpponentAndHimselfWithSpecialAttack() {
		warrior.specialAttack(mage);
		
		assertEquals("Gérald utilise Coup de Rage et inflige 20 dommages.\n"
				+ "Arnold perd 20 points de vie.\n"
				+ "Gérald perd 5 points de vie.\n", 
				outContent.toString());
		
		assertEquals(45, warrior.getStat(StatNames.HP));
		assertEquals(30, mage.getStat(StatNames.HP));
	}
	
	@Test
	void MageAffectsHimselfWithSpecialAttack() {
		mage.update(StatNames.HP, -30);
		mage.specialAttack(mage);
		
		assertEquals("Arnold utilise Soin et gagne 20 en vitalité.\n", 
				outContent.toString());
		
		assertEquals(40, mage.getStat(StatNames.HP));
	}
	
	@Test
	void RoverAffectsHimselfWithSpecialAttack() {
		rover.specialAttack(rover);
		
		assertEquals("Archibald utilise Concentration et gagne 5 en agilité.\n",
				outContent.toString());
		
		assertEquals(15, rover.getStat(StatNames.AGI));
	}
	
	@Test
	void displayThePlayerIsDeadWhenHPAreAtZeroInAFight() {
		warrior.update(StatNames.HP, -40); // Warrior's HP are at 10
		mage.basicAttack(warrior);
		
		assertEquals("Arnold utilise Boule de Feu et inflige 10 dommages.\n"
				+ "Gérald perd 10 points de vie.\n"
				+ "Gérald est mort.\n", 
		outContent.toString());
	}

}
