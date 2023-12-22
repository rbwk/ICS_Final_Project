public class Characters {

    // Characteristics (Maybe a menu for customizing characters)
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

    // Constructor Methods
    public Characters() {
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

    // Behaviours
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

}
