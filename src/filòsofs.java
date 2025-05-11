//8.- Implementa el següent enunciat utilitzant la sincronització de fils:
// ‘N filòsofs estan asseguts a taula amb un gran bol d'espaguetis al mig i N forquilles
// distribuïdes al voltant de la taula de manera que hi hagi una a l'esquerra i una altre
// a la dreta de cada filòsof. Els espaguetis estan tan enredats que cada filòsof
// necessita 2 forquilles per menjar-se’ls. Un filòsof només pot utilitzar les forquilles
// que són immediatament a la seva esquerra i dreta. Un filòsof executa repetidament
// la següent seqüència de accions:
// pensar;
// agafar forquilles de la taula;
// menjar;
// tornar a posar les forquilles a la taula. ’

public class filòsofs {
    public static void main(String[] args) {

        int numFilosofs = 3;
        boolean[] forquilles = new boolean[numFilosofs];

        // marcar todas como disponibles
        for (int i = 0; i < numFilosofs; i++) {
            forquilles[i] = true;
        }

        // Crear i iniciar els filòsofs
        for (int i = 0; i < numFilosofs; i++) {
            new filòsof(i, forquilles).start();
        }
    }
}

class filòsof extends Thread {

    private int id;
    private boolean[] forquilles;
    private int numFilosofs;

    public filòsof(int filòsofs, boolean[] forquilles) {
        this.id = filòsofs;
        this.forquilles = forquilles;
        this.numFilosofs = forquilles.length;
    }

    public void run() {
        while (true) {
            try {
                pensar();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            agafarForquilles();
            menjar();
            tornarForquilles();
        }
    }

    private void pensar() throws InterruptedException {
        boolean teLasForquilles = false;
        int dreta = (id + 1) % numFilosofs;

        while (!teLasForquilles) {
            synchronized (filòsof.class) {
                if (forquilles[id] && forquilles[dreta]) {
                    System.out.println("El filòsof " + id + " te las 2 forquilles lliures.");
                    teLasForquilles = true;
                } else {
                    filòsof.class.wait();
                }
            }
        }

    }

    private void agafarForquilles() {
        int dreta = (id + 1) % numFilosofs;
        forquilles[id] = false;
        forquilles[dreta] = false;
    }

    private void menjar() {
        System.out.println("El filòsof " + id + " menja.");
    }

    private void tornarForquilles() {
        System.out.println("El filòsof " + id + " deixa les forquilles a la taula.");
        int dreta = (id + 1) % numFilosofs;
        synchronized (filòsof.class) {
            forquilles[id] = true;
            forquilles[dreta] = true;
            filòsof.class.notifyAll();
        }
    }
}
