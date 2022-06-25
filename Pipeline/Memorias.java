package Pipeline;

import java.util.ArrayList;

public class Memorias {
    private ArrayList<Registrador> conteudo;
    private Integer numeroCelulas;

    Memorias(Integer tamanhoCelula, Integer numeroCelulas){
        this.numeroCelulas = numeroCelulas;
        this.conteudo = new ArrayList<Registrador>(numeroCelulas);
        for(int i = 0; i < numeroCelulas; i++){
            this.conteudo.add(new Registrador(tamanhoCelula));
        }
    }

    void setMemorias(ArrayList<Registrador> conteudo){
        this.conteudo = conteudo;
    }

    void setMemorias(ArrayList<Integer> valor, Integer posicao){
        this.conteudo.get(posicao).setConteudo(valor);
    }

    ArrayList<Registrador> getMemorias(){
        return this.conteudo;
    }

    @Override
    public String toString() {
        String aux = "";
        for(int i = 0; i < this.numeroCelulas; i++){
            aux += this.conteudo.get(i).toString() + "\n";
        }
        return aux;
    }
}
