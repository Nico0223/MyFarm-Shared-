public class PurchaseTool {
    private double cost;
    private double experience;

    public void initializeOrder(ToolList orderList, String request) {
        int index = orderList.getIndexTool(request);

        if (index != -1) {
            this.cost = orderList.costIndex(index);
            this.experience = orderList.expIndex(index);
            System.out.println("You have spent " + this.cost + " ObjectCoins");
            System.out.println("You have gained " + this.experience + " experiences");
        }
    }

    public double getCost() {
        return this.cost;
    }

    public double getExp() {
        return this.experience;
    }
}