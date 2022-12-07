import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;




public class GUI {
    Color bgColor = new Color(99, 189, 64);
    private final JFrame mainFrame = new JFrame("MyFarm");
    private JDialog seedShop;
    private Player player = new Player();
    private final Lot[][] tile = new Lot[5][10];
    private int day = 1;

    private JLabel farmType;
    private JLabel objCoins;
    private JLabel nDays;
    private JLabel level;

    private JButton[][] bTile = new JButton[5][10];
    private int tileX;
    private int tileY;
    private final boolean[] isRock = new boolean[50];

    private JLabel cropStat;
    private JLabel waterStat;
    private JLabel fertilizerStat;

    private JButton bPlow;
    private JButton bShovel;
    private JButton bBed;
    private JButton bRank;

    private String order;

    private final Border br = BorderFactory.createLineBorder(Color.black);
    private final Border defaultBr = new JButton().getBorder();

    public GUI (){
        this.mainFrame.getContentPane().setBackground(bgColor);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new FlowLayout(FlowLayout.CENTER,1000,30));
        this.mainFrame.setSize(700, 500);     
        topPanel();
        middlePanel();
        bottomPanel();
        this.mainFrame.setVisible(true);
    }

    public void topPanel() {
        JPanel panel = new JPanel(new GridLayout(2,2));
        panel.setBackground(new Color(211, 131, 58));
        farmType = new JLabel();
        farmType.setText(player.getFarmerType());
        objCoins = new JLabel("ObjectCoins: " + player.getObjectCoin());
        nDays = new JLabel("Days: " + day);
        panel.add(farmType, BorderLayout.WEST);
        panel.add(objCoins, BorderLayout.WEST);
        level = new JLabel("Lv. " + player.getLevel() + " " +
                player.getExperience() % 100 + " / 100");
        panel.add(level, BorderLayout.SOUTH);
        panel.add(nDays, BorderLayout.SOUTH);


        this.mainFrame.add(panel);

    }

    public void middlePanel(){
        JPanel panel2 = new JPanel(new GridLayout(5,10,5,5));
        panel2.setBackground(new Color(115, 35, 5));
        bTile = new JButton[5][10];
        int i,j;
        int index = 0;
        generateRocks();
      
        for (i = 0; i < 5; i++) {
            for (j = 0; j < 10; j++) {
                tile[i][j] = new Lot();
                if (isRock[index])
                    tile[i][j].generateRock();
                index++;
              
                bTile[i][j] = new JButton(tile[i][j].showState().substring(0,1));
                bTile[i][j].setMargin(new Insets(5,5,5,5));
                bTile[i][j].setFont(new Font("Arial", Font.BOLD, 12));
                bTile[i][j].setPreferredSize(new Dimension(36,36));
                panel2.add(bTile[i][j]);
                int finalJ = j;
                int finalI = i;
                bTile[i][j].addActionListener(new ActionListener(){
                @Override
                 public void actionPerformed(ActionEvent e) {
                   JButton button = (JButton) e.getSource();
                    if (order != null)
                    {
                      player.setOrder(order);
                      PurchaseTool orderTool = new PurchaseTool(); 
                      if (player.buyTool(orderTool) != -1){ 
                          Tools tool = new Tools(order); 
                          player.equipTool(tool); 
                          player.useTool(tile[finalI][finalJ]); 
                      
                          button.setText(tile[finalI][finalJ].showState().substring(0,1));
                          updateInterface();
                      }
                    }
                    else if (tile[finalI][finalJ].getCrop() != null){
                          System.out.println("hi seed");
                          tileX = finalI;
                          tileY = finalJ;
                          Seed tempSeed = tile[finalI][finalJ].getCrop();
                          cropStat.setText(tile[finalI][finalJ].showState());
                          waterStat.setText(tempSeed.showWaterProgress(new SeedList()));
                          fertilizerStat.setText(tempSeed.showFertilizerProgress(new SeedList()));
                          bPlow.setText("Water");
                          bShovel.setText("Fertilize");
                          bRank.setText("Harvest");
                          bBed.setText("Close");
                    }
                   
                    else if (tile[finalI][finalJ].showState().equals("Plowed")){
                          tileX = finalI;
                          tileY = finalJ;
                          seedShop();
                          order = null;
                      
                    }
                    else if (tile[finalI][finalJ].showState().equals("Rock")){
                            tileX = finalI;
                            tileY = finalJ;
                            bPlow.setText("Pickaxe");
                            bShovel.setVisible(false);
                            bRank.setVisible(false);
                            bBed.setText("Close");
                        }
                  }  
                });
              }
            }
        this.mainFrame.add(panel2);
    }

    public void bottomPanel(){
        JPanel panel3 = new JPanel(new BorderLayout(5,0));
        panel3.setBackground(new Color(99, 189, 64));
        JPanel subPanel = new JPanel(new GridLayout(3,1,2,2));
        subPanel.setBackground(new Color(99, 189, 64));
        JPanel subPanel2 = new JPanel(new GridLayout(1,4,5,5));
        subPanel2.setBackground(new Color(99, 189, 64));
        bPlow = new JButton("Plow");
        bPlow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();            
                if (bPlow.getText().equals("Plow"))
                {
                     if (button.getBorder() == defaultBr){
                    button.setBorder(br);
                    order = "Plow";
                    bShovel.setBorder(defaultBr);
                     }
                     else{
                    button.setBorder(defaultBr);
                    order = null;
                }
                }
                else if (button.getText().equals("Water")){
                    String order = "Watering can";
                    player.setOrder(order);
                    player.buyTool(new PurchaseTool());
                    Tools tool = new Tools(order);
                    player.equipTool(tool);
                    player.useTool(tile[tileX][tileY]);
                    waterStat.setText(tile[tileX][tileY].getCrop().showWaterProgress(new SeedList()));
                    objCoins.setText("ObjectCoins: " + player.getObjectCoin());
                    level.setText("Lv. " + player.getLevel() + " " +
                            player.getExperience() % 100 + " / 100");
                    order = null;
                }
                else if (button.getText().equals("Pickaxe")){
                    if (JOptionPane.showConfirmDialog(mainFrame,"Do you want to remove the rock for " +
                            "50 Objectcoins?","Message",
                            JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE) == JOptionPane.NO_OPTION)
                        return;
                    order = "Pickaxe";
                    player.setOrder(order);
                    player.buyTool(new PurchaseTool());
                    Tools tool = new Tools(order);
                    player.equipTool(tool);
                    player.useTool(tile[tileX][tileY]);
                    updateInterface();
                    bTile[tileX][tileY].setText(tile[tileX][tileY].showState().substring(0,1));
                    bPlow.setText("Plow");
                    bShovel.setVisible(true);
                    bRank.setVisible(true);
                    bBed.setText("Bed");
                    order = null;
                }
                  /*
                else if (bPlow.getText().equals("Plant")){
                
                }*/
              
            }
        });
      
        bShovel = new JButton("Shovel");
        bShovel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals("Shovel")){
                    if (button.getBorder() == defaultBr){
                    button.setBorder(br);
                    order = "Shovel";
                    bPlow.setBorder(defaultBr);
                }
                else{
                    button.setBorder(defaultBr);
                    order = null;
                }
                }
                
              else if (button.getText().equals("Fertilize")){
                    order = "Fertilizer";
                    player.setOrder(order);
                    player.buyTool(new PurchaseTool());
                    Tools tool = new Tools(order);
                    player.equipTool(tool);
                    player.useTool(tile[tileX][tileY]);
                    fertilizerStat.setText(tile[tileX][tileY].getCrop().showFertilizerProgress(new SeedList()));
                    objCoins.setText("ObjectCoins: " + player.getObjectCoin());
                    level.setText("Lv. " + player.getLevel() + " " +
                            player.getExperience() % 100 + " / 100");
                    order = null;
                }
            }
            
        });
        bRank = new JButton("Rank up");
        bRank.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();

                if (button.getText().equals("Rank up")){
                    FarmerTypeList farmerTypeList = new FarmerTypeList();
                    int farmerIndex = farmerTypeList.getIndexFarmerType(player.getFarmerType()) + 1;
                    int minLevel = farmerTypeList.getMinimumLevel(farmerIndex);
                    String farmerType = farmerTypeList.getType(farmerIndex);
                    if (player.getLevel() >= minLevel){
                        int a = JOptionPane.showConfirmDialog(mainFrame,"Do you want to register a " + farmerType +
                                        "\nfor " + (int) farmerTypeList.getRegistrationFee(farmerIndex) + " objectCoins?",
                                "Registration",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
                        if (a == JOptionPane.YES_OPTION){
                            player.setOrder(farmerType);
                            player.register();
                            farmType.setText(player.getFarmerType());
                            updateInterface();
                            for (int i = 0; i < 5; i++)
                                for (int j = 0; j < 10; j++)
                                    if (tile[i][j].getCrop() != null)
                                        tile[i][j].updateBonus(player);
                        }
                    }
                    else
                        new Prompt("You have to be Lv. " + minLevel + " and above\n" +
                                "to register the " + farmerType + ".");
                }
                else if (button.getText().equals("H")){
                    double temp = player.getObjectCoin();
                    player.sellHarvest(tile[tileX][tileY]);
                    if (temp != player.getObjectCoin()){
                        bTile[tileX][tileY].setText(tile[tileX][tileY].showState().substring(0,1));
                        updateInterface();
                        cropStat.setText(" ");
                        waterStat.setText(" ");
                        fertilizerStat.setText(" ");
                        bPlow.setText("P");
                        bPlow.setBorder(defaultBr);
                        bShovel.setText("S");
                        bShovel.setBorder(defaultBr);
                        button.setText("R");
                        bBed.setText("B");
                    }
                    order = null;

                }
                else if (button.getText().equals("Harvest")){
                    double temp = player.getObjectCoin();
                    player.sellHarvest(tile[tileX][tileY]);
                    if (temp != player.getObjectCoin()){
                        bTile[tileX][tileY].setText(tile[tileX][tileY].showState().substring(0,1));
                        objCoins.setText("ObjectCoins: " + player.getObjectCoin());
                        level.setText("Lv. " + player.getLevel() + " " +
                                player.getExperience() % 100 + " / 100");
                        cropStat.setText(" ");
                        waterStat.setText(" ");
                        fertilizerStat.setText(" ");
                        bPlow.setText("Plow");
                        bPlow.setBorder(defaultBr);
                        bShovel.setText("Shovel");
                        bShovel.setBorder(defaultBr);
                        button.setText("Rank up");
                        bBed.setText("Bed");
                    }
                    order = null;

                }
            }
        });
        bBed = new JButton("Bed");
        bBed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              JButton button = (JButton) e.getSource();
              if (button.getText().equals("Bed")){
                  day++;
                  nDays.setText("Days: " + day);
                  boolean flag = true;
                  for (int i = 0; i < 5; i++){
                      for (int j = 0; j < 10; j++){
                          if (tile[i][j].getCrop() != null){
                                tile[i][j].leaveLot();
                                if (tile[i][j].showState().equals("Withered"))
                                    bTile[i][j].setText(tile[i][j].showState().substring(0,1));
                                if (tile[i][j].showState().equals("Ready to harvest"))
                                    bTile[i][j].setText("H");
                            }
                            if (tile[i][j].showState().equals("Unplowed") || tile[i][j].showState().equals("Plowed")){
                                if (player.getObjectCoin() > 5){
                                    flag = false;
                                }
                            }
                            else if (tile[i][j].getCrop() != null)
                                flag = false;
                          System.out.println(flag);
                        }
                    }
                    if (flag){
                        int a = JOptionPane.showConfirmDialog(mainFrame,
                                "Congratulations, you lost the game!\n" + "Want to try again?","Message",
                                JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
                        if (a == JOptionPane.YES_OPTION){
                            player = new Player();
                            objCoins.setText("ObjectCoins: " + player.getObjectCoin());
                            level.setText("Lv. " + player.getLevel() + " " +
                                    player.getExperience() % 100 + " / 100");
                            int index = 0;
                            for (int i = 0; i < 5; i++){
                                for (int j = 0; j < 10; j++){
                                    tile[i][j] = new Lot();
                                    if (isRock[index])
                                      tile[i][j].generateRock();
                                    index++;
                                    bTile[i][j].setText(tile[i][j].showState().substring(0,1));
                                }
                            }
                            day = 1;
                            nDays.setText("Days: " + day);
                        }
                        else
                            System.exit(0);
                    }
              }
              else if (button.getText().equals("Close")){
        
                    cropStat.setText(" ");
                    waterStat.setText(" ");
                    fertilizerStat.setText(" ");
                    bPlow.setText("Plow");
                    bPlow.setBorder(defaultBr);
                    bShovel.setText("Shovel");
                    bShovel.setVisible(true);
                    bShovel.setBorder(defaultBr);
                    bRank.setText("Rank up");
                    bRank.setVisible(true);
                    bBed.setText("Bed");
                    order = null;
              }
            }
        });

        cropStat = new JLabel(" ");
        waterStat = new JLabel(" ");
        fertilizerStat = new JLabel(" ");
        subPanel.add(cropStat);
        subPanel.add(waterStat);
        subPanel.add(fertilizerStat);
        panel3.add(subPanel, BorderLayout.WEST);
      
        subPanel2.add(bPlow);
        subPanel2.add(bShovel);
        subPanel2.add(bRank);
        subPanel2.add(bBed);
        panel3.add(subPanel2, BorderLayout.EAST);

        this.mainFrame.add(panel3);

    }

  public void updateInterface(){
      objCoins.setText("ObjectCoins: " + player.getObjectCoin());
      level.setText("Lv. " + player.getLevel() + " " +
              player.getExperience() % 100 + " / 100");
  }
  
  public void seedShop(){
        seedShop = new JDialog(mainFrame, "Seed Shop");
        seedShop.getContentPane().setBackground(new Color(211, 131, 58));
        seedShop.setSize(350, 350);
        //seedShop.setSize(600, 450);
        seedShop.setLocation(200, 200);
        seedShop.setLayout(new FlowLayout());
        JPanel panelShop = new JPanel(new GridLayout(1, 10, 5, 5));
        JPanel seedNamePanel = new JPanel(new GridLayout(9,1,0,5));
        seedNamePanel.setBackground(new Color(211, 131, 58));
        JPanel seedTypePanel = new JPanel(new GridLayout(9,1,0,5));
        seedTypePanel.setBackground(new Color(211, 131, 58));
        JPanel seedCostPanel = new JPanel(new GridLayout(9,1,0,5));
        seedCostPanel.setBackground(new Color(211, 131, 58));
        JPanel buyPanel = new JPanel(new GridLayout(9,1,0,5));
        buyPanel.setBackground(new Color(211, 131, 58));
        JLabel lSeedName = new JLabel("Seed Name");
        seedNamePanel.add(lSeedName);
        JLabel temp;
        SeedList seedList = new SeedList();
        for (int i = 0; i < 8; i++){
            temp = new JLabel(seedList.getSeedName(i));
            seedNamePanel.add(temp);
        }

        JLabel lCropType = new JLabel("Crop Type");
        seedTypePanel.add(lCropType);
        for (int i = 0; i < 8; i++){
            temp = new JLabel(seedList.getCropType(i));
            seedTypePanel.add(temp);
        }
        /*JLabel lHarvestTime = new JLabel("Harvest Time");
        JLabel lWaterNeeds = new JLabel("Water Needs");
        JLabel lFertilizerNeeds = new JLabel("Fertilizer Needs");
        JLabel lProduces = new JLabel("Produces");
        JLabel lPrice = new JLabel("Base Price");
        JLabel lExpYield = new JLabel("Exp Yield");*/

        JLabel lSeedCost = new JLabel("Seed Cost");
        seedCostPanel.add(lSeedCost);
        Registration registration = player.getRegistration();
        for (int i = 0; i < 8; i++){
            temp = new JLabel(String.valueOf(seedList.getCost(i) - registration.getCostReduction()), SwingConstants.CENTER);
            seedCostPanel.add(temp);
        }

        JLabel lBuy = new JLabel(" ");
        buyPanel.add(lBuy);
        for (int i = 0; i < 8; i++){
            JButton bBuy = new JButton("Buy");
            buyPanel.add(bBuy);
            int index = i;
            bBuy.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    order = seedList.getSeedName(index);
                    player.setOrder(order);
                    if (checkSurroundings(seedList.getCropType(index))){
                        if (player.buySeed(new PurchaseSeed()) != -1) {
                            Seed seed = new Seed(order);
                            tile[tileX][tileY].plantSeed(seed);
                            tile[tileX][tileY].updateBonus(player);
                            bTile[tileX][tileY].setText(tile[tileX][tileY].showState().substring(0, 1) +
                                    tile[tileX][tileY].getCrop().showType().charAt(0));
                            objCoins.setText("ObjectCoins: " + player.getObjectCoin());
                            cropStat.setText(" ");
                            waterStat.setText(" ");
                            fertilizerStat.setText(" ");
                        }
                    }
                    order = null;
                    seedShop.dispose();
                }
            });
        }
    
        /*panelShop.add(lSeedName);
        panelShop.add(lCropType);
        panelShop.add(lHarvestTime);
        panelShop.add(lWaterNeeds);
        panelShop.add(lFertilizerNeeds);
        panelShop.add(lProduces);
        panelShop.add(lPrice);
        panelShop.add(lExpYield);
        panelShop.add(lSeedCost);
        //panelShop.add();
        panelShop.add(label);*/
        panelShop.add(seedNamePanel);
        panelShop.add(seedTypePanel);
        panelShop.add(seedCostPanel);
        panelShop.add(buyPanel);
        seedShop.add(panelShop);
        seedShop.setVisible(true);
    }

    public void generateRocks(){
        try{
            Scanner scanner = new Scanner(new File("RockGenerator.txt"));
            String line;
            int index = 0;
            while (scanner.hasNextLine()){
                line = scanner.nextLine();
                System.out.println(line);
                this.isRock[index] = line.charAt(0) == '1';
                //System.out.println(line.charAt(0));
                index++;

            }
            scanner.close();
        }catch (Exception e){
            new Prompt(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public boolean checkSurroundings(String seed){
        if (seed.equals("Tree")){
            for (int i = tileX-1; i <= tileX+1; i++){
                if (i < 0 || (tileX + 1) > 4){
                    new Prompt("You trying to be cheeky or what?");
                    System.out.println("You trying to be cheeky or what?");
                    return false;
                }
                for (int j = tileY-1; j <= tileY+1; j++){
                    if (j < 0 || (tileY + 1) > 9){
                        new Prompt("You trying to be cheeky or what?");
                        System.out.println("You trying to be cheeky or what?");
                        return false;
                    }
                    if (!tile[i][j].showState().equals("Unplowed") && !tile[i][j].showState().equals("Plowed")){
                        new Prompt("Dude, give me some space to plant a tree!!");
                        System.out.println("Dude, give me some space to plant a tree!!");
                        return false;
                    }
                }
            }
        }

        return true;
    }
}

