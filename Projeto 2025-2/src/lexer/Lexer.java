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

    private static final int TAM_MAX = 35; 

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


    // Comentários


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


    // Token


    public Token proximoToken() throws IOException {

        // Ignora espaços em branco
        while (proximoChar != -1 && Character.isWhitespace(proximoChar)) {
            avancar();
        }

        // Comentários
        if (proximoChar == '/') {
            avancar();

            if (proximoChar == '/') {
                pularComentarioLinha();
                return proximoToken();
            }

            if (proximoChar == '*') {
                pularComentarioBloco();
                return proximoToken();
            }

            return new Token("/", reservadas.getCodigo("/"), linha, -1);
        }

        // Fim de arquivo
        if (proximoChar == -1) {
            return null;
        }


        // Identifiers

        if (Character.isLetter(proximoChar)) {
            StringBuilder sb = new StringBuilder();

            while (proximoChar != -1 &&
                   (Character.isLetterOrDigit(proximoChar) || proximoChar == '_')) {

                sb.append((char) proximoChar); // NÃO TRUNCA AQUI
                avancar();
            }

            String lexema = sb.toString();

            if (reservadas.isReservada(lexema)) {
                return new Token(lexema, reservadas.getCodigo(lexema), linha, -1);
            } else {
                int indice = tabelaSimbolos.inserir(lexema, "IDN02", linha);
                return new Token(lexema, "IDN02", linha, indice);
            }
        }


        // Números 

        if (Character.isDigit(proximoChar)) {
            StringBuilder sb = new StringBuilder();
            boolean isReal = false;

            while (proximoChar != -1 &&
                  (Character.isDigit(proximoChar) || proximoChar == '.')) {

                if (proximoChar == '.') isReal = true;

                if (sb.length() < TAM_MAX) {
                    sb.append((char) proximoChar);
                }
                avancar();
            }

            String codigo = isReal ? "IDN05" : "IDN04";
            return new Token(sb.toString(), codigo, linha, -1);
        }


        // String

        if (proximoChar == '"') {
            StringBuilder sb = new StringBuilder();
            sb.append('"');
            avancar();

            while (proximoChar != -1 && proximoChar != '"') {
                if (sb.length() < TAM_MAX) {
                    sb.append((char) proximoChar);
                }
                avancar();
            }

            if (proximoChar == '"') {
                if (sb.length() < TAM_MAX) {
                    sb.append('"');
                }
                avancar();
            }

            return new Token(sb.toString(), "IDN06", linha, -1);
        }


        // Char

        if (proximoChar == '\'') {
            StringBuilder sb = new StringBuilder();
            sb.append('\'');
            avancar();

            if (proximoChar != -1) {
                sb.append((char) proximoChar);
                avancar();
            }

            if (proximoChar == '\'') {
                sb.append('\'');
                avancar();
            }

            return new Token(sb.toString(), "IDN07", linha, -1);
        }


        // Operadores

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


        // Símbolos simples

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
