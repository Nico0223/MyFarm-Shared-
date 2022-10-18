public class Player {
    private int objectCoins = 100;
    private double experience = 0;
    private Tools tool = null;

    public void DisplayInterface(Lot tile){
        System.out.println("Objectcoins: " + this.objectCoins);
        System.out.println("Experience: " + this.experience);
        /*if (tool == null)
            System.out.println("Equipped: None");
        else
            System.out.println("Equipped: " + this.tool.ShowTool());*/
        System.out.println("Tile State: " + tile.ShowState());
    }


    public void BuyAndEquip(PurchaseTool orderTool, String request) {
        ToolList orderList = new ToolList();
        tool = orderTool.InitializeOrder(orderList, request);
        if (tool == null) {
            System.out.println("This tool is not available.\n");
            return;
        }
        objectCoins = objectCoins - orderTool.GetCost();
        experience = experience + orderTool.GetExp();
    }

    public void UseTool(Lot tile){
        switch (this.tool.ShowTool()) {
            case "Plow" -> tile.PlowTile();
            case "Watering Can" -> tile.WaterPlant();
            case "Fertilizer" -> tile.FertilizePlant();
            case "Pickaxe" -> tile.MineRock();
            case "Shovel" -> tile.ShovelTile();
            default -> System.out.println("You trying to do it with bare hands? Nuts!");
        }
        tool = null;
    }

}