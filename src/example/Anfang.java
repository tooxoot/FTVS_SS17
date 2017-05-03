package example;

/**
 * @author Thorsten Kimmeskamp
 *
 * Konkretes Fehlertoleranzverfahren als Beispiel f�r die Anwendung von Soft:
 * schwach fehlertoleranter Nachrichtentransfer. A sendet Inhalt der Eingabe-
 * zeile an B, bei Empfang schickt B eine Quittung an A zur�ck.
 * 
 * Vorschl�ge f�r Fehlerinjektion: fail omission in A, fail omission in B
 */


import SoFTlib.*;
import static SoFTlib.Helper.*;


class A extends Node {

	public String runNode(String input) throws SoFTException {  // Verhalten von Knoten A.
		String Inhalt = words(input, 1, 1, 1);      	// Erstes Wort der Eingabezeile.
	  	Msg m;
	  	say("test");

	 	m = form('n', Inhalt).send("B");  				// an Knoten B senden

	  	if (receive("B", 'q', 100) == null) {       	// Empfang der Quittierungsnachr.
	  		return "1";                  
	  	}
	  	else {
			return "0";
	  	}
	}
}


class B extends Node {

	public String runNode(String input) throws SoFTException {  // Verhalten von Knoten B.
		Msg m = receive('A', 'n', 200);             	// Empfange Nachricht von Knoten A.
	  	if (m == null) {
	  		return "1 keinInhalt";      				// Keine Nachr. erhalten.
	  	}
	  	else {
	  		form('q', "").send('A');                	// Sende Quittierungsnachr. an A.
			return "0 " + m.getCo();              
	  	}
	  	
	}
}

public class Anfang extends SoFT {

	public int result (String input, String[] output) {
		// Bewertung der Ausgaben: 0 = bestens, ... , 5 = am schlechtesten.
    	// Hier:  Result 0 = Alles ok.
		//        Result 1 = Quittung verlorengegangen.
		//        Result 2 = Urspr�ngliche Nachricht verlorengegangen.
		return number(words(output[0], 1, 1, 1)) + number(words(output[1], 1, 1, 1));
  	}


	public static void main (String[] args) {
  		new Anfang().runSystem(new Node[]{new A(),new B()},"Anfang", "Anfang mit SoFT", "Thorsten Kimmeskamp");
	}
}
