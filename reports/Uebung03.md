# Übungsblatt 2
###### Nobles, Justin
###### Vaupel, Sven

## Aufgabe 1:

- Mehrheitsentscheidung
  `a = b = 3`

   Es kann entschieden werden, sobald eine absolute Mehrheit empfangen wurde (&#8658; b).
   Wenn keine absolute Mehrheit vorliegt, kann keine Entscheidung gefällt werden (&#8658; a).


- Paarentscheidung
  `a = b = 2`

   Es kann entschieden werden, sobald ein Paar gleicher Ergebnisse empfangen wurde (&#8658; b).
   Wenn kein einziges Paar vorliegt,kann keine Entscheidung gefällt werden (&#8658; a).


- Meiststimmenentscheidung
  `a = 1`
  `b = 3`

   Es kann entschieden werden, sobald eine absolute Mehrheit empfangen wurde (&#8658; b).
   Bei einer relativen Mehrheit ist die Größe der Mengen egal (&#8658; a).


- Einstimmigkeitsentscheidung
  `a = b = 5`

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
  | fail omission          | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          3           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | corrupt sender field   | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | corrupt contents field | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          3           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | duplication fault      | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | delay fault            | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          4           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | combination of faults  | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          4           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |


### Paarentscheidung
  `a = b = 2`

  |       Fehlerart        | Wdh. | Fehlerhafte Knoten f | Result [%]: |   0   |   1   |   2   |   3   |   4   |       |
  | ---------------------- | ---- |:--------------------:|:-----------:| ----- | ----- | ----- | ----- | ----- | ----- |
  | fail omission          | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          3           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | corrupt sender field   | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | corrupt contents field | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          3           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | duplication fault      | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | delay fault            | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          4           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | combination of faults  | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          4           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |

### Meiststimmenentscheidung
  `a = 1`
  `b = 5`

  |       Fehlerart        | Wdh. | Fehlerhafte Knoten f | Result [%]: |   0   |   1   |   2   |   3   |   4   |       |
  | ---------------------- | ---- |:--------------------:|:-----------:| ----- | ----- | ----- | ----- | ----- | ----- |
  | fail omission          | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          3           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | corrupt sender field   | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | corrupt contents field | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          3           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | duplication fault      | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | delay fault            | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          4           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | combination of faults  | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          4           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |

### Einstimmigkeitsentscheidung
  `a = b = 5`

  |       Fehlerart        | Wdh. | Fehlerhafte Knoten f | Result [%]: |   0   |   1   |   2   |   3   |   4   |       |
  | ---------------------- | ---- |:--------------------:|:-----------:| ----- | ----- | ----- | ----- | ----- | ----- |
  | fail omission          | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          3           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | corrupt sender field   | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | corrupt contents field | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          3           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | duplication fault      | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | delay fault            | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          4           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | combination of faults  | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          4           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |

### Medianentscheidung
  `a = 1`
  `b = 5`

  |       Fehlerart        | Wdh. | Fehlerhafte Knoten f | Result [%]: |   0   |   1   |   2   |   3   |   4   |       |
  | ---------------------- | ---- |:--------------------:|:-----------:| ----- | ----- | ----- | ----- | ----- | ----- |
  | fail omission          | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          3           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | corrupt sender field   | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | corrupt contents field | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          3           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | duplication fault      | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | delay fault            | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          4           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  | combination of faults  | 200  |          2           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
  |                        |      |          4           |             | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 | 00.00 |
