import java.util.Random;

/**
 * This class represents the seed, which has the name of the seed, type of crop, index of seed retrieved from the
 * seedList class, number of times watered and fertilized, bonus limit of watering and fertilizing the plant,
 * number of produces yielded, amount of time left for a crop to grow, base price of the produce, earning bonus
 * based on the farmer's registration, exp gained after harvest, and the indicators on whether the plant is
 * ready to harvest or withered.
 * <p>
 * The seed object is responsible for simulating its growth along with lot object and
 * computing the total price of the harvest.
 */
public class Seed {
    private final String seedName; // a string variable for the seedName
    private final String cropType; // the type of crop of the seed
    private final int seedIndex; // the index of the seed based on the seedList
    private int numWatered = 0; // number of times the seed/crop was fertilized
    private int waterBonus; // the water bonus based on the farmer registration of the user
    private int numFertilized = 0; // number of times the seed/crop was fertilized
    private int fertilizerBonus; // the fertilizer bonus based from the farmer registration of the user
    private boolean harvestable = false; // the state of the seed/crop on being harvestable default false
    private boolean withered = false; // the state of the seed/crop on being harvestable default false
    private int harvestTime; // harvest time of the seed/crop
    private final int produce; // number of produces by the crop
    private final double sellingPrice; // base selling price of the crop
    private double farmerTypeEarningBonus; // the bonus earnings of the user based on the farmer type
    private final double expGained; // experienced gained from the interaction of the seed/crop

    /** Creates a seed object by retrieving its properties dictated by the seedList class with the ordered seed
     * the player bought.
     *
     * @param order the pending seed ordered
     */
    public Seed(String order){ // a constructor for the class to initializes the interaction of the seed
        this.seedName = order; // the order of the user gets passed to the seedName variable
        SeedList seedList = new SeedList(); // instantiate the seedList object with a SeedList class
        this.seedIndex = seedList.getIndexSeed(order); // getting the index of ordered seed (seedName) from seedList
        // getting the properties of the crop with seedIndex
        this.waterBonus = seedList.getWaterBonus(this.seedIndex);
        this.fertilizerBonus = seedList.getFertilizerBonus(this.seedIndex);
        this.produce = seedList.getProduced(this.seedIndex);
        this.sellingPrice = seedList.getSellingPrice(this.seedIndex);
        this.cropType = seedList.getCropType(this.seedIndex);
        this.expGained = seedList.getExp(this.seedIndex);
        this.harvestTime = seedList.getHarvestTime(this.seedIndex);
    }

    /** A method that shows or gets the name of the seed.
     *
     * @return the name of the seed
     */
    public String showName(){
        return this.seedName;
    }

    public String showType(){
        return this.cropType;
    }

    /** A method that shows or gets the amount of time left until of the
     * crop is ready to harvest.
     *
     * @return the time left until harvest
     */
    public int showHarvestTime(){
        return this.harvestTime;
    }

    /** A method that adds the additional bonus limit based from water, fertilizer, and earnings
     * from the farmer type registration
     *
     * @param player the player object composed of the registration object
     */
    public void addBonus(Player player){
      SeedList seedlist = new SeedList();
      this.waterBonus = seedlist.getWaterBonus(this.seedIndex) + player.getWaterBonus();
      this.fertilizerBonus = seedlist.getFertilizerBonus(this.seedIndex) + player.getFertilizerBonus();
      this.farmerTypeEarningBonus = player.getBonusEarning();
    }

    /** A method that checks whether the crop is ready to harvest based on number of
     * times watered and fertilized, and it becomes withered when the conditions are
     * not met or the crop is past harvest day.
     *
     * @param seedList the seedList object dictating the minimum number of times watered
     *                 and fertilized to be harvestable and avoid becoming withered
     */
    public void checkCondition(SeedList seedList){
        if (this.harvestTime >= 0 && !this.withered) { // if the harvestTime is more than 0 and the crop is not withered
            if (this.numWatered >= seedList.getWaterNeeds(this.seedIndex)){
                // if the number of times watered is at least the WaterNeeds of the seed/crop
                if (this.numFertilized >= seedList.getFertilizerNeeds(this.seedIndex)) {
                    // if the number of times fertilized is at least the FertilizerNeeds of the seed/crop
                    this.harvestable = true; // the crop ready to harvest
                }
            }
            // System.out.println(this.numWatered);
            // System.out.println(this.numFertilized);

            /*if (this.numWatered < seedList.getWaterNeeds(this.seedIndex))
                System.out.println("I need water. Gimme " + (seedList.getWaterNeeds(this.seedIndex) - this.numWatered)
                        + " waters");*/
            if (this.numWatered >= this.waterBonus){
                // Number of times fertilized can be increased until it reaches bonus limit of the crop
                System.out.println("Enough enough, I am refreshed already!");
                this.numWatered = this.waterBonus;
            }
            /*if (this.numFertilized < seedList.getFertilizerNeeds(this.seedIndex)) // if the number of times watered is less than the WaterNeeds of the seed/crop
                System.out.println("I need nutrients. Gimme " +
                        (seedList.getFertilizerNeeds(this.seedIndex) - this.numFertilized) + " Fertilizers");*/
            if (this.numFertilized >= this.fertilizerBonus){
                // Number of times fertilized can be increased until it reaches bonus limit of the crop
                System.out.println("Enough, enough, I am strong and healthy now!");
                this.numFertilized = this.fertilizerBonus;
                //Comment these out if the gui is made
            }
            System.out.println(this.showWaterProgress(seedList));
            System.out.println(this.showFertilizerProgress(seedList));
        }
        if (this.harvestTime <= 0){ // if the harvestTime is less than or equal 0
            if (!harvestable) // if the crop is not harvestable
                this.withered = true; // the crop will be withered
            else if (harvestTime < 0) // if the harvestTime is less than -
                this.withered = true; // the crop will be withered
        }
    }

