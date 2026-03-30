# 🌍 Projeto - Cidades ESGInteligentes

[![Java Version](https://img.shields.io/badge/Java-21-blue.svg)](https://adoptium.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-green.svg)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Container-blue.svg)](https://www.docker.com/)

## 📋 Sobre o Projeto

Aplicação Spring Boot para promover práticas ESG (Environmental, Social, and Governance) em cidades inteligentes.

---

## 🚀 Como executar localmente com Docker

### Pré-requisitos
- Docker 20.10+ e Docker Compose 2.0+
- Portas 8080 e 3306 disponíveis

### Passo a passo

```bash
# 1. Clone o repositório
git clone https://github.com/Pedro-Thomazi/Atividade_Facul_Fase6_Ano2.git
# 2. Criar imagem
docker build -t myapp .
# 3. Suba os containers
docker run -d -p 8080:8080 {Image ID}
```

## 🔄 Pipeline CI/CD
### Ferramentas Utilizadas
Ferramenta	Versão	Finalidade
GitHub Actions	-	Automação do pipeline
Java	21	Runtime da aplicação
Maven	3.9.8	Gerenciamento de dependências e build
Docker	Latest	Containerização
Docker Compose	2.0+	Orquestração local


## 🐳 Containerização
Utilizamos multi-stage builds para otimizar a imagem final:
1. Estágio de build: Compila a aplicação usando Maven
2. Estágio final: Apenas o JAR e o JRE, reduzindo tamanho

## 💻 Tecnologias utilizadas

### Backend

| Tecnologia | Versão | Descrição |
|------------|--------|-------------|
| Java | 21 | Linguagem de programação |
| Spring Boot | 3.2.0 | Framework principal |
| Spring Data JPA | 3.2.0 | ORM e acesso a dados |
| Spring Web | 3.2.0 | API REST |
| Maven | 3.9.8 | Gerenciador de dependências |

### Banco de Dados

| Tecnologia | Versão | Descrição |
|------------|--------|-------------|
| MySQL | 8.0 | Banco de dados relacional |
| H2 | (testes) | Banco em memória para testes |

### DevOps & Containerização

| Tecnologia | Versão | Descrição |
|------------|--------|-------------|
| Docker | Latest | Containerização |
| Docker Compose | 2.0+ | Orquestração local |
| GitHub Actions | - | CI/CD Pipeline |
| Ubuntu | Latest | Ambiente do runner |

### Ferramentas de Desenvolvimento

| Ferramenta | Finalidade |
|------------|-------------|
| IntelliJ IDEA | IDE principal |
| Postman | Testes de API |
| Git | Controle de versão |
| Docker Desktop | Ambiente local |