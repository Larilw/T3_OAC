package Pipeline;

import java.util.ArrayList;
import java.util.Arrays;

public class Memorias {
    public ArrayList<Registrador> conteudo;
    public Integer numeroCelulas;

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

    public void imprimirParteMemoria(){
        String aux = "";
        for(int i = 0; i < this.numeroCelulas; i++){
            if(Conversor.binToInt(this.conteudo.get(i).conteudo) != 0){
                aux += Conversor.binToInt(this.conteudo.get(i).conteudo) + "\n";
            }
        }
        System.out.println(aux);
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
