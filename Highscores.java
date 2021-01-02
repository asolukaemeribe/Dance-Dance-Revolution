import java.awt.*;
import java.util.List;

import javax.swing.*;

public class Highscores extends JFrame {

    private static final long serialVersionUID = 1L;
    private List<String> printList;
    private String list = "";

    public Highscores(List<String> printList) {
        super("Highscores");
        this.printList = printList;
    }

    public void printHighscores() {
        setLocation(1000, 1000);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(750, 75));
        final JPanel panel = new JPanel();

        for (String s : printList) {
            list += s + "\n";
        }

        final JTextArea highscores = new JTextArea(list);
        highscores.setEditable(false);
        panel.add(highscores);
        add(panel, BorderLayout.CENTER);

        pack();

        setVisible(true);
    }
}