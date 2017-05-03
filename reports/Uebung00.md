## Uebung00:

> Fragen an den Korrekteur:
>> 1. Was ist der Unterschied zwischen Fehlertyp 1 und 2
>> 2. Was sind die Fehlertypen 8, 11 aund 12 
>> 3. Ist die Beschreibung zu Result 1 Fehlerhaft?
>> Wenn B nicht antwortet wird dieser Code ausgegeben obwohl die Nachricht von A fehlerfrei ist!
>> Dieser Code wird auch bei anderen Fehlern ausgegeben
### Aufgabe 1c:

|  **Fehler in Knoten** | **A** | **B** |
| :- | - | - |
|  **_1 fail silence_** | Code 3  | Code 1: Codebeschreibung fehlerhaft?! | 
|  **_2 fail omission_** | Code 1 | Code 1 |
|  **_3 corrupt sender field_** | Code 1 | Code 1 |
|  **_4 corrupt receiver field_** | Code 0: receiver AB| Code 0: receiver AB |
||Code 1: receiver A| Code 1: receiver B |
|  **_5 corrupt Type field_** | Code 1 | Code 1 |
|  **_6 corrupt contents field_** | Code 2: Fehler in Signatur / Trennzeichen | Code 0 |
||Code 4: Sonst2||
|  **_7 corrupt signature field_** | Code 0 | Code 0 |
|  **_8 unjustified forwarding_** | Code 0| Code 0 |
|  **_9 duplicate fault_** | Code 0 | Code 0 |
|  **_10 delay fault_** | Code 1 | Code 1 |
|  **_11 benign Byzantine fault_** | Code 0 | Code 0 |
|  **_12 malicious Byzantine fault_** | Code 0 |  Code 0 |


### Aufgabe 2b:

|  **Fehler in Knoten** | **A** | **B** |
| - | - | - |
|  **_1 fail silence_** | Code 3  | | 
|  **_2 fail omission_** | Code 1 | |
|  **_3 corrupt sender field_** | Code 1 | |
|  **_4 corrupt receiver field_** | Code 0: receiver AB| |
||Code 1: receiver A||
|  **_5 corrupt Type field_** | Code 1 | |
|  **_6 corrupt contents field_** | Code 2: Fehler in Signatur / Trennzeichen | |
||Code 4: Sonst2||
|  **_7 corrupt signature field_** | Code 0 | |
|  **_8 unjustified forwarding_** | Code 0| |
|  **_9 duplicate fault_** | Code 0 | |
|  **_10 delay fault_** | Code 1 | |
|  **_11 benign Byzantine fault_** | Code 0 | |
|  **_12 malicious Byzantine fault_** | Code 0 | |


~~~
Fehlercodes:
0 = Erste Nachricht von A ist ok.
1 = Erste Nachr. von A kommt nicht an, zweite ist ok.
2 = Erste Nachr. von A kommt falsch an, zweite ist ok.
3 = Empfaenger B erhaelt keine korrekte Nachricht von A.
4 = Empf. B entscheidet sich fuer einen falschen Inhalt.
5 = nicht benutzt.