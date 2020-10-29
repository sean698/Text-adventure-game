package graphics;

import gui.Panel2D;

/**
 * The superclass that designs how shapes in the animated panel of the GUI
 * will be set up. All shapes need to store their pixel and colour data.
 * They also need to know where to draw themselves in a Panel2D instance.
 *
 * @author Dr Russell Campbell
 */
abstract class Shape implements Drawable {
	// ALL shapes in this code are designed to draw in the same Panel2D.
	// NOTE: this is a simplification and not a good code design choice.
	protected static Panel2D parentPanel;
	protected int pixelsIndex, colorsIndex; // all children must have these
	
	/*
	 * This will help write shorter constructors for subclasses with use of super() constructor.
	 *
	 * @param pixelsIndex
	 *   The index of the character to draw with for the Panel2D.PIXELS array.
	 *
	 * @param colorsIndex
	 *   The index of the colour to draw with for the Panel2D.COLORS array.
	 */
	Shape(int pixelsIndex, int colorsIndex) {
		this.pixelsIndex = pixelsIndex;
		this.colorsIndex = colorsIndex;
	}
	
	/**
	 * Set the <code>parentPanel</code> to draw inside for later. Note ALL subclasses
	 * now act as if this method is written inside them, without having to repeat typing it.
	 * 
	 * @param parentPanel
	 *   An instance of a <code>Panel2D</code> to be used for drawing inside later.
	 */
	@Override
	public void setParent(Panel2D parentPanel) {
		this.parentPanel = parentPanel;
	}
	
	// NOTE: the Drawable abstract method "draw" is implemented differently in each subclass.
}
