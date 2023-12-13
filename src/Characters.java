public class Characters {
    
    //Characteristics (Maybe a menu for customizing characters)
    protected String name;
    protected String gender;
    protected String eye_color;
    protected String skin_tone;
    protected String hair_color;
    protected String hair_length;
    
    protected boolean visible_teeth;
    protected boolean facial_hear;
    protected boolean glasses;
    protected boolean hat;

    //Constructor Methods
    public Characters(){
        name = null;
        sex = null;
        hair_length = null;
    }

    //Behaviours
    public void setName(String newName){
        name = newName;
    }
    public String getName(){
        return name;
    }

    public void setSex(String newSex){
        sex = newSex;
    }
    public String getSex(){
        return sex;
    }


}
