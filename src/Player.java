import java.util.*;

public class Player {
    private double objectCoins = 100;
    private double experience = 0;
    private Tools tool = null;
    private final Registration registration = new Registration();
    private String order;
    private Boolean gameEnd = false;


    public Boolean getGameEnd() { //  returns the value of gameEnd
		return gameEnd;
	}

	public void setGameEnd() { // sets the value of gameEnd
		this.gameEnd = true;
	}

	public void displayInterface(Lot tile){ //this method displays the farm information in terms of objectCoins, experience and Registration level and the farm tiles.
        System.out.println("Objectcoins: " + this.objectCoins);
        System.out.println("Experience: " + this.experience);
        System.out.println("Level: " + this.registration.showLevel());
        System.out.println("Type: " + this.registration.showRegistration());
        System.out.println("Tile State: " + tile.showState());
        if (this.objectCoins < 5 && tile.getCrop() == null){
            System.out.println("You have filed Chapter 11 Bankruptcy");
            this.gameEnd = true;
        }
        if (tile.getCrop() != null){
            tile.updateBonus(this);
        }

    }

    public String inputPlayer(){ // asks input from the user for their order
        if (this.gameEnd)
            return "Forfeit";
        Scanner sc = new Scanner(System.in);
        System.out.print("\nWhat do you order? ");
        this.order = sc.nextLine();
        System.out.println("You ordered " + order + ".");

        return this.order;
    }
    public String inputSeed(){ // this method asks input from the user on the type of seed to order
        Scanner sc = new Scanner(System.in);
        System.out.println("What kind of seed? ");
        this.order = sc.nextLine();
        System.out.println("You choose " + this.order + ".");
        //sc.close();
        return this.order;
    }


    public int buyTool(PurchaseTool orderTool) { // this method is initialized buying the tool that the user orders
        ToolList orderList = new ToolList(); // Instantiate the orderlist object with a ToolList class
        orderTool.initializeOrder(orderList, this.order);

        if (this.objectCoins < orderTool.getCost()){ // an if-statement to indicate if the objectCoins is less than the cost of the selected tool
            System.out.println("Hah, you broke!!");
            return -1; 
        }
        objectCoins = objectCoins - orderTool.getCost() + registration.getCostReduction(); // objectCoins gets subtracted to the cost of the orderTool
        experience = experience + orderTool.getExp(); // user gains experience based on the orderTool
        return 1;
    }

    public void equipTool(Tools tool){ // this method equips the tool that the user will use
        this.tool = tool;
    }

    public void useTool(Lot tile){ // this method would change the state of the selected tile with a equipped tool
        switch (this.tool.showTool()) {
            case "Plow" -> tile.plowTile(); // If using the "Plow" tool, it would plow the tile
            case "Watering Can" -> { // if using the "Watering Can" tool
                if (tile.getCrop() == null){ // if no crop is currently planted in the tile
                    System.out.println("Dude, plant the tile first!");
                }
                else // if a crop has been planted
                    tile.waterPlant();
            }
            case "Fertilizer" -> { //if using the "Fertilizer" tool
                if (tile.getCrop() == null){ // if there is no plant in the current tile
                    System.out.println("Dude, plant the tile first!");
                    this.objectCoins += 10; // refund process
                    this.experience -= 4; // refund process
                }
               else
                    tile.fertilizePlant(); // tile gets fertitlized
            }
            case "Pickaxe" -> { // If using the "Pickaxe" tool (coming soon)
                System.out.println("Bruh");
                System.out.println("Here's your refund so STOP BUYING A PICKAXE!");
                this.objectCoins += 50; // refund process
                this.experience -= 15; // refund process
            }
            case "Shovel" -> tile.shovelTile(); // If using the "Shovel" tool
            default -> System.out.println("What the hell is this?!\n");
        }
        registration.levelUp(this.experience);
        tool = null; // tool gets removed and set to null after use
    }

    public int buySeed(PurchaseSeed purchaseSeed)  { // This method would initialize the user's order for buying seeds
        SeedList seedList = new SeedList(); // initialize the seedList object with the SeedList class
        int i = purchaseSeed.initializeOrder(seedList, this.order); // the variable "i" gets the index of the seedList
        if (i != -1){ // if the index was found
            if (this.objectCoins < purchaseSeed.getCost()){
                System.out.println("Hah, you broke!!"); // if the objectCoins is less than the cost of the purchaseSeed
                return -1;
            }
            this.objectCoins -= purchaseSeed.getCost(); // objectCoins gets subtracted by the cost of the purchaseSeed
        }
        return i;
    }
    public void plantSeed(Lot tile, Seed seed) { // This method initializes on planting the seed on the tile
        tile.plantSeed(seed);

    }
    public void sellHarvest(Lot tile) {
        double temp = tile.harvest();
        if (temp == 0){
            System.out.println("This plant is too young to harvest");
            return;
        }
        this.objectCoins += temp;
        this.experience += tile.getExperience();
        System.out.println("You have gained " + tile.getExperience() + " experiences");
        registration.levelUp(this.experience);
    }

    public void registration(){
        this.objectCoins = this.registration.initializeRegistration(new FarmerTypeList(), this.objectCoins);
    }

    public int getWaterBonus(){
        return registration.getWaterBonus();
    }

    public int getFertilizerBonus(){
        return registration.getFertilizerBonus();
    }

    public double getBonusEarning(){
        return registration.getBonusEarning();
    }


}