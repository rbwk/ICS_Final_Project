import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TextFileReader {
    protected String filePath;
    protected ArrayList<String> lines;

    public TextFileReader(String filePath) {
        this.filePath = filePath;
        this.lines = new ArrayList<>();
    }

    public void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getLines() {
        return lines;
    }
}