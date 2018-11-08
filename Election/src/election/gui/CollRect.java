package election.gui;

public class CollRect {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean active;
	
	/**
	 * A collision rectangle that can check if a point is inside it.
	 * This constructor assumes the collision rectangle should be active
	 * by default
	 * @param x X coordinate of the top left of the rectangle
	 * @param y Y coordinate of the top left of the rectangle
	 * @param width Width of the rectangle
	 * @param Height height of the rectangle
	 */
	public CollRect(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.active = true;
	}
	
	/**
	 * A collision rectangle that can check if a point is inside it.
	 * This constructor allows it to be inactive at initialisation
	 * by default
	 * @param x X coordinate of the top left of the rectangle
	 * @param y Y coordinate of the top left of the rectangle
	 * @param width Width of the rectangle
	 * @param Height height of the rectangle
	 * @param active Whether the object is currently checking for collisions
	 */
	public CollRect(int x, int y, int width, int height, boolean active) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.active = active;
	}
	
	/**
	 * Checks if the point (mouseX, mouseY) is inside the object
	 * @param mouseX
	 * @param mouseY
	 * @return Whether the point is inside the object
	 */
	public boolean contains(int mouseX, int mouseY) {
		if (active && mouseX > x && mouseX < (x + width) && mouseY > y && mouseY < (y + height)) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public boolean getActive() {return active;}
	
	public void setActive(boolean b) {
		active = b;
	}

}
