package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;


public class DobbeltLenketListe<T> implements Liste<T> {

    //// MAIN TEST //////////////////////////////////////////////////////////////
    // main-metode til testing av oppgavene må slettes før oppgaven leveres!
    public static void main(String[] args) {

        //// Oppgave 1 /////////////////////////////////////////////////////////
        Liste<String> liste = new DobbeltLenketListe<>();
        System.out.println(liste.antall() +""+ liste.tom());
        // Utskrift: 0 true

        String[] s = {"Ole",null,"Per","Kari",null};
        Liste<String> stringListe = new DobbeltLenketListe<>(s);
        System.out.println(stringListe.antall() +""+ stringListe.tom());
        // Utskrift: 3 false

        //// Oppgave 2 /////////////////////////////////////////////////////////

        //// Oppgave 3 /////////////////////////////////////////////////////////

        //// Oppgave 4 /////////////////////////////////////////////////////////

        //// Oppgave 5 /////////////////////////////////////////////////////////

        //// Oppgave 6 /////////////////////////////////////////////////////////

        //// Oppgave 7 /////////////////////////////////////////////////////////

        //// Oppgave 8 /////////////////////////////////////////////////////////
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
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        // throw new UnsupportedOperationException();
        // Oppgave 1



        /*
        ●Stoppes en null-tabell? Kastes i så fall en NullPointerException?
        ●Blir det korrekt hvis parametertabellen inneholder en eller flere null-verdier?
        ●Blir det korrekt hvis parametertabellen er tom (har lengde 0)?
        ●Blir det korrekt hvis parametertabellen kun har null-verdier?
        ●Blir det korrekt hvis parametertabellen har kun én verdi som ikke er null?
        ●Blir antallet satt korrekt?
        ●Får verdiene i listen samme rekkefølge som i tabellen?
         */
    }

    public DobbeltLenketListe(T[] a) {
        throw new UnsupportedOperationException();
    }

    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int antall() {
        throw new UnsupportedOperationException();
        // Oppgave 1

    }

    @Override
    public boolean tom() {
        throw new UnsupportedOperationException();
        // Oppgave 1
    }

    @Override
    public boolean leggInn(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
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

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }

    public String omvendtString() {
        throw new UnsupportedOperationException();
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


