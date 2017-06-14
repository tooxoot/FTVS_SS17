package uebung02;
import static SoFTlib.Helper.*;

import java.util.Arrays;
import java.util.stream.IntStream;

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
	Nach Ausfuehrung eines Rechenschritts sendet Knoten A eine Ergebnis-Nachricht (Typ íeí), die alle
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
			Ruecksetzpunkte werden gemaeﬂ dem Verfahren Ñunvollstaendige Zustandsaufzeichnungì dadurch erstellt,
			dass Knoten A nach jedem Rechenschritt die Elemente des Arrays a, die sich veraendert haben, in einer
			Ruecksetzpunkt-Nachricht (Nachrichtentyp ízí ohne Fehlerinjektion) an den Ruecksetzpunktverwalter C
			sendet ñ und zwar unmittelbar bevor er das Array a in einer Ergebnis-Nachricht an den Absoluttest
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
			ist sie oftmals groeﬂer als 80.
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
			if (recived_msg==null) break;
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
		  	Nach Ausf¸hrung eines Rechenschritts sendet Knoten A eine Ergebnis-Nachricht (Typ íeí), die alle
			8 Elemente des Vektors a enth‰lt (durch Leerzeichen getrennt) an den Absoluttest B, der diese auf
			Korrektheit pr¸ft. Dazu wird die Methode
			boolean absoluttest(int[] a)
			benutzt, die ebenfalls im Programmger¸st bereitgestellt wird. Bei bestandenem Absoluttest (R¸ckgabewert
			true) sendet Knoten B alle 8 Elemente des Vektors a an den Knoten A zur¸ck (Nachrichtentyp
			ízí).
		 
		 *	In Knoten B soll weiterhin eine int-Variable f vereinbart werden, die den erreichten Fortschritt angibt.
			Dazu wird unmittelbar vor jedem Absoluttest
			f = fortschritt(f, a);
			aufgerufen. Damit wird angezeigt, dass das fiktive Anwendungsprogramm mit den Daten des Arrays
			a einen Fortschritt erreicht (der beim Zur¸cksetzen wieder teilweise reduziert wird). Die Methode
			Fortschritt wird ebenfalls im Programmger¸st zur Verf¸gung gestellt.
			Wenn der Absoluttest einen Fehler erkennt, sendet er keine Nachricht an A, sondern eine Fehlermeldung
			(Nachrichtentyp ífí) an den R¸cksetzpunktverwalter C mit dem Inhalt "<f> + Fehlermeldung", wobei
			<f> der Wert der Fortschrittsvariablen ist.
		 
		 * 	Die Knoten B und C verwenden f¸r ihre Empfangsoperationen eine
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
		 * Es wird eine Fehlermeldung (Nachrichtentyp ífí)  von dem  R¸cksetzpunktverwalter C mit dem Inhalt "<f> + Fehlermeldung" empfangen, wobei
			<f> der Wert der Fortschrittsvariablen ist. Als Antwort darauf sendet der R¸cksetzpunktverwalter an
			den Knoten A einen vollst‰ndigen R¸cksetzpunkt, bestehend aus 8 Zahlen (Nachrichtentyp ízí), die A in
			das Array a ¸bernimmt.
			
			R¸cksetzpunkte werden gem‰ﬂ dem Verfahren Ñunvollst‰ndige Zustandsaufzeichnungì dadurch erstellt,
			dass Knoten A nach jedem Rechenschritt die Elemente des Arrays a, die sich ver‰ndert haben, in einer
			R¸cksetzpunkt-Nachricht (Nachrichtentyp ízí ohne Fehlerinjektion) an den R¸cksetzpunktverwalter C
			sendet
			
			Knoten C speichert die ihm zugesandten (meist unvollst‰ndigen) R¸cksetzpunkte in einem zweidimensionalen
			Array RP. Der erste Index von RP bezeichnet den (meist unvollst‰ndigen) R¸cksetzpunkt,
			wobei ‰ltere R¸cksetzpunkt einen kleineren Index aufweisen. Insgesamt werden bis zu 50 (meist unvollst‰ndige)
			RP gespeichert. Der zweite Index von RP bezeichnet das Element von a. Bei unvollst‰ndigen
			R¸cksetzpunkten wird f¸r alle nicht verf¸gbaren Elemente von a der Wert ñ1 eingetragen. Wenn der
			R¸cksetzpunkt-Speicher RP voll ist und ein weiterer R¸cksetzpunkt gespeichert werden soll, muss der
			mit Index 1 abgespeicherte R¸cksetzpunkt RP[1][...] aus RP gelˆscht werden. Alle nachfolgenden
			R¸cksetzpunkte RP[x][...] r¸cken dann um eine Position nach vorn. Der ‰lteste R¸cksetzpunkt RP
			[0][...] enth‰lt die Anfangsbelegung {10, 10, 10, 10, 10, 10, 10, 10} von a und wird niemals
			gelˆscht.
			
			Wenn der Absoluttest durch eine Fehlermeldung (Nachrichtentyp ífí) eine R¸ckw‰rtsbehebung verlangt,
			dann soll aus den unvollst‰ndigen R¸cksetzpunkten ein vollst‰ndiger zusammengesetzt werden. Auﬂerdem
			ist die R¸cksetzweite in geeigneter Weise zu bestimmen. Dazu soll der mit der Fehlermeldung ¸bermittelte
			Fortschritt ausgewertet werden.
			
			
			Die Knoten B und C verwenden f¸r ihre Empfangsoperationen eine
			Zeitschranke, so dass sie ebenfalls terminieren, wenn sie von A keine Nachricht mehr erhalten. Knoten
			B terminiert mit
			return f + " wurde erreicht.";
			wobei f der letzte Wert der Fortschrittsvariablen f ist. Knoten C terminiert mit
			return anzZurueck + " mal zurueckgesetzt.";
			wobei anzZurueck die Anzahl der durchgef¸hrten R¸cksetzoperationen ist.
		 */
		
		int[][] RP =new int [50][8];
		int RP_count=0;
		int[] RPdefault={-1,-1,-1,-1,-1,-1,-1,-1};
		
		RP[0][0]=10;RP[0][1]=10;RP[0][2]=10;RP[0][3]=10;RP[0][4]=10;RP[0][5]=10;RP[0][6]=10;RP[0][7]=10;
		int errorcount=0;
		
		Msg receivedRP = receive('A', 'z', 100);
		while(receivedRP!= null){
			RP_count++;
			if(RP_count>=50){
				//setting RP_count on 49 so we can use the same method for inserting all of the messages
				RP_count=49;
				//if RP is full delete oldest item (RP[1]) and move all other items of the array one position up
				for(int i=1;i<=48;i++){	RP[i]=RP[i+1];	}
			}
			//setting all of the current RP to -1
			RP[RP_count]=RPdefault;
			//changing the -1 to the values that were sent from A
			for(int i=0; i< getItemCount(receivedRP.getCo());i++){	 
				RP[RP_count][number(receivedRP.getCo(), i+1, 1)]= number(receivedRP.getCo(),i+1,2);
			}
			
			Msg receive_temp = receive("AB", time()+100);
			//no message received... abort!
			if(receive_temp==null){break;}
			
			//received a message from B 
			else if(receive_temp.getTy()=='f' && receive_temp.getSe()=='B'){
				errorcount++;
				int[] RP_send =RPdefault;
				String RP_send_str="";
				while(IntStream.of(RP_send).anyMatch(x -> x== -1)){
					for(int i=0; i<=7;i++){	 
						if(RP_send[i] < RP[RP_count][i]){
							RP_send[i]= RP[RP_count][i];
					}
					RP_count--;
					}
				}
				
				
				for(int i=0; i<=7;i++){RP_send_str+= RP_send[i]+" "; }
				form('z', RP_send_str).send('A');
				//send RP to A
				
			}
			
			//received a RP from A
			else if (receive_temp.getTy()=='z' && receive_temp.getSe()=='A') {
				receivedRP=receive_temp;
			}
			
			
		}
			
		
		return errorcount+ " mal zurueckgesetzt";
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
			return 2; // Zu geringer/groﬂer Fortschritt.
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
