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
	
	/**
	 * @return Whether a component needs to be enabled
	 */
	public boolean getToEnable() {return toEnable;}
	
	/**
	 * @return Whether a component needs to be disabled
	 */
	public boolean getToDisable() {return toDisable;}
	
	/**
	 * @return Array of which elements need enabling.
	 * If an element's corresponding entry in the array returns true it needs
	 * to be enabled.
	 * 0 = SHARE
	 * 1 = CHANGE
	 * 2 = SWING
	 */
	public boolean[] getEnable() {return enable;}
	
	/**
	 * @return Array of which elements need disabling.
	 * If an element's corresponding entry in the array returns true it needs
	 * to be disabled.
	 * 0 = SHARE
	 * 1 = CHANGE
	 * 2 = SWING
	 */
	public boolean[] getDisable() {return disable;}
	
	/**
	 * Call once all components that need enabling have been enabled
	 */
	public void hasEnabled() {
		toEnable = false;
	}
	
	/**
	 * Call once all components that need disabling have been disabled
	 */
	public void hasDisabled() {
		toDisable = false;
	}
	
	/**
	 * Call immediately after a component has been enabled
	 * @param gs The corresponding GraphState to the graph just enabled
	 */
	public void enable(GraphState gs) {
		enable[gs.getValue()] = false;
	}
	
	/**
	 * Call immediately after a component has been disabled
	 * @param gs The corresponding GraphState to the graph just disabled
	 */
	public void disable(GraphState gs) {
		disable[gs.getValue()] = false;
	}
	
	
}
