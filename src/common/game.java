package common;

import java.util.ArrayList;
import common.TextFileReader; // Import TextFileReaders
import javax.xml.stream.events.Characters;

public class game {
	protected static int diff; // Difficulty level
    protected static ArrayList<String> questions; // List of questions
    protected static ArrayList<Characters> characters; // List of characters
	protected static ArrayList<Characters> p1_characters; // Player 1 list of remaining characters
    protected static ArrayList<Characters> p2_characters; // Player 2 list of remaining characters
	
    public static void main(String[] args) {
        characters = new ArrayList<>();
        questions = new ArrayList<>();

        // Initialize TextFileReader for characters
        TextFileReader characterReader = new TextFileReader("data.txt");
        characterReader.readFile();

        // Create Characters objects and add them to the characters list
        for (int i = 0; i < characterReader.getname().size(); i++) {
            Characters character = new Characters(
                characterReader.getname().get(i),
                characterReader.getgender().get(i),
                characterReader.getskin_tone().get(i),
                characterReader.geteye_color().get(i),
                characterReader.gethair_color().get(i),
                characterReader.gethair_length().get(i),
                characterReader.getshowing_teeth().get(i),
                characterReader.getwearing_hat().get(i),
                characterReader.getfacial_hair().get(i),
                characterReader.getpiercings().get(i),
                characterReader.getglasses().get(i)
            );
            characters.add(character);
        }

        // Initialize TextFileReader for questions
        TextFileReader questionReader = new TextFileReader("questions.txt");
        questionReader.readFile();
        questions = questionReader.getQuestions();

    }

}
