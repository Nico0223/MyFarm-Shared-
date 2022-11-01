public class Seed { // The class initializes the user on interacting with the seed whether to plant, water, harvest and sell the seed/crop
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
    private final double sellingPrice; // selling price of a single crop
    private double farmerTypeEarningBonus; // the bonus earnings of the user based on the farmer type
    private final double expGained; // experienced gained from the interaction of the seed/crop

    public Seed(String order){ // a constructor for the class to initializes the interaction of the seed
        this.seedName = order; // the order of the user gets passed to the seedName variable
        SeedList seedList = new SeedList(); // instantiate the seedList object with a SeedList class
        this.seedIndex = seedList.getIndexSeed(order); // the index of the seed based from the seedList object gets passed to the seedIndex variable. (Same scenario can be said for lines 21-24)
        this.produce = seedList.getProduced(this.seedIndex);
        this.sellingPrice = seedList.getSellingPrice(this.seedIndex);
        this.cropType = seedList.getCropType(this.seedIndex);
        this.expGained = seedList.getExp(this.seedIndex);
        this.harvestTime = seedList.getHarvestTime(this.seedIndex);
    }
    public String showName(){ // shows or gets the name of the seedName
        return this.seedName;
    }
    public int showHarvestTime(){ // shows or gets the harvestTime of the crop/seed
        return this.harvestTime;
    }
    public void addBonus(Player player){ // this method initializes on the adding of bonus based from water, fertilizer, earnings from the farmer type registration
      this.waterBonus = player.getWaterBonus();
      this.fertilizerBonus = player.getFertilizerBonus();
      this.farmerTypeEarningBonus = player.getBonusEarning();
    }

    public void checkCondition(SeedList seedList){ // this method checks the condition of the seed based on number of times watered and fertilized and conditions to become harvestable or withered
        if (this.harvestTime >= 0 && !this.withered) { // if the harvestTime is more than 0 and the crop is not withered
            if (this.numWatered >= seedList.getWaterNeeds(this.seedIndex)){ // if the number of times watered is greater than or equal to the WaterNeeds of the seed/crop
                if (this.numFertilized >= seedList.getFertilizerNeeds(this.seedIndex)) { // if the number of times fertilized is greater than or equal to the FertilizerNeeds of the seed/crop
                    this.harvestable = true; // the state of the seed/crop will become true
                }
            }

            if (this.numWatered < seedList.getWaterNeeds(this.seedIndex)) // if the number of times watered is less than the WaterNeeds of the seed/crop
                System.out.println("I need water. Gimme " + (seedList.getWaterNeeds(this.seedIndex) - this.numWatered)
                        + " waters");
            else if (this.numWatered >= seedList.getWaterNeeds(this.seedIndex) + this.waterBonus){ // if the number of times watered is greater than or equal to the WaterNeeds of the seed/crop
                System.out.println("Enough enough, I am refreshed already!");
                this.numWatered = seedList.getWaterNeeds(this.seedIndex) + this.waterBonus;
            } // the total number times watered is computed by the water needs of the seed/crop plus the water bonus from the farmer registration

            if (this.numFertilized < seedList.getFertilizerNeeds(this.seedIndex)) // if the number of times watered is less than the WaterNeeds of the seed/crop
                System.out.println("I need nutrients. Gimme " +
                        (seedList.getFertilizerNeeds(this.seedIndex) - this.numFertilized) + " Fertilizers");
            else if (this.numFertilized >= seedList.getFertilizerNeeds(this.seedIndex) + this.fertilizerBonus){ // if the number of times watered is greater than or equal to the WaterNeeds of the seed/crop
                System.out.println("Enough, enough, I am strong and healthy now!");
                this.numFertilized = seedList.getFertilizerNeeds(this.seedIndex); // the total number times fertilized is computed by the fertilizer needs of the seed/crop plus the fertilizer bonus from the farmer registration
            }
        }
        if (this.harvestTime <= 0){ // if the harvestTime is less than or equal 0
            if (!harvestable) // if the crop is not harvestable
                this.withered = true; // the crop will be withered
            else if (harvestTime < 0) // if the harvestTime is less than -
                this.withered = true; // the crop will be withered
        }
    }

    public double getTotalPrice(){ // computes for the total price from harvesting and selling the seed/crop
        double harvestTotal, waterBonus, fertilizerBonus, finalHarvestPrice; // variables for the total number of crops harvested, water and fertilizer bonus and total price.
        System.out.println("You have produced " + this.produce + " " + this.seedName + "(s)");
        harvestTotal = this.produce * (this.sellingPrice + this.farmerTypeEarningBonus); // harvestTotal gets computed by the number of products produced multiplied to sum of selling price and earning bonus
        waterBonus = harvestTotal * 0.2  * (this.numWatered - 1); // the water bonus gets computed by the harvest total multiply to 0.2 multiplied to one less the number of times watered
        fertilizerBonus = harvestTotal * 0.5 * this.numFertilized; // the fertilizerBonus is computed by the harvestTotal multiplied to 0.5 multiplied to number of times fertilized
        finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus; // the totalPrice is computed by the sum of harvestTotal, waterBonus and fertilizerBonus
        if (this.cropType.equals("Flower")){ // if the cropType is a "Flower"
            finalHarvestPrice *= 1.1; // total price gets multiplied to 1.1
        }
        System.out.println("The total price of " + this.seedName + " is " + finalHarvestPrice);
        return finalHarvestPrice;

    }
    public double getExpGained(){ // a getter for the experience gained from the crop
        return this.expGained;
    }
    public void grow(){ // initializes the growing the crop, decrementing the harvestTime by 1
        this.harvestTime--;
    }
    public void addWater(){ // initializes on adding water to the seed/crop
        this.numWatered++;
    }
    public void addFertilizer(){ // initializes adding fertilizer to the seed/crop
        this.numFertilized++;
    }
    public boolean isWithered(){ // initializes that the seed/crop will become withered
        return this.withered;
    }
    public boolean isHarvestable(){ // initializes that the seed/crop will become harvestable
        return  this.harvestable;
    }


}
