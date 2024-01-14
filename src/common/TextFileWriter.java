/* TextFileWriter.java class
 * By Nathan, Aryan, and Victoria
 * Last updated: January 11th 2024
 * 
 * Class used throughout game code to write data to files. Contains different methods for different applications of 
 * writing to text file.
 * 
 */

package common;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFileWriter {
    protected String filePath; // Path of file being written to.
    
    /**
     * Constructor 
     * 
     * @param filePath // Path to file that will be written to
     */
    public TextFileWriter(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method used to write to txt if no special action needs to be taken
     * 
     * @param lines // Contents to be written to file.
     */
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
    
    /**
     * Special method used by Initialization.java to store initialization state of the game.
     * A backup source of security to ensure files are not accidentally wiped mid game.
     * 
     * @param state // State of Initialization that is to be written to file.
     */
    public void writeInitializationState(boolean state) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(String.valueOf(state));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used by Initialization.java to clear all temp files at the end of a round.
     */
    public void clearFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to write the lists of questions to text.
     * 
     * @param questions // The list of questions to be stored.
     */
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

    /**
     * Method used to write leaderboard data to txt for storage and use in future rounds.
     * 
     * @param filePath // Path of the file being written to.
     * @param leaderboardData // Data that is to be written to file.
     */
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

    public void appendToFile(String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendArrayToFile(ArrayList<String> lines) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
        for (String line : lines) {
            writer.write(line);
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
