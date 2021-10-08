package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;


public class DobbeltLenketListe<T> implements Liste<T> {

    //// MAIN TEST ///////////////////////////////////////////////////////
    // main-metode til testing av oppgavene. Slettes før oppgaven leveres!
    public static void main(String[] args) {

        /*

        //// Oppgave 1 ///////////////////////////////////////////////////
        Liste<String> liste1a = new DobbeltLenketListe<>();
        System.out.println(liste1a.antall() + " " + liste1a.tom());
        // Utskrift: 0 true

        String[] s = {"Ole", null, "Per", "Kari", null};
        Liste<String> liste1b = new DobbeltLenketListe<>(s);
        System.out.println(liste1b.antall() + " " + liste1b.tom());
        // Utskrift: 3 false


        //// Oppgave 2 a) ///////////////////////////////////////////////////
        String[] s1 = {}, s2 = {"A"}, s3 = {null,"A",null,"B",null};
        DobbeltLenketListe<String> l1 = new DobbeltLenketListe<>(s1);
        DobbeltLenketListe<String> l2 = new DobbeltLenketListe<>(s2);
        DobbeltLenketListe<String> l3 = new DobbeltLenketListe<>(s3);
        System.out.println(l1.toString() +" "+ l2.toString()+" "+ l3.toString() +" "+
                l1.omvendtString()+" "+ l2.omvendtString() +" "+ l3.omvendtString());
        // Utskrift: [] [A] [A, B] [] [A] [B, A]


        //// Oppgave 2 b) ///////////////////////////////////////////////////
        DobbeltLenketListe<Integer> liste2b = new DobbeltLenketListe<>();
        System.out.println(liste2b.toString() +""+ liste2b.omvendtString());
        for(int i = 1; i <= 3; i++) {
            liste2b.leggInn(i);
            System.out.println(liste2b.toString() +" "+ liste2b.omvendtString());
        }
        // Utskrift:
        // [] []
        // [1] [1]
        // [1, 2] [2, 1]
        // [1, 2, 3] [3, 2, 1]


        //// Oppgave 3 b) ///////////////////////////////////////////////////
        Character[] c = {'A','B','C','D','E','F','G','H','I','J',};
        DobbeltLenketListe<Character> liste3b = new DobbeltLenketListe<>(c);
        System.out.println(liste3b.subliste(3,8)); // [D,E, F, G, H]
        System.out.println(liste3b.subliste(5,5)); // []
        System.out.println(liste3b.subliste(8,liste3b.antall())); // [I, J]
        System.out.println(liste3b.subliste(0,11)); // skal kaste unntak

        */

        /// Oppgave 5 /////////////////////////////////////////////////////

        Character [] ch = {'A', 'C', 'E'};
        DobbeltLenketListe<Character> liste5 = new DobbeltLenketListe<>(ch);
        System.out.println(liste5.toString());
        liste5.leggInn(1, 'B');
        System.out.println(liste5.toString());
    }
    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen, skal økes for hver innlegging og reduserer for hver fjerning
    private int endringer;         // antall endringer i listen, skal økes for hver endring i listen (innlegging, oppdatering, fjerning, nullstill)

    /**
     *
     * Standardkontruktør.
     *
     * Kilde: Kompendiet, kapittel 3.3.2.
     */
    public DobbeltLenketListe() {
        // throw new UnsupportedOperationException();

        // (1)  'hode' og 'hale' settes til null
        hode = null;
        hale = null;

        antall = 0;         // forteller oss at vi har en tom liste
        endringer = 0;      // ingen endringer er foreløpig gjort

    }

    /**
     *
     * Konstruktør.
     *
     * Kilde: Kompendiet, kapittel 3.3.2.
     * Kilde: https://www.w3schools.com/java/java_break.asp
     */
    public DobbeltLenketListe(T[] a) {
        // throw new UnsupportedOperationException();

        // det kastes en null-pointer exception om listen er tom
        if (a == null) {
            throw new NullPointerException("Tabellen er null!");
        }

        Node<T> q = null; // oppretter en midlertidig node
        // (1) vi går igjennom tabell 'a'
        for (int i = 0; i < a.length; i++) {
            if(a[i] == null) continue; // hvis verdi er null, så hoppver vi videre i loopen
            antall++; // teller første verdi

            // (2) sjekker om vi er på første indeks
            if (hode == null) {

                // (3) første verdi settes til hode
                hode = new Node<>(a[i], null, null);
                hale = hode;
                q = hode; // midelrtidig node settes til hode

                // (4) hvis verdien ikke er første, så fortsetter vi å legge inn i listen bakfra
            } else {
                // neste midlertidige node
                Node<T> p = new Node<>(a[i]);
                q.neste = p;
                p.forrige = q;
                q = p;
            }
        }
        hale = q; // 'q' representerer siste verdien til slutt
    }

