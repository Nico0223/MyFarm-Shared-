import javax.swing.*;

public class Prompt {
    JFrame frame = new JFrame();
    public Prompt(String message){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,message,"Message",JOptionPane.PLAIN_MESSAGE);
        frame.dispose();
    }

    public Prompt(int set){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int a = JOptionPane.showConfirmDialog(frame, "Congratulations, you lost the game!","Message",
                JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
        frame.dispose();
    }
}
