package entropy.xu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;





public class Toxic_Instruction extends Toxic_Block {
    Ins_Type type;

    public Toxic_Instruction(String asm)
    {
        Toxic_Block bits = new Toxic_Block();
        switch (asm)
        {
            case "P1":
                type = Ins_Type.P1;
                bits = new Toxic_Block("0000");
                break;
            case "P11":
                type = Ins_Type.P11;
                bits = new Toxic_Block("0001");
                break;
            case "CMP":
                type = Ins_Type.CMP;
                bits = new Toxic_Block("0011");
                break;
            case "PC":
                type = Ins_Type.PC;
                bits = new Toxic_Block("0010");
                break;
            case "POP":
                type = Ins_Type.POP;
                bits = new Toxic_Block("0100");
                break;
            case "DIS":
                type = Ins_Type.DIS;
                bits = new Toxic_Block("0101");
                break;
            case "SWP":
                type = Ins_Type.SWP;
                bits = new Toxic_Block("0111");
                break;
            case "RVS":
                type = Ins_Type.RVS;
                bits = new Toxic_Block("0110");
                break;
            case "ADD":
                type = Ins_Type.ADD;
                bits = new Toxic_Block("1100");
                break;
            case "NAND":
                type = Ins_Type.NAND;
                bits = new Toxic_Block("1101");
                break;
            case "LS":
                type = Ins_Type.LS;
                bits = new Toxic_Block("1111");
                break;
            case "RS":
                type = Ins_Type.RS;
                bits = new Toxic_Block("1110");
                break;
            case "SV":
                type = Ins_Type.SV;
                bits = new Toxic_Block("1000");
                break;
            case "LD":
                type = Ins_Type.LD;
                bits = new Toxic_Block("1001");
                break;
            case "B1":
                type = Ins_Type.B1;
                bits = new Toxic_Block("1011");
                break;
            case "B0":
                type = Ins_Type.B0;
                bits = new Toxic_Block("1010");
                break;
                default: throw new IllegalArgumentException("No Corresponding Instruction");
        }
        this.bit0 = bits.bit0;
        this.bit1 = bits.bit1;
        this.bit2 = bits.bit2;
        this.bit3 = bits.bit3;
    }

    public Toxic_Instruction(Toxic_Block blk)
    {
        type = Toxic_ISA.ISA[blk.toInt()];
        this.bit0 = blk.bit0;
        this.bit1 = blk.bit1;
        this.bit2 = blk.bit2;
        this.bit3 = blk.bit3;
    }


    @Override
    public String toString() {
        return type.toString();
    }

    public String getBits()
    {
        return super.toString();
    }

    public static Toxic_Instruction[] loadAsm(String filename) throws IOException {
        List<Toxic_Instruction> code_lst = new ArrayList<Toxic_Instruction>();
        File file = new File(filename);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null)
            code_lst.add(new Toxic_Instruction(st));

        Toxic_Instruction[] ins = new Toxic_Instruction[code_lst.size()];
        for(int i = 0; i < code_lst.size(); i++)
        {
            ins[i] = code_lst.get(i);
        }

        return ins;
    }

    public Toxic_Block toToxicBlock()
    {
        return new Toxic_Block(this.bit3, this.bit2, this.bit1, this.bit0);
    }
}
