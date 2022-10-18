
public class Lot {
    //private int tile;
    private String state = "Unplowed";
    private boolean rock;
    //private Seed crop;

    public void PlowTile(){
        state = "Plowed";
    }
    public void WaterPlant(){
        state = "Watered";
    }
    public void FertilizePlant(){
        state = "Fertilized";
    }
    public void MineRock(){
        state = "Unplowed";
    }
    public void ShovelTile(){
        if (state.equals("Unplowed"))
            System.out.println("Bruh");

        state = "Unplowed";
    }

    public String ShowState(){
        return state;
    }

}
