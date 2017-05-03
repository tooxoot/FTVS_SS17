package uebung00;

public abstract class AbstractNode2b extends AbstractNode{
	// Bildung der Signatur modulo mod;
	long mod = 10000;
	// Geheimer Schluessel-Faktor von Knoten A.
	long geheimFaktor = 2317;
	// Oeffentlicher Schluessel-Faktor von Knoten A.
	long oeffFaktor = 3917;
	// Oeffentliches Produkt von Knoten A
	long oeffProdukt = geheimFaktor * oeffFaktor % mod;
	
		
	public long komprimiere(String inhalt) {
		long k = 0;
		if (inhalt != null)
		for (int i = 0; i < inhalt.length(); i++) {
		k = 128 * k + inhalt.charAt(i) % 128;
		k %= 8357;
		}
		return k;
	}

	@Override
	public String signiere(String sender, String inhalt) {
		long signatur = (komprimiere(inhalt) * geheimFaktor) % mod;
		return String.valueOf(signatur);
	}

}
