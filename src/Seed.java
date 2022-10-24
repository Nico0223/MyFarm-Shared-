public class Seed {
    private final String seedName;
    private final String cropType;
    private final int seedIndex;
    private int numWatered = 0;
    private int waterBonus;
    private int numFertilized = 0;
    private int fertilizerBonus;
    private boolean harvestable = false;
    private boolean withered = false;
    private int harvestTime;
    private final int produce;
    private final double sellingPrice;
    private double farmerTypeEarningBonus;
    private final double expGained;

    public Seed(String order){
        this.seedName = order;
        SeedList seedList = new SeedList();
        this.seedIndex = seedList.getIndexSeed(order);
        this.produce = seedList.getProduced(this.seedIndex);
        this.sellingPrice = seedList.getSellingPrice(this.seedIndex);
        this.cropType = seedList.getCropType(this.seedIndex);
        this.expGained = seedList.getExp(this.seedIndex);
        this.harvestTime = seedList.getHarvestTime(this.seedIndex);
    }
    public String showName(){
        return this.seedName;
    }
    public int showHarvestTime(){
        return this.harvestTime;
    }
    public void addBonus(Player player){
      this.waterBonus = player.getWaterBonus();
      this.fertilizerBonus = player.getFertilizerBonus();
      this.farmerTypeEarningBonus = player.getBonusEarning();
    }

    public void checkCondition(SeedList seedList){
        if (this.harvestTime >= 0 && !this.withered) {
            if (this.numWatered >= seedList.getWaterNeeds(this.seedIndex)){
                if (this.numFertilized >= seedList.getFertilizerNeeds(this.seedIndex)) {
                    this.harvestable = true;
                    if (this.numWatered >= seedList.getWaterNeeds(this.seedIndex) + this.waterBonus){
                      System.out.println("Enough enough, I am refreshed already!");
                      this.numWatered = seedList.getWaterNeeds(this.seedIndex) + this.waterBonus;
                    }
                    if (this.numFertilized >= seedList.getFertilizerNeeds(this.seedIndex) + this.fertilizerBonus){
                      System.out.println("Enough, enough, I am strong and healthy now!");
                      this.numFertilized = seedList.getFertilizerNeeds(this.seedIndex);
                    }
                }
            }
            if (this.numWatered < seedList.getWaterNeeds(this.seedIndex))
                System.out.println("I need water. Gimme " + (seedList.getWaterNeeds(this.seedIndex) - this.numWatered)
                        + " waters");
            if (this.numFertilized < seedList.getFertilizerNeeds(this.seedIndex))
                System.out.println("I need nutrients. Gimme " +
                        (seedList.getFertilizerNeeds(this.seedIndex) - this.numFertilized) + " Fertilizers");
        }
        if (this.harvestTime <= 0){
            if (!harvestable)
                this.withered = true;
            else if (harvestTime < 0)
                this.withered = true;
        }
    }

    public double getTotalPrice(){
        double harvestTotal, waterBonus, fertilizerBonus, finalHarvestPrice;
        System.out.println("You have produced " + this.produce + " " + this.seedName + "(s)");
        harvestTotal = this.produce * (this.sellingPrice + this.farmerTypeEarningBonus);
        waterBonus = harvestTotal * 0.2  * (this.numWatered - 1);
        fertilizerBonus = harvestTotal * 0.5 * this.numFertilized;
        finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;
        if (this.cropType.equals("Flower")){
            finalHarvestPrice *= 1.1;
        }
        System.out.println("The total price of " + this.seedName + " is " + finalHarvestPrice);
        return finalHarvestPrice;

    }
    public double getExpGained(){
        return this.expGained;
    }
    public void grow(){
        this.harvestTime--;
    }
    public void addWater(){
        this.numWatered++;
    }
    public void addFertilizer(){
        this.numFertilized++;
    }
    public boolean isWithered(){
        return this.withered;
    }
    public boolean isHarvestable(){
        return  this.harvestable;
    }


}
