package common;

public class game {

	public static void main(String[] args) {
		TextFileReader TFR = new TextFileReader("data.txt");
		TFR.readFile();
		System.out.println(TFR.getname());

	}

}
