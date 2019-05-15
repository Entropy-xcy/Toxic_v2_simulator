package entropy.xu;

enum Ins_Type{
    P1, P11, RVS, CMP, POP, DIS, PC, SWP, ADD, NAND, LS, RS, SV, LD, B1, B0;
}

public class Toxic_Instruction {
    Toxic_Register bits;
    Ins_Type type;

    public Toxic_Instruction(Toxic_Register reg)
    {
        bits = reg;
        switch (reg.toString())
        {
            case "0000":
                type = Ins_Type.P1;
                break;
            case "0001":
                type = Ins_Type.P11;
                break;
            case "0010":
                type = Ins_Type.PC;
                break;
            case "0011":
                type = Ins_Type.CMP;
                break;
            case "0100":
                type = Ins_Type.POP;
                break;
            case "0101":
                type = Ins_Type.DIS;
                break;
            case "0111":
                type = Ins_Type.SWP;
                break;
            case "0110":
                type = Ins_Type.RVS;
                break;
            case "1100":
                type = Ins_Type.ADD;
                break;
            case "1101":
                type = Ins_Type.NAND;
                break;
            case "1111":
                type = Ins_Type.LS;
                break;
            case "1110":
                type = Ins_Type.RS;
                break;
            case "1000":
                type = Ins_Type.SV;
                break;
            case "1001":
                type = Ins_Type.LD;
                break;
            case "1011":
                type = Ins_Type.B1;
                break;
            case "1010":
                type = Ins_Type.B0;
                break;
                default:
                    throw new IllegalArgumentException("Invalid Instruction!");
        }
    }

    public Toxic_Instruction(String asm)
    {
        switch (asm)
        {
            case "P1":
                type = Ins_Type.P1;
                bits = new Toxic_Register("0000");
                break;
            case "P11":
                type = Ins_Type.P11;
                bits = new Toxic_Register("0001");
                break;
            case "CMP":
                type = Ins_Type.CMP;
                bits = new Toxic_Register("0011");
                break;
            case "PC":
                type = Ins_Type.PC;
                bits = new Toxic_Register("0010");
                break;
            case "POP":
                type = Ins_Type.POP;
                bits = new Toxic_Register("0100");
                break;
            case "DIS":
                type = Ins_Type.DIS;
                bits = new Toxic_Register("0101");
                break;
            case "SWP":
                type = Ins_Type.SWP;
                bits = new Toxic_Register("0111");
                break;
            case "RVS":
                type = Ins_Type.RVS;
                bits = new Toxic_Register("0110");
                break;
            case "ADD":
                type = Ins_Type.ADD;
                bits = new Toxic_Register("1100");
                break;
            case "NAND":
                type = Ins_Type.NAND;
                bits = new Toxic_Register("1101");
                break;
            case "LS":
                type = Ins_Type.LS;
                bits = new Toxic_Register("1111");
                break;
            case "RS":
                type = Ins_Type.RS;
                bits = new Toxic_Register("1110");
                break;
            case "SV":
                type = Ins_Type.SV;
                bits = new Toxic_Register("1000");
                break;
            case "LD":
                type = Ins_Type.LD;
                bits = new Toxic_Register("1001");
                break;
            case "B1":
                type = Ins_Type.B1;
                bits = new Toxic_Register("1011");
                break;
            case "B0":
                type = Ins_Type.B0;
                bits = new Toxic_Register("1010");
                break;
                default: throw new IllegalArgumentException("No Corresponding Instruction");
        }
    }


    @Override
    public String toString() {
        return type.toString();
    }
}
