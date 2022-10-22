public class PurchaseSeed {
    private int seedCost;

    public int initializeOrder(SeedList seedList, String request){
        int index = seedList.getIndexSeed(request);

        if (index != -1)
            this.seedCost = seedList.getCost(index);
        return index;
    }
    public int getCost(){
        return this.seedCost;
    }
}
