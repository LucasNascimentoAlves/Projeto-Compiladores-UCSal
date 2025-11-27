package main;

import lexer.Lexer;
import lexer.Token;
import simbolos.TabelaSimbolos;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Principal {

    public static void main(String[] args) {

        try {

            // 1) LEITURA DO NOME DO ARQUIVO

            if (args.length == 0) {
                System.out.println("ERRO: Informe o nome do arquivo sem extensão.");
                System.out.println("Exemplo: java main.Principal teste1");
                return;
            }

            String nomeBase = args[0];              
            String arquivoEntrada = nomeBase + ".252";
            String arquivoLEX = nomeBase + ".LEX";
            String arquivoTAB = nomeBase + ".TAB";


            // 2) INICIALIZA ESTRUTURAS

            TabelaSimbolos tabela = new TabelaSimbolos();
            Lexer lexer = new Lexer(arquivoEntrada, tabela);

            PrintWriter lexOut = new PrintWriter(new FileWriter(arquivoLEX));


            // 3) CABEÇALHO DO .LEX

            lexOut.println("===============================================");
            lexOut.println("RELATÓRIO DA ANÁLISE LÉXICA");
            lexOut.println("Arquivo analisado: " + arquivoEntrada);
            lexOut.println("Data/Hora: " + dataHoraAtual());
            lexOut.println("Equipe: EQ02");
            lexOut.println("Integrantes:");
            lexOut.println("- Diego Peon San Martin Neto | diego.neto@ucsal.edu.br | (71) 9902-1700");
            lexOut.println("- Jailton da Cruz Mocitaiba Filho | jailton.filho@ucsal.edu.br | (71) 9154-2873"); 
            lexOut.println("- Lucas Nascimento Alves | lucas.alves@ucsal.edu.br | (74) 9998-7422"); 
            lexOut.println("===============================================");
            lexOut.println("LEXEMA | CÓDIGO | LINHA | ÍNDICE_TS");
            lexOut.println("-----------------------------------------------");


            // 4) PROCESSO LÉXICO

            Token token;
            while ((token = lexer.proximoToken()) != null) {
                lexOut.println(
                        token.getLexema() + " | " +
                        token.getCodigo() + " | " +
                        token.getLinha() + " | " +
                        token.getIndiceTabela()
                );
            }

            lexOut.close();
            lexer.fechar();


            // 5) GERAÇÃO DO .TAB

            PrintWriter tabOut = new PrintWriter(new FileWriter(arquivoTAB));

            tabOut.println("===============================================");
            tabOut.println("RELATÓRIO DA TABELA DE SÍMBOLOS");
            tabOut.println("Arquivo analisado: " + arquivoEntrada);
            tabOut.println("Data/Hora: " + dataHoraAtual());
            tabOut.println("Equipe: EQ02");
            tabOut.println("===============================================");
            tabOut.println("IND | COD | LEXEMA | TAM_ANTES | TAM_DEP | TIPO | LINHAS");
            tabOut.println("--------------------------------------------------------");

            tabela.getTodosSimbolos().forEach(simbolo -> {
                tabOut.println(simbolo);
            });

            tabOut.close();


            // 6) MENSAGEM FINAL

            System.out.println("Análise concluída com sucesso!");
            System.out.println("Arquivos gerados:");
            System.out.println(" - " + arquivoLEX);
            System.out.println(" - " + arquivoTAB);

        } catch (Exception e) {
            System.out.println("Erro durante a execução do Static Checker:");
            e.printStackTrace();
        }
    }


    // MÉTODO AUXILIAR DE DATA/HORA

    private static String dataHoraAtual() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.now().format(fmt);
    }
}
