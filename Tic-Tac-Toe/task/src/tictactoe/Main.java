package tictactoe;
import java.util.*;
import java.lang.Math;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static char[][] row = new char[3][3];
    static String res = "";
    public static void main(String[] args) {
        for (int i = 0; i < row.length; i++) {
            for (int j = 0; j < row[i].length; j++) {
                row[i][j] = ' ';
            }
        }

        printFullField(row);

        while (res.isEmpty()) {
            if (!full()) {
                turn('X');
                res = state();
                if (!full() && !res.isEmpty()) {
                    turn('O');
                    res = state();
                }
            }
        }

        System.out.println(res);
    }

    public static void turn(char i) {
        System.out.print("Enter the coordinates: ");

        int coordX;
        int coordY;

        while (!scanner.hasNextInt()) {
            System.out.println("You should enter numbers!");
            System.out.print("Enter the coordinates: ");
            scanner.next();
            continue;
        }

        coordX = scanner.nextInt();
        coordY = scanner.nextInt();

        if (coordX < 0 || coordY < 0 || coordX > 3 || coordY > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            turn(i);
        }  else {
            int xPos = position(coordY);
            int yPos = coordX - 1;
            if (!(occupied(xPos, yPos, row))) {
                row[xPos][yPos] = i;
                printFullField(row);
                state();
            } else {
                System.out.println("This cell is occupied! Choose another one!");
                turn(i);
            }
        }
    }

    public static boolean occupied(int x, int y, char[][] row) {
        return row[x][y] != ' ';
    }

    public static int position(int y) {
        int row = 0;
        switch (y) {
            case 3:
                row = 0;
                break;
            case 2:
                row = 1;
                break;
            case 1:
                row = 2;
                break;
        }
        return row;
    }

    public static void printFullField(char[][] row) {
        System.out.println("---------");
        printField(row[0]);
        printField(row[1]);
        printField(row[2]);
        System.out.println("---------");
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

    public static boolean full() {
        for (int i = 0; i < row.length; i++) {
            for (int j = 0; j < row[i].length; j++) {
                if (row[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static String state() {
        boolean x = check(row, 'X');
        boolean o = check(row, 'O');

        if (x == true && o == false) {
            return "X wins";
        } else if (x == false && o == true) {
            return "O wins";
        } else if (x == true && o == true) {
            return "Draw";
        } else {
            return "";
        }
    }
}

