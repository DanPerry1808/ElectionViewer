package election.gui;

public class CollRect {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean active;
	
	public CollRect(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.active = true;
	}
	
	public CollRect(int x, int y, int width, int height, boolean active) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.active = active;
	}
	
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
