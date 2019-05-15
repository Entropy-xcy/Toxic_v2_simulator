package entropy.xu;

import java.util.ArrayList;
import java.util.List;

public class Toxic_Stack {
    final int DEPTH;
    Toxic_Block[] stack;
    int tos_index;

    public Toxic_Stack()
    {
        this(8);
    }

    public Toxic_Stack(int depth){
        this.DEPTH = depth;
        stack = new Toxic_Block[this.DEPTH];
        for(int i = 0; i < DEPTH; i++)
        {
            stack[i] = new Toxic_Block();
        }
        tos_index = DEPTH - 1;
    }

    public void push(Toxic_Block blk)
    {
        tos_index = Math.floorMod(tos_index + 1, DEPTH);
        stack[tos_index] = blk.clone();
    }

    public void push(String blk)
    {
        Toxic_Block tblk = new Toxic_Block(blk);
        push(tblk);
    }

    public Toxic_Block pop()
    {
        Toxic_Block ret = this.tos();
        tos_index = Math.floorMod(tos_index - 1, DEPTH);
        return ret;
    }

    public Toxic_Block tos()
    {
        return stack[tos_index];
    }

    public Toxic_Block ntos()
    {
        return stack[Math.floorMod(tos_index - 1, DEPTH)];
    }

    @Override
    public String toString()
    {
        String ret = "";
        ret = "TOS:" + this.tos().toString() + "\nNTOS:" + this.ntos().toString();
        return  ret;
    }
}
