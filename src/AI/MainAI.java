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
        if (remainingCharacters.size() == 1 || shouldMakeGuess()) {
            return remainingCharacters.get(0).getName();
        }
        return null;
    }
    
    private boolean shouldMakeGuess() {
        return false; // temp
    }

    private String selectRandomQuestion() {
        if (availableQuestions.isEmpty()) {
            return null;
        }
        int randomIndex = random.nextInt(availableQuestions.size());
        return availableQuestions.get(randomIndex);
    }
    private String selectBalancedQuestion() {
        String bestQuestion = null;
        double bestDifference = Double.MAX_VALUE;
    
        for (String question : availableQuestions) {
            double elimPercentage = calculateEliminationPercentage(question);
            double difference = Math.abs(0.5 - elimPercentage);
    
            if (difference < bestDifference) {
                bestDifference = difference;
                bestQuestion = question;
            }
        }
    
        return bestQuestion;
    }

    private String selectStrategicQuestion() {
        String bestQuestion = null;
        double bestElimination = 0.0;
    
        for (String question : availableQuestions) {
            double elimPercentage = calculateEliminationPercentage(question);
            if (elimPercentage > bestElimination) {
                bestElimination = elimPercentage;
                bestQuestion = question;
            }
        }
    
        return bestQuestion;
    }

    private double calculateEliminationPercentage(String question) {
        int totalCharacters = remainingCharacters.size();
        int eliminatedCharacters = 0;
    
        for (GameCharacter character : remainingCharacters) {
            if (questionEliminatesCharacter(question, character)) {
                eliminatedCharacters++;
            }
        }
    
        return totalCharacters > 0 ? (double) eliminatedCharacters / totalCharacters : 0;
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
}
