package uebung00;

import SoFTlib.*;
import static SoFTlib.Helper.*;

import javax.management.loading.MLet;

public class Geruest2b extends SoFT {

	private static class A extends AbstractNode2b {

		@Override
		public String runNode(String input) throws SoFTException {
			// Erstes Wort der Eingabezeile.
			String Inhalt = words(input, 1, 1, 1);

			Msg m1;
			
			// Nachricht formen (Inhalt:Signatur) und an Knoten B senden
			m1 = form('n', Inhalt + ";" + signiere("A", Inhalt));
			
			int tryCount = 1;
			do{
				m1.send("B");
				if ((receive("B", 'q', 100)) != null) break;
			}while(tryCount++ < 6);
			
			return tryCount == 1? "0" : "1";
			
		}

		@Override
		public String pruefe(String inhalt) {
			// Wird hier nicht ben�tigt...
			return "";
		}
	}

	private static class B extends AbstractNode2b {
		
		@Override
		public String runNode(String input) throws SoFTException {
			Msg m = receive('A', 'n', 200); // Empfange Nachricht von Knoten A.
			
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
			int s = Integer.valueOf(signatur);
			if (signatur != null && signatur.length() == 4) {
				
				if ((s * oeffFaktor) % mod == (komprimiere(tokens[0]) * oeffProdukt) % mod) {
					return signatur;
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
	}

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
		new Geruest2b().runSystem(new Node[] { new A(), new B() }, "Einf�hrungs�bung", "Einf�hrungs�bung f�r die Einarbeitung mit SoFT", "");
	}
}
