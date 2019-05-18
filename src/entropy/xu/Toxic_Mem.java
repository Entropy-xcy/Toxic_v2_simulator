package entropy.xu;

public class Toxic_Mem {
    public final int addr_block_width;
    public final int hmemory_size;
    public Toxic_Block[] mem;
    public static final int pc0 = 16;


    public Toxic_Mem(int addr_bit_width)
    {
        this.addr_block_width = addr_bit_width / 4;
        hmemory_size = (int) Math.pow(2, addr_block_width * 4);
        mem = new Toxic_Block[hmemory_size];
        for(int i = 0; i < hmemory_size; i++)
        {
            mem[i] = new Toxic_Block();
        }
    }

    public void save(int addr, Toxic_Block blk)
    {
        mem[addr] = blk.clone();
    }

    public Toxic_Block load(int addr)
    {
        return mem[addr];
    }

    public void loadProgram(Toxic_Instruction[] code)
    {
        for(int i = 0; i < code.length; i ++)
        {
            mem[pc0 + i] = code[i].toToxicBlock();
        }
    }
}
