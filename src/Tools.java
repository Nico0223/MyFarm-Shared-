public class Tools {
    private String toolType = "None";

    public void GenerateTool(String newTool){
        this.toolType = newTool;
    }

    public String ShowTool() {
        return toolType;
    }

}
