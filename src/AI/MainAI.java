/* MainAI.java class
 * By Nathan, Aryan, and Victoria
 * Last updated: January 11th 2024
 *
 * This class represents the AI player in the game.
 * It handles decision-making for question selection and guessing characters based on the
 * current state of the game and the set difficulty level.
 */


package AI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import common.GameCharacter;
import common.TextFileWriter;

public class MainAI {
    private int difficulty; // Represents the difficulty level of the AI
    private List<String> availableQuestions; // List of questions available for the AI to ask
    private List<GameCharacter> remainingCharacters; // Characters remaining in the game
    private Random random; // Random generator for making random choices
    
    /**
     * Constructor for MainAI.
     *
     * @param difficulty The difficulty level of the AI.
     */
    public MainAI(int difficulty) {
        this.difficulty = difficulty;
        this.availableQuestions = new ArrayList<>(); // Initialize with all possible questions
        this.remainingCharacters = new ArrayList<>(); // Initialize with all characters
        this.random = new Random();

    }

    // Sets the AI's difficulty level (for subsequent rounds should the difficulty change)
    public void setDifficulty(int level) {
        this.difficulty = level;
    }

    /**
     * Selects a question for the AI to ask based on the game's current state and AI's difficulty.
     *
     * @return A question selected by the AI.
     */
    public String selectQuestion() {

        // Check if AI should make a guess
        String aiGuess = makeGuess();
        if (aiGuess != null) {
            TextFileWriter logger = new TextFileWriter("src\\resources\\ai_log"); // logger for final character guess logging
            logger.appendToFile("AI Character Final Choice: " + aiGuess);    
            return aiGuess; // AI makes a guess instead of asking a question
        }
        if (difficulty == 1) { // Hard
            return selectStrategicQuestion();
        } else if (difficulty == 2) { // Medium
            return selectBalancedQuestion();
        } else { // Easy
            return selectRandomQuestion();
        }
    }
    
    /**
     * Updates the game state from the AI's perspective.
     *
     * @param updatedCharacters The updated list of remaining characters.
     * @param updatedQuestions The updated list of available questions.
     */
    public void updateGameState(List<GameCharacter> updatedCharacters, List<String> updatedQuestions) {
        this.remainingCharacters = updatedCharacters;
        this.availableQuestions = updatedQuestions;
    }

    /**
     * Determines if the AI should make a guess and returns the guess if applicable.
     *
     * @return The AI's guess or null if no guess is made.
     */
    public String makeGuess() {
        // Check if the AI should make a guess
        if (shouldMakeGuess()) {
            // If there's only one character left, return that character's name
            if (remainingCharacters.size() == 1) {
                return remainingCharacters.get(0).getName();
            }
    
            // If there are more characters, but AI decides to guess, pick one randomly or based on some logic
            int randomIndex = random.nextInt(remainingCharacters.size());
            return remainingCharacters.get(randomIndex).getName();
        }
        // No guess is made
        return null;
    }
    
    /**
     * Selects a question randomly from the available questions.
     *
     * @return A randomly selected question.
     */
    private String selectRandomQuestion() {
        if (availableQuestions.isEmpty()) {
            return null;
        }
        int randomIndex = random.nextInt(availableQuestions.size());
        String returnQuestion = availableQuestions.get(randomIndex);
        TextFileWriter logger = new TextFileWriter("src\\resources\\ai_log"); // logger for question logging
        logger.appendToFile("AI Question Choice: " + returnQuestion);
        return returnQuestion;
    }

    /**
     * Selects a balanced question that aims to evenly split the remaining characters based on the answer.
     *
     * @return A balanced question based on current game state.
     */
    public String selectBalancedQuestion() {
        int totalCharacters = remainingCharacters.size();
        double minDifferencePercentage = Double.MAX_VALUE;
        String bestQuestion = null;
        for (String question : availableQuestions) {
            double yesEliminationCount = calculateEliminationPercentage(question, true);
            double noEliminationCount = calculateEliminationPercentage(question, false);
    
            double yesEliminationPercentage = (double) yesEliminationCount / totalCharacters;
            double noEliminationPercentage = (double) noEliminationCount / totalCharacters;
    
            // Find the question with the smallest percentage difference between yes and no eliminations
            double balancePercentage = Math.abs(yesEliminationPercentage - noEliminationPercentage);
            if (balancePercentage < minDifferencePercentage) {
                minDifferencePercentage = balancePercentage;
                bestQuestion = question;
            }
        }
        TextFileWriter logger = new TextFileWriter("src\\resources\\ai_log"); // logger for calculation and question logging
        logger.appendToFile("AI Question Choice: " + bestQuestion);
        logger.appendToFile("Ai Percentage Calculation: " + minDifferencePercentage);
        return bestQuestion;
    }
    
    /**
     * Selects a strategic question intended to maximize the elimination of characters regardless of the answer.
     *
     * @return A strategic question aimed at maximizing character elimination.
     */
    public String selectStrategicQuestion() {
        int totalCharacters = remainingCharacters.size();
        double maxGuaranteedEliminationPercentage = 0.0;
        String bestQuestion = null;
    
        for (String question : availableQuestions) {
            double yesEliminationCount = calculateEliminationPercentage(question, true);
            double noEliminationCount = calculateEliminationPercentage(question, false);
    
            double yesEliminationPercentage = (double) yesEliminationCount / totalCharacters;
            double noEliminationPercentage = (double) noEliminationCount / totalCharacters;
    
            // Choose the question that ensures the highest minimum percentage of eliminations, regardless of the answer
            double guaranteedEliminationPercentage = Math.min(yesEliminationPercentage, noEliminationPercentage);
            if (guaranteedEliminationPercentage > maxGuaranteedEliminationPercentage) {
                maxGuaranteedEliminationPercentage = guaranteedEliminationPercentage;
                bestQuestion = question;

            }
        }
        TextFileWriter logger = new TextFileWriter("src\\resources\\ai_log"); // logger for calculation and question logging
        logger.appendToFile("AI Question Choice: " + bestQuestion);
        logger.appendToFile("Ai Percentage Calculation: " + maxGuaranteedEliminationPercentage);
        return bestQuestion;
    }
    
