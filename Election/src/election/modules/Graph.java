package election.modules;

import java.awt.Font;

import election.gui.Element;

public class Graph extends Element {
	
	protected GraphBar[] bars;
	
	protected Font f;
	
	protected final int X_AXIS_LENGTH = 550;
	protected final int Y_AXIS_LENGTH = 500;
	
	// Space between bars
	protected final int BAR_PADDING = 30;
	protected final int BAR_WIDTH = 80;
	
	/**
	 * Class for VoteShareGraoh and VoteChangeGraph to extend.
	 * Contains common variables such as an array of GraphBars and
	 * pixels measurements for the axis
	 * @param x
	 * @param y
	 */
	public Graph(int x, int y) {
		super(x, y, 620, 600);
		f = new Font("Helvetica", Font.PLAIN, 14);
	}

}
