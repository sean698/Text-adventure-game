package story;

import java.util.Arrays;

import logic.DataManager;
import logic.InputManager;

/**
 * This class takes care of describing what is happening in the story events of our game.
 * It is much easier to keep track of all the parts of our story in one place: this class.
 */
public class Narrator {
	// There is only one interactive story, so all methods are static.
	private final static int FINAL_CHAPTER = 8;
	private static int chapter = 0;
	private static DataManager data;
	private static InputManager in = new InputManager();
	private static int storyChoice = -1;
	private static int finalChoice = -1;
	
	// If everything is static, then we never need a constructor.
	// All variables only store one primitive value, or one instance of an object.
	// All methods can execute on the shared variables above.
	
	/**
	 * Connect to the same data instance used by the Game. So the Narrator can know
	 * what the player's name is, and whether they have died or not.
	 * 
	 * @param data
	 *   Should pass in the same data instance used by the Game.
	 */
	public static void setData(DataManager data) {
		Narrator.data = data; // instance shared by Game, Narrator
	}
	
	/**
	 * A method to get the different parts of our story. Each time this method is
	 * executed, the chapter number advances by one value. Extend the given code,
	 * or you could design your story to behave in a completely different way if 
	 * you want. For example, creating descriptions of your story or character 
	 * dialogue as strings in an array or collection, and choosing parts of it 
	 * in different ways, perhaps partly random.
	 *
	 * @return
	 *   An array of string descriptions.
	 */
	public static String[] tell() {
		if (data.isPlayerChosen() && data.isPlayerDead()) {
			chapter = FINAL_CHAPTER;
		}
		
		// we will not need break statements if we return instead
		switch (chapter++) {
			case 0:
				return begin();
			case 1:
				return chapter1();
			// case 2:
				// return chapter2(); WRITE MORE CHAPTERS for plot points 4, 5, and 6
				// the interactivity you design should be written in Game.java
			case 2:
				return chapter2();
			case 3:
				return chapter3();
			case 4:
				return chapter4();
			case 5:
				return chapter5();
			case 6:
				return chapter6();
			case 7:
				return chapter7();
			case FINAL_CHAPTER:
				return end(); // you will need to change the number for FINAL_CHAPTER as part of your design
			default:
				return new String[] { "Done telling a story. (this message is for help with debugging)" };
		}
	}
	
	// The start of the story.
	private static String[] begin() {
		/* 
		 * String of dashes below is example of maximum length line we can have in
		 * a Billboard (62 chars) it is designed this way in the Billboard code by way
		 * of throwing Exceptions. This forces other programmers to follow your
		 * method designs and get useful debug error messages that are expected from
		 * taking advantage of exceptions.
		 *  "--------------------------------------------------------------"
		 */
		String[] intro = {
			"Hello, " + data.getUserName() + "!",
			"--- The Hunter ---",
			"", // a blank line
			"1999/2/13. PM 11:58. Los Angeles. ",
			"When I was 10, my parents were murdered in a robbery.",
			"They were both killed in front me.",
			"From then on, I decided to uphold the justice."
		};
		return intro;
	}
	
	// You could give your own methods useful scene names to help yourself
	// keep track of what parts of your story are about and their order.
	private static String[] chapter1() {
		String[] prose = {
			"I want the absolute justice in this city,",
			"however, the law does not work well sometimes.",
			"I realized that the law is just a toy for powerful people.",
			"Therefore, I decided to find the justice in my own way.",
			"",
			"I stand in court as a lawyer for a living at day,",
			"and fight crime as a hero under a mask at night.",
			"The public call me The Hunter.",
			"And now, is my hunting time.",
			""
		};
		prose = combine(prose, data.describeMonsters());
		return prose;
	}
	
	private static String[] chapter2() {
		String[] prose = {
			"There is an organization that has been doing illegal",
			"business in my city for some time.",
			"The boss is called Kingpin, but I have never seen him before.",
			"I got some news that they would sell",
			"a ton of drugs at a dock tonight,",
			"and I would definitely stop them.",
			"(Night)",
			"I have been waiting for 2 hours at this dock,",	
			"but no one shows up.",	
			"That is a bit strange...",	
			"Wait, this is a trap! Damn!",
			""
		};
		prose = combine(prose, data.describeMonsters());
		return prose;
	}
	
	private static String[] chapter3() {
		String[] prose = {
			"I was almost killed last night.",
			"I hurt bad and asked help to my best friend Jimmy.",
			"He is the only one who knows who exactly I am.",
			"He said that I had to stop ",
			"because that was not my duty.",
			"What I am doing is breaking rules and that makes",
			"me the same with Kingpin.",
			"Maybe he is right.",	
			"Maybe this is the time to say goodbye to The Hunter.",	
			""
		};
		return prose;
	}
	
