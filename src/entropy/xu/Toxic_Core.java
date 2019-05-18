package entropy.xu;

import java.io.IOException;

public class Toxic_Core {
    public Toxic_Mem mem;
    public Toxic_Bus bus;
    public Toxic_Stack stack;
    public Toxic_PC pc;
    public boolean carry;

    public Toxic_Core(int stack_depth, int bus_width)
    {
        stack = new Toxic_Stack(stack_depth);
        bus = new Toxic_Bus(bus_width);
        mem = new Toxic_Mem(bus_width);
        pc = new Toxic_PC(bus_width);
        carry = false;
    }

    public void loadProgram(String filename) throws IOException
    {
        Toxic_Instruction[] asm = Toxic_Instruction.loadAsm(filename);
        mem.loadProgram(asm);
    }

    public void step()
    {
        // Instruction fetch
        Toxic_Instruction ins = new Toxic_Instruction(mem.load(pc.toInt()));

        // Instruction Decode
        Toxic_Block tos = stack.tos();
        Toxic_Block ntos = stack.ntos();

        // Execution
        switch (ins.type)
        {
            case P1:
                stack.push(new Toxic_Block(false, false, false, true));
                pc.increment();
            case P11:
                stack.push(new Toxic_Block(false, false, true, true));
                pc.increment();
            case POP:
                Toxic_Block blk = stack.pop().clone();
                bus.push(blk);
                pc.increment();
            case DIS:
                stack.pop();
                pc.increment();
            case RVS:
                Toxic_Block blkk = bus.pop().clone();
                stack.push(blkk);
                pc.increment();
            case SWP:
                Toxic_Block last_tos = stack.pop().clone();
                Toxic_Block last_ntos = stack.pop().clone();
                stack.push(last_tos);
                stack.push(last_ntos);
                pc.increment();
            case PC:
                bus.setAddress(pc.getBlocks());
                pc.increment();
            case CMP:
                boolean eq, gt, lt;
                eq = tos.isEqualTo(ntos);
                gt = tos.isGreaterThan(ntos);
                lt = tos.isLessThan(ntos);
                stack.push(new Toxic_Block(carry, lt, gt, eq));
                pc.increment();
            case ADD:
                carry = tos.add_carry(ntos);
                stack.push(tos.add(ntos).clone());
                pc.increment();
            case NAND:
                stack.push(tos.nand(ntos));
                pc.increment();
            case LS:
                carry = tos.ls_c();
                stack.pop();
                stack.push(tos.ls_s());
                pc.increment();
            case RS:
                carry = tos.rs_c();
                stack.pop();
                stack.push(tos.rs_s());
                pc.increment();
            case SV:
                mem.save(Toxic_Block.blocks2int(bus.getAddress()), tos.clone());
                stack.pop();
                pc.increment();
            case LD:
                stack.push(mem.load(Toxic_Block.blocks2int(bus.getAddress())));
                pc.increment();
            case B0:
                if(!tos.getLastBit())
                {
                    pc.branchTo(bus.getAddress());
                }
            case B1:
                if(tos.getLastBit())
                {
                    pc.branchTo(bus.getAddress());
                }
            default:
                throw new IllegalArgumentException("illegal instruction type!");
        }
    }
}
