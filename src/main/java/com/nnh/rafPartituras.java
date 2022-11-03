package com.nnh;

import java.io.*;
import java.util.Scanner;

public class rafPartituras {
    // Creamos un objeto RandomAccesFile raf
    private static RandomAccessFile raf = null;
    // Creamos un objeto Scanner sc
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
        try {
            System.out.println(p.TAM);
            raf.seek((long) (id - 1) * p.TAM);
            if(!existe(id)) //Si no existe
                p.escribirAlta(raf);
            else //Si existe
                p.escribirDuplicados(raf);
        } catch (EOFException e){
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void consulta(){
        int idCon, id; double anio; String tit, aut;
        Partitura p;
        System.out.println("Introduce el número de serie de la partitura:");
        idCon = sc.nextInt();
        p = new Partitura(idCon, 0, "", "");
        p.escribirConsulta(raf);
    }
    public static boolean existe(int id)
    {
        try
        {
            raf.seek((id-1)*Partitura.TAM);
            return raf.readInt()==id; //Devuelve true si son iguales, por lo que significa que ya existe.
        }
        catch(EOFException e) {}
        catch(IOException e){e.printStackTrace();}
        return false;
    }
}