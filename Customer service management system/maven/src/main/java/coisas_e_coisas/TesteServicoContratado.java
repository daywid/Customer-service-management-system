package coisas_e_coisas;

import java.util.Date;

public class TesteServicoContratado {

    public static void main(String[] args) {
        // Criando um novo serviço contratado 
        
        Date dataAtendimento = new Date();//Date para pegar a data atual.
        // Cria um cliente e o salva no banco de dados
        Cliente cliente = new Cliente("João", "Rua A, 123", "987654321", "joao@example.com");
        cliente.save();
        
        // Cria um serviço e o salva no banco de dados
        Servicos servico = new Servicos("Limpeza", "Descrição do serviço de limpeza", 100.0);
        double valor = servico.valor;
        servico.save();
        
        // Cria um prestador de servicos e o salva no banco de dados
        PrestadorDeServicos prestador = new PrestadorDeServicos("Prestador X", "123456789", "email@.com");
        prestador.save();
        
        // Cria o serviço contratado, inserindo o cliente e o serviço
        ServicoContratado novoServicoContratado = new ServicoContratado(dataAtendimento, cliente, servico, prestador);

        // Salvar o novo serviço contratado no banco de dados
        novoServicoContratado.save();
        
        // Verificar o ID atribuído ao serviço contratado
        System.out.println("ID do novo serviço contratado: " + novoServicoContratado.id);

        // Buscar um serviço contratado pelo ID
        ServicoContratado servicoBuscado = ServicoContratado.find_one(novoServicoContratado.id);

        // Exibir os dados do serviço contratado buscado
        if (servicoBuscado != null) {
            System.out.println("Serviço contratado encontrado:");
            System.out.println("ID: " + servicoBuscado.id);
            System.out.println("Data de atendimento: " + servicoBuscado.data_atendimento);
            System.out.println("Cliente: " + servicoBuscado.cliente.nome);
            System.out.println("Serviço: " + servicoBuscado.servico.nome);
            System.out.println("Prestador:" + servicoBuscado.prestador.nome);
        } else {
            System.out.println("Serviço contratado não encontrado.");
        }

        // Deletar o serviço contratado
        ServicoContratado servicoDeletar = servicoBuscado;
        if (servicoDeletar != null) {
            boolean deletado = servicoDeletar.delete();
            if(deletado)
            System.out.println("Serviço contratado deletado com sucesso.");
        } else {
            System.out.println("Erro ao deletar o serviço contratado.");
        } 

        // Deletar um cliente
        Cliente clienteParaDeletar = Cliente.find_one(cliente.id);
        if (clienteParaDeletar != null) {
        boolean deletado = clienteParaDeletar.delete();
        if (deletado) {
            System.out.println("Cliente deletado com sucesso.");
        } else {
            System.out.println("Falha ao deletar cliente.");
     }
    }       

    //deletar o serviço
    Servicos servicoDelete = Servicos.find_one(servico.id);
    if(servicoDelete != null){
        boolean deletado = servicoDelete.delete();
     if (deletado) {
        System.out.println("servico deletado com sucesso.");
    } else {
        System.out.println("Falha ao deletar o servico.");
     }
}

    //deletar o prestador
    PrestadorDeServicos prestadorDelete =  PrestadorDeServicos.find_one(prestador.id);
    if(prestadorDelete != null) {
    	boolean deletado = prestadorDelete.delete();
    	if(deletado) {
    		System.out.println("Prestador deletado com sucesso.");
    	}else {
    		System.out.println("Falha ao deletar prestador de servicos");
    	}
    	
    }else {
    	System.out.println("erro");
}
  

    }


}
