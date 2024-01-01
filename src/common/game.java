package common;

import java.util.ArrayList;

public class game {

    protected static ArrayList<GameCharacter> characterList; // Character array.

    protected static void characterStore() {
        characterList = new ArrayList<>();
        TextFileReader characterReader = new TextFileReader("src\\resources\\data.txt");
        characterReader.readFile();

        for (int i = 0; i < characterReader.getname().size(); i++) {
            GameCharacter character = new GameCharacter();
            character.setName(characterReader.getname().get(i));
            character.setGender(characterReader.getgender().get(i));
            character.setSkinTone(characterReader.getskin_tone().get(i));
            character.setEyeColour(characterReader.geteye_color().get(i));
            character.setHairColour(characterReader.gethair_color().get(i));
            character.setHairLength(characterReader.gethair_length().get(i));
            character.setShowingTeeth(characterReader.getshowing_teeth().get(i));
            character.setWearingHat(characterReader.getwearing_hat().get(i));
            character.setFacialHair(characterReader.getfacial_hair().get(i));
            character.setPiercings(characterReader.getpiercings().get(i));
            character.setGlasses(characterReader.getglasses().get(i));
            characterList.add(character);
        }
    }
    public static void characterChoice(int player, String choice){
        ArrayList<GameCharacter> selectedCharacter = new ArrayList<>();
        characterList = new ArrayList<>();
        characterStore();
        for (GameCharacter character : characterList) {
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

    public static void checkanswer(String question, int player){
        
    }
    

    

}
