package entropy.xu;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Toxic_Bus bus = new Toxic_Bus(8);
        bus.push("0011");
        bus.push("1100");
        bus.push("0101");
        bus.pop();
        System.out.println(bus);
    }
}
