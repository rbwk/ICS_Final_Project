package common;

import java.util.ArrayList;

public class game {

	//Variables [P1 = Player 1, P2 = Player 2]
	protected static int diff;		//Difficulty
	protected static int p1_num;	//Player 1 Amount of Characters Left
	protected static int p2_num;	//Player 2 Amount of Characters Left
	protected static ArrayList<String> Questions;


	public static void main(String[] args) {
		TextFileReader C = new TextFileReader("data.txt"); //Characters
		C.readFile();
		System.out.println(C.getname());
		
		TextFileReader Q = new TextFileReader("questions.txt"); //Questions
		Q.readFile();
		System.out.println(Q.getQuestions());
	}

}
