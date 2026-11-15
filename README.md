# 🌱 AgroFácil — API de Gestão Agrícola

## Integrantes da equipe

- Igor Brunelli Ralo - rm 555035
- Jose Antonio Kretzer Rodriguez - rm 555523
- victor mattenhauer lopes capp - rm 555753

## Global Solution 2026.2

Disciplina: Arquitetura Orientada a Serviços e Web Services

Projeto: AgroFácil — API de Gestão Agrícola

## 📌 Sobre o projeto

O AgroFácil é uma API REST desenvolvida como projeto acadêmico da disciplina de Service-Oriented Architecture (SOA).

O sistema tem como objetivo auxiliar no gerenciamento de informações agrícolas, permitindo cadastrar agricultores, controlar suas plantações e registrar as colheitas realizadas.

A aplicação foi construída utilizando Java, Spring Boot e PostgreSQL, seguindo uma arquitetura em camadas e boas práticas de desenvolvimento de APIs REST.

## 🎯 Objetivo

Oferecer uma solução backend para organizar dados de agricultores, plantações e colheitas, garantindo a integridade das informações por meio de validações, relacionamentos no banco de dados e regras de negócio.

## 🛠️ Tecnologias utilizadas

- Java 21
- Spring Boot 4.0.9
- Spring Web MVC
- Spring Data JPA
- Hibernate
- PostgreSQL 17
- Flyway
- Maven
- Swagger / OpenAPI
- Springdoc OpenAPI 3.0.0
- JUnit
- Mockito

## 🏗️ Arquitetura do projeto

A aplicação utiliza uma arquitetura em camadas:

- **Controller:** recebe as requisições HTTP e disponibiliza os endpoints REST.
- **Service:** implementa as regras de negócio e validações.
- **Repository:** realiza a comunicação com o banco de dados utilizando Spring Data JPA.
- **Model:** representa as entidades do sistema.
- **DTO:** define os objetos utilizados para entrada e saída de dados.

Essa separação facilita a manutenção, a organização e os testes da aplicação.

## 🌾 Entidades do sistema

### Agricultor

Representa o responsável pelas atividades agrícolas.

Atributos:

- id
- nome
- telefone
- cidade
- email (opcional)

### Plantação

Representa uma área cultivada pertencente a um agricultor.

Atributos:

- id
- agricultorId
- cultura
- areaHectares
- dataPlantio

### Colheita

Representa a produção obtida em determinada plantação.

Atributos:

- id
- plantacaoId
- quantidadeKg
- dataColheita

## 🔗 Relacionamentos

O banco de dados possui os seguintes relacionamentos:

- Um agricultor pode possuir várias plantações.
- Cada plantação pertence a um agricultor.
- Uma plantação pode possuir várias colheitas.
- Cada colheita pertence a uma plantação.

Os relacionamentos são implementados utilizando chaves estrangeiras no PostgreSQL e mapeamento JPA.

## ⚙️ Funcionalidades

### Gerenciamento de agricultores

- Cadastrar agricultor.
- Listar agricultores.
- Buscar agricultor por ID.
- Atualizar agricultor.
- Excluir agricultor.

### Gerenciamento de plantações

- Cadastrar plantação.
- Listar plantações.
- Buscar plantação por ID.
- Listar plantações de determinado agricultor.
- Atualizar plantação.
- Excluir plantação.

### Gerenciamento de colheitas

- Cadastrar colheita.
- Listar colheitas.
- Buscar colheita por ID.
- Atualizar colheita.
- Excluir colheita.

## 📋 Regras de negócio

O sistema implementa validações para impedir o cadastro de informações inválidas.

### Regras dos agricultores

- O nome é obrigatório.
- A cidade é obrigatória.
- Operações de consulta e atualização devem verificar a existência do agricultor.

### Regras das plantações

- A cultura agrícola é obrigatória.
- A área cultivada deve ser maior que zero.
- A data de plantio é obrigatória.
- Toda plantação deve estar vinculada a um agricultor.
- O agricultor informado deve existir.

### Regras das colheitas

- Toda colheita deve estar vinculada a uma plantação.
- A quantidade colhida deve ser maior que zero.
- A data da colheita é obrigatória.
- A plantação informada deve existir.
- A data da colheita não pode ser anterior à data do plantio.

## 🌐 Endpoints da API

A API utiliza o endereço base:

