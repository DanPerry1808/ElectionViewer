package election.modules;

import java.awt.Font;

import election.gui.Element;

public class Graph extends Element {
	
	protected GraphBar[] bars;
	
	protected Font f;
	
	protected final int X_AXIS_LENGTH = 500;
	protected final int Y_AXIS_LENGTH = 500;
	protected final int BAR_PADDING = 30;
	protected final int BAR_WIDTH = 80;
	
	public Graph(int x, int y) {
		super(x, y, 620, 600);
		f = new Font("Helvetica", Font.PLAIN, 14);
	}

}
