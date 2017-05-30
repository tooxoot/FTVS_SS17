# Übungsblatt 1
###### Redundanz

### Aufgabe 2:

Input | Wdh. | Fehlerart | Fh.Knoten | Fehler Wahrsch. | new column |
- | - | - | - | -
1,BCD | ≥ 200 | Corrupt Content | BCDEF | 0.1
1,BCDEF | ≥ 200 | Corrupt Content | BCDEF | 0.1
1,BCDEF | ≥ 200 | Fail Silence | BCDEF | 0.1
2,BCD | ≥ 200 | Fail Silence | BCDEF | 0.1
2,BCDEF | ≥ 200 | Fail Silence | BCDEF | 0.1
2,BCDEF | ≥ 200 | Fail Omission | BCDEF | 0.1
2,BCDEF | ≥ 200 | Corrupt Content | BCDEF | 0.1
3,BCD | ≥ 200 | Fail Silence | BCDEF | 0.1
3,BCD | ≥ 200 | Corrupt Content | BCDEF | 0.1
4,BCD | ≥ 200 | Corrupt Content | BCDEF | 0.1
4,BCDEF | ≥ 200 | Corrupt Content | BCDEF | 0.1
4,BCDEF | ≥ 200 | Fail Silence | BCDEF | 0.1
