package election.modules;

import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import election.db.Queries;
import election.gui.Button;
import election.gui.Element;
import election.gui.ElementList;
import election.main.Main;
import election.util.Majority;
import election.util.PartyList;
import election.util.Result;
import election.util.Swing;

public class ConstitScreen extends Element{
	
	private boolean loaded;
	
	private ElementList el;
	
	private Font f;
	private NameBar nb;
	private ResultsList rl;
	private VoteShareGraph vsg;
	private VoteChangeGraph vcg;
	private Swingometer sw;
	private Button voteShareButton;
	private Button voteChangeButton;
	private Button swingButton;
	private Button back;
	private GraphStateManager gsm;
	
	private String name;
	private int turnout15;
	private int turnout17;
	private double[] topVsc;
	private Majority majority17;
	private ArrayList<VSCResult> vsc;
	private Swing swing;

	public ConstitScreen(int x, int y, int width, int height) {
		super(x, y, width, height);
		el = new ElementList();
		loaded = false;
		
	}
	
	public void loadConstit(int conID) {
		//Get Constituency name
		name = Queries.runScriptStrSingle("get_con_name.py " + Integer.toString(conID));
		// Get votes as a Result[] ordered by votes descending
		ArrayList<Result> res15 = Queries.getResultfromScript("get_votes_15.py " + Integer.toString(conID));
		ArrayList<Result> res17 = Queries.getResultfromScript("get_votes_17.py " + Integer.toString(conID));
		// Calc turnout for both years
		turnout15 = sumVotes(res15);
		turnout17 = sumVotes(res17);
		//Calc voteshare change
		// 2 space array for top 2 parties' voteshare changes
		topVsc = new double[2];
		//For each party in res17, find it in res 15 and calc the VSCResult
		vsc = new ArrayList<VSCResult>();
		for (int i = 0; i < res17.size(); i++) {
			int i15 = Result.find(res15, res17.get(i).getParty().getName());
			vsc.add(new VSCResult(res17.get(i).getParty(), res15.get(i15).toPerc(turnout15), res17.get(i).toPerc(turnout17)));
			if (i < topVsc.length) {
				topVsc[i] = vsc.get(i).getPercChange();
			}
		}
		res15.add(new Result(PartyList.parties[0], turnout15));
		res17.add(new Result(PartyList.parties[0], turnout17));
		//Calc swing
		swing = Swing.createSwing(res17.get(0).getParty(), topVsc[0], res17.get(1).getParty(), topVsc[1]);
		// Calc majority
		majority17 = new Majority(res17.get(0).getParty(), res17.get(0).getVotes() - res17.get(1).getVotes());
		//Start of module init
		nb = new NameBar(0, 0, Main.WIDTH, 100, res17.get(0).getParty(), res15.get(0).getParty(), majority17);
		nb.setName(name);
		rl = new ResultsList(0, 120, 400, 400, res17);
		f = new Font("Helvetica", Font.PLAIN, 20);
		voteShareButton = new Button(420, 120, 140, 50, "Vote Share");
		voteShareButton.setFont(f);
		voteChangeButton = new Button(420, 220, 140, 50, "Vote Change");
		voteChangeButton.setFont(f);
		swingButton = new Button(420, 320, 140, 50, "Swingometer");
		swingButton.setFont(f);
		
		// I have to clone the ArrayList so that the VoteShareGraph does not modify the original
		ArrayList<Result> clone = (ArrayList<Result>) res17.clone();
		vsg = new VoteShareGraph(580, 120, clone);
		vcg = new VoteChangeGraph(580, 120, vsc);
		sw = new Swingometer(580, 120, swing);
		back = new Button(10, 600, 70, 40, "Back");
		back.setFont(f);
		el.add(nb);
		el.add(rl);
		el.add(voteShareButton);
		el.add(voteChangeButton);
		el.add(swingButton);
		el.add(vsg);
		el.add(vcg);
		el.add(sw);
		el.add(back);
		gsm = new GraphStateManager(GraphState.SHARE);
		loaded = true;
	}
	
	/**
	 * Sets all variables to null and removes all elements from the ElementList
	 * Needed to load the next constituency in
	 */
	public void unloadConstit() {
		name = null;
		turnout15 = 0;
		turnout17 = 0;
		majority17 = null;
		nb = null;
		rl = null;
		vsg = null;
		el.deleteList();
		loaded = false;
	}
	
	/**
	 * Gets the total number of votes for a constituency
	 * @param res An ArrayList of results that are added up
	 * @return The sum of all getVote attributes of the Results ArrayList
	 */
	private int sumVotes(ArrayList<Result> res) {
		int sum = 0;
		for(int i = 0; i < res.size(); i++) {
			sum += res.get(i).getVotes();
		}
		return sum;
	}
	
	public void draw(Graphics2D g) {
		if(loaded) {
			el.draw(g);
		}else {
			g.drawString("Loading...", 10, 10);
		}
	}
	
	public Button getBack() {return back;}
	public Button getVoteShareButton() {return voteShareButton;}
	public Button getVoteChangeButton() {return voteChangeButton;}
	public Button getSwingButton() {return swingButton;}
	
	public GraphStateManager getGSM() {return gsm;}
	
	public void update(int xCoord, int yCoord) {
		el.update(xCoord, yCoord);
		if (gsm.getToEnable()) {
			for (int i = 0; i < gsm.getEnable().length; i++) {
				if (gsm.getEnable()[i]) {
					gsm.enable(GraphState.values()[i]);
					if(i == 0) {
						vsg.setVisible(true);
					}else if (i == 1) {
						vcg.setVisible(true);
					} else if (i == 2) {
						sw.setVisible(true);
					}
				}
			}
			gsm.hasEnabled();
		}
		
		if (gsm.getToDisable()) {
			for (int i = 0; i < gsm.getDisable().length; i++) {
				if (gsm.getDisable()[i]) {
					gsm.disable(GraphState.values()[i]);
					if (i == 0) {
						vsg.setVisible(false);
					}else if (i == 1) {
						vcg.setVisible(false);
					}else if (i == 2) {
						sw.setVisible(false);
					}
				}
			}
			gsm.hasDisabled();
		}
	}
	
	public void onHover(int xCoord, int yCoord) {
		el.onHover(xCoord, yCoord);
	}
	
	public void onClick(int xCoord, int yCoord) {
		el.onClick(xCoord, yCoord);
	}
}
