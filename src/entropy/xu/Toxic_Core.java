package entropy.xu;

import com.sun.tools.internal.xjc.model.CArrayInfo;

import java.io.IOException;

import static entropy.xu.Ins_Type.*;

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
                break;
            case P11:
                stack.push(new Toxic_Block(false, false, true, true));
                pc.increment();
                break;
            case POP:
                Toxic_Block blk = stack.pop().clone();
                bus.push(blk);
                pc.increment();
                break;
            case DIS:
                stack.pop();
                pc.increment();
                break;
            case RVS:
                Toxic_Block blkk = bus.pop().clone();
                stack.push(blkk);
                pc.increment();
                break;
            case SWP:
                Toxic_Block last_tos = stack.pop().clone();
                Toxic_Block last_ntos = stack.pop().clone();
                stack.push(last_tos);
                stack.push(last_ntos);
                pc.increment();
                break;
            case PC:
                bus.setAddress(pc.getBlocks());
                pc.increment();
                break;
            case CMP:
                boolean eq, gt, lt;
                eq = tos.isEqualTo(ntos);
                gt = tos.isGreaterThan(ntos);
                lt = tos.isLessThan(ntos);
                stack.push(new Toxic_Block(carry, lt, gt, eq));
                pc.increment();
                break;
            case ADD:
                carry = tos.add_carry(ntos);
                stack.push(tos.add(ntos).clone());
                pc.increment();
                break;
            case NAND:
                stack.push(tos.nand(ntos));
                pc.increment();
                break;
            case LS:
                carry = tos.ls_c();
                stack.pop();
                stack.push(tos.ls_s());
                pc.increment();
                break;
            case RS:
                carry = tos.rs_c();
                stack.pop();
                stack.push(tos.rs_s());
                pc.increment();
                break;
            case SV:
                mem.save(Toxic_Block.blocks2int(bus.getAddress()), tos.clone());
                stack.pop();
                pc.increment();
                break;
            case LD:
                stack.push(mem.load(Toxic_Block.blocks2int(bus.getAddress())));
                pc.increment();
                break;
            case B0:
                if(!tos.getLastBit())
                {
                    pc.branchTo(bus.getAddress());
                }
                break;
            case B1:
                if(tos.getLastBit())
                {
                    pc.branchTo(bus.getAddress());
                }
                break;
            default:
                throw new IllegalArgumentException("illegal instruction type: " + ins.type);
        }
    }

    public void display()
    {
        final int code_mem_display_width = 5;

        System.out.println("<------------------------------>");
        System.out.println("TOS:\t" + stack.tos().toString());
        System.out.println("NTOS:\t" + stack.ntos().toString());
        System.out.println("Carry:\t" + carry);
        System.out.println("Bus:\t" + Toxic_Block.blocks2int(bus.getAddress()));
        System.out.println("PC:\t\t" + pc.toInt());
        System.out.println("Code:");
        for(int i = -code_mem_display_width; i < code_mem_display_width; i ++)
        {
            if(i != 0)
                System.out.print(" ");
            else
                System.out.print("*");
            int address = pc.toInt() + i;
            System.out.println(address + ":\t" + mem.load(pc.toInt() + i).toString());
        }
        System.out.println("<------------------------------>");
    }
}
