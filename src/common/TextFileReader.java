/*TextFileReader.java class
 * By Nathan, Aryan, and Victoria
 * Last updated: January 11th 2024
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

    protected ArrayList<String> name;
    protected ArrayList<String> gender;
    protected ArrayList<String> skin_tone;
    protected ArrayList<String> eye_color;
    protected ArrayList<String> hair_color;
    protected ArrayList<String> hair_length;

    protected List<Boolean> showing_teeth = new ArrayList<Boolean>(Arrays.asList(true));
    protected List<Boolean> wearing_hat = new ArrayList<Boolean>(Arrays.asList(true));
    protected List<Boolean> facial_hair = new ArrayList<Boolean>(Arrays.asList(true));
    protected List<Boolean> piercings = new ArrayList<Boolean>(Arrays.asList(true));
    protected List<Boolean> glasses = new ArrayList<Boolean>(Arrays.asList(true));

    protected ArrayList<String> questions;

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

    private void processCharacterData(BufferedReader reader) throws IOException {
        name.add(reader.readLine());
        gender.add(reader.readLine());
        eye_color.add(reader.readLine());
        skin_tone.add(reader.readLine());
        hair_color.add(reader.readLine());
        facial_hair.add(Boolean.parseBoolean(reader.readLine()));
        glasses.add(Boolean.parseBoolean(reader.readLine()));
        showing_teeth.add(Boolean.parseBoolean(reader.readLine()));
        wearing_hat.add(Boolean.parseBoolean(reader.readLine()));
        hair_length.add(reader.readLine());
        piercings.add(Boolean.parseBoolean(reader.readLine()));
    }

    public void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                switch (filePath) {
                    case "src\\resources\\data.txt":
                    case "src\\resources\\p1_characters_remaining.txt":
                    case "src\\resources\\p2_characters_remaining.txt":
                    case "src\\resources\\p2choice.txt":
                    case "src\\resources\\p1choice.txt":
                    case "src\\resources\\p2_characters_removed.txt":
                    case "src\\resources\\p1_characters_removed.txt":
                        processCharacterData(reader);
                        break;
                    case "src\\resources\\questions.txt":
                    case "src\\resources\\p2_questions_remaining.txt":
                    case "src\\resources\\p1_questions_remaining.txt":
                        questions.add(line);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


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
    public boolean readInitializationState() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            return line != null && Boolean.parseBoolean(line);
        } catch (IOException e) {
            return false; // Default to false if file was empty
        }
    }
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