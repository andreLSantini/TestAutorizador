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


## L4. Questão aberta
- A seguir está uma questão aberta sobre um recurso importante de um autorizador completo (que você não precisa implementar, apenas discuta da maneira que achar adequada, como texto, diagramas, etc.). Transações simultâneas: dado que o mesmo cartão de crédito pode ser utilizado em diferentes serviços online, existe uma pequena mas existente probabilidade de ocorrerem duas transações ao mesmo tempo. O que você faria para garantir que apenas uma transação por conta fosse processada em um determinado momento? Esteja ciente do fato de que todas as solicitações de transação são síncronas e devem ser processadas rapidamente (menos de 100 ms), ou a transação atingirá o timeout. 
- Resposta:
  - Acredito que, para garantir que não ocorram transações duplicadas, é necessário um mecanismo de controle. Para isso, implementei um identificador de idempotência (idempotency key), que assegura que uma transação possa ser executada apenas uma única vez. Realizo a persistência dessa transação e verifico se já existe uma transação com o mesmo identificador de idempotência. Além disso, anoto transações com @Transaction para garantir que seja executada ou de rollback. Acredito que essas abordagens ajudam a mitigar grande parte das transações duplicadas

## Video Explicativo
[![Watch the video](https://i.sstatic.net/Vp2cE.png)](https://youtu.be/gA6MmIRl0a8)

## Contato

Se tiver qualquer dúvida ou sugestão, fique à vontade para entrar em contato.

## dev. Andre Luis Santini