    /**
     *
     * @param fra
     * @param til
     * @return
     */
    public Liste<T> subliste(int fra, int til) {
        // throw new UnsupportedOperationException();

        // sjekk om indeks [fra, til> er lovlig
        fraTilKontroll(antall, fra, til);

        Liste<T> subliste = new DobbeltLenketListe<>();
        if ((fra == 0) && (til == 0)) {
            return subliste;
        }
        Node<T> start = finnNode(fra);
        for (int i = fra; i < til; i++) {

            // første verdi settes
            if (i == fra) {
                subliste.leggInn(start.verdi);
                start = start.neste;
            // neste verdiene settes
            } else {
                subliste.leggInn(start.verdi);
                start = start.neste;
            }
        }

        return subliste;
    }

    private static void fraTilKontroll(int antall, int fra, int til) {
        if((fra < 0 ) || (fra > til) || (til > antall)) {
            throw new IndexOutOfBoundsException("Ugyldig intervall! Må være mellom [0, " + antall + ">.");
        }else {
            return;
        }
    }

    @Override
    public int antall() {
        // throw new UnsupportedOperationException();

        // (1) hvis antall har en verdi, så returnerer vi den
        if ( antall != 0) {
            return antall;
            // (2) hvis ikke returnerer vi 0
        } else {
            return 0;
        }
    }

    @Override
    public boolean tom() {
        // throw new UnsupportedOperationException();

        // (1) hvis vi ikke har telt noen antall i listen, så er den tom
        if (antall() != 0) {
            // returnerer 'false' hvis listen inneholder verdier
            return false;
        }
        // returnerer 'true' hvis listen er tom
        return true;
    }


    /**
     *
     * Noden blir enten lagt inn i en tom liste (Tilfelle 1),
     * hvor både hode og hale settes til den nye noden.
     *
     * Ellers blir noden lagt inn bakerst i listen (Tilfelle 2), hvor siste node
     * peker til den nye noden, og halen blir flyttet til siste node.
     *
     * Ved begge tilfeller økes både antall og endringer med 1.
     *
     * @param verdi - verdi på ny node
     * @return true - dersom verdi != null.
     */
    @Override
    public boolean leggInn(T verdi) {

        //Null verdier ikke tillatt, kaster unntak
        Objects.requireNonNull(verdi);

        Node <T> p = new Node<>(verdi);

        // Tilfelle 1: Liste er på forhånd tom
        if((hode == null) && (hale == null)) {
           hode = p;
           hale = p;
        }
        // Tilfelle 2: Liste er ikke tom
        else {
            Node <T> q = hale;
            hale = p;
            q.neste = p;
            p.forrige = q;

        }
        antall++;
        endringer++;
        return true;
    }


    /**
     *
     * Her skal verdier legges i en liste etter gitt indeks.
     *
     * Kilde: kompendiet 3.3.2
     *
     * @param indeks
     * @param verdi
     */
    @Override
    public void leggInn(int indeks, T verdi) {
        // throw new UnsupportedOperationException();


        // Oppgave 5
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");

        indeksKontroll(indeks, false);

        if (antall == 0) {
            Node<T> nyNode = new Node<>(verdi);
            hode = nyNode;
            hale = hode;
        }
        if (indeks == 0 && antall != 0) {
            Node<T> gammelNode = finnNode(indeks);
            Node<T> nyNode = new Node<>(verdi, null, gammelNode);
            hode = nyNode;
            gammelNode.forrige = nyNode;
        } else if (indeks == antall){
            Node<T> buffer = hale;
            Node<T> nyNode = new Node<>(verdi, buffer, null);
            buffer.neste = nyNode;
            hale = nyNode;
        } else {
            Node<T> gammelNode = finnNode(indeks);
            Node<T> nyNode = new Node<>(verdi,gammelNode.forrige, gammelNode);
            gammelNode.forrige.neste = nyNode;
            gammelNode.forrige = nyNode;
        }
        endringer++;
        antall ++;
    }

