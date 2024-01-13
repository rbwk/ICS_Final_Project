/* Initialization.java class
 * By Nathan, Aryan, and Victoria
 * Last updated: January 6th 2024
 *
 * This class is responsible for initializing and resetting the game state.
 * It handles operations like setting up character data, question lists, and clearing temporary files.
 */

package common;

import java.util.ArrayList;

public class Initialization {
    protected static ArrayList<GameCharacter> characters; // Character array.
    private static final String statePath = "src\\resources\\initialization_state.txt"; // Path for initialization state storage.
    private static final String characterPath = "src\\resources\\data.txt"; // Path to the base character attribute txt file.
    private static final String p1Path = "src\\resources\\p1_characters_remaining.txt"; // Path to player 1 character storage file.
    private static final String p2Path = "src\\resources\\p2_characters_remaining.txt"; // Path to player 2 character storage file
    private static final String p2ChoicePath = "src\\resources\\p2choice.txt"; // Path to player 2 character storage file
    private static final String p1ChoicePath = "src\\resources\\p1choice.txt"; // Path to player 2 character storage file
    private static final String p1QuestionsPath = "src\\resources\\p1_questions_remaining.txt"; // Path to player 1 character storage file.
    private static final String p2QuestionsPath = "src\\resources\\p2_questions_remaining.txt"; // Path to player 1 character storage file.
    private static final String p1EliminatedPath = "src\\resources\\p1_characters_removed.txt"; // Path to player 1 character storage file.
    private static final String p2EliminatedPath = "src\\resources\\p2_characters_removed.txt"; // Path to player 1 character storage file.

    /**
     * Initializes the game by setting up characters and questions if not already done.
     * Writes the initial game state to files.
     */
    public static void initializeGame() {
        characters = new ArrayList<>();
        initializeCharacters(characterPath);
        writeCharactersToFile(p1Path);
        writeCharactersToFile(p2Path);
        initializeQuestions();
        TextFileWriter writer = new TextFileWriter(statePath);
        writer.writeInitializationState(true);
    }

    /**
     * Initializes the list of questions for both players.
     * Reads the questions from a file and writes them into player-specific question files.
     */
    private static void initializeQuestions() {
        // Initialize TextFileReader for questions
        TextFileReader questionReader = new TextFileReader("src\\resources\\questions.txt");
        questionReader.readFile();
        ArrayList<String> questions = questionReader.getQuestions();

        // Write questions to player 1 and player 2 files
        TextFileWriter writerP1 = new TextFileWriter(p1QuestionsPath);
        writerP1.writeQuestions(questions);

        TextFileWriter writerP2 = new TextFileWriter(p2QuestionsPath);
        writerP2.writeQuestions(questions);
    }

    /**
     * Initializes the characters from a file and stores them in the 'characters' list.
     * 
     * @param filePath Path to the file containing character data.
     */
    private static void initializeCharacters(String filePath) {
        TextFileReader characterReader = new TextFileReader(filePath);
        characterReader.readFile();

        for (int i = 0; i < characterReader.getname().size(); i++) {
            GameCharacter character = new GameCharacter();
            character.setName(characterReader.getname().get(i));
            character.setGender(characterReader.getgender().get(i));
            character.setEyeColour(characterReader.geteye_color().get(i));
            character.setSkinTone(characterReader.getskin_tone().get(i));
            character.setHairColour(characterReader.gethair_color().get(i));
            character.setFacialHair(characterReader.getfacial_hair().get(i));
            character.setGlasses(characterReader.getglasses().get(i));
            character.setShowingTeeth(characterReader.getshowing_teeth().get(i));
            character.setWearingHat(characterReader.getwearing_hat().get(i));
            character.setHairLength(characterReader.gethair_length().get(i));
            character.setPiercings(characterReader.getpiercings().get(i));
            characters.add(character);
        }
    }

    /**
     * Writes the list of characters to a file. Each character is converted to a string format suitable for file storage.
     * 
     * @param filePath Path to the file where character data will be written.
     */
    public static void writeCharactersToFile(String filePath) {
        TextFileWriter writer = new TextFileWriter(filePath);
        ArrayList<String> characterStrings = new ArrayList<>();

        for (GameCharacter character : characters) {
            characterStrings.add(character.toFileString());
        }

        writer.writeFile(characterStrings);
    }

    /**
     * Resets the game state by clearing all temporary files.
     * This method is called at the end of a game round to prepare for a new game.
     */
    public static void resetGame() {
        TextFileWriter writer = new TextFileWriter(p1Path); // Path to player 1's remaining characters file
        writer.clearFile();
        writer = new TextFileWriter(p2Path); // Path to player 2's remaining characters file
        writer.clearFile();
        writer = new TextFileWriter(p2ChoicePath); // Path to player 2's character choice file
        writer.clearFile();
        writer = new TextFileWriter(p1ChoicePath); // Path to player 1's character choice file
        writer.clearFile();
        writer = new TextFileWriter(p1QuestionsPath);  // Path to player 1's questions file
        writer.clearFile();
        writer = new TextFileWriter(p2QuestionsPath); // Path to player 2's questions file
        writer.clearFile();
        writer = new TextFileWriter(p1EliminatedPath);  // Path to player 1's eliminated file
        writer.clearFile();
        writer = new TextFileWriter(p2EliminatedPath); // Path to player 2's eliminated file
        writer.clearFile();


    }

}