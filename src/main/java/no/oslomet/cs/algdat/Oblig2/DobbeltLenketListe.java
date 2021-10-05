package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;


public class DobbeltLenketListe<T> implements Liste<T> {

    //// MAIN TEST ///////////////////////////////////////////////////////
    // main-metode til testing av oppgavene. Slettes før oppgaven leveres!
    public static void main(String[] args) {

        //// Oppgave 1 ///////////////////////////////////////////////////
        Liste<String> liste = new DobbeltLenketListe<>();
        System.out.println(liste.antall() + "" + liste.tom());
        // Utskrift: 0 true

        String[] s = {"Ole", null, "Per", "Kari", null};
        Liste<String> stringListe = new DobbeltLenketListe<>(s);
        System.out.println(stringListe.antall() + "" + stringListe.tom());
        // Utskrift: 3 false
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
     */
    public DobbeltLenketListe(T[] a) {
        // throw new UnsupportedOperationException();

        // det kastes en null-pointer exception om listen er tom
        if (a == null) {
            throw new NullPointerException("Tabellen er null!");
        }

        // (1) vi går igjennom tabell 'a'
        for (int i = 0; i < a.length; i++) {

            // (2) sjekker om vi er på indeks 0
            if ((i == 0) && (a[i] != null)) {

                // (3) setter inn første verdi
                hode = new Node<>(a[i], null, null);

                // hvis vi ikke har satt inn andre verdier så settes verdien til første verdi
                if (antall == 0) {
                    hale = hode;
                }
                // vi registrer at det er satt inn antall: 1 verdi
                antall ++;

                // (4) hvis listen ikke er tom så setter ny node bakfra, til å være hale
            } else if (a[i] != null) {
                hale.neste = new Node<>(a[i], hale, null);
                hale = hale.neste;
                // vi legger på antall for hver nye node vi legger inn
                antall++;
            }

        }

    }

    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
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


    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
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

        // (1) Setter klammeparantes i starten av String
        s.append('[');

        // (2) Sjekker om liste er tom, hvis ikke løpes det gjennom ved å flytte neste-peker
        if(!tom()){
            Node<T> q = hode;
            s.append(q.verdi);

            q = q.neste;

            // (3) Frem til siste node (hvor q = null), sett komma mellom verdiene.
            while(q != null){
                s.append(',').append(' ').append(q.verdi);
                q = q.neste;
            }
        }

        // (4) Legg på en klammeparantes på slutten av stringen
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

        if(!tom()){
            Node<T>r = hale;
            s.append(hale.verdi);

            r = r.forrige;

            while(r != null){
                s.append(',').append(' ').append(r.verdi);
                r = r.forrige;
            }
        }
        s.append(']');

        return s.toString();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
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
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
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


