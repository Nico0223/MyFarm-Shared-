public class ToolList { // This class initializes on the list of tools that the user can use for the farm lot
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

    public double costIndex(int index){ // gets the index on the cost of the tool
        return costList[index];
    }
    public double expIndex(int index){ // gets the index on the experience gained from the tool
        return experienceList[index];
    }
}
