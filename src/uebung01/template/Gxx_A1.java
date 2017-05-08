package uebung01.template;
import java.util.*;

import static SoFTlib.Helper.*;
import SoFTlib.*;

class BCDEF extends Node {

	public static final long MAX_DAUER = 60 * 1000;

	@Override
	public String runNode(String input) throws SoFTException {

		Msg rxMsg = null;
		Msg txMsg = null;
		long nextTxTime = MAX_DAUER;

		boolean ende = false;
		while (!ende) {

			rxMsg = receive("A", nextTxTime);
			long currentTime = time();
			if (nextTxTime <= currentTime) {
				if (txMsg != null)
					txMsg.send('A');
				nextTxTime = MAX_DAUER;
			}

			if (rxMsg != null) {
				// say("got A message!");
				switch (rxMsg.getTy()) {
				case 'a': // Neue Auftragsnachricht
					int ergebnis = number(rxMsg.getCo(), 1) + number(rxMsg.getCo(), 2) + number(rxMsg.getCo(), 3);
					txMsg = form('e', ergebnis);
					nextTxTime = currentTime + 150;
					break;

				case 'q': // Terminierungsnachricht
					ende = true;
					break;
				default:
					break;
				}
			}
		}
		return "";
	}
}

class A extends Node {

	public static String erzeugeInhalt(int seqNr) {
		Random r = new Random(seqNr);
		String s = (r.nextInt(100) - 50) + " ";
		s += (r.nextInt(100) - 50) + " ";
		s += (r.nextInt(100) - 50);
		return s;
	}

	/*
	 * Fuehrt einen (schlechten) Absoluttest zwischen einem Eingabe-Vektor und einem ergebniswert durch. Die Methode liefert true zurueck,
	 * falls das Ergebnis fuer den Eingabe-Vektor richtig ist oder aber der Absoluttest versagt hat.
	 */
	public static boolean schlechterAbsoluttest(String eingabeVektor, int ergebnis) {
		int eingabe1 = number(eingabeVektor, 1), eingabe2 = number(eingabeVektor, 2),
				eingabe3 = number(eingabeVektor, 3);

		return (((eingabe1 + eingabe2 + eingabe3) ^ ergebnis) & 0x07) == 0;
	}

	/*
	 * Fuehrt einen perfekten Test von einem Eingabe-Vektor mit dem Ergebnis durch. Die Methode liefert dann und nur dann true zurueck,
	 * falls das Ergebnis fuer den eingabeVektor richtig ist. Die Methode darf nicht als Absoluttest verwendet werden.
	 */
	public static boolean perfekterTest(String eingabeVektor, int ergebnis) {
		int eingabe1 = number(eingabeVektor, 1), eingabe2 = number(eingabeVektor, 2),
				eingabe3 = number(eingabeVektor, 3);
		return (eingabe1 + eingabe2 + eingabe3) == ergebnis;
	}

	/*
	 * Prueft, ob in einem Array von Werten eine Mehrheit von mindestAnzahl gleichen Werten enthalten ist. Falls ja, dann liefert die
	 * Methode true zurueck, sonst false.
	 */
	static boolean istMehrheitVorhanden(int[] werte, int mindestAnzahl) {
		if (werte.length < mindestAnzahl)
			return false;
		else {
			for (int i = 0; i < werte.length; i++) {
				int curVal = werte[i];
				int curCount = 1;
				for (int j = i + 1; j < werte.length; j++) {
					if (curVal == werte[j])
						curCount++;
					if (curCount >= mindestAnzahl)
						return true;
				}
			}
		}
		return false;
	}

	/*
	 * Gibt einen Mehrheitswert aus einem Array von Werten zurueck, wobei in dem Array mindestens mindestAnzahl gleiche Werte enthalten sein
	 * muessen (zu pruefen durch die Methode istMehrheitVorhanden).
	 */
	static int bildeMehrheit(int[] werte, int mindestAnzahl) {
		if (werte.length < mindestAnzahl)
			return -100;
		else {
			for (int i = 0; i < werte.length; i++) {
				int curVal = werte[i];
				int curCount = 1;
				for (int j = i + 1; j < werte.length; j++) {
					if (curVal == werte[j])
						curCount++;
					if (curCount >= mindestAnzahl)
						return curVal;
				}
			}
		}
		return -100;
	}

	/*
	 * Gibt ein Array aus 5 boolean-Werten zurueck, wobei jeder Wert anzeigt, ob der Buchstabe eines korrespondierenden Knotens enthalten
	 * ist (Index 0 gehoert zu Rechner B, Index 1 zu Rechner C, ...).
	 */
	static boolean[] nodeStrToBoolArray(String s) {
		boolean[] r = new boolean[5];
		for (int i = 0; i < s.length(); i++)
			if ((s.charAt(i) >= 'B') && (s.charAt(i) <= 'F'))
				r[s.charAt(i) - 'B'] = true;
		return r;
	}

	/*
	 * Bildet aus einem boolean-Array einen Knoten-String (umgekehrte Funktionsweise von nodeStrToBoolArray).
	 */
	static String nodeBoolArrayToStr(boolean[] a) {
		String r = "";
		for (int i = 0; i < a.length; i++)
			if (a[i])
				r += (char) ((int) 'B' + i);
		return r;
	}

	@Override
	public String runNode(String input) throws SoFTException {
		int erkannteFehler = 0;
		int unerkannteFehler = 0;
		boolean abbruch = false;

		// Ergaenzen Sie den Quellcode!

		form('q', "").send("BCDEF");
		return abbruch + " " + erkannteFehler + " " + unerkannteFehler;
	}
}

public class Gxx_A1 extends SoFT {

	public int result(String input, String[] output) {
		boolean abbruch = Boolean.parseBoolean((word(output[0], 1)));
		int erkannteFehler = number(output[0], 2);
		int unerkannteFehler = number(output[0], 3);
		int r = 5;
		if ((abbruch == false) && (erkannteFehler == 0) && (unerkannteFehler == 0))
			r = 0;
		if ((abbruch == false) && (erkannteFehler > 0) && (unerkannteFehler == 0))
			r = 1;
		if ((abbruch == false) && (erkannteFehler > 0) && (unerkannteFehler > 0))
			r = 2;
		if ((abbruch == false) && (erkannteFehler == 0) && (unerkannteFehler > 0))
			r = 3;
		if (abbruch == true)
			r = 4;

		return r;
	}

	public static void main(String[] args) {
		new Gxx_A1().runSystem(new Node[] { new A(), new BCDEF(), new BCDEF(), new BCDEF(), new BCDEF(), new BCDEF() },
				"Gxx_A1", "�bungsblatt 1: Redundanz", "Name");
	}
}
