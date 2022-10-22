public class FarmerTypeList {
    private final String[] type = {"Farmer", "Registered Farmer", "Distinguished Farmer", "Legendary Farmer"};
    private final double[] bonusEarning = {0, 1, 2, 4};
    private final double[] costReduction = {0, 1, 2, 3};
    private final int[] waterBonus = {0, 0, 1, 2};
    private final int[] fertilizerBonus = {0, 0, 0, 1};
    private final double[] registrationFee = {0, 200, 300, 400};
    private final int[] minimumLevel = {0, 5, 10, 15};

    public int getIndexFarmerType(String order){
        int i;
        for (i = 0; i < 4; i++){
            if (this.type[i].equals(order))
                return i;
        }
        return -1;
    }

    public String getType(int index) {
        return type[index];
    }

    public double getBonusEarning(int index) {
        return bonusEarning[index];
    }

    public double getCostReduction(int index) {
        return costReduction[index];
    }

    public int getWaterBonus(int index) {
        return waterBonus[index];
    }

    public int getFertilizerBonus(int index) {
        return fertilizerBonus[index];
    }

    public double getRegistrationFee(int index) {
        return registrationFee[index];
    }

    public int getMinimumLevel(int index) {
        return minimumLevel[index];
    }
}
