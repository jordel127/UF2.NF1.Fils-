package preparacionExamen;

import java.util.concurrent.Semaphore;

public class Semaforo {

    // Declaració del semàfor
    // el semàfor té un valor inicial de 1.
    // 1 es la cantita de Fils que poden executar aquest semafor a la vegada.
    private final Semaphore semafor = new Semaphore(1);

    public void decirHola() throws InterruptedException {
        // Aquesta instrucció bloqueja el fil actual
        // i espera que el semàfor sigui disponible.
        semafor.acquire();

        System.out.println("Hola!");


        // Aquesta instrucció libera el semàfor
        // i el fil que ha bloquejat-lo pot continuar.
        semafor.release();
    }
}
