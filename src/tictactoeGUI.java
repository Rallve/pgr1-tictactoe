import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tictactoeGUI {
    private JButton LOLButton;
    private JPanel panel1;

    public tictactoeGUI() {
        LOLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("tictactoeGUI");
        frame.setContentPane(new tictactoeGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
