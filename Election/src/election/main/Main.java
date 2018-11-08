package election.main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {
	
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Election Data Viewer";
	
	/**
	 * Sets up JFrame with main Display element
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame(TITLE);
		f.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		f.setSize(WIDTH, HEIGHT);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Display d = new Display();
		f.add(d);
		f.pack();
		f.setVisible(true);
		
	}

}
