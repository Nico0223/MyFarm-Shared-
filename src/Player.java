import java.util.*;

public class Player {
    private double objectCoins = 100; // Lines 4-9, initialize variables for the "Player" Class
    private double experience = 90;
    private Tools tool = null;
    private Registration registration = new Registration();
    private String order;
    private Boolean gameEnd;

    public double getObjectCoins() { // returns the value of objectCoins
		return objectCoins;
	}

	public void setObjectCoins(double objectCoins) { // sets the value of objectCoins
		this.objectCoins = objectCoins;
	}

	public double getExperience() { // returns the value of experience
		return experience;
	}

	public void setExperience(double experience) { // sets the value of experience
		this.experience = experience;
	}

	public Tools getTool() { // returns the value of tool
		return tool;
	}

	public void setTool(Tools tool) { // sets the value of tool
		this.tool = tool;
	}

	public String getOrder() { // returns the value of order
		return order;
	}

	public void setOrder(String order) { //  sets the value of experience
		this.order = order;
	}

	public Boolean getGameEnd() { //  returns the value of gameEnd
		return gameEnd;
	}

	public void setGameEnd(Boolean gameEnd) { // sets the value of gameEnd
		this.gameEnd = gameEnd;
	}

	public void displayInterface(Lot tile){ //this method displays the farm information in terms of objectCoins, experience and Registration level and the farm tiles.
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

    public String inputPlayer(){ // asks input from the user for their order
        Scanner sc = new Scanner(System.in);
        System.out.print("\nWhat do you order? ");
        this.order = sc.nextLine();
        System.out.println("You ordered " + order + ".");
        //sc.close();
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


    public int buyTool(PurchaseTool orderTool) { // this method is initialize buying the tool that the user orders
        ToolList orderList = new ToolList(); // Instantiate the orderlist object with a ToolList class
        orderTool.initializeOrder(orderList, this.order);
        if (this.objectCoins < orderTool.getCost()){ // an if-statement to indicate if the objectCoins is less than the cost of the selected tool
            System.out.println("Hah, you broke!!");
            return -1; 
        }
        objectCoins = objectCoins - orderTool.getCost(); // objectCoins gets subtracted to the cost of the orderTool
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
    public void sellHarvest(Lot tile) { // This method initializes on selling the harvested crops
        this.experience += tile.getExperience(); // experience gets added by the experiencegained from the harvest tile
        this.objectCoins += tile.harvest(); // objectCoins gets added by the objectCoins from the harvest tile
    }
    public void registration(){ // This method initializes showing the information on current farmer registration
        this.objectCoins = this.registration.initializeRegistration(new FarmerTypeList(), this.objectCoins);
    }
    public Registration showRegistration(){ // returns the value of registration
      return this.registration;
    }
    public int getWaterBonus(){ // returns the value of WaterBonus from the registration object
        return this.registration.getWaterBonus();
    }

    public int getFertilizerBonus(){ //  returns te value of the FertilizerBonus from registration object
        return this.registration.getFertilizerBonus();
    }
    public double getBonusEarning(){ // returns the value of the BonusEarning from the registration object
      return this.registration.getBonusEarning();
    }
    public void updateLevel(){
      this.registration.levelUp(this.experience);
    }
}