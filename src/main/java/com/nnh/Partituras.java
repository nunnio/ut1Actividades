package com.nnh;

import java.util.ArrayList;

public class Partituras {
    private ArrayList<Partitura> partituras = null;

    public Partituras(){
        partituras = new ArrayList<>();
    }
    public void addPartitura(Partitura p){
        partituras.add(p);
    }
}
