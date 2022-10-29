public class PurchaseSeed {
    private double seedCost;

    public int initializeOrder(SeedList seedList, String request){
        int index = seedList.getIndexSeed(request);

        if (index != -1){
            this.seedCost = seedList.getCost(index);
            System.out.println("You have spent " + this.seedCost + " ObjectCoins");
        }
        return index;
    }
    public double getCost(){
        return this.seedCost;
    }
}
