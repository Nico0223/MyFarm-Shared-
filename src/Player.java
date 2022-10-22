import java.util.*;

public class Player {
    private double objectCoins = 1000;
    private double experience = 0;
    private Tools tool = null;
    private Registration registration = new Registration();
    private String order;

    public void displayInterface(Lot tile){
        System.out.println("Objectcoins: " + this.objectCoins);
        System.out.println("Experience: " + this.experience);
        /*if (tool == null)
            System.out.println("Equipped: None");
        else
            System.out.println("Equipped: " + this.tool.ShowTool());*/
        System.out.println("Level: " + this.registration.showLevel());
        System.out.println("Type: " + this.registration.showRegistration());
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


    public int buyTool(PurchaseTool orderTool) {
        ToolList orderList = new ToolList();
        orderTool.initializeOrder(orderList, this.order);
        if (this.objectCoins < orderTool.getCost()){
            System.out.println("Hah, you broke!!");
            return -1;
        }
        objectCoins = objectCoins - orderTool.getCost();
        experience = experience + orderTool.getExp();
        return 1;
    }

    public void equipTool(Tools tool){
        this.tool = tool;
    }

    public void useTool(Lot tile){
        switch (this.tool.showTool()) {
            case "Plow" -> tile.plowTile();
            case "Watering Can" -> {
                if (tile.getCrop() == null){
                    System.out.println("Dude, plant the tile first!");
                }
                else
                    tile.waterPlant();
            }
            case "Fertilizer" -> {
                if (tile.getCrop() == null){
                    System.out.println("Dude, plant the tile first!");
                    this.objectCoins += 10;
                    this.experience -= 4;
                }
               else
                    tile.fertilizePlant();
            }
            case "Pickaxe" -> {
                System.out.println("Bruh");
                System.out.println("Here's your refund so STOP BUYING A PICKAXE!");
                this.objectCoins += 50;
                this.experience -= 15;
            }
            case "Shovel" -> tile.shovelTile();
            default -> System.out.println("What the hell is this?!\n");
        }
        tool = null;
    }

    public int buySeed(PurchaseSeed purchaseSeed, String order) {
        SeedList seedList = new SeedList();
        int i = purchaseSeed.initializeOrder(seedList, order);
        if (i != -1){
            if (this.objectCoins < purchaseSeed.getCost()){
                System.out.println("Hah, you broke!!");
                return -1;
            }
            this.objectCoins -= purchaseSeed.getCost();
        }
        return i;
    }
    public void plantSeed(Lot tile, Seed seed) {
        tile.plantSeed(seed);
    }
    public void sellHarvest(Lot tile) {
        if (tile.harvest() == 0)
            return;
        this.objectCoins += tile.harvest();
        this.experience += tile.getExperience();
    }

    public void registration(){
        this.objectCoins = this.registration.initializeRegistration(new FarmerTypeList(), this.objectCoins);
    }


}