package Pipeline;

import java.util.ArrayList;

public class Conversor {
    public static ArrayList<Integer> intToBin(Integer valor){
        ArrayList<Integer> binario = new ArrayList<>();
        String aux = Integer.toBinaryString(valor);
        for(int i = 0; i < aux.length(); i++){
            binario.add(Integer.parseInt(String.valueOf(aux.charAt(i))));
        }
        return binario;
    }

    public static Integer binToInt(ArrayList<Integer> valor){
        Integer numero = 0;
        Integer aux = 2;
        numero += valor.get(valor.size() - 1);
        for(int i = valor.size() - 2; i >= 0; i++){
            numero += aux * valor.get(i);
            aux *= 2;
        }
        return numero;
    }
}
