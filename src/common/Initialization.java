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

    public static void initializeGame() {
        TextFileReader reader = new TextFileReader(statePath);
        if (!reader.readInitializationState()) {
            characters = new ArrayList<>();
            initializeCharacters(characterPath);
            writeCharactersToFile(p1Path);
            writeCharactersToFile(p2Path);
            initializeQuestions();
            TextFileWriter writer = new TextFileWriter(statePath);
            writer.writeInitializationState(true);
        }
    }


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

    // Filling the character array with all the character objects
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

    // Filling the P1 and P2 character files with the character objects
    public static void writeCharactersToFile(String filePath) {
        TextFileWriter writer = new TextFileWriter(filePath);
        ArrayList<String> characterStrings = new ArrayList<>();

        for (GameCharacter character : characters) {
            characterStrings.add(character.toFileString());
        }

        writer.writeFile(characterStrings);
    }
    public static void resetGame() {
        TextFileWriter writer = new TextFileWriter(p1Path); // Path to player 1's remaining characters file
        writer.clearFile();
        writer = new TextFileWriter(p2Path); // Path to player 2's remaining characters file
        writer.clearFile();
        writer = new TextFileWriter(p2ChoicePath); // Path to player 2's character choice file
        writer.clearFile();
        writer = new TextFileWriter(p1ChoicePath); // Path to player 1's character choice file
        writer.clearFile();
        writer = new TextFileWriter(statePath);  // Path to the state check file
        writer.clearFile();
        writer = new TextFileWriter(p1QuestionsPath);  // Path to player 1's questions file
        writer.clearFile();
        writer = new TextFileWriter(p2QuestionsPath); // Path to player 2's questions file
        writer.clearFile();

    }

}