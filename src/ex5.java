public class ex5 {
    public static void main(String[] args) throws InterruptedException {
        filvocals fil = new filvocals();

        Thread primero = new Thread(fil);
        Thread segundo = new Thread(fil);

        primero.setName("primero");
        segundo.setName("segundo");

        primero.start();
        segundo.start();

        primero.join();
        segundo.join();
    }
}

class filvocals extends Thread {
    private final char[] arrayVocales = {'a', 'e', 'i', 'o', 'u'};
    private static int posicion = 0;

    public void run() {
        while (posicion < arrayVocales.length) {
            synchronized (this) {
                if ((Thread.currentThread().getName().equals("primero") && posicion % 2 == 0) ||
                        (Thread.currentThread().getName().equals("segundo") && posicion % 2 != 0)) {
                    System.out.println(Thread.currentThread().getName() + ": " + arrayVocales[posicion]);
                    posicion++;
                    this.notify(); // Notificamos al otro hilo
                } else {
                    try {
                        this.wait(); // Si no es su turno, espera
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
