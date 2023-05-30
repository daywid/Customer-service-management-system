package coisas_e_coisas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {

    // Declaração de variáveis e objetos
    private static ConexaoMySQL instance;
    private Connection conexao;

    private ConexaoMySQL() {
        try {
            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
             // Configurações de conexão com o banco de dados
            String url = "jdbc:mysql://localhost:3306/coisas_e_coisas";
            String usuario = "root";
            String senha = "root";
            
            // Estabelece a conexão com o banco de dados
            conexao = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
    
    // Obtém a instância única da classe ConexaoMySQL
    public static ConexaoMySQL getInstance() {
        if (instance == null) {
            synchronized (ConexaoMySQL.class) {//synchronized garante que a criação da instância seja feita de forma thread-safe, evitando problemas de concorrência.
                if (instance == null) {
                    instance = new ConexaoMySQL();
                }
            }
        }
        return instance;
    }
    
    // Retorna a conexão com o banco de dados
    public Connection getConnection() {
        return conexao;
    } 

}
