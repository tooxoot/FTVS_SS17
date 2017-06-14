package uebung02;
import static SoFTlib.Helper.*;

import java.util.Arrays;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import SoFTlib.*;
class Rechenprozess extends Node {

	/**
	 * Simuliert einen Rechenschritt durch Manipulation des Parameters a und einer kurzen Wartezeit. Die Methode arbeitet vollkommen
	 * deterministisch. Die Implementierung dieser Methode MUSS NICHT nachvollzogen werden.
	 * 
	 * @param a
	 *            Inhalt des Arrays a vor Ausfuehrung des Rechenschritts
	 * @return Boolsches Array welches impliziert, welche Werte des Arrays a nach Ausfuehrung des Rechenschritts veraendert wurden
	 * @throws SoFTException
	 */
	
	/*Sie veraendert bei jedem Aufruf das Array a scheinbar zufaellig,
	aber deterministisch. Der erste Eintrag des Arrays a[0] kann als eine Art Befehlszeiger aufgefasst
	werden, der bei jedem fehlerfreien Rechenschritt um 1 erhoeht wird. Weiterhin simuliert schritt auch
	eine kurze Bearbeitungsdauer. Der von schritt zurueckgelieferte Wert ist ein 8-elementiges boolesches
	Array, das angibt, welche Elemente von a in dem durchgefuehrten Rechenschritt veraendert worden sind.
	Nach Ausfuehrung eines Rechenschritts sendet Knoten A eine Ergebnis-Nachricht (Typ ’e’), die alle
	8 Elemente des Vektors a enthaelt (durch Leerzeichen getrennt) an den Absoluttest B, der diese auf
	Korrektheit prueft.
	*/
	
	boolean[] schritt(int[] a) throws SoFTException {
		boolean[] b = { true, false, false, false, false, false, false, false };
		int[] k = { Math.max(0, (10000 * (a[0] % 99) + 100 * (a[1] % 99) + (a[4] % 99)) % 7),
				Math.max(0, (10000 * (a[0] % 99) + 100 * (a[3] % 99) + (a[5] % 99)) % 7) };
		a[0]++;
		if (a[0] % 13 == 0) {
			a[7]++;
			b[7] = true;
		}
		for (int i = 0; i < 2; i++) {
			int[] d = { 0, 0, 0 };
			if (k[i] <= 2) {
				d[k[i]] = 3;
			} else if (k[i] <= 4) {
				d[k[i] - 3] = 2;
				d[k[i] - 2] = 1;
			} else if (k[i] == 5) {
				d[0] = 2;
				d[2] = 1;
			} else {
				d[0] = d[1] = d[2] = 1;
			}
			for (int j = 0; j < 3; j++)
				if (d[j] > 0) {
					a[3 * i + j + 1] = a[3 * i + j + 1] % 99 + d[j];
					b[3 * i + j + 1] = true;
				}
			if (a[3 * i + 1] % 99 + a[3 * i + 2] % 99 + a[3 * i + 3] % 99 > 6 * (8 + i) + 30) {
				int m = 3 * i + 24, x;
				x = Math.min(m, a[3 * i + 3] - 10);
				a[3 * i + 3] -= x;
				m -= x;
				x = Math.min(m, a[3 * i + 2] - 10);
				a[3 * i + 2] -= x;
				m -= x;
				a[3 * i + 1] -= m;
				b[3 * i + 3] = b[3 * i + 2] = b[3 * i + 1] = true;
			}
		}
		receive('A', 'x', time() + 5);
		return b;
	}

