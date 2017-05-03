package uebung00;

import SoFTlib.Node;

public abstract class AbstractNode extends Node {
	// Bildung der Signatur modulo mod;
	long mod = 10000;
	// Geheimer Schluessel-Faktor von Knoten A.
	long geheimFaktor = 2317;
	// Oeffentlicher Schluessel-Faktor von Knoten A.
	long oeffFaktor = 3917;
	// Oeffentliches Produkt von Knoten A
	long oeffProdukt = geheimFaktor * oeffFaktor % mod;
	


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