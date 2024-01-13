/* HandleScores.java class
 * By Nathan, Aryan, and Victoria
 * Created: January 11th 2024
 *
 * This class is responsible for handling the game's scoring system, including maintaining
 * and updating the leaderboards for games won and average questions asked per game.
 * It utilizes two-dimensional lists (List<List<String>>) for flexible data storage, allowing
 * theoretically unlimited entries in the leaderboards.
 */
package common;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class HandleScores {

    private List<List<String>> winsLeaderboard; // List that stores the names and number of won rounds of players
    private List<List<String>> averageQuestionsLeaderboard; // List that stores names and avg question count for win of players.

    /**
     * Constructor to initialize the leaderboards by reading existing leaderboard data from files.
     */
    public HandleScores() {
        winsLeaderboard = TextFileReader.readLeaderboard("src\\resources\\games_won_leaderboard");
        averageQuestionsLeaderboard = TextFileReader.readLeaderboard("src\\resources\\average_questions_leaderboard");
    }

    /**
     * Method called by game code to process the results of a game for leaderboard storage.
     * 
     * @param playerName // Name of the player who won.
     * @param questionsAsked // Number of questions that player asked to achieve win.
     * @param isWin // Flag value.
     */
    public void processGameResult(String playerName, int questionsAsked, boolean isWin) {
        if (isWin) {
            updateLeaderboards(playerName, questionsAsked);
            sortWinsLeaderboard();
            sortAverageQuestionsLeaderboard();
            TextFileWriter.writeLeaderboard("src\\resources\\games_won_leaderboard", winsLeaderboard);
            TextFileWriter.writeLeaderboard("src\\resources\\average_questions_leaderboard", averageQuestionsLeaderboard);
        }
    }

    /**
     * Method to update the leaderboards.
     * 
     * @param playerName // Name of player who won.
     * @param questionsAsked // Number of questions player asked in the round they won.
     */
    private void updateLeaderboards(String playerName, int questionsAsked) {
        boolean playerExists = false;
        for (List<String> player : winsLeaderboard) {
            if (player.get(0).equals(playerName)) {
                playerExists = true;
                int wins = Integer.parseInt(player.get(1));
                player.set(1, String.valueOf(wins + 1));
                break;
            }
        
        }
        
        if (!playerExists) {
            List<String> winEntry = new ArrayList<>();
            winEntry.add(playerName);
            winEntry.add("1"); 
            winsLeaderboard.add(winEntry);

            List<String> questionEntry = new ArrayList<>();
            questionEntry.add(playerName);
            questionEntry.add(String.valueOf(questionsAsked)); 
            averageQuestionsLeaderboard.add(questionEntry);
        } else {
            for (List<String> player : averageQuestionsLeaderboard) {
                if (player.get(0).equals(playerName)) {
                    double currentAverage = Double.parseDouble(player.get(1));
                    int wins = Integer.parseInt(getWinCount(playerName));
                    double newAverage = ((currentAverage * (wins - 1)) + questionsAsked) / wins;
                    player.set(1, String.format("%.2f", newAverage));
                    break;
                }
            }
        }
    }

    // Method used to return the number of wins a player had before. Returns 0 if name doesn't exist or they haven't won anything.
    private String getWinCount(String playerName) {
        for (List<String> player : winsLeaderboard) {
            if (player.get(0).equals(playerName)) {
                return player.get(1);
            }
        }
        return "0";
    }

    // Test methods left in for now, can be adapted to do the leaderboard writing in gui potentially.
    public void printLeaderboards() {
        System.out.println("Wins Leaderboard:");
        for (List<String> player : winsLeaderboard) {
            System.out.println(player.get(0) + ": " + player.get(1) + " wins");
        }
    
        System.out.println("\nAverage Questions Leaderboard:");
        for (List<String> player : averageQuestionsLeaderboard) {
            System.out.println(player.get(0) + ": " + player.get(1) + " average questions");
        }
    }

    public void sortWinsLeaderboard() {
        Collections.sort(winsLeaderboard, (a, b) -> Integer.parseInt(b.get(1)) - Integer.parseInt(a.get(1)));
    }

    public void sortAverageQuestionsLeaderboard() {
        Collections.sort(averageQuestionsLeaderboard, (a, b) -> {
            double avgA = Double.parseDouble(a.get(1));
            double avgB = Double.parseDouble(b.get(1));
            return Double.compare(avgA, avgB);
        });
    }
    
}
