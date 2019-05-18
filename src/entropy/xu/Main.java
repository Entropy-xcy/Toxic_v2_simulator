package entropy.xu;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Toxic_Instruction[] blks = Toxic_Instruction.loadAsm("test.asm");
        for(int i = 0; i < blks.length; i++)
        {
            System.out.println(blks[i].getBits());
        }
    }
}
