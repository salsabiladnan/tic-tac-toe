package tictactoe;
import java.util.*;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter cells: ");
        String input = scanner.nextLine().replace("_", " ");
        
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

        printFullField(row);
        
        System.out.print("Enter the coordinates: ");
        
        boolean check = scanner.hasNextInt();
        
        int coordX;
        int coordY; 
        
        for(;;) {
            if (!scanner.hasNextInt()) {
                System.out.println("You should enter numbers!");
                System.out.print("Enter the coordinates: ");
                scanner.next();
                continue;
            }

            coordX = scanner.nextInt();
            coordY = scanner.nextInt();

            if (coordX < 0 || coordY < 0 || coordX > 3 || coordY > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                System.out.print("Enter the coordinates: ");
                coordX = scanner.nextInt();
                coordY = scanner.nextInt();
            } 
            break;
        }
        

        
        int xPos = position(coordY);
        int yPos = coordX - 1;

        while (occupied(xPos, yPos, row)) {
            System.out.println("This cell is occupied! Choose another one!");
            System.out.print("Enter the coordinates: ");
            coordX = scanner.nextInt();
            coordY = scanner.nextInt();
            xPos = position(coordY);
            yPos = coordX - 1;
        }

        row[xPos][yPos] = 'X';
        printFullField(row);
        //state(row, input);
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

    public static void state(char[][] row, String input) {
        boolean x = check(row, 'X');
        boolean o = check(row, 'O');
        
        boolean empty = input.contains(" ");
        
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

