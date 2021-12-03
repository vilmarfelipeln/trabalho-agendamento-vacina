package controller;

import dao.DatabaseConnector;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Classe responsável por disponibilizar métodos para realizar o login no sistema.
 */
public class LoginController {

    private LoginController() {
    }

    /**
     * Realiza uma autenticação. Retorna <code>TRUE</code> caso seja possível autenticar, ou <code>FALSE</code> caso contrário.
     *
     * @param usuario Usuário para autenticação.
     * @param senha   Senha para autenticação.
     * @return <code>TRUE</code> caso seja possível autenticar, ou <code>FALSE</code> caso contrário.
     */
    public static boolean autenticar(String usuario, String senha) {
        try (Connection conexao = DatabaseConnector.conectarBD()) {
            if (conexao != null) {
                String sql = "select * from usuario where login = ? and senha = ?";

                try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                    ps.setString(1, usuario);
                    ps.setString(2, senha);

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Usuário autenticado!");
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválidos!");
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao realizar a autenticação!\n" + e, "Erro: ", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    /**
     * @param usuario
     * @param senha
     * @return
     */
    public static String buscarCpf(String usuario, String senha) {
        try (Connection conexao = DatabaseConnector.conectarBD()) {
            if (conexao != null) {
                String sql = "select cpf from usuario where login = ? and senha = ?";

                try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                    ps.setString(1, usuario);
                    ps.setString(2, senha);

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        return (rs.getString("cpf"));
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar CPF!\n" + e, "Erro: ", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return null;
    }
}