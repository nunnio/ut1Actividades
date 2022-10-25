package com.nnh;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

class Partitura {
    private static final int maxString = 50;
    private static final int TAM = 2*(maxString + 1) + 2*Integer.SIZE/8;
    //hacer tamlist
    int id; double anio; String tit, aut;
    public Partitura(int id, double anio, String tit, String aut) {
        this.id = id;
        this.anio = anio;
        this.tit = tit.substring(0, Math.min(20, tit.length())); //quitar esto y añadirlo al momento de writeline
        this.aut = aut.substring(0, Math.min(20, aut.length())); ;
    }
    public void muestra(){
        if(id != 0){
            System.out.println("Partitura: "+id+"\nTítulo: "+tit+"\nAutor: "+aut+"\nAño de publicación: "+anio);
        }
        else
            System.out.println("Esta partitura no existe.");
    }
    public void escribirAlta(RandomAccessFile raf){
        try {
            raf.seek((long) (id - 1) * TAM);
            raf.writeInt(id);
            raf.writeDouble(anio);
            //tit = tit.substring(0, Math.min(20, tit.length()));
            //aut = aut.substring(0, Math.min(20, aut.length()));
            raf.writeUTF(tit);
            raf.writeUTF(aut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void escribirConsulta(RandomAccessFile raf){
        try {
            raf.seek((long)(id - 1) * TAM);
            id = raf.readInt();
            if(id != 0){
                anio = raf.readDouble();
                aut = raf.readUTF();
                tit = raf.readUTF();
                System.out.println("Partitura: "+id+"\nTítulo: "+tit+"\nAutor: "+aut+"\nAño de publicación: "+anio);
            }
        }catch (EOFException e){
            System.out.println("Esta partitura no existe.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class rafPartituras {
    private static RandomAccessFile raf = null;
    private static Scanner sc = null;

    public static void main(String[] args) {
        try {
            raf =  new RandomAccessFile("partituras.dat","rw");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        sc = new Scanner(System.in);
        int sel;
        while(true) {
            System.out.println("\n\tGestor de partituras\n1- Introduce una partitura\n2- Consulta una partitura\n0- Salir");
            sel = sc.nextInt();
            switch (sel) {
                case 1:
                    alta();
                    break;
                case 2:
                    consulta();
                    break;
                default:
                    System.out.println("Fin del programa");
                    System.exit(0);
            }
        }
    }
    public static void alta() {
        int id; double anio; String tit, aut;
        Partitura p;

        System.out.println("Introduce el número de serie de la partitura:");
        id = sc.nextInt();
        System.out.println("Introduce el año de publicación:");
        anio = sc.nextDouble();
        System.out.println("Introduce el nombre de la obra:");
        sc.nextLine();
        tit = sc.nextLine();
        System.out.println("Introduce el nombre del autor:");
        aut = sc.nextLine();
        p = new Partitura(id, anio, tit, aut);
        p.escribirAlta(raf);
    }
    public static void consulta(){
        int idCon, id; double anio; String tit, aut;
        Partitura p;
        System.out.println("Introduce el número de serie de la partitura:");
        idCon = sc.nextInt();
        p = new Partitura(idCon, 0, "", "");
        p.escribirConsulta(raf);
    }
}