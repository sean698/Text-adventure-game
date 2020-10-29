package performers;

import logic.Random;

/**
 * A killer is a well-trained fighter hired to kill other people.
 * The unique attack is shooting darts.
 */
public class Killer extends Monster {
	
	public Killer() {

		super(
			Random.rand(5) + 10,  // hitPoints
			Random.rand(5) + 3,   // strength
			Random.rand(5) + 3    // wisdom
		);
	}
	
	/**
	 * A basic attack for the Killer type of monster.
	 * 
	 * @param plyr
	 *   The person the user is using to play the game.
	 * 
	 * @return
	 *   An array of strings, describing the results of a Killer attack on the player.
	 */
	@Override
	public String[] attack(Player plyr) {
		plyr.damageHitPoints(strength - plyr.armor);
		return new String[] { "A killer attacked you for " + (strength - plyr.armor) + " damage!" };
	}
	
	/**
	 * A more descriptive result to go along with a Killer's unique spell (or ability) to cast.
	 * 
	 * @param plyr
	 *   The person the user is using to play the game.
	 * 
	 * @return
	 *   An array of strings, describing the results of a Killer's spell on the player.
	 */
	@Override
	public String[] castSpell(Player plyr) {
		plyr.damageHitPoints(wisdom + 2 - plyr.armor);
		return new String[] {
			"A killer hides in the shadows and shoots darts at you!",
			"You are injured, the blood splashed out, and you take " + (wisdom + 2 - plyr.armor) + " damage!"
		};
	}
	
	/**
	 * Get a killer description.
	 * 
	 * @return
	 *   A short phrase describing a Killer.
	 */
	@Override
	public String getDescription() {
		return "a killer";
	}
}