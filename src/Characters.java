public class Characters {

    // Characteristics (Maybe a menu for customizing characters)
    protected String name;
    protected String gender;
    protected String eye_colour;
    protected String skin_tone;
    protected String hair_colour;
    protected boolean facial_hair;
    protected boolean glasses;
    protected boolean showing_teeth;
    protected boolean wearing_hat;
    protected String hair_length;
    protected boolean piercings;

    // Constructor Methods
    public Characters() {
        name = null;
        gender = null;
        eye_colour = null;
        skin_tone = null;
        hair_colour = null;
        facial_hair = false;
        glasses = false;
        showing_teeth = false;
        wearing_hat = false;
        hair_length = null;
        piercings = false;
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

    public void setEyeColour(String newEyeColour) {
        eye_colour = newEyeColour;
    }

    public String getEyeColour() {
        return eye_colour;
    }

    public void setSkinTone(String newSkinTone) {
        skin_tone = newSkinTone;
    }

    public String getSkinTone() {
        return skin_tone;
    }

    public void setHairColour(String newHairColour) {
        hair_colour = newHairColour;
    }

    public String getHairColour() {
        return hair_colour;
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
