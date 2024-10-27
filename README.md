# Autorizador Caju


## Tecnologias Utilizadas

- **Kotlin**: Linguagem de programação principal usada para o backend.
- **Spring Boot**: Framework para facilitar o desenvolvimento da aplicação com configuração mínima.
- **Spring Data JPA**: Para facilitar o acesso e manipulação de dados no banco de dados MySQL.
- **Flyway**: Controle de versões de banco de dados.
- **MySQL**: Banco de dados relacional utilizado para persistência dos dados.
- **JUnit 5**: Framework para testes unitários.
- **Mockito**: Biblioteca para criação de mocks e testes de comportamento.
- **TestRestTemplate**: Utilizado para testes de integração com endpoints HTTP.
- **Docker**: Para rodar o banco de dados MySQL em contêiner.
- **Gradlew**: Gerenciador de dependências e automação de build.

## Funcionalidades
- Para o desafio, foi solicitado a criaçao de uma api de authorização de transaçao e realizar o debito conforme 
regras L1,L2,L3 e L4
- Para isso, foi criado duas apis, ``/api/v1/transactions/authorize`` e ``/api/v1/account``, para authorizar a transaçao e uma auxiliar para a criaçao de uma account para fins de teste


## Como Executar o Projeto
- **Passo 1**: clone o projeto em. https://github.com/andreLSantini/TestAutorizador 
  - ``git clone https://github.com/andreLSantini/TestAutorizador.git``
- **Passo 2**: Execute o docker-compose que se encontra dentro da pasta docker do projeto, ele ira criar o banco de dados
- **Passo 3**: Aqui voce pode opter por executar o projeto usando a IDE ou o commando
  - ``./gradlew clean build`` depois ``./gradlew bootRun``

## Swagger
Configurado swagger para auxiliar nos testes da aplicaçao.
http://localhost:8080/swagger-ui/index.html#/
#### Para TESTE do projeto, usar ```username: usuario e password: 123456```

## Contato

Se tiver qualquer dúvida ou sugestão, fique à vontade para entrar em contato.

## dev. Andre Luis Santini