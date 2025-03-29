/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gesbibliothèque.classe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Stéphane Nana
 */
public class Connexion {

    private static final String URL = "jdbc:mysql://localhost:3306/bibliodb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Instance unique de connexion
    private static Connection connection = null;

    // Constructeur privé pour empêcher l'instanciation de cette classe depuis l'extérieur
    private Connexion() {}

    // Méthode pour obtenir l'unique instance de connexion
    public static Connection getConnection() {
        if (connection == null) { // Si aucune connexion n'est encore établie
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connexion à la base de données établie.");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
            }
        }
        return connection; // Retourne l'unique connexion
    }

    // Méthode pour fermer la connexion lorsqu'on arrête l'application
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
     
  
}
