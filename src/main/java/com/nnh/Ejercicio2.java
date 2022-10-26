package com.nnh;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;

public class Ejercicio2 {
    public static void main(String[] args) {
        copia();
    }
    public static void copia(){
        try{
            RandomAccessFile raf = new RandomAccessFile("src/main/java/com/nnh/rafPartituras.java", "r");
            FileChannel readChannel = raf.getChannel();
            ByteBuffer bb = ByteBuffer.allocate(1024);
            Path destino = Paths.get("copiaRafPartituras.java");
            Set<StandardOpenOption> opciones = new HashSet<>();
            opciones.add(StandardOpenOption.APPEND);
            opciones.add(StandardOpenOption.CREATE);
            FileChannel writeChannel = FileChannel.open(destino, opciones);
            while(readChannel.read(bb)>0){
                bb.flip();
                writeChannel.write(bb);
                bb.rewind();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
