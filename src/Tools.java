/**
 * This class initializes on the user on using the tool.
 */

public class Tools { 
    private final String toolType; // string variable for the type of tool 

    /**
     * a constructor for the Tool class that creates the tool with the order from the player
     * @param newTool the tool to be created
     */
    public Tools(String newTool){
        this.toolType = newTool; // // the newTool parameter or the order of the user gets passed into the toolType variable
    }

    /**
     * A getter method for returning tool that the player equips.
     *
     * @return the name of the tool the player equips
     */
    public String showTool() { // shows or gets the toolType of the user
        return this.toolType;
    }

}
