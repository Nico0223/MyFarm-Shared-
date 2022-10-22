public class Seed {
    private final String seedName;
    private String cropType;
    private final int seedIndex;
    private int numWatered = 0;
    private int numFertilized = 0;
    private boolean harvestable = false;
    private boolean withered = false;
    private int harvestTime;
    private final int produce;
    private final int sellingPrice;
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

    public void checkCondition(SeedList seedList){
        if (this.harvestTime >= 0 && !this.withered) {
            if (this.numWatered >= seedList.getWaterNeeds(this.seedIndex)){
                if (this.numFertilized >= seedList.getFertilizerNeeds(this.seedIndex)) {
                    this.harvestable = true;
                }
                else
                    System.out.println("I need nutrients. Gimme " +
                            (seedList.getFertilizerNeeds(this.seedIndex) - this.numFertilized) + " Fertilizers");
            }
            else {
                System.out.println("I need water. Gimme " + (seedList.getWaterNeeds(this.seedIndex) - this.numWatered)
                                    + " waters");
            }
        }
        if (this.harvestTime <= 0){
            if (!harvestable)
                this.withered = true;
            else if (harvestTime < 0)
                this.withered = true;
        }
    }

    public int getTotalPrice(){
        System.out.println("You have produced " + this.produce + " " + this.seedName + "(s)");
        return this.produce * (this.sellingPrice);
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