	public String runNode(String input) throws SoFTException {
		/*
		 * TODO Rechenprozess Implementieren
		 * 
		 * 
		 *1.Ruecksetzpunkte erstellen und Nachricht senden:
			Ruecksetzpunkte werden gemaeß dem Verfahren „unvollstaendige Zustandsaufzeichnung“ dadurch erstellt,
			dass Knoten A nach jedem Rechenschritt die Elemente des Arrays a, die sich veraendert haben, in einer
			Ruecksetzpunkt-Nachricht (Nachrichtentyp ’z’ ohne Fehlerinjektion) an den Ruecksetzpunktverwalter C
			sendet – und zwar unmittelbar bevor er das Array a in einer Ergebnis-Nachricht an den Absoluttest
			B sendet. In der Ruecksetzpunkt-Nachricht wird eine kommaseparierte Liste verwendet, wobei jedes
			Element aus dem Index der veraenderten Zahl und der veraenderten Zahl selbst besteht, welche wiederum
			durch Leerzeichen getrennt werden.
		 
		 *2.Empfangen von Nachrichten:
		 	Bekommt Werte zugesand und fuehrt mit diesen Werten dann den naechsten Rechenschritt aus. Insgesamt fuehrt Knoten
			A stets genau 80 Rechenschritte auf diese Weise aus. Insbesondere laeuft a[0] dabei von 10 bis 90.
		 	Anmerkung: Letztlich uebernimmt Knoten A vor jedem Rechenschritt die 8
			Zahlen fuer das Array a aus der eintreffenden Nachricht, gleichgueltig ob diese von Knoten B oder Knoten
			C kommt.
		 
		 *3.Terminieren:
		 	Knoten A fuehrt so lange Rechenschritte aus, bis ihm nach 80 Rechenschritten die Endwerte {90, 46,
			19, 13, 46, 23, 12, 16} des Arrays a zugesandt werden. Danach terminiert A mit
			return s + " Rechenschritte durchgefuehrt.";
			wobei s die Anzahl der tatsaechlich durchgefuehrten Rechenschritte ist. Bedingt durch das Zuruecksetzen
			ist sie oftmals groeßer als 80.
		 * 
		 */
		int[] a={10, 10, 10, 10, 10, 10, 10, 10}; //starting values for a
		int[] resulta={90, 46, 19, 13, 46, 23, 12, 16}; //result   values for a
		int s =0;	//counter for operations
		while(!Arrays.equals(a, resulta)){
			//increasing counter by one
			s++; 
			//using the method "schritt" to perform progress
			boolean[] tempBoolenanArray= schritt(a); //
				
			//creating message for C & B
			String b = null;
			String a_String="";
			for(int i=0; i<=7;i++){
				a_String+= a[i]+" "; 					//message to B has to be separated with spaces
				if(tempBoolenanArray[i] == true){
					if(b == null){b= i + " " + a[i];} 	//no comma in first place
					else{b+= ", " + i + " " + a[i];} 	//need comma here
				}
			}
			//messages done, now send to C to create RP
			form('z', b).send('C');
			//send to B for testing
			form('e', a_String ).send('B');
			
			//recieve message from either B or C and use the results for next iteration
			Msg recived_msg = receive("BC", 'z', time()+50);
			//overwriting values from a with recieved array
			/* TODO insert this line for full functionality of A
			 */
			 	for (int i = 0; i <= 7; i++) {	a[i] = number(recived_msg.getCo() ,i+1);	}	//fixed the "word" method, because the recived message is only separated by spaces and not by commas
			//																					//and the number and word method start with index 1
			
			
		}
		return s + " Rechenschritte durchgefuehrt" ;
	}

}

class Absoluttest extends Node {

	/**
	 * Gibt den wahren Fortschritt an, der sich aus dem Inhalt des Arrays a ergibt. Der zuvor erreichte Fortschritt (f_alt) wird nicht
	 * unterschritten
	 * 
	 * @param f_alt
	 *            der zuvor erreichte Fortschritt.
	 * @param a
	 *            Inhalt des Arrays a, welcher vom Rechenprozess zugesandt wurde
	 * @return Inhalt der Fortschrittsvariablen
	 */
	int fortschritt(int f_alt, int[] a) {
		int n1 = a[0] % 99 - 10,
				n2 = (((a[1] % 99 + a[2] % 99 + a[3] % 99 - a[4] % 99 - a[5] % 99 - a[6] % 99) / 3 + 9) % 9 * 8
						+ (a[1] % 99 + a[2] % 99 + a[3] % 99 - 30) / 3);
		if (n1 == n2 && a[0] / 13 + 10 == a[7])
			return Math.max(f_alt, n1);
		else
			return f_alt;
	}

