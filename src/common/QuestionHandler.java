package common;

import java.util.ArrayList;
import java.util.Iterator;

public class QuestionHandler {
	//protected static int diff; // Difficulty level (CURRENTLY UNUSED)
    //protected static ArrayList<String> questions; // List of questions (CURRENTLY UNUSED)
    private static ArrayList<GameCharacter> characters; // Players list of remaining characters.
    protected static String[] questionAsked = new String[3]; // The array housing the question and the player asking, passed into this class.
    protected static String playerQuestion; // The question storage.
    protected static String playerAsking; // The player who asked the question.
    protected static String result; // Determines if the question asked is true or false
    protected static String CharacterPath; // Path of the players characters remaining, determined by who asked the question.

    /**
     * Sets the questions asked array to the question and the player asking
     * @param question The array containing the question asked and the user that asked it, along with whether 
     *                 the answer to the question is true or false
     */
    public static void setQuestionAsked(String[] question) {
        for(int i=0; i<3;i++){
            questionAsked[i] = question[i];

        }
    }

    // Fills player characterlists with the new remaining characters after a question has been processed.
    public static void handleQuestions() {
        characters = new ArrayList<>();
        //questions = new ArrayList<>();
        String playerAsking = questionAsked[1];
        String result = questionAsked[2];
        String playerQuestion = questionAsked[0];
        String CharacterPath = "";

        if(playerAsking.equals("1")){
            CharacterPath = "src\\resources\\p1_characters_remaining.txt";
        }
        else if(playerAsking.equals("2")){
            CharacterPath = "src\\resources\\p2_characters_remaining.txt";
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
        

        // Initialize TextFileReader for questions   (CURRENTLY UNUSED)
        //TextFileReader questionReader = new TextFileReader("src\\resources\\questions.txt");
        //questionReader.readFile();
        //questions = questionReader.getQuestions();                
 
            /**
             * Group of if statements responsible for question handling.
             * Written off of the assumption that question asked by user will be given to this program as a string.
             * "Is the person a male?"   - Like so
             * This program will not remove questions from the list of options available to it, 
             * instead it will assume the class that called this class will already handle this functionality. 
             * Keep in mind, if you wish to add this functionality into this class instead
             * the option to is present and should be very simple, for simplicity sake in writing the logic, and because
             * the GUI programs and other game logic programs will probably need to filter the list of questions remaining, 
             * that was not implemented here.
             * 
            */
        //First Question
        if(playerQuestion.equals("Is the person a male?")){
            // Iterator of characters array to remove objects without outofbounds error.
            if(result.equals("true")){
                Iterator<GameCharacter> iterator = characters.iterator();
                while (iterator.hasNext()) {
                    GameCharacter character = iterator.next();
                    if (character.getGender().equals("female")) {
                        iterator.remove();
                    }
                }
                writeCharactersToFile(CharacterPath);

            }
            if(result.equals("false")){
                Iterator<GameCharacter> iterator = characters.iterator();
                while (iterator.hasNext()) {
                    GameCharacter character = iterator.next();
                    if (character.getGender().equals("male")) {
                        iterator.remove();
                    }
                }
                writeCharactersToFile(CharacterPath);

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
                        iterator.remove();
                    }
                }
                writeCharactersToFile(CharacterPath);

            }
            if(result.equals("false")){
                Iterator<GameCharacter> iterator = characters.iterator();
                while (iterator.hasNext()) {
                    GameCharacter character = iterator.next();
                    if (character.getGender().equals("female")) {
                        iterator.remove();
                    }
                }
                writeCharactersToFile(CharacterPath);
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
                        iterator.remove();
                    }
                    if (character.getEyeColour().equals("blue")) {
                        iterator.remove();
                    }  
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getEyeColour().equals("brown")) {
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);    
        } 

        //Fourth Question
        if(playerQuestion.equals("Is the eye colour green?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getEyeColour().equals("brown")) {
                        iterator.remove();
                    }
                    if (character.getEyeColour().equals("blue")) {
                        iterator.remove();
                    }  
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getEyeColour().equals("green")) {
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath); 
        } 

        //Fifth Question
        if(playerQuestion.equals("Is the eye colour blue?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getEyeColour().equals("green")) {
                        iterator.remove();
                    }
                    if (character.getEyeColour().equals("brown")) {
                        iterator.remove();
                    }  
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getEyeColour().equals("blue")) {
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
        }

        // Sixth Question
        if(playerQuestion.equals("Does the person have a light skin tone?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getSkinTone().equals("dark")) {
                        iterator.remove();
                    }  
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getSkinTone().equals("light")) {
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
        }

        // Seventh Question
        if(playerQuestion.equals("Does the person have a dark skin tone?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getSkinTone().equals("light")) {
                        iterator.remove();
                    }  
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getSkinTone().equals("dark")) {
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
        }

        // Eighth Question
        if(playerQuestion.equals("Is the hair colour black?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getHairColour().equals("brown")) {
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("blonde")) {
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("ginger")) {
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("white")) {
                        iterator.remove();
                    }  
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getHairColour().equals("black")) {
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
        }
        
        // Ninth Question
        if(playerQuestion.equals("Is the hair colour brown?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getHairColour().equals("black")) {
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("blonde")) {
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("ginger")) {
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("white")) {
                        iterator.remove();
                    }  
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getHairColour().equals("brown")) {
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
        }

        // Tenth Question
        if(playerQuestion.equals("Is the hair colour ginger?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getHairColour().equals("brown")) {
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("blonde")) {
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("black")) {
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("white")) {
                        iterator.remove();
                    }  
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getHairColour().equals("ginger")) {
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
        }

        // Eleventh Question
        if(playerQuestion.equals("Is the hair colour white/no hair?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getHairColour().equals("brown")) {
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("blonde")) {
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("ginger")) {
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("black")) {
                        iterator.remove();
                    }  
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getHairColour().equals("white")) {
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
        }

        // Twelfth Question
        if(playerQuestion.equals("Is the hair colour blonde?")){
            // Iterator of characters array to remove objects without outofbounds error.
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getHairColour().equals("brown")) {
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("black")) {
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("ginger")) {
                        iterator.remove();
                    }  
                    if (character.getHairColour().equals("white")) {
                        iterator.remove();
                    }  
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getHairColour().equals("blonde")) {
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
        }

        // Thirteenth Question
        if(playerQuestion.equals("Does the person have facial hair?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                boolean istrue = character.getFacialHair();

                if (result.equals("true") && !istrue) {
                    iterator.remove();
                } else if (result.equals("false") && istrue) {
                    iterator.remove();
                }
            }
            writeCharactersToFile(CharacterPath);
        }

        // Fourteenth Question
        if(playerQuestion.equals("Is the person wearing glasses?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                boolean istrue = character.getGlasses();

                if (result.equals("true") && !istrue) {
                    iterator.remove();
                } else if (result.equals("false") && istrue) {
                    iterator.remove();
                }
            }
            writeCharactersToFile(CharacterPath);
        }

        // Fifteenth Question
        if(playerQuestion.equals("Is the person showing their teeth?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                boolean istrue = character.getShowingTeeth();

                if (result.equals("true") && !istrue) {
                    iterator.remove();
                } else if (result.equals("false") && istrue) {
                    iterator.remove();
                }
            }
            writeCharactersToFile(CharacterPath);
        }

        // Sixteenth Question
        if(playerQuestion.equals("Is the person wearing a hat?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                boolean istrue = character.getWearingHat();

                if (result.equals("true") && !istrue) {
                    iterator.remove();
                } else if (result.equals("false") && istrue) {
                    iterator.remove();
                }
            }
            writeCharactersToFile(CharacterPath);
        }

        // Seventeenth Question
        if(playerQuestion.equals("Does the person have short hair?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getHairLength().equals("long")) {
                        iterator.remove();
                    } 
                    if (character.getHairLength().equals("tied")) {
                        iterator.remove();
                    }
                    if (character.getHairLength().equals("bald")) {
                        iterator.remove();
                    } 
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getHairLength().equals("short")) {
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
        }

        // Eighteenth Question
        if(playerQuestion.equals("Does the person have their hair tied up?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getHairLength().equals("long")) {
                        iterator.remove();
                    }
                    if (character.getHairLength().equals("short")) {
                        iterator.remove();
                    }
                    if (character.getHairLength().equals("bald")) {
                        iterator.remove();
                    } 
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getHairLength().equals("tied")) {
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
        }

        // Ninteenth Question
        if(playerQuestion.equals("Does the person have long hair?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getHairLength().equals("tied")) {
                        iterator.remove();
                    }
                    if (character.getHairLength().equals("short")) {
                        iterator.remove();
                    }
                    if (character.getHairLength().equals("bald")) {
                        iterator.remove();
                    } 
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getHairLength().equals("long")) {
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
        }

        // Twentieth Question
        if(playerQuestion.equals("Is the person bald?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                if(result.equals("true")){
                    GameCharacter character = iterator.next();
                    if (character.getHairLength().equals("tied")) {
                        iterator.remove();
                    }

                    if (character.getHairLength().equals("short")) {
                        iterator.remove();
                    }
                    if (character.getHairLength().equals("long")) {
                        iterator.remove();
                    } 
                }
                if(result.equals("false")){
                    GameCharacter character = iterator.next();
                    if (character.getHairLength().equals("bald")) {
                        iterator.remove();
                    }
                }
            }
            writeCharactersToFile(CharacterPath);
        }

        // Twenty First Question
        if(playerQuestion.equals("Does the person have an ear piercing?")){
            Iterator<GameCharacter> iterator = characters.iterator();
            while (iterator.hasNext()) {
                GameCharacter character = iterator.next();
                boolean istrue = character.getPiercings();
                if (result.equals("true") && !istrue) {
                    iterator.remove();
                } else if (result.equals("false") && istrue) {
                    iterator.remove();
                }
            }
            writeCharactersToFile(CharacterPath);
        }           
           
    }
    public static void writeCharactersToFile(String filePath) {
        TextFileWriter writer = new TextFileWriter(filePath);
        ArrayList<String> characterStrings = new ArrayList<>();

        for (GameCharacter character : characters) {
            characterStrings.add(character.toFileString());
        }

        writer.writeFile(characterStrings);
    }

    // Checks the question asked by user and returns the true or false statement depending on the result.
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


    // For checkQuestion method, handleQuestions method has its own file reader.
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
