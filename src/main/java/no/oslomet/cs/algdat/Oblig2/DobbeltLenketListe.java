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


        // ------ MÅ OPPDATERES ------

        // det kastes en null-pointer exception om listen er tom
        if (a == null) {
            throw new NullPointerException("Tabellen er null!");
        }

        // det kastes en null-pointer exception om listen er tom
        if (a == null) {
            throw new NullPointerException("Tabellen er null!");
        }


        // (1) vi går igjennom tabell 'a'
        for (int i = 0; i < a.length; i++) {

            // midlertidig perker
            Node<T> q = hode;

            // (2) sjekker om vi er på første indeks
            if ((hode == null) && (hale == null)) {

                // (3) første verdi settes inn
                hode = new Node<>(a[i], null, null);
                hale = hode;

                // hvis hvis indeksverdien er null hopper vi over den
                if (a[i] != null) {
                    antall ++;
                } else {
                    continue;
                }

                // (4) hvis verdien ikke er første, så fortsetter vi å legge inn i listen bakfra
            } else {

                // en til midlertidig peker
                Node<T> p = hale;

                q.neste = new Node<>(a[i], p, null);
                hale = q;
                q.forrige = p;
                p.neste = q;

                if (a[i] != null) {
                    antall ++;
                } else {
                    continue;
                }
            }
        }

    }

    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();

        // ------ MÅ OPPDATERES ------


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

        Node<T> q = hode;

        int indeks = -1;

        if (verdi == null) {
            return -1;
        }

        for (int i = 0; i < antall; i++) {
            if (q.verdi == verdi) {
                indeks = i;
                break;
            } else {
                q = q.neste;
            }
        }
        return indeks;
    }


    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();

        // MÅ OPPPDATERES ----
    }

    /**
     * Skal fjerne indeks.verdi fra listen, og returnere true.
     * Hvis det er flere forekomster av verdier, skal kun
     * den første (fra venstre) fjernes.
     *
     * Det skal ikke kastes unntak hvis verdi er null, men returnere
     * false.
     *
     * Skal være så effektiv som mulig.

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

        StringBuilder s = new StringBuilder("[");
        boolean hasNext = true;
        Node<T> q = hale;

        while(hasNext && (q != null)){
            if(q.forrige == null){
                hasNext = false;
                s.append(q.verdi);
            } else {
                s.append(q.verdi).append(", ");
                q = q.forrige;
            }
        }

        s.append("]");
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

    private Node<T> finnNode(int indeks) {

        indeksKontroll(indeks, false);

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


