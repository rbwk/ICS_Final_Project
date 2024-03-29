/* TextFileReader.java class
 * By Nathan, Aryan, and Victoria
 * Last updated: January 12th 2024
 *
 * This class is responsible for reading various text files used in the game.
 * It can read character attribute files, question files, and leaderboard data.
 */

package common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextFileReader {
    protected String filePath;

    // Lists to store character attributes
    protected ArrayList<String> name;
    protected ArrayList<String> gender;
    protected ArrayList<String> skin_tone;
    protected ArrayList<String> eye_color;
    protected ArrayList<String> hair_color;
    protected ArrayList<String> hair_length;
    // Lists to store boolean character attributes
    protected List<Boolean> showing_teeth = new ArrayList<Boolean>(Arrays.asList(true));
    protected List<Boolean> wearing_hat = new ArrayList<Boolean>(Arrays.asList(true));
    protected List<Boolean> facial_hair = new ArrayList<Boolean>(Arrays.asList(true));
    protected List<Boolean> piercings = new ArrayList<Boolean>(Arrays.asList(true));
    protected List<Boolean> glasses = new ArrayList<Boolean>(Arrays.asList(true));
    // List to store questions
    protected ArrayList<String> questions;
    
    /**
     * Constructor to initialize the TextFileReader with a file path.
     * 
     * @param filePath The path of the file to be read.
     */
    public TextFileReader(String filePath) {
        this.filePath = filePath;

        this.name = new ArrayList<>();
        this.gender = new ArrayList<>();
        this.skin_tone = new ArrayList<>();
        this.eye_color = new ArrayList<>();
        this.hair_color = new ArrayList<>();
        this.hair_length = new ArrayList<>();

        this.showing_teeth = new ArrayList<>();
        this.wearing_hat = new ArrayList<>();
        this.facial_hair = new ArrayList<>();
        this.piercings = new ArrayList<>();
        this.glasses = new ArrayList<>();

        this.questions = new ArrayList<>();
    }

    /**
     * Reads the file specified by the filePath.
     * Depending on the file, it populates different lists with character attributes or questions.
     */

    public void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (filePath.equals("src\\resources\\questions.txt") || 
                    filePath.equals("src\\resources\\p2_questions_remaining.txt") || 
                    filePath.equals("src\\resources\\p1_questions_remaining.txt")) {
                    questions.add(line);
                } else {
                    name.add(line);
                    line = reader.readLine();
                    gender.add(line);
                    line = reader.readLine();
                    eye_color.add(line);
                    line = reader.readLine();
                    skin_tone.add(line);
                    line = reader.readLine();
                    hair_color.add(line);
                    line = reader.readLine();
                    facial_hair.add(Boolean.parseBoolean(line));
                    line = reader.readLine();
                    glasses.add(Boolean.parseBoolean(line));
                    line = reader.readLine();
                    showing_teeth.add(Boolean.parseBoolean(line));
                    line = reader.readLine();
                    wearing_hat.add(Boolean.parseBoolean(line));
                    line = reader.readLine();
                    hair_length.add(line);
                    line = reader.readLine();
                    piercings.add(Boolean.parseBoolean(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get methods so the objects can be written to arraylist.
    public ArrayList<String> getname() {
        return name;
    }

    public ArrayList<String> getgender() {
        return gender;
    }

    public ArrayList<String> getskin_tone() {
        return skin_tone;
    }

    public ArrayList<String> geteye_color() {
        return eye_color;
    }

    public ArrayList<String> gethair_color() {
        return hair_color;
    }

    public ArrayList<String> gethair_length() {
        return hair_length;
    }

    public List<Boolean> getshowing_teeth() {
        return showing_teeth;
    }

    public List<Boolean> getwearing_hat() {
        return wearing_hat;
    }

    public List<Boolean> getfacial_hair() {
        return facial_hair;
    }

    public List<Boolean> getpiercings() {
        return piercings;
    }

    public List<Boolean> getglasses() {
        return glasses;
    }

    public ArrayList<String> getQuestions() {
        return questions;
    }

    // Method used by initialization.java to lock initialization until the next round (extra error protection)
    public boolean readInitializationState() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            return line != null && Boolean.parseBoolean(line);
        } catch (IOException e) {
            return false; // Default to false if file was empty
        }
    }

    // Method to read leaderboard data. Reads both leaderboards to listoflists for storage.
    // Uses list lists, which allow for what are essentially 2d arraylists.
    public static List<List<String>> readLeaderboard(String filePath) {
        List<List<String>> leaderboardData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); 
                List<String> rowData = new ArrayList<>();
                for (String part : parts) {
                    rowData.add(part.trim());
                }
                leaderboardData.add(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return leaderboardData;
    }

}