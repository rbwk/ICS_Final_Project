public class Characters {
    
    //Characteristics (Maybe a menu for customizing characters)
    protected String name;
    protected String sex;
    protected String hair_length;
    protected String skin_tone;

    //Make more indepth in seperate program. 
    protected boolean accessory;
    protected boolean bald;
    

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
