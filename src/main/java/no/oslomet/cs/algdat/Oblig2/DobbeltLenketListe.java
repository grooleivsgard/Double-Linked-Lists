package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;


public class DobbeltLenketListe<T> implements Liste<T> {

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
     * Standardkontruktøren oppretter en tabell med en fast størrelse (tom).
     *
     */
    public DobbeltLenketListe() {
        hode = null;
        hale = null;

        antall = 0;         // forteller oss at vi har en tom liste
        endringer = 0;      // ingen endringer er foreløpig gjort

    }

    /**
     * Konstruktøren gir mulighet for å opprette en tabell med en bestemt størrelse.
     * Størrelsen til tabellen kan endres.
     *
     * Kilde: Kompendiet, kapittel 3.3.2.
     * Kilde: https://www.w3schools.com/java/java_break.asp
     */
    public DobbeltLenketListe(T[] a) {
        // sjekker om listen er tom
        if (a == null) {
            throw new NullPointerException("Tabellen er null!");
        }

        Node<T> q = null;

        for (int i = 0; i < a.length; i++) {
            // hopper over verdi om den er 'null'
            if(a[i] == null) continue;
            antall++;
            // setter inn første verdi
            if (hode == null) {
                hode = new Node<>(a[i], null, null);
                hale = hode;
                q = hode;
            } else {            // setter inn neste verdi
                Node<T> p = new Node<>(a[i]);
                q.neste = p;
                p.forrige = q;
                q = p;
            }
        }
        hale = q;
    }

    /**
     * @param fra - hvor indeks starter fra.
     * @param til - hvor indeks slutter frem til.
     * @return subliste.
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

    /**
     * Sjekker om listen sine verdier og indeks er innenfor riktige grenser.
     *
     * @param antall - antall verdier i listen.
     * @param fra - fra indeks.
     * @param til - til indeks.
     */
    private static void fraTilKontroll(int antall, int fra, int til) {
        if((fra < 0 ) || (til > antall)) {
            throw new IndexOutOfBoundsException("Ugyldig intervall! Må være mellom [0, " + antall + ">.");
        }
        if (fra > til){
            throw new IllegalArgumentException("fra " + fra + "er større en til" + til);
        }
        return;

    }

    /**
     * @return antall - antall verdier i listen.
     */
    @Override
    public int antall() {

        if ( antall != 0) {
            return antall;
        } else {
            return 0;
        }
    }

    /**
     * @return true - dersom liste liten er tom.
     */
    @Override
    public boolean tom() {

        if (antall() != 0) {
            return false;
        }
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
     * Ny node legges inn i tabellen.
     *
     * Kilde: kompendiet 3.3.2
     *
     * @param indeks - finner indeks og tabellen oppdateres.
     * @param verdi - verdi settes inn og tabellen oppdateres.
     */
    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");

        indeksKontroll(indeks, true);

        if (antall == 0) {      // hvis tabellen er tom
            Node<T> nyNode = new Node<>(verdi);
            hode = nyNode;
            hale = hode;
        }

        if (indeks == 0 && antall != 0) {   // hvis verdi legges først
            Node<T> gammelNode = finnNode(indeks);
            Node<T> nyNode = new Node<>(verdi, null, gammelNode);
            hode = nyNode;
            gammelNode.forrige = nyNode;
        } else if (indeks == antall){       // hvis verdi legges sist
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

    /**
     *  Kilde: kompendiet kapittel 3.3.3
     *
     * @param indeks - Finner verdien til indeks gjennom metoden finnNode().
     * @return verdi.
     */
    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        endringer ++;
        return finnNode(indeks).verdi;
    }

    /**
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
            if(q.verdi.equals(verdi)){
                return posisjon;
            } else {
                q = q.neste;
            }
        }
        return -1;
    }

    /**
     * @param indeks - oppdaterer indeks.
     * @param nyverdi - oppdaterer verdi på indeksen.
     * @return gammelverdi - returnerer den gamle verdien som fjernes.
     */
    @Override
    public T oppdater(int indeks, T nyverdi) {
        Objects.requireNonNull(nyverdi, "Ikke tillatt med null-verdier!");

        Node<T> curr = finnNode(indeks);

        T gammelVerdi = curr.verdi;

        // vi erstatter  gammel verdi på plass indeks med ny verdi
        curr.verdi = nyverdi;

        endringer++;
        // returnere det som lå der fra før
        return gammelVerdi;
    }

    /**
     * @param indeks - finner indeks på node.
     * @return indeks.
     */
    private Node<T> finnNode(int indeks) {
        indeksKontroll(indeks, false);

        // hvis listen ikke er tom
        if (!tom()) {
            Node<T> p;

            // deler liste i 2 og sjkekker om indeks ligger til høyre eller venstre for listen
            if(indeks <= (antall / 2)) {
                p = hode;

                // sjekker fra hode (venstre) mot midten
                for (int i = 0; i <= indeks; i++) {
                    if (indeks == i) {
                        return p;
                    } else {
                        p = p.neste;
                    }
                }
            } else {            // sjekker fra hale (høyre) mot midten
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
        }else {                 // hvis listen er tom
            throw new NullPointerException("Indeks er tom!");
        }

    }

    /**
     * @param verdi - verdi på plass indeks skal fjernes.
     * @return - true, dersom verdien er funnet og fjernet. false hvis den
     * ikke er funnet, eller om verdi = null.
     */
    @Override
    public boolean fjern(T verdi) {
        Node<T> p = hode;

        boolean funnet = false;

        if(antall == 0){            // hvis listen er tom
            return false;
        }

        while(p != null) {          // så lenge 'p' har en verdi
            if (p.verdi.equals(verdi)) {
                funnet = true;
                break;              // stopper når verdi er funnet
            } else {
                p = p.neste;
            }
        }

        if(funnet){
            if(antall == 1) {       // hvis det bare er 1 verdi
                hode = null;
                hale = null;
                p.neste = null;
                p.forrige = null;
                antall = 0;
                endringer++;
                return true;
            }
            if(hode == p){          // hvis verdi er hode
                hode.neste.forrige = null;
                hode = hode.neste;
                hode.forrige = null;
                p.neste = null;
                antall--;
                endringer++;
                return true;
            } else if (hale == p){  // hvis verdi er hale
                hale.forrige.neste = null;
                hale = hale.forrige;
                hale.neste = null;
                p.forrige = null;
                antall--;
                endringer++;
                return true;
            } else {
                p.neste.forrige = p.forrige;
                p.forrige.neste = p.neste;
                p.neste = null;
                p.forrige = null;
                antall--;
                endringer++;
                return true;
            }
        }
        return false;
    }

    /**
     * @param indeks skal finnes og fjernes.
     * @return indeks - verdien som er fjernet.
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
            hode.forrige = null;
        }
        //fjerne halen:
        else if (indeks == antall-1){
            fjernIndex = hale.verdi;
            hale = hale.forrige;
            hale.neste = null;
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

    /**
     * Nullstill tabell slik at den blir tom. 2 ulike metoder.
     */
    @Override
    public void nullstill() {
        Node<T> slett = hode;

        // 1. måte:
        while (slett != null) {
            hode = slett.neste;
            hode.forrige = null;
            slett = hode;
            if (hode == hale) {
                hale.forrige = null;
                hode = null;
                hale = null;
                antall = 0;
                endringer++;
                break;
            }
            antall--;
            endringer++;
        }

        // 2. måte:
        for(int i = 0; i < antall -1; i++){
            hode = hode.neste;
            fjern(i);

            if(hode == hale){
                fjern(i);
                break;
            }
            antall--;
            endringer++;
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