
public class Lot {
    //private int tile;
  // Lines 5-8, initialize variables for the "Lot" class
    private String state = "Unplowed";
    //private boolean rock;
    private Seed crop = null;
    private double experience;

    public void plowTile(){ // This method initializes plowing the tile
        if (this.state.equals("Unplowed"))
            this.state = "Plowed";
    }
    public void plantSeed(Seed seed){ // This method initializes for planting the seed
        this.crop = seed; // the seed will then become a "crop"
        experience = this.crop.getExpGained(); // user gains experience based on the crop planted
        this.state = this.crop.showName() + " - " + this.crop.showHarvestTime() + " days left"; // informs the user on the state of the tile by crop name and days left to harvest
        crop.checkCondition(new SeedList()); // checks the condition of the seed whether it has been watered and fertilized
    }
    public void leaveLot(){ // This method initializes to advance the day of the farm
        crop.grow();
        crop.checkCondition(new SeedList()); 
        if (this.crop.showHarvestTime() == 0 && crop.isHarvestable()) // if the harvestime is set to 0 and the crop is harvestable
            this.state = "Ready to harvest";
        else if (this.crop.isWithered()) // if crop is now withered
            this.state = "Withered";
        else
            this.state = this.crop.showName() + " - " + this.crop.showHarvestTime() + " days left"; // shows the state of the crop at the advancing day
    }
    public void waterPlant(){ // this method intitializes the user on watering the plant
        System.out.println("Watered");
        this.crop.addWater(); // the crop's condition is now added as "watered"
        this.crop.checkCondition(new SeedList()); // updates condition of the crop
    }
    public void fertilizePlant(){ // this method initializes the user on fertilizing the plant
        System.out.println("Fertilized");
        this.crop.addFertilizer(); // the crop's condition is now added as "fertilized"
        this.crop.checkCondition(new SeedList()); // updates the condition of the crop
    }
    /*public void mineRock(){
        this.state = "Unplowed";
    }*/
    public void shovelTile(){ // this method initializes the user on using a shovel on the tile
        if (this.state.equals("Unplowed")) // if the tile is already"unplowed"
            System.out.println("Bruh");

        this.state = "Unplowed"; // state of the plow is now "unplowed"
        this.crop = null; // there is no longer a crop on the tile
    }

    public void updateBonus(Player player){
        crop.addBonus(player);
    }
    public double harvest(){ // this method initializes for the user on harvesting the crop
        if (this.state.equals("Ready to harvest")){ // if the state of the tile is considered ready to harvest
            double total = this.crop.getTotalPrice(); // the variable total gets the total price of the crops harvested
            System.out.println("Earned " + total + " objectcoins"); // shows the user how much objectCoins acquired
            this.crop = null; // no crop will be planted
            this.state = "Unplowed"; // the state of the tile is now "unplowed"
            return total; // returns total objectCoins acquired
        }

        if (this.state.equals("Withered")) // if the state of the tile is "withered"
            System.out.println("Use the shovel to remove the withered plant"); // tells the user to use a shover to remove the plant
        else
            System.out.println("If you see this, there is a bug in my pc");
        return 0;
    }
    public double getExperience(){ // returns the value of experience
        return this.experience;
    }

    public String showState(){ // returns the value on state of the tile
        return this.state;
    }
    public Seed getCrop(){ // returns the value on the crop planted on the tile
        return this.crop;
    }



}
