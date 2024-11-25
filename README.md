# 📚 Sistema de Comunicação Escolar - Back-End

Um sistema robusto para facilitar a comunicação entre escolas, pais, professores e administradores, oferecendo funcionalidades essenciais como postagem de notícias, gerenciamento de usuários e consulta de cardápio semanal. Este repositório contém a implementação **back-end**, construída com **Spring Boot** e outras tecnologias modernas.

---

## 🗂️ Índice

- [Descrição Geral](#descrição-geral)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Funcionalidades Implementadas](#funcionalidades-implementadas)
- [Configuração do Ambiente Local](#configuração-do-ambiente-local)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Detalhes do Deploy](#detalhes-do-deploy)
- [Contribuições](#contribuições)

---

## 📖 Descrição Geral

Este projeto visa aprimorar a comunicação escolar ao centralizar informações importantes em uma plataforma digital, permitindo:
- Gerenciamento de **usuários** com diferentes níveis de acesso (Admin, Professor, Pai).
- Publicação e consulta de **notícias** pela escola.
- Atualização e visualização do **cardápio semanal**.
- Integração com serviços como AWS S3 e Firebase para gerenciamento de arquivos e mensagens.

O sistema prioriza segurança, organização e facilidade de acesso às informações.

---

## 🛠️ Tecnologias Utilizadas

### **Principais Tecnologias**
- **Java (Spring Boot)**: Framework para desenvolvimento do back-end.
- **JWT (Json Web Token)**: Gerenciamento de autenticação e autorização.
- **Spring Data JPA**: Comunicação com o banco de dados.
- **MySQL**: Banco de dados relacional.

### **Serviços e Integrações**
- **AWS S3**: Armazenamento de imagens e outros arquivos.
- **Firebase**: Gerenciamento de mensagens em tempo real para funcionalidades de chat.
- **Docker**: Containerização da aplicação.

---

## 🚀 Funcionalidades Implementadas

1. **Gerenciamento de Usuários**:
   - Cadastro, edição e exclusão de usuários.
   - Diferenciação de permissões por **papel** (Admin, Professor, Pai).

2. **Sistema de Notícias**:
   - Criação, edição e exclusão de notícias.
   - Upload de imagens para ilustrar as notícias.
   - Armazenamento seguro de imagens no AWS S3.

3. **Cardápio Semanal**:
   - Visualização do cardápio pelos usuários.
   - Atualização semanal realizada exclusivamente por administradores.
   - Remoção de itens do cardápio mantendo a estrutura semanal.

4. **Autenticação e Autorização**:
   - Autenticação baseada em **JWT**.
   - Controle de acesso por nível de permissão.

---

## 🖥️ Configuração do Ambiente Local

### **Pré-requisitos**
- **Java** (versão 17 ou superior)
- **MySQL** (versão 8.x)
- **Maven** (versão 3.6 ou superior)
- **Docker** (opcional, para containerização)

### **Passos para Configuração**

1. **Clone o repositório**:
   ```
   git clone https://github.com/baldancam/projeto-escolar-extensao.git
   cd projeto-escolar-extensao
2. **Configure o banco de dados**:

- Crie um banco de dados chamado **escolar**.
- Configure o arquivo **application.properties** com suas credenciais do MySQL:
```
spring.datasource.url=jdbc:mysql://localhost:3306/escolar
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
```

3. **Instale as dependências e execute o projeto**:

```
mvn clean install
mvn spring-boot:run
```
**Acesse o sistema:**
```
API estará disponível em: http://localhost:8080
```

---

## 📂 Estrutura do Projeto

```
src/main/java/com/sistema/escolar
├── controller         # Controladores das rotas REST
├── model              # Modelos e entidades do banco de dados
├── repository         # Repositórios (Spring Data JPA)
├── service            # Regras de negócio e integrações
├── security           # Configuração de autenticação e autorização
```

## 🌍 Detalhes do Deploy

O sistema foi configurado e implantado em um ambiente de produção com as seguintes tecnologias:

- **AWS EC2**: Hospedagem do servidor.
- **MySQL**: Banco de dados gerenciado.
- **AWS S3**: Armazenamento de imagens e documentos.

## 🤝 Contribuições

Contribuições são bem-vindas! Para colaborar:

1. Faça um fork do repositório.
2. Crie uma branch para suas alterações:
```
git checkout -b feature/nova-funcionalidade
```
3. Envie suas alterações:
```
git commit -m "Descrição clara da alteração"
git push origin feature/nova-funcionalidade
```
4. Abra um Pull Request.
