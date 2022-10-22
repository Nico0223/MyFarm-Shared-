import java.util.*;

public class Player {
    private int objectCoins = 100;
    private double experience = 0;
    private Tools tool = null;
    private String order;

    public void displayInterface(Lot tile){
        System.out.println("Objectcoins: " + this.objectCoins);
        System.out.println("Experience: " + this.experience);
        /*if (tool == null)
            System.out.println("Equipped: None");
        else
            System.out.println("Equipped: " + this.tool.ShowTool());*/
        System.out.println("Tile State: " + tile.showState());
    }

    public String inputPlayer(){
        Scanner sc = new Scanner(System.in);
        System.out.print("\nWhat do you order? ");
        this.order = sc.nextLine();
        System.out.println("You ordered " + order + ".");
        return this.order;
    }
    public String inputSeed(){
        Scanner sc = new Scanner(System.in);
        System.out.println("What kind of seed? ");
        this.order = sc.nextLine();
        System.out.println("You choose " + this.order + ".");
        return this.order;
    }


    public void buyTool(PurchaseTool orderTool) {
        ToolList orderList = new ToolList();
        orderTool.initializeOrder(orderList, this.order);
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
            case "Pickaxe" -> System.out.println("Bruh");
            case "Shovel" -> tile.shovelTile();
            default -> System.out.println("What the hell is this?!\n");
        }
        tool = null;
    }

    public int buySeed(PurchaseSeed purchaseSeed, String order) {
        SeedList seedList = new SeedList();
        int i = purchaseSeed.initializeOrder(seedList, order);
        if (i != -1)
            this.objectCoins -= purchaseSeed.getCost();
        return i;
    }
    public void plantSeed(Lot tile, Seed seed) {
        tile.plantSeed(seed);
    }
    public void sellHarvest(Lot tile) {
        this.experience += tile.getExperience();
        this.objectCoins += tile.harvest();
    }

}