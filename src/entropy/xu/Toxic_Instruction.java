package entropy.xu;

enum Ins_Type{
    P0, P1, RVS, CMP, POP, DIS, PC, SWP, ADD, NAND, LS, RS, SV, LD, B1, B0;
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
                type = Ins_Type.P0;
                break;
            case "0001":
                type = Ins_Type.P1;
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

    public Toxic_Instruction(String str)
    {
        this(new Toxic_Register(str));
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
