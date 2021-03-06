package election.db;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import election.util.PartyClass;
import election.util.PartyList;
import election.util.Result;

public class Queries {
	
	private static String OS;
	/**
	 * Gets name of the OS running this program
	 * @return String of the OS name
	 */
	public static String getOSName() {
		if (OS == null) {
			OS = System.getProperty("os.name");
		}
		return OS;
	}
	
	/**
	 * @return Whether OS name starts with Windows
	 */
	public static boolean isWindows() {
		if (getOSName().startsWith("Windows")) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Checks what command should be sent to the terminal to call python depending on OS
	 * @return 'py' on Windows, 'python3' on everything else
	 */
	public static String getPythonVersion() {
		if (isWindows()) {
			return "py";
		}else {
			return "python3";
		}
	}
	
	/**
	 * Runs a terminal command to run a python script to contact the DB and run a query
	 * @param pyFile The python file to run, all python scripts are found in /python
	 * @return An ArrayList of Strings from the DB query output
	 */
	public static ArrayList<String> runScriptStr(String pyFile) {
		ArrayList<String> output = new ArrayList<String>();
		String s;
        Process p;
        try {
            p = Runtime.getRuntime().exec(getPythonVersion() + " python/" + pyFile);
            BufferedReader br = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null) {
                output.add(s);
            }
            p.waitFor();
            p.destroy();
        } catch (IOException | InterruptedException e) {
        	e.printStackTrace();
        }
        return output;
	}
	
	/**
	 * Runs a terminal command to run a python script to contact the DB and run a query
	 * @param pyFile The python file to run, all python scripts are found in /python
	 * @return A String from the DB query output
	 */
	public static String runScriptStrSingle(String pyFile) {
		String output = "";
		String s;
		Process p;
		try {
			p = Runtime.getRuntime().exec(getPythonVersion() + " python/" + pyFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			if ((s = br.readLine()) != null){
				output = s;
			}
			p.waitFor();
			p.destroy();
		}catch (IOException | InterruptedException e){
			e.printStackTrace();
		}
		return output;
	}
	
	/**
	 * Runs a terminal command to run a python script to contact the DB and run a query
	 * @param pyFile The python file to run, all python scripts are found in /python
	 * @return An ArrayList of Results from the DB query output
	 */
	public static ArrayList<Result> getResultfromScript(String pyFile) {
		ArrayList<Result> output = new ArrayList<Result>();
		String s;
		String[] splitLine;
		Process p;
		try {
			p = Runtime.getRuntime().exec(getPythonVersion() + " python/" + pyFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while((s = br.readLine()) != null) {
				splitLine = s.split(" ");
				output.add(new Result(PartyList.getParties().get(Integer.parseInt(splitLine[0])), Integer.parseInt(splitLine[1])));
			}
			p.waitFor();
			p.destroy();
		}catch (IOException | InterruptedException e){
			e.printStackTrace();
		}
		return output;
	}
	
	public static ArrayList<PartyClass> getParties(String pyFile) {
		ArrayList<PartyClass> output = new ArrayList<PartyClass>();
		output.add(new PartyClass("Total", "TOT", new Color(255, 255, 255)));
		String s;
		String[] splitLine;
		Process p;
		try {
			p = Runtime.getRuntime().exec(getPythonVersion() + " python/" + pyFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((s = br.readLine()) != null) {
				splitLine = s.split("/");
				Color c = new Color(Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[3]), Integer.parseInt(splitLine[4]));
				output.add(new PartyClass(splitLine[0], splitLine[1], c));
			}
			p.waitFor();
			p.destroy();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		return output;
	}
}
