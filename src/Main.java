

public class Main { // The Main class for initializing the whole program up until the end of the game
    public static void main(String[] args) {
        Player player = new Player(); // instantiate the player object with the Player class
        Lot tile = new Lot(); //  instantiate the tile object with the Lot class.
        PurchaseTool orderTool; // a class-level variable for orderTool
        PurchaseSeed purchaseSeed; // a class-level variable for purchaseSeed
        while (!player.getGameEnd()){ // when the getGameEnd is false
            player.displayInterface(tile); // displays the interface or information for the user
            String order; // creates a variable for order
            order = player.inputPlayer(); // passes the value of the input of the user to the order variable
            switch (order) {
                case "Seed" -> { // If the user orders "Seed"
                    if (tile.showState().equals("Plowed")){ // if the state of the tile is "Plowed"
                        purchaseSeed = new PurchaseSeed(); // instantiate the purchaseSeed object with a PurchaseSeed class
                        order = player.inputSeed(); // passes the value of the seed input to the order variable
                        if (player.buySeed(purchaseSeed) != -1) { // if the seed was found
                            Seed seed = new Seed(order); // instantiate the seed object with the Seed class
                            tile.plantSeed(seed); // initialize to plant the seed on the plant
                        }
                        else // if the seed was not found
                            System.out.println("Sorry, we don't have that seed");
                    }
                    else // if the state of the plow is not plowed
                        System.out.println("Dude, plow the tile first!");
                }
                case "Bed" -> { // If the user orders "Bed" or advances day
                    if (tile.getCrop() != null) // if there is no crop on the tile
                        tile.leaveLot(); // user leaves thelot
                    if (tile.showState().equals("Withered")) // if the state of the tile is "Withered"
                        player.setGameEnd(); // sets the gameEnd to "true"
                }
                case "Harvest" -> player.sellHarvest(tile); // if user orders "Harvest" then the tile gets harvested and sold
                case "Register" -> player.register(); // if the user orders "Register", initializes the register method
                case "Forfeit" -> player.setGameEnd(); // if the user orders "Forfeit", initializes the gameEnd to true.
                default -> {
                    orderTool = new PurchaseTool(); // instantiate the orderTool object with the PurchaseTool class
                    if (player.buyTool(orderTool) != -1){ // if the buytool of the user is found
                        Tools tool = new Tools(order); // instantiate the tool object with the Tools class
                        player.equipTool(tool); // player equips the tool
                        player.useTool(tile); // players uses the tool on the tile
                    }
                }
            }
        }
        System.out.println("Congratulations, you lost the game!");
        player.displayInterface(tile);
    }
}
