import java.util.*;
// This class initializes the player in doing various tasks within the farm, which includes buying/selling seeds, using tools,  getting farmer registration, plowing, planting, fertilizing, and watering plants
public class Player {
    private double objectCoins = 100; // default objectCoins are set to 100
    private double experience = 0; // default experience is set to 0
    private Tools tool = null; // default tool being used is "none"
    private final Registration registration = new Registration(); // default registration is set to 0
    private String order; // the order of the farmer
    private Boolean gameEnd = false; // the gameEnd is set to false as default


    public Boolean getGameEnd() { //  returns the value of gameEnd
		return gameEnd;
	}

    public void setGameEnd() { // sets the value of gameEnd
		this.gameEnd = true;
	}

    public boolean checkGame(){
        if (this.gameEnd){
            System.out.println("Congratulations, you lost the game!");
            while (true){
                System.out.print("Try again? ");
                Scanner sc = new Scanner(System.in);
                String answer = sc.nextLine();
                switch (answer){
                    case "yes" ->{
                        this.gameEnd = false;
                        return true;
                    }
                    case "no" -> {
                        return false;
                    }
                }
            }

        }
        return false;
    }

	public void displayInterface(Lot tile){ //this method displays the farm information in terms of objectCoins, experience and Registration level and the farm tiles.
        System.out.println("ObjectCoins: " + this.objectCoins);
        System.out.println("Experience: " + this.experience);
        System.out.println("Level: " + this.registration.showLevel());
        System.out.println("Type: " + this.registration.showRegistration());
        System.out.println("Tile State: " + tile.showState());
        if (this.objectCoins < 5 && tile.getCrop() == null){
            System.out.println("You have filed Chapter 11 Bankruptcy"); // If the farmer has less than 5 objectCoins and no crops on the tile
            this.gameEnd = true; // gameEnd will be set to "true"
        }
        if (tile.getCrop() != null){ // if there is a crop on the tile
            tile.updateBonus(this); // adds bonus to the tile
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


    public int buyTool(PurchaseTool orderTool) { // this method is initializes buying the tool that the user orders
        ToolList orderList = new ToolList(); // Instantiate the orderList object with a ToolList class
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

    public void useTool(Lot tile){ // this method would change the state of the selected tile with an equipped tool
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
                    tile.fertilizePlant(); // tile gets fertilized
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
    public void sellHarvest(Lot tile) { // initializes the player selling the harvested crop on the tile
        double temp = tile.harvest(); // passes the value of the price of the harvested crop
        if (temp == 0){ // if temp is 0 or there is no value on the price of the harvested price
            System.out.println("This plant is too young to harvest");
            return;
        }
        this.objectCoins += temp; // objectCoins gets added by temp
        this.experience += tile.getExperience(); // experience gets added by the experience of harvesting the tile
        System.out.println("You have gained " + tile.getExperience() + " experiences");
        registration.levelUp(this.experience);
    }

    public void register(){ // registers the user for the succeeding registration, the objectCoins will be updated
        this.objectCoins = this.registration.initializeRegistration(new FarmerTypeList(), this.objectCoins);
    }

    public int getWaterBonus(){ // a getter for the WaterBonus
        return registration.getWaterBonus();
    }

    public int getFertilizerBonus(){ // a getter for the FertilizerBonus
        return registration.getFertilizerBonus();
    }

    public double getBonusEarning(){ // a getter for the BonusEarning
        return registration.getBonusEarning();
    }


}