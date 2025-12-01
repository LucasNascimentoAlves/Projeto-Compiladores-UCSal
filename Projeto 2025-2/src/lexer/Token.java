package lexer;

public class Token {

    private String lexema;

    private String codigo;

    private int linha;

    // (-1 se não existir)
    private int indiceTabela;

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
