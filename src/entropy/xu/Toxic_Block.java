package entropy.xu;

public class Toxic_Block {
    private boolean bit3, bit2, bit1, bit0;

    public Toxic_Block()
    {
        bit0 = false;
        bit1 = false;
        bit2 = false;
        bit3 = false;
    }

    public Toxic_Block(boolean bit3, boolean bit2, boolean bit1, boolean bit0)
    {
        this.bit0 = bit0;
        this.bit1 = bit1;
        this.bit2 = bit2;
        this.bit3 = bit3;
    }

    private static boolean int2bool(int i)
    {
        switch (i)
        {
            case 1:
                return true;
            case 0:
                return false;
            default:
                throw new IllegalArgumentException("Illegal bit: " + i);
        }
    }

    private static String bool2str(boolean b)
    {
        if(b)
            return "1";
        else
            return "0";
    }

    private static boolean char2bool(char c)
    {
        switch (c)
        {
            case '1':
                return true;
            case '0':
                return false;
                default:
                    throw new IllegalArgumentException("Invalid Char to convert to boolean!");
        }
    }

    public Toxic_Block(int bit3, int bit2, int bit1, int bit0)
    {
        this.bit0 = int2bool(bit0);
        this.bit1 = int2bool(bit1);
        this.bit2 = int2bool(bit2);
        this.bit3 = int2bool(bit3);
    }

    public Toxic_Block(String reg_bits)
    {
        int bit_len = reg_bits.length();
        if (bit_len != 4){
            throw new IllegalArgumentException("Invalid Bits String: "+ reg_bits);
        }

        this.bit3 = char2bool(reg_bits.charAt(0));
        this.bit2 = char2bool(reg_bits.charAt(1));
        this.bit1 = char2bool(reg_bits.charAt(2));
        this.bit0 = char2bool(reg_bits.charAt(3));
    }

    @Override
    public Toxic_Block clone()
    {
        Toxic_Block blk = new Toxic_Block();
        blk.bit0 = this.bit0;
        blk.bit1 = this.bit1;
        blk.bit2 = this.bit2;
        blk.bit3 = this.bit3;
        return blk;
    }

    @Override
    public String toString() {
        return bool2str(this.bit3)+bool2str(this.bit2)+bool2str(this.bit1)+bool2str(this.bit0);
    }
}
