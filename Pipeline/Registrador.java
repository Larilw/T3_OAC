package Pipeline;

import java.util.ArrayList;

public class Registrador {
    ArrayList<Integer> conteudo;
    Integer tamanho;

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

    ArrayList<Integer> getBits(Integer pinicial, Integer pfinal){
        ArrayList<Integer> aux = new ArrayList<>();
        for(int i = pinicial; i <= pfinal; i++){
            aux.add(this.conteudo.get(i));
        }
        return aux;
    }

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
