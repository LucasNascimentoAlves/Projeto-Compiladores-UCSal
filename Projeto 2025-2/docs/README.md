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

- **Linguagem:** Java 21  
- **IDE de execução:** Visual Studio Code  
- **Sistema de arquivos:** Leitura e escrita de arquivos `.252`, `.LEX` e `.TAB`



## 4. ESTRUTURA DO PROJETO

O projeto deve possuir a seguinte estrutura de diretórios:

```md
Projeto2025-2/
├── src/
│   ├── main/
│   │   └── Principal.java
│   ├── lexer/
│   │   ├── Lexer.java
│   │   └── Token.java
│   ├── simbolos/
│   │   ├── EntradaSimbolo.java
│   │   └── TabelaSimbolos.java
│   └── reservadas/
│       └── TabelaReservadas.java
│
├── testes/
│   └── teste1.252
│
├── docs/
│   └── README.md
│
└── bin/
```

## 5. ARQUIVO `.LEX`

O arquivo `.LEX` contém:

- Cabeçalho com dados da equipe
- Lista de tokens reconhecidos
- Código do átomo (PRS, SRS, IDN)
- Linha em que o token aparece
- Índice na Tabela de Símbolos (quando aplicável)

Exemplo de linha:
program | PRS14 | 1 | -1


## 6. ARQUIVO `.TAB`

O arquivo `.TAB` contém a **Tabela de Símbolos**, incluindo:

- Índice do símbolo
- Código do átomo
- Lexema
- Tamanho antes da truncagem
- Tamanho após a truncagem (máx. 35)
- Tipo (ND)
- Lista das primeiras linhas onde o símbolo aparece


## 7. TRATAMENTO DE COMENTÁRIOS

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




















