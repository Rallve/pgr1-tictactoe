import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int row = JOptionPane.showInputDialog("Hur många rader?");
        int column = JOptionPane.showInputDialog("Hur många kolumner?");
        List<String> spelplan = new ArrayList<>(Arrays.asList(""));
        for (int i; i < row * column; i++) {
            spelplan.add("0");
        }
        JOptionPane.showMessageDialog(null, spelplan);
    }
}