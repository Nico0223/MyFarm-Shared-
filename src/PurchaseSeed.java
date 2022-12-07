/** This class initializes the player on purchasing the tool of their choice. It has the cost of the selected tool
 * and the experiences gained from using the selected tool which was obtained from the orderList object with the index
 */
public class PurchaseSeed { // This class initializes the buying of the seed for the user
    private double seedCost; // a variable for the cost of the seed

    /**
     * This method initializes the purchase of seed by searching for available seeds in the SeedList class and
     * sets the value of the cost of the seed. Furthermore, the cost of the seed is reduced based on the current
     * level of registration of the player
     *
     * @param seedList the list of available seeds in the game with their cost.
     * @param registration the current level of registration of the player along with its benefits
     * @param request the seed the player orders
     * @return the index of the seed if success and -1 if the transaction fails
     */
    public int initializeOrder(SeedList seedList, Registration registration, String request){ // initializes the order of the user 
        int index = seedList.getIndexSeed(request); // the index of the user's seed to purchase gets passed to the index variable

        if (index != -1){ // if the seed is found
            this.seedCost = seedList.getCost(index) - registration.getCostReduction(); // passes the cost of the seed to the variable seedCost
            System.out.println("You have spent " + this.seedCost + " ObjectCoins");
        }
        return index; // returns the index of the purchase seed
    }

    /**
     * This getter method returns the cost of the seed
     * @return the cost of the seed
     */
    public double getCost(){ // a getter for the cost of the seed
        return this.seedCost;
    }
}
