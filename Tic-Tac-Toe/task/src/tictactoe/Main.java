package tictactoe;
import java.util.*;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter cells: ");
        String input = scanner.nextLine();
        
        char[][] row = new char[3][3];

        for (int i = 0; i < input.length(); i++) {
            if (i >= 0 && i <= 2) {
                row[0][i] = input.charAt(i);
            } else if (i >= 3 && i <= 5) {
                row[1][i - 3] = input.charAt(i);
            } else {
                row[2][i - 6] = input.charAt(i);
            }
        }

        System.out.println("---------");
        printField(row[0]);
        printField(row[1]);
        printField(row[2]);
        System.out.println("---------");
        state(row, input);
    }

    public static void printField(char[] row) {
        System.out.print("| ");
        for (char a : row) {
            System.out.print(a);
            System.out.print(" ");
        }
        System.out.println("|");
    }

    public static boolean checkOneRow(char[][] row, char a) {
        for (int i = 0; i < row.length; i++) {
            if (row[i][0] == row[i][1] && row[i][1] == row[i][2] && row[i][2] == a) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean checkOneColumn(char[][] row, char a) {
        for (int i = 0; i < row.length; i++) {
            if (row[0][i] == row[1][i] && row[1][i] == row[2][i] && row[2][i] == a) {
                return true;
            }
        }
        return false;
    }

    public static boolean check(char[][] row, char a) {
        if (checkOneRow(row, a)) {
            return true;
        } else if (checkOneColumn(row, a)) {
            return true;
        } else if (row[0][0] == a && row[1][1] == a && row[2][2] == a) {
            return true;
        } else if (row[0][2] == a && row[1][1] == a && row[2][0] == a) {
            return true;
        } 
        return false;       
    }
    
    public static boolean countChar(String input) {
        boolean countRes = true;
        int o = 0;
        int x = 0;

        for (char i : input.toCharArray()) {
            if (i == 'O') {
                o += 1;
            } else if (i == 'X') {
                x += 1;
            }
        }

        int diff = Math.abs(o - x);
        if (diff > 1) {
            countRes = false;
        }

        return countRes;
    }

    public static void state(char[][] row, String input) {
        boolean x = check(row, 'X');
        boolean o = check(row, 'O');
        
        boolean empty = input.contains("_");
        
        if (!countChar(input) || x == true && o == true) {
            System.out.println("Impossible");
        } else if (x == true && o == false) {
            System.out.println("X wins");
        } else if (x == false && o == true) {
            System.out.println("O wins");
        } else if (empty && x == false && o == false) {
            System.out.println("Game not finished");
        } else if (!empty && x == false && o == false) {
            System.out.println("Draw");
        }
    }
}

