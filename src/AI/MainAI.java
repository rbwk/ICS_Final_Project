/* MainAI.java class
 * By Nathan, Aryan, and Victoria
 * Last updated: January 11th 2024
 */

package AI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import common.GameCharacter;

public class MainAI {
    private int difficulty;
    private List<String> availableQuestions;
    private List<GameCharacter> remainingCharacters;
    private Random random;

    public MainAI(int difficulty) {
        this.difficulty = difficulty;
        this.availableQuestions = new ArrayList<>(); // Initialize with all possible questions
        this.remainingCharacters = new ArrayList<>(); // Initialize with all characters
        this.random = new Random();
    }

    public void setDifficulty(int level) {
        this.difficulty = level;
    }

    public String selectQuestion() {

            // First, check if AI should make a guess
        String aiGuess = makeGuess();
        if (aiGuess != null) {
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
    

    public void updateGameState(List<GameCharacter> updatedCharacters, List<String> updatedQuestions) {
        this.remainingCharacters = updatedCharacters;
        this.availableQuestions = updatedQuestions;
    }

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
    
    

    private String selectRandomQuestion() {
        if (availableQuestions.isEmpty()) {
            return null;
        }
        int randomIndex = random.nextInt(availableQuestions.size());
        return availableQuestions.get(randomIndex);
    }
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
    
        return bestQuestion;
    }
    
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
                System.out.println(guaranteedEliminationPercentage + " is the guaranteed percentage percentage");

            }
        }
        System.out.println(maxGuaranteedEliminationPercentage + " is the final max percentage");

        return bestQuestion;
    }
    


    private double calculateEliminationPercentage(String question, boolean answer) {
        int count = 0;
        for (GameCharacter character : remainingCharacters) {
            if (questionEliminatesCharacter(question, character) == answer) {
                count++;
            }
        }
        System.out.println(count + " is the amount of people that would be eliminated");
        return count;    
    }
    
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

    // Method to update the list of remaining characters for the AI
    public void updateRemainingCharacters(List<GameCharacter> updatedCharacters) {
        this.remainingCharacters = updatedCharacters;
    }

    private boolean shouldMakeGuess() {
        int totalCharacters = remainingCharacters.size();

        // Define thresholds for guessing based on difficulty
        int hardThreshold = 3;   // Hard difficulty: Guess when 3 or fewer characters remain
        int mediumThreshold = 4; // Medium difficulty: Guess when 5 or fewer characters remain
        int easyThreshold = 4;   // Easy difficulty: Guess randomly when 3 or fewer characters remain
    
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
    
    private double calculateGuessProbability() {
        if (remainingCharacters.size() == 1) {
            return 1.0; // 100% probability
        }
        return 1.0 / remainingCharacters.size();
    }
            
}
