package entropy.xu;

public class Toxic_PC {
    final int blockWidth;
    static final int pc0 = 16;
    private Toxic_Block[] pc;

    public Toxic_PC(int bitWidth)
    {
        blockWidth = bitWidth / 4;
        pc = new Toxic_Block[blockWidth];
        for(int i=0; i < pc.length; i ++)
        {
            pc[i] = new Toxic_Block();
        }
        for(int i = 0; i < pc0; i++)
            this.increment();
    }

    public void increment()
    {
        boolean carry = true;
        for(int i = 0; i < pc.length; i++)
        {
            boolean c = pc[i].add_carry(new Toxic_Block(false, false, false, carry));
            pc[i] = pc[i].add(new Toxic_Block(false, false, false, carry));
            carry = c;
            if(carry == false)
                break;
        }
    }

    public Toxic_Block[] getBlocks()
    {
        return pc;
    }

    public void branchTo(Toxic_Block[] busAddr)
    {
        if(busAddr.length != pc.length)
            throw new IllegalArgumentException("Incompatible BUS Width!!");
        for(int i = 0; i < busAddr.length; i ++)
        {
            pc[i] = busAddr[i].clone();
        }
    }

    public int toInt()
    {
        return Toxic_Block.blocks2int(pc);
    }
}
