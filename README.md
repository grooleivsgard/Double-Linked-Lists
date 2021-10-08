# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Jenny Liang Nergård, S312980, s312980@oslomet.no
* Gro Elisabeth Oleivsgard, S344222, s344222@oslomet.no

# Arbeidsfordeling

*(2 pers på gruppe skal gjøre: Oppgave 1, 2, 3, 4, 5, 6, 7, 8. (fjern test av oppgave 9 og 10))*

I oppgaven har vi hatt følgende arbeidsfordeling:
* Jenny har hatt hovedansvar for oppgave 1, 3, og 5. 
* Gro har hatt hovedansvar for oppgave 2, 4, og 6. 
* Vi har i fellesskap løst oppgave 7 og 8. 

# Oppgavebeskrivelse

I oppgave (1) så gikk vi frem ved å gå igjennom liste 'a' og opprette to midlertidige pekere. Først sjekket vi om vi 
var på første indeks og la inn første verdi. Deretter la vi inn de neste tallene fra halen mot venstre slik at 
'stringListe' skulle printes ut riktig. Vi teller antall() kun når vi la inn verdier som ikke var null. Tom() vil 
returnere 'true' dersom det er sant at listen er tom eller bare inneholder null-verdier.

I oppgave (2) så brukte vi en StringBuilder til å sette sammen strenger i et array format, hvor vi brukte append til å legge til verdiene i strengen. I toString() sjekker vi om listen er tom ved å sjekke om en midlertidig node-peker satt til hode er null. Dersom dette er tilfellet, utføres en while-løkke, hvor det for hver gjennomgang sjekkes for om den neste noden er null. Hvis den er det, settes hasNext til false, og verdien legges inn i strengen. Siden hasNext må være true for å gå gjennom løkken igjen, vil den gå ut av løkken. Dersom node-pekeren ikke er null, printes verdien til stringen, og node-pekeren flyttes til neste node. I omvendtString() prøvde vi oss med tom()-metoden for å sjekke om listen er tom. Dersom den ikke er det, settes node-pekeren til halen, denne verdien skrives ut til strengen, og pekeren flyttes til den forrige pekeren. Deretter kjøres det gjennom en while-løkke så lenge node-pekeren ikke er tom, og noden flyttes til den forrige. 

I oppgave (3)...

I oppgave (4) satt vi nodepekeren q til hode. Først sjekkes det om verdien er null, isåfall returneres -1. Hvis verdien ikke er null, løpes det gjennom en for-løkke. Her er grensene satt til at nodepekeren q ikke skal være null, og posisjonen (tilsvarende int i) skal økes for hver gang. Når verdien er funnet ved gjennomløpingen (q.verdi == verdi), returneres posisjonen. Frem til dette flyttes pekeren til neste for hver gang. Dersom verdien ikke blir funnet, returneres til slutt -1. 


I oppgave (5)...


I oppgave (6) så har vi laget metodene boolean fjern(T verdi) og T fjern(int indeks). I boolean fjern(T verdi) har vi opprettet en hjelpevariabel p satt til hode. Vi setter boolean funnet til false. Dersom listen er tom, blir dette fanget opp i første if-setning, og false blir returnert. Hvis ikke, startes en while-løkke, hvor betingelsen er satt til at verdi ikke skal være lik null. Dersom verdi ikke er null, sjekkes det for om pekeren sin verdi er lik verdi. Isåfall er verdien funnet, og løkken avsluttes. Hvis ikke, flyttes pekeren til neste. Hvis verdien blir funnet, sjekkes det om denne verdien er hode, hale, eller mellom disse. Noden fjernes ved å justere pekerne. Deretter økes endringer, og antall reduseres. I T fjern(int indeks), sjekker vi først med en indekskontroll om indeksen er innenfor lovlige grenser. Deretter blir det opprettet en hjelpevariabel fjernIndex. Det blir først sjekket for om listen kun har en verdi (antall == 1), deretter om det er hode som skal fjernes (indeks == 0), eller om det er halen som skal fjernes (indeks == antall-1). Hvis ingen av disse er tilfellet, er indeksen et sted mellom hode og hale. Da opprettes en node "current", som settes lik noden til indeksen ved å kalle på metoden finnNode(indeks). Pekerne til current endres for å fjerne denne noden. Til slutt reduseres antall og endringer økes, og indeksen til den noden som er fjernet returneres. 
