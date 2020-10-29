package graphics;

import gui.Panel2D;

/**
 * This interface allows us to collect things that we can draw in the
 * GUI animated display panel, and then draw them without worrying
 * about what data type they actually are.
 */
public interface Drawable {
	
	// All methods are automatically public AND abstact in an interface.
	
	/**
	 * Read the descriptions in the classes that implement the Drawable interface.
	 * This method should be used to make Drawables know which Panel2D intsance
	 * that they can draw inside later.
	 *
	 * @param parentPanel
	 *   A reference to a Panel2D intsance so that this Drawable can use it to draw
	 *   inside later.
	 */
	void setParent(Panel2D parentPanel);
	
	/**
	 * Read the descriptions in the classes that implement the Drawable interface.
	 * This method should be used by a Drawable's <code>parentPanel</code> to display
	 * and animate a Drawable.
	 */
	void draw();
	
}