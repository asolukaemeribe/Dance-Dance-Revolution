import java.util.*;

public enum ArrowDirection {
    UPARROW, DOWNARROW, LEFTARROW, RIGHTARROW;

    private static final List<ArrowDirection> VALUES = new ArrayList<ArrowDirection>();

    static {
        VALUES.add(ArrowDirection.UPARROW);
        VALUES.add(ArrowDirection.DOWNARROW);
        VALUES.add(ArrowDirection.LEFTARROW);
        VALUES.add(ArrowDirection.RIGHTARROW);
    }

    public static ArrayList<ArrowDirection> getValues() {
        return (ArrayList<ArrowDirection>) VALUES;
    }
}