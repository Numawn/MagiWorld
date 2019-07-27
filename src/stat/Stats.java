package stat;

import java.util.HashMap;
import java.util.Map;

import exceptions.LevelValueIsInvalid;
import exceptions.StatisticPointsAreInvalid;

/**
 * 
 * @author Numawn
 * 
 * This class represent all the stats a character has.
 * It has the responsibility to get and set the 
 * value of one statistic.
 *
 */

public class Stats {

	private Map<String, Integer> statistics = new HashMap<String, Integer>();
	private int maxHP;
	
	public Stats(Stat... stats) {
		int statPoints = 0;
		
		for(Stat stat : stats) {
			this.statistics.put(stat.getName(), stat.getValue());
			if(!stat.getName().equals(StatNames.LVL)) {
				statPoints += stat.getValue();
			}
		}
		
		this.maxHP = statistics.get(StatNames.LVL) * 5;
		this.statistics.put(StatNames.HP, maxHP);
		
		this.verifyStatPoints(statPoints);
		
	}
	
	private void verifyStatPoints(int statPoints) {
		int level = statistics.get(StatNames.LVL);
	
		if(level < 1 || level > 100) {
			throw new LevelValueIsInvalid("Le niveau doit être compris entre 1 et 100 !");
			
		}else if(statPoints != level) {
			throw new StatisticPointsAreInvalid("Les points de stat doivent être égaux au niveau !");
		}

	}

	public int getStat(String name) {
		return this.statistics.get(name);
	}
	
	public void update(String name, int affectedVal) {
		int curVal = statistics.get(name);
		
		if(curVal + affectedVal < 0) 
			statistics.replace(name, 0);

		else if(name.equals(StatNames.HP) && curVal + affectedVal > maxHP)
			statistics.replace(name, maxHP);

		else
			this.statistics.replace(name, curVal + affectedVal);


	}
	
	
	public void display() {
		System.out.print(
				" niveau " + statistics.get(StatNames.LVL) 
				+ " je possède " + statistics.get(StatNames.HP)+ " de vitalité, "
				+ statistics.get(StatNames.STR) + " de force, "
				+ statistics.get(StatNames.AGI) + " d'agilité et "
				+ statistics.get(StatNames.INT) + " d'intelligence !"
				 + "\n");
	}
	
}
