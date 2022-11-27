import java.util.*;

/**
 * This class represents the player that can do various tasks within the farm, which includes buying/selling seeds,
 * using tools like pickaxe and shovel to clear a tile, getting farmer registration, plowing, planting, fertilizing,
 * and watering plants. It has objectCoins which is referred to the currency used to purchase tools, seeds, and
 * registration, experience, tool which is associated to the Tools class, registration which is associated with the
 * Registration class, order which is referred to the input of action/request the player wants to perform/have, and
 * gameEnd which determines whether the game ends when the conditions are met.
 */
public class Player {
    private double objectCoins = 1000; // default objectCoins are set to 100
    private double experience = 1000; // default experience is set to 0
    private Tools tool = null; // default tool being used is "none"
    private final Registration registration = new Registration(); // default registration is set to 0
    private String order; // the order of the farmer
    private Boolean gameEnd = false; // the gameEnd is set to false as default

    /** A method that returns the value of gameEnd to terminate the loop from the GUI.
     *
     * @return the value of gameEnd which is either true or false
     */
    public Boolean getGameEnd() { //  returns the value of gameEnd
		return gameEnd;
	}

    /** A method sets the value of gameEnd to true to end the game by terminating
     * the loop from the GUI.
     */
    public void setGameEnd() { // sets the value of gameEnd
		this.gameEnd = true;
	}

    /** A method that asks the player whether he wants to try again after he lost the game.
     *
     * @return the boolean value
     */
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

    // showing interface for MCO1
    private boolean flag = true;
    private Seed seed = new Seed("Turnip");
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
        if (flag){
            tile.plantSeed(seed);
            flag = false;
        }

    }
    public void setOrder(String order){
        this.order = order;
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

    /** A method that initializes buying the tool that the user orders.
     *
     * @param orderTool where the transaction of buying tools is being held
     * @return 1 if the transaction is successful and -1 if the transaction is failed
     */
    public int buyTool(PurchaseTool orderTool) {
        ToolList orderList = new ToolList(); // Instantiate the orderList object with a ToolList class
        orderTool.initializeOrder(orderList, this.order);

        if (this.objectCoins < orderTool.getCost()){ // an if-statement to indicate if the objectCoins is less than the
            // cost of the selected tool
            new Prompt("Hah, you broke!!");
            System.out.println("Hah, you broke!!");
            return -1; 
        }
        objectCoins = objectCoins - orderTool.getCost() + registration.getCostReduction(); // objectCoins gets
        // subtracted to the cost of the orderTool
        experience = experience + orderTool.getExp(); // user gains experience based on the orderTool
        return 1;
    }

    /** A method that allows the player to equip the tool.
     *
     * @param tool the tool that the player will use
     */
    public void equipTool(Tools tool){ //
        this.tool = tool;
    }

    /** A method that would change the state of the selected tile with an equipped tool.
     *
     * @param tile selected tile
     */
    public void useTool(Lot tile){
        switch (this.tool.showTool()) {
            case "Plow" -> {
                if (tile.showState().equals("Unplowed"))
                    tile.plowTile(); // If using the "Plow" tool, it would plow the tile
                else
                    this.experience -= 0.5;
            }
            case "Watering can" -> { // if using the "Watering Can" tool
                if (tile.getCrop() == null){ // if no crop is currently planted in the tile
                    System.out.println("Dude, plant the tile first!"); //legacy code for mco1
                }
                else // if a crop has been planted
                    tile.waterPlant();
            }
            case "Fertilizer" -> { //if using the "Fertilizer" tool
                if (tile.getCrop() == null){ // if there is no plant in the current tile
                    System.out.println("Dude, plant the tile first!"); //legacy code for mco1
                    this.objectCoins += 10; // refund process
                    this.experience -= 4; // refund process
                }
               else
                    tile.fertilizePlant(); // tile gets fertilized
            }
            case "Pickaxe" -> { // If using the "Pickaxe" tool
               tile.mineRock();
            }
            case "Shovel" -> tile.shovelTile(); // If using the "Shovel" tool
            default -> System.out.println("What the hell is this?!\n"); //legacy line of code from MCO1
        }
        registration.levelUp(this.experience);
        tool = null; // tool gets removed and set to null after use
    }

    /** A method that  would initialize the user's order for buying seeds.
     *
     * @param purchaseSeed where a transaction of buying seed is held
     * @return -1 if the transaction fails and i if the transaction is successful
     */
    public int buySeed(PurchaseSeed purchaseSeed)  {
        SeedList seedList = new SeedList(); // initialize the seedList object with the SeedList class
        int i = purchaseSeed.initializeOrder(seedList, this.order); // the variable "i" gets the index of the seedList
        if (i != -1){ // if the index was found
            if (this.objectCoins < purchaseSeed.getCost()){
                new Prompt("Hah, you broke!!");
                System.out.println("Hah, you broke!!"); // if the objectCoins is less than the cost of the purchaseSeed
                return -1;
            }
            this.objectCoins -= purchaseSeed.getCost(); // objectCoins gets subtracted by the cost of the purchaseSeed
        }
        return i;
    }

    /** A method that initializes the player selling the harvested crop on the tile.
     *
     * @param tile selected tile
     */
    public void sellHarvest(Lot tile) {
        double temp = tile.harvest(); // passes the value of the price of the harvested crop
        if (temp == 0){ // if temp is 0 or there is no value on the price of the harvested price
            new Prompt("This plant is too young to harvest");
            System.out.println("This plant is too young to harvest");
            return;
        }
        this.objectCoins += temp; // objectCoins gets added by temp
        this.experience += tile.getExperience(); // experience gets added by the experience of harvesting the tile
        System.out.println("You have gained " + tile.getExperience() + " experiences");
        registration.levelUp(this.experience);
    }

    /** A method that registers the user for the succeeding registration and the objectCoins will be updated.
     *
     */
    public void register(){ //
        this.objectCoins = this.registration.initializeRegistration(new FarmerTypeList(), this.objectCoins);
    }

    /** A getter method for the waterBonus from the registration object.
     *
     * @return the extension of water bonus limit for seed object
     */
    public int getWaterBonus(){
        return registration.getWaterBonus();
    }

    /** A getter method for the fertilizerBonus from the registration object.
     *
     * @return the extension of fertilizer bonus limit for seed object
     */
    public int getFertilizerBonus(){
        return registration.getFertilizerBonus();
    }

    /** A getter method for the bonusEarning from the registration object.
     *
     * @return the bonus earnings of selling the harvest
     */
    public double getBonusEarning(){
        return registration.getBonusEarning();
    }

    /** A getter method for the farmerType from the registration object.
     *
     * @return the farmer type of the player
     */
    public String getFarmerType() {
        return this.registration.showRegistration();
    }

    /** A getter method for the level from the registration object.
     *
     * @return the level of the player
     */
    public int getLevel() {
        return this.registration.showLevel();
    }

    /** A getter method for the experience from this class
     *
     * @return the experience of the player
     */
    public double getExperience(){
        return this.experience;
    }

    /** A getter method for the objectCoins from this class
     *
     * @return the objectCoin of the player
     */
    public double getObjectCoin(){
        return this.objectCoins;
    }

    //Test code in Player class
    public static void main(String[] args) {
        Lot[][] farm = new Lot[5][10];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                farm[i][j] = new Lot();
            }
        }
        Player player = new Player();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                player.displayInterface(farm[i][j]);
            }
        }
    }


}