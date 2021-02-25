import java.util.Random;
import java.util.Scanner;

public class Lesson4 {


        static char[][] map;
        static final int SIZE = 3;

        static final char DOT_Empty = '_';
        static final char DOT_X = 'X';
        static final char DOT_O = 'O';
        static final int DOT_TO_WIN = 3;

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if(checkWin(DOT_X)){
                System.out.println("Поздравляю, вы победили!");
                break;
            }
            if (isMapFull()){
                System.out.println("Ничья.");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)){
                System.out.println("Компьютор победил!");
                break;
            }
            if (isMapFull()){
                System.out.println("Ничья.");
                break;
            }
        }
        System.out.println("Игра закончена!");
    }

    static void initMap(){
        map = new char[SIZE][SIZE];
        for(int x = 0; x < SIZE; x++){
            for (int y = 0; y < SIZE; y++){
                map[x][y] = DOT_Empty;
            }
        }
    }

    static void printMap(){
        for(int x=0; x<=SIZE; x++){
            System.out.print(x+ " ");
        }
        System.out.println();

        for(int x=0; x<SIZE; x++){
                System.out.print((x+1) + " ");
            for(int y=0; y<SIZE; y++){
                System.out.print(map[x][y] + " ");
            }
            System.out.println();
        }
    }

    static void humanTurn(){
        Scanner sc = new Scanner(System.in);
        int x;
        int y;
        do{
            System.out.println("Ведите координаты фишки в формате координат X Y.");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        }while (!(isCellValid(x, y)));
        map[x][y]=DOT_X;
    }

    static boolean isCellValid(int x,int y){
        if(x < 0 || x >= SIZE || y < 0 || y >= SIZE){
            return false;
        }
        if (map[x][y] == DOT_Empty){
            return true;
        }
        return false;
    }

    static void aiTurn(){
        Random rnd = new Random();
        int x;
        int y;
        do{
            x = rnd.nextInt(SIZE);
            y = rnd.nextInt(SIZE);
        }while (!(isCellValid(x, y)));
        System.out.println("Компьютор сходил в координату " + (x+1) + " " + (y+1));
        map[x][y]=DOT_O;
    }

    static boolean checkWin(char symb){
        int endOfOffset = map.length - DOT_TO_WIN;

        for (int rowOffset = 0; rowOffset <= endOfOffset; rowOffset++){
            if (isDiagonals(symb, rowOffset)){
                return true;
            }
            for (int columnOffset = 0; columnOffset <= endOfOffset; columnOffset++){
                boolean hasWin = isLines(symb, rowOffset, columnOffset);

                if (hasWin){
                    return true;
                }
            }
        }

        return false;
    }

    static boolean isDiagonals(char symb, int rowOffset){
        int firstDiagonal = 0;
        int secondDiagonal = 0;

        final int subSquareLength = (DOT_TO_WIN + rowOffset);

        for (int row = rowOffset; row < subSquareLength; row++){
            if (map[row][row] == symb){
                firstDiagonal++;
            }
            else{
                firstDiagonal = 0;
            }
            if (map[row][map.length - 1 - row] == symb){
                secondDiagonal++;
            }
            else{
                secondDiagonal = 0;
            }
        }
        return (firstDiagonal == DOT_TO_WIN) || (secondDiagonal == DOT_TO_WIN);
    }

    static boolean isLines(char symb, int rowOffset, int columnOffset){
        for (int row = rowOffset; row < (DOT_TO_WIN + rowOffset); row++){
            int horizontalCounter = 0;
            int verticalCounter = 0;
            for (int column = columnOffset; column < (DOT_TO_WIN + columnOffset); column++){
                if (map[row][column] == symb){
                    horizontalCounter++;
                }
                else{
                    horizontalCounter = 0;
                }
                if (map[column][row] == symb){
                    verticalCounter++;
                }
                else{
                    verticalCounter = 0;
                }
            }

            if ((horizontalCounter == DOT_TO_WIN) || (verticalCounter == DOT_TO_WIN)){
                return true;
            }
        }
        return false;
    }

    static boolean isMapFull(){
        for (int x=0; x<SIZE; x++){
            for (int y=0; y<SIZE; y++){
                if (map[x][y] == DOT_Empty){
                    return false;
                }
            }
        }
        return true;
    }
}