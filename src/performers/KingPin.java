package performers;

import logic.Random;

/**
 * The Kingpin is the Final enemy. He is way more stronger than normal people.
 * The unique attack is using his belt as a whip.
 */
public class KingPin extends Monster{

	public KingPin() {
		super(
			Random.rand(10) + 20,  // hitPoints
			Random.rand(10) + 10,   // strength
			Random.rand(10) + 10    // wisdom
		);
	}
	
	/**
	 * A basic attack for the final enemy.
	 * 
	 * @param plyr
	 *   The person the user is using to play the game.
	 * 
	 * @return
	 *   An array of strings, describing the results of Kingpin attack on the player.
	 */
	@Override
	public String[] attack(Player plyr) {
		plyr.damageHitPoints(strength - plyr.armor);
		String[] result = new String[] {
			"The Kingpin grabs your collar taking you to the air and throw you at the wall!",
			"You slammed into the wall, and you take " + (strength - plyr.armor) + " damage!"
		};
		return result;
	}
	
	/**
	 * A more descriptive result to go along with the Kingpin's unique spell (or ability) to cast.
	 * 
	 * @param plyr
	 *   The person the user is using to play the game.
	 * 
	 * @return
	 *   An array of strings, describing the results of the Kingpin's spell on the player.
	 */
	@Override
	public String[] castSpell(Player plyr) {
		plyr.damageHitPoints(wisdom - plyr.armor);
		return new String[] {
			"The Kingpin wrapps your neck with his belt, and then knocks you down to the ground!",
			"Blood flows out of your nose and mouth, and you take " + (wisdom - plyr.armor) + " damage!"
		};
	}
	
	/**
	 * Get the KingPin description.
	 * 
	 * @return
	 *   A short phrase describing the KingPin.
	 */
	@Override
	public String getDescription() {
		String description = "The Kingpin";
		return description;
	}
	
}
