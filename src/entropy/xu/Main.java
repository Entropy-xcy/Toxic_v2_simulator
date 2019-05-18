package entropy.xu;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Toxic_Core core = new Toxic_Core(8, 8);
        core.loadProgram("test.asm");
        core.step();
        core.step();
        core.step();
        core.step();
        core.step();

        core.display();
    }
}
