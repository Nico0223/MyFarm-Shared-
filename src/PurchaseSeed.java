public class PurchaseSeed {
    private double seedCost;

    public int initializeOrder(SeedList seedList, String request){
        int index = seedList.getIndexSeed(request);

        if (index != -1)
            this.seedCost = seedList.getCost(index);
        return index;
    }
    public double getCost(){
        return this.seedCost;
    }
}
