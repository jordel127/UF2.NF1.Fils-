package Processos;

public class ex4p {
    public static void main(String[] args) {
        BD db = new BD();

        filDB fil1 = new filDB(db);
        filDB fil2 = new filDB(db);

        fil1.start();
        fil2.start();

    }
}

class filDB extends Thread {
    private BD db;

    public filDB(BD db) {
        this.db = db;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            db.mostrarRegistres();
        }
    }

}