/**The class contains the list of levels of registration that the farmer/player can plant. Each level of registration
 * has its type or the type of farmer, bonus earning on every harvest, reducing cost of the seed, amount of water and
 * fertilizer bonus limit increasing, registration fee, and minimum level required to register. This information is
 * stored in array and getting the index is done through linear search
 */
public class FarmerTypeList { // The class that initializes the farmer type list together with its effects on the farm lot
    private final String[] type = {"Farmer", "Registered Farmer", "Distinguished Farmer", "Legendary Farmer"}; // an array of farmer types
    private final double[] bonusEarning = {0, 1, 2, 4}; // an array of bonusEarnings correlated with their farmer types
    private final double[] costReduction = {0, 1, 2, 3}; // an array of costReduction correlated with their farmer types
    private final int[] waterBonus = {0, 0, 1, 2}; // an array of waterBonus correlated with their farmer types
    private final int[] fertilizerBonus = {0, 0, 0, 1};// an array of fertilizerBonus correlated with their farmer types
    private final double[] registrationFee = {0, 200, 300, 400}; // an array of registrationFee correlated with their farmer types
    private final int[] minimumLevel = {0, 5, 10, 15}; // an array of minimumLevel correlated with their farmer types

    /**
     * This method returns the index of the succeeding level of registration with the type array on order to get the
     * information to reap information such as bonuses from that level
     *
     * @param order the succeeding level of registration
     * @return index of the succeeding level of registration
     */
    public int getIndexFarmerType(String order){ // gets the index of the farmerType based on the arrays
        int i;
        for (i = 0; i < 4; i++){ // use linear search
            if (this.type[i].equals(order))
                return i;
        }
        return -1;
    }

    /**
     * This getter method returns the type of registration
     *
     * @param index index of the current level of registration
     * @return the type of registration
     */
    public String getType(int index) { // a getter for the farmer type
        return type[index];
    }

    /**
     * This getter method returns the bonus earnings on every harvest
     *
     * @param index index of the current level of registration
     * @return the bonus earnings on every harvest
     */
    public double getBonusEarning(int index) { // a getter for the BonusEarning
        return bonusEarning[index];
    }

    /**
     * This getter method returns the cost reduction of seed
     *
     * @param index index of the current level of registration
     * @return the cost reduction of seed
     */
    public double getCostReduction(int index) { // a getter for the CostReduction
        return costReduction[index];
    }

    /**
     * This getter method returns the increasing water bonus limit
     *
     * @param index index of the current level of registration
     * @return the increasing water bonus limit
     */
    public int getWaterBonus(int index) { // a getter for the WaterBonus
        return waterBonus[index];
    }

    /**
     * This getter method returns the increasing fertilizer bonus limit
     *
     * @param index index of the current level of registration
     * @return the increasing fertilizer bonus limit
     */
    public int getFertilizerBonus(int index) { // a getter for the FertilizerBonus
        return fertilizerBonus[index];
    }

    /**
     * This getter method returns the registration fee
     *
     * @param index index of the current level of registration
     * @return the registration fee
     */
    public double getRegistrationFee(int index) { // a getter for the RegistrationFee
        return registrationFee[index];
    }

    /**
     * This getter method returns the minimum level required to register
     *
     * @param index index of the current level of registration
     * @return the minimum level required to register
     */
    public int getMinimumLevel(int index) { // a getter for the MinimumLevel
        return minimumLevel[index];
    }
}
