# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Jenny Liang Nergård, S312980, s312980@oslomet.no
* Gro Elisabeth Oleivsgard, S344222, s344222@oslomet.no

# Arbeidsfordeling

*(2 pers på gruppe skal gjøre: Oppgave 1, 2, 3, 4, 5, 6, 7, 8. (fjern test av oppgave 9 og 10))*

I oppgaven har vi hatt følgende arbeidsfordeling:
* Jenny har hatt hovedansvar for oppgave 1, 3, 5 og 7. 
* Gro har hatt hovedansvar for oppgave 2, 4, 6 og 8.

# Oppgavebeskrivelse

I oppgave (1) lagde vi en standardkonstruktør som oppretter en tom tabell med pekere, og en kontruktør som gjør det 
mulig å opprette en tabell med noder, pekere og med (n) lengde. DobbeltLenketListe(T[] a) tar inn verdier og setter de inn
i tabellen fra halen, mot venstre. Dereetter lagde vi metodene antall() og tom(). antall() teller hvor mange verdier 
som finnes i listen som ikke er 'null'. tom() bruker metoden antall() for å finne ut om listen er tom. Metoden er 
boolean og returerer 'true' dersom listen er tom.

I oppgave (2) så brukte vi en StringBuilder til å sette sammen strenger i et array format, hvor vi brukte append til å 
legge til verdiene i strengen. I toString() sjekker vi om listen er tom ved å sjekke om en midlertidig node-peker satt 
til hode er null. Dersom dette er tilfellet, utføres en while-løkke, hvor det for hver gjennomgang sjekkes for om den 
neste noden er null. Hvis den er det, settes hasNext til false, og verdien legges inn i strengen. Siden hasNext må være 
true for å gå gjennom løkken igjen, vil den gå ut av løkken. Dersom node-pekeren ikke er null, printes verdien til 
stringen, og node-pekeren flyttes til neste node. I omvendtString() prøvde vi oss med tom()-metoden for å sjekke om 
listen er tom. Dersom den ikke er det, settes node-pekeren til halen, denne verdien skrives ut til strengen, og pekeren 
flyttes til den forrige pekeren. Deretter kjøres det gjennom en while-løkke så lenge node-pekeren ikke er tom, og noden 
flyttes til den forrige. 

I oppgave (3a) opprettet vi metoden public T hent(int indeks) den skal ved hjelp av hjelpemetoden finnNode() finne
verdien på noden med gitt indeks og returnere verdien. Deretter opprettet vi en ny metode 
T oppdater(int indeks, T nyVerdi). Den skal finne en verdi (gammelVerdi) på en gitt indeks og erstatte verdien med en 
ny verdi. Det oppdateres at det er blitt gjort en endring, men siden tabellen har samme lengde endres ikke antall.

I oppgave (3b) opprettet vi metoden Liste<T> subliste(int fra,int til). Denne skal opprette en tom subliste som skal 
kopiere verdier fra en annen liste, men kun i intervallet mellom [fra, til> (ikke inkludere 'til'). Vi opprettet noden 
'start' som er den første noden som legges inn i listen. Vi la inn en fraTilKontroll(antall, fra, til) for å passe på at 
indeksene og antall ikke er utenfor grensene.

I oppgave (4) satt vi nodepekeren q til hode. Først sjekkes det om verdien er null, isåfall returneres -1. Hvis verdien 
ikke er null, løpes det gjennom en for-løkke. Her er grensene satt til at nodepekeren q ikke skal være null, og 
posisjonen (tilsvarende int i) skal økes for hver gang. Når verdien er funnet ved gjennomløpingen (q.verdi == verdi), 
returneres posisjonen. Frem til dette flyttes pekeren til neste for hver gang. Dersom verdien ikke blir funnet, 
returneres til slutt -1. 

I oppgave (5) lagde vi en metode som legger inn og oppdaterer en tabell. Vi oppretter en 'nyNode' som settes inn på en 
gitt indeks. Den "tar over" plassen til 'gammelNode' og forsyver 'gammelNode' mot høyre slik at 'gammelNode' blir neste 
på listen. Antall verdier og endringer som er gjort blir oppdatert.

I oppgave (6) så har vi laget metodene boolean fjern(T verdi) og T fjern(int indeks). I boolean fjern(T verdi) har vi 
opprettet en hjelpevariabel p satt til hode. Vi setter boolean funnet til false. Dersom listen er tom, blir dette 
fanget opp i første if-setning, og false blir returnert. Hvis ikke, startes en while-løkke, hvor betingelsen er satt 
til at verdi ikke skal være lik null. Dersom verdi ikke er null, sjekkes det for om pekeren sin verdi er lik verdi. 
Isåfall er verdien funnet, og løkken avsluttes. Hvis ikke, flyttes pekeren til neste. Hvis verdien blir funnet, sjekkes 
det om denne verdien er hode, hale, eller mellom disse. Noden fjernes ved å justere pekerne. Deretter økes endringer, 
og antall reduseres. I T fjern(int indeks), sjekker vi først med en indekskontroll om indeksen er innenfor lovlige 
grenser. Deretter blir det opprettet en hjelpevariabel fjernIndex. Det blir først sjekket for om listen kun har en 
verdi (antall == 1), deretter om det er hode som skal fjernes (indeks == 0), eller om det er halen som skal fjernes 
(indeks == antall-1). Hvis ingen av disse er tilfellet, er indeksen et sted mellom hode og hale. Da opprettes en node 
"current", som settes lik noden til indeksen ved å kalle på metoden finnNode(indeks). Pekerne til current endres for å 
fjerne denne noden. Til slutt reduseres antall og endringer økes, og indeksen til den noden som er fjernet returneres. 

I oppgave (7.1) laget vi en metode som nullstiller alle verdier i tabellen ved å få nodene til å peke til 'null', en 
etter en. Vi starter med 'hode' noden og fjerner alle pekere slik at alle nodene peker på null. I oppgave (7.2) bygget 
vi videre på fjern metoden ved å lage en løkke som fjerner en og en verdi. Etterpå sammenliknet vi hvor raske de 2
ulike måtene var:

1. måte: BUILD SUCCESSFUL in 2s, 3 actionable tasks: 1 executed, (test passed - 27 ms)
2. måte: BUILD SUCCESSFUL in 2s, 3 actionable tasks: 1 executed, (test passed - 32 ms)
