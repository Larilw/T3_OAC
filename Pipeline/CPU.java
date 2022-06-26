package Pipeline;
import java.util.ArrayList;
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
    }

    public void run(){
        init();

        //Declaração de todos os wires 
        Registrador IFIDrs1 = new Registrador(5);
        Registrador IFIDrs2 = new Registrador(5);
        Registrador IDEXop = new Registrador(7);
        Registrador EXMEMop = new Registrador(7);
        Registrador MEMWBop = new Registrador(7);
        Registrador MEMWBrd = new Registrador(5);

        while(true){
            Integer enderecoNovaInstrucao;
            Integer enderecoA;
            Integer enderecoB;

            //Primeira instrução é buscada e incrementa PC
            enderecoNovaInstrucao = Conversor.binToInt(Conversor.shiftRight(2, this.PC.conteudo));
            this.IFIDIR.setConteudo(this.IMemory.conteudo.get(enderecoNovaInstrucao).conteudo);
            this.PC.inc(4);

            /*Fazendo o assign dos wires
             * Conversão do índice em binário e do índice do ArrayList
             * Posição no ArrayList em java
             * 00 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31
             * 
             * Posição no índice em binário
             * 31 30 29 28 27 26 25 24 23 22 21 20 19 18 17 16 15 14 13 12 11 10 09 08 07 06 05 04 03 02 01 00
             */ 
            IFIDrs1.setConteudo(this.IFIDIR.getBits(12, 16));
            IFIDrs2.setConteudo(this.IFIDIR.getBits(7, 11));
            IDEXop.setConteudo(this.IDEXIR.getBits(25, 31));
            EXMEMop.setConteudo(this.EXMEMIR.getBits(25, 31));
            MEMWBop.setConteudo(this.MEMWBIR.getBits(25, 31));
            MEMWBrd.setConteudo(this.MEMWBIR.getBits(20, 24));

            //Busca dos registradores A e B
            enderecoA = Conversor.binToInt(IFIDrs1.conteudo);
            this.IDEXA.setConteudo(this.Regs.conteudo.get(enderecoA).conteudo);

            enderecoB = Conversor.binToInt(IFIDrs2.conteudo);
            this.IDEXB.setConteudo(this.Regs.conteudo.get(enderecoB).conteudo);

            //Atualização do IR
            this.IDEXIR.setConteudo(this.IFIDIR.conteudo);

            
        }

    }
}
