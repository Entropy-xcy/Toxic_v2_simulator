package entropy.xu;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Toxic_PC pc = new Toxic_PC(8);

        for(int i = 0; i < 300; i ++)
        {
            System.out.println(pc.toInt());
            pc.increment();
        }
    }
}
