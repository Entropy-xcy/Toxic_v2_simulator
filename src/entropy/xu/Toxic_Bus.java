package entropy.xu;

public class Toxic_Bus extends Toxic_Stack {
    private final int bus_width;

    public Toxic_Bus(int bus_width)
    {
        super(bus_width / 4 * 2);
        this.bus_width = bus_width;
    }

    public Toxic_Block[] getAddress()
    {
        Toxic_Block[] bus_addr = new Toxic_Block[bus_width / 4];
        for(int i = 0; i < bus_width / 4; i ++)
        {
            bus_addr[i] = super.stack[Math.floorMod(tos_index - i, DEPTH)];
        }
        return bus_addr;
    }

    @Override
    public String toString() {
        String ret = "";
        Toxic_Block[] bus_addr = getAddress();
        for(Toxic_Block blk: bus_addr)
        {
            ret = blk.toString() + " " + ret;
        }
        return ret;
    }
}
