package election.gui;

import java.awt.Graphics2D;

public class Element {
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected CollRect rect;
	protected boolean visible = true;
	
	public Element(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rect = new CollRect(x, y, width, height);
	}
	
	public void update(int x, int y) {
		
	}
	
	public void onHover(int x, int y) {
		
	}
	
	public void onClick(int x, int y) {
	}
	
	public void draw(Graphics2D g) {
		
	}
	
	public boolean isInRect(CollRect cr, int mouseX, int mouseY) {
		if (cr.contains(mouseX, mouseY)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean getVisible() {return visible;}
	
	public void setVisible(boolean b) {
		visible = b;
	}

}
