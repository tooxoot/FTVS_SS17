package uebung00;

import SoFTlib.*;
import static SoFTlib.Helper.*;

abstract class AbstractNode extends Node {

	/**
	 * Erstellt eine Signatur f�r einen Nachrichhteninhalt
	 * 
	 * @param sender
	 *            Bezeichnung des Senders
	 * @param inhalt
	 *            Der zu signierende Inhalt einer Nachricht
	 * @return
	 */
	public String signiere(String sender, String inhalt) {
		
		String a = sender;
		int s = 32 + (int) a.charAt(0);
		a = "" + (char) +s;
		//TODO
		say(">>" + sender + " | " + inhalt + " | " + s + " | " + a);
		return a;

	}

	/**
	 * F�hrt eine Pr�fungs gem�� des zugrunde liegenden Signatuverfahrens durch und dient damit der Erkennung von Fehlern in einer
	 * Nachricht.
	 * 
	 * @param nachricht
	 *            Die Nachricht als String, die auf Korrektheit gepr�ft werden soll. Das Format der Nachrichten, die Knoten B erh�lt ist von der Form
	 *            "inhalt;signatur"
	 * @return Die Signatur bei bestandener Pr�fung; sonst null.
	 */
	public abstract String pruefe(String nachricht);
}

class A extends AbstractNode {

	@Override
	public String runNode(String input) throws SoFTException {
		// Erstes Wort der Eingabezeile.
		String Inhalt = words(input, 1, 1, 1);

		Msg m1;

		// Nachricht formen (Inhalt:Signatur) und an Knoten B senden
		m1 = form('n', Inhalt + ";" + signiere("A", Inhalt));
		//TODO
		say(">>  " + m1.getCo());
		m1.send("B");
		
		if ((receive("B", 'q', 100)) == null) {
			m1.send('B');
			return "1"; // Ggf. Wiederholung des Sendens.
		} else {
			return "0"; // Keine Wiederholung des Sendens.
		}
	}

	@Override
	public String pruefe(String inhalt) {
		// Wird hier nicht ben�tigt...
		return "";
	}
}

class B extends AbstractNode {
	
	@Override
	public String runNode(String input) throws SoFTException {
		Msg m = receive('A', 'n', 200); // Empfange Nachricht von Knoten A.
		say(">>" + m.getCo());
		
		if (m == null) {
			return "2 keinInhalt"; // Keine Nachr. erhalten.
		}
		if (pruefe(m.getCo()) != null) {
			form('q', "").send('A'); // Sende Quittierungsnachr. an A.
			return "0 " + m.getCo(); // Zuerst empfangene Nachr. war ok.
		} else {
			m = receive('A', 'n', 200); // Empfange Wiederholung der Nachr.
			if (m != null && pruefe(m.getCo()) != null)// Signatur der Wiederhol. ist ok?
			{
				form('q', "").send('A'); // Sende Quittierungsnachr. an A.
				return "1 " + m.getCo(); // Als zweite empf. Nachr. war ok.
			} else {
				System.out.println("2 keinInhalt");
				return "2 keinInhalt"; // Keine der empf. Nachr. war ok.
			}

		}

	}

	@Override
	public String pruefe(String nachricht) {
		/* Knoten B erwartet eine Nachricht von Knoten A */

		String[] tokens = nachricht.split("[;]"); // Die Methode split zerlegt den String mit Hilfe des Trennzeichen ; in mehrere (hier nur
													// zwei) String Objekte.
		if (tokens.length <= 1)
			return null;

		String signatur = tokens[1]; // Der Signaturwert ist im zweiten index des Arrays hinterlegt

		// Pr�ft, ob die Nachricht tats�chlich von Knoten A stammt.
		if (signatur != null && signatur.length() == 1) {
			int s = (int) signatur.charAt(0);
			if (s >= 97 && s % 2 == 1) {
				return signatur;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}

public class GeruestUebung0 extends SoFT {
	
	@Override
	public int result(String input, String[] output) {
		// Bewertung der Ausgaben: 0 = bestens, ... , 5 = am schlechtesten.

		// Hier:
		// Result 0 = Erste Nachricht von A ist ok.
		// Result 1 = Erste Nachr. von A kommt nicht an, zweite ist ok.
		// Result 2 = Erste Nachr. von A kommt falsch an, zweite ist ok.
		// Result 3 = Empf�nger B erh�lt keine korrekte Nachricht von A.
		// Result 4 = Empf. B entscheidet sich f�r einen falschen Inhalt.
		// Result 5 = nicht benutzt.
		if (number(words(output[1], 1, 1, 1)) <= 1 && !words(output[1], 1, 2, 2).equals(input))
			return 4;
		else
			return number(words(output[0], 1, 1, 1)) + number(words(output[1], 1, 1, 1));
	}

	public static void main(String[] args) {
		new GeruestUebung0().runSystem(new Node[] { new A(), new B() }, "Einf�hrungs�bung", "Einf�hrungs�bung f�r die Einarbeitung mit SoFT", "");
	}
}
