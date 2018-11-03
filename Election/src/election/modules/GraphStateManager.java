package election.modules;

public class GraphStateManager {
	
	private GraphState currState;
	private boolean toDisable;
	private boolean[] disable;
	private boolean toEnable;
	private boolean[] enable;
	
	public GraphStateManager(GraphState defState) {
		currState = defState;
		toDisable = false;
		/* When true, the corresponding Element needs to be disabled
		 * 0 is SHARE
		 * 1 is CHANGE
		 * 2 is SWING
		 */
		disable = new boolean[] {false, false, false};
		toEnable = false;
		/* When true, the corresponding Element needs to be enabled
		 * 0 is SHARE
		 * 1 is CHANGE
		 * 2 is SWING
		 */
		enable = new boolean[] {false, false, false};
	}
	
	public GraphState getState() {return currState;}
	
	public void changeState(GraphState s) {
		GraphState oldState = currState;
		currState = s;
		if (oldState == currState) {
			return;
		}else {
			switch (oldState) {
			case SHARE:
				toDisable = true;
				disable[0] = true;
				break;
			case CHANGE:
				toDisable = true;
				disable[1] = true;
				break;
			case SWING:
				toDisable = true;
				disable[2] = true;
				break;
			}
			
			switch(currState) {
			case SHARE:
				toEnable = true;
				enable[0] = true;
				break;
			case CHANGE:
				toEnable = true;
				enable[1] = true;
				break;
			case SWING:
				toEnable = true;
				enable[2] = true;
				break;
			}
		}
	}
	
	public boolean getToEnable() {return toEnable;}
	public boolean getToDisable() {return toDisable;}
	public boolean[] getEnable() {return enable;}
	public boolean[] getDisable() {return disable;}
	
	public void hasEnabled() {
		toEnable = false;
	}
	
	public void hasDisabled() {
		toDisable = false;
	}
	
	public void enable(GraphState gs) {
		enable[gs.getValue()] = false;
	}
	
	public void disable(GraphState gs) {
		disable[gs.getValue()] = false;
	}
	
	
}
