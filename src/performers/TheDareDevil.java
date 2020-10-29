package performers;

import logic.Random;

/**
 * The Daredevil is from DC comics, and he is a strength hero.
 * 
 * @author Shiyuan Miao 300163777
 *
 */
public class TheDareDevil extends Player {
	
	public TheDareDevil() {
		super(
			Random.rand(10) + 90,  //hitpoints
			Random.rand(20) + 30,  //strength
			Random.rand(5) + 5,    //wisdom
			20,					   //mana
			0					   //armor
		);	
	}
	
	/**
	 * Basic attack; Dependent on the strength.
	 * 
	 * @param monsters
	 *   Attack 1 monster every time.
	 *   
	 * @return
	 *   An array of strings, describing the results of this player attack on the monsters.
	 */
	@Override
	public String[] attack(Monster... monsters) {
		armor = 0;
		String[] results = new String[monsters.length + 1];
		int i = 0;
		results[i++] = "You kick an enemy down with your leg.";
		
		// Describe stats on this monster.
		int damage = Random.rand(strength) + 2;
		monsters[0].damageHitPoints(damage);
		results[i++] = 
			"- hit " + 
			monsters[0].getDescription() + 
			" with " + 
			damage + 
			" damage;" +
			" it has " + 
			monsters[0].getHitPoints() +
			" hit points remaining.";

		return results;
	}
	
	/**
	 * Spell attack; Dependent on the wisdom, and consume mana;
	 * 
	 * @param monsters
	 *   Attack 3 monsters every time.
	 * 
	 * @return
	 *   An array of strings, describing the results of this player attack on the monsters.
	 */
	@Override
	public String[] castSpell(Monster... monsters) {
		armor = 0;
		String[] results = new String[monsters.length + 2];
		int i = 0;
		results[i++] = "You cast poisonous smoke to the enemies to paralyze them.";

		for (Monster mnstr : monsters) {
			int damage = Random.rand(wisdom) + 5;
			mnstr.damageHitPoints(damage);
			
			// Describe stats on this monster.
			results[i++] = 
				"- hit " + 
				mnstr.getDescription() + 
				" with " +  
				damage + 
				" damage;" +
				" it has " + 
				mnstr.getHitPoints() +
				" hit points remaining.";
			mana --;
		}
		
		results[i++] = "- You have " + mana + " mana remaining.";
		return results;	
	}
	
	/**
	 * Reduce some damage you take and restore 3 mana.
	 * 
	 * @return
	 *   An array of strings, describing the results after this player defend.
	 */
	@Override
	public String[] defend() {
		armor = 3;
		mana += 3;
		String[] results = new String[] {
			"You blocked some attacks and suffered a small amount of damage.",
			"- Your mana has been restored by 3.",
			"- You have " + mana + " mana remaining."
		};
		return results;
	}
}
