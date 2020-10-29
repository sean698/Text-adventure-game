package logic;

import javax.swing.SwingUtilities;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import graphics.Drawable;
import graphics.Box;
import graphics.Circle;
import graphics.Fuzzy;
import gui.AnimatedGUI;
import gui.Panel2D;
import performers.Player;
import performers.Monster;
import story.Narrator;
import items.*;

/**
 * This is the class that will manage all parts of the game, bringing them together.
 * Its execution sets up the application window, begins other threads for animation,
 * and loops through the story of the game until the user either wins or loses.
 * 
 * @author Dr Russell Campbell
 *
 */
public class Game {
	
	// Only need one instance of each piece of the game, so they can all be static.
	
	// For the main application window for our program.
	private static AnimatedGUI window;
	// For the separate execution of our game logic.
	private static Thread gameThread;
	// For controlling the sharing of information between parts of our game.
	private static InputManager in = new InputManager();
	private static OutputManager out = new OutputManager();
	private static DataManager data = new DataManager();
	
	// For performers within our game that can do various interesting things.
	private static Player plyr;
	private static ArrayList<Monster> monsters;
	// How many monsters the player must defeat.
	private static final int N_MONSTERS = 5;
	// Which chapter is in progress now.
	private static int chapter = 1;
	
	/**
	 * This is where the game begins.
	 */
	public void play() {
		
		setupApplicationWindow();
		setupAnimations();
		
		GameThread gameThread = new GameThread();
		in.setGameThread(gameThread);
		window.getPanelIO().setGameThread(gameThread);
		gameThread.start(); // begins execution of gameLoop method
		
		// Take special note of the small size of each method.
		// Small methods are easier to understand and debug.
	}
	
