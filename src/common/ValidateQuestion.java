package common;

import java.util.Iterator;
import java.util.ArrayList;

public class ValidateQuestion {
    private static ArrayList<GameCharacter> characters; // Character array.
    
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
}

