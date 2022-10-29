public class FarmerTypeList { // The class that initializes the farmer type list together with its effects on the farm lot
    private final String[] type = {"Farmer", "Registered Farmer", "Distinguished Farmer", "Legendary Farmer"}; // an array of farmer types
    private final double[] bonusEarning = {0, 1, 2, 4}; // an array of bonusEarnings correlated with their farmer types
    private final double[] costReduction = {0, 1, 2, 3}; // an array of costReduction correlated with their farmer types
    private final int[] waterBonus = {0, 0, 1, 2}; // an array of waterBonus correlated with their farmer types
    private final int[] fertilizerBonus = {0, 0, 0, 1};// an array of fertilizerBonus correlated with their farmer types
    private final double[] registrationFee = {0, 200, 300, 400}; // an array of registrationFee correlated with their farmer types
    private final int[] minimumLevel = {0, 5, 10, 15}; // an array of minimumLevel correlated with their farmer types

    public int getIndexFarmerType(String order){ // gets the index of the farmertype based on the arrays
        int i;
        for (i = 0; i < 4; i++){ // use linear search
            if (this.type[i].equals(order))
                return i;
        }
        return -1;
    }

    public String getType(int index) { // a getter for the farmer type
        return type[index];
    }

    public double getBonusEarning(int index) { // a getter for the BonusEarning
        return bonusEarning[index];
    }

    public double getCostReduction(int index) { // a getter for the CostReduction
        return costReduction[index];
    }

    public int getWaterBonus(int index) { // a getter for the WaterBonus
        return waterBonus[index];
    }

    public int getFertilizerBonus(int index) { // a getter for the FertilizerBonus
        return fertilizerBonus[index];
    }

    public double getRegistrationFee(int index) { // a getter for the RegistrationFee
        return registrationFee[index];
    }

    public int getMinimumLevel(int index) { // a getter for the MinimumLevel
        return minimumLevel[index];
    }
}
