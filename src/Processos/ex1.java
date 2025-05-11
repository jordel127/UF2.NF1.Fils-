package Processos;

import java.sql.Connection;

public class ex1 {
    public static void main(String[] args) throws InterruptedException {
        Monitor monitor = new Monitor();
        filLectura fil1 = new filLectura("filX", monitor);
        filLectura fil2 = new filLectura("filY", monitor);

        BD db = new BD();
        db.mostrarRegistres();

//        fil1.start();
//        fil2.start();

    }
}


class filLectura extends Thread {
    private final String nom;
    private final Monitor monitor;
    private int numFil;
    private static int f = 0;

    public filLectura(String nom, Monitor monitor) {
        f += 1;

        this.nom = nom;
        this.monitor = monitor;
        numFil = f;
    }

    @Override
    public void run() {

        Lectura();

        try {
            if (numFil == 2) {
                Thread.sleep(5000);
            }else {
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Escriptura();

    }
    private void Lectura() {
        monitor.openReading();
        System.out.println("concurrència."+nom+ " obert Monitor per lectura "+numFil);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("concurrència."+nom+ " tancat Monitor per lectura");
        monitor.closeReading();
    }
    private void Escriptura() {
        monitor.openWriting();
        System.out.println("concurrència."+nom+ " obert Monitor per escriptura");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        monitor.closeWriting();
        System.out.println("concurrència."+nom+ " tancat Monitor per escriptura");
    }
}
