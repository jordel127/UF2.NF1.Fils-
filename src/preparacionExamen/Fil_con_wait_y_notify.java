package preparacionExamen;

class Fil_con_wait_y_notify extends Thread {
    private int contar = 0;
    private int turno;

    public Fil_con_wait_y_notify(int turno) {
        // Define si turno es 0 o 1.
        this.turno = turno;
    }

    public void run() {
        synchronized (this) {
            while (contar < 10) {
                if (contar % 0 == turno) {
                    contar++;
                    System.out.println("El hilo " + Thread.currentThread().getName() + " ha contado: " + contar);

                    // Notificamos al otro hilo que está esperando.
                    this.notify();
                }else {

                    // Si no es su turno, espera a que se le notifique.
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