    /**
     *
     * @param verdi - skal sjekkes om verdien befinner seg
     *              i listen.
     * @return true hvis verdien befinner seg i listen,
     *          hvis ikke return false.
     */
    @Override
    public boolean inneholder(T verdi) {

        if(indeksTil(verdi) == -1){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public T hent(int indeks) {
        // throw new UnsupportedOperationException();
        // Oppgave 3
        // 3.3.3 a)
        indeksKontroll(indeks, false);  // Se Liste, false: indeks = antall er ulovlig
        endringer ++;
        return finnNode(indeks).verdi;
    }

    /**
     *
     * @param verdi - verdi på Node som det skal sjekkes om befinner
     *              seg i listen
     * @return - indeksen/posisjonen til verdi hvis den finnes,
     *           hvis den ikke finnes skal den returnere -1.
     *           Hvis verdi forekommer flere ganger, skal indeksen
     *           til den første av dem (fra venstre) returneres.
     */
    @Override
    public int indeksTil(T verdi) {

        Node <T> q = hode;


        if(verdi == null){
            return -1;
        }

        for(int posisjon = 0; q != null; posisjon++){
            if(q.verdi == verdi){
                return posisjon;

               // bør være: -- break; -- for at den ikke
                // skal gå videre i forløkken, men funker ikke
                // sammen med "return -1" inni for-løkken.

                //sånn som det er nå vil posisjonen til SISTE
                // verdi (dersom verdien befinner seg flere steder
                // i listen) bli returnert, men skal være FØRSTE

            } else {
                q = q.neste;
            }
        }
        return -1;
    }


    @Override
    public T oppdater(int indeks, T nyverdi) {
        // throw new UnsupportedOperationException();

        // throw new UnsupportedOperationException();
        // Oppageve 3

        // sjekker at indeks ikke er null
        // indeksKontroll(indeks, false);
        // ny verdi kan ikke være null
        Objects.requireNonNull(nyverdi, "Ikke tillatt med null-verdier!");

        Node<T> curr = finnNode(indeks);

        T gammelVerdi = curr.verdi;

        // vi erstatter  gammel verdi på plass indeks med ny verdi
        curr.verdi = nyverdi;

        endringer++;
        // returnere det som lå der fra før
        return gammelVerdi;
    }

    // kompendiet kapittel 3.3.3
    // Oppgave 3
    private Node<T> finnNode(int indeks) {

        indeksKontroll(indeks, false);

        if (!tom()) {
            Node<T> p; // midlertidig peker
            if(indeks <= (antall / 2)) {
                p = hode;

                for (int i = 0; i <= indeks; i++) {
                    if (indeks == i) {
                        return p;
                    } else {
                        p = p.neste;
                    }
                }
            } else {
                p = hale;
                for (int i = antall - 1; i > (antall / 2) && (i < antall); i--) {
                    if (indeks == i) {
                        return p;
                    } else {
                        p = p.forrige;
                    }
                }
            }
            return p;
        }else {
            throw new NullPointerException("Indeks er tom!");
        }

    }

    /**
     * @param verdi -- verdi på plass indeks skal fjernes.
     * @return -- true, dersom verdien er funnet og fjernet. false hvis den
     * ikke er funnet, eller om verdi = null.
     */

    @Override
    public boolean fjern(T verdi) {
        Node<T> p = hode;

        boolean funnet = false;

        if(antall == 0){
            return false;
        }

        while(verdi != null) {
            if (p.verdi == verdi) {
                funnet = true;
                break;
            } else {
                p = p.neste;
            }
        }

        if(funnet){
            if(verdi == hode.verdi){          //if value is head
                hode = hode.neste;
                hode.forrige = null;

            } else if (verdi == hale.verdi){  //if value is tail
                hale = hale.forrige;
                hale.neste = null;
            } else {                    //value is between head and tail
                p.neste.forrige = p.forrige;
                p.forrige.neste = p.neste;
            }
            antall--;
            endringer++;
        }

        return funnet;
    }

    /**
     * @param indeks skal finnes og fjernes.
     *  @return indeks -- verdien som er fjernet.
*/
    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks,false);

        T fjernIndex;
        //kun en node i listen:
        if (antall == 1){
            fjernIndex = hode.verdi;
            hode = hale = null;
        }
        //fjerne hodet:
        else if (indeks == 0){
            fjernIndex = hode.verdi;
            hode = hode.neste;
            hode.neste.forrige = null;
        }
        //fjerne halen:
        else if (indeks == antall -1){
            fjernIndex = hale.verdi;
            hale = hale.forrige;
            hale.forrige.neste = null;

        }
        //noden er et sted mellom hode og hale:
        else {
            Node<T> current = finnNode(indeks);
            fjernIndex = current.verdi;
            current.forrige.neste = current.neste;
            current.neste.forrige = current.forrige;
        }

        antall--;
        endringer++;
        return fjernIndex;
    }

