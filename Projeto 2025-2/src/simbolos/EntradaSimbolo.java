package simbolos;

import java.util.ArrayList;
import java.util.List;

public class EntradaSimbolo {

    private int indice;
    private String codigo;  
    private String lexema;
    private int tamAntes;
    private int tamDepois;
    private String tipo;
    private List<Integer> linhas;

    public EntradaSimbolo(int indice, String codigo, String lexema, int tamAntes, int tamDepois) {
        this.indice = indice;
        this.codigo = codigo;
        this.lexema = lexema;
        this.tamAntes = tamAntes;
        this.tamDepois = tamDepois;
        this.tipo = "ND"; // Não definido ainda
        this.linhas = new ArrayList<>();
    }

    public int getIndice() {
        return indice;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getLexema() {
        return lexema;
    }

    public int getTamAntes() {
        return tamAntes;
    }

    public int getTamDepois() {
        return tamDepois;
    }

    public String getTipo() {
        return tipo;
    }

    public List<Integer> getLinhas() {
        return linhas;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void adicionarLinha(int linha) {
        if (linhas.size() < 5 && !linhas.contains(linha)) {
            linhas.add(linha);
        }
    }

    @Override
    public String toString() {
        return indice + " | " +
               codigo + " | " +
               lexema + " | " +
               tamAntes + " | " +
               tamDepois + " | " +
               tipo + " | " +
               linhas;
    }
}
