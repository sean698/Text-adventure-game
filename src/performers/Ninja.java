package performers;

import logic.Random;

/**
 * Everybody knows what a Ninja is.
 * The unique attack is using smoke bombs.
 */
public class Ninja extends Monster {
	
	/**
	 * To create a Ninaj, the superclass constructor is called first.
	 * You could make more variables to design more interesting things if you wanted.
	 */
	public Ninja() {

		super(
			Random.rand(5) + 10,  // hitPoints
			Random.rand(5) + 3,   // strength
			Random.rand(5) + 3    // wisdom
		);
	}
	
	/**
	 * A basic attack for the Ninja type of monster.
	 * 
	 * @param plyr
	 *   The person the user is using to play the game.
	 * 
	 * @return
	 *   An array of strings, describing the results of a Ninja attack on the player.
	 */
	@Override
	public String[] attack(Player plyr) {
		plyr.damageHitPoints(strength - plyr.armor);
		return new String[] { "A ninja attacked you for " + (strength - plyr.armor) + " damage!" };
	}
	
	/**
	 * A more descriptive result to go along with a Ninja's unique spell (or ability) to cast.
	 * 
	 * @param plyr
	 *   The person the user is using to play the game.
	 * 
	 * @return
	 *   An array of strings, describing the results of a Ninja's spell on the player.
	 */
	@Override
	public String[] castSpell(Player plyr) {
		plyr.damageHitPoints(wisdom - plyr.armor);
		return new String[] {
			"A ninja throws a smoke bomb at you, and then attacks you when you can't see anything!",
			"You are injured, the blood splashed out, and you take " + (wisdom - plyr.armor) + " damage!"
		};
	}
	
	/**
	 * Get a Ninja description.
	 * 
	 * @return
	 *   A short phrase describing a Killer.
	 */
	@Override
	public String getDescription() {
		return "a ninja";
	}
}