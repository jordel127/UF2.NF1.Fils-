public class ex6 {
    static int[] movX = {2, 1, -1, -2, -2, -1, 1, 2};
    static int[] movY = {1, 2, 2, 1, -1, -2, -2, -1};

    static int N = 6;

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int[][] tauler = new int[N][N];
                for (int k = 0; k < N; k++) {
                    for (int l = 0; l < N; l++) {
                        tauler[k][l] = -1;
                    }
                }

                tauler[i][j] = 1;

                if (solucionaCavall(i, j, 2, tauler)) {
                    System.out.println("Posició de inici del cavall: ( " + i + " , " + j + " );");
                    imprimeixTauler(tauler);
                }
            }
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Temps total d'execució: " + duration + " ms");
    }

    public static boolean solucionaCavall(int x, int y, int pas, int[][] tauler) {
        if (pas == N * N + 1) {
            return true;
        }

        for (int i = 0; i < 8; i++) {
            int novaX = x + movX[i];
            int novaY = y + movY[i];

            if (esValid(novaX, novaY, tauler)) {
                tauler[novaX][novaY] = pas;

                if (solucionaCavall(novaX, novaY, pas + 1, tauler)) {
                    return true;
                }
                tauler[novaX][novaY] = -1;
            }
        }

        return false;
    }

    public static boolean esValid(int x, int y, int[][] tauler) {
        return x >= 0 && y >= 0 && x < N && y < N && tauler[x][y] == -1;
    }

    public static void imprimeixTauler(int[][] tauler) {
        System.out.println("Posició final tauler:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print("| " + tauler[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("----------------------------");
    }
}
