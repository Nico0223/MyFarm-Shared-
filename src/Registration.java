import java.util.Scanner;

public class Registration {
    private String farmerType = "Farmer";
    private int index = 0;
    private int level = 0;
    private double bonusEarning = 0;
    private double costReduction = 0;
    private int waterBonus = 0;
    private int fertilizerBonus = 0;

    public String showRegistration(){
        return this.farmerType;
    }

    public double initializeRegistration(FarmerTypeList farmerTypeList, double objectCoins){
        System.out.println("Welcome to Farmer Registration");
        while(true){
            System.out.println("Here are the available Farmer Types:");
            int i;
            for (i = 0; i < 4; i++){
                System.out.print(farmerTypeList.getType(i));
                if (this.farmerType.equals(farmerTypeList.getType(i)))
                    System.out.println("(Current)");
                else if (this.level >= farmerTypeList.getMinimumLevel(i)){
                    System.out.println(" - " + farmerTypeList.getRegistrationFee(i) + " ObjectCoins");
                }
                else {
                    System.out.println(" - Require Level " + farmerTypeList.getMinimumLevel(i));
                }
            }
            System.out.println("Please choose your type: ");
            Scanner sc = new Scanner(System.in);
            String request = sc.nextLine();
            if (request.equals("Leave")){
              //sc.close();
              return objectCoins;
            }
            i = farmerTypeList.getIndexFarmerType(request);
            if (i == -1){
                System.out.println("What?");
            }
            else if (this.farmerType.equals(request)){
                System.out.println("You wanna be a " + this.farmerType + " again? Nuts!?");
            }
            else if (this.level < farmerTypeList.getMinimumLevel(i)){
                System.out.println("Hey, you're too new to register this!");
            }
            else if (objectCoins < farmerTypeList.getRegistrationFee(i)){
                System.out.println("Not enough ObjectCoins to register");
            }
            else{
                this.index = farmerTypeList.getIndexFarmerType(request);
                this.farmerType = request;
                this.bonusEarning = farmerTypeList.getBonusEarning(this.index);
                this.costReduction = farmerTypeList.getCostReduction(this.index);
                this.waterBonus = farmerTypeList.getWaterBonus(this.index);
                this.fertilizerBonus = farmerTypeList.getFertilizerBonus(this.index);
                System.out.println("Congratulation, You become a " + this.farmerType + "!!");
                //sc.close();
                return objectCoins - farmerTypeList.getRegistrationFee(this.index);
            }
        }
    }


    public void levelUp(double experience){
        int previousLevel = this.level;
        this.level = (int) (experience / 100);
        while (previousLevel != this.level){
          System.out.println("Leveled up!");
          previousLevel++;
        }
    }

    public int showLevel(){
        return this.level;
    }

    public double getCostReduction(){
        return this.costReduction;
    }

    public double getBonusEarning(){
        return this.bonusEarning;
    }
      public int getWaterBonus(){
        return this.waterBonus;
    }

    public int getFertilizerBonus(){
        return this.fertilizerBonus;
    }

}
