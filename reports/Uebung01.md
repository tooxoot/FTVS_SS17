# Übungsblatt 1
###### Redundanz

### Aufgabe 2:

InputWdh. | FehlerartFh. | KnotenFehler | Wahrsch. | new column |
- | - | - | - | -
1,BCD | ≥ 200 | Corrupt Content | BCDEF | new column
1,BCDEF | ≥ 200 | Corrupt Content | BCDEF
1,BCDEF | ≥ 200 | Fail Silence | BCDEF
2,BCD | ≥ 200 | Fail Silence | BCDEF
2,BCDEF | ≥ 200 | Fail Silence | BCDEF
2,BCDEF | ≥ 200 | Fail Omission | BCDEF
2,BCDEF | ≥ 200 | Corrupt Content | BCDEF
3,BCD | ≥ 200 | Fail Silence | BCDEF
3,BCD | ≥ 200 | Corrupt Content | BCDEF
4,BCD | ≥ 200 | Corrupt Content | BCDEF
4,BCDEF | ≥ 200 | Corrupt Content | BCDEF
4,BCDEF | ≥ 200 | Fail Silence | BCDEF
