import java.util.Random;

/**
 * The class contains the list of seeds that the farmer/user can plant. Each seed has their own information in terms
 * of harvest time, water needs & bonuses, fertilizer needs & bonuses, minimum & maximum number of crops produced,
 * seed cost/price, selling price as well as experience gained. This information is stored in forms of arrays.
 * There is also a linear search for finding the seed name and getters for each information.
 */
public class SeedList { 
    // name of the seeds
    private final String[] seedName = {"Turnip", "Carrot", "Potato", "Rose", "Tulips", "Sunflower", "Mango", "Apple"};
    // properties of seeds that corresponds with the seedName array
    private final String[] cropType = {"Root crop", "Root crop", "Root crop", "Flower", "Flower", "Flower",
                                       "Tree", "Tree"};
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

    /**
     * This method returns the index of the ordered seed in the seedName array on order to get the information
     * to create a new seed
     *
     * @param order the seed the player ordered
     * @return the index of the seed
     */
    public int getIndexSeed(String order){ // gets the index of the seed based on the user's order
        int i;

        for (i = 0; i < 8; i++) { // uses linear search to find the seed
            if (seedName[i].equals(order))
                return i;
        }
        return -1;
    }

    /**
     * This getter method returns the name of the seed
     *
     * @param index the index of the seed
     * @return the name of the seed
     */
    public String getSeedName(int index){
        return this.seedName[index];
    }

    /**
     * This getter method returns the type of crop
     *
     * @param index the index of the seed
     * @return the type of crop
     */
    public String getCropType(int index){ // a getter for the crop type
        return this.cropType[index];
    }

    /**
     * This getter method returns the cost of the seed
     *
     * @param index the index of the seed
     * @return the cost of the seed
     */
    public double getCost(int index){ // a getter for the cost of the seed/crop
        return this.seedCost[index];
    }

    /**
     * This getter method returns the experience gained from harvesting the crop
     *
     * @param index the index of the seed
     * @return the experience gained from harvesting the crop
     */
    public double getExp(int index){ // a getter for the experience gained from the seed/crop
        return this.expGained[index];
    }

    /**
     * This getter method returns the number of produces of the crop harvested which ranges from the minimum number
     * of produces to the maximum number of produces
     *
     * @param index the index of the seed
     * @return the number of produces of the crop harvested
     */
    public int getProduced(int index){ // a getter for the number of products produced from the seed
        Random randomGenerator = new Random(); // initializes a random number generator for the number of products produced
        return randomGenerator.nextInt(this.minProduced[index], this.maxProduced[index] + 1); // generates a random number based on minimum and maximum number of the crop produced
    }

    /**
     * This getter method returns the base selling price of the crop per produce
     *
     * @param index the index of the seed
     * @return the base selling price of the crop per produce
     */
    public double getSellingPrice(int index){ // a getter for the selling price of the seed/crop
        return this.sellingPrice[index];
    }

    /**
     * This getter method returns the amount of water needed for the crop to grow
     *
     * @param index the index of the seed
     * @return the amount of water needed for the crop to grow
     */
    public int getWaterNeeds(int index){ // a getter for the waterNeeds of the seed/crop
        return this.waterNeeds[index];
    }

    /**
     * This getter method returns the bonus limit of watering the crop to get extra income after harvesting
     *
     * @param index the index of the seed
     * @return the bonus limit of watering the crop
     */
    public int getWaterBonus(int index){
        return this.waterBonus[index];
    }

    /**
     * This getter method returns the bonus limit of fertilizing the crop to get extra income after harvesting
     *
     * @param index the index of the seed
     * @return the bonus limit of fertilizing the crop
     */
    public int getFertilizerBonus(int index){
        return this.fertilizerBonus[index];
    }

    /**
     * This getter method returns the amount of fertilizer needed for the crop to grow
     *
     * @param index the index of the seed
     * @return the amount of fertilizer needed for the crop to grow
     */
    public int getFertilizerNeeds(int index){ // a getter for the fertilizerNeeds of the seed/crop
        return this.fertilizerNeeds[index];
    }

    /**
     * This getter method returns the number of days it takes for a crop to grow
     *
     * @param index the index of the seed
     * @return the number of days it takes for a crop to grow
     */
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
