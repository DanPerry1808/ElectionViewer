package election.main;

public class StateManager {
	
	private State currState;
	
	public StateManager(State defState) {
		currState = defState;
	}
	
	public State getCurrState() {return currState;}
	
	public void changeState(State newState) {
		currState = newState;
	}

}
