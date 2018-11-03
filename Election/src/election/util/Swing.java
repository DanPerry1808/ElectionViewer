package election.util;

public class Swing {
	
	private PartyClass from;
	private PartyClass to;
	private double amount;
	
	/**
	 * Object to hold the swing between two parties
	 * @param from The PartyClass object the swing is moving away from
	 * @param to The PartyClass object the swing is moving towards
	 * @param amount The percentage point magnitude of the swing
	 */
	public Swing(PartyClass from, PartyClass to, double amount) {
		this.from = from;
		this.to = to;
		this.amount = amount;
	}
	
	public PartyClass getFrom() {return from;}
	public PartyClass getTo() {return to;}
	public double getAmount() {return amount;}
	
	/**
	 * Calculates the swing between the two parties
	 * @param p1 The 2017 first place party
	 * @param perc1 Party 1's vote share change
	 * @param p2 The 2017 second place party
	 * @param perc2 Party 2's vote share change
	 * @return A swing object representing the change between the parties
	 */
	public static Swing createSwing(PartyClass p1, double perc1, PartyClass p2, double perc2) {
		if (p1.getName() == p2.getName()) {
			System.out.println("Same party!!!");
		}
		double amount = (perc1 - perc2) / 2.0;
		if (amount < 0) {
			return new Swing(p1, p2, Math.abs(amount));
		}else {
			return new Swing(p2, p1, Math.abs(amount));
		}
		
	}

}
