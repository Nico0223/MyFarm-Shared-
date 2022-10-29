

public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        Lot tile = new Lot();
        PurchaseTool orderTool;
        PurchaseSeed purchaseSeed;
        while (!player.getGameEnd()){
            player.displayInterface(tile);
            String order;
            order = player.inputPlayer();
            switch (order) {
                case "Seed" -> {
                    if (tile.showState().equals("Plowed")){
                        purchaseSeed = new PurchaseSeed();
                        order = player.inputSeed();
                        if (player.buySeed(purchaseSeed) != -1) {
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
                    if (tile.getCrop() != null)
                        tile.leaveLot();
                    if (tile.showState().equals("Withered"))
                        player.setGameEnd();
                }
                case "Harvest" -> player.sellHarvest(tile);
                case "Register" -> player.registration();
                case "Forfeit" -> player.setGameEnd();
                default -> {
                    orderTool = new PurchaseTool();
                    if (player.buyTool(orderTool) != -1){
                        Tools tool = new Tools(order);
                        player.equipTool(tool);
                        player.useTool(tile);
                    }
                }
            }
        }
        System.out.println("Congratulations, you lost the game!");
        player.displayInterface(tile);
    }
}
