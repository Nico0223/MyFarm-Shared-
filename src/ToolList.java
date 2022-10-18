public class ToolList {
    private final String[] toolList = {"Plow", "Watering can", "Fertilizer", "Pickaxe", "Shovel"};
    private final int[] costList = {0,0, 10, 50, 7};
    private final double[] experienceList = {0.5, 0.5, 4, 15, 2};

    public int GetIndexTool(String order) {
        int i;
        for (i = 0; i < 5; i++) {
            if (toolList[i].equals(order))
                return i;
        }
        return -1;
    }

    public int CostIndex(int index){
        return costList[index];
    }
    public double ExpIndex(int index){
        return experienceList[index];
    }
}
