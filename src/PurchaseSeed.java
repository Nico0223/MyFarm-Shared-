public class PurchaseSeed { // This class initializes the buying of the seed for the user
    private double seedCost; // a variable for the cost of the seed

    public int initializeOrder(SeedList seedList, String request){ // initializes the order of the user 
        int index = seedList.getIndexSeed(request); // the index of the user's seed to purchase gets passed to the index variable

        if (index != -1){ // if the seed is found
            this.seedCost = seedList.getCost(index); // passes the cost of the seed to the variable seedCost
            System.out.println("You have spent " + this.seedCost + " ObjectCoins");
        }
        return index; // returns the index of the purchase seed
    }
    public double getCost(){ // a getter for the cost of the seed
        return this.seedCost;
    }
}
