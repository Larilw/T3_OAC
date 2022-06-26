package Pipeline;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    /**
     * Limpa o console
     */
    public static void limparTela(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }

    /**
     * Aguarda a entrada de um enter do teclado para gerenciamento das telas dos menus
     */
    public static void pressionarEnterParaContinuar(){ 
           System.out.println("Pressione Enter para continuar");
           try
           {
               new Scanner(System.in).nextLine();
           }  
           catch(Exception e)
           {}  
    }
}
