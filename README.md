# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Miriam Sarpong, s364766, s364766@oslomet.no


# Oppgavebeskrivelse

I oppgave 1 så gikk jeg frem ved å kopiere kildekoden fra 5.2.3 og gjorde litt endring i den ved at referanse foreldre
får riktig verdi i hver node. Slik at hver gang forrige node får "barn" blir den da en forelder node. 

I oppgave 2 så brukte gikk jeg først frem ved å lage en if-sjekk, for å sjekke om verdien ikke er i treet. 
Deretter ble det laget en teller verdi og en peker mot rotnoden. 
Ettersom at vi skal returnere antall forekomster av verdi treet, ble verdien og roten sammenlignet
for å så da sjekke om verdien er mindre (går til venstre) og om det er større (går til høyre). 
I tillegg sjekker den om det er duplikater. Tok litt inspirasjon fra seksjon 5.2.6, oppgave 2

I oppgave 3 så gikk jeg frem ved å lage en sjekk for parameteren p, også for å finne førstepostorden, ble det laget en while-loop for å så da
returnere p som førstepostorden node. Dette ble gjort for p sin høyre og venstre barn. For nestePostorden ble det sjekket
om p er den siste i postorden, og hvis det er det skal metoden returnere null.


I oppgave 4 så gikk jeg frem ved - ble ikke gjort

I oppgave 5 så gikk jeg frem ved - ble ikke gjort

I oppgave 6 så gikk jeg frem ved å lage metodene fjern, fjernAlle og nullstill. For fjern metoden brukte jeg
kildekoden i kompendiet, seksjon 5.2.8. Det eneste som ble endret på den er å sette riktig verdi for forelder pekkeren
etter at alle nodene er fjernet. For metoden fjernAlle ble det laget en hjelpevariabel for å
holde oversikt over antall verdier, og om vi ikke finner noe verdier ble det returnert 0. 
for å da fjerne veriden ble metoden public boolean fjern(T verdi) brukt. Hentet også inspirasjon fra kompendiet
I nullstill metoden var det greit å lage noe hjelpevariabler for å ha orden og oversikt, her ble førstePostorden metoden brukt,
for å fjerne første verdi i postorden. Sammen med metoden der, ble også metodene fjern og nestePostorden benyttet av seg. 
