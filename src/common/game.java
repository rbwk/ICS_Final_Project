package common;

import java.util.ArrayList;

public class game {
	protected static int diff; // Difficulty level
    protected static ArrayList<String> questions; // List of questions
    protected static ArrayList<GameCharacter> characters; // List of characters
    protected static ArrayList<GameCharacter> p1_characters; // Player 1 list of remaining characters
    protected static ArrayList<GameCharacter> p2_characters; // Player 2 list of remaining characters
	
    public static void main(String[] args) {

        // Initialize TextFileReader for characters
        TextFileReader characterReader = new TextFileReader("data.txt");
        characterReader.readFile();

        // Create GameCharacter objects and add them to the characters list
        for (int i = 0; i < characterReader.getname().size(); i++) {
            GameCharacter character = new GameCharacter();
            character.setName(characterReader.getname().get(i));
            character.setGender(characterReader.getgender().get(i));
            character.setSkinTone(characterReader.getskin_tone().get(i));
            character.setEyeColour(characterReader.geteye_color().get(i));
            character.setHairColour(characterReader.gethair_color().get(i));
            character.setHairLength(characterReader.gethair_length().get(i));
            character.setShowingTeeth(characterReader.getshowing_teeth().get(i));
            character.setWearingHat(characterReader.getwearing_hat().get(i));
            character.setFacialHair(characterReader.getfacial_hair().get(i));
            character.setPiercings(characterReader.getpiercings().get(i));
            character.setGlasses(characterReader.getglasses().get(i));
            characters.add(character);
        }

        // Initialize TextFileReader for questions
        TextFileReader questionReader = new TextFileReader("questions.txt");
        questionReader.readFile();
        questions = questionReader.getQuestions();
    }

}
