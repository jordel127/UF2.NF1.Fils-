package preparacionExamen;

public class EstructuraFils {
    public static void main(String[] args) throws InterruptedException {
        // Grupo de hilos
        ThreadGroup group = new ThreadGroup("Grupo");


        // Declaració fils
        // El constructor recibe el grupo de hilos.
        fil fil1 = new fil(10, group);

        // Inicia el hilo. Llama al método run() del hilo.
        fil1.start();

        // El hilo donde se ejecuta espera a que 'fil1' termine su ejecución antes de continuar.
        fil1.join();

        // Interrumpe el grupo de hilos,
        // todos los hilos del grupo se van a detener.
        group.interrupt();
    }
}

class fil extends Thread {
    private static int sumaFils = 0;

    // Constructor
    public fil(int number, ThreadGroup group) {
        //Al estar en un grupo se le asigna un nombre al hilo.
        super(group, "fil " + number);
    }


    public void run() {

        try {
            //Detiene el hilo en el que se ejecuta por el tiempo indicado.
            Thread.sleep(3000);

            // synchronized hace que solo un hilo pueda entrar
            // mientras los otros esperan a que acabe.
            synchronized (fil.class) {
                sumaFils += 1;
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
