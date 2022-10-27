package com.nnh;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class Ejercicio3 {
    public static void main(String[] args) {
        System.out.println("\n\t-- Creador de ficheros XML --");
        System.out.println("A continuación crearemos las parituras que desees añadir, PRESIONA 0 en el id para dejar de añadir: ");
        Vector<Partitura> listaPartituras = creaPartituras();
        System.out.println("Generando un fichero XML...");
        creaXml(listaPartituras);
        System.out.println("El fichero XML creado está compuesto por: ");
        leeXml();
    }
    public static Vector<Partitura> creaPartituras(){
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
    }
    public static void creaXml(Vector<Partitura> listaPartituras){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        DOMImplementation dom = builder.getDOMImplementation();
        Document document = (Document) dom.createDocument(null, "xml", null);
        Element raiz = document.createElement("partituras");
        document.getDocumentElement().appendChild(raiz);
        Element nodoPartitura = null, nodoDatos = null;
        Text texto = null;

        for (Partitura p:listaPartituras) {
            nodoPartitura = document.createElement("partitura");
            raiz.appendChild(nodoPartitura);


            nodoDatos = document.createElement("id");
            nodoPartitura.appendChild(nodoDatos);
            texto = document.createTextNode(String.valueOf(p.getId()));
            nodoDatos.appendChild(texto);

            nodoDatos = document.createElement("anioPublicacion");
            nodoPartitura.appendChild(nodoDatos);
            texto = document.createTextNode(String.valueOf(p.getAnio()));
            nodoDatos.appendChild(texto);

            nodoDatos = document.createElement("titulo");
            nodoPartitura.appendChild(nodoDatos);
            texto = document.createTextNode(p.getTit());
            nodoDatos.appendChild(texto);

            nodoDatos = document.createElement("autor");
            nodoPartitura.appendChild(nodoDatos);
            texto = document.createTextNode(p.getAut());
            nodoDatos.appendChild(texto);
        }
        Source source = new DOMSource(document);
        Result result = new StreamResult(new File("partitura.xml"));

        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty("indent","yes");
            transformer.transform(source,result);


        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
