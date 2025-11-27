package lexer;

public class Token {

    // 1. Texto do token (lexema)
    private String lexema;

    // 2. Código do átomo
    private String codigo;

    // 3. Linha onde apareceu
    private int linha;

    // 4. Índice na Tabela de Símbolos (-1 se não existir)
    private int indiceTabela;

    // Construtor: usado pelo Lexer para criar tokens
    public Token(String lexema, String codigo, int linha, int indiceTabela) {
        this.lexema = lexema;
        this.codigo = codigo;
        this.linha = linha;
        this.indiceTabela = indiceTabela;
    }

    // Getters

    public String getLexema() {
        return lexema;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getLinha() {
        return linha;
    }

    public int getIndiceTabela() {
        return indiceTabela;
    }

    // Como será impresso no terminal
    @Override
    public String toString() {
        return "Lexema: " + lexema +
               " | Código: " + codigo +
               " | Linha: " + linha +
               " | Índice TS: " + indiceTabela;
    }
}
