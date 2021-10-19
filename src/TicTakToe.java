import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTakToe {

    private static final char DOT_X = 'x';
    private static final char DOX_O = 'O';
    private static final char DOT_EMPTY = '•';

    private static final int SIZE = 3;
    private static char[][] map;
    private static Scanner scan = new Scanner(System.in);;

    public static void main(String[] args) {
        initMap();
        printMap ();
        while (true) {
            humanTurn();
            printMap();

            aiTurn();
            printMap();

            if (isMapFull()){
                System.out.println("Ничья");
                break;
            }
            if (isWin(DOT_X)){
                System.out.println("Победил человек");
            }

        }
    }

    private static boolean isWin(char symbol) {
        if (map[0][0] == symbol && map[0][1] == symbol && map[0][2] == symbol) return true;
        if (map[1][0] == symbol && map[1][1] == symbol && map[1][2] == symbol) return true;
        if (map[2][0] == symbol && map[2][1] == symbol && map[2][2] == symbol) return true;
        if (map[0][0] == symbol && map[1][0] == symbol && map[2][0] == symbol) return true;
        if (map[0][1] == symbol && map[1][1] == symbol && map[2][1] == symbol) return true;
        if (map[0][2] == symbol && map[1][2] == symbol && map[2][2] == symbol) return true;
        return false;
    }

    private static boolean isMapFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (isEmptyCell(row, col)){
                    return false;
                }
            }
        }
        return true;
    }


    private static void aiTurn() {
        int row, col;
        Random rand = new Random();
        do {
            row = rand.nextInt(SIZE);
            col = rand.nextInt(SIZE);
        } while (!isEmptyCell(row, col));
        map[row][col] = DOX_O;
    }

    private static void humanTurn() {
        System.out.println("Введите координаты row col: ");
        int row = 0;
        int col = 0;
        do {
            row = readIndex();
            col = readIndex();

            if (!checkRange(row) || !checkRange(col)) {
                System.out.println("Координаты должны быть в диапазоне от 1 до " + SIZE);
                continue;
            }

            if (isEmptyCell(row - 1, col - 1)){
                break;
            } else {
                System.out.println("Клетка занята");

            }
        } while (true);

        map[row - 1][col - 1] = DOT_X;
    }

    private static boolean isEmptyCell(int row, int col){
        return map[row][col] == DOT_EMPTY;
    }

    private static int readIndex() {
        while (!scan.hasNextInt()){
            System.out.println("Координаты должны иметь целочисленные значения!");
            scan.next();
        }
        
        return scan.nextInt();
    }

    private static boolean checkRange(int index) {
        return index >= 1 && index <= SIZE;
    }


    private static void initMap() {
        map = new char[SIZE][SIZE];

        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i],DOT_EMPTY);
        }
    }

    private static void printMap () {
        printMapHeader();
        printMapState();
        System.out.println();
    }

    private static void printMapState() {
        for (int i = 0; i < map.length; i++) {
            printRowNumber(i);
            printRow(map[i]);
            System.out.println();
        }
    }

    private static void printRow(char[] chars) {
        for (int j = 0; j < chars.length; j++) {
            System.out.print(chars[j] + " ");
        }
    }

    private static void printRowNumber(int rowNumber) {
        System.out.print((rowNumber + 1 ) + " ");
    }

    private static void printMapHeader() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
