package performers;

import graphics.Point2D;

/**
 * A superclass for all of our monsters in the game.
 * An abstract class cannot be initialized, so initialize subclasses of it instead.
 * For subclasses, we are forced to write the code of the abstract methods.
 * For regular methods in the superclass, we get to use these in any subclass,
 * without having to write them in the subclasses again and again.
 */
public abstract class Monster {
	protected int hitPoints;
	protected int strength;
	protected int wisdom;
	
	/**
	 * Every monster should have hit points, and some strength, and some wisdom;
	 * But, this is really up to your own design, and you can change these to keep track
	 * of different data that is common to all your monsters.
	 * 
	 * @param hitPoints
	 *   An amount of damage a monster must have removed before dying.
	 * @param strength
	 *   An amount of physical ability a moster has.
	 */
	public Monster(int hitPoints, int strength, int wisdom) {
		this.hitPoints = hitPoints;
		this.strength = strength;
		this.wisdom = wisdom;
	}
	
	/**
	 * Decreases hit points of this montser.
	 * 
	 * @param damage
	 *   The amount of damage this monster will receive to reduce its hit points.
	 */
	public void damageHitPoints(int damage) {
		if (hitPoints > damage)
			hitPoints -= damage;
		else
			hitPoints = 0;
	}
	
	/**
	 * Returns the hit points of this monster.
	 *
	 * @return
	 *   The hit points of this monster.
	 */
	public int getHitPoints() {
		return hitPoints;
	}
	
	/**
	 * Checks whether this montser is dead or still alive.
	 * 
	 * @return
	 *   A value of <code>true</code> if this monster has no hit points, 
	 *   and <code>false</code> otherwise.
	 */
	public boolean isDead() {
		return hitPoints == 0;
	}
	
	/**
	 * Each subclass monster should have a unique attack.
	 * 
	 * @param plyr
	 *   Access to the player instance so you can control it from inside a monster.
	 * 
	 * @return
	 *   An array of strings, describing the results of this monster attack on the player.
	 */
	public abstract String[] attack(Player plyr);
	
	/**
	 * Each subclass monster should have a unique spell to cast.
	 * 
	 * @param plyr
	 *   Access to the player instance so you can control it from inside a monster.
	 * 
	 * @return
	 *   An array of strings, describing the results of this monster spell on the player.
	 */
	public abstract String[] castSpell(Player plyr);
	
	/**
	 * Get a description of the kind of subclass of this monster.
	 * 
	 * @return
	 *   The kind of subclass of this monster in the form of a description in
	 *   the context of the story.
	 */
	public abstract String getDescription();
	
}