/** This class initializes the player on purchasing the tool of their choice. It has the cost of the selected tool
 * and the experiences gained from using the selected tool which was obtained from the orderList object with the index
*/
public class PurchaseTool { 
    private double cost; // a variable for the cost of the tool
    private double experience; // a variable for the experience from buying the tool

    /**
     * This method initializes the purchase of tools by searching for available tool in the ToolList class and
     * sets the value of the cost of the tool and the experience gained from using it.
     *
     * @param orderList the list of available tools in the game with their cost and exp gained
     * @param request the tool the player orders
     */
    public void initializeOrder(ToolList orderList, String request) { // initializes the user on buying the tool
        int index = orderList.getIndexTool(request); // index of the user's tool to purchase gets passed into the index variable

        if (index != -1) { // if the tool to purchase is found
            this.cost = orderList.costIndex(index); // passes the cost of the user's purchaseTool to the variable index
            this.experience = orderList.expIndex(index); // passes the experience value on the user's purchaseTool to the variable experience
            System.out.println("You have spent " + this.cost + " ObjectCoins");
            System.out.println("You have gained " + this.experience + " experiences");
        }
    }

    /**
     * This getter method returns the cost of the selected tool
     *
     * @return the cost of the tool
     */
    public double getCost() { // a getter for the cost of the selected tool
        return this.cost;
    }

    /**
     * This getter method returns the exp gained from using the selected tool
     *
     * @return the experience gained to the player
     */
    public double getExp() { // a getter for the experience on buying the tool to purchase
        return this.experience;
    }
}