public class PurchaseTool { // This class initializes the player on purchasing the tool of their choice
    private double cost; // a variable for the cost of the tool
    private double experience; // a variable for the experience from buying the tool

    public void initializeOrder(ToolList orderList, String request) { // initializes the user on buying the tool
        int index = orderList.getIndexTool(request); // index of the user's tool to purchase gets passed into the index variable

        if (index != -1) { // if the tool to purchase is found
            this.cost = orderList.costIndex(index); // passes the  the cost of the user's purchasetool to the variable index
            this.experience = orderList.expIndex(index); // passes the experience value on the user's purchasetool to the variable experience
            System.out.println("You have spent " + this.cost + " ObjectCoins");
            System.out.println("You have gained " + this.experience + " experiences");
        }
    }

    public double getCost() { // a getter for the cost of the toolto purchase
        return this.cost;
    }

    public double getExp() { // a getter for the experience on buying the tool to purchase
        return this.experience;
    }
}