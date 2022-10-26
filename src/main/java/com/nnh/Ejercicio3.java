package com.nnh;

import org.w3c.dom.DOMImplementation;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Vector;

public class Ejercicio3 {
    public static void main(String[] args) {
        Partitura p1 = new Partitura(001,2000,"Criminal","Eminem");
        Partitura p2 = new Partitura(002,1990, "Hotel California", "Gipsy Kings");
        Vector<Partitura> listaPartituras = new Vector<Partitura>();
        listaPartituras.add(p1);
        listaPartituras.add(p2);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document
    }
}
