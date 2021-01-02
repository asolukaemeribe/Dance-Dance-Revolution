import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GameGrid extends JPanel {
    private DDR ddr;
    private JLabel status;

    private final int gridWidth = 1000;
    private final int gridHeight = 1000;
    private final int rapidInterval = 35;
    private final int gameoverInterval = 60000;
    private boolean gameIsOver = false;
    private BufferedImage background;
    private List<String> stringList;
    private String username = "";
    private Timer timesUp;
    private Timer timer;
    private final String backgroundPNG = "files/flappybirdbackground.png";
    private int numberOfTimesWritern = 0;

    public GameGrid(JLabel status, String name) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        username = name;
        stringList = new LinkedList<String>();

        timer = new Timer(rapidInterval, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start();

        timesUp = new Timer(gameoverInterval, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameIsOver = true;
            }
        });
        timesUp.start();

        setFocusable(true);

        ddr = new DDR();
        this.status = status;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                int x = p.x;
                int y = p.y;

                if ((x < 500 && x > 250) && (y < 350 && y > 100)) {
                    ddr.clickGrid(0, 0);
                } else if ((x < 750 && x > 500) && (y < 350 && y > 100)) {
                    ddr.clickGrid(0, 1);
                } else if ((x < 500 && x > 250) && (y < 600 && y > 350)) {
                    ddr.clickGrid(1, 0);
                } else if ((x < 750 && x > 500) && (y < 600 && y > 350)) {
                    ddr.clickGrid(1, 1);
                }

                ddr.setOrdersString("");
                repaint();
            }
        });

    }

    void tick() {
        if (ddr.awardPoints()) {
            ddr.reset();
            repaint();
        }

        if (gameIsOver) {
            timer.stop();
            timesUp.stop();
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!gameIsOver) {
            int squareSwitcher = 0;
            try {
                background = ImageIO.read(new File(backgroundPNG));
                g.drawImage(background, 0, 0, 1000, 1000, null);
            } catch (IOException e) {
                System.out.println("Internal Error:" + e.getMessage());
            }

            g.drawLine(250, 600, 750, 600);
            g.drawLine(250, 100, 750, 100);
            g.drawLine(250, 100, 250, 600);
            g.drawLine(750, 100, 750, 600);
            g.drawLine(250, 350, 750, 350);
            g.drawLine(500, 100, 500, 600);

            for (int row = 0; row < ddr.getGrid().length; row++) {
                for (int col = 0; col < (ddr.getGrid())[row].length; col++) {
                    if (squareSwitcher == 0 && (ddr.getGrid())[row][col] != null) {
                        GameObj obj = ddr.getArrowObject((ddr.getGrid())[row][col]);
                        obj.draw(g, 300, 150);
                    } else if (squareSwitcher == 1 && (ddr.getGrid())[row][col] != null) {
                        GameObj obj = ddr.getArrowObject((ddr.getGrid())[row][col]);
                        obj.draw(g, 550, 150);
                    } else if (squareSwitcher == 2 && (ddr.getGrid())[row][col] != null) {
                        GameObj obj = ddr.getArrowObject((ddr.getGrid())[row][col]);
                        obj.draw(g, 300, 400);
                    } else if (squareSwitcher == 3 && (ddr.getGrid())[row][col] != null) {
                        GameObj obj = ddr.getArrowObject((ddr.getGrid())[row][col]);
                        obj.draw(g, 550, 400);
                    }

                    squareSwitcher++;
                }
            }

            int fontSize = 20;
            Font f = new Font("Comic Sans MS", Font.BOLD, fontSize);
            g.setFont(f);

            g.drawString("Score", 850, 300);
            g.drawString(ddr.getScore() + "", 865, 350);
            g.drawString(ddr.getOrdersString(), 370, 50);
            ddr.setOrdersString("");
        } else {
            try {
                background = ImageIO.read(new File(backgroundPNG));
                g.drawImage(background, 0, 0, 1000, 1000, null);
            } catch (IOException e) {
                System.out.println("Internal Error:" + e.getMessage());
            }

            status.setText("Game Over!!!");

            int fontSize = 50;
            Font f = new Font("Comic Sans MS", Font.BOLD, fontSize);
            g.setFont(f);

            g.drawString("Game Over!!!", 375, 325);
            g.drawString("Final Score", 395, 375);
            g.drawString(ddr.getScore() + "", 475, 450);

            if (numberOfTimesWritern == 0) {
                numberOfTimesWritern++;
                stringList.add(username + "," + ddr.getScore());
                writeStringsToFile(stringList, "files/Highscores.txt", true);
            }

        }

    }

    public void resetEntireGame() {
        ddr = new DDR();
        gameIsOver = false;
        timer.stop();
        timesUp.stop();
        timer = new Timer(rapidInterval, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start();

        timesUp = new Timer(gameoverInterval, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameIsOver = true;
            }
        });
        timesUp.start();

        status.setText("Game On");
        repaint();
        requestFocusInWindow();
    }

    public void setGameIsOver(boolean newVal) {
        gameIsOver = newVal;
    }

    static String extractColumn(String csvLine, int csvColumn) {
        try {
            if (csvLine != null) {
                String[] row = csvLine.split(",");
                if (row[csvColumn] == null) {
                    return null;
                } else {
                    return row[csvColumn];
                }
            } else {
                return null;
            }
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void writeStringsToFile(List<String> stringsToWrite, String filePath, boolean append) {
        File file = Paths.get(filePath).toFile();
        BufferedWriter bw = null;
        try {
            FileWriter printer = new FileWriter(file, append);
            bw = new BufferedWriter(printer);

            for (String s : stringsToWrite) {
                bw.newLine();
                bw.write(s);
            }
            bw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found");
        } catch (IOException e) {
            System.out.println("File parsing is at its end");
        }
    }

    static List<String> csvFileToTweets(String pathToCSVFile, int tweetColumn) {
        FileLineIterator i = new FileLineIterator(pathToCSVFile);
        List<String> arr = new LinkedList<String>();
        try {
            while (i.hasNext()) {
                String s = extractColumn(i.next(), tweetColumn);
                if (s != null) {
                    arr.add(s);
                }
            }

            return arr;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(gridWidth, gridHeight);
    }
}