	/**
	 * Fuehrt einen Absoluttest zur Feststellung der Korrektheit der Werte von a durch mit einer nur begrenzten Fehlererfassung. Manche
	 * Fehler werden nicht sofort sondern eventuell erst einige Rechenschritte spaeter erkannt.
	 * 
	 * @param a
	 *            Zu ueberpruefendes int-Array
	 * @return Ergebnis des Absoluttests
	 */
	boolean absoluttest(int[] a) {
		int n1 = a[0] % 99 - 10,
				n2 = (((a[1] % 99 + a[2] % 99 + a[3] % 99 - a[4] % 99 - a[5] % 99 - a[6] % 99) / 3 + 9) % 9 * 8
						+ (a[1] % 99 + a[2] % 99 + a[3] % 99 - 30) / 3);
		if (n1 != n2 || a[0] / 13 + 10 != a[7])
			if ((7 * n1 + 11 * n2) % 5 < 3)
				say("Absoluttest erkennt Fehler nicht.");
			else
				say("Absoluttest erkennt Fehler.");
		return n1 == n2 && a[0] / 13 + 10 == a[7] || (7 * n1 + 11 * n2) % 5 < 3;
	}

	public String runNode(String input) throws SoFTException {
		/* 	Empfaengt Nachricht von A und preuft dies mit der Methode absoluttest:	
		  	Nach Ausführung eines Rechenschritts sendet Knoten A eine Ergebnis-Nachricht (Typ ’e’), die alle
			8 Elemente des Vektors a enthält (durch Leerzeichen getrennt) an den Absoluttest B, der diese auf
			Korrektheit prüft. Dazu wird die Methode
			boolean absoluttest(int[] a)
			benutzt, die ebenfalls im Programmgerüst bereitgestellt wird. Bei bestandenem Absoluttest (Rückgabewert
			true) sendet Knoten B alle 8 Elemente des Vektors a an den Knoten A zurück (Nachrichtentyp
			’z’).
		 
		 *	In Knoten B soll weiterhin eine int-Variable f vereinbart werden, die den erreichten Fortschritt angibt.
			Dazu wird unmittelbar vor jedem Absoluttest
			f = fortschritt(f, a);
			aufgerufen. Damit wird angezeigt, dass das fiktive Anwendungsprogramm mit den Daten des Arrays
			a einen Fortschritt erreicht (der beim Zurücksetzen wieder teilweise reduziert wird). Die Methode
			Fortschritt wird ebenfalls im Programmgerüst zur Verfügung gestellt.
			Wenn der Absoluttest einen Fehler erkennt, sendet er keine Nachricht an A, sondern eine Fehlermeldung
			(Nachrichtentyp ’f’) an den Rücksetzpunktverwalter C mit dem Inhalt "<f> + Fehlermeldung", wobei
			<f> der Wert der Fortschrittsvariablen ist.
		 
		 * 	Die Knoten B und C verwenden für ihre Empfangsoperationen eine
			Zeitschranke, so dass sie ebenfalls terminieren, wenn sie von A keine Nachricht mehr erhalten. Knoten
			B terminiert mit
			return f + " wurde erreicht.";
			wobei f der letzte Wert der Fortschrittsvariablen f ist.
		 */
		
		int f=0;
		int[] a= {0,0,0,0,0,0,0,0};
		 Msg receivedmsg =receive('A', 'e', 100);
		while(receivedmsg!= null){
			
			//creating int[] 
			for(int i=0; i<=7;i++){	 a[i]= number(receivedmsg.getCo(),i+1);} 				
			
			//using the "fortschritt" method to simulate progress that was made
			f=fortschritt(f, a);
			
			//using the "absoluttest" method to check the recieved message and send Message back to A 
			if(absoluttest(a)==true){form('z', receivedmsg.getCo()).send('A');}
			
			//if recieced message is incorrecct send message to C
			else{ form('f', f + " + Fehlermeldung").send('C'); }
			
			
			receivedmsg = receive('A', 'e', time()+100);
		}
		return f + " wurde erreicht";
	}
}

class Ruecksetzpunktverwalter extends Node {

