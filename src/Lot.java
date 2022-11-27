/**
 * This class represents the lot which contains the state of the tile, a seed object, and experience
 * that will be given to the player if the crop is harvested.
 */
public class Lot {
  // Lines 5-8, initialize variables for the "Lot" object
    private String state = "Unplowed";
    private Seed crop = null;
    private double experience;


    public void generateRock(boolean hasRock){
        this.state = "Rock";
    }
    /** A method that changes the state of the tile to plowed.
     */
    public void plowTile(){
        if (this.state.equals("Unplowed")){
            this.state = "Plowed";
            System.out.println("hi");
        }
        System.out.println("hi");
    }

    /** A method that changes the state of the tile based on the seed the player planted.
     *
     * @param seed the seed object the player bought and planted
     */
    public void plantSeed(Seed seed){ // This method initializes for planting the seed
        this.crop = seed; // the seed will then become a "crop"
        experience = this.crop.getExpGained(); // user gains experience based on the crop planted
        this.state = this.crop.showName() + " - " + this.crop.showHarvestTime() + " days left"; // informs the user on the state of the tile by crop name and days left to harvest
        crop.checkCondition(new SeedList()); // checks the condition of the seed whether it has been watered and fertilized
    }

    /** A method that simulates the growth of the plant by advancing the day.
     * If the player is does not take care of the plant properly, the crop will wither.
     */
    public void leaveLot(){ // This method initializes to advance the day of the farm
        crop.grow();
        crop.checkCondition(new SeedList()); 
        if (this.crop.showHarvestTime() == 0 && crop.isHarvestable()) // if the harvestTime is set to 0 and the crop is harvestable
            this.state = "Ready to harvest";
        else if (this.crop.isWithered()){// if crop is now withered
            this.state = "Withered";
            this.crop = null;
        }

        else
            this.state = this.crop.showName() + " - " + this.crop.showHarvestTime() + " days left"; // shows the state of the crop at the advancing day
    }

    /** A method the simulates the player watering the plant.
     */
    public void waterPlant(){
        System.out.println("Watered");//debug code
        this.crop.addWater(); // the crop's condition is now added as "watered"
        this.crop.checkCondition(new SeedList()); // updates condition of the crop
    }

    /** A method the simulates the player fertilizing the plant.
     */
    public void fertilizePlant(){ // this method initializes the user on fertilizing the plant
        System.out.println("Fertilized"); //debug code
        this.crop.addFertilizer(); // the crop's condition is now added as "fertilized"
        this.crop.checkCondition(new SeedList()); // updates the condition of the crop
    }

    /** A method the simulates the player mining the rock from the tile.
     */
    public void mineRock(){
        this.state = "Unplowed";
    }

    /** A method the simulates the player shoveling the tile.
     */
    public void shovelTile(){ // this method initializes the user on using a shovel on the tile
        //legacy code from MCO1
        /*if (this.state.equals("Unplowed")) // if the tile is already "unplowed"
            System.out.println("Bruh");*/

        this.state = "Unplowed"; // state of the plow is now "unplowed"
        this.crop = null; // removes a crop from the tile
    }

    /** An intermediate method between the player object and seed object that
     * passes the bonus values to the seed object.
     *
     * @param player the player object composed of the registration object
     */
    public void updateBonus(Player player){
        crop.addBonus(player);
    }

    /** A method that simulates the player harvesting the crop.
     *
     * @return total price of the harvests
     */
    public double harvest(){ // this method initializes for the user on harvesting the crop
        if (this.state.equals("Ready to harvest")){ // if the state of the tile is considered ready to harvest
            double total = this.crop.getTotalPrice(); // the variable total gets the total price of the crops harvested
            System.out.println("Earned " + total + " objectCoins"); // shows the user how much objectCoins acquired
            this.crop = null; // no crop will be planted
            this.state = "Unplowed"; // the state of the tile is now "unplowed"
            return total; // returns total objectCoins acquired
        }

        return 0;
    }

    /** A getter method that returns the experiences the player will earn
     * from harvesting the crop.
     *
     * @return the experiences
     */
    public double getExperience(){ // returns the value of experience
        return this.experience;
    }

    /** A method that returns the state of the tile
     *
     * @return the state of the tile
     */
    public String showState(){
        return this.state;
    }

    /** A method that returns the seed object
     *
     * @return the seed object
     */
    public Seed getCrop(){
        return this.crop;
    }


}
