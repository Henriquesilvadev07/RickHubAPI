API REST desenvolvida em Java para gerenciamento de usuários e autenticação segura baseada em tokens. O projeto implementa um sistema completo de controle de acesso (CRUD de usuários) aliado a um fluxo robusto de autenticação via JSON Web Token (JWT), garantindo que apenas usuários autenticados possam acessar rotas protegidas.

<img width="901" height="731" alt="Screenshot 2026-10-24 at 18 24 55" src="https://github.com/user-attachments/assets/8909f7d4-a835-4126-8f3e-a6b793eccc79" />

<img width="1255" height="922" alt="Screenshot 2026-10-24 at 18 26 00" src="https://github.com/user-attachments/assets/397ba1a9-9712-4928-9103-6c65f89a2b99" />

<img width="1251" height="925" alt="Screenshot 2026-10-24 at 18 25 20" src="https://github.com/user-attachments/assets/6f640503-cea3-40b6-b33b-aa72406f27e5" />

<img width="947" height="616" alt="Screenshot 2026-10-24 at 18 26 13" src="https://github.com/user-attachments/assets/bbd18739-f5b6-498c-b5fb-1425965f949f" />


Funcionalidades
Gerenciamento de Usuários: Cadastro, atualização, consulta e exclusão de contas.

Autenticação Stateless: Login com emissão de token JWT, ideal para arquiteturas modernas de API REST.

Segurança Avançada com Spring Security: Proteção de rotas, criptografia de senhas utilizando BCrypt
e gerenciamento de estado sem sessão (STATELESS).

Tecnologias e Ferramentas
Java 21
Spring Boot 3
Spring Security
Spring Data JPA
PostgreSQL
Auth0 Java JWT (para geração e validação de tokens)
Lombok
Validation

 Boas Práticas e Arquitetura
O projeto foi construído seguindo padrões de mercado e arquitetura limpa

Senhas Criptografadas: As senhas nunca são salvas em texto plano no banco de dados;
o Spring Security utiliza o BCryptPasswordEncoder para o hash seguro.

Arquitetura Stateless: O servidor não armazena sessão de usuário;
toda a validação de identidade é feita de forma independente por meio do token JWT enviado via cabeçalho (Authorization: Bearer <token>).

Separação de Responsabilidades: Organização clara entre Controllers, Services, Repositories e DTOs (Records),
isolando as regras de negócio da camada de transporte.

Injeção de Dependências: Uso rigoroso da inversão de controle do Spring para desacoplar as classes e facilitar testes e manutenção.
