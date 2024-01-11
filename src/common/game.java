package common;

import java.util.ArrayList;
import java.util.Iterator;

public class game {

    private static ArrayList<GameCharacter> characters; // Character array.
    //private static MainAI aiPlayer; // AI player instance

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
     * Method to take question and result and update the characterlists and questionlists accordingly. 
     * Uses player result input instead of auto check, for use in AI gamemode.
     * 
     * @param question
     * @param player
     * @param result
     */
    public static void checkAnswerPVC(String question, int player, boolean result){
        System.out.println(question);
        String Stresult = String.valueOf(result);
        String Strplayer = String.valueOf(player);
        String[] arrayQuestion = { question, Strplayer, Stresult };
        QuestionHandler.setQuestionAsked(arrayQuestion);
        QuestionHandler.handleQuestions();
        QuestionListHandler.updateQuestionsList(question, player, result);
    }

    /**
     * Method to take question, obtain result and pass along to the QuestionHandling class for processing.
     * In player vs AI, still using this method if the player asks a question since logic has AI's chosen character.
     * Computers the answer and passes it back to the human player.
     * 
     * @param question // The question asked (String format) Eg: "Is the person a male?"
     * @param player // The player who posed the question
     */
    public static boolean checkAnswer(String question, int player){
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
        return result;
    }

    // Passthrough methods to Initialization.java methods.
    public static void restartGame(){
        Initialization.resetGame();
    }
    public static void initializeGame(){
        Initialization.initializeGame();
    }

    // Call method in gui code if game is being initialized against AI player, and pass difficulty chosen.
    public void startAI(int difficulty) {
        // Initialize the AI if playing against AI
    //    aiPlayer = new MainAI(difficulty); 

    }

    /**
     * Method to handle enemy players character guess, thus determining endgame and who wins.
     * 
     * @param guess
     * @param asked
     * @return // Returns True/False value
     */
    public static boolean endGame(String guess, int asked){
        String path = new String();

        if(asked == 1){
            path = "src\\resources\\p2choice.txt";
        }
        if(asked == 2) {
            path = "src\\resources\\p1choice.txt";
        }
        characterStore(path);
        Iterator<GameCharacter> iterator = characters.iterator();
        GameCharacter character = iterator.next();
        if (character.getName().equals(guess)) {
            if(asked == 2) {
                updateAICharacters();
            }
            return true;
        } else {     

            return false;

        }

    }

    public static void updateAICharacters() {
        String aiCharactersFilePath = "src\\resources\\p2_characters_remaining.txt"; // AI characters file
        TextFileReader characterReader = new TextFileReader(aiCharactersFilePath);
        characterReader.readFile();
        ArrayList<GameCharacter> aiRemainingCharacters = new ArrayList<>();

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
            aiRemainingCharacters.add(character);
        }

       // aiPlayer.updateRemainingCharacters(aiRemainingCharacters);
    }

    // So the instance of aiPlayer can be used elsewhere.
   // public static MainAI getAiPlayer() {
     //   return aiPlayer;
  //  }

}
