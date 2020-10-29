package logic;

import format.Billboard;
import gui.PanelIO;
import gui.PanelStats;

/**
 * This is the class that will manage formatting all the output that needs to
 * be printed by the descriptions and action information of the game.
 * Use its methods to make sure your output is both easier to read, and 
 * adds a small bit of personality to the Narrator descriptions.
 * 
 * @author Dr Russell Campbell
 *
 */
public class OutputManager {
	private static Billboard billboard = new Billboard();
	private static PanelIO outIO;
	private static PanelStats outStats;
	
	/**
	 * So the text we want to print knows which PanelIO to send its descriptions.
	 * Also, so that it knows where to get user input.
	 * 
	 * @param outIO
	 *   A PanelIO instance for user interaction with its GUI.
	 */
	public void setPanelIO(PanelIO outIO) {
		this.outIO = outIO;
	}
	
	/**
	 * So the text we want to print knows which PanelStats to send its statistics.
	 * 
	 * @param outStats
	 *   A PanelStats instance for the user to read with its GUI.
	 */
	public void setPanelStats(PanelStats outStats) {
		this.outStats = outStats;
	}
	
	// METHODS FOR DISPLAYING TEXT TO THE PanelIO output
	
	/**
	 * This is meant to be used to print Narrator story inside a fancy billboard.
	 * 
	 * @param description
	 *   The array of string descriptions to be printed, each string on its own
	 *   line and centred inside the boundary of its rectangular border. Have some
	 *   empty string elements inside the array to break up reading too much text.
	 */
	public void printFancyIO(String... description) {
		outIO.append(billboard.getFormattedMessage(description));
		printBlankLineIO();
	}
	
	/**
	 * This is meant to be called by the InputManager for prompts. Makes InputManager
	 * code easier to read.
	 * 
	 * @param prompt
	 *   One string meant to ask the user to enter something.
	 */
	public void printPromptIO(String prompt) {
		outIO.append(prompt);
	}
	
	/**
	 * This is meant to be used by the Game for descriptive information that does
	 * not need to be printed in a fancy billboard box, because it is not a narrative
	 * part of the story. Also makes Game code easier to read. It could even
	 * help with debugging as an alternative that lets you print an array of strings to
	 * see it within the PanelIO output.
	 * 
	 * @param info
	 *   An array of strings to give information about current game descriptions.
	 *   Make sure you are printing enough information so the user knows what is
	 *   happening in the game.
	 */
	public void printInfoIO(String... info) {
		outIO.append(info);
		printBlankLineIO();
	}
	
	/**
	 * Sometimes you just want to space things out a bit. If you want different output
	 * behaviour, then you can just change this method, instead of rewriting everywhere
	 * a System.out.println() is used.
	 */
	public void printBlankLineIO() {
		outIO.append("\n");
	}
	
	/**
	 * Clears the text currently displayed in the PanelIO of the application window,
	 * specifically, the output JTextArea, not the input JTextField.
	 */
	public void clearIO() {
		outIO.clear();
	}
	
	// METHODS FOR DISPLAYING TEXT TO PanelStats output
	
	/**
	 * Clears the text currently displayed in the PanelStats output.
	 */
	public void clearStats() {
		outStats.clear();
	}
	
	/**
	 * This is meant to be used by the Game for stat information. Also makes Game
	 * code easier to read. It could even help with debugging when you just
	 * want to print an array of strings to output.
	 * 
	 * @param info
	 *   An array of strings to give information about current game stats.
	 *   Make sure you are printing enough information so the user knows what is
	 *   the state of objects in the game (the current values of certain instance
	 *   variables).
	 */
	public void printInfoStats(String... info) {
		outStats.append(info);
	}
	
	/**
	 * Sometimes you just want to space things out a bit. If you want different output
	 * behaviour, then you can just change this method, instead of rewriting everywhere
	 * a System.out.println() is used.
	 */
	public void printBlankLineStats() {
		outStats.append("\n");
	}
	
}