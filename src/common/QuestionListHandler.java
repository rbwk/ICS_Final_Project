/* QuestionListHandler.java class
 * By Nathan, Aryan, and Victoria
 * Last updated: January 4th 2024
 *
 * This class is responsible for handling the list of questions available to players in the game.
 * It updates the list based on the questions asked and their outcomes, ensuring that the game
 * reflects the current state of knowledge about the characters.
 */

package common;

import java.util.ArrayList;
import java.util.List;

public class QuestionListHandler {

    private static final String p1QuestionsPath = "src\\resources\\p1_questions_remaining.txt"; // Path to p1 question list
    private static final String p2QuestionsPath = "src\\resources\\p2_questions_remaining.txt"; // Path to p2 question list

    /**
    * Updates the list of remaining questions for a player based on the question asked.
    * It removes the asked question and any related questions based on the answer's outcome.
    *
    * @param questionAsked The question asked.
    * @param player The player who asked the question.
    * @param result The result of the question (true or false).
    */
    public static void updateQuestionsList(String questionAsked, int player, boolean result) {
        String questionsPath = (player == 1) ? p1QuestionsPath : p2QuestionsPath;
        TextFileReader questionReader = new TextFileReader(questionsPath);
        questionReader.readFile();
        ArrayList<String> questions = questionReader.getQuestions();

        // Remove the asked question
        questions.remove(questionAsked);

        // Remove related questions
        ArrayList<String> relatedQuestions = getRelatedQuestions(questionAsked, result);
        questions.removeAll(relatedQuestions);

        // Write the updated list back to the file
        TextFileWriter questionWriter = new TextFileWriter(questionsPath);
        questionWriter.writeQuestions(questions);
    }

    /**
     * Determines and returns a list of questions related to the question asked.
     * These related questions are removed from the player's list to reflect the new game state.
     *
     * @param question The question that was asked.
     * @param result The result of the question (true or false).
     * @return A list of related questions to be removed.
     */
    private static ArrayList<String> getRelatedQuestions(String question, boolean result) {
        ArrayList<String> relatedQuestions = new ArrayList<>();
        switch (question) {
            case "Is the person a male?":
                if (result) {
                    relatedQuestions.add("Is the person a female?");
                }
                else{
                    relatedQuestions.add("Is the person a female?");
                }
                break;
            case "Is the person a female?":
                if (result) {
                    relatedQuestions.add("Is the person a male?");
                }
                else{
                    relatedQuestions.add("Is the person a male?");
                }
                break;
            case "Is the eye colour brown?":
                if (result) {
                    relatedQuestions.addAll(List.of("Is the eye colour green?", "Is the eye colour blue?"));
                }
                break;
            case "Is the eye colour green?":
                if (result) {
                    relatedQuestions.addAll(List.of("Is the eye colour brown?", "Is the eye colour blue?"));
                }
                break;
            case "Is the eye colour blue?":
                if (result) {
                    relatedQuestions.addAll(List.of("Is the eye colour brown?", "Is the eye colour green?"));
                }
                break;
            case "Does the person have a light skin tone?":
                if (result) {
                    relatedQuestions.add("Does the person have a dark skin tone?");
                }
                else{
                    relatedQuestions.add("Does the person have a dark skin tone?");
                }
                break;
            case "Does the person have a dark skin tone?":
                if (result) {
                    relatedQuestions.add("Does the person have a light skin tone?");
                }
                else{
                    relatedQuestions.add("Does the person have a light skin tone?");
                }
                break;
            case "Is the hair colour black?":
                if (result) {
                    relatedQuestions.addAll(List.of("Is the hair colour brown?", "Is the hair colour ginger?", "Is the hair colour blonde?", "Is the hair colour white/no hair?"));
                }
                break;
            case "Is the hair colour brown?":
                if (result) {
                    relatedQuestions.addAll(List.of("Is the hair colour black?", "Is the hair colour ginger?", "Is the hair colour blonde?", "Is the hair colour white/no hair?"));
                }
                break;
            case "Is the hair colour ginger?":
                if (result) {
                    relatedQuestions.addAll(List.of("Is the hair colour black?", "Is the hair colour brown?", "Is the hair colour blonde?", "Is the hair colour white/no hair?"));
                }
                break;
            case "Is the hair colour blonde?":
                if (result) {
                    relatedQuestions.addAll(List.of("Is the hair colour black?", "Is the hair colour brown?", "Is the hair colour ginger?", "Is the hair colour white/no hair?"));
                }
                break;
            case "Is the hair colour white/no hair?":
                if (result) {
                    relatedQuestions.addAll(List.of("Is the hair colour black?", "Is the hair colour brown?", "Is the hair colour ginger?", "Is the hair colour blonde?"));
                }
                break;
                case "Does the person have short hair?":
                if (result) {
                    relatedQuestions.addAll(List.of("Does the person have their hair tied up?", "Does the person have long hair?", "Is the person bald?"));
                }
                break;
            case "Does the person have their hair tied up?":
                if (result) {
                    relatedQuestions.addAll(List.of("Does the person have short hair?", "Does the person have long hair?", "Is the person bald?"));
                }
                break;
            case "Does the person have long hair?":
                if (result) {
                    relatedQuestions.addAll(List.of("Does the person have short hair?", "Does the person have their hair tied up?", "Is the person bald?"));
                }
                break;
            case "Is the person bald?":
                if (result) {
                    relatedQuestions.addAll(List.of("Does the person have short hair?", "Does the person have their hair tied up?", "Does the person have long hair?"));
                }
                break;
            case "Does the person have facial hair?":
                break;
            case "Is the person wearing glasses?":
                break;
            case "Is the person showing their teeth?":
                break;
            case "Is the person wearing a hat?":
                break;
            case "Does the person have an ear piercing?":
                break;
        }
        return relatedQuestions;
    } 
}
