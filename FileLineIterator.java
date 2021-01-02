import java.io.*;
import java.util.*;

public class FileLineIterator implements Iterator<String> {

    BufferedReader buffR = null;
    String curr = "";
    private int lineNum = 0;

    public FileLineIterator(String filePath) {
        File aFile = new File(filePath);
        if (filePath == null || !aFile.exists()) {
            throw new IllegalArgumentException();
        }

        try {
            FileReader r = new FileReader(filePath);
            buffR = new BufferedReader(r);
            curr = buffR.readLine();
            lineNum = -1;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean hasNext() {
        if (curr != null) {
            return true;
        }

        return false;
    }

    @Override
    public String next() {
        if (hasNext()) {
            try {
                lineNum++;
                String s = curr;
                curr = buffR.readLine();
                return s;
            } catch (IOException e) {
                throw new NoSuchElementException();
            }
        }

        return (lineNum + curr);

    }
}
