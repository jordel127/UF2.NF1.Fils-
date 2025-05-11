package Processos;

import java.sql.*;
import java.util.concurrent.Semaphore;

public class BD {
    private static String url = "jdbc:mysql://localhost:3306/filsiprocessos";
    private static String user = "root";
    private static String pass = "1212";

    private final Semaphore semafor = new Semaphore(1);

    public void mostrarRegistres() {
        Connection connexio = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            semafor.acquire();
            System.out.println(Thread.currentThread().getName() + " ha adquirit el semàfor. Permisos restants: " + semafor.availablePermits());

            Class.forName("com.mysql.cj.jdbc.Driver");

            connexio = DriverManager.getConnection(url, user, pass);
            stmt = connexio.createStatement();

            rs = stmt.executeQuery("SELECT * FROM taula_noms");

            Thread.sleep(3000);

            while (rs.next()) {
                int id = rs.getInt("idtaula1");
                String nom = rs.getString("nom");
                String cognom = rs.getString("cognom");
                System.out.println("ID: " + id + ", Nom: " + nom + ", Cognom: " + cognom);
            }

        } catch (ClassNotFoundException e) {
            System.err.println("No s'ha trobat el driver JDBC.");
        } catch (SQLException e) {
            System.err.println("Error amb la base de dades: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Interrupció mentre esperava.");
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
            try { if (stmt != null) stmt.close(); } catch (SQLException ignored) {}
            try { if (connexio != null) connexio.close(); } catch (SQLException ignored) {}

            semafor.release();
            System.out.println(Thread.currentThread().getName() + " ha alliberat el semàfor. Permisos disponibles: " + semafor.availablePermits());
        }
    }
}
