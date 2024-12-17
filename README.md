# Literalura 📚

Literalura é uma aplicação desenvolvida em **Spring Boot** com integração ao banco de dados **PostgreSQL**, que permite buscar e gerenciar dados de livros e autores utilizando a API pública [Gutendex](https://gutendex.com/). A interação com o usuário ocorre através de um menu no terminal, permitindo diversas operações relacionadas a livros e autores.

## Funcionalidades 

A aplicação oferece as seguintes opções de interação no menu do prompt de comando:

```less
Escolha o número de sua opção: 
    1- buscar livro pelo título
    2- listar livros registrados
    3- listar autores registrados
    4- listar autores vivos em um determinado ano
    5- listar livros em um determinado idioma
    0- sair 
```


## Tecnologias Utilizadas 🛠

- **Java 17**: Linguagem de programação principal.
- **Spring Boot**: Framework para simplificar o desenvolvimento de aplicações web.
  - **Spring Data JPA**: Para integração com o banco de dados.
- **PostgreSQL**: Banco de dados relacional utilizado para persistência.
- **Maven**: Gerenciamento de dependências e build.
- **Gutendex API**: Fonte de dados de livros e autores.

## Pré-requisitos 📜

Antes de rodar o projeto, certifique-se de ter instalado:

- **Java 17** ou superior.
- **Maven**.
- **PostgreSQL**.
- Acesso à API Gutendex.

### Configuração do Banco de Dados

Certifique-se de criar um banco de dados PostgreSQL e configurar as credenciais no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:8080/literalura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

### Estrutura do Projeto 🧱

- **Model**: Representa as entidades (livros e autores).

- **Service**: Contém a lógica por trás da aplicação.

- **Repository**: Interface para interagir com o banco de dados.

- **Principal**: Responsável pela interação com o usuário.

### Melhorias futuras 💡

- Implementar testes unitários.

- Adicionar suporte para mais opções de filtragem e busca.

- Criar uma interface web para facilitar o uso da aplicação.




<div>
    <p>
        <img src="badge literalura.png" height="300" tittle="site"> 
    </p>
</div>
