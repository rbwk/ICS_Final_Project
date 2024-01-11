package AI;
import java.util.ArrayList;
import java.util.Arrays;
import common.GameCharacter;
import common.TextFileReader;

public class test {

    private static ArrayList<GameCharacter> chrs; 

    public static void main(String[] args) {
        chrs = new ArrayList<>();
        TextFileReader characterReader = new TextFileReader("src\\resources\\data.txt");
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
            chrs.add(character);
        }

        ArrayList<String> questions = new ArrayList<>(Arrays.asList(
            "Is the person a male?",
            "Is the person a female?",
            "Is the eye colour brown?",
            "Is the eye colour green?",
            "Is the eye colour blue?",
            "Does the person have a light skin tone?",
            "Does the person have a dark skin tone?",
            "Is the hair colour black?",
            "Is the hair colour brown?",
            "Is the hair colour ginger?",
            "Is the hair colour blonde?",
            "Is the hair colour white/no hair?",
            "Does the person have facial hair?",
            "Is the person wearing glasses?",
            "Is the person showing their teeth?",
            "Is the person wearing a hat?",
            "Does the person have short hair?",
            "Does the person have their hair tied up?",
            "Does the person have long hair?",
            "Is the person bald?",
            "Does the person have an ear piercing?"
        ));


        // Initialize the AI with a difficulty level (1 for hard, 2 for medium, 3 for easy)
        MainAI ai = new MainAI(1); // Using medium difficulty for this test

        // Update AI's game state with chrs and questions
        ai.updateGameState(chrs, questions);

        // Let the AI select a question
        String selectedQuestion = ai.selectQuestion();

        // Output the selected question
        System.out.println("Selected Question: " + selectedQuestion);

    }
}
