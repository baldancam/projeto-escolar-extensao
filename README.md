# Comunicação Escolar - Projeto de Extensão

Este projeto visa criar uma aplicação de comunicação escolar que facilite o envio de recados, calendários, cardápios e outras informações entre a escola e os pais de alunos, permitindo uma comunicação mais centralizada e organizada.

## 📑 Funcionalidades

- **Recados:** Envio de recados individuais ou em grupo para os pais.
- **Calendário Escolar:** Consulta de eventos e feriados escolares.
- **Cardápio:** Consulta do cardápio escolar.
- **Feed de Fotos:** Visualização de fotos de eventos escolares (somente administradores podem postar).
- **Dúvidas e Suporte:** Comunicação direta entre pais e escola para dúvidas e informações.
  
## 🔧 Tecnologias Utilizadas

- **Linguagem principal:** Java 17
- **Frameworks:**
  - Spring Boot
  - Spring Security
  - Spring Data JPA
- **Banco de dados:** MySQL
- **Controle de versões:** Git e GitHub
- **Ferramentas de desenvolvimento:** Spring Tool Suite (STS)

## 🚀 Configuração e Execução

### Pré-requisitos

- **Java 17** ou superior instalado.
- **MySQL** instalado e configurado.
- **Maven** para gerenciamento de dependências.

### Passo a Passo para Rodar o Projeto

**1.** Clone o repositório:

```sh
git clone https://github.com/seu-usuario/projeto-escolar-extensao.git
cd projeto-escolar-extensao
```

**2.** Configure o banco de dados:

- **Crie um banco de dados MySQL com o nome ``sistema_escolar``.**
- **Atualize o arquivo ``application.properties`` com suas credenciais de MySQL:**

 ```sh 
spring.datasource.url=jdbc:mysql://localhost:3306/sistema_escolar?useSSL=false&serverTimezone=UTC
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
```

**3.** Compile o projeto:

```sh 
mvn clean install
```

**4.** Execute a aplicação:

```sh
mvn spring-boot:run
```

**5.** Acesse a aplicação no navegador:
- **URL:** [http://localhost:8080](http://localhost:8080)

## 🛠️ Como Contribuir

### Criando uma Branch

**1.** Sempre crie uma branch a partir da ``develop``:

```sh 
git checkout develop
git pull
git checkout -b feature/nome-da-sua-feature
```

**2.** Após concluir o desenvolvimento, faça o commit e o push:

```sh 
git add .
git commit -m "Descrição da sua feature"
git push origin feature/nome-da-sua-feature
```

### Criando uma Pull Request

**1.** Quando a sua feature estiver pronta, crie uma Pull Request no GitHub apontando da sua branch para a ``develop``.
**2.** Aguarde a revisão de pelo menos um membro da equipe antes de mesclar (merge).

## 📜 Regras de Contribuição

- A branch ``master`` está protegida, e apenas o dono do repositório pode aprovar e mesclar alterações.
- Todas as pull requests devem passar por revisão de código.
- Utilize a nomenclatura padrão para branches: ``feature/nome-da-feature`` ou ``bugfix/nome-do-bug``.
  
## 📄 Licença

Este projeto é desenvolvido como parte de um projeto de extensão acadêmica e ainda não possui uma licença definida.

