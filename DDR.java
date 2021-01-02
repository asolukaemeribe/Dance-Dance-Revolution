import java.util.*;

public class DDR {
    private ArrowDirection[][] grid;
    private LinkedList<ArrowDirection> orders;
    private ArrayList<ArrowDirection> enumList = ArrowDirection.getValues();
    private LinkedList<ArrowDirection> ordersShuffler;
    private LinkedList<ArrowDirection> gridShuffler;
    private int score = 0;
    private String ordersString;

    public DDR() {
        resetEntireGame();
    }

    public void reset() {
        grid = new ArrowDirection[2][2];
        ordersShuffler = new LinkedList<ArrowDirection>();
        gridShuffler = new LinkedList<ArrowDirection>();
        orders = new LinkedList<ArrowDirection>();
        ordersString = "";

        for (int i = 0; i < 4; i++) {
            gridShuffler.add(enumList.get(i));
        }

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                int i = (int) (Math.random() * (gridShuffler.size()));
                grid[row][col] = gridShuffler.get(i);
                gridShuffler.remove(i);
            }
        }

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                ordersShuffler.add(grid[row][col]);
            }
        }

        for (int i = 0; i < 4; i++) {
            int z = (int) (Math.random() * (ordersShuffler.size()));
            orders.add(ordersShuffler.get(z));
            ordersShuffler.remove(z);
        }

    }

    public void resetEntireGame() {
        score = 0;
        grid = new ArrowDirection[2][2];
        ordersShuffler = new LinkedList<ArrowDirection>();
        gridShuffler = new LinkedList<ArrowDirection>();
        orders = new LinkedList<ArrowDirection>();
        ordersString = "";

        for (int i = 0; i < 4; i++) {
            gridShuffler.add(enumList.get(i));
        }

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                int i = (int) (Math.random() * (gridShuffler.size()));
                grid[row][col] = gridShuffler.get(i);
                gridShuffler.remove(i);
            }
        }

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                ordersShuffler.add(grid[row][col]);
            }
        }

        for (int i = 0; i < 4; i++) {
            int z = (int) (Math.random() * (ordersShuffler.size()));
            orders.add(ordersShuffler.get(z));
            ordersShuffler.remove(z);
        }

    }

    public GameObj getArrowObject(ArrowDirection arrow) {
        switch (arrow) {
            case LEFTARROW:
                return new LeftArrow();

            case RIGHTARROW:
                return new RightArrow();

            case UPARROW:
                return new UpArrow();

            case DOWNARROW:
                return new DownArrow();

            default:
                return null;
        }
    }

    public String getOrdersString() {
        GameObj obj;
        String s;
        for (int i = 0; i < orders.size(); i++) {
            obj = getArrowObject(orders.get(i));
            s = obj.getString();

            if (ordersString == "") {
                ordersString += s;
            } else {
                ordersString += ", " + s;
            }

        }

        return ordersString;
    }

    public void clickGrid(int row, int col) {
        if (grid[row][col] != null && grid[row][col].equals(orders.getFirst())) {
            grid[row][col] = null;
            orders.removeFirst();
        }
    }

    public boolean awardPoints() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] != null) {
                    return false;
                }
            }
        }
        score += 10;
        return true;
    }

    public int getScore() {
        return this.score;
    }

    public void setOrdersString(String s) {
        ordersString = s;
    }

    public LinkedList<ArrowDirection> getOrders() {
        return this.orders;
    }

    public ArrowDirection[][] getGrid() {
        return this.grid;
    }

}