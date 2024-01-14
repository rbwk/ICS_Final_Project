/* QuestionHandler.java class
 * By Nathan, Aryan, and Victoria
 * Last updated: January 4th 2024
 *
 * This class handles the processing of questions asked in the game.
 * It manages character data, filtering characters based on the answers to questions,
 * and updates the corresponding files with the new state of the game.
 */

package common;

import java.util.ArrayList;
import java.util.Iterator;

public class QuestionHandler {
    private static ArrayList<GameCharacter> characters; // Players list of remaining characters.
    protected static String[] questionAsked = new String[3]; // The array housing the question and the player asking, passed into this class.
    protected static String playerQuestion; // The question storage.
    protected static String playerAsking; // The player who asked the question.
    protected static String result; // Determines if the question asked is true or false
    protected static String CharacterPath; // Path of the players characters remaining, determined by who asked the question.
    private static ArrayList<GameCharacter> eliminatedCharacters; // List of characters that were eliminated so far.
    protected static String EliminatedPath; // Path of the players characters remaining, determined by who asked the question.


    /**
     * Sets the question asked by the player.
     * 
     * @param question Array containing the question, the player who asked, and the result (true/false).
     */
    public static void setQuestionAsked(String[] question) {
        for(int i=0; i<3;i++){
            questionAsked[i] = question[i];

        }
    }

