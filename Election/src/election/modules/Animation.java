package election.modules;

import election.main.Display;

public class Animation {
	
	private boolean active;
	private double percDone;
	private double lengthSecs;
	private boolean deactFlag;
	
	/**
	 * Animation of specified length, automatically activates
	 * @param lengthSecs The length the animation lasts in seconds
	 */
	public Animation(double lengthSecs) {
		percDone = 0;
		this.lengthSecs = lengthSecs;
		start();
	}
	
	/**
	 * Animation of specified length, parameter defines if it starts at once
	 * @param lengthSecs The length the animation lasts in seconds
	 * @param active Whether the animation starts immediately
	 */
	public Animation(double lengthSecs, boolean active) {
		percDone = 0;
		this.lengthSecs = lengthSecs;
		this.active = active;
	}
	
	/**
	 * Starts the animation
	 */
	public void start() {
		active = true;
		deactFlag = false;
	}
	
	/**
	 * Stops the animation
	 */
	public void reset() {
		active = false;
		percDone = 0;
		deactFlag = true;
	}
	
	/**
	 * Updates the progress of the animation
	 */
	public void update() {
		percDone += 1.0 / ((double)Display.UPS * lengthSecs);
		if (percDone >= 1) {
			reset();
		}
	}
	
	/**
	 * @return Whether the animation is playing
	 */
	public boolean getActive() {
		return active;
	}
	
	/**
	 * @return A double between 0 and 1 where 0 is not started
	 * and 1 is finished. Represents how far along the animation is
	 */
	public double getPercDone() {
		return percDone;
	}
	
	/**
	 * @return The time taken to do the animation in seconds
	 */
	public double getLengthSecs() {
		return lengthSecs;
	}
	
	/**
	 * @return Whether the animation needs to be deactivated
	 */
	public boolean getDeactFlag() {
		return deactFlag;
	}
	
	/**
	 * Informs objects that it has been deactivated
	 */
	public void deact() {
		deactFlag = false;
	}

}
