package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 */
public class DatabaseConnector {

    private DatabaseConnector() {

    }

    /**
     * @return
     */
    public static Connection conectarBD() {
        Connection conexao = null;

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/agendamento_vacinacao";
        String user = "root";
        String password = "";

        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password); // NOSONAR
            return conexao;
        } catch (Exception e) {
            System.out.println("Erro de conexão no banco!\n" + e);
            System.out.println("URL: " + url);
            System.out.println("User: " + user);
            System.out.println("Password: " + password);
            return null;
        }
    }
}
