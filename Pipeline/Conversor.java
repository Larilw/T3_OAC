package Pipeline;

import java.util.ArrayList;

public class Conversor {

    //Converte Integer em ArrayList com o binario
    public static ArrayList<Integer> intToBin(Integer valor){
        ArrayList<Integer> binario = new ArrayList<>();
        String aux = Integer.toBinaryString(valor);
        for(int i = 0; i < aux.length(); i++){
            binario.add(Integer.parseInt(String.valueOf(aux.charAt(i))));
        }
        return binario;
    }

    //Converte ArrayList com os binários em Integer
    public static Integer binToInt(ArrayList<Integer> valor){
        Integer numero = 0;
        Integer aux = 2;
        numero += valor.get(valor.size() - 1);
        for(int i = valor.size() - 2; i >= 0; i--){
            numero += aux * valor.get(i);
            aux *= 2;
        }
        return numero;
    }

    //Realiza um shift right de acordo com um Inteiro de shift
    public static ArrayList<Integer> shiftRight(Integer shift, ArrayList<Integer> conteudo){
        ArrayList<Integer> aux = new ArrayList<>();
        aux.addAll(conteudo);
        for(int i = 0; i < shift; i++){
            aux.remove(aux.size() - 1);
            aux.add(0, 0);
        }
        return aux;
    }

    //Replica um valor n vezes e insere em um ArrayList 
    public static ArrayList<Integer> replicar(Integer nReplicacoes, Integer valor){
        ArrayList<Integer> aux = new ArrayList<>();
        for(int i = 0; i < nReplicacoes; i++){
            aux.add(valor);
        }
        return aux;
    }

    //Concatena dois ArrayLists de Integer
    public static ArrayList<Integer> concatenarArray(ArrayList<Integer> array1, ArrayList<Integer> array2){
        ArrayList<Integer> aux = new ArrayList<>();
        aux.addAll(array1);
        aux.addAll(array2);
        return aux;
    }
}