	private void setupApplicationWindow() {
		
		// our main application
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					window = new AnimatedGUI();
				}
			});
		} catch (InterruptedException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		// Connect GUI to the rest of our program.
		out.setPanelIO(window.getPanelIO());
		out.setPanelStats(window.getPanelStats());
		in.setPanelIO(window.getPanelIO());
	}
	
	private void setupAnimations() {
		// Now we have interfaces to let us collect objects that are NOT related.
		// This allows us to call the methods on each object in the collection described in Drawable.
		ArrayList<Drawable> actors = new ArrayList<Drawable>();
		actors.add(
			new Box(3, 25, 28, 6, 6, Panel2D.ORANGE)
		);
		actors.get(0).setParent(window.getPanel2D());
		window.getPanel2D().setActors(actors);
		
		// Can still change actors after connecting with Panel2D.
		actors.add(
			new Circle(10, 10, 3, 4, Panel2D.BLUE)
		);
		
		actors.add(
			new Fuzzy(20, 10)
		);
		// Fuzzy doesn't share static parentPanel with the other Shapes,
		// because it is not a subclass of Shape, so we have to set it separately.
		actors.get(2).setParent(window.getPanel2D());
		
		window.beginAnimation(); // same concepts as in Assignment 2
	}
	
	/**
	 * The basic program flow-of-control for our game.
	 * You could design your own flow-of-control if you want to make
	 * a completely different game. The basic flow here is for every iteration
	 * to interact with the user, and then process their choices until the user
	 * either achieves their goal, or fails.
	 */
	static void gameLoop() {
		start();
		
		while (!Narrator.isAtFinalChapter()) {
			iteration(); // designed for Narrator to advance one chapter each iteration
		}
		
		finish();
	}
	
	/*
	 * The start of the game. Initialize and connect any part of the game that should 
	 * be set up before the iteration loop.
	 */
	private static void start() {
		Narrator.setData(data);
		data.setUserName(in.getInputString("Please enter your name, traveller! (enter below)"));
		// Needed to store the user's name in data, because there is no Player intsance created yet.
		// This is because the user still has to choose which subclass of Player they want to be.
		
		out.printFancyIO(Narrator.tell());
		in.pause();
		
		int choice = in.getPlayerChoice();
		plyr = data.createPlayer(choice);
		data.setPlayer(plyr);
		out.printInfoIO(in.getHistory(0));
		
		monsters = new ArrayList<Monster>();
		data.createMonsters(monsters, N_MONSTERS);
	}
	
	/*
	 * This method is the part of the game that can be repeated. The basic
	 * idea is to give the player a choice, and then process that choice
	 * towards some kind of goal. Attacking monsters is a <b>very</b> common 
	 * theme in many games.
	 */
	private static void iteration() {		
		// Describe some interesting events.
		out.printFancyIO(Narrator.tell());
		playerPrintStats();
		monsterPrintStats();
		in.pause();
		if (plyr.isDead()) return;
		// Player and monsters take turns attacking each other until either has no hit points.
		while (!monsters.isEmpty()) {
			
			playerAction();
			// You could design attacks on a random number of randomly chosen monsters.
			monsterActions();
			
			out.printInfoIO("You have " + plyr.getHitPoints() + " hit points left.");
			
			out.clearStats();
			playerPrintStats();
			monsterPrintStats();
			in.pause(); // give user time to absorb reading output of actions
			
			// No need to loop if player has no hit points remaining.
			if (plyr.isDead()) {
				// Narrator will give details of losing the game elsewhere.
				return;
			}
		}
		
		if (chapter == 1 || chapter == 2) {
			out.printInfoIO("----------\\\\ YOU DEFEATED ALL THE ENEMIES! ///----------");
			in.pause();
		}
	
		//  If the next chapter is 5, the monster will be an unique monster.
		if (chapter + 1 == 5) DataManager.chapter = 5;
			
		//  If the next chapter is 2 or 5, creating monsters.
		if (chapter + 1 == 2 || chapter + 1 == 5) {
			monsters = new ArrayList<Monster>();
			data.createMonsters(monsters, N_MONSTERS);
		}
		
		chapter ++;
	}
	
	// Let the player decide what they want to do in a monster battle.
	private static void playerAction() {
		
		int choice = in.getActionChoice();
		
		String[] results = new String[] { "no actions processed yet" };
		switch (Actions.values()[choice]) {
			case DEFEND:
				// Use your imagination to write code to allow the player to defend.
				results = plyr.defend();
				break;
			case ATTACK:
				// These are arbitrary actions, and you need to change them.
				results = plyr.attack(monsters.get(0));
				break;
			case CAST:
				if (monsters.size() > 2)
					results = plyr.castSpell(monsters.get(0), monsters.get(1), monsters.get(2));
				else if (monsters.size() > 1)
					results = plyr.castSpell(monsters.get(0), monsters.get(1));
				else
					results = plyr.castSpell(monsters.get(0));
				break;
			case BAG:
				int bagChoice = in.getBagChoice();
				switch(BagChoice.values()[bagChoice]) {
				case STRENGTH:
					StrengthPotion sPotion = new StrengthPotion();
					plyr.setStrength(plyr.getStrength() + sPotion.getIncrease());
					results = sPotion.use();
					break;
				case WISDOM:
					WisdomPotion wPotion = new WisdomPotion();
					plyr.setWisdom(plyr.getWisdom() + wPotion.getIncrease());
					results = wPotion.use();
					break;
				case HEALTH:
					HealthPotion hPotion = new HealthPotion();
					plyr.setHitPoints(plyr.getHitPoints() + hPotion.getIncrease());
					results = hPotion.use();
					break;
				}
				break;
		}
		
		out.printInfoIO(results);
	}
	
	// Clean up the monsters collection, and let remaining monsters do some actions.
	private static void monsterActions() {
		
		removeDefeatedMonsters();
		
		for (Monster mnstr : monsters) {
			String[] results;
			int mnstr_choice = Random.rand(2) + 1; // +1 a way to omit DEFEND choice; redesign this
			switch (Actions.values()[mnstr_choice]) {
				case ATTACK:
					results = mnstr.attack(plyr);
					out.printInfoIO(results);
					break;
				case CAST:
					results = mnstr.castSpell(plyr);
					out.printInfoIO(results);
					break;
			}
			// No need to keep attacking if player has no hit points remaining.
			if (plyr.isDead()) {
				// Narrator will give details of losing the game elsewhere.
				return;
			}
		}
	}
	
	// Monsters with zero hit points should be removed from the monsters collection.
	private static void removeDefeatedMonsters() {
		ArrayList<Monster> temp = new ArrayList<Monster>();
		for (Monster mnstr : monsters)
			if (!mnstr.isDead()) temp.add(mnstr); // this avoids needing iterators
		monsters = temp;
		data.setMonsters(monsters); // update references to other parts of the game
	}
	
	// You could add code to print other statistics about the player.
	private static void playerPrintStats() {
		out.printInfoStats(
			data.getUserName() + "'s hit points: " + plyr.getHitPoints()
		);
		out.printBlankLineStats();
	}
	
	// Prints the stats of remaining monsters---again, you could design more statistics.
	private static void monsterPrintStats() {
		if (!monsters.isEmpty()) {
			String[] results = new String[monsters.size()];
			int k = 0;
			for (Monster mnstr : monsters) {
				results[k++] = 
					"There is " + 
					mnstr.getDescription() + 
					" remaining with " + 
					mnstr.getHitPoints() + 
					" hit points left.";
			}
			out.printInfoStats(results);
		}
	}
	
	// You should put any code here you want to be the last thing to execute.
	// Maybe a climactic animation?
	private static void finish() {
		// You could have different endings described in the Narrator.
		out.printFancyIO(Narrator.tell());
	}
}