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
            bus_addr[i] = stack[Math.floorMod(tos_index - i, DEPTH)];
        }
        return bus_addr;
    }

    public void setAddress(Toxic_Block[] setAddr)
    {
        if(setAddr.length != bus_width / 4)
            throw new IllegalArgumentException("The width of the setAddr Must be the same as the Bus Width");
        for(int i = 0; i < bus_width / 4; i++)
        {
            stack[i] = setAddr[i];
        }
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
