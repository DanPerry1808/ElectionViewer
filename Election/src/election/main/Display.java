package election.main;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferStrategy;

import election.db.Queries;
import election.gui.Button;
import election.gui.Dropdown;
import election.gui.ElementList;
import election.modules.ConstitScreen;
import election.modules.GraphState;

public class Display extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	// Number of update calls the program makes every second
	public static final int UPS = 60;
	
	private Thread thread;
	private boolean running = false;
	
	private StateManager sm;
	private MouseTracker mt;
	
	// An ElementList is used here to make calling common methods between
	// GUI Elements easier
	private ElementList menuElements;
	
	// GUI elements used in the main menu
	private Button b;
	private Dropdown conList;
	
	private Font f;
	
	// Constit stuff
	private ConstitScreen cs;
	
	/**
	 * Main class that deals with input and drawing to screen
	 * Contains a main menu and the constituency screen
	 * which it can switch between
	 */
	public Display() {
		
		// Reads in an array of constituency names from the DB
		// for the drop down list
		String[] constits = {};
		constits = Queries.runScriptStr("read_con_names.py").toArray(constits);
		
		setFocusable(true);
		requestFocus(true);
		
		// Creates StateManager to manage transition between this screen and
		// the constituency viewer screen
		sm = new StateManager(State.MENU);
		
		mt = new MouseTracker();
		addMouseListener(mt);
		addMouseMotionListener(mt);
		
		menuElements = new ElementList();
		
		// GUI elements, button and drop down list inititalised
		b = new Button(500, 100, 100, 60, "Open");
		conList = new Dropdown(100, 100, 300, 60, 50, constits);
		
		f = new Font("Helvetica", Font.PLAIN, 24);
		
		// Adds GUI elements to the list of elements
		menuElements.add(b);
		menuElements.add(conList);
		
		cs = new ConstitScreen(0, 0, Main.WIDTH, Main.HEIGHT);
		
		start();
	}
	
	/**
	 * Initialises thread object and starts game loop
	 */
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Election");
		thread.start();
	}
	
	/**
	 * Shuts down the program
	 */
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Called when the Thread object is started.
	 * Contains the main game loop
	 */
	public void run() {
		
		// Sets up timing variables to ensure program updates uniformly
		long lastTime = System.nanoTime();
		final double UPDATE_TIME = 1000000000.0 / (double)UPS;
		double delta = 0;
		
		// Running is true when the program is still active
		while (running) {
			
			long now = System.nanoTime();
			delta += (now - lastTime) / UPDATE_TIME;
			lastTime = now;
			
			// If due for update, run update methods
			while (delta >= 1) {
				
				// If StateManager is currently in Menu mode, run menu updates
				if(sm.getCurrState() == State.MENU) {
					menuElements.update(mt.getX(), mt.getY());
					//onHover for when the mouse hovers over stuff
					menuElements.onHover(mt.getX(), mt.getY());
					// If there is a click event that needs checking
					if (mt.checkForClick()) {
						menuElements.onClick(mt.getX(), mt.getY());
						// If open button pressed
						if (b.getPressed()) {
							
							// Change state to Constituency viewer screen
							sm.changeState(State.CONSTIT);
							// conID must have 1 added to it as conID is sent
							// to the DB where IDs start from 1, not 0 like in
							// the drop down list
							int conID = conList.getCurrentOptionIndex() + 1;
							cs.loadConstit(conID);
						}
					}
					
				// If in the constituency viewer screen
				}else if(sm.getCurrState() == State.CONSTIT) {
					cs.update(mt.getX(), mt.getY());
					cs.onHover(mt.getX(), mt.getY());
					if (mt.checkForClick()) {
						cs.onClick(mt.getX(), mt.getY());
						
						// Checks if back button has been pressed
						if(cs.getBack().getPressed()) {
							// Change state back to menu and unload all
							// variables inside the ConstitViewer so it can be
							// used again
							sm.changeState(State.MENU);
							cs.unloadConstit();
						}
						
						// Check each graph button on ConstitViewer has been pressed
						if (cs.getVoteShareButton().getPressed()) {
							cs.getGSM().changeState(GraphState.SHARE);
						}
						if (cs.getVoteChangeButton().getPressed()) {
							cs.getGSM().changeState(GraphState.CHANGE);
						}
						if (cs.getSwingButton().getPressed()) {
							cs.getGSM().changeState(GraphState.SWING);
						}
					}
				}
				// Decrease delta shows update has been performed
				delta--;
			}
			
			// Makes sure window is ready to draw to before drawing
			while (!isDisplayable()) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			// Calling main drawing method
			render();
		}
	}
	
	/**
	 * This is the main drawing method
	 */
	public void render() {
		
		// Generating buffer strategy to draw to
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics2D g = (Graphics2D)bs.getDrawGraphics();
		
		// Fills the entire screen with white to draw over last frame
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		g.setColor(Color.BLACK);
		
		// If in menu state, draw menu
		if (sm.getCurrState() == State.MENU) {
			
			// Stores current font in tempFont so I can reset it to that later
			Font tempFont = g.getFont();
			g.setFont(f);
			//Draw text
			g.drawString("Please select a constituency", 50, 50);
			//Draw elements
			menuElements.draw(g);
			//Draw border
			int borderWidth = 10;
			Stroke tempStroke = g.getStroke();
			g.setStroke(new BasicStroke(borderWidth));
			g.drawRect(borderWidth, borderWidth, Main.WIDTH - (borderWidth * 2), Main.HEIGHT - (borderWidth * 4));
			g.setStroke(tempStroke);
			g.setFont(tempFont);
			
		// If in ConstitViewer, show it instead
		}else if(sm.getCurrState() == State.CONSTIT) {
			cs.draw(g);
		}
		
		// Show the current frame
		g.dispose();
		bs.show();
	}

}
