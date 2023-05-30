package coisas_e_coisas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Servicos {
    
    public int id;
    public String nome;
    public String descricao;
    public double valor;
    
    public Servicos(String nome, String descricao, double valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Servicos(int id, String nome, String descricao, double valor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public static Servicos find_one(int id) {
    	Servicos Servico = null;
        Connection conexao = null;
        try {
            // Obter a conexão com o banco de dados
            conexao = ConexaoMySQL.getInstance().getConnection();
            
            // Preparar a consulta SQL para buscar o serviço pelo ID
            String sql = "SELECT * FROM servicos WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            
            // Executar a consulta SQL
            ResultSet rs = ps.executeQuery();

            // Verificar se há um resultado e criar um objeto Servicos com os dados
            if (rs.next()) {
                Servico = new Servicos(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getDouble("valor")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar serviço: " + e.getMessage());
        } 
        return Servico;
    }

    public void save() {
        Connection conexao = null;
        try {
            // Obter a conexão com o banco de dados
            conexao = ConexaoMySQL.getInstance().getConnection();
            
            if (this.id == 0) {
                // Inserir novo serviço
                String sql = "INSERT INTO servicos (nome, descricao, valor) VALUES (?, ?, ?)";
                PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                
                // Definir os valores dos parâmetros na consulta SQL
                ps.setString(1, this.nome);
                ps.setString(2, this.descricao);
                ps.setDouble(3, this.valor);
                
                // Executar a atualização no banco de dados e obter as chaves geradas automaticamente
                int retorno = ps.executeUpdate();
                System.out.println(retorno);

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    this.id = rs.getInt(1);
                }
            } else {
                // Atualizar serviço existente
                String sql = "UPDATE servicos SET nome = ?, descricao = ?, valor = ? WHERE id = ?";
                PreparedStatement ps = conexao.prepareStatement(sql);
                
                // Definir os valores dos parâmetros na consulta SQL
                ps.setString(1, this.nome);
                ps.setString(2, this.descricao);
                ps.setDouble(3, this.valor);
                ps.setInt(4, this.id);
                
                // Executar a atualização no banco de dados
                int retorno = ps.executeUpdate();
                System.out.println(retorno);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao salvar serviço: " + e.getMessage());
        } 
    }
    
    public boolean delete() {
        Connection conexao = null;
        try {
            // Obter a conexão com o banco de dados
            conexao = ConexaoMySQL.getInstance().getConnection();
            
            String sql = "DELETE FROM servicos WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, this.id);

            // Executar a exclusão no banco de dados
            int rowsAffected = ps.executeUpdate();

            // Verificar se a exclusão foi bem-sucedida
            if (rowsAffected > 0) {
                this.id = 0;
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar serviço: " + e.getMessage());
        } 
        return false;
    }
}
