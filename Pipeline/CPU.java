package Pipeline;
import java.util.ArrayList;
import java.util.Arrays;

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

    //Definição das operações que serão executadas
    private void setarOperacoes(){

        //Definição de instrução de soma: reg(5)=5 -> addi x5,x2,3 //x5=x2+3
        ArrayList<Integer> operacao1 = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 
         0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1));
         //Definição de instrução de soma reg(6)=10->addi x6,x0,10/x6=x0+10
         ArrayList<Integer> operacao2 = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0,
         0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1));
         //Definição de instrução de soma reg(10)=20->addi x10,x0,20
         ArrayList<Integer> operacao3 = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 
         0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1));
         //Definição de instrução lw x11, 0(x10)//x11= Mem[20]
         ArrayList<Integer> operacao4 = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
         0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1));
         //Definição de instrução lw x12,4(x10)//x12= Mem[24]
         ArrayList<Integer> operacao5 = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1,
         0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1));
         //Definição de instrução add x14, x11, x12//x14= x11+x12
         ArrayList<Integer> operacao6 = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1,
         0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1));

         //Salva as instruções na memória de intruções
        this.IMemory.setMemorias(operacao1, 0);
        this.IMemory.setMemorias(operacao2, 1);
        this.IMemory.setMemorias(operacao3, 2);
        this.IMemory.setMemorias(operacao4, 3);
        this.IMemory.setMemorias(operacao5, 4);
        this.IMemory.setMemorias(operacao6, 5);
    }

    //Inicializa alguns registradores
    private void init(){
        this.IFIDIR.setConteudo(Constantes.NOP);
        this.IDEXIR.setConteudo(Constantes.NOP);
        this.EXMEMIR.setConteudo(Constantes.NOP);
        this.MEMWBIR.setConteudo(Constantes.NOP);
        for(int i = 0; i <= 31; i++){
            this.Regs.setMemorias(Conversor.intToBin(i), i);
        }
        setarOperacoes();
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

        Integer iteracao = 0;

        //Execução em loop
        while(true){
            Integer enderecoNovaInstrucao;
            Integer enderecoA;
            Integer enderecoB;
            Integer enderecoDMemory;

            System.out.println("Iteracao: " + iteracao);

            //Primeira instrução é buscada e incrementa PC
            System.out.println("Estagio IF - obtencao da instrucao");
            System.out.println("PC: " + Conversor.binToInt(this.PC.conteudo));
            
            enderecoNovaInstrucao = Conversor.binToInt(Conversor.shiftRight(2, this.PC.conteudo));
            this.IFIDIR.setConteudo(this.IMemory.conteudo.get(enderecoNovaInstrucao).conteudo);
            this.PC.inc(4);
            
            System.out.println("Instrucao obtida: " + this.IFIDIR.toString());

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
            EXMEMop.setConteudo(this.EXMEMIR.getBits(25, 31));
            MEMWBrd.setConteudo(this.MEMWBIR.getBits(20, 24));
            
            //Busca dos registradores A e B
            System.out.println("Estagio ID - Decodificacao da instrucao e leitura dos registradores");
            enderecoA = Conversor.binToInt(IFIDrs1.conteudo);
            this.IDEXA.setConteudo(this.Regs.conteudo.get(enderecoA).conteudo);
            
            enderecoB = Conversor.binToInt(IFIDrs2.conteudo);
            this.IDEXB.setConteudo(this.Regs.conteudo.get(enderecoB).conteudo);
            
            //Atualização do IR
            this.IDEXIR.setConteudo(this.IFIDIR.conteudo);
            IDEXop.setConteudo(this.IDEXIR.getBits(25, 31));
            System.out.println("IDEXIR: " + IDEXIR.toString());

            Integer Ain = Conversor.binToInt(IDEXA.conteudo);
            Integer Bin = Conversor.binToInt(IDEXB.conteudo);

            System.out.println("IDEXA: " + Conversor.binToInt(IDEXA.conteudo));
            System.out.println("IDEXB: " + Conversor.binToInt(IDEXB.conteudo));


            //Realização do cálculo do endereço ou execução de operação na ULA
            System.out.println("Estagio EX - Execucao da instrucao ou calculo de endereco");
            System.out.println("IDEXop: " + Conversor.binToInt(IDEXop.conteudo));
            if(IDEXop.compareTo(Constantes.LD)){
                System.out.println("Instrucao LD");
                Integer soma = 0;
                ArrayList<Integer> aux;
                aux = Conversor.replicar(53, this.IDEXIR.getBits(00, 00).get(0));
                aux = Conversor.concatenarArray(aux, this.IDEXIR.getBits(1, 11));
                soma = Conversor.binToInt(IDEXA.conteudo) + Conversor.binToInt(aux);
                this.EXMEMALUOut.setConteudo(Conversor.intToBin(soma));
                System.out.println("EXMEMALUOut: " + Conversor.binToInt(this.EXMEMALUOut.conteudo));
            }
            else if(IDEXop.compareTo(Constantes.SD)){
                System.out.println("Instrucao SD");
                Integer soma = 0;
                ArrayList<Integer> aux;
                aux = Conversor.replicar(53, this.IDEXIR.getBits(00, 00).get(0));
                aux = Conversor.concatenarArray(aux, this.IDEXIR.getBits(1, 6));
                aux = Conversor.concatenarArray(aux, this.IDEXIR.getBits(20, 24));
                soma = Conversor.binToInt(IDEXA.conteudo) + Conversor.binToInt(aux);
                this.EXMEMALUOut.setConteudo(Conversor.intToBin(soma));
                System.out.println("EXMEMALUOut: " + Conversor.binToInt(this.EXMEMALUOut.conteudo));
            }
            else if(IDEXop.compareTo(Constantes.ALUop)){
                System.out.println("Instrucao ALUop");
                if(Conversor.binToInt(this.IDEXIR.getBits(0, 6)) == 0){
                    Integer saida = Ain + Bin;
                    this.EXMEMALUOut.setConteudo(Conversor.intToBin(saida));
                    System.out.println("EXMEMALUOut: " + Conversor.binToInt(this.EXMEMALUOut.conteudo));
                }
            }

            //Atualização dos registradores IR e B
            this.EXMEMIR.setConteudo(IDEXIR.conteudo);
            this.EXMEMB.setConteudo(IDEXB.conteudo);

            System.out.println("EXMEMIR: " + this.EXMEMIR.toString());
            System.out.println("EXMEMB: " + this.EXMEMB.toString());

            //Estágio da memória do pipeline
            System.out.println("Estagio MEM - Acessa os dados da memoria");
            if(EXMEMop.compareTo(Constantes.ALUop)){
                MEMWBValue.setConteudo(EXMEMALUOut.conteudo);
            }
            else if(EXMEMop.compareTo(Constantes.LD)){
                enderecoDMemory = Conversor.binToInt(Conversor.shiftRight(2, this.EXMEMALUOut.conteudo));
                this.MEMWBValue.setConteudo(this.DMemory.conteudo.get(enderecoDMemory).conteudo);
            }
            else if(EXMEMop.compareTo(Constantes.SD)){
                ArrayList<Integer> shiftAluout = Conversor.shiftRight(2, EXMEMALUOut.conteudo);
                Integer enderecoShift = Conversor.binToInt(shiftAluout);
                this.DMemory.setMemorias(EXMEMB.conteudo, enderecoShift);
            }

            System.out.println("MEMWBVALUE: " + this.MEMWBValue.toString());
            System.out.println("DMemory: ");
            this.DMemory.imprimirParteMemoria("\n");

            //Passa o IR a frente
            this.MEMWBIR.setConteudo(EXMEMIR.conteudo);
            MEMWBop.setConteudo(this.MEMWBIR.getBits(25, 31));
            System.out.println("MEMWBIR: " + this.MEMWBIR.toString());

            //Estágio WB
            System.out.println("Estagio WB - Escreve o resultado");
            if((MEMWBop.compareTo(Constantes.LD) || MEMWBop.compareTo(Constantes.ALUop)) && Conversor.binToInt(MEMWBrd.conteudo) != 0){
                this.Regs.setMemorias(MEMWBValue.conteudo, Conversor.binToInt(MEMWBrd.conteudo));
                System.out.println("Regs: ");
                this.Regs.imprimirParteMemoria(", ");
            }
            iteracao++;
            Menu.pressionarEnterParaContinuar();
            Menu.limparTela();
        }
    }
}
