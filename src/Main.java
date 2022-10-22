

public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        Lot tile = new Lot();
        PurchaseTool orderTool = new PurchaseTool();
        PurchaseSeed purchaseSeed = new PurchaseSeed();
        boolean flag = true;
        int i;
        while (flag){
            player.displayInterface(tile);
            String order;
            order = player.inputPlayer();
            switch (order) {
                case "Seed" -> {
                    if (tile.showState().equals("Plowed")){
                        order = player.inputSeed();
                        i = player.buySeed(purchaseSeed, order);
                        if (i != -1) {
                            Seed seed = new Seed(order);
                            player.plantSeed(tile, seed);
                        }
                        else
                            System.out.println("Sorry, we don't have that seed");
                    }
                    else
                        System.out.println("Dude, plow the tile first!");
                }
                case "Bed" -> {
                    tile.leaveLot();
                    if (tile.showState().equals("Withered"))
                        flag = false;
                }
                case "Harvest" -> player.sellHarvest(tile);
                default -> {
                    player.buyTool(orderTool);
                    Tools tool = new Tools(order);
                    player.equipTool(tool);
                    player.useTool(tile);
                }
            }
        }
        System.out.println("Congratulations, you lost the game!");
        player.displayInterface(tile);
    }
}
