/* TextFileWriter.java class
 * By Nathan, Aryan, and Victoria
 * Last updated: January 11th 2024
 */

package common;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TextFileWriter {
    protected String filePath;

    public TextFileWriter(String filePath) {
        this.filePath = filePath;
    }

    public void writeFile(List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void writeInitializationState(boolean state) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(String.valueOf(state));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void clearFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeQuestions(List<String> questions) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String question : questions) {
                writer.write(question);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeLeaderboard(String filePath, List<List<String>> leaderboardData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (List<String> rowData : leaderboardData) {
                String line = String.join(", ", rowData);
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
