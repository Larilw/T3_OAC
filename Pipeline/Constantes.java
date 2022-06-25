package Pipeline;

import java.util.ArrayList;
import java.util.Arrays;

public class Constantes {
    public static final ArrayList<Integer> LD = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 1, 1));
    public static final ArrayList<Integer> SD = new ArrayList<>(Arrays.asList(0, 1, 0, 0, 0, 1, 1));
    public static final ArrayList<Integer> BEQ = new ArrayList<>(Arrays.asList(1, 1, 0, 0, 0, 1, 1));
    public static final ArrayList<Integer> NOP = new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0, 1, 1));
    public static final ArrayList<Integer> ALUop = new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0, 1, 1));
}