    /** A method that displays whether the number of times watered reaches the required amount,
     * and it can exceed to the water bonus limit.
     *
     * @param seedList the seedList object dictating the minimum number of times watered
     *                 and fertilized to be harvestable and avoid becoming withered
     * @return the string of the water progress
     */
    public String showWaterProgress(SeedList seedList){
        int waterNeeds = seedList.getWaterNeeds(this.seedIndex);
        String string = "Times watered: " + this.numWatered + "/" + waterNeeds;
        if (this.numWatered >= waterNeeds){
            if (this.numWatered < this.waterBonus)
                return string.concat(" :)");
            else if (this.numWatered == this.waterBonus)
                return string.concat("*");
        }
        return string;
    }

    /** A method that displays whether the number of times fertilized reaches the required amount,
     * and it can exceed to the fertilize bonus limit.
     *
     * @param seedList the seedList object dictating the minimum number of times
     *                 fertilized to be harvestable and avoid becoming withered
     * @return the string of fertilizer progress
     */
    public String showFertilizerProgress(SeedList seedList){
        int fertilizerNeeds = seedList.getFertilizerNeeds(this.seedIndex);
        String string = "Times fertilized: " + this.numFertilized + "/" + fertilizerNeeds;
        if (this.numFertilized >= fertilizerNeeds){
            if (this.numFertilized < this.fertilizerBonus)
                return string.concat(" :)");
            else if (this.numFertilized == this.fertilizerBonus)
                return string.concat("*");
        }
        return  string;
    }

    /** A method that computes for the total price of harvested crops to be sold
     *
     * @return the total price of the harvests
     */
    public double getTotalPrice(){
        // variables for the total number of crops harvested, water and fertilizer bonus and total price.
        double harvestTotal, waterBonus, fertilizerBonus, finalHarvestPrice;
        System.out.println("You have produced " + this.produce + " " + this.seedName + "(s)");

        // harvestTotal is computed by the number of produces multiplied to sum of selling price and earning bonus
        harvestTotal = this.produce * (this.sellingPrice + this.farmerTypeEarningBonus);

        // the waterBonus is computed by 20% of harvestTotal multiplied by one less the number of times watered
        waterBonus = harvestTotal * 0.2  * (this.numWatered - 1);

        // the fertilizerBonus is computed by 20% of harvestTotal multiplied by the number of times fertilized
        fertilizerBonus = harvestTotal * 0.5 * this.numFertilized;

        // the totalPrice is computed by the sum of harvestTotal, waterBonus and fertilizerBonus
        finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;

        if (this.cropType.equals("Flower")){ // if the cropType is a "Flower"
            finalHarvestPrice *= 1.1; // total price gets multiplied to 1.1
        }
        new Prompt("You have produced " + this.produce + " " + this.seedName + "(s)\n" + "The total price of " +
                this.seedName + " is " + finalHarvestPrice);
        System.out.println("The total price of " + this.seedName + " is " + finalHarvestPrice);
        return finalHarvestPrice;

    }

    /** A getter method for the experiences gained from harvesting the crop.
     *
     * @return the experiences
     */
    public double getExpGained(){ // a getter for the experience gained from the crop
        return this.expGained;
    }

    /** A method that simulates the growing of the crop, decrementing the amount of time
     * left to be by 1 each day.
     */
    public void grow(){
        this.harvestTime--;
    }

    /** A setter method that adds the amount of water to the seed/crop
     */
    public void addWater(){
        this.numWatered++;
    }

    /** A setter method that adds the amount of fertilizer to the seed/crop
     */
    public void addFertilizer(){ // initializes adding fertilizer to the seed/crop
        this.numFertilized++;
    }

    /** A method that determines whether the crop is withered.
     *
     * @return the boolean of whether the crop is withered
     */
    public boolean isWithered(){ // initializes that the seed/crop will become withered
        return this.withered;
    }

    /** A method that determines whether the crop is ready to harvest
     *
     * @return the boolean of whether the crop is harvestable
     */
    public boolean isHarvestable(){ // initializes that the seed/crop will become harvestable
        return  this.harvestable;
    }

    public static void main(String[] args){
        Seed seed;
        for (int i = 0; i < 50; i++){
            /*seed = new Seed("Tulips");
            System.out.println(seed.getTotalPrice());*/
            Random random = new Random();
            System.out.println(random.nextInt());
        }

    }

}
