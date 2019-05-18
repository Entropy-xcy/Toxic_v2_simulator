package entropy.xu;

import com.sun.org.apache.bcel.internal.generic.FASTORE;

public class Toxic_Block {
    boolean bit3, bit2, bit1, bit0;

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

    public int toInt()
    {
        int sum = 0;
        if(bit0)
            sum += 1;
        if(bit1)
            sum += 2;
        if(bit2)
            sum += 4;
        if(bit3)
            sum += 8;
        return sum;
    }

    public static int blocks2int(Toxic_Block[] blks)
    {
        int sum = 0;
        for(int i = 0; i < blks.length; i ++)
        {
            sum += blks[i].toInt() * (int) Math.pow(16, i);
        }
        return sum;
    }

    private static boolean FA_s(boolean a, boolean b, boolean c)
    {
        return a ^ b ^ c;
    }

    private static boolean FA_c(boolean a, boolean b, boolean c)
    {
        return a & b | c & (a ^ b);
    }

    public Toxic_Block add(Toxic_Block blk)
    {
        boolean sum0, sum1, sum2, sum3;
        boolean carry0, carry1, carry2, carry3;
        sum0 = FA_s(this.bit0, blk.bit0, false);
        carry0 = FA_c(this.bit0, blk.bit0, false);

        sum1 = FA_s(this.bit1, blk.bit1, carry0);
        carry1 = FA_c(this.bit1, blk.bit1, carry0);

        sum2 = FA_s(this.bit2, blk.bit2, carry1);
        carry2 = FA_c(this.bit2, blk.bit2, carry1);

        sum3 = FA_s(this.bit3, blk.bit3, carry2);
        carry3 = FA_c(this.bit3, blk.bit3, carry2);
        return new Toxic_Block(sum3, sum2, sum1, sum0);
    }

    public boolean add_carry(Toxic_Block blk)
    {
        boolean sum0, sum1, sum2, sum3;
        boolean carry0, carry1, carry2, carry3;
        sum0 = FA_s(this.bit0, blk.bit0, false);
        carry0 = FA_c(this.bit0, blk.bit0, false);

        sum1 = FA_s(this.bit1, blk.bit1, carry0);
        carry1 = FA_c(this.bit1, blk.bit1, carry0);

        sum2 = FA_s(this.bit2, blk.bit2, carry1);
        carry2 = FA_c(this.bit2, blk.bit2, carry1);

        sum3 = FA_s(this.bit3, blk.bit3, carry2);
        carry3 = FA_c(this.bit3, blk.bit3, carry2);
        return carry3;
    }

    public Toxic_Block nand(Toxic_Block blk)
    {
        boolean r0, r1, r2, r3;
        r0 = !(this.bit0 & blk.bit0);
        r1 = !(this.bit1 & blk.bit1);
        r2 = !(this.bit2 & blk.bit2);
        r3 = !(this.bit3 & blk.bit3);
        return new Toxic_Block(r3, r2, r1, r0);
    }

    public Toxic_Block ls();

    public Toxic_Block rs();

    public boolean ls_carry();
    public boolean rs_carry();
}
