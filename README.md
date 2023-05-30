# Sistema de Gerenciamento de Atendimento a Clientes

Este é um sistema de gerenciamento de atendimento a clientes que permite registrar clientes, serviços, prestadores de serviços e os serviços contratados. O sistema é implementado em Java e utiliza um banco de dados MySQL para armazenar os dados.

## Configuração do Banco de Dados

Antes de executar o sistema, é necessário configurar um banco de dados MySQL com as tabelas necessárias. Siga os passos abaixo:

1. Crie um banco de dados chamado "coisas_e_coisas":

```sql
CREATE DATABASE coisas_e_coisas;
```

2. Selecione o banco de dados criado:

```sql
USE coisas_e_coisas;
```

3. Crie a tabela "clientes" com as colunas "id", "nome", "endereco", "telefone" e "email":

```sql
CREATE TABLE clientes (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  endereco VARCHAR(255) NOT NULL,
  telefone VARCHAR(20) NOT NULL,
  email VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);
```

4. Insira alguns registros de exemplo na tabela "clientes":

```sql
INSERT INTO clientes (nome, endereco, telefone, email) VALUES 
('João da Silva', 'Rua A, 123', '(11) 9999-8888', 'joao.silva@example.com'),
('Lucas Roberto', 'Rua das Flores, 123', '(11) 98888-7777', 'ana.silva@gmail.com'),
('José Santos', 'Avenida Paulista, 456', '(11) 97777-6666', 'jose.santos@hotmail.com');
```

5. Crie a tabela "servicos" com as colunas "id", "nome", "descricao" e "valor":

```sql
CREATE TABLE servicos (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  descricao TEXT NOT NULL,
  valor DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (id)
);
```

6. Insira alguns registros de exemplo na tabela "servicos":

```sql
INSERT INTO servicos (nome, descricao, valor) VALUES
('Reparos elétricos', 'Realização de reparos em instalações elétricas residenciais e comerciais', 120.00),
('Reparos hidráulicos', 'Realização de reparos em encanamentos e tubulações hidráulicas', 150.00),
('Reparos em alvenaria', 'Realização de reparos em estruturas e superfícies em alvenaria', 180.00);
```

7. Crie a tabela "prestador_de_servicos" com as colunas "id", "nome", "telefone" e "email":

```sql
CREATE TABLE prestador_de_servicos (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  telefone VARCHAR(20) NOT NULL,
  email VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);
```

8. Insira alguns registros de exemplo na tabela "prestador_de_servicos":

```sql
INSERT INTO prestador_de_servicos (nome, telefone, email) VALUES
('João da Silva', '(11) 9999-8888', 'joao.silva@coisasecoisas.com'),
('Maria dos Santos', '(11) 9888-7777', 'maria.santos@coisasecoisas);

```

9. Crie a tabela "servicos_contratados" com as colunas "id", "id_cliente", "id_servico" e "id_prestador":

```sql
CREATE TABLE servicos_contratados (
  id INT NOT NULL AUTO_INCREMENT,
  id_cliente INT NOT NULL,
  id_servico INT NOT NULL,
  id_prestador INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_cliente) REFERENCES clientes(id),
  FOREIGN KEY (id_servico) REFERENCES servicos(id),
  FOREIGN KEY (id_prestador) REFERENCES prestador_de_servicos(id)
);
```

10. Compile e execute o sistema. Certifique-se de que o arquivo `mysql-connector-java.jar` esteja adicionado ao classpath do projeto.

11. O sistema permitirá o gerenciamento de clientes, serviços, prestadores de serviços e os serviços contratados. Ele fornecerá opções para adicionar, atualizar, remover e visualizar os registros.

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
