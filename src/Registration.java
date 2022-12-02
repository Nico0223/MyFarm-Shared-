/*
This class initializes the registration of the farmer on the farm lot. It contains information such as the type of farmer, level required, bonus earnings from crops, cost reduction from shops, water bonuses and fertilizer bonuse and all of which are depending on the current registration of the user. There is also a method for updating the registration, as well as restrictions and requirements for the user to buy the next registration.
*/


import java.util.Scanner;

public class Registration { 
    private String farmerType = "Farmer"; // string variable for farmerType set to "Farmer"
    private int index = 0;
    private int level = 0;
    private double bonusEarning = 0;
    private double costReduction = 0;
    private int waterBonus = 0;
    private int fertilizerBonus = 0;

    public String showRegistration(){ // shows the current registration of the user
        return this.farmerType;
    }

    public double initializeRegistration(FarmerTypeList farmerTypeList, double objectCoins){ // initializes on the change of registration for the user
        System.out.println("ObjectCoins: " + objectCoins);
        System.out.println("Welcome to Farmer Registration");
        while(true){
            System.out.println("Here are the available Farmer Types:");
            int i;
            for (i = 0; i < 4; i++){
                System.out.print(farmerTypeList.getType(i)); // prints the list of farmer types
                if (this.farmerType.equals(farmerTypeList.getType(i)))
                    System.out.println("(Current)"); // shows the current farmer type of the user
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
            if (request.equals("Leave")) // if user chooses to leave the initialization of farmer registration
                return objectCoins;
            i = farmerTypeList.getIndexFarmerType(request);
            if (i == -1){ // if the farmer type is not found
                System.out.println("What?");
            }
            else if (this.farmerType.equals(request)){ // if user chooses to register to their current farmer type
                System.out.println("You wanna be a " + this.farmerType + " again? Nuts!?");
            }
            else if (this.level < farmerTypeList.getMinimumLevel(i)){ // if user chooses to register to a farmer type lower than the minimum level
                System.out.println("Hey, you're too new to register this!");
            }
            else if (objectCoins < farmerTypeList.getRegistrationFee(i)){ // if the objectCoins is lower than the registrationFee for that farmer type
                System.out.println("Not enough ObjectCoins to register");
            }
            else{ // if there is no violation on buying the farmer type
                //this.updateFarmer(farmerTypeList, request);
                return objectCoins - farmerTypeList.getRegistrationFee(this.index);
            }
        }
    }
    public double updateFarmer(double objectCoins, FarmerTypeList farmerTypeList, String request){
        // Lines 59-65, the effects of the farmer registration takes place on the farmer lot
        this.index = farmerTypeList.getIndexFarmerType(request);
        double cost = farmerTypeList.getRegistrationFee(this.index);
        if (objectCoins >= cost){
            this.farmerType = request;
            this.bonusEarning = farmerTypeList.getBonusEarning(this.index);
            this.costReduction = farmerTypeList.getCostReduction(this.index);
            this.waterBonus = farmerTypeList.getWaterBonus(this.index);
            this.fertilizerBonus = farmerTypeList.getFertilizerBonus(this.index);
            new Prompt("Congratulation, You become a " + this.farmerType + "!!");
            System.out.println("Congratulation, You become a " + this.farmerType + "!!");
            return objectCoins - cost;
        }
        else{
            new Prompt("Hah, you broke!!");
            return 0;
        }
    }


    public void levelUp(double experience){ // initializes the player on leveling up
        int temp = this.level; // passes the level of the user to the temp variable
        this.level = (int) (experience / 100); // the level of the user is equal to the experience of the user divided by 100
        while (temp < this.level){ // 
            System.out.println("Level Up!");
            temp++; // temp gets incremented by 1;
        }
    }

    public int showLevel(){ // shows or gets the level of the user
        return this.level;
    }

    public double getCostReduction(){ // a getter for the costReduction of the farmer registration
        return this.costReduction;
    }

    public double getBonusEarning(){ // a getter for the BonusEarning of the farmer registration
        return this.bonusEarning;
    }

    public int getWaterBonus(){ // a getter for the WaterBonus of the farmer registration
        return this.waterBonus;
    }

    public int getFertilizerBonus(){ // a getter for the FertilizerBonus
        return this.fertilizerBonus;
    }

    public static void main(String[] args){
        double objectCoins = 1000;
        Registration registration = new Registration();

        registration.initializeRegistration(new FarmerTypeList(), objectCoins);
        System.out.println(registration.showRegistration());
        System.out.println(registration.getBonusEarning());
        System.out.println(registration.getCostReduction());
        System.out.println(registration.getWaterBonus());
        System.out.println(registration.getFertilizerBonus());
    }

}
