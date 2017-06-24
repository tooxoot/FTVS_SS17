# Übungsblatt 2
###### Nobles, Justin
###### Vaupel, Sven

## Aufgabe 1:

Erhält C einen RP von A, so filtert C die veränderten Positionen aus dem empfangenen String und schreibt die erhaltenen neuen Werte an die entsprechenden Stellen des abgespeicherten Arrays.
Alle unveränderten Positionen werden mit -1 abgespeichert.
Sind bereits 49 RPs abgespeichert wird der Älteste gelöscht.

Bei der RP Anforderung von B wird angenommen, dass wirklich alle Schritte seit dem letzten korrekten RP wiederholt werden müssen.
Wird ein RP von B angefordert so werden zunächst solange RPs entfernt, bis der RP mit dem der korrekten Fortschrittsanzeige (An C gesendeter Fortschritt + 10) gefunden wird.
Aus diesem gespeicherten Array wird dann ein vollständiger RP gebildet indem für jede Stelle des Arrays der erste gespeicherte RP Wert abgerufen wird der ungleich -1 ist.
Der So konstruierte RP wird dann an A gesendet.

## Aufgabe 2:

_Siehe Code_

## Aufgabe 3:

| Fehler Wahrsch. | Wdh. | Durchschnittliche Rücksetzzahl | Result [%]: |  0  |  1  |  2  |  3  |  4  |
| --------------- | ---- |:-------------------------------:|:-----------:| --- | --- | --- | --- | --- |
| 0.00            | 200  |               0.0               |             | 100 | 0   | 0   | 0   | 0   |
| 0.01            | 200  |               0.7               |             | 45  | 55  | 0   | 0   | 0   |
| 0.02            | 200  |               1.6               |             | 17  | 82  | 1   | 0   | 0   |
| 0.05            | 200  |               4.0               |             | 3   | 97  | 0   | 0   | 0   |
| 0.01            | 200  |              15.7               |             | 0   | 99  | 1   | 0   | 0   |
