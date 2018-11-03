package election.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseTracker implements MouseListener, MouseMotionListener {
	
	private int mouseX;
	private int mouseY;
	private boolean mouseClicked;
	
	public MouseTracker() {
		
	}
	
	public boolean checkForClick() {
		if (mouseClicked) {
			mouseClicked = false;
			return true;
		}else {
			return false;
		}
	}
	
	public int getX() {return mouseX;}
	public int getY() {return mouseY;}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

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

	@Override
	public void mousePressed(MouseEvent e) {
		mouseClicked = true;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
