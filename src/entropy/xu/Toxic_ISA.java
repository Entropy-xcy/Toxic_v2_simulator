package entropy.xu;

enum Ins_Type{
    P1, P11, RVS, CMP, POP, DIS, PC, SWP, ADD, NAND, LS, RS, SV, LD, B1, B0;
}

public class Toxic_ISA {
    public final static Ins_Type ISA[] = {Ins_Type.P1, Ins_Type.P11, Ins_Type.PC, Ins_Type.CMP,
                                            Ins_Type.POP, Ins_Type.DIS, Ins_Type.RVS, Ins_Type.SWP,
                                            Ins_Type.SV, Ins_Type.LD, Ins_Type.B0, Ins_Type.B1,
                                            Ins_Type.ADD, Ins_Type.NAND, Ins_Type.RS, Ins_Type.LS};
}
