import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        fil timer = new fil(true);
        fil fil1 = new fil(10);
        fil fil2 = new fil(5);


        timer.start();
        fil1.start();
        fil2.start();

        fil1.join();
        fil2.join();

        System.out.println("Suma total de los factoriales: " + fil.getSumaFils());

        timer.stopTimer();
        timer.join();
    }
}

class fil extends Thread {
    private int number;
    private static long sumaFils = 0;
    private boolean isTimer;
    private Instant startTime;

    public fil(int number) {
        this.number = number;
        this.isTimer = false;
    }

    public fil(boolean isTimer) {
        this.isTimer = isTimer;
    }

    private long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public void run() {
        if (isTimer) {
            startTime = Instant.now();
            while (!Thread.interrupted()) {

            }
            Instant endTime = Instant.now();
            long elapsedTime = Duration.between(startTime, endTime).toNanos();
            System.out.println("Tiempo total de ejecución: " + elapsedTime);
        } else {
            long result = factorial(number);
            synchronized (fil.class) {
                sumaFils += result;
            }
            System.out.println("Factorial de " + number + " = " + result);
        }
    }

    public static long getSumaFils() {
        return sumaFils;
    }

    public void stopTimer() {
        this.interrupt();
    }
}

