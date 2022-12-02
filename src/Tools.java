/**
// This class initializes on the user on using the tool. There is a getter method for the tool that the user will use.
*/

public class Tools { 
    private final String toolType; // string variable for the type of tool 

    public Tools(String newTool){ // a constructor for the Tool class
        this.toolType = newTool; // // the newtool parameter or the order of the user gets passed into the tooltype variable
    }

    public String showTool() { // shows or gets the toolType of the user
        return this.toolType;
    }

}
