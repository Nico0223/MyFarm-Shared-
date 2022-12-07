import javax.swing.*;
/**
* The Prompt class forms the pop-up window that alerts the player about the important information such as
 * successful harvest and failure of purchase.
*/
public class Prompt {
    JFrame frame = new JFrame();

    /**
     * Creates the pop-up prompt message by getting the input of string of messages from other objects and destroys
     * itself to save memory
     *
     * @param message the message to be inserted into the prompt window
     */
    public Prompt(String message) { // constructor
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame, message, "Message", JOptionPane.PLAIN_MESSAGE);
        frame.dispose();
    }
}
