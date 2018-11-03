package election.modules;

public enum GraphState {
	SHARE(0),
	CHANGE(1),
	SWING(2);
	
	private int value;
	private GraphState(int state) {
		value = state;
	}
	
	public int getValue() {
		return value;
	}
}