    @Override
    public void nullstill() {
    // throw new UnsupportedOperationException();
        Node<T> slett = hode;

        //Første måte:
        while (slett != null) {
            hode = slett.neste;
            System.out.println("slett : " + hode.forrige.verdi);
            hode.forrige = null;
            slett = hode;
            if (hode == hale) {
                hale.forrige = null;
                System.out.println("slett : " + hale.verdi);
                hode = null;
                hale = null;
                antall = 0;
                endringer++;
                break;
            }
            antall--;
            endringer++;
            System.out.println("Antall: " + antall + "Endringer : " + endringer);
        }

        //Andre måte:
        for(int i = 0; i < antall; i++){
            hode = hode.neste;
            fjern(i);
            if(hode == hale){
                fjern(i);
                break;
            }
        }
    }

    /**
     * ---- toString ---
     * @return en toString, som inneholder listens verdier, fra hode til hale.
     * Hvis listen feks inneholder tallene 1, 2, 3,
     * skal metoden returnere strengen "[1, 2, 3]" og kun "[]" hvis listen er tom.
     *
     * Bruker en StringBuilder til å opprette tegnstrengen.
     *
     * Traverserer fra hode til hale ved hjelp av neste-pekere.
     *
     * Kilde: Kompendiet, kapittel 3.3.2.
     */

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();
        boolean hasNext = true;
        Node<T> q = hode;

        // (1) Setter klammeparantes i starten av String
        s.append('[');

        // (2) Sjekker om liste er tom, hvis ikke løpes det gjennom ved å flytte neste-peker
        while (hasNext && q != null) {
            if (q.neste == null) {
                hasNext = false;
                s.append(q.verdi);
            } else {
                s.append(q.verdi).append(',').append(' ');
                q = q.neste;
            }
        }
        s.append(']');
        return s.toString();
    }
    /**
     *
     * @return en toString som inneholder listens verdier i omvendt rekkefølge,
     * fra hale til hode.
     *
     * Bruker en StringBuilder til å opprette tegnstrengen.
     *
     * Traverserer fra hale til hode ved hjelp
     * av forrige-pekere.
     *
     * Kilde: Kompendiet, kapittel 3.3.2.
     */
    public String omvendtString() {

        StringBuilder s = new StringBuilder();

        s.append('[');

        if (!tom()) {
            Node<T> r = hale;
            s.append(hale.verdi);

            r = r.forrige;

            while (r != null) {
                s.append(',').append(' ').append(r.verdi);
                r = r.forrige;
            }
        }
        s.append(']');

        return s.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator ();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);
        return new DobbeltLenketListeIterator (indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            denne = finnNode(indeks);
            fjernOK = false;
            iteratorendringer = endringer;
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            if(iteratorendringer != endringer){
                throw new ConcurrentModificationException("Iteratorendringer er ulikt endringer!");
            }
            if(!hasNext()){                             //ingen flere igjen i listen
                throw new NoSuchElementException("Det er ikke flere noder i listen");
            }

            fjernOK = true;
            T returnerDenne = denne.verdi;
            denne = denne.neste;
            return returnerDenne;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


