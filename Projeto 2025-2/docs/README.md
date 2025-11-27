# ETAPA 7 – STATIC CHECKER – ANÁLISE LÉXICA  


## 1. IDENTIFICAÇÃO DA EQUIPE

**Equipe:** EQ02  

**Integrantes:**
- Diego Peon San Martin Neto | diego.neto@ucsal.edu.br | (71) 9902-1700  
- Jailton da Cruz Mocitaiba Filho | jailton.filho@ucsal.edu.br | (71) 9154-2873  
- Lucas Nascimento Alves | lucas.alves@ucsal.edu.br | (74) 9998-7422  


## 2. OBJETIVO DA ETAPA 7

Nessa etapa foi feita a implementação do **Analisador Léxico (Lexer)** da linguagem definida na disciplina, responsável por:

- Ler um arquivo-fonte `.252`
- Reconhecer tokens da linguagem
- Ignorar comentários de linha (`//`) e de bloco (`/* ... */`)
- Gerar automaticamente:
  - Um arquivo `.LEX` contendo o relatório dos tokens
  - Um arquivo `.TAB` contendo a Tabela de Símbolos

Todos os códigos dos átomos seguem o Apêndice A do documento do projeto.


## 3. LINGUAGEM E FERRAMENTAS

- **Linguagem:** Java  
- **IDE de execução:** IntelliJ IDEA   
- **Sistema de arquivos:** Leitura e escrita de arquivos `.252`, `.LEX` e `.TAB`



## 4. ESTRUTURA DO PROJETO

O projeto deve possuir a seguinte estrutura de diretórios:

Projeto2025-2
│
├── src
│ ├── main
│ │ └── Principal.java
│ ├── lexer
│ │ ├── Lexer.java
│ │ └── Token.java
│ ├── simbolos
│ │ ├── EntradaSimbolo.java
│ │ └── TabelaSimbolos.java
│ └── reservadas
│ └── TabelaReservadas.java
│
├── testes
│ └── teste1.252
│
├── docs
│ └── README.md
│
└── bin



## 5. COMO EXECUTAR NO INTELLIJ IDEA

### 5.1 Abrir o projeto

1. Abrir o **IntelliJ IDEA**
2. Clicar em **Open**
3. Selecionar a pasta raiz do projeto:  
   `Projeto2025-2`


### 5.2 Configurar o arquivo de entrada

O analisador espera receber **apenas o nome base do arquivo**, sem a extensão `.252`.

Exemplo de arquivo existente:

testes/teste1.252

O argumento passado será:

testes/teste1


### 5.3 Configurar os argumentos no IntelliJ

1. Clique em:
Run → Edit Configurations
2. Selecione a classe:
main.Principal
3. No campo:
Program arguments
Informe:
testes/teste1

4. Clique em **Apply** e depois em **OK**


### 5.4 Executar o programa

Basta clicar em **Run ▶** no IntelliJ.

Ao final da execução, o sistema gerará automaticamente:
testes/teste1.LEX
testes/teste1.TAB


## 6. ARQUIVO `.LEX`

O arquivo `.LEX` contém:

- Cabeçalho com dados da equipe
- Lista de tokens reconhecidos
- Código do átomo (PRS, SRS, IDN)
- Linha em que o token aparece
- Índice na Tabela de Símbolos (quando aplicável)

Exemplo de linha:
program | PRS14 | 1 | -1


## 7. ARQUIVO `.TAB`

O arquivo `.TAB` contém a **Tabela de Símbolos**, incluindo:

- Índice do símbolo
- Código do átomo
- Lexema
- Tamanho antes da truncagem
- Tamanho após a truncagem (máx. 35)
- Tipo (ND)
- Lista das primeiras linhas onde o símbolo aparece


## 8. TRATAMENTO DE COMENTÁRIOS

O analisador ignora completamente:

- Comentários de linha:
  ```txt
  // comentário

- Comentários de bloco:
  ```txt
  /* comentário */

Nenhum comentário gera token ou entrada na Tabela de Símbolos.

## 9. CONFORMIDADE COM O APÊNDICE A

Todos os códigos seguem o padrão:

- PRSxx → Palavras reservadas

- SRSxx → Símbolos reservados

- IDNxx → Identificadores e constantes











