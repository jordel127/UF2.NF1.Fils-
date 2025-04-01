public class ex4 {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("GrupoEx4");
        agragar2hilos(group);

        Thread.sleep(3000);
        group.interrupt();
    }

    public static void agragar2hilos(ThreadGroup group) {
        filSuma filSuma = new filSuma(group, "filSuma");
        filMultiplica filMultiplica = new filMultiplica(group, "filMultiplica");

        filSuma.start();
        filMultiplica.start();
    }

}
class filSuma extends Thread {
    private int number = 0;

    public filSuma(ThreadGroup group, String name) {
        super(group, name);
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("Suma: " + number);
                number += 1;
                Thread.sleep(1000);
            }
        }catch (InterruptedException e) {
            System.out.println(getName() + " parao.");
        }
    }
}

class filMultiplica extends Thread {
    private int number = 1;

    public filMultiplica(ThreadGroup group, String name) {
        super(group, name);
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("Multiplica: " + number);
                number *= 2;
                Thread.sleep(1000);
            }
        }catch (InterruptedException e) {
            System.out.println(getName() + " parao.");
        }
    }
}