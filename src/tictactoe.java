import javax.swing.*;
import java.util.*;

public class tictactoe {
    public static void main(String[] args) {
        int row = Integer.parseInt(JOptionPane.showInputDialog("Hur många rader?"));
        int column = Integer.parseInt(JOptionPane.showInputDialog("Hur många kolumner?"));
        int line = Integer.parseInt(JOptionPane.showInputDialog("Hur många X/O på raken för att vinna?"));
        List<String> grid = new ArrayList<>(Arrays.asList());
        for (int i = 0; i < row * column; i++) {
            grid.add("0");
        }
        int loop = 0;
        String[] XO = {"x", "o"};
        while (true) {
            int[] result = coordsExtract(JOptionPane.showInputDialog("Skriv in koordinater.\n" + gridDisplay(grid, row, column)));
            int index = (result[1] - 1) * column + result[0];
            if (grid.get(index - 1).equals("0")) {
                grid.set(index - 1, XO[loop % 2]);
                if (gridScan(grid, XO[loop % 2], line, column)) {
                    JOptionPane.showMessageDialog(null, XO[loop % 2] + " vann!");
                }
                System.out.println("--------------------------------");
                loop += 1;
            } else {
                JOptionPane.showMessageDialog(null, "Ogiltig ruta.");
            }
        }
    }

    static int[] coordsExtract(String coords) {
        int x = 0;
        int y = 0;
        String coordinate = "";
        boolean coord1 = false;
        for (int i = 0; x == 0 || y == 0; i++) {
            try {
                int scan = Integer.parseInt(String.valueOf(coords.charAt(i)));
                if (scan > 0 && coord1) {
                    coordinate += String.valueOf(coords.charAt(i));
                } else if (scan > 0) {
                    coordinate = String.valueOf(coords.charAt(i));
                    coord1 = true;
                }
            } catch (Exception e) {
                if (coord1) {
                    if (x != 0) {
                        y = Integer.parseInt(coordinate);
                    } else {
                        x = Integer.parseInt(coordinate);
                    }
                    coordinate = "";
                    coord1 = false;
                }
            }
            if (i > 99999) {
                break;
            }
        }
        int[] output = {x, y};
        return output;
    }

    static String gridDisplay(List<String> grid, int row, int column) {
        String display = "";
        for (int i = 0; i < row; i++) {
            for (int n = 0; n < column; n++) {
                int gridInd = i * column + n;
                if (grid.get(gridInd).equals("x")) {
                    display += "X ";
                } else if (grid.get(gridInd).equals("o")) {
                    display += "O ";
                } else {
                    display += "- ";
                }
            }
            display += "\n";
        }
        return display;
    }

    static boolean gridScan(List<String> grid, String symbol, int line, int column) {
        List<Integer> xoList = new ArrayList<>(Arrays.asList());
        for (int i = 0; i < grid.size(); i++) {
            if (grid.get(i).equals(symbol)) {
                xoList.add(i);
            }
        }
        for (int i = 0; i < xoList.size(); i++) {
            List<Integer> lock = new ArrayList(Arrays.asList(0, 0, 0, 0));
            System.out.println("--------------\n" + i + " i");
            for (int n = 1; n < line; n++) {
                System.out.println(n + " n");
                try {
                    if (!horizontal(xoList, i, n, column)) {
                        lock.set(0, 1);
                        System.out.println("Horizontal locked");
                    }
                    if (!vertical(xoList, i, n, column)) {
                        lock.set(1, 1);
                        System.out.println("Vertical locked");
                    }
                    if (!diagonalDown(xoList, i, n, column)) {
                        lock.set(2, 1);
                        System.out.println("Diagonal Down locked");
                    }
                    if (!diagonalUp(xoList, i, n, column)) {
                        lock.set(3, 1);
                        System.out.println("Diagonal Up locked");
                    }
                    System.out.println("Lock 0 = " + lock.get(0) + "\nLock 1 = " + lock.get(1));
                    if (lock.contains(0) && n == line - 1) {
                        return true;
                    }
                    if (!lock.contains(0)) {
                        break;
                    }
                } catch (Exception e) {
                    break;
                }
            }
        }
        return false;
    }

    static boolean horizontal(List<Integer> xoList, int i, int n, int column) {
        return xoList.contains(xoList.get(i) + n) && (xoList.get(i) + n) % column != 0;
    }

    static boolean vertical(List<Integer> xoList, int i, int n, int column) {
        return xoList.contains(xoList.get(i) + n * column);
    }

    static boolean diagonalDown(List<Integer> xoList, int i, int n, int column) {
        return xoList.contains(xoList.get(i) + (n * column) + n) && (xoList.get(i) + n) % column != 0;
    }

    static boolean diagonalUp(List<Integer> xoList, int i, int n, int column) {
        return xoList.contains(xoList.get(i) - (n * column) + n) && (xoList.get(i) + n) % column != 0;
    }
}