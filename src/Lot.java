
public class Lot {
    //private int tile;
    private String state = "Unplowed";
    private boolean rock;
    //private Seed crop;

    public void plowTile(){
        state = "Plowed";
    }
    public void waterPlant(){
        state = "Watered";
    }
    public void fertilizePlant(){
        state = "Fertilized";
    }
    public void mineRock(){
        state = "Unplowed";
    }
    public void shovelTile(){
        if (state.equals("Unplowed"))
            System.out.println("Bruh");

        state = "Unplowed";
    }

    public String showState(){
        return state;
    }

}
