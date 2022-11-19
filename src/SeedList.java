import java.util.Random;

/**
 *
 */
public class SeedList { // The class contains the list of seeds that the farmer/user can plant
    // name of the seeds
    private final String[] seedName = {"Turnip", "Carrot", "Potato", "Rose", "Tulips", "Sunflower", "Mango", "Apple"};
    // properties of seeds that corresponds with the seedName array
    private final String[] cropType = {"Root crop", "Root crop", "Root crop", "Flower", "Flower", "Flower",
                                       "Fruit tree", "Fruit tree"};
    private final int[] harvestTime = {2, 3, 5, 1, 2, 3, 10, 10};
    private final int[] waterNeeds = {1, 1, 3, 1, 2, 2, 7, 7};
    private final int[] waterBonus = {2, 2, 4, 2, 3, 3, 7, 7};
    private final int[] fertilizerNeeds = {0, 0, 1, 0, 0, 1, 4 , 5};
    private final int[] fertilizerBonus = {1, 1, 2, 1, 1, 2, 4, 5};
    private final int[] minProduced = {1, 1, 1, 1, 1, 1, 5, 10};
    private final int[] maxProduced = {2, 2, 10, 1, 1, 1, 15, 15};
    private final int[] seedCost = {5, 10, 20, 5, 10, 20, 100, 200};
    private final double[] sellingPrice = {6, 9, 3, 5, 9, 19, 8, 5};
    private final double[] expGained = {5, 7.5 , 12.5, 2.5, 5, 7.5, 25, 25};

    public int getIndexSeed(String order){ // gets the index of the seed based on the user's order
        int i;

        for (i = 0; i < 8; i++) { // uses linear search to find the seed
            if (seedName[i].equals(order))
                return i;
        }
        return -1;
    }
    public String getCropType(int index){ // a getter for the crop type
        return this.cropType[index];
    }

    public double getCost(int index){ // a getter for the cost of the seed/crop
        return this.seedCost[index];
    }
    public double getExp(int index){ // a getter for the experience gained from the seed/crop
        return this.expGained[index];
    }
    public int getProduced(int index){ // a getter for the number of products produced from the seed
        Random randomGenerator = new Random(); // initializes a random number generator for the number of products produced
        return randomGenerator.nextInt(this.minProduced[index], this.maxProduced[index] + 1); // generates a random number based on minimum and maximum number of the crop produced
    }
    public double getSellingPrice(int index){ // a getter for the selling price of the seed/crop
        return this.sellingPrice[index];
    }
    public int getWaterNeeds(int index){ // a getter for the waterNeeds of the seed/crop
        return this.waterNeeds[index];
    }
    public int getWaterBonus(int index){
        return this.waterBonus[index];
    }
    public int getFertilizerBonus(int index){
        return this.fertilizerBonus[index];
    }

    public int getFertilizerNeeds(int index){ // a getter for the fertilizerNeeds of the seed/crop
        return this.fertilizerNeeds[index];
    }
    public int getHarvestTime(int index){ // a getter for the harvestTime of the seed/crop
        return this.harvestTime[index];
    }

    public static void main(String[] args){
        int i;
        SeedList seedList = new SeedList();

        for (i = 0; i < 20; i++){
            System.out.println(seedList.getProduced(7));
        }
    }

}
