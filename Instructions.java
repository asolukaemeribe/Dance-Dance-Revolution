import java.awt.*;
import javax.swing.*;

public class Instructions extends JFrame {

    private static final long serialVersionUID = 1L;

    public Instructions() {
        super("DDR V2 Instructions");
    }

    public void printInstructions() {
        setLocation(1000, 1000);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(750, 75));
        final JPanel panel = new JPanel();
        final JTextArea instructions = new JTextArea(
                "The objective of DDR "
                + "V2 is to succesfully"
                + " complete "
                + "as many ten point combos as you can in under a "
                + "minute!\nJust click each image on the grid in "
                + "the order their respective names appear at "
                + "the top of the screen!");
        instructions.setEditable(false);
        panel.add(instructions);
        add(panel, BorderLayout.CENTER);

        pack();

        setVisible(true);
    }
}