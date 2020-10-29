package format;

import graphics.Point2D;

/**
 * A shape of a filled rectangle that can be drawn in its <code>Chars2D buffer</code>.
 * This is also an example of a class that is only used inside its package. Remember, no access
 * modifier means that the code is package-private, so only other classes inside the graphics
 * package can use it.
 * 
 * @author Dr Russell Campbell
 */
class FillBox {
	private Chars2D buffer; // a buffer is a temporary storage to pass data to other places later
	private Point2D topLeft, bottomRight;
	private char stroke;
	
	/**
	 * Create an instance of a rectangle shape that will be filled
	 * with the same character repeated throughout.
	 * 
	 * @param x1 
	 *   Horizontal position of the top-left corner.
	 * @param y1 
	 *   Vertical position of the top-left corner.
	 * @param x2 
	 *   Horizontal position of the bottom-right corner.
	 * @param y2 
	 *   Vertical position of the bottom-right corner.
	 * @param stroke 
	 *   The character used to fill all characters within the rectangle.
	 */
	FillBox(int x1, int y1, int x2, int y2, char stroke) {
		topLeft = new Point2D(x1, y1);
		bottomRight = new Point2D(x2, y2);
		this.stroke = stroke;
	}
	
	/**
	 * Set the <code>buffer</code> to draw inside for later.
	 * 
	 * @param buffer
	 *   An instance of a <code>Chars2D</code> to be used for drawing inside later.
	 */
	void setBuffer(Chars2D buffer) {
		this.buffer = buffer;
	}
	
	/**
	* Draws this filled rectangle shape in its <code>buffer</code>, so that buffer
	* contents can be sent to an output destination later.
	*/
	void draw() {
		// No need to vertically flip a symmetrical design.
		for (int j = bottomRight.y; j <= topLeft.y; j++) {
			for (int i = topLeft.x; i <= bottomRight.x; i++) {
				buffer.set(i, j, stroke);
			}		
		}
	}
}
