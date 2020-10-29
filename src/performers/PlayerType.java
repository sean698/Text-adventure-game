package performers;

/**
 * This organizes the player's choices for what type of person they can play.
 * 
 * @author Dr Russell Campbell
 *
 */
public enum PlayerType {
	BATMAN,
	DAREDEVIL,
	RORSCHACH;
	
	/**
	 * A method for returning the set of constants as strings in an array.
	 * 
	 * @return
	 *   An array of strings giving the names of the constants 
	 *   inside this Enum, so that this Enum can be used easier.
	 */
	public static String[] getValues() {
		int n = PlayerType.values().length;
		String[] vals = new String[n];
		int i = 0;
		for (PlayerType type : PlayerType.values()) {
			vals[i] = type.toString();
			i++;
		}
		return vals;
	}
	
	/**
	 * A method to get the number of constants in this Enum.
	 * 
	 * @return
	 *   An integer for the number of constants in this Enum.
	 */
	public static int size() {
		return PlayerType.values().length;
	}
}
