/* HandleScores.java class
 * By Nathan, Aryan, and Victoria
 * Created: January 11th 2024
 */

package common;

import java.util.List;
import java.util.ArrayList;

public class HandleScores {

    private List<List<String>> winsLeaderboard;
    private List<List<String>> averageQuestionsLeaderboard;

    public HandleScores() {
        winsLeaderboard = TextFileReader.readLeaderboard("src\\resources\\games_won_leaderboard");
        averageQuestionsLeaderboard = TextFileReader.readLeaderboard("src\\resources\\average_questions_leaderboard");
    }

    public void processGameResult(String playerName, int questionsAsked, boolean isWin) {
        if (isWin) {
            updateLeaderboards(playerName, questionsAsked);
            TextFileWriter.writeLeaderboard("src\\resources\\games_won_leaderboard", winsLeaderboard);
            TextFileWriter.writeLeaderboard("src\\resources\\average_questions_leaderboard", averageQuestionsLeaderboard);
        }
    }

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
    
}
