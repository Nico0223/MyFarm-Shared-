import javax.swing.*;
/**
This class represents the gameover mechanism whenever the player has failed the game on the farm simulaion. It will be in a form of a pop-up window from the output screen.
*/
public class Prompt {
    JFrame frame = new JFrame();
    public Prompt(String message){ // constructor
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,message,"Message",JOptionPane.PLAIN_MESSAGE);
        frame.dispose();
    }
 /** This method initializes the pop-up window when the player loses the farm game simulation. 
     *
     * @param set, setting the game status
     */
    public Prompt(int set){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int a = JOptionPane.showConfirmDialog(frame, "Congratulations, you lost the game!","Message",
                JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
        frame.dispose();
    }
}
