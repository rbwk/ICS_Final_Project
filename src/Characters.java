public class Characters {
    
    //Characteristics (Maybe a menu for customizing characters)
    protected String name;
    protected String sex;
    protected String hair_length;

    //Make more indepth in seperate program. 
    protected boolean accessory;

    //Constructor Methods
    public Characters(){
        name = null;
        sex = null;
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
