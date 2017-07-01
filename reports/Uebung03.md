# Übungsblatt 2
###### Nobles, Justin
###### Vaupel, Sven

## Aufgabe 1:

- Mehrheitsentscheidung
  `a = b = 3`

   Es kann abgebrochen werden, wenn eine Mehrheit gleicher Ergebnisse empfangen wurde (=> b).
   Wenn keine Mehrheit empfangen wurde kann nicht entschieden werden (=> a).


- Paarentscheidung
  `a = b = 2`

   Es kann abgebrochen werden, wenn ein Paar gleicher Ergebnisse empfangen wurde (=> b).
   Wenn kein einziges Paar empfangen wurde kann nicht entschieden werden (=> a).


- Meiststimmenentscheidung
  `a = 1`
  `b = 5`

   Es kann erst entschieden werden, wenn alle ergebnisse vorliegen (=> b).
   Bei einer relativen Mehrheit ist die Groesse der Mengen egal (=> a).


- Einstimmigkeitsentscheidung
  `a = b = 5`

   Es kann erst entschieden werden, wenn alle ergebnisse vorliegen (=> b).
   Fuer Einstimmigkeit muessen alle Ergebnisse vorliegen (=> a).


- Medianentscheidung
  `a = 1`
  `b = 5`

   Es kann erst entschieden werden, wenn alle ergebnisse vorliegen (=> b).
   Fuer Medianentscheidung sollen alle Ergebnisse mit einbezogen werden! (=> a).


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
