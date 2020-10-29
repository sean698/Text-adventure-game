package logic;

import gui.PanelIO;
import performers.PlayerType;

/**
 * This is the class that will manage all the input from the user as your game
 * interacts with the story you design.
 * It was created so that you would not have to set up as many parameters when
 * creating your own methods in the project code.
 * 
 * @author Dr Russell Campbell
 *
 */
public class InputManager {
	
	private static PanelIO in;
	private static OutputManager out = new OutputManager();
	private static GameThread gameThread; // so InputManager can wait when it needs user input
	private String[] choiceHistory = new String[5]; // place to save the last 5 user input choices
	private int currentChoice = choiceHistory.length - 1; // index for choiceHistory
	
	/**
	 * Sets a reference to a PanelIO instance that the user needs to interact with later.
	 * 
	 * @param in
	 *   The PanelIO instance that the user needs to interact with.
	 */
	public void setPanelIO(PanelIO in) {
		this.in = in;
	}
	
	/**
	 * Sets a GameThread instance that this InputManager needs to be able to signal
	 * in order to both pause and resume the game later.
	 * 
	 * @param gameThread 
	 *   The GameThread instance that this InputManager needs to be able to signal.
	 */
	public void setGameThread(GameThread gameThread) {
		this.gameThread = gameThread;
	}
	
	/**
	 * Prints a prompt to the user for some String input, 
	 * and then waits for the user to enter one line of text.
	 * 
	 * @param msg 
	 *   The prompt for the user to read, so the player
	 *   knows what kind of input to type.
	 *   
	 * @return 
	 *   The line of text the user entered.
	 */
	public String getInputString(String msg) {
		out.printPromptIO(msg);
		gameThread.pause();
		setHistory(in.getInputString());
		return getHistory(0);
	}
	
	/**
	 * Prints a prompt to the user for an integer input, 
	 * and then waits for the user to enter an integer.
	 * 
	 * @param msg 
	 *   The prompt for the user to read, so the player
	 *   knows what integer values they can choose.
	 *   
	 * @return 
	 *   The integer the user entered.
	 */
	public int getInputInt(String msg) {
		out.printPromptIO(msg);
		gameThread.pause();
		// The user *could* type something that is not a number; debug and fix this!
		int choice = Integer.parseInt(in.getInputString());
		out.clearIO();
		
		return choice;
	}
	
	/**
	 * A prompt for the player so they have some time to read output. You should be using
	 * this method in Game quite often, so the user is not overwhelmed with output.
	 */
	public void pause() {
		// Use this when you want to pause output so the 
		// player can read it before printing more output.
		getInputString("(press \"submit\" button to continue...)");
		out.clearIO(); // you might not want output to be erased every time after pausing...
	}
	
	/**
	 * This validates the player's input choice, given a maximum number of choices.
	 * Note that it does NOT handle input data with mismatched types. Fix that.
	 * 
	 * @param choice
	 *   The input integer the user entered.
	 *   
	 * @param n
	 *   The maximum number of choices the user has.
	 *   
	 * @return
	 *   Will return <code>true</code> if the user's choice is valid, and
	 *   <code>false</code> otherwise.
	 */
	private boolean validateUserChoice(int choice, int n) {
		if (choice >= n) {
			out.printInfoIO("You need to choose among the given options.");
			pause();
			return false; // we should not continue and try to access PlayerType
		}
		return true;
	}
	
	/*
	 * This prints the prompt for the choices the player has to choose.
	 */
	private void promptForChoice(String prompt, String[] choices) {
		int n = choices.length;
		String[] choicesMessage = new String[n+1];
		choicesMessage[0] = prompt;
		for (int i = 0; i < n; i++) {
			choicesMessage[i+1] = 
				" - "
				+ choices[i]
				+ " (enter " + i + ")";
		}
		
		out.printFancyIO(choicesMessage);
	}
	
	/**
	 * This asks the user for their first choice: 
	 * which type of player they want to be.
	 * 
	 * @return
	 *   A valid integer number chosen by the user within the range of possible choices.
	 */
	public int getPlayerChoice() {
		int choice = -1;
		boolean validChoice = false;
		while (!validChoice) {
			promptForChoice(
				"The possible people you can play:",
				PlayerType.getValues()
			);
			
			choice = getInputInt("Choose a person. (type choice below)");
			
			int n = PlayerType.size();
			validChoice = validateUserChoice(choice, n);
		}
		
		// Record their choice.
		switch (PlayerType.values()[choice]) {
			case BATMAN:
				setHistory("- You chose The Batman!");
				break;
			case DAREDEVIL:
				setHistory("- You chose The Daredevil!");
				break;
			case RORSCHACH:
				setHistory("- You chose Rorschach!");
				break;
		}
		return choice;
	}
	
