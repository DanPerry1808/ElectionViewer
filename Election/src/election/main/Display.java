package election.main;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
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
	private Thread thread;
	private boolean running = false;
	
	private StateManager sm;
	private MouseTracker mt;
	
	// Menu stuff
	private ElementList menuElements;
	
	private Button b;
	private Dropdown conList;
	
	private FontMetrics fm;
	private int fHeight;
	private Font f;
	
	// Constit stuff
	private ConstitScreen cs;
	
	public Display() {
		
		String[] constits = {};
		constits = Queries.runScriptStr("read_con_names.py").toArray(constits);
		
		setFocusable(true);
		requestFocus(true);
		
		sm = new StateManager(State.MENU);
		
		mt = new MouseTracker();
		addMouseListener(mt);
		addMouseMotionListener(mt);
		
		menuElements = new ElementList();
		
		b = new Button(500, 100, 100, 60, "Open");
		conList = new Dropdown(100, 100, 300, 60, 50, constits);
		f = new Font("Helvetica", Font.PLAIN, 24);
		
		menuElements.add(b);
		menuElements.add(conList);
		
		cs = new ConstitScreen(0, 0, Main.WIDTH, Main.HEIGHT);
		
		start();
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Election");
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		final double UPS = 60.0;
		final double UPDATE_TIME = 1000000000.0 / UPS;
		double delta = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / UPDATE_TIME;
			lastTime = now;
			
			while (delta >= 1) {
				
				// Menu input
				if(sm.getCurrState() == State.MENU) {
					// Runs update, check if it does anything
					menuElements.update(mt.getX(), mt.getY());
					//onHover for when the mouse hovers over stuff
					menuElements.onHover(mt.getX(), mt.getY());
					// If there is a click event that needs checking
					if (mt.checkForClick()) {
						menuElements.onClick(mt.getX(), mt.getY());
						// If open button pressed
						if (b.getPressed()) {
							
							sm.changeState(State.CONSTIT);
							int conID = conList.getCurrentOptionIndex() + 1;
							cs.loadConstit(conID);
						}
					}
					
				}else if(sm.getCurrState() == State.CONSTIT) {
					cs.update(mt.getX(), mt.getY());
					cs.onHover(mt.getX(), mt.getY());
					if (mt.checkForClick()) {
						cs.onClick(mt.getX(), mt.getY());
						
						if(cs.getBack().getPressed()) {
							sm.changeState(State.MENU);
							cs.unloadConstit();
						}
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
				delta--;
			}
			
			while (!isDisplayable()) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			render();
		}
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics2D g = (Graphics2D)bs.getDrawGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		g.setColor(Color.BLACK);
		
		if (sm.getCurrState() == State.MENU) {
			
			if (fm == null) {
				fm = g.getFontMetrics(f);
				fHeight = fm.getHeight();
			}
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
			g.drawRect(0, 0, Main.WIDTH - borderWidth, Main.HEIGHT - borderWidth);
			g.setStroke(tempStroke);
			g.setFont(tempFont);
		}else if(sm.getCurrState() == State.CONSTIT) {
			cs.draw(g);
		}
		
		g.dispose();
		bs.show();
	}

}
