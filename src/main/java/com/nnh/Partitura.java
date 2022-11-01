package com.nnh;

import java.io.*;
import java.util.Scanner;

class Partitura {
    private static final int maxString = 50;
    public static final int TAM = 2*(maxString + 1) + 2*Integer.SIZE/8;
    //hacer tamlist
    int id, tamPas; double anio; String tit, aut;
    public int getTamPas() {
        //tamPas = TAM;
        return TAM;
    }

    public Partitura(int id, double anio, String tit, String aut) {
        this.id = id;
        this.anio = anio;
        this.tit = tit.substring(0, Math.min(20, tit.length()));
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
    public void escribirDuplicados(RandomAccessFile raf){
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("sinonimos.dat", true)))
        {
            dos.writeInt(id);
            dos.writeDouble(anio);
            dos.writeUTF(tit);
            dos.writeUTF(aut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // Contructor
    public Partitura(int id, int tamPas, double anio, String tit, String aut) {
        this.id = id;
        this.tamPas = tamPas;
        this.anio = anio;
        this.tit = tit;
        this.aut = aut;
    }
    public Partitura(){
    }
    // Setters & getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAnio() {
        return anio;
    }

    public void setAnio(double anio) {
        this.anio = anio;
    }

    public String getTit() {
        return tit;
    }

    public void setTit(String tit) {
        this.tit = tit;
    }

    public String getAut() {
        return aut;
    }

    public void setAut(String aut) {
        this.aut = aut;
    }
    // ToString
    @Override
    public String toString() {
        return "Partitura{" +
                "id=" + id +
                ", tamPas=" + tamPas +
                ", anio=" + anio +
                ", tit='" + tit + '\'' +
                ", aut='" + aut + '\'' +
                '}';
    }
}