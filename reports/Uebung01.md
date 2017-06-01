# Übungsblatt 1
###### Nobles, Justin
###### Vaupel, Sven

## Aufgabe 2:

##### Testergebnisse:
| #  | Input   | Wdh. | Fehlerart       | Fh.Knoten | Fehler Wahrsch. | Result [%]: | 0   | 1   | 2   | 3   | 4   |
|---:|:--------|:----:|:---------------:|:---------:|:---------------:|------------:|:---:|:---:|:---:|:---:|:---:|
| 01 | 1,BCD   | 400  | Corrupt Content | BCDEF     | 0.1             |             | 3   | 69  | 13  | 0   | 15  |
| 02 | 1,BCDEF | 400  | Corrupt Content | BCDEF     | 0.1             |             | 1   | 92  | 2   | 0   | 5   |
| 03 | 1,BCDEF | 200  | Fail Silence    | BCDEF     | 0.1             |             | 1   | 92  | 0   | 0   | 7   |
| 04 | 2,BCD   | 283  | Fail Silence    | BCDEF     | 0.1             |             | 36  | 53  | 0   | 0   | 11  |
| 05 | 2,BCDEF | 209  | Fail Silence    | BCDEF     | 0.1             |             | 34  | 65  | 0   | 0   | 1   |
| 06 | 2,BCDEF | 213  | Fail Omission   | BCDEF     | 0.1             |             | 100 | 0   | 0   | 0   | 0   |
| 07 | 2,BCDEF | 207  | Corrupt Content | BCDEF     | 0.1             |             | 37  | 46  | 10  | 7   | 0   |
| 08 | 3,BCD   | 400  | Fail Silence    | BCDEF     | 0.1             |             | 5   | 90  | 0   | 0   | 5   |
| 09 | 3,BCD   | 400  | Corrupt Content | BCDEF     | 0.1             |             | 6   | 70  | 11  | 0   | 13  |
| 10 | 4,BCD   | 400  | Corrupt Content | BCDEF     | 0.1             |             | 5   | 27  | 5   | 0   | 63  |
| 11 | 4,BCDEF | 400  | Corrupt Content | BCDEF     | 0.1             |             | 0.5 | 59  | 7.5 | 0   | 33  |
| 12 | 4,BCDEF | 400  | Fail Silence    | BCDEF     | 0.1             |             | 0   | 61  | 0   | 0   | 39  |


##### Auswertung:

Auffällig ist vor allem die hundert prozentige Fehlerfreiheit von Test Nr. 6. Offenbar sind hier Nachrichtenausfälle nie direkt aufeinander folgend aufgetreten.

Außerdem ist auffällig, dass Ergebnis 3 in fast allen Tests ausbleibt. Dies ist allerdings mit der geringen Wahrscheinlichkeit zu erklären, dass nur unerkannte Fehler auftreten.


## Aufgabe 3:


#### Welche Redundanztechniken kommen in den einzelnen Betriebsarten zum Einsazt?
- Modus 1

  Hier wird eine statische strukturelle Redundanztechnik verwendet. Es werden während des gesamten Einsatzzeitraumes die Redundanten Mittel aktiv verwendet. Es handelt sich hierbei im ein n-von-m-System, da bei erhalt der Nachrichten der aktiven Knoten geprüft wird welche Nachricht am häufigsten angekommen ist und diese wird als richtig eingestuft.

- Modus 2

  Bei Modus 2 werden mehrere dynamische Redundanztechniken verwendet.
Im Fehlerfall wird zuerst dynamisch funktionelle Redundanz angewendet, weil die Nachricht erneut an den gleichen Rechner gesendet wird.
Falls diese Nachricht ebenfalls nicht vom Empfänger korrekt verarbeitet werden kann greift die dynamisch strukturelle Redundanz. Es wird von dem primären Rechner auf einen im Input eingegeben Ersatzrechner umgeschaltet, falls dieser noch ff ist.

- Modus 3

  Hier wurde eine strukturelle Hybridredundanz implementiert, da zu den redundant genutzten Primärrechnern Ersatzrechner vorliegen, die im Fehlerfall integriert werden.

- Modus 4

  Selbstreinigende Redundanz, da aus den Ausgangsrechnern fehlerhafte Rechner ausgeschlossen werden.


#### Welcher weiteren Vor- und Nachteile  haben die einzelnen Redundanztechniken, die nicht unmittelbar aus den Simulationsergebnissen ersichtlich sind?

- Modus 1

  Problematisch bei statischer struktureller Redundanz ist, dass bspw. Alle Rechner dasselbe falsche Ergebnis liefern können, welches aufgrund der Mehrheitsentscheidung als richtig eingestuft wird.

- Modus 2

  Vorteil bei Modus 2 ist, dass selbst mit nur einem ff Rechner die volle Funktionalität zur Verfügung steht und selbst mit diesem einen Rechner gewisse Fehler toleriert werden, da bei einem einmaligen Fehler eine Sendewiederholung erfolgt.
  
  Nachteilig ist hierbei, dass für jeden erkannten Fehler erneut gesendet werden muss -  was natürlich Zeit kostet.

- Modus 3

  Der Nachteil von Modus 3 ist identisch zu dem in Modus 1 wobei er hier noch gravierender ist, da dann die ff Rechner ausgegliedert und durch andere Rechner ersetzt werden.

  Vorteilhaft ist allerdings, dass kein Zeitverlust erlitten wird falls es zu fehlerhaften Ergebnisen kommt.

- Modus 4

  Der Nachteil von Modus 4 ist identisch zu dem in Modus 1 & 3 wobei er hier noch gravierender ist, da dann die ff Rechner ausgegliedert werden und nur mit fh Rechner weitergearbeitet wird.


#### Gibt es Betriebsarten, die unter eingescchränkten Fehlerannahmen alle Fehler tolerieren und wenn ja, welche und unter welchen Fehlerannahmen?

- Modus 1

  Unter der Annahme, dass nicht mehr als 2 Rechner fh werden, können bei einer Eingabe von min. 5 Rechnern alle Fehlerarten mit Auftrittswahrscheinlichekeiten bis zu 100% toleriert.

- Modus 2

  Unter der Annahme, dass nicht mehr als 4 Rechner fh werden, können die Fehler fail silence und fail omission zu 100% toleriert werden. Corrupt Content kann aufgrund des schlechten Absoluttests nicht immer toleriert werden. Falls dieser immer richtig liegen würde, könnte auch dieser Fehler zu 100% toleriert werden.

- Modus 3

  Unter der Annahme, dass nicht mehr als 2 Rechner fh werden und zu Beginn eine absolute Mehrheit an ff Rechnern vorhanden ist, können alle Fehler zu 100% toleriert werden.


- Modus 4

  Unter der Annahme, dass nicht mehr als 3 Rechner fh werden und diese nicht zum gleichen Zeitpunkt fh werden, können in dieser Betriebsart alle Fehlertypen toleriert werden.
