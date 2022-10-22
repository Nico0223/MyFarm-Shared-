import java.util.Random;

public class SeedList {
    private final String[] seedName = {"Turnip", "Carrot", "Potato", "Rose", "Turnips", "Sunflower", "Mango", "Apple"};
    private final String[] cropType = {"Root crop", "Root crop", "Root crop", "Flower", "Flower", "Flower",
                                       "Fruit tree", "Fruit tree"};
    private final int[] harvestTime = {2, 3, 5, 1, 2, 3, 10, 10};
    private final int[] waterNeeds = {1, 1, 3, 1, 2, 2, 7, 7};
    private final int[] fertilizerNeeds = {0, 0, 1, 0, 0, 1, 4 , 5};
    private final int[] minProduced = {1, 1, 1, 1, 1, 1, 5, 10};
    private final int[] maxProduced = {2, 2, 10, 1, 1, 1, 15, 15};
    private final int[] seedCost = {5, 10, 20, 5, 10, 20, 100, 200};
    private final double[] sellingPrice = {6, 9, 3, 5, 9, 19, 8, 5};
    private final double[] expGained = {5, 7.5 , 12.5, 2.5, 5, 7.5, 25, 25};

    public int getIndexSeed(String order) {
        int i;

        for (i = 0; i < 8; i++) {
            if (seedName[i].equals(order))
                return i;
        }
        return -1;
    }
    public String getCropType(int index){
        return this.cropType[index];
    }

    public double getCost(int index){
        return this.seedCost[index];
    }
    public double getExp(int index){
        return this.expGained[index];
    }
    public int getProduced(int index){
        Random randomGenerator = new Random();
        if (this.cropType[index].equals("Flower"))
            return this.minProduced[index];
        return randomGenerator.nextInt(this.minProduced[index], this.maxProduced[index]);
    }
    public double getSellingPrice(int index){
        return this.sellingPrice[index];
    }
    public int getWaterNeeds(int index){
        return this.waterNeeds[index];
    }
    public int getFertilizerNeeds(int index){
        return this.fertilizerNeeds[index];
    }
    public int getHarvestTime(int index){
        return this.harvestTime[index];
    }

}
