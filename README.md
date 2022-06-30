# Projeto de Testes da API_Simulação

# Sobre o projeto

Projeto realizado para o desafio da SoftDesign de automação de API.

# Tecnologias utilizadas
- Java (Ver. 1.8.0)
- Maven (Ver. 3.8.1)
- JUnit (Ver. 4.12)
- RestAssured (Ver. 5.1.1)
- Maven Surefire Report Plugin (Ver. 3.0.0-M7)
- IntelliJ IDEA (Ver. 2020.2.2)

# Arquitetura do projeto

A arquitetura é baseada a apresentada na palestra "Criando uma arquitetura para seus testes de API com RestAssured" por Elias Nogueira. Adaptando a divisão de classes semelhante ao design pattern PageObjects mas mantendo-as em um único projeto.
- Link da palestra: https://www.youtube.com/watch?v=hhvHGLfoQQQ (minuto 20)

# Como executar o projeto

Pré-requisitos: Java 8

## Clonar o repositório
- https://github.com/rafaelleitesh/api_simulacao_test

## Executar o projeto
- Executar no terminal o comando: 
mvn clean surefire-report:report

## Relatório
- Após executado o projeto o Surefire Report Plugin gera um relatório surefire-report.html no caminho target/site.
- Abra o arquivo .html no navegador para visualizar os resultados dos testes executados e evidências das falhas.


# Autor

Rafael Cardoso Leite

https://www.linkedin.com/in/rafael-cardoso-leite/