    /**
     * Calculates the percentage of characters that would be eliminated given a specific question and answer.
     *
     * @param question The question to evaluate.
     * @param answer The answer (true/false) to the question.
     * @return The percentage of characters that would be eliminated.
     */
    private double calculateEliminationPercentage(String question, boolean answer) {
        int count = 0;
        for (GameCharacter character : remainingCharacters) {
            if (questionEliminatesCharacter(question, character) == answer) {
                count++;
            }
        }
        return count;    
    }
    
    /**
     * Determines whether a specific question would eliminate a given character from consideration.
     *
     * @param question The question being evaluated.
     * @param character The character to check against the question.
     * @return True if the character would be eliminated, false otherwise.
     */
    private boolean questionEliminatesCharacter(String question, GameCharacter character) {
        switch (question) {
            case "Is the person a male?":
                return !character.getGender().equals("male");
            case "Is the person a female?":
                return !character.getGender().equals("female");
            case "Is the eye colour brown?":
                return !character.getEyeColour().equals("brown");
            case "Is the eye colour green?":
                return !character.getEyeColour().equals("green");
            case "Is the eye colour blue?":
                return !character.getEyeColour().equals("blue");
            case "Is the hair colour black?":
                return !character.getHairColour().equals("black");
            case "Does the person have a light skin tone?":
                return !character.getSkinTone().equals("light");
            case "Does the person have a dark skin tone?":
                return !character.getSkinTone().equals("dark");
            case "Is the hair colour brown?":
                return !character.getHairColour().equals("brown");
            case "Is the hair colour ginger?":
                return !character.getHairColour().equals("ginger");
            case "Is the hair colour white/no hair?":
                return !character.getHairColour().equals("white");
            case "Is the hair colour blonde?":
                return !character.getHairColour().equals("blonde");
            case "Does the person have facial hair?":
                return !character.getFacialHair();
            case "Is the person wearing glasses?":
                return !character.getGlasses();
            case "Is the person showing their teeth?":
                return !character.getShowingTeeth();
            case "Is the person wearing a hat?":
                return !character.getWearingHat();
            case "Does the person have short hair?":
                return !character.getHairLength().equals("short");
            case "Does the person have their hair tied up?":
                return !character.getHairLength().equals("tied");
            case "Does the person have long hair?":
                return !character.getHairLength().equals("long");
            case "Is the person bald?":
                return !character.getHairLength().equals("bald");
            case "Does the person have an ear piercing?":
                return !character.getPiercings();
            default:
                return false; 
        }
    }

    /**
     * Updates the list of remaining characters for the AI based on the current game state.
     *
     * @param updatedCharacters The updated list of remaining characters.
     */
    public void updateRemainingCharacters(List<GameCharacter> updatedCharacters) {
        this.remainingCharacters = updatedCharacters;
    }

    /**
     * Determines if the AI should make a guess based on the current state of the game and the difficulty level.
     *
     * @return True if the AI should make a guess, false otherwise.
     */
    private boolean shouldMakeGuess() {
        int totalCharacters = remainingCharacters.size();

        // Define thresholds for guessing based on difficulty
        int hardThreshold = 1;   // Hard difficulty: Guess when only 1 character remaining
        int mediumThreshold = 2; // Medium difficulty: Guess when 2 or fewer characters remain
        int easyThreshold = 3;   // Easy difficulty: Guess randomly when 3 or fewer characters remain
    
        // Determine the threshold based on the difficulty level
        int threshold = 0;
        switch (difficulty) {
            case 1: // Hard
                threshold = hardThreshold;
                break;
            case 2: // Medium
                threshold = mediumThreshold;
                break;
            case 3: // Basic AI
                threshold = easyThreshold;
                break;
        }
    
        // Make a guess if the number of characters is below the threshold
        if (totalCharacters <= threshold) {
            return true;
        }
    
        // For higher difficulties, consider the probability of a correct guess
        if (difficulty == 1 || difficulty == 2) {
            double probability = calculateGuessProbability();
            double confidenceLevel = difficulty == 1 ? 0.75 : 0.6;
            return probability >= confidenceLevel;
        }
    
        // For Easy and Basic AI, the decision is based solely on the threshold
        return false;
    
    }    
    
    /**
     * Calculates the probability of the AI making a correct guess based on the remaining characters.
     *
     * @return The probability of making a correct guess.
     */
    private double calculateGuessProbability() {
        if (remainingCharacters.size() == 1) {
            return 1.0; // 100% probability
        }
        return 1.0 / remainingCharacters.size();
    }

    public void logCharacterChoice(String logcharacter) {
        TextFileWriter logger = new TextFileWriter("src\\resources\\ai_log"); // Logger for character choice
        logger.appendToFile("AI Character Choice: " + logcharacter);
    }
    
    public void logNewGame(){
        TextFileWriter logger = new TextFileWriter("src\\resources\\ai_log"); // Logger for new round
        logger.appendToFile("\nNew Round: \n ");
    }
            
}
