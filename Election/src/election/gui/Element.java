package election.gui;

import java.awt.Graphics2D;

public class Element {
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected CollRect rect;
	protected boolean visible = true;
	
	/**
	 * Base class for all GUI elements
	 * @param x X coordinate to draw element at
	 * @param y Y coordinate to draw element at
	 * @param width Width of element
	 * @param height Height of element
	 */
	public Element(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rect = new CollRect(x, y, width, height);
	}
	
	// The next 4 methods are just there to be inherited from
	
	public void update(int x, int y) {
		
	}
	
	public void onHover(int x, int y) {
		
	}
	
	public void onClick(int x, int y) {
	}
	
	public void draw(Graphics2D g) {
		
	}
	
	/**
	 * Checks if the coordinates (mouseX, mouseY) form a point inside the
	 * element's collison rectangle
	 * @param cr
	 * @param mouseX
	 * @param mouseY
	 * @return Whether the two coordinates are inside the CollRect
	 */
	public boolean isInRect(CollRect cr, int mouseX, int mouseY) {
		if (cr.contains(mouseX, mouseY)) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * @return whether the element is currently being drawn to the screen
	 */
	public boolean getVisible() {return visible;}
	
	public void setVisible(boolean b) {
		visible = b;
	}

}