	public String runNode(String input) throws SoFTException {
		/*
		 * TODO Ruecksetzpunktverwalter implementieren!
		 * 
		 * 
		 * Es wird eine Fehlermeldung (Nachrichtentyp ’f’)  von dem  Rücksetzpunktverwalter C mit dem Inhalt "<f> + Fehlermeldung" empfangen, wobei
			<f> der Wert der Fortschrittsvariablen ist. Als Antwort darauf sendet der Rücksetzpunktverwalter an
			den Knoten A einen vollständigen Rücksetzpunkt, bestehend aus 8 Zahlen (Nachrichtentyp ’z’), die A in
			das Array a übernimmt.
			
			Rücksetzpunkte werden gemäß dem Verfahren „unvollständige Zustandsaufzeichnung“ dadurch erstellt,
			dass Knoten A nach jedem Rechenschritt die Elemente des Arrays a, die sich verändert haben, in einer
			Rücksetzpunkt-Nachricht (Nachrichtentyp ’z’ ohne Fehlerinjektion) an den Rücksetzpunktverwalter C
			sendet
			
			Knoten C speichert die ihm zugesandten (meist unvollständigen) Rücksetzpunkte in einem zweidimensionalen
			Array RP. Der erste Index von RP bezeichnet den (meist unvollständigen) Rücksetzpunkt,
			wobei ältere Rücksetzpunkt einen kleineren Index aufweisen. Insgesamt werden bis zu 50 (meist unvollständige)
			RP gespeichert. Der zweite Index von RP bezeichnet das Element von a. Bei unvollständigen
			Rücksetzpunkten wird für alle nicht verfügbaren Elemente von a der Wert –1 eingetragen. Wenn der
			Rücksetzpunkt-Speicher RP voll ist und ein weiterer Rücksetzpunkt gespeichert werden soll, muss der
			mit Index 1 abgespeicherte Rücksetzpunkt RP[1][...] aus RP gelöscht werden. Alle nachfolgenden
			Rücksetzpunkte RP[x][...] rücken dann um eine Position nach vorn. Der älteste Rücksetzpunkt RP
			[0][...] enthält die Anfangsbelegung {10, 10, 10, 10, 10, 10, 10, 10} von a und wird niemals
			gelöscht.
			
			Wenn der Absoluttest durch eine Fehlermeldung (Nachrichtentyp ’f’) eine Rückwärtsbehebung verlangt,
			dann soll aus den unvollständigen Rücksetzpunkten ein vollständiger zusammengesetzt werden. Außerdem
			ist die Rücksetzweite in geeigneter Weise zu bestimmen. Dazu soll der mit der Fehlermeldung übermittelte
			Fortschritt ausgewertet werden.
			
			
			Die Knoten B und C verwenden für ihre Empfangsoperationen eine
			Zeitschranke, so dass sie ebenfalls terminieren, wenn sie von A keine Nachricht mehr erhalten. Knoten
			B terminiert mit
			return f + " wurde erreicht.";
			wobei f der letzte Wert der Fortschrittsvariablen f ist. Knoten C terminiert mit
			return anzZurueck + " mal zurueckgesetzt.";
			wobei anzZurueck die Anzahl der durchgeführten Rücksetzoperationen ist.
		 */
		return null;
	}
}

public class G06_A2 extends SoFT {

	public int result(String input, String[] output) {
		int a = number(output[0], 1), b = number(output[1], 1), c = number(output[2], 1);
		if (exec() == 1)
			setSummary(c + "   " + c + "   " + c + ".0 mal zurueckgesetzt.");
		else {
			int s = number(getSummary(), 2);
			String q10 = "" + (10 * (s + c) / exec());
			int r = q10.length();
			String q1 = q10.substring(0, r - 1) + "." + q10.substring(r - 1, r);
			setSummary(c + "   " + (s + c) + "   " + q1 + " mal zurueckgesetzt.");
		}
		if (a < 10 || b < 10)
			return 5; // Keine sinnvolle Programmausfuehrung.
		if (b < 60 || b > 81)
			return 2; // Zu geringer/großer Fortschritt.
		if (a < b)
			return 4; // Weniger Schritte als Fortschritt angibt.
		if (a > b && c <= 0)
			return 3; // Zu viele Schritte, aber kein Zuruecksetzen.
		if (a == b && c != 0)
			return 3; // Zuruecksetzen erhoeht Schrittanzahl nicht.
		if (a > b && c > 0)
			return 1; // Zuruecksetzen erfolgreich durchgefuehrt.
		return 0; // Niemals zurueckgesetzt (offenbar stets ff).
	}

	public static void main(String[] Aufrufparameter) {
		new G06_A2().runSystem(new Node[] { new Rechenprozess(), new Absoluttest(), new Ruecksetzpunktverwalter() },
				"sxx_02b", "Unvollstaendige Zustandsaufzeichnung", "Sven und Justin");
	}
}
