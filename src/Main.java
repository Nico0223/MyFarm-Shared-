/**
 * <b>MyFarm</b> is a farming simulation game, where the player acts as the sole farmer (and manager) of their own farm.
 * The gameplay typically involves the following tasks:
 * <ul>
 * <li>Buying seeds,
 * <li>Preparing land,
 * <li>Planting seeds,
 * <li>Advancing days (err… watching crops grow), and
 * <li>Harvesting crops
 * </ul>
 * <p>
 * There’s obviously a lot more to the game, but that’s the general idea. There’s also no real end goal to this
 * game – as in many simulation games. However, to make things clear, the game can theoretically go on forever and the
 * player can continue to play for as long as they want except in the case when they (1) don’t have any active/growing
 * crops and (2) don’t have enough money to buy new seeds. The player also cannot continue if all the tiles in their
 * farm lot contain withered crops (i.e. crops that died due to lack of care). When either situation happens, the game
 * ends and the player is asked if they’d want to start a new game or simply exit the program.
 *
 * @author      Carlos Dominic R. Gonzaga
 * @author      Jared Zachary M. Ranjo
 * @version     %I%, %G%
 * @since       1.0
 */
public class Main { // The Main class for initializing the whole program up until the end of the game
    public static void main(String[] args) {
        Player player = new Player(); // instantiate the player object with the Player class
        Lot tile = new Lot(); //  instantiate the tile object with the Lot of class.
        PurchaseTool orderTool; // a class-level variable for orderTool
        PurchaseSeed purchaseSeed; // a class-level variable for purchaseSeed
        player.displayInterface(tile);
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
                        tile.leaveLot(); // user leaves  the lot
                    if (tile.showState().equals("Withered")) // if the state of the tile is "Withered"
                        player.setGameEnd(); // sets the gameEnd to "true"
                }
                case "Harvest" -> player.sellHarvest(tile); // if user orders "Harvest" then the tile gets harvested and sold
                case "Register" -> player.register(); // if the user orders "Register", initializes the register method
                case "Forfeit" -> player.setGameEnd(); // if the user orders "Forfeit", initializes the gameEnd to true.
                default -> {
                    orderTool = new PurchaseTool(); // instantiate the orderTool object with the PurchaseTool class
                    if (player.buyTool(orderTool) != -1){ // if the buyTool of the user is found
                        Tools tool = new Tools(order); // instantiate the tool object with the Tools class
                        player.equipTool(tool); // player equips the tool
                        player.useTool(tile); // players uses the tool on the tile
                    }
                }
            }
            if (player.checkGame()){
                player = new Player();
                tile = new Lot();
            }
        }
        player.displayInterface(tile);
    }
}

/**
 *  This is to certify that this project is our own work, based on our personal efforts in studying and applying the
 *  concepts learned. We have constructed the classes, methods, and their respective algorithms
 *  and corresponding code by ourselves. The program was run, tested, and debugged by our own efforts. We further
 *  certify that we have not copied in part or whole or otherwise plagiarized the work of other students and/or
 *  persons.
 *  Carlos Dominic R. Gonzaga, DLSU ID# 12106402
 *  Jared Zachary M. Ranjo, DLSU ID# 12110132
 *  Sources: https://docs.oracle.com/en/java/javase/12/docs/api/java.base/module-summary.html
 *           https://www.w3schools.com/java/
 *           https://www.tutorialspoint.com/java/index.htm
 */