`http://localhost:8080`

### Agricultores

| Método | Endpoint           | Descrição                |
| ------ | ------------------ | ------------------------ |
| POST   | /agricultores      | Cadastrar agricultor     |
| GET    | /agricultores      | Listar agricultores      |
| GET    | /agricultores/{id} | Buscar agricultor por ID |
| PUT    | /agricultores/{id} | Atualizar agricultor     |
| DELETE | /agricultores/{id} | Excluir agricultor       |

### Plantações

| Método | Endpoint                              | Descrição                          |
| ------ | ------------------------------------- | ---------------------------------- |
| POST   | /plantacoes                           | Cadastrar plantação                |
| GET    | /plantacoes                           | Listar plantações                  |
| GET    | /plantacoes/{id}                      | Buscar plantação por ID            |
| GET    | /plantacoes/agricultor/{agricultorId} | Listar plantações de um agricultor |
| PUT    | /plantacoes/{id}                      | Atualizar plantação                |
| DELETE | /plantacoes/{id}                      | Excluir plantação                  |

### Colheitas

| Método | Endpoint        | Descrição              |
| ------ | --------------- | ---------------------- |
| POST   | /colheitas      | Cadastrar colheita     |
| GET    | /colheitas      | Listar colheitas       |
| GET    | /colheitas/{id} | Buscar colheita por ID |
| PUT    | /colheitas/{id} | Atualizar colheita     |
| DELETE | /colheitas/{id} | Excluir colheita       |

## 📦 Exemplos de requisições

### Cadastrar agricultor

**POST /agricultores**

```json
{
  "nome": "Pedro Silva",
  "telefone": "11988887777",
  "cidade": "Campinas"
}
```

### Cadastrar plantação

**POST /plantacoes**

```json
{
  "agricultorId": 1,
  "cultura": "Milho",
  "areaHectares": 8.5,
  "dataPlantio": "2026-11-14"
}
```

### Cadastrar colheita

**POST /colheitas**

```json
{
  "plantacaoId": 1,
  "quantidadeKg": 1500.0,
  "dataColheita": "2027-03-15"
}
```

## 🗄️ Banco de dados

O projeto utiliza PostgreSQL para armazenamento das informações.

Banco de dados utilizado:

`agrofacil`

### Tabelas

- agricultores
- plantacoes
- colheitas

As tabelas possuem chaves primárias, chaves estrangeiras e restrições para garantir a integridade dos dados.

## 🔄 Migrações com Flyway

O controle de versão do banco de dados é realizado utilizando Flyway.

As migrações ficam localizadas em:

`src/main/resources/db/migration`

### V1 — Criação das tabelas

Arquivo:

`V1__criar_tabelas.sql`

Responsável pela criação das tabelas:

- agricultores
- plantacoes
- colheitas

Também define os relacionamentos e as restrições do banco.

### V2 — Inserção de dados de exemplo

Arquivo:

`V2__inserir_dados_exemplo.sql`

Responsável pela inserção de dados fictícios para demonstração da aplicação, incluindo:

- Agricultor Carlos Ferreira.
- Agricultora Ana Souza.
- Plantação de soja vinculada ao Carlos Ferreira.
- Colheita de 3.500 kg vinculada à plantação de soja.

As migrações são executadas automaticamente na inicialização da aplicação.

### V3 — Adição do e-mail aos agricultores

Arquivo:

`V3__adicionar_email_agricultores.sql`

Essa migração adiciona a coluna `email` à tabela `agricultores`.

O campo é opcional e permite cadastrar um endereço de e-mail
sem prejudicar os agricultores registrados anteriormente.

## 🚀 Como executar o projeto

### Pré-requisitos

- Java 21 instalado.
- PostgreSQL instalado e em execução.
- Banco de dados `agrofacil` criado.
- Acesso ao terminal PowerShell.

O projeto inclui o Maven Wrapper, portanto não é necessário instalar o Maven separadamente.

### 1. Clonar o repositório

```bash
git clone https://github.com/igguin/agrofacil.git
```

Depois, acessar a pasta do projeto que contém o arquivo `pom.xml`.

### 2. Configurar o PostgreSQL

Criar um banco de dados chamado:

`agrofacil`

Configurar a conexão no arquivo de propriedades da aplicação, localizado em:

`src/main/resources/application.properties`

