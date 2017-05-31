# Übungsblatt 1
###### Nobles, Justin
###### Vaupel, Sven

## Aufgabe 2:

##### Testergebnisse:

| # | Input | Wdh. | Fehlerart | Fh.Knoten | Fehler Wahrsch. | Result [%]: | 0 | 1 | 2 | 3 | 4 |
| -: | :- | :-: | :-: | :-: | :-: | -: | :-: | :-: | :-: | :-: | :-: |
| 01 | 1,BCD | 203 | Corrupt Content | BCDEF | 0.1 |  | 84 | 0 | 0 | 0 | 36 |
| 02 | 1,BCDEF | 226 | Corrupt Content | BCDEF | 0.1 |  | 94 | 0 | 0 | 0 | 6 |
| 03 | 1,BCDEF | 207 | Fail Silence | BCDEF | 0.1 |  | 90 | 0 | 0 | 0 | 10 |
| 04 | 2,BCD | 283 | Fail Silence | BCDEF | 0.1 |  | 36 | 53 | 0 | 0 | 11 |
| 05 | 2,BCDEF | 209 | Fail Silence | BCDEF | 0.1 |  | 34 | 65 | 0 | 0 | 1 |
| 06 | 2,BCDEF | 213 | Fail Omission | BCDEF | 0.1 |  | 100 | 0 | 0 | 0 | 0 |
| 07 | 2,BCDEF | 207 | Corrupt Content | BCDEF | 0.1 |  | 37 | 46 | 10 | 7 | 0 |
| 08 | 3,BCD | 219 | Fail Silence | BCDEF | 0.1 |  | 5 | 87 | 0 | 0 | 8 |
| 09 | 3,BCD | 247 | Corrupt Content | BCDEF | 0.1 |  | 5 | 80 | 0 | 0 | 15 |
| 10 | 4,BCD | 269 | Corrupt Content | BCDEF | 0.1 |  | 4 | 30 | 0 | 0 | 66 |
| 11 | 4,BCDEF | 208 | Corrupt Content | BCDEF | 0.1 || 0 | 67 | 0 | 0 | 33 |
| 12 | 4,BCDEF | 202 | Fail Silence | BCDEF | 0.1 |  |  0 | 62 | 0 | 0 | 38 |

##### Auswertung:

## Aufgabe 3:


#### Welche Redundanztechniken kommen in den einzelnen Betriebsarten zum Einsazt?

Modus 1: Hier wird eine statische strukturelle Redundanztechnik verwendet. Es werden während des gesamten Einsatzzeitraumes die Redundanten Mittel aktiv verwendet.
			Es handelt sich hierbei im ein n-von-m-System, da bei erhalt der Nachrichten der aktiven Knoten geprüft wird welche Nachricht am häufigsten angekommen ist und diese wird als richtig eingestuft.

Modus 2:	Bei Modus 2 werden mehrere dynamische Redundanztechniken verwendet.
			Im Fehlerfall wird zuerst dynamisch funktionelle Redundanz angewendet, weil die Nachricht erneut an den gleichen Rechner gesendet wird.
			Falls diese Nachricht ebenfalls nicht vom Empfänger korrekt verarbeitet werden kann greift die dynamisch strukturelle Redundanz. Es wird von dem primären Rechner auf einen im Input eingegeben Ersatzrechner umgeschaltet, falls dieser noch ff ist.

Modus 3:	Hier wurde eine strukturelle Hybridredundanz implementiert, da zu den redundant genutzten Primärrechnern Ersatzrechner vorliegen, die im Fehlerfall integriert werden.

Modus 4:	Selbstreinigende Redundanz, da aus den Ausgangsrechnern fehlerhafte Rechner ausgeschlossen werden.

#### Welcher weiteren Vor- und Nachteile  haben die einzelnen Redundanztechniken, die nicht unmittelbar aus den Simulationsergebnissen ersichtlich sind?

Modus 1:	Problematisch bei statischer struktureller Redundanz ist, dass bspw. Alle Rechner dasselbe falsche Ergebnis liefern können, welches aufgrund der Mehrheitsentscheidung als richtig eingestuft wird.

Modus 2:	 

Modus 3:

Modus 4:

##### Gibt es Betriebsarten, die unter eingescchränkten Fehlerannahmen alle Fehler tolerieren und wenn ja, welche und unter welchen Fehlerannahmen?

Modus 1: Unter der Annahme, dass nicht mehr als 2 Rechner fh werden, können bei einer Eingabe von min. 5 Rechnern alle Fehlerarten mit Auftrittswahrscheinlichekeiten bis zu 100% toleriert.

Modus 2: Unter der Annahme, dass nicht mehr als 4 Rechner fh werden, können die Fehler fail silence und fail omission zu 100% toleriert werden. Corrupt Content kann aufgrund des schlechten Absoluttests nicht immer toleriert werden. Falls dieser immer richtig liegen würde, könnte auch dieser Fehler zu 100% toleriert werden.

Modus 3: Unter der Annahme, dass nicht mehr als 2 Rechner fh werden und zu Beginn eine absolute Mehrheit an ff Rechnern vorhanden ist, können alle Fehler zu 100% toleriert werden.

Modus 4: Unter der Annahme, dass nicht mehr als 3 Rechner fh werden und diese nicht zum gleichen Zeitpunkt fh werden, können in dieser Betriebsart alle Fehlertypen toleriert werden.
