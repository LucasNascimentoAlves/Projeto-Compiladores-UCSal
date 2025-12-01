package lexer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import reservadas.TabelaReservadas;
import simbolos.TabelaSimbolos;

public class Lexer {

    private BufferedReader reader;
    private int linha;
    private int proximoChar;
    private TabelaReservadas reservadas;
    private TabelaSimbolos tabelaSimbolos;

    public Lexer(String caminhoArquivo, TabelaSimbolos tabelaSimbolos) throws IOException {
        reader = new BufferedReader(new FileReader(caminhoArquivo));
        linha = 1;
        proximoChar = reader.read();
        reservadas = new TabelaReservadas();
        this.tabelaSimbolos = tabelaSimbolos;
    }

    private void avancar() throws IOException {
        if (proximoChar == '\n') {
            linha++;
        }
        proximoChar = reader.read();
    }


    // TRATAMENTO DE COMENTÁRIOS

    private void pularComentarioLinha() throws IOException {
        while (proximoChar != -1 && proximoChar != '\n') {
            avancar();
        }
    }

    private void pularComentarioBloco() throws IOException {
        avancar(); // após o *

        while (proximoChar != -1) {
            if (proximoChar == '*') {
                avancar();
                if (proximoChar == '/') {
                    avancar();
                    return;
                }
            } else {
                avancar();
            }
        }
    }

    public Token proximoToken() throws IOException {

        // Ignorar espaços
        while (proximoChar != -1 && Character.isWhitespace(proximoChar)) {
            avancar();
        }

        // Comentários
        if (proximoChar == '/') {
            avancar();

            if (proximoChar == '/') { // comentário de linha
                pularComentarioLinha();
                return proximoToken();
            }

            if (proximoChar == '*') { // comentário de bloco
                pularComentarioBloco();
                return proximoToken();
            }

            return new Token("/", reservadas.getCodigo("/"), linha, -1);
        }

        // Fim de arquivo
        if (proximoChar == -1) {
            return null;
        }


        // IDENTIFICADORES / PRS

        if (Character.isLetter(proximoChar)) {
            StringBuilder sb = new StringBuilder();

            while (proximoChar != -1 &&
                    (Character.isLetterOrDigit(proximoChar) || proximoChar == '_')) {

                sb.append((char) proximoChar);
                avancar();

                if (sb.length() == 35) break;
            }

            String lexema = sb.toString();

            if (reservadas.isReservada(lexema)) {
                return new Token(lexema, reservadas.getCodigo(lexema), linha, -1);
            } else {
                String codigoIdentificador = "IDN02"; // variable por padrão
                int indice = tabelaSimbolos.inserir(lexema, codigoIdentificador, linha);
                return new Token(lexema, codigoIdentificador, linha, indice);
            }
        }


        // NÚMEROS

        if (Character.isDigit(proximoChar)) {
            StringBuilder sb = new StringBuilder();
            boolean isReal = false;

            while (proximoChar != -1 &&
                  (Character.isDigit(proximoChar) || proximoChar == '.')) {

                if (proximoChar == '.') isReal = true;
                sb.append((char) proximoChar);
                avancar();
            }

            String codigo = isReal ? "IDN05" : "IDN04"; // realConst | intConst
            return new Token(sb.toString(), codigo, linha, -1);
        }


        // STRING

        if (proximoChar == '"') {
            StringBuilder sb = new StringBuilder();
            sb.append((char) proximoChar);
            avancar();

            while (proximoChar != -1 && proximoChar != '"') {
                sb.append((char) proximoChar);
                avancar();
            }

            if (proximoChar == '"') {
                sb.append((char) proximoChar);
                avancar();
            }

            return new Token(sb.toString(), "IDN06", linha, -1);
        }


        // CHAR

        if (proximoChar == '\'') {
            StringBuilder sb = new StringBuilder();
            sb.append((char) proximoChar);
            avancar();

            if (proximoChar != -1) {
                sb.append((char) proximoChar);
                avancar();
            }

            if (proximoChar == '\'') {
                sb.append((char) proximoChar);
                avancar();
            }

            return new Token(sb.toString(), "IDN07", linha, -1);
        }


        // OPERADORES COMPOSTOS

        if (proximoChar == ':' || proximoChar == '<' ||
                proximoChar == '>' || proximoChar == '=' ||
                proximoChar == '!') {

            char primeiro = (char) proximoChar;
            avancar();

            if (proximoChar == '=') {
                char segundo = (char) proximoChar;
                avancar();
                String op = "" + primeiro + segundo;

                if (reservadas.isReservada(op)) {
                    return new Token(op, reservadas.getCodigo(op), linha, -1);
                }
            }

            return new Token(String.valueOf(primeiro),
                    reservadas.getCodigo(String.valueOf(primeiro)),
                    linha, -1);
        }


        // SÍMBOLO SIMPLES

        char atual = (char) proximoChar;
        avancar();

        if (reservadas.isReservada(String.valueOf(atual))) {
            return new Token(String.valueOf(atual),
                    reservadas.getCodigo(String.valueOf(atual)),
                    linha, -1);
        }

        return proximoToken();
    }

    public void fechar() throws IOException {
        reader.close();
    }
}

