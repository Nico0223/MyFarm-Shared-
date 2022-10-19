public class Player {
    private int objectCoins = 100;
    private double experience = 0;
    private Tools tool = null;

    public void displayInterface(Lot tile){
        System.out.println("Objectcoins: " + this.objectCoins);
        System.out.println("Experience: " + this.experience);
        /*if (tool == null)
            System.out.println("Equipped: None");
        else
            System.out.println("Equipped: " + this.tool.ShowTool());*/
        System.out.println("Tile State: " + tile.showState());
    }


    public void buyTool(PurchaseTool orderTool, String request) {
        ToolList orderList = new ToolList();
        orderTool.initializeOrder(orderList, request);
        objectCoins = objectCoins - orderTool.getCost();
        experience = experience + orderTool.getExp();
    }

    public void equipTool(Tools tool){
        this.tool = tool;
    }

    public void useTool(Lot tile){
        switch (this.tool.showTool()) {
            case "Plow" -> tile.plowTile();
            case "Watering Can" -> tile.waterPlant();
            case "Fertilizer" -> tile.fertilizePlant();
            case "Pickaxe" -> tile.mineRock();
            case "Shovel" -> tile.shovelTile();
            default -> System.out.println("What the hell is this?!\n");
        }
        tool = null;
    }

}