	/**
	 * This asks the user for their action choice during a brawl with monsters.
	 * 
	 * @return
	 *   A valid integer number chosen by the user within the range of possible choices.
	 */
	public int getActionChoice() {
		int choice = -1;
		boolean validChoice = false;
		while (!validChoice) {
			promptForChoice(
				"The possible things you can do:",
				Actions.getValues()
			);
			
			choice = getInputInt("Choose an action. (type choice below)");
			
			int n = Actions.size();
			validChoice = validateUserChoice(choice, n);
		}
		
		switch (Actions.values()[choice]) {
			case DEFEND:
				setHistory("You chose to defend.");
				break;
			case ATTACK:
				setHistory("You chose to attack.");
				break;
			case CAST:
				setHistory("You chose to cast a spell.");
				break;
		}
		
		// Code for processing their choice is more involved and should be in Game.java.
		
		return choice;
	}
	
	/**
	 * This asks the user for their choice for the first story choice.
	 * 
	 * @return
	 *   A valid integer number chosen by the user within the range of possible choices.
	 */
	public int getStroyChoice() {
		int choice = -1;
		boolean validChoice = false;
		while (!validChoice) {
			promptForChoice(
				"Do you want to just give up or fight till death?",
				Storys.getValues()
			);
			
			choice = getInputInt("Please make your decision. (Your choice will affect the ending.)");
			
			int n = Storys.size();
			validChoice = validateUserChoice(choice, n);
		}
		
		switch (Storys.values()[choice]) {
			case GIVEUP:
				setHistory("You chose to get back to normal life.");
				break;
			case FIGHT:
				setHistory("You chose to fight till death!");
				break;
		}
		return choice;
	}
	
	/**
	 * This asks the user for their choice for the second story choice.
	 * 
	 * @return
	 *   A valid integer number chosen by the user within the range of possible choices.
	 */
	public int getFinalChoice() {
		int choice = -1;
		boolean validChoice = false;
		while (!validChoice) {
			promptForChoice(
				"Do you want to let him go or hand him over to the police?",
				FinalChoice.getValues()
			);
			
			choice = getInputInt("Please make your decision. (Your choice will affect the ending.)");
			
			int n = FinalChoice.size();
			validChoice = validateUserChoice(choice, n);
		}
		
		switch (FinalChoice.values()[choice]) {
			case LETGO:
				setHistory("You chose to get back to normal life.");
				break;
			case CATCH:
				setHistory("You chose to fight till death!");
				break;
		}
		return choice;
	}
	
	/**
	 * This asks the user for their choice for the potions they have.
	 * 
	 * @return
	 *   A valid integer number chosen by the user within the range of possible choices.
	 */
	public int getBagChoice() {
		int choice = -1;
		boolean validChoice = false;
		while (!validChoice) {
			promptForChoice(
				"The kinds of potions that you have:",
				BagChoice.getValues()
			);
			
			choice = getInputInt("Choose a potion to use. (type choice below)");
			
			int n = BagChoice.size();
			validChoice = validateUserChoice(choice, n);
		}
		
		switch (BagChoice.values()[choice]) {
			case STRENGTH:
				setHistory("- You just used the strength potion.");
				break;
			case WISDOM:
				setHistory("- You just used the wisdom potion.");
				break;
			case HEALTH:
				setHistory("- You just used the health potion.");
				break;
		}
		return choice;	
	}
	// Only the InputManager needs to record what the user chooses.
	// You could design a different way of keeping track of what the user chooses.
	private void setHistory(String choice) {
		currentChoice++; // incrementing first makes it a bit easier for writing code of getHistory
		currentChoice %= choiceHistory.length; // modulo wrap index back to 0 if needed
		choiceHistory[currentChoice] = choice;
	}
	
	/**
	 * This gets a description of a choice made by the user at <code>back</code> number of inputs
	 * ago. If you are reading this, then congratulations, you have found extra code you can use
	 * that is not currently called anywhere else in the game. Feel free to modify it to do
	 * something more interesting, or use it the way it is in an interesting way.
	 * 
	 * @param back
	 *   The number of elements backward to read one choice from the choiceHistory array.
	 *   Need to add some validation code here to make sure the integer passed in is less than
	 *   the number of elements in the choiceHistory array.
	 * 
	 * @return
	 *   A string describing the choice made <i>back</i> number of choices ago.
	 */
	public String getHistory(int back) {
		int prevIndex = currentChoice;
		prevIndex += (choiceHistory.length - back);
		prevIndex %= choiceHistory.length;
		// May seem weird, but modulo does not have standard behaviour with negative numbers
		// across programming languages, so you always want to avoid the issue by using only
		// positive numbers involved; done here by adding choiceHistory.length in the calculations.
		return choiceHistory[currentChoice];
	}
}