package Pipeline;
import java.util.ArrayList;
import java.util.MissingFormatWidthException;

import Pipeline.Constantes;
import Pipeline.Registrador;

public class CPU {
    private Registrador PC;
    private Registrador IDEXA;
    private Registrador IDEXB;
    private Registrador EXMEMB;
    private Registrador EXMEMALUOut;
    private Registrador MEMWBValue;
    private Registrador IFIDIR;
    private Registrador IDEXIR;
    private Registrador EXMEMIR;
    private Registrador MEMWBIR;
    private Memorias Regs;
    private Memorias IMemory;
    private Memorias DMemory;

    CPU(){
        this.PC = new Registrador(64);
        this.IDEXA = new Registrador(64);
        this.IDEXB = new Registrador(64);
        this.EXMEMB = new Registrador(64);
        this.EXMEMALUOut = new Registrador(64);
        this.MEMWBValue = new Registrador(64);
        this.IFIDIR = new Registrador(32);
        this.IDEXIR = new Registrador(32);
        this.EXMEMIR = new Registrador(32);
        this.MEMWBIR = new Registrador(32);
        this.Regs = new Memorias(64, 32);
        this.IMemory = new Memorias(32, 1024);
        this.DMemory = new Memorias(32, 1024); 
    }

    private void init(){
        this.IFIDIR.setConteudo(Constantes.NOP);
        this.IDEXIR.setConteudo(Constantes.NOP);
        this.EXMEMIR.setConteudo(Constantes.NOP);
        this.MEMWBIR.setConteudo(Constantes.NOP);
        for(int i = 0; i <= 31; i++){
            this.Regs.setMemorias(Conversor.intToBin(i), i);
        }
        System.out.println(this.IFIDIR.toString());
        this.IFIDIR.shiftRight(0);
        System.out.println(this.IFIDIR.toString());
    }

    public void run(){
        init();
        while(true){

        }

    }
}
