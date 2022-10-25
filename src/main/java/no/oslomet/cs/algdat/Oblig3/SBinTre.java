package no.oslomet.cs.algdat.Oblig3;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.StringJoiner;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null; //p starter i roten
        int cmp = 0; //hjelpevariabel

        while (p != null){ //fortsetter til p er ute av treet
            q = p; //q er forelder til p
            cmp = comp.compare(verdi, p.verdi); //bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre; //flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte
        p = new Node<>(verdi, q); //oppretter en ny node, her er q forelderen til ny node

        if (q == null) {
            rot = p; //p blir rotnode
        }
        else if (cmp < 0) { //sjekker om cmp er mindre enn null
            q.venstre = p; //venstre barn til q
            p.forelder = q; // nå blir forrige satt til forelder
        }
        else {
            q.høyre = p; //høyre barn til q
            p.forelder = q; // forrige blir satt til forelder
        }

        antall++; //en verdi mer i treet
        return true; //vellykket innlegging
    } //Kompendiet 5.2.3

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int antall(T verdi) {

        //Hvis verdi ikke  er i treet (null er ikke i treet), skal metoden returnere 0.
        if (verdi == null) {
            return 0;
        }

        int antallV = 0; //lager en teller verdi
        int cmp = 0;
        Node<T> p = rot; //lager en peker mot rotnoden

        if (p != null){
            cmp = comp.compare(verdi, p.verdi); //sammenligner verdien og p (roten)
            if (cmp < 0) {
                p = p.venstre; //legger verdien til venstre
            } else {
                if (cmp == 0) {
                antallV++;
            }
                p = p.høyre;
            }
        }
        return antallV;
    }

    //Den skal returnere antall forekomster av verdi i treet


    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postorden(Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


} // ObligSBinTre
