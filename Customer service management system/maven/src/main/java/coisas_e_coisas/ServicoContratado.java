package coisas_e_coisas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ServicoContratado {

    public int id;
    public Date data_atendimento;
    public Cliente cliente;
    public Servicos servico;
    public PrestadorDeServicos prestador;
    //public double valor;

    public ServicoContratado(Date data_atendimento, Cliente cliente, Servicos servico, PrestadorDeServicos prestador) {
        this.data_atendimento = data_atendimento;
        this.cliente = cliente;
        this.servico = servico;
        this.prestador = prestador;
        //this.valor = valor;
    }
   
    public ServicoContratado(int id, Date data_atendimento, Cliente cliente, Servicos servico, PrestadorDeServicos prestador) {
        this.id = id;
        this.data_atendimento = data_atendimento;
        this.cliente = cliente;
        this.servico = servico;
        this.prestador = prestador;
       // this.valor = valor;
    }

    public static ServicoContratado find_one(int id) {
    	ServicoContratado servicoContratado = null;
        Connection conexao = null;
        try  {
            // Obter a conexão com o banco de dados
            conexao = ConexaoMySQL.getInstance().getConnection();
          
         // Preparar a consulta SQL para buscar o serviço pelo ID
            String sql = "SELECT * FROM servicos_contratados WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            
         // Executar a consulta SQL
            ResultSet rs = ps.executeQuery();

         // Verificar se há um resultado e criar um objeto Servicos com os dados
            if (rs.next()) {
                int clienteId = rs.getInt("id_cliente");
                int servicoId = rs.getInt("id_servico");
                int prestadorId = rs.getInt("id_prestador_de_servicos");
                java.sql.Date data_atendimento = rs.getDate("data_atendimento");
                //double valor = rs.getDouble("valor");

                Cliente cliente = Cliente.find_one(clienteId);
                Servicos servico = Servicos.find_one(servicoId);
                PrestadorDeServicos prestador = PrestadorDeServicos.find_one(prestadorId);

                servicoContratado =  new ServicoContratado(id, data_atendimento, cliente, servico, prestador);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar serviço contratado: " + e.getMessage());
        }
        return servicoContratado;
    }

    public void save() {
        Connection conexao = null;
        try {
            // Obter a conexão com o banco de dados
            conexao = ConexaoMySQL.getInstance().getConnection();
            if (this.id == 0) {
                // Inserir novo serviço contratado
                String sql = "INSERT INTO servicos_contratados (data_atendimento, id_cliente, id_servico, id_prestador_de_servicos) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    
                // Definir os valores dos parâmetros na consulta SQL
                ps.setDate(1, new java.sql.Date(this.data_atendimento.getTime()));
                ps.setInt(2, this.cliente.id);
                ps.setInt(3, this.servico.id);
                ps.setInt(4, this.prestador.id);
                //ps.setDouble(5, this.valor);
                int retorno = ps.executeUpdate();
                System.out.println(retorno);
    
                // Obter o ID gerado automaticamente pelo banco de dados
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    this.id = rs.getInt(1);
                }
            } else {
                // Atualizar serviço contratado existente
                String sql = "UPDATE servicos_contratados SET data_atendimento = ?, id_cliente = ?, id_servico = ?, id_prestador_de_servicos = ? WHERE id = ?";
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setDate(1, new java.sql.Date(this.data_atendimento.getTime()));
                ps.setInt(2, this.cliente.id);
                ps.setInt(3, this.servico.id);
                ps.setInt(4, this.prestador.id);
               // ps.setDouble(5, this.valor);
                ps.setInt(5, this.id);
                int retorno = ps.executeUpdate();
                System.out.println(retorno);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao salvar serviço contratado: " + e.getMessage());
        }
    }
    
    public boolean delete() {
        Connection conexao = null;
        try {
             // Obter a conexão com o banco de dados
             conexao = ConexaoMySQL.getInstance().getConnection();
            String sql = "DELETE FROM servicos_contratados WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, this.id);

            // Executar a exclusão no banco de dados
            int rowsAffected = ps.executeUpdate();

            // Verificar se a exclusão foi bem-sucedida
            if (rowsAffected > 0) {
                this.id = 0;
                return true;
            }
            System.out.println(rowsAffected);
        } catch (SQLException e) {
            System.out.println("Erro ao excluir serviço contratado: " + e.getMessage());
        }
        return false;
    }
}    