package election.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import election.util.PartyList;
import election.util.Result;

public class Queries {
	
	private static String OS;
	/**
	 * Gets name of the OS running this
	 * @return String of the OS name
	 */
	public static String getOSName() {
		if (OS == null) {
			OS = System.getProperty("os.name");
		}
		return OS;
	}
	
	/**
	 * Checks if the OS starts with 'Windows'
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
	 * @return 'python' on windows, 'python3' on everything else
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
				output.add(new Result(PartyList.parties[Integer.parseInt(splitLine[0])], Integer.parseInt(splitLine[1])));
			}
			p.waitFor();
			p.destroy();
		}catch (IOException | InterruptedException e){
			e.printStackTrace();
		}
		return output;
	}
}
