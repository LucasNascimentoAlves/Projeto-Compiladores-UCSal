package reservadas;

import java.util.HashMap;
import java.util.Map;

public class TabelaReservadas {

    private Map<String, String> tabela;

    public TabelaReservadas() {
        tabela = new HashMap<>();
        carregarReservadas();
    }

    private void carregarReservadas() {

        // PALAVRAS RESERVADAS (PRS)

        tabela.put("integer", "PRS01");
        tabela.put("real", "PRS02");
        tabela.put("character", "PRS03");
        tabela.put("string", "PRS04");
        tabela.put("boolean", "PRS05");
        tabela.put("void", "PRS06");
        tabela.put("true", "PRS07");
        tabela.put("false", "PRS08");
        tabela.put("varType", "PRS09");
        tabela.put("funcType", "PRS10");
        tabela.put("paramType", "PRS11");
        tabela.put("declarations", "PRS12");
        tabela.put("endDeclarations", "PRS13");
        tabela.put("program", "PRS14");
        tabela.put("endProgram", "PRS15");
        tabela.put("functions", "PRS16");
        tabela.put("endFunctions", "PRS17");
        tabela.put("endFunction", "PRS18");
        tabela.put("return", "PRS19");
        tabela.put("if", "PRS20");
        tabela.put("else", "PRS21");
        tabela.put("endIf", "PRS22");
        tabela.put("while", "PRS23");
        tabela.put("endWhile", "PRS24");
        tabela.put("break", "PRS25");
        tabela.put("print", "PRS26");


        // SÍMBOLOS RESERVADOS (SRS)

        tabela.put(";", "SRS01");
        tabela.put(",", "SRS02");
        tabela.put(":", "SRS03");
        tabela.put(":=", "SRS04");
        tabela.put("?", "SRS05");
        tabela.put("(", "SRS06");
        tabela.put(")", "SRS07");
        tabela.put("[", "SRS08");
        tabela.put("]", "SRS09");
        tabela.put("{", "SRS10");
        tabela.put("}", "SRS11");
        tabela.put("+", "SRS12");
        tabela.put("-", "SRS13");
        tabela.put("*", "SRS14");
        tabela.put("/", "SRS15");
        tabela.put("%", "SRS16");
        tabela.put("==", "SRS17");
        tabela.put("!=", "SRS18");
        tabela.put("#", "SRS18"); // alias de !=
        tabela.put("<", "SRS19");
        tabela.put("<=", "SRS20");
        tabela.put(">", "SRS21");
        tabela.put(">=", "SRS22");
    }

    public boolean isReservada(String lexema) {
        return tabela.containsKey(lexema);
    }

    public String getCodigo(String lexema) {
        return tabela.get(lexema);
    }
}
