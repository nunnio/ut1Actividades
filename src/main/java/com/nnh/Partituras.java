package com.nnh;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Partituras {
    @XmlElement(name = "partitura")
    private ArrayList<Partitura> partituras = null;

    public Partituras(){
        partituras = new ArrayList<>();
    }
    public void addPartitura(Partitura p){
        partituras.add(p);
    }
}
