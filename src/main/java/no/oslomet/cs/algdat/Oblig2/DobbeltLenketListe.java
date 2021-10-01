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
        // deler av kode fra Programkode 3.3.2 c) og d) fra kompendiet
        // Standardkontruktør
        hode = hale = null;        // hode og hale til null

        antall = 0;         // tom liste
        endringer = 0;

    }

    public DobbeltLenketListe(T[] a) {
        // throw new UnsupportedOperationException();

        // Oppgave 1
        // Kontruktør
        // deler av kode fra Programkode 3.2.2 d) i kompendiet
        this(a.length);

        for (T verdi : a) {
            if (verdi != null) a[antall++] = verdi; // hopper over nullverdier
        }
    }

    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int antall() {
        // throw new UnsupportedOperationException();

        // Oppgave 1

        // deler av kode fra Programkode 3.1.2 b) og oppgave 2 til Avsnitt 3.1.1 fra kompendiet
        int antall = 0;
        for (T t : this) if (t != null) antall++;
        // bruker en "for alle"-løkke for å telle antall verdier > 0 i tabellen

        return antall;
    }

    @Override
    public boolean tom() {
        // throw new UnsupportedOperationException();

        // Oppgave 1

        if (antall() != 0) {
            return false;
        }
        return true;
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
        // throw new UnsupportedOperationException();

        // kode fra kompendiet: oppgave 1 til Avsnitt 3.1.2
        if (verdi == null)  // tillatt med null-verdier?
        {
            for (T t : this) if (t == null) return true;
        }
        else
        {
            for (T t : this) if (verdi.equals(t)) return true;
        }
        return false;
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
        // throw new UnsupportedOperationException();

        // Kode fra kompendiet: oppgave 2 til Avsnitt 3.1.2
        for (Iterator<T> it = iterator(); it.hasNext(); )
        {
            it.next();
            it.remove();
        }
    }

    @Override
    public String toString() {
        // throw new UnsupportedOperationException();

        // kode fra kompendiet: oppgave 3 til Avsnitt 3.1.2
        StringBuilder st = new StringBuilder("[");

        Iterator<T> it = iterator();

        if (it.hasNext()) st.append(it.next());  // første verdi

        while (it.hasNext())
        {
            st.append(',');        // komma
            st.append(' ');        // blank
            st.append(it.next());   // verdi
        }

        st.append(']');

        return st.toString();
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


