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

    public void readFile() { // Very Disgusting way but it works so don't complain
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (filePath == "src\\data.txt") {
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

                } else if (filePath == "src\\questions.txt") {
                    questions.add(line);
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
}