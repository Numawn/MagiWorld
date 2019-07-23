package characterTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import character.Player;
import character.Rover;
import character.Mage;
import character.Warrior;
import stat.Stat;
import stat.Stats;

class CharacterTest {
	
	private Player warrior = new Warrior("Warrior",new Stats(new Stat("Niveau",10),new Stat("PV",50), new Stat("Attack",10), new Stat("Intelligence",0), new Stat("Agilité",0)));
	private Player mage = new Mage("Mage", new Stats(new Stat("Niveau",10),new Stat("PV",50), new Stat("Attack",0), new Stat("Intelligence",10), new Stat("Agilité",0)));
	private Player rover = new Rover("Rover", new Stats(new Stat("Niveau",10),new Stat("PV",50), new Stat("Attack",0), new Stat("Intelligence",0), new Stat("Agilité",10)));
	
	
	@Test
	void ReturnTheAskedStatValue() {
		
	}
	
	@Test
	void SetNewValueToTheAskedStat() {
		
	}
	
	@Test
	void GetTheDescriptionOfCharacter() {
		
	}
	
	@Test
	void ImpossibleToHaveNegativeLevel() {
		
	}
	
	@Test
	void WarriorAffectsOpponentWithBasicAttack() {
		
	}
	
	@Test
	void MageAffectsOpponentWithBasicAttack() {
		
	}
	
	@Test
	void RoverAffectsOpponentWithBasicAttack() {
		
	}
	
	@Test
	void WarriorAffectsOpponentAndHimselfWithSpecialAttack() {
		
	}
	
	@Test
	void MageAffectsHimselfWithSpecialAttack() {
		
	}
	
	@Test
	void RoverAffectsHimselfWithSpecialAttack() {
		
	}

}