Para a configuração que utiliza a variável de ambiente `DB_PASSWORD`, informar a senha no terminal PowerShell:

```powershell
$env:DB_PASSWORD = Read-Host "Digite a senha do PostgreSQL"
```

A senha não deve ser enviada ao GitHub nem registrada diretamente no código.

### 3. Iniciar a aplicação

No terminal PowerShell, executar:

```powershell
.\mvnw.cmd spring-boot:run
```

A aplicação será iniciada em:

`http://localhost:8080`

### 4. Executar as migrações

As migrações Flyway são aplicadas automaticamente durante a inicialização do Spring Boot.

Não é necessário executar manualmente os arquivos SQL.

## 📚 Documentação Swagger

A API possui documentação interativa utilizando Swagger UI e OpenAPI.

Com a aplicação em execução, acessar:

http://localhost:8080/swagger-ui/index.html

A documentação apresenta os endpoints e permite visualizar os parâmetros, os modelos de dados e as operações disponíveis.

Especificação OpenAPI em formato JSON:

http://localhost:8080/v3/api-docs

## 🧪 Testes automatizados

Os testes foram desenvolvidos com JUnit e Mockito.

O projeto possui **17 testes automatizados**, distribuídos entre testes unitários dos serviços, um teste de inicialização do Spring Boot e um teste de endpoint HTTP.

### Teste de endpoint HTTP

O arquivo `PlantacaoControllerTest.java` utiliza MockMvc para verificar se a API retorna:

- HTTP 400 quando uma plantação possui área igual a zero.
- Uma resposta JSON com a mensagem de erro correspondente.

O Mockito permite simular os repositórios sem modificar os dados reais do PostgreSQL durante os testes unitários.

### Testes de agricultores

- Impedir cadastro sem nome.
- Impedir cadastro sem cidade.
- Permitir cadastro com dados válidos.

### Testes de plantações

- Impedir cadastro com área zero.
- Impedir cadastro com área negativa.
- Impedir cadastro sem cultura.
- Impedir cadastro sem data de plantio.
- Impedir cadastro sem agricultor.
- Impedir cadastro com agricultor inexistente.

### Testes de colheitas

- Impedir cadastro com quantidade zero.
- Impedir cadastro com quantidade negativa.
- Impedir colheita anterior ao plantio.
- Impedir cadastro sem data de colheita.
- Impedir cadastro sem plantação.
- Impedir cadastro com plantação inexistente.

Também existe um teste de inicialização do contexto do Spring Boot.

### Executar todos os testes

```powershell
.\mvnw.cmd test
```

Os testes unitários utilizam repositórios simulados. O teste de inicialização do Spring Boot utiliza a configuração de banco de dados da aplicação e requer credenciais válidas para conexão.

## 📊 Códigos HTTP utilizados

| Código | Significado                                      |
| ------ | ------------------------------------------------ |
| 200    | Requisição processada com sucesso                |
| 201    | Recurso criado, quando configurado pelo endpoint |
| 400    | Dados inválidos                                  |
| 404    | Recurso não encontrado                           |

## 🔐 Boas práticas

O projeto utiliza:

- Separação de responsabilidades entre camadas.
- DTOs para entrada e saída de dados.
- Validação de regras de negócio.
- Tratamento de erros HTTP.
- Relacionamentos entre entidades.
- Restrições no banco de dados.
- Migrações versionadas com Flyway.
- Documentação automática com Swagger.
- Testes automatizados com JUnit e Mockito.

## 🎓 Contexto acadêmico

Projeto desenvolvido para a disciplina de Service-Oriented Architecture (SOA), com foco em construção de serviços REST, persistência de dados relacionais, aplicação de regras de negócio, versionamento de banco de dados, documentação e testes.

## 📝 Considerações finais

O AgroFácil demonstra a implementação de uma API REST para gestão agrícola, integrando Java, Spring Boot e PostgreSQL.

A solução permite gerenciar agricultores, plantações e colheitas, mantendo relacionamentos entre os dados e aplicando validações para preservar a consistência das informações.

### Cadastro de agricultores com e-mail

O campo e-mail é opcional.

Exemplo de requisição `POST /agricultores`:

```json
{
  "nome": "Joao Oliveira",
  "telefone": "11977776666",
  "cidade": "Sorocaba",
  "email": "joao@exemplo.com"
}
```

O campo `email` também está disponível nas consultas e atualizações dos agricultores.
