package election.modules;

import java.awt.Color;

public class GraphBar {
	
	private Color colour;
	private int height;
	
	/**
	 * Represents one bar of a Vote share or Votes share change graph
	 * @param colour The colour of the bar
	 * @param height The height of the bar in pixels
	 */
	public GraphBar(Color colour, int height) {
		this.colour = colour;
		this.height = height;
	}
	
	public Color getColour() {return colour;}
	public int getHeight() {return height;}

}
