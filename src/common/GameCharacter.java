/* GameCharacter.java class
 * By Nathan, Aryan, and Victoria
 * Last updated: January 5th 2024
 *
 * This class represents a character in the game. It holds various attributes of the character
 * like name, gender, physical traits, and other features. It also provides methods to set and
 * get these attributes.
 */

package common;
public class GameCharacter {

    // Attributes representing different characteristics of the game character
    protected String name;
    protected String gender;
    protected String skin_tone;
    protected String eye_color;
    protected String hair_color;
    protected String hair_length;

    protected boolean showing_teeth;
    protected boolean wearing_hat;
    protected boolean facial_hair;
    protected boolean piercings;
    protected boolean glasses;

    /**
     * Constructor for GameCharacter. Initializes the character attributes.
     */
    public GameCharacter() {
        name = null;
        gender = null;
        skin_tone = null;
        eye_color = null;
        hair_color = null;
        hair_length = null;
        
        showing_teeth = false;
        wearing_hat = false;
        facial_hair = false;
        piercings = false;
        glasses = false;
    }

    // Setters and Getters for each attribute
    public void setName(String newName) {
        name = newName;
    }

    public String getName() {
        return name;
    }

    public void setGender(String newGender) {
        gender = newGender;
    }

    public String getGender() {
        return gender;
    }

    public void setEyeColour(String newEyeColor) {
        eye_color = newEyeColor;
    }

    public String getEyeColour() {
        return eye_color;
    }

    public void setSkinTone(String newSkinTone) {
        skin_tone = newSkinTone;
    }

    public String getSkinTone() {
        return skin_tone;
    }

    public void setHairColour(String newHairColor) {
        hair_color = newHairColor;
    }

    public String getHairColour() {
        return hair_color;
    }

    public void setFacialHair(boolean newFacialHair) {
        facial_hair = newFacialHair;
    }

    public boolean getFacialHair() {
        return facial_hair;
    }

    public void setGlasses(boolean newGlasses) {
        glasses = newGlasses;
    }

    public boolean getGlasses() {
        return glasses;
    }

    public void setShowingTeeth(boolean newShowingTeeth) {
        showing_teeth = newShowingTeeth;
    }

    public boolean getShowingTeeth() {
        return showing_teeth;
    }

    public void setWearingHat(boolean newWearingHat) {
        wearing_hat = newWearingHat;
    }

    public boolean getWearingHat() {
        return wearing_hat;
    }

    public void setHairLength(String newHairLength) {
        hair_length = newHairLength;
    }

    public String getHairLength() {
        return hair_length;
    }

    public void setPiercings(boolean newPiercings) {
        piercings = newPiercings;
    }

    public boolean getPiercings() {
        return piercings;
    }

    /**
     * Converts the character's attributes to a string format suitable for file storage.
     * Each attribute is separated by a newline character.
     * 
     * @return String representation of the character's attributes.
     */
    public String toFileString() {
        return name + "\n" + 
               gender + "\n" + 
               eye_color + "\n" + 
               skin_tone + "\n" + 
               hair_color + "\n" + 
               String.valueOf(facial_hair) + "\n" + 
               String.valueOf(glasses) + "\n" + 
               String.valueOf(showing_teeth) + "\n" + 
               String.valueOf(wearing_hat) + "\n" + 
               hair_length + "\n" + 
               String.valueOf(piercings);
    }
}

