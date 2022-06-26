package Pipeline;

import java.util.ArrayList;
import java.util.Arrays;

public class Registrador {
    ArrayList<Integer> conteudo;
    Integer tamanho;

    //Inicializa um registrador de acordo com seu tamanho com 0
    Registrador(Integer tamanho){
        this.conteudo = new ArrayList<>(tamanho);
        this.tamanho = tamanho;
        for(int i = 0; i < tamanho; i++){
            this.conteudo.add(0);
        }
    }

    ArrayList<Integer> getConteudo(){
        return this.conteudo;
    }

    void setConteudo(ArrayList<Integer> conteudo){
        int j = this.tamanho - 1;
        for(int i = conteudo.size() - 1; i >= 0; i--){
            this.conteudo.set(j--, conteudo.get(i));
        }
    }

    //Retorna um ArrayList com o intervalo contido entre pinicial e pfinal
    ArrayList<Integer> getBits(Integer pinicial, Integer pfinal){
        ArrayList<Integer> aux = new ArrayList<>();
        for(int i = pinicial; i <= pfinal; i++){
            aux.add(this.conteudo.get(i));
        }
        return aux;
    }

    //Realiza a comparação entre ArrayLists
    Boolean compareTo(ArrayList<Integer> op){
        if(Conversor.binToInt(op) == Conversor.binToInt(this.conteudo)){
            return true;
        }
        else return false;
    }

    //Incrementa n vezes no valor do registrador
    void inc(Integer n){
        Integer conteudo = Conversor.binToInt(this.conteudo);
        conteudo += n;
        setConteudo(Conversor.intToBin(conteudo));
    }

    @Override
    public String toString() {
        String aux = "[";
        for(int i = 0; i < this.tamanho; i++){
            aux += this.conteudo.get(i);
            if(i != this.tamanho - 1){
                aux += ", ";
            }
        }
        aux += "]";
        return aux;
    }
}
