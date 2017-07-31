# Übungsblatt 2
###### Nobles, Justin
###### Vaupel, Sven

## Aufgabe 1:

- Mehrheitsentscheidung
  `a = b = 3`
  <!-- korrekt -->
   Es kann entschieden werden, sobald eine absolute Mehrheit empfangen wurde (&#8658; b).
   Wenn keine absolute Mehrheit vorliegt, kann keine Entscheidung gefällt werden (&#8658; a).


- Paarentscheidung
  `a = b = 2`
  <!-- korrekt -->
   Es kann entschieden werden, sobald ein Paar gleicher Ergebnisse empfangen wurde (&#8658; b).
   Wenn kein einziges Paar vorliegt,kann keine Entscheidung gefällt werden (&#8658; a).


- Meiststimmenentscheidung
  `a = 1`
  `b = 3`
  <!--  -->
   Es kann entschieden werden, sobald eine absolute Mehrheit empfangen wurde (&#8658; b).
   Bei einer relativen Mehrheit ist die Größe der Mengen egal (&#8658; a).


- Einstimmigkeitsentscheidung
  `a = b = 5`
  <!-- korrekt -->
   Es kann erst entschieden werden, wenn alle Ergebnisse empfangen wurde (&#8658; b).
   Für Einstimmigkeit müssen alle Ergebnisse vorliegen (&#8658; a).


- Medianentscheidung
  `Nicht Möglich`

  In eine Medianentscheidung müssen alle Ergebnisse einfließen. Daher müsste `b = 5` gesetzt werden.
  Für alle Kombinationen mit `a` werden durch die Verwendung von `max(a,c)` Ergebnisse aussortiert, falls mehrere gleiche Ergebnisse empfangen wurden. Daraus folgt, dass in Einzelfällen keine echte  Medianentscheidung gefällt wird.
  Ein Beispielmenge für einen solchen Fall wäre:
  `{ {2017} , {218} , {2018, 2018} , {2019} }`

## Aufgabe 2:

_Siehe Code_

## Aufgabe 3:

### Mehrheitsentscheidung
  `a = b = 3`

  |       Fehlerart        | Wdh. | Fehlerhafte Knoten f | Result [%]: |   0   |   1   |   2   |   3   |   4   |       |
  | ---------------------- | ---- |:--------------------:|:-----------:| ----- | ----- | ----- | ----- | ----- | ----- |
  | fail omission          | 200  |          2           |             | 100.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          3           |             | 74.50 | 25.50 | 00.00 | 00.00 | 00.00 | 00.00 |
  | corrupt sender field   | 200  |          2           |             | 100.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | corrupt contents field | 200  |          2           |             | 100.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          3           |             | 79.50 | 20.50 | 00.00 | 00.00 | 00.00 | 00.00 |
  | duplication fault      | 200  |          2           |             | 100.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | delay fault            | 200  |          2           |             | 100.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          4           |             | 52.00 | 36.00 | 12.00 | 00.00 | 00.00 | 00.00 |
  | combination of faults  | 200  |          2           |             | 100.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          4           |             | 75.50 | 23.00 | 1.50 | 00.00 | 00.00 | 00.00 |


### Paarentscheidung
  `a = b = 2`

  |       Fehlerart        | Wdh. | Fehlerhafte Knoten f | Result [%]: |   0   |   1   |   2   |   3   |   4   |       |
  | ---------------------- | ---- |:--------------------:|:-----------:| ----- | ----- | ----- | ----- | ----- | ----- |
  | fail omission          | 200  |          2           |             | 100.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          3           |             | 100.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | corrupt sender field   | 200  |          2           |             | 100.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | corrupt contents field | 200  |          2           |             | 85.50 | 14.50 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          3           |             | 67.50 | 29.00 | 01.50 | 02.00 | 00.00 | 00.00 |
  | duplication fault      | 200  |          2           |             | 100.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | delay fault            | 200  |          2           |             | 100.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          4           |             | 88.50 | 11.50 | 00.00 | 00.00 | 00.00 | 00.00 |
  | combination of faults  | 200  |          2           |             | 100.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          4           |             | 96.00 | 04.00 | 00.00 | 00.00 | 00.00 | 00.00 |

### Meiststimmenentscheidung
  `a = 1`
  `b = 5`

  |       Fehlerart        | Wdh. | Fehlerhafte Knoten f | Result [%]: |   0   |   1   |   2   |   3   |   4   |       |
  | ---------------------- | ---- |:--------------------:|:-----------:| ----- | ----- | ----- | ----- | ----- | ----- |
  | fail omission          | 200  |          2           |             | 100.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          3           |             | 100.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | corrupt sender field   | 200  |          2           |             | 100.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | corrupt contents field | 200  |          2           |             | 100.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          3           |             | 84.00 | 16.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | duplication fault      | 200  |          2           |             | 100.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | delay fault            | 200  |          2           |             | 100.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          4           |             | 100.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | combination of faults  | 200  |          2           |             | 100.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          4           |             | 97.00 | 03.00 | 00.00 | 00.00 | 00.00 | 00.00 |

### Einstimmigkeitsentscheidung
  `a = b = 5`

  |       Fehlerart        | Wdh. | Fehlerhafte Knoten f | Result [%]: |   0   |   1   |   2   |   3   |   4   |       |
  | ---------------------- | ---- |:--------------------:|:-----------:| ----- | ----- | ----- | ----- | ----- | ----- |
  | fail omission          | 200  |          2           |             | 04.50 | 37.00 | 58.50 | 00.00 | 00.00 | 00.00 |
  |                        |      |          3           |             | 03.00 | 24.00 | 73.00 | 00.00 | 00.00 | 00.00 |
  | corrupt sender field   | 200  |          2           |             | 37.50 | 51.50 | 11.00 | 00.00 | 00.00 | 00.00 |
  | corrupt contents field | 200  |          2           |             | 07.50 | 39.00 | 53.50 | 00.00 | 00.00 | 00.00 |
  |                        |      |          3           |             | 01.00 | 23.50 | 75.50 | 00.00 | 00.00 | 00.00 |
  | duplication fault      | 200  |          2           |             | 100.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | delay fault            | 200  |          2           |             | 08.50 | 29.50 | 62.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          4           |             | 01.00 | 17.00 | 82.00 | 00.00 | 00.00 | 00.00 |
  | combination of faults  | 200  |          2           |             | 19.50 | 43.50 | 37.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          4           |             | 02.50 | 30.00 | 67.50 | 00.00 | 00.00 | 00.00 |
