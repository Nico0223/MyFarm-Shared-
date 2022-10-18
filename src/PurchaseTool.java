public class PurchaseTool {
    private int cost;
    private double experience;

    public Tools InitializeOrder(ToolList orderList, String request) {
        int index = orderList.GetIndexTool(request);

        if (index != -1) {
            this.cost = orderList.CostIndex(index);
            this.experience = orderList.ExpIndex(index);
            Tools tool = new Tools();
            tool.GenerateTool(request);
            return tool;
        }
        return null;
    }

    public int GetCost() {
        return this.cost;
    }

    public double GetExp() {
        return this.experience;
    }
}