	private static String[] chapter4() {
		String[] prose = {};
		storyChoice = in.getStroyChoice();
		if (storyChoice == 0) {
			chapter = FINAL_CHAPTER;
			prose = new String[] {
				"You chose to get back to normal life."
			};
		} else {
			prose = new String[] {
				"You chose to fight till death!"
			};
		}
		return prose;
	}
	
	private static String[] chapter5() {
		String[] prose = {
			"I have made my decision.",
			"I will regain peace for this city,",
			"and I would not stop until Kingpin is arrested.",
			"(Night)",
			"I sneaked into Kingpin's house and finally meet him.",
			"He looks very strong and tall.",
			"I have to defeat him to end the chaos.",
			""
		};
		String[] finalMonsterDescription = {
			"The Kinpin",
			"The source of the chaos in this city"
		};
		prose = combine(prose, finalMonsterDescription);
		return prose;
	}
	
	private static String[] chapter6() {
		String[] prose = {
			"I finally beat him down.",
			"However, he made me an offer.",
			"He said that if I let him go, he would leave this city,",
			"and I will never see him again.",
			"Or, he would hurt the people I care about",
			"if I give him to the police",
			"What should I do..."
		};
		return prose;
	}
	
	private static String[] chapter7() {
		String[] prose = {};
		finalChoice = in.getFinalChoice();
		if (finalChoice == 0) {
			prose = new String[] {
				"You chose to let him go."	
			};
		} 
		if (finalChoice == 1) {
			prose = new String[] {
				"You chose to hand him over to the police."
			};
		}
		return prose;
	}
	
	// Two simple and different ways the game can end.
	private static String[] end() {
		String[] ending = {};
		if (data.isPlayerDead()) 
			chapter = FINAL_CHAPTER;
			ending = new String[] { // you are expected to write more...
				"I was so hurt that I finally couldn't stand",
				"and fell to the ground...",
				"The Hunter is gone...",
				"(Game over!)",
				"(Thank you for playing!)"
			};
		
		//ending A
		if (storyChoice == 0)
			ending = new String[] { // you are expected to write more...
				"I thought I could get back to the normal life,",
				"but I was wrong.",
				"The Kingpin found out Jimmy's address,",
				"and curelly murdered my best friend to revenge me.",
				"Then he blamed me on this crime.",
				"Now, I stand in court as a defendent,",
				"and I will be spend the rest of my life in prison.",
				"I could have made a different choice at that time,",
				"and saved Jimmy's life.",
				"That was all my fault...",
				"",
				"(The end!)",
				"(Thanks for playing!)",
				"(This is one of the endings, try other choices!)"
			};
		
		//ending B
		if (finalChoice == 0)
			ending = new String[] {
				"Like Kingpin said,",
				"he left this city,",
				"and never shown up.",
				"I am so tired, and finally can get a breath.",
				"I get back to the normal life,",
				"and have a beautiful girlfriend now.",
				"I don't know how long the peace will last,",
				"but I do know The Hunter is never gone.",
				"He will be back when this city needs him.",
				"",
				"(The end!)",
				"(Thanks for playing!)",
				"(This is one of the endings, try other choices!)"
			};
		
		//ending C
		if (finalChoice == 1)
			ending = new String[] {
				"I finally put Kingpin in jail,",
				"and I really thought this thing is over,",
				"but it is not.",
				"Even though Kingpin is in jail,",
				"he is still powerful in this city.",
				"He was released on bail only after one week,",
				"and curelly murdered my best friend to revenge me.",
				"The blood must be paid by the blood.",
				"I will never stop hunting him till he dies.",
				"",
				"(The end!)",
				"(Thanks for playing!)",
				"(This is one of the endings, try other choices!)"
			};
		return ending;
	}
	
	/**
	 * Checks if the Narrator is ready to end its story.
	 * This helps Game know what to do next. Perhaps you
	 * could check similarly for other chapters?
	 *
	 * @return
	 *   Returns <code>true</code> if the Narrator is currently about 
	 *   to tell the final chapter in the story, and <code>false</code> otherwise.
	 */
	public static boolean isAtFinalChapter() {
		return chapter == FINAL_CHAPTER;
	}
	
	// A helper method to sequence together two string arrays into one array.
	private static String[] combine(String[] first, String[] second) {
		// Copy the first, and make enough extra space for the second.
		String[] combined = Arrays.copyOf(first, first.length + second.length);
		// Copy the second into the extra space.
		System.arraycopy(second, 0, combined, first.length, second.length);
		return combined;
	}
	
	/*
	 * Maybe you could design your own line-breaking method to create arrays of Strings
	 * in a much more efficient way than I have done above? Pass in a String, choose how
	 * to partition it, and return an array of Strings. It would help you design a story
	 * in your code much faster instead of writing chapters of it inside String arrays
	 * like I did above.
	 */
}