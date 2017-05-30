# Übungsblatt 1
###### Redundanz

### Aufgabe 2:

\# | Input | Wdh. | Fehlerart | Fh.Knoten | Fehler Wahrsch. | Result [%]: | 0 | 1 | 2 | 3 | 4
-: | :- | :-: | :-: | :-: | :-: || :-: | :-: | :-: | :-: | :-:
01 | 1,BCD | 203 | Corrupt Content | BCDEF | 0.1 || 84 | 0 | 0 | 0 | 36
02 | 1,BCDEF | 226 | Corrupt Content | BCDEF | 0.1 || 94 | 0 | 0 | 0 | 6
03 | 1,BCDEF | 207 | Fail Silence | BCDEF | 0.1 || 90 | 0 | 0 | 0 | 10
04 | 2,BCD | 283 | Fail Silence | BCDEF | 0.1 || 36 | 53 | 0 | 0 | 11
05 | 2,BCDEF | 209 | Fail Silence | BCDEF | 0.1 || 34 | 65 | 0 | 0 | 1
06 | 2,BCDEF | 213 | Fail Omission | BCDEF | 0.1 || 100 | 0 | 0 | 0 | 0
07 | 2,BCDEF | 207 | Corrupt Content | BCDEF | 0.1 || 37 | 46 | 10 | 7 | 0
08 | 3,BCD | 219 | Fail Silence | BCDEF | 0.1 || 5 | 87 | 0 | 0 | 8
09 | 3,BCD | 247 | Corrupt Content | BCDEF | 0.1 || 5 | 80 | 0 | 0 | 15
10 | 4,BCD | 269 | Corrupt Content | BCDEF | 0.1 || 4 | 30 | 0 | 0 | 66
11 | 4,BCDEF | 208 | Corrupt Content | BCDEF | 0.1 || 0 | 67 | 0 | 0 | 33
12 | 4,BCDEF | 202 | Fail Silence | BCDEF | 0.1 ||  0 | 62 | 0 | 0 | 38

### Aufgabe 3:
