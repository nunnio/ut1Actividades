package com.nnh;

import jakarta.xml.bind.JAXB;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class Ejercicio4 {
    public static void main(String[] args) {
        JAXBContext context = null;
        Marshaller marshaller = null;
        System.out.println("A continuación crearemos las parituras que desees añadir, PRESIONA 0 en el id para dejar de añadir: ");
        Vector<Partitura> listaPartituras = null ; //creaPartituras();
        System.out.println("Generando fichero ...");
        escribirJAXB(listaPartituras, marshaller, context);

    }
    public static void escribirJAXB(Vector<Partitura> listaPartituras, Marshaller marshaller, JAXBContext context){
        /*for(int i = 0; i <= listaPartituras.size(); i++) {
            try {
                context = JAXBContext.newInstance(listaPartituras.get(i).getClass());
                marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                marshaller.marshal(listaPartituras, new File("partiturasJAXB.xml"));
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }*/
        Partituras partituras = new Partituras();
        Partitura p1 = new Partitura(100, 2000,"Martini Bros","Tanzen");
        Partitura p2 = new Partitura(200, 2010,"Waka Wakkak","Shakira");
        partituras.addPartitura(p1); partituras.addPartitura(p2);
        try {
            context = JAXBContext.newInstance(p1.getClass());
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(partituras, new File("partiturasJAXB.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
    /*public static Vector<Partitura> creaPartituras(){
        Scanner sc = new Scanner(System.in);
        int id, n= 1; double anio; String tit, aut;
        Vector<Partitura> listaPartituras = new Vector<Partitura>();
        for (int i = 1; true; i++){
            System.out.println("Partitura nº"+i);
            System.out.println("ID: ");
            id = sc.nextInt();
            if(id < 1)
                break;
            System.out.println("Año de publicación: ");
            anio = sc.nextDouble();
            sc.nextLine();
            System.out.println("Autor: ");
            aut = sc.nextLine();
            System.out.println("Título: ");
            tit = sc.nextLine();
            listaPartituras.add(new Partitura(id, anio, aut, tit));
        }
        return listaPartituras;
    }*/
}
