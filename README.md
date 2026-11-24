# 🐾 PetCare

Sistema de linha de comando para cadastro de **tutores** e seus **pets**, desenvolvido em Java para a disciplina de Programação Orientada a Objetos (ADS).

Autor: Ravi Freire

## Funcionalidades

- CRUD completo de **Tutor** (cadastrar, listar, atualizar, excluir)
- CRUD completo de **Pet** (cadastrar, listar, atualizar, excluir)
- Relacionamento: um tutor pode ter vários pets (`pet.tutor_id` → `tutor.id`)
- Validação dos dados (CPF, CEP, telefone, campos obrigatórios, idade)
- Tratamento de erros na entrada do usuário
- Consulta de endereço pelo CEP usando a API pública **ViaCEP** (rua, cidade e estado são preenchidos automaticamente)

## Tecnologias

- Java 21
- Maven
- MySQL 8 (JDBC)
- Gson (leitura do JSON da API)
- dotenv-java (leitura do arquivo `.env`)

## Pré-requisitos

- JDK 21 instalado
- MySQL Server em execução
- Conexão com a internet (para consultar o ViaCEP e baixar as dependências)

## Instalação e execução

### 1. Clonar o repositório

```bash
git clone https://github.com/ravifreirev/PetCare
cd PetCare
```

### 2. Criar o banco de dados

Execute o script `schema.sql` no MySQL. Pelo terminal:

```bash
mysql -u root -p < schema.sql
```

Ou abra o arquivo no MySQL Workbench e execute-o (File → Open SQL Script → Run).

### 3. Configurar o arquivo `.env`

Copie o arquivo de exemplo e preencha com os seus dados:

```bash
cp .env.example .env
```

No Windows (PowerShell): `copy .env.example .env`

Edite o `.env` (na mesma pasta do `pom.xml`):

```
DB_URL=jdbc:mysql://localhost:3306/petcare
DB_USER=seu_usuario_mysql
DB_PASSWORD=sua_senha_mysql
```

### 4. Executar o projeto

**Pré-requisito:** JDK 21 instalado.

**Pelo IntelliJ IDEA:** abra a pasta do projeto, aguarde o Maven baixar as dependências e execute a classe `Main` (`br.edu.poo.m0024.Main`).

**Pelo terminal** (na pasta do projeto, onde está o `pom.xml`):

Windows:
```bash
.\mvnw.cmd compile exec:java
```

Linux/Mac:
```bash
./mvnw compile exec:java
```

> O programa lê o `.env` a partir da pasta onde é executado. Execute sempre a partir da raiz do projeto.

## Dependências (pom.xml)

| Dependência | Versão |
|---|---|
| mysql-connector-j | 9.5.0 |
| dotenv-java | 3.2.0 |
| gson | 2.13.2 |

## Estrutura do projeto

```
src/main/java/br/edu/poo/m0024/
├── Main.java            # menu interativo
├── model/               # Tutor e Pet (atributos e validações)
├── dao/                 # TutorDAO e PetDAO (acesso ao banco)
├── database/            # Conexao (conexão JDBC)
└── api/                 # ViaCepService e ViaCepResposta
```

## Como usar

Ao executar, o menu é exibido. Digite o número da opção desejada:

1. Cadastrar tutor (o endereço é buscado no ViaCEP a partir do CEP)
2. Listar tutores
3. Atualizar tutor
4. Excluir tutor

5. Cadastrar pet (é necessário informar o ID de um tutor existente)
6. Listar pets
7. Atualizar pet
8. Excluir pet
0. Sair

> Para excluir um tutor, exclua antes os pets dele.
