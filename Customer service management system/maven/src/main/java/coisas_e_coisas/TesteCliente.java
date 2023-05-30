package coisas_e_coisas;

public class TesteCliente {

  public static void main(String[] args) {
        
        // Buscar cliente pelo ID
        Cliente cliente = Cliente.find_one(2);
        if (cliente != null) {
            System.out.println("Cliente encontrado: " + cliente.nome + " " + "ID:" + cliente.id) ;
        } else {
            System.out.println("Cliente não encontrado.");
        }

        // Inserir um novo cliente
        Cliente novoCliente = new Cliente("Maria Oliveira", "Rua B, 456", "(11) 9999-7777", "maria.oliveira@example.com");
        novoCliente.save();
        System.out.println("Novo cliente inserido. ID: " + novoCliente.id);

        // Atualizar um cliente existente
        Cliente clienteExistente = Cliente.find_one(2);
        if (clienteExistente != null) {
        clienteExistente.nome = "Ana Santos";
        clienteExistente.endereco = "Nova Rua, 456";
        clienteExistente.telefone = "(11) 98765-4321";
        clienteExistente.email = "ana.santos@gmail.com";
        clienteExistente.save();
        System.out.println("Cliente atualizado: " + clienteExistente.nome + " (ID: " + clienteExistente.id + ")");
        } else {
    System.out.println("Cliente não encontrado.");
        }
        
        // Deletar um cliente
        Cliente clienteParaDeletar = Cliente.find_one(novoCliente.id);
        if (clienteParaDeletar != null) {
        boolean deletado = clienteParaDeletar.delete();//cria uma variavel em boolean, para verificar se a exclusão foi bem sucedida ou não.
        if (deletado) {
            System.out.println("Cliente deletado com sucesso.");
        } else {
            System.out.println("Falha ao deletar cliente.");
     }
}         else {
            System.out.println("Cliente não encontrado.");
    }

    }

}
