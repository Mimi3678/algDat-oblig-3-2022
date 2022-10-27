package no.oslomet.cs.algdat.Oblig3;


import java.util.*;

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
        if (verdi == null) return false;  // treet har ingen nullverdier

        Node<T> p = rot, q = null;   // q skal være forelder til p

        while (p != null)            // leter etter verdi
        {
            int cmp = comp.compare(verdi,p.verdi);      // sammenligner
            if (cmp < 0) { q = p; p = p.venstre; }      // går til venstre
            else if (cmp > 0) { q = p; p = p.høyre; }   // går til høyre
            else break;    // den søkte verdien ligger i p
        }
        if (p == null) return false;   // finner ikke verdi

        if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
        {
            Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn
            if (p == rot) {
                rot = b;
                if(b != null) // tilfelle1: gjør til at pekeren får rett verdi (forelder får peker null)
                    b.forelder = null;
            }
            else if (p == q.venstre) {
                q.venstre = b;

                if(b != null) //tilfelle2: etter sletting vil nå b sin forelder være q
                    b.forelder = q;
            }
            else {
                q.høyre = b;
                if(b != null)
                    b.forelder = q;
            }
        }
        else  // Tilfelle 3)
        {
            Node<T> s = p, r = p.høyre;   // finner neste i inorden
            while (r.venstre != null)
            {
                s = r;    // s er forelder til r
                r = r.venstre;
            }

            p.verdi = r.verdi;   // kopierer verdien i r til p

            if (s != p) {
                s.venstre = r.høyre; // r fjernes her ved at s settes lik r.høyre
                if(r.høyre != null)
                    r.høyre.forelder = s; //tilfelle3 for venstre barn: etter sletting vil r sin forelder være nå s sin forelder
            }
            else {
                s.høyre = r.høyre;
                if(r.høyre != null) //tilfelle3 for høyre barn: etter sletting vil r sin forelder være nå s sin forelder
                    r.høyre.forelder = s;
            }
        }

        antall--;
        endringer++; //de nye endringene
        // det er nå én node mindre i treet
        return true;

    }

    public int fjernAlle(T verdi) {

        int verdiA = 0;
        if (tom()){ //om treet er tomt, returner 0
            return 0;
        }
        else {
            return verdiA; //skal returnere antall som ble fjernet
        }

       /* int verdiAntall = 0;
        while (fjern(verdi)) verdiAntall++;
        return verdiAntall;*/
    }

    public int antall(T verdi) {

        //Hvis verdi ikke  er i treet (null er ikke i treet), skal metoden returnere 0.
        if (verdi == null) {
            return 0;
        }

        int antallV = 0; //lager en teller verdi
        int cmp = 0;
        Node<T> p = rot; //lager en peker mot rotnoden

        while (p != null){ //måte bruke while her (vet ikke hvorfor), brukte if-først
            cmp = comp.compare(verdi, p.verdi); //sammenligner verdien og p (roten)
            if (cmp < 0) {
                p = p.venstre; //legger verdien til venstre
            } else {
                if (cmp == 0) { //hvis den er lik, duplikater
                antallV++;
            }
                p = p.høyre; //legger verdien til høyre
            }
        }
        return antallV;
    } //Den skal returnere antall forekomster av verdi i treet


    public void nullstill() {

        //nullstille venstre og høyre barn
        /*rot.høyre = nullstill();
        rot.venstre = nullstill();

       if (rot == null){
           return ;
       }


        /*Node<T> p = rot;
        Node<T> q;

        //rot = null;

        if (tom()){

        } else {

        }*/



        //må vi ha med iterator her?


    }

    //Oppgave 3
    private static <T> Node<T> førstePostorden(Node<T> p) {
        Objects.requireNonNull(p); //her sjekkes det om p ikke er en nullverdi

        while (true) {
            if (p.venstre != null) {
                p = p.venstre; //p sin venstre barn
            } else if (p.høyre != null) {
                p = p.høyre; //p sin høyre barn
            } else {
                return p;
            }
        }
    }

    //Oppgave 3
    private static <T> Node<T> nestePostorden(Node<T> p) {
        //initializere forelder
        Node<T> s = p.forelder;

        if (s == null){
            return null; //Dersom det ikke er noe neste postorden, eller ingen forelder
        }

        if (s.høyre == p || s.høyre == null) { //om høyre barn får verdien p, skal vi returnere forelder eller at det er tomt
            return s;
        } else {
            return førstePostorden(s.høyre); //kaller førstePostorden metoden
        }

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