    /**
     * Handles the logic for filtering characters based on the question asked.
     * Updates the character lists and writes the changes to the corresponding files.
     */
    public static void handleQuestions() {
        characters = new ArrayList<>();
        eliminatedCharacters = new ArrayList<>();
        String playerAsking = questionAsked[1];
        String result = questionAsked[2];
        String playerQuestion = questionAsked[0];
        String CharacterPath = "";
        String EliminatedPath = "";

        if(playerAsking.equals("1")){
            CharacterPath = "src\\resources\\p1_characters_remaining.txt";
            EliminatedPath = "src\\resources\\p1_characters_removed.txt";
        }
        else if(playerAsking.equals("2")){
            CharacterPath = "src\\resources\\p2_characters_remaining.txt";
            EliminatedPath = "src\\resources\\p2_characters_removed.txt";
        }
        // Initialize TextFileReader for characters
        TextFileReader characterReader = new TextFileReader(CharacterPath);
        characterReader.readFile();


        // Dumping the users GameCharacter objects and add them to the players character list
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
            characters.add(character);
        }

        
        //First Question
        if(playerQuestion.equals("Is the person a male?")){
            // Iterator of characters array to remove objects without outofbounds error.
            if(result.equals("true")){
                Iterator<GameCharacter> iterator = characters.iterator();
                while (iterator.hasNext()) {
                    GameCharacter character = iterator.next();
                    if (character.getGender().equals("female")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                }
                writeCharactersToFile(CharacterPath);
                writeEliminatedCharactersToFile(EliminatedPath);
            }
            if(result.equals("false")){
                Iterator<GameCharacter> iterator = characters.iterator();
                while (iterator.hasNext()) {
                    GameCharacter character = iterator.next();
                    if (character.getGender().equals("male")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                }
                writeCharactersToFile(CharacterPath);
                writeEliminatedCharactersToFile(EliminatedPath);

            }  
        }  
        //Second Question
        if(playerQuestion.equals("Is the person a female?")){
            // Iterator of characters array to remove objects without outofbounds error.
            if(result.equals("true")){
                Iterator<GameCharacter> iterator = characters.iterator();
                while (iterator.hasNext()) {
                    GameCharacter character = iterator.next();
                    if (character.getGender().equals("male")) {
                        eliminatedCharacters.add(character);                        
                        iterator.remove();
                    }
                }
                writeCharactersToFile(CharacterPath);
                writeEliminatedCharactersToFile(EliminatedPath);

            }
            if(result.equals("false")){
                Iterator<GameCharacter> iterator = characters.iterator();
                while (iterator.hasNext()) {
                    GameCharacter character = iterator.next();
                    if (character.getGender().equals("female")) {
                        eliminatedCharacters.add(character);                        
                        iterator.remove();
                    }
                }
                writeCharactersToFile(CharacterPath);
                writeEliminatedCharactersToFile(EliminatedPath);
            }   
        } 
        
        //Third Question
        if(playerQuestion.equals("Is the eye colour brown?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getEyeColour().equals("green")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                    if (character.getEyeColour().equals("blue")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getEyeColour().equals("brown")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);    
            writeEliminatedCharactersToFile(EliminatedPath);
        } 

        //Fourth Question
        if(playerQuestion.equals("Is the eye colour green?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getEyeColour().equals("brown")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                    if (character.getEyeColour().equals("blue")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getEyeColour().equals("green")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath); 
            writeEliminatedCharactersToFile(EliminatedPath);
        } 

        //Fifth Question
        if(playerQuestion.equals("Is the eye colour blue?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getEyeColour().equals("green")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                    if (character.getEyeColour().equals("brown")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getEyeColour().equals("blue")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
            writeEliminatedCharactersToFile(EliminatedPath);
        }

        // Sixth Question
        if(playerQuestion.equals("Does the person have a light skin tone?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getSkinTone().equals("dark")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getSkinTone().equals("light")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
            writeEliminatedCharactersToFile(EliminatedPath);
        }

        // Seventh Question
        if(playerQuestion.equals("Does the person have a dark skin tone?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getSkinTone().equals("light")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getSkinTone().equals("dark")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
            writeEliminatedCharactersToFile(EliminatedPath);
        }

        // Eighth Question
        if(playerQuestion.equals("Is the hair colour black?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getHairColour().equals("brown")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("blonde")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("ginger")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("white")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getHairColour().equals("black")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
            writeEliminatedCharactersToFile(EliminatedPath);
        }
        
        // Ninth Question
        if(playerQuestion.equals("Is the hair colour brown?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getHairColour().equals("black")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("blonde")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("ginger")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("white")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getHairColour().equals("brown")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
            writeEliminatedCharactersToFile(EliminatedPath);
        }

        // Tenth Question
        if(playerQuestion.equals("Is the hair colour ginger?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getHairColour().equals("brown")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("blonde")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("black")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();  
                    }  
                    if (character.getHairColour().equals("white")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getHairColour().equals("ginger")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
            writeEliminatedCharactersToFile(EliminatedPath);
        }

        // Eleventh Question
        if(playerQuestion.equals("Is the hair colour white/no hair?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getHairColour().equals("brown")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("blonde")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("ginger")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("black")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getHairColour().equals("white")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
            writeEliminatedCharactersToFile(EliminatedPath);
        }

        // Twelfth Question
        if(playerQuestion.equals("Is the hair colour blonde?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getHairColour().equals("brown")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("black")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("ginger")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("white")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }  
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getHairColour().equals("blonde")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
            writeEliminatedCharactersToFile(EliminatedPath);
        }

        // Thirteenth Question
        if(playerQuestion.equals("Does the person have facial hair?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                boolean istrue = character.getFacialHair();

                if (result.equals("true") && !istrue) {
                    eliminatedCharacters.add(character);
                    iterator.remove();
                } else if (result.equals("false") && istrue) {
                    eliminatedCharacters.add(character);
                    iterator.remove();
                }
            }
            writeCharactersToFile(CharacterPath);
            writeEliminatedCharactersToFile(EliminatedPath);
        }

        // Fourteenth Question
        if(playerQuestion.equals("Is the person wearing glasses?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                boolean istrue = character.getGlasses();

                if (result.equals("true") && !istrue) {
                    eliminatedCharacters.add(character);
                    iterator.remove();
                } else if (result.equals("false") && istrue) {
                    eliminatedCharacters.add(character);
                    iterator.remove();
                }
            }
            writeCharactersToFile(CharacterPath);
            writeEliminatedCharactersToFile(EliminatedPath);
        }

        // Fifteenth Question
        if(playerQuestion.equals("Is the person showing their teeth?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                boolean istrue = character.getShowingTeeth();

                if (result.equals("true") && !istrue) {
                    eliminatedCharacters.add(character);
                    iterator.remove();
                } else if (result.equals("false") && istrue) {
                    eliminatedCharacters.add(character);
                    iterator.remove();
                }
            }
            writeCharactersToFile(CharacterPath);
            writeEliminatedCharactersToFile(EliminatedPath);
        }

        // Sixteenth Question
        if(playerQuestion.equals("Is the person wearing a hat?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                boolean istrue = character.getWearingHat();

                if (result.equals("true") && !istrue) {
                    eliminatedCharacters.add(character);
                    iterator.remove();
                } else if (result.equals("false") && istrue) {
                    eliminatedCharacters.add(character);
                    iterator.remove();
                }
            }
            writeCharactersToFile(CharacterPath);
            writeEliminatedCharactersToFile(EliminatedPath);
        }

        // Seventeenth Question
        if(playerQuestion.equals("Does the person have short hair?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getHairLength().equals("long")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    } 
                    if (character.getHairLength().equals("tied")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                    if (character.getHairLength().equals("bald")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    } 
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getHairLength().equals("short")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
            writeEliminatedCharactersToFile(EliminatedPath);
        }

        // Eighteenth Question
        if(playerQuestion.equals("Does the person have their hair tied up?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getHairLength().equals("long")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                    if (character.getHairLength().equals("short")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                    if (character.getHairLength().equals("bald")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    } 
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getHairLength().equals("tied")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
            writeEliminatedCharactersToFile(EliminatedPath);
        }

        // Ninteenth Question
        if(playerQuestion.equals("Does the person have long hair?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getHairLength().equals("tied")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                    if (character.getHairLength().equals("short")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                    if (character.getHairLength().equals("bald")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    } 
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getHairLength().equals("long")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
            writeEliminatedCharactersToFile(EliminatedPath);
        }

        // Twentieth Question
        if(playerQuestion.equals("Is the person bald?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getHairLength().equals("tied")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }

                    if (character.getHairLength().equals("short")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                    if (character.getHairLength().equals("long")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    } 
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getHairLength().equals("bald")) {
                        eliminatedCharacters.add(character);
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
            writeEliminatedCharactersToFile(EliminatedPath);
        }

        // Twenty First Question
        if(playerQuestion.equals("Does the person have an ear piercing?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                boolean istrue = character.getPiercings();
                if (result.equals("true") && !istrue) {
                    eliminatedCharacters.add(character);
                    iterator.remove();
                } else if (result.equals("false") && istrue) {
                    eliminatedCharacters.add(character);
                    iterator.remove();
                }
            }
            writeCharactersToFile(CharacterPath);
            writeEliminatedCharactersToFile(EliminatedPath);
        }           
           
    }

    /**
     * Writes the list of remaining characters to a file.
     * 
     * @param filePath Path to the file where the remaining characters are stored.
     */
    public static void writeCharactersToFile(String filePath) {
        TextFileWriter writer = new TextFileWriter(filePath);
        ArrayList<String> characterStrings = new ArrayList<>();

        for (GameCharacter character : characters) {
            characterStrings.add(character.toFileString());
        }

        writer.writeFile(characterStrings);
    }

    /**
     * Writes the list of eliminated characters to a file.
     * 
     * @param filePath Path to the file where the eliminated characters are stored.
     */
    public static void writeEliminatedCharactersToFile(String filePath) {
        TextFileWriter writer = new TextFileWriter(filePath);
        ArrayList<String> characterStrings = new ArrayList<>();

        for (GameCharacter character : eliminatedCharacters) {
            characterStrings.add(character.toFileString());
        }

        writer.appendArrayToFile(characterStrings);
    }


    /**
     * Checks if a specific question asked by the player is true or false.
     * This method is used in player-vs-player and player-vs-computer modes.
     * 
     * @param playerQuestion The question asked by the player.
     * @param player The player who asked the question.
     * @return true if the question's answer is affirmative, false otherwise.
     */
    public static boolean checkQuestion(String playerQuestion, int player){
        if(player == 1){
            getCharacters("src\\resources\\p2choice.txt");
        }
        if(player == 2){
            getCharacters("src\\resources\\p1choice.txt");
        }
        
        //First Question
        if(playerQuestion.equals("Is the person a male?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                if (character.getGender().equals("male")) {

                    return true;
                } else {     

                    return false;

                }
            }     
        }  

        //Second Question
        else if(playerQuestion.equals("Is the person a female?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                if (character.getGender().equals("female")) {
                    return true;
                } else{
                    return false;
                }
            }   
        } 
        
        //Third Question
        else if(playerQuestion.equals("Is the eye colour brown?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                if (character.getEyeColour().equals("brown")) {
                    return true;
                } else {
                    return false;
                }
            }
        } 

        //Fourth Question
        else if(playerQuestion.equals("Is the eye colour green?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                if (character.getEyeColour().equals("green")) {
                    return true;
                } else {
                    return false;
                }
            }
        } 

        //Fifth Question
        else if(playerQuestion.equals("Is the eye colour blue?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                if (character.getEyeColour().equals("blue")) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        // Sixth Question
        else if(playerQuestion.equals("Does the person have a light skin tone?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                if (character.getSkinTone().equals("light")) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        // Seventh Question
        else if(playerQuestion.equals("Does the person have a dark skin tone?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                if (character.getSkinTone().equals("dark")) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        // Eighth Question
        else if(playerQuestion.equals("Is the hair colour black?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                if (character.getHairColour().equals("black")) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        
        // Ninth Question
        else if(playerQuestion.equals("Is the hair colour brown?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                if (character.getHairColour().equals("brown")) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        // Tenth Question
        else if(playerQuestion.equals("Is the hair colour ginger?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                if (character.getHairColour().equals("ginger")) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        // Eleventh Question
        else if(playerQuestion.equals("Is the hair colour white/no hair?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                if (character.getHairColour().equals("white")) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        // Twelfth Question
        else if(playerQuestion.equals("Is the hair colour blonde?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                if (character.getHairColour().equals("blonde")) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        // Thirteenth Question
        else if(playerQuestion.equals("Does the person have facial hair?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                if (character.getFacialHair()) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        // Fourteenth Question
        else if(playerQuestion.equals("Is the person wearing glasses?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                if (character.getGlasses()) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        // Fifteenth Question
        else if(playerQuestion.equals("Is the person showing their teeth?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                if (character.getShowingTeeth()) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        // Sixteenth Question
        else if(playerQuestion.equals("Is the person wearing a hat?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                if (character.getWearingHat()) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        // Seventeenth Question
        else if(playerQuestion.equals("Does the person have short hair?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                if (character.getHairLength().equals("short")) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        // Eighteenth Question
        else if(playerQuestion.equals("Does the person have their hair tied up?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                if (character.getHairLength().equals("tied")) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        // Ninteenth Question
        else if(playerQuestion.equals("Does the person have long hair?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                if (character.getHairLength().equals("long")) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        // Twentieth Question
        else if(playerQuestion.equals("Is the person bald?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                if (character.getHairLength().equals("bald")) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        // Twenty First Question
        else if(playerQuestion.equals("Does the person have an ear piercing?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                if (character.getPiercings()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Retrieves the list of characters for a player.
     * Used by checkQuestion method.
     * 
     * @param path The file path of the player's character list.
     */
    private static void getCharacters(String path) {
        characters = new ArrayList<>();
        TextFileReader characterReader = new TextFileReader(path);
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
            characters.add(character);
        }
    }
}
