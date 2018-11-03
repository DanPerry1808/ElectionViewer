package election.modules;

import java.awt.Color;

public class GraphBar {
	
	private Color colour;
	private int height;
	
	public GraphBar(Color colour, int height) {
		this.colour = colour;
		this.height = height;
	}
	
	public Color getColour() {return colour;}
	public int getHeight() {return height;}

}
