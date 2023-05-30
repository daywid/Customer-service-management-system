package coisas_e_coisas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Cliente {
    
    public int id;
    public String nome;
    public String endereco;
    public String telefone;
    public String email;
 
    // Construtor sem id
    public Cliente(String nome, String endereco, String telefone, String email) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }
    // Construtor com id
    public Cliente(int id, String nome, String endereco, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }
    
    public static Cliente find_one(int id) {
    	Cliente Cliente = null;
        Connection conexao = null;
        try {
            // Obter a conexão com o banco de dados
            conexao = ConexaoMySQL.getInstance().getConnection();
            
            // Preparar a consulta SQL para buscar o cliente pelo ID
            String sql = "SELECT * FROM clientes WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            
            // Executar a consulta SQL
            ResultSet rs = ps.executeQuery();

            // Verificar se há um resultado e criar um objeto Cliente com os dados
            if (rs.next()) {
               Cliente = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
                
            }
        } catch (SQLException e) { //exibir mensagem caso ocorra algum erro
            System.out.println("Erro ao buscar cliente: " + e.getMessage());
        } 
    
        return Cliente;
    }

    public void save() {
        Connection conexao = null;
        try {
            // Obter a conexão com o banco de dados
            conexao = ConexaoMySQL.getInstance().getConnection();
            
            if (this.id == 0) {
                // Inserir novo cliente
                String sql = "INSERT INTO clientes (nome, endereco, telefone, email) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                
                // Definir os valores dos parâmetros na consulta SQL
                ps.setString(1, this.nome);
                ps.setString(2, this.endereco);
                ps.setString(3, this.telefone);
                ps.setString(4, this.email);
                
                // Executar a atualização no banco de dados e obter as chaves geradas automaticamente
                int retorno = ps.executeUpdate();
                System.out.println(retorno);

                 // Obter o ID gerado automaticamente pelo banco de dados
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    this.id = rs.getInt(1);
                }
            } else {
                // Atualizar cliente existente
                String sql = "UPDATE clientes SET nome = ?, endereco = ?, telefone = ?, email = ? WHERE id = ?";
                PreparedStatement ps = conexao.prepareStatement(sql);
                
                // Definir os valores dos parâmetros na consulta SQL
                ps.setString(1, this.nome);
                ps.setString(2, this.endereco);
                ps.setString(3, this.telefone);
                ps.setString(4, this.email);
                ps.setInt(5, this.id);
                
                // Executar a atualização no banco de dados
                int retorno = ps.executeUpdate();
                System.out.println(retorno);
                
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao salvar cliente: " + e.getMessage());}
        } 
    
    public boolean delete() {
        Connection conexao = null;
        try {
            // Obter a conexão com o banco de dados
            conexao = ConexaoMySQL.getInstance().getConnection();
            
            String sql = "DELETE FROM clientes WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, this.id);

            // Executar a exclusão no banco de dados
            int rowsAffected = ps.executeUpdate();

            // Verificar se a exclusão foi bem-sucedida
            if (rowsAffected > 0) {
                this.id = 0;
                return true;
            }
        } catch (SQLException e) {//obter mensagem detalhando o erro, caso este ocorra
            System.out.println("Erro ao deletar cliente: " + e.getMessage());
            e.printStackTrace();
        } 
        return false;
    }
}
