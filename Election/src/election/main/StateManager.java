package election.main;

public class StateManager {
	
	private State currState;
	
	/**
	 * Used to check whether the program is on the main menu or the
	 * ConstitViewer screen using the State enum
	 * @param defState The default state the object is in at initialisation
	 */
	public StateManager(State defState) {
		currState = defState;
	}
	
	/**
	 * @return The current State enum the object has stored
	 * Represents whether the program is in main menu or
	 * ConstitViewer screen
	 */
	public State getCurrState() {return currState;}
	
	/**
	 * Sets the State enum to the state given
	 * @param newState The State enum to change to
	 */
	public void changeState(State newState) {
		currState = newState;
	}

}
