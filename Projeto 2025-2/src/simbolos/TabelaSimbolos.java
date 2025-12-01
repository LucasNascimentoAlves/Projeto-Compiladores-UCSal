package simbolos;

import java.util.*;

public class TabelaSimbolos {

    private Stack<Map<String, EntradaSimbolo>> pilhaEscopos;

    private List<EntradaSimbolo> todosSimbolos;

    private int proximoIndice;

    public TabelaSimbolos() {
        pilhaEscopos = new Stack<>();
        pilhaEscopos.push(new HashMap<>()); 
        todosSimbolos = new ArrayList<>();
        proximoIndice = 0;
    }


    // Controle de escopo

    public void entrarEscopo() {
        pilhaEscopos.push(new HashMap<>());
    }

    public void sairEscopo() {
        if (pilhaEscopos.size() > 1) {
            pilhaEscopos.pop();
        }
    }


    // Inserção de símbolos

    public int inserir(String lexema, String codigo, int linha) {

        Map<String, EntradaSimbolo> escopoAtual = pilhaEscopos.peek();

        if (!escopoAtual.containsKey(lexema)) {

            int tamAntes = lexema.length();
            int tamDepois = Math.min(35, tamAntes);

            String lexemaFinal = lexema.length() > 35
                    ? lexema.substring(0, 35)
                    : lexema;

            EntradaSimbolo entrada = new EntradaSimbolo(
                    proximoIndice,
                    codigo,
                    lexemaFinal,
                    tamAntes,
                    tamDepois
            );

            entrada.adicionarLinha(linha);

            escopoAtual.put(lexema, entrada);
            todosSimbolos.add(entrada); 
            proximoIndice++;

            return entrada.getIndice();

        } else {
            EntradaSimbolo entrada = escopoAtual.get(lexema);
            entrada.adicionarLinha(linha);
            return entrada.getIndice();
        }
    }


    public EntradaSimbolo buscar(String lexema) {
        for (int i = pilhaEscopos.size() - 1; i >= 0; i--) {
            Map<String, EntradaSimbolo> escopo = pilhaEscopos.get(i);
            if (escopo.containsKey(lexema)) {
                return escopo.get(lexema);
            }
        }
        return null;
    }


    public Collection<EntradaSimbolo> getTodosSimbolos() {
        return todosSimbolos;
    }


    // teste

    public void imprimirTabela() {
        System.out.println("=== TABELA DE SÍMBOLOS COMPLETA ===");
        for (EntradaSimbolo e : todosSimbolos) {
            System.out.println(e);
        }
    }
}
