package simbolos;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TabelaSimbolos {

    private Map<String, EntradaSimbolo> tabela;
    private int proximoIndice;

    public TabelaSimbolos() {
        tabela = new HashMap<>();
        proximoIndice = 0;
    }

    public int inserir(String lexema, String codigo, int linha) {

        if (!tabela.containsKey(lexema)) {

            int tamAntes = lexema.length();
            int tamDepois = Math.min(35, tamAntes);

            EntradaSimbolo entrada = new EntradaSimbolo(
                    proximoIndice,
                    codigo,
                    lexema.length() > 35 ? lexema.substring(0, 35) : lexema,
                    tamAntes,
                    tamDepois
            );

            entrada.adicionarLinha(linha);

            tabela.put(lexema, entrada);
            proximoIndice++;

            return entrada.getIndice();

        } else {
            EntradaSimbolo entrada = tabela.get(lexema);
            entrada.adicionarLinha(linha);
            return entrada.getIndice();
        }
    }

    public Collection<EntradaSimbolo> getTodosSimbolos() {
        return tabela.values();
    }

    public void imprimirTabela() {
        System.out.println("=== TABELA DE SÍMBOLOS ===");
        for (EntradaSimbolo e : tabela.values()) {
            System.out.println(e);
        }
    }
}
