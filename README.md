# Sistema de Gerenciamento de Atendimento a Clientes

Este é um protótipo de um sistema de gerenciamento de atendimento a clientes que permite registrar clientes, serviços, prestadores de serviços e os serviços contratados. O sistema é implementado em Java e utiliza um banco de dados MySQL para armazenar os dados.

## Configuração do Banco de Dados

Antes de executar o sistema, é necessário configurar um banco de dados MySQL com as tabelas necessárias. Siga os passos abaixo:

1. Certifique-se de ter o servidor MySQL instalado em seu ambiente de desenvolvimento.

2. Crie um novo banco de dados no MySQL:

```sql
CREATE DATABASE nome_do_banco_de_dados;
```

3. Acesse o arquivo ConexaoMySQL.java e atualize as configurações de conexão com o banco de dados, como o nome do banco, nome de usuário e senha.

4. Compile e execute o sistema. Certifique-se de que o arquivo `mysql-connector-java.jar` esteja adicionado ao classpath do projeto.

5. O sistema permitirá o gerenciamento de clientes, serviços, prestadores de serviços e os serviços contratados. Ele fornecerá opções para adicionar, atualizar, remover e visualizar os registros.

## Funcionalidades do Sistema


O sistema oferece as seguintes funcionalidades:


#### Conexão com o Banco de Dados

A classe `ConexaoMySQL` é responsável por estabelecer a conexão com o banco de dados MySQL. Ela utiliza a biblioteca `mysql-connector-java` para realizar a conexão e executar as consultas. Certifique-se de adicionar o arquivo JAR do conector MySQL ao classpath do projeto.

#### Busca de um Registro Específico

As classes DAO (Data Access Object) fornecem métodos para buscar registros específicos no banco de dados. Por exemplo, a classe `Cliente` contém o método `findOne()` que permite buscar um cliente específico no banco de dados com base em seu ID. O método retorna um objeto `Cliente` contendo as informações do cliente encontrado.

Exemplo de uso:

```java
Cliente cliente = new Cliente();
cliente.findOne(cliente.id);
```

#### Salvamento de Registros

As classes DAO também possuem métodos `save()` para salvar ou atualizar registros no banco de dados. Por exemplo, a classe `Cliente` possui o método `save()` que permite salvar um novo cliente no banco de dados ou atualizar um cliente existente. O método recebe um objeto `Cliente` como parâmetro e verifica se o cliente já possui um ID atribuído. Se o ID estiver presente, o método atualiza os dados do cliente no banco de dados, caso contrário, ele insere um novo registro.

Exemplo de uso:

```java
Cliente cliente= new Cliente("João da Silva", "joao@example.com", "123456789");
cliente.save();
```

#### Exclusão de Registros

Os métodos `delete()` nas classes DAO permitem excluir registros do banco de dados com base em um critério específico. Por exemplo, a classe `Cliente` possui o método `delete()` que permite excluir um cliente do banco de dados com base em seu ID.

Exemplo de uso:

```java
cliente.delete(cliente.id);
```

## Como executar o Sistema

1. Clone este repositório para o seu ambiente local:

```bash
git clone https://github.com/seu-usuario/sistema-gerenciamento-atendimento-clientes.git
```

2. Certifique-se de ter o Java Development Kit (JDK) instalado em seu computador.

3. Navegue até o diretório do projeto:

```bash
cd sistema-gerenciamento-atendimento-clientes
```

4. Compile o projeto:

```bash
javac -cp mysql-connector-java.jar Main.java
```

5. Execute o sistema:

```bash
java -cp .:mysql-connector-java.jar Main
```

6. Agora você pode usar as opções fornecidas pelo sistema para gerenciar os registros de clientes, serviços, prestadores de serviços e serviços contratados.

## Contribuições

Contribuições são bem-vindas! Se você encontrar algum problema ou tiver sugestões de melhorias, fique à vontade para abrir uma issue ou enviar um pull request.

## Licença

Este projeto está licenciado sob a [MIT License](https://opensource.org/licenses/MIT).
