package election.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseTracker implements MouseListener, MouseMotionListener {
	
	private int mouseX;
	private int mouseY;
	private boolean mouseClicked;
	
	/**
	 * MouseTracker keeps track of the position of the mouse
	 * and whether it has been clicked
	 */
	public MouseTracker() {
		
	}
	
	/**
	 * If there has been a click registered, this returns true then sets
	 * mouseClicked to false so this object knows the click has been handled
	 * @return True if mouse click has happened, false if not
	 */
	public boolean checkForClick() {
		if (mouseClicked) {
			mouseClicked = false;
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * @return X coordinate of mouse pointer relative to top left of screen
	 */
	public int getX() {return mouseX;}
	
	/**
	 * @return Y coordinate of mouse pointer relative to top left of screen
	 */
	public int getY() {return mouseY;}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * Whenever the mouse is moved, the variables storing the mouse coords
	 *  are changed to the current mouse coords
	 * @param e The event generated when the mouse is moved
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * When the mouse is pressed, the method sets a private variable to true
	 *  which is later checked against to see if any clicks have occurred since
	 *  the last time the MouseListener was checked
	 * @param e The event generated when the mouse was clicked
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		mouseClicked = true;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
