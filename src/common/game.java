package common;

import java.util.ArrayList;

public class game {

    private static ArrayList<GameCharacter> characters; // Character array.
    public static void main(String[] args){
        characterChoice(1,"Eric");
        characterChoice(2,"Daniel");
        checkAnswer("Does the person have facial hair?",2);
    }
    private static void characterStore(String path) {
        characters = new ArrayList<>();
        TextFileReader characterReader = new TextFileReader(path);
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
    public static void characterChoice(int player, String choice){
        ArrayList<GameCharacter> selectedCharacter = new ArrayList<>();
        characters = new ArrayList<>();
        characterStore("src\\resources\\data.txt");
        for (GameCharacter character : characters) {
            if (character.getName().equals(choice)) {
                selectedCharacter.add(character); // Add the matching character to the new list
            }
        }
        if(player == 1){
            TextFileWriter writer = new TextFileWriter("src\\resources\\p1choice.txt");
            ArrayList<String> characterStrings = new ArrayList<>();

            for (GameCharacter character : selectedCharacter) {
                characterStrings.add(character.toFileString());
            }

            writer.writeFile(characterStrings);
        }
        if(player == 2){
            TextFileWriter writer = new TextFileWriter("src\\resources\\p2choice.txt");
            ArrayList<String> characterStrings = new ArrayList<>();

            for (GameCharacter character : selectedCharacter) {
                characterStrings.add(character.toFileString());
            }

            writer.writeFile(characterStrings);
        }
    
    }

    /**
     * Method to take question, obtain result and pass along to the QuestionHandling class for processing.
     * 
     * @param question // The question asked (String format) Eg: "Is the person a male?"
     * @param player // The player who posed the question
     */
    public static void checkAnswer(String question, int player){
        String path = new String();
        int value = player;

        if(player == 1){
            path = "src\\resources\\p2choice.txt";
        }
        if(player == 2) {
            path = "src\\resources\\p1choice.txt";
        }
        characterStore(path);
        boolean result = QuestionHandler.checkQuestion(question, value);
        String Stresult = String.valueOf(result);
        String Strplayer = String.valueOf(player);
        String[] arrayQuestion = { question, Strplayer, Stresult };
        QuestionHandler.setQuestionAsked(arrayQuestion);
        QuestionHandler.handleQuestions();
        QuestionListHandler.updateQuestionsList(question, player, result);
    }

    // Passthrough methods to Initialization.java methods.
    public static void restartGame(){
        Initialization.resetGame();
    }
    public static void initializeGame(){
        Initialization.initializeGame();
    }
}
