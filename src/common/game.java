package common;

import java.util.ArrayList;

public class game {
	protected static int diff; // Difficulty level
    protected static ArrayList<String> questions; // List of questions
    protected static ArrayList<GameCharacter> characters; // List of characters
    protected static ArrayList<GameCharacter> p1_characters; // Player 1 list of remaining characters
    protected static ArrayList<GameCharacter> p2_characters; // Player 2 list of remaining characters
    protected static String questionAsked; // The question passed to this program

    public static void setQuestionAsked(String question) {
        questionAsked = question;
    }

    public static String getQuestionAsked() {
        return questionAsked;
    }

    public static void main(String[] args) {
        characters = new ArrayList<>();
        questions = new ArrayList<>();
        // Initialize TextFileReader for characters
        TextFileReader characterReader = new TextFileReader("src\\resources\\data.txt");
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
        TextFileReader questionReader = new TextFileReader("src\\resources\\questions.txt");
        questionReader.readFile();
        questions = questionReader.getQuestions();

        // Testing Object Creation
        for (int i = 0; i < characters.size(); i++) {
            System.out.println(characters.get(i).getName());
        }       
        for (int i = 0; i < questions.size(); i++) {
            System.out.println(questions.get(i));
        }        
 
            /**
             * Group of if statements responsible for question handling.
             * Written off of the assumption that question asked by user will be given to this program as a string.
             * "Is the person a male?"   - Like so
             * This program will not remove questions from the list of options available to it, 
             * instead it will assume the class that called this class will already handle this functionality. 
             * Keep in mind, if you wish to add this functionality into this class instead
             * the option to is present and should be very simple, for simplicity sake in writing the logic, and because
             * the GUI programs and other game logic programs will probably need to filter the list of questions remaining, 
             * that was not implemented here.
             * 
            */
        
     
    }
}
