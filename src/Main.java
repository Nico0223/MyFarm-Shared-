import java.util.*;

public class Main {
    static Lot tile = new Lot();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Player player = new Player();
        player.DisplayInterface(tile);
        System.out.print("\nWhat tool do you want to use: ");
        String order = sc.nextLine();
        System.out.println("You ordered " + order + ".");
        PurchaseTool orderTool = new PurchaseTool();
        player.BuyAndEquip(orderTool, order);
        player.UseTool(tile);
        player.DisplayInterface(tile);

    }
}
