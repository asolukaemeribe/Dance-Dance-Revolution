import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game implements Runnable {
    public void run() {
        final JFrame frame = new JFrame("DDR V2");
        frame.setLocation(1000, 1000);

        final String inputName = JOptionPane.showInputDialog(frame, 
                "Please enter a username.", "Username",
                JOptionPane.PLAIN_MESSAGE);

        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);

        final GameGrid grid = new GameGrid(status, inputName);
        frame.add(grid, BorderLayout.CENTER);

        List<String> highScorefile = GameGrid.csvFileToTweets("files/Highscores.txt", 1);
        List<String> userNames = GameGrid.csvFileToTweets("files/Highscores.txt", 0);
        int[] numOrder = new int[highScorefile.size()];
        TreeMap<Integer, String> orginalSet = new TreeMap<Integer, String>();
        List<String> printList = new ArrayList<String>();

        for (int i = 0; i < highScorefile.size(); i++) {
            try {
                orginalSet.put(Integer.parseInt(highScorefile.get(i)), userNames.get(i));
                numOrder[i] = Integer.parseInt(highScorefile.get(i));
            } catch (NumberFormatException e) {
                System.out.println("Error caught");
            }
        }

        Arrays.sort(numOrder);
        for (int x : numOrder) {
            for (Map.Entry<Integer, String> entry : orginalSet.entrySet()) {
                String value = entry.getValue();
                Integer key = entry.getKey();
                if (x == key) {
                    printList.add("UserName: " + value + ", " + "Score: " + key);
                }
            }

        }

        if (printList.isEmpty()) {
            System.out.println("Empty");
        }

        for (String s : printList) {
            System.out.println("Higher Score: " + s);
        }

        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                grid.resetEntireGame();
            }
        });
        control_panel.add(reset);

        final JButton instructions = new JButton("Instructions");
        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Instructions i = new Instructions();
                i.printInstructions();
            }
        });
        control_panel.add(instructions);

        final JButton highscore = new JButton("Highscore");
        highscore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Highscores h = new Highscores(printList);
                h.printHighscores();
            }
        });
        control_panel.add(highscore);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        grid.resetEntireGame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}