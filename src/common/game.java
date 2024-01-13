/* game.java class
 * By Nathan, Aryan, and Victoria
 * Last updated: January 11th 2024
 *
 * This class encapsulates all game logic for ease of access and organization.
 * It manages character selections, question handling, score updating, and game state initialization.
 */


package common;

import java.util.ArrayList;
import java.util.Iterator;

public class game {

    private static HandleScores handleScores = new HandleScores(); // Assuming one instance for the entire game
    private static ArrayList<GameCharacter> characters; // Character array.

    public static void main(String[] args){
        characterChoice(1,"Eric");
        characterChoice(2,"Daniel");
        checkAnswer("Does the person have facial hair?",2);                            
    }
    
    /**
     * Loads characters from a specified file into the characters list.
     *
     * @param path File path of the character data.
     */
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

    /**
     * Handles character selection for a player.
     *
     * @param player Player number (1 or 2).
     * @param choice Name of the character chosen by the player.
     */
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
     * Processes a question and its result, updating character lists and question lists accordingly.
     * Used in player vs. computer mode.
     *
     * @param question The question asked.
     * @param player The player asking the question.
     * @param result The result of the question (true or false).
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
     * Processes a question in player vs. player or player vs. AI mode.
     * Computes the answer and updates character lists and question lists.
     *
     * @param question The question asked.
     * @param player The player who posed the question.
     * @return The result of the question (true or false).
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

    /**
     * Updates the game's leaderboards at the end of a round.
     *
     * @param winnerName Name of the winning player.
     * @param questionsAsked Number of questions asked by the winning player.
     * @param isWin Flag indicating a win (always true when this method is called).
     */
    public static void updateScores(String winnerName, int questionsAsked, boolean isWin) {
        // Process game result for score updating
        handleScores.processGameResult(winnerName, questionsAsked, isWin);
    }

    /**
     * Resets the game state and clears temporary files.
     */
    public static void restartGame(){
        Initialization.resetGame();
    }
    /**
     * Initializes the game state.
     */
    public static void initializeGame(){
        Initialization.initializeGame();
    }



    /**
     * Handles the endgame scenario by checking a player's guess against the opponent's character.
     *
     * @param guess The guessed character name.
     * @param asked The player making the guess.
     * @return True if the guess is correct, false otherwise.
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
            return true;
        } else {     
            return false;
        }
    }
}
