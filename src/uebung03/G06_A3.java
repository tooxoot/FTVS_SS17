package uebung03;

import static SoFTlib.Helper.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import SoFTlib.*;

// A
class Replicator extends Node {
	public String runNode(String input) throws SoFTException {
		form('a', "2017").send("BCDEF");
		return "DONE";
	}
}

// BCDEF
class Calculator extends Node {

	public String runNode(String input) throws SoFTException {
		Msg receivedInput = receive("A", 'a', time() + 50);
		int content = number(receivedInput.getCo()) + 1;
		try { sleep(25); } catch (Exception e) {}
		form('e', "" + content).send('G');
		form('e', "" + content).send('H');

		return "DONE";
	}
}

class Masker extends Node {
	private int a;
	private int b;
	private int receivedMessages;
	// Should hold the classes key (can be calculated by
	// calculateEquivalentClass(...) ) and its cardinal number
	private ArrayList<equivalentClass> classes;
	
	public String runNode(String input) throws SoFTException {
		try {
			a = number(input.split(" ")[0]);
			b = number(input.split(" ")[1]);
		} catch (Exception e) {
			a = 1;
			b = 1;
		}
		classes = new ArrayList<>();
		
		receivedMessages = 0;
		say("Etered a = " + a);
		say("Etered b = " + b);

		long timeout = time() + 100;
		while (time() < timeout && receivedMessages < 5) {
			Msg receivedMessage = receive("BCDEF", timeout);
			//String key = calculateEquivalentClass(receivedMessage.getCo());
			if (receivedMessage != null) {
				receivedMessages++;
				if ( add(receivedMessage.getCo()) >= b) break;
			}
		}
		String prettifiedClasses = getPrettifiedClasses();
		say(prettifiedClasses);
		
		String decision = getDecision();
		// TODO implement decision
		return decision;
	}
	
	public int add(final String value){
		for(equivalentClass c : classes){
			if(c.equals(value)){ 
				c.values.add(value);
				return c.values.size();
			}
		};
		classes.add(new equivalentClass(value));
		return 1;
	}
		
	public String getPrettifiedClasses() {
		String output = "}\n";
		for(equivalentClass c : classes){
			output += "\t\t\t\t'" + c.key + "' : " + c.values.size() + " : [ ";
			for(String value : c.values) output += value + " ";
			output += "]\n";
		}
		output += "\t\t\t\t}\n";
		return output;
	}
	
	private String calculateKey(String input) {
		return number(input) + "";
	}

	
	public String getDecision(){
		int maxValueCount = 0;
		for(equivalentClass c : classes){ if(c.values.size() > maxValueCount) maxValueCount = c.values.size(); }
		int minCardinalNumber = Math.max(a, maxValueCount);

		ArrayList<String> overleftValues = new ArrayList<>();
		ArrayList<Integer> overleftValuesAsInt = new ArrayList<>();
		
		for(equivalentClass c : classes){ 
			if(c.values.size() >= minCardinalNumber){
				overleftValues.addAll(c.values);
				overleftValuesAsInt.addAll(c.valuesAsInt);
			}
		}
		overleftValuesAsInt.sort(null);

		String output = "[ ";
		for(Integer i : overleftValuesAsInt) output += i + " ";
		output += "]";
		say(output);
		
		if(overleftValuesAsInt.size() > 0)
			if(overleftValuesAsInt.size() % 2 != 0) return "" + overleftValuesAsInt.get( overleftValuesAsInt.size() / 2 );
			else {
				int lower = overleftValuesAsInt.get( Math.min( overleftValues.size() / 2 - 1 , 0) );
				int higher= overleftValuesAsInt.get( Math.min( overleftValues.size() / 2  , 0));
				return "" + Math.min(  lower , higher);
			}
		return "";
	}
	
	private class equivalentClass { 
		String key;
		ArrayList<String> values = new ArrayList<>();
		ArrayList<Integer> valuesAsInt = new ArrayList<>();
		
		public equivalentClass(String value){
			key = calculateKey(value);
			values.add(value);
			valuesAsInt.add(number(value));
		}	
	
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof String){
				return this.key.equals(calculateKey((String)obj));
			} else if ( obj instanceof equivalentClass){
				return this.key.equals( ((equivalentClass) obj).key);
			}
			else return super.equals(obj);
		}
		
	}
	
}

public class G06_A3 extends SoFT {

	public int result(String input, String[] output) {
		
		if (output[6].equals("2018") && output[7].equals("2018"))
			return 0;
		else if (output[6].equals("2018") || output[7].equals("2018"))
			return 1;
		else if (output[6].equals(output[7]))
			return 2;
		else
			return 3;

	}

	public static void main(String[] Aufrufparameter) {
		new G06_A3()
				.runSystem(
						new Node[] {
								new Replicator(), 
								new Calculator(), 
								new Calculator(), 
								new Calculator(),
								new Calculator(),
								new Calculator(), 
								new Masker(), 
								new Masker()
								},
						"sxx_02b",
						"",
						"Sven und Justin");
	}
}
