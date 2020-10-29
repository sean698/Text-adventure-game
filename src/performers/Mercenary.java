package performers;

import logic.Random;

/**
 * A mercenary is a professional soldier hired to serve in a foreign army.
 * The unique attack is using a dagger.
 */
public class Mercenary extends Monster {
	
	public Mercenary() {

		super(
			Random.rand(5) + 10,  // hitPoints
			Random.rand(5) + 3,   // strength
			Random.rand(5) + 3    // wisdom
		);
	}
	
	/**
	 * A basic attack for the Mercenary type of monster.
	 * 
	 * @param plyr
	 *   The person the user is using to play the game.
	 * 
	 * @return
	 *   An array of strings, describing the results of a Mercenary attack on the player.
	 */
	@Override
	public String[] attack(Player plyr) {
		plyr.damageHitPoints(strength - plyr.armor);
		return new String[] { "A mercenary attacked you for " + (strength - plyr.armor) + " damage!" };
	}
	
	/**
	 * A more descriptive result to go along with a Mercenary's unique spell (or ability) to cast.
	 * 
	 * @param plyr
	 *   The person the user is using to play the game.
	 * 
	 * @return
	 *   An array of strings, describing the results of a Mercenary's spell on the player.
	 */
	@Override
	public String[] castSpell(Player plyr) {
		plyr.damageHitPoints(wisdom + 2 - plyr.armor);
		return new String[] {
			"A mercenary rushes to you and plunge into your shoulder with a dagger!",
			"You are injured, the blood splashed out, and you take " + (wisdom + 2 - plyr.armor) + " damage!"
		};
	}
	
	/**
	 * Get a Mercenary description.
	 * 
	 * @return
	 *   A short phrase describing a Mercenary.
	 */
	@Override
	public String getDescription() {
		return "a mercenary";
	}
}