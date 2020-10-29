import logic.Game;

/**
 * This class helps to have a simple compile command on the command line.
 * Other classes in packages cannot import Console because it is in the
 * default package (not inside any subfolder of <code>src</code>).
 * <br><br>
 * Please rewrite this doc comment to explain the basic concept of your
 * game, but not any of its code details.
 * <br><br>
 * In other words: give a one or two sentence summary.
 * <br><br>
 * This is the main program that demos how to use a Game instance.
 * In the example code given, the user can play a basic text adventure
 * game with separate user interface components that each take
 * care of text-graphics animation, displaying statistics, story
 * narrative, and one-line user input so the user can make choices.
 * 
 * @author Dr Russell Campbell
 */
class Console {
	
	/**
	 * Program execution starts here.
	 * 
	 * @param args 
	 *   An array of Strings that we can use to pass data into our program when it runs.
	 */
	public static void main(String[] args) {
		Game game = new Game();
		game.play();
	}
	
}
