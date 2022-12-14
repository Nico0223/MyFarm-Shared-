/**
 * This class initializes on the list of tools that the user can use for the farm lot. Similar to the SeedList class,
 * information on the tools are in a form of arrays, and they are the lists of tools, cost of the tools, and experiences
 * gained from buying the tool. There is also a linear search for finding the tool and getters for each information
 */

public class ToolList { 
    private final String[] toolList = {"Plow", "Watering can", "Fertilizer", "Pickaxe", "Shovel"}; // array strings for the list of tools
    private final double[] costList = {0, 0, 10, 50, 7}; // an array on the list of costs corresponding to the tool list (same scenario for line 4)
    private final double[] experienceList = {0.5, 0.5, 4, 15, 2};
    public int getIndexTool(String order) { // finds the index of the tool based on the user's order
        int i;
        for (i = 0; i < 5; i++) { // uses linear search to find the tool
            if (toolList[i].equals(order)) // if the tool is found
                return i;
        }
        return -1;
    }

    /**
     * This getter method returns the cost of the selected tool
     *
     * @param index the index of the selected tool
     * @return the cost of the selected tool
     */
    public double costIndex(int index){ // gets the index on the cost of the tool
        return costList[index];
    }

    /**
     * This getter method returns the experiences gained from using the selected tool
     *
     * @param index the index of the selected tool
     * @return the experiences gained from using the selected tool
     */
    public double expIndex(int index){ // gets the index on the experience gained from the tool
        return experienceList[index];
    }
}
