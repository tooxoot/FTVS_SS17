package uebung01.exercise01;
import java.util.*;

import static SoFTlib.Helper.*;
import SoFTlib.*;
import jdk.nashorn.internal.runtime.events.RecompilationEvent;

/**
 * The Nodes {B, C, D, E, F} are implemented in this class. Therefore their behavior is identical.
 */
class BCDEF extends Node {

	//60 seconds
	public static final long MAX_DAUER = 60 * 1000;

	@Override
	public String runNode(String input) throws SoFTException {
		// currently received message
		Msg rxMsg = null;

		// Result message for last received Message
		Msg txMsg = null;
		long nextTxTime = MAX_DAUER;

		boolean ende = false;
		while (!ende) {

			rxMsg = receive("A", nextTxTime);
			long currentTime = time();

			//Only send the result-message if 150 ms have passed
			// >> If a new Message is received in between this 150 ms the last result is deleted!
			if (nextTxTime <= currentTime) {
				if (txMsg != null)
					txMsg.send('A');
				nextTxTime = MAX_DAUER;
			}

			// Calculates the result or terminates
			if (rxMsg != null) {
				// say("got A message!");
				switch (rxMsg.getTy()) {
				case 'a': // Neue Auftragsnachricht
					//Calculates the sum of the three integers given in the Message
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
		//The word's and items's numbering is 1 based, meaning the first word is assignet to 1.
		int mode = number(input, 1);
		String receivers = word(input, 2, 1);
		int receiverCount = receivers.length();
		say("mode + receivers + receiverCount: " + mode + " " + receivers + " " + receiverCount );
		
		int erkannteFehler = 0;
		int unerkannteFehler = 0;
		boolean abbruch = false;
		
		//Array f�r den aktuellen Zustand der Knoten aus Sicht von A, Anfangs alle Knoten ff
		//Index 0 gilt f�r Knoten B usw.
		boolean fehlerhafte_Rechner[]={true,true,true,true,true,}; 
		
//		double currentTime = time();
//		double deltaTime = 151;
//		int i = 1;
//		ArrayList<Msg> receivedMessages = new ArrayList<Msg>();
//		while (true){
//			
//			if(deltaTime > 150){
//				deltaTime = 0;
//				String content = erzeugeInhalt(i + 1);
//				Msg currentMessage = form('a', content);
//				currentMessage.send(receivers);
//			}
//			
//			if(receivedMessages.size() < receiverCount){
//				
//			}
//			
//			break;
//		}
		
		switch (mode) {
		case 1:
			/* In dieser Betriebsart werden Aufträge an alle<Rechner>gleichzeitig gesendet. Nur wenn A eine absolute
			Mehrheit gleicher Ergebnisse empfängt, gilt die Bearbeitung des Auftrags aus der Sichtweise von A als
			korrekt. Falls keine absolute Mehrheit gebildet werden konnte, soll der entsprechende Simulationslauf
			vorzeitig abgebrochen werden.*/
			int i = 0;
			do {
				// Form message and send it to all receivers
				String content = erzeugeInhalt(i + 1);
				Msg currentMessage = form('a', content);
				currentMessage.send(receivers);
				
				// Receives all Results
				ArrayList<Msg> receivedMessages = new ArrayList<Msg>();
				while(receivedMessages.size() < receiverCount){
					Msg receivedMessage = receive(receivers, 160); //Kommt es hier zu Problemen? Da die recieve Methode mit der globalen Zeit arbeitet? 
					//Muss hier M�glicherweise "receive(receivers, 160*(i+1))" hin!?
					if( receivedMessage != null ) receivedMessages.add(receivedMessage);
					
					say("MSG:" + receivedMessages.size());
				}
				
				// Extract Results as int
				int[] receivedResults = new int[receivedMessages.size()];
				for(int j = 0; j < receivedMessages.size(); j++){
					receivedResults[j] = number( receivedMessages.get(j).getCo() );
				}
				
				// Check Results and terminate if 
				if( !istMehrheitVorhanden(receivedResults, Math.round(receivedResults.length / 2)) ){
					abbruch = true;
					break;
				}
			
			} while(i++ < 10);
			break;
		case 2:
			/*In dieser Betriebsart wird immer nur ein Rechner der festgelegten<Rechner>gleichzeitig verwendet. Falls
			ein vordefnierter schlechter Absoluttest (MethodeschlechterAbsoluttest, entdeckt ca. 87,5% der
			Fehler) ein Ergebnis als fehlerhaft bewertet oder aber ein Ergebniswert fehlt, soll A wiederholt den gleichen
			Auftrag von dem gleichen Rechner anfordern. Sobald ein Rechner zweimal ein falsches bzw. fehlendes
			Ergebnis lieferte, gilt dieser Rechner bis zum Ende eines Simulationslaufs als fehlerhaft. Dementsprechend
			beauftragtAeinen weiteren, noch nicht als fehlerhaft eingestuften Rechner mit dem aktuellen und
			den nachfolgenden Aufträgen, bis alle Aufträge erfolgreich bearbeitet wurden oder aber kein fehlerfreier
			Rechner mehr verfügbar ist. In letzterem Fall soll ein Simulationslauf vorzeitig abgebrochen werden.*/
			int j = 0;
			do{
				//Form message and send it to the current reciever(has to be only 1!)
				String content = erzeugeInhalt(j + 1);
				Msg currentMessage = form('a', content);
				currentMessage.send(receivers);
				
				Msg recievedMessage = receive(receivers, 160*(j+1));
				if(recievedMessage==null){
					currentMessage.send(receivers);
				}
				
				
				
			}while(j++ < 10);
		
			break;
		
		case 3:
			/*Adarf stets alle 5 Rechner nutzen, wobei zeitgleich zunächst nur die durch <Rechner> angegebenen
			Rechner parallel rechnen. Sobald neben einer absoluten Mehrheit auch eine Minderheit ofenbar falscher
			oder fehlender Ergebnisse vorliegt, sollen die Rechner dieser falschen bzw. fehlenden Ergebnisse aus-
			gegliedert und durch andere, bislang ungenutzte Rechner für nachfolgende Rechnungen ersetzt werden.
			Falls keine absolute Mehrheit gebildet werden konnte, soll der entsprechende Simulationslauf vorzeitig
			abgebrochen werden.*/


			break;
		case 4:
			/*In dieser Betriebsart werden zunächst alle durch<Rechner>festgelegten Rechner zur Ermittlung eines
			mehrheitlichen gleichen Ergebnisses verwendet. Existiert allerdings ein mehrheitlich gleiches Ergebnis und
			existieren weitere davon abweichende oder fehlende Ergebnisse, so gelten die entsprechenden Rechner bis
			zum Ende eines Simulationslaufs als fehlerhaft und werden für zukünftige Aufträge nicht mehr verwendet.
			Auf diese Weise verringert sich über einen Simulationslauf die Anzahl der nutzbaren Rechner und dadurch
			auch die Anzahl der notwendigen gleichen Ergebnisse, die eine absolute Mehrheit bilden. Falls nur noch
			ein Rechner als fehlerfrei gilt oder aber keine absolute Mehrheit mehr gebildet werden konnte, soll der
			entsprechende Simulationslauf vorzeitig abgebrochen werden.*/

			break;
		default:
			break;
		}

		//form('q', "").send("BCDEF");
		return abbruch + " " + erkannteFehler + " " + unerkannteFehler;
	}
}

public class G06_A1 extends SoFT {

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
		new G06_A1().runSystem(new Node[] { new A(), new BCDEF(), new BCDEF(), new BCDEF(), new BCDEF(), new BCDEF() },
				"G06_A1", "�bungsblatt 1: Redundanz", "Sven und Justin");
	}
}
