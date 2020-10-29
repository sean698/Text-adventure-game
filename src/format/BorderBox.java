package format;

import graphics.Point2D;

/**
 * A shape of a rectangle that can be drawn in its <code>Chars2D buffer</code>.
 * 
 * @author Dr Russell Campbell
 */
class BorderBox {
	private static Chars2D buffer;
	
	private Point2D topLeft, bottomRight; // only points needed to define a rectangle
	private char stroke; // the character used to draw the rectangle
	
	/**
	 * Create an instance of a rectangle shape to draw a border with a repeated character.
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
	 *   The character used to draw the rectangle.
	 */
	public BorderBox(int x1, int y1, int x2, int y2, char stroke) {
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
	public void setBuffer(Chars2D buffer) {
		this.buffer = buffer;
	}
	
	/**
	 * Translates (moves) the two corner points of this box. Not used for animation,
	 * just to redraw a rectangle in a slightly different position later.
	 * 
	 * @param dx1
	 *   The horizontal amount of translation to the top left corner.
	 * 
	 * @param dy1
	 *   The vertical amount of translation to the top left corner.
	 *   
	 * @param dx2
	 *   The horizontal amount of translation to the bottom right corner.
	 * 
	 * @param dy2
	 *   The vertical amount of translation to the bottom right corner.
	 */
	void shiftCorners(int dx1, int dy1, int dx2, int dy2) {
		topLeft.translate(dx1, dy1);
		bottomRight.translate(dx2, dy2);
	}
	
	/**
	 * Draws this rectangle shape in its <code>buffer</code>.
	 */
	void draw() {
		Point2D topRight = new Point2D(bottomRight.x, topLeft.y);
		Point2D bottomLeft = new Point2D(topLeft.x, bottomRight.y);
		
		buffer.drawLine(topLeft, topRight, stroke);
		buffer.drawLine(topRight, bottomRight, stroke);
		buffer.drawLine(bottomRight, bottomLeft, stroke);
		buffer.drawLine(bottomLeft, topLeft, stroke);
	}
	
}
