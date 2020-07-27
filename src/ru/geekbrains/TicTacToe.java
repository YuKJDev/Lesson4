package ru.geekbrains;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static char[][] map;
    public static final int SIZE = 3;
  //  public static final int DOTS_TO_WIN = 3;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';

    public static void main(String[] args) {
        // write your code here
        start();
    }

    static void start() {

        String playerName;
        Scanner scanName = new Scanner(System.in);
        System.out.println("Введите имя игрока!");
        playerName = scanName.nextLine();
        String winnerName = "";
        char[][] field = initDrowFields();
        drowField();

        do {

            doPlayerMove(field, DOT_X);
            // Проверка на победу
            if (checkWin(field, DOT_X)) {
                winnerName = playerName;
                break;
            }
            doAIMove(field, DOT_O);
            drowField();
            // Проверка на победу AI
            if (checkWin(field, DOT_O)) {
                winnerName = "Computer";
                break;
            }
            if (isDraw()) {
                winnerName = "";
                break;
            }

        } while (true);
        if (winnerName == "") {
            System.out.println("Ничья...!");
        } else
            System.out.println("Победил " + winnerName);
    }

    static void doValidate(char[][] field, char sign) {
        int xVal = 0;
        int yVal = 0;
        Scanner scanner = new Scanner(System.in);
        do  {
        do {
            System.out.println("Введите значение координаты по 'X' от 1 до 3");
            while (!scanner.hasNextInt()) {
                System.out.println("Введите число от 1 до 3");
                scanner.next();
            } // Не даем пользователю ввести нечисловое значение
            xVal = scanner.nextInt() - 1;
        } while (xVal < 0 || xVal > 2); // Проверяем что диапазон равен от 1 до 3

        do {
            System.out.println("Введите значение координаты по 'Y' от 1 до 3");
            while (!scanner.hasNextInt()) {
                System.out.println("Введите число от 1 до 3");
                scanner.next();
            } // Не даем пользователю ввести нечисловое значение
            yVal = scanner.nextInt() - 1;

        } while (yVal < 0 || yVal > 2); // Проверяем что диапазон равен от 1 до 3

            System.out.println("Поле занято. Выбеите другое поле.");
        } while (field[xVal][yVal] != DOT_EMPTY);

        field[xVal][yVal] = sign;
     

    }

    static void doPlayerMove(char[][] field, char sign) {
        doValidate(field, sign);
        drowField();
    }

    static void drowField() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    static void doAIMove(char[][] field, char sign) {
        Random random = new Random();
        System.out.println("Компьютер ходит...");
        // Вводим координаты Х, Y
        int xVal = random.nextInt(3);
        int yVal = random.nextInt(3);
        int count = 0;

        // Если значение по координатам занято, то делаем перегенерацию координат, пока не найдем свободные
       if (field[0][0] == field[1][0] && field[1][0] == DOT_X && field[2][0] == DOT_EMPTY) {
           xVal = 2;
           yVal = 0;
       } else
       if (field[0][1] == field[1][1] && field[1][1] == DOT_X && field[1][2] == DOT_EMPTY) {
           xVal = 1;
           yVal = 2;
       } else
       if (field[2][0] == field[2][1] && field[2][1] == DOT_X && field[2][2] == DOT_EMPTY) {
           xVal = 2;
           yVal = 2;
        } else
        if (field[0][0] == field[0][1] && field[0][1] == DOT_X && field[0][2] == DOT_EMPTY) {
            xVal = 0;
            yVal = 2;
        } else
        if (field[1][0] == field[1][1] && field[1][1] == DOT_X && field[1][2] == DOT_EMPTY) {
            xVal = 1;
            yVal = 2;
        } else
        if (field[0][2] == field[1][2] && field[1][2] == DOT_X && field[2][2] == DOT_EMPTY) {
            xVal = 2;
            yVal = 1;
        } else {

            while (field[xVal][yVal] != DOT_EMPTY) {
                xVal = random.nextInt(3);
                yVal = random.nextInt(3);
            }
        }
        System.out.println(String.format("Ход компьютера X : %s", xVal + 1));
        System.out.println(String.format("Ход компьютера Y: %s", yVal + 1));
        field[xVal][yVal] = sign;
    }

    static char[][] initDrowFields() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
        return map;
    }

    // Проверка победы
    static boolean checkWin(char[][] field, char sign) {

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {

                if (field[i][0] == sign && field[i][1] == sign && field[i][2] == sign) {
                    return true; //return true;
                } else {
                    if (field[0][j] == sign && field[1][j] == sign && field[2][j] == sign) {
                        return true;  // return true;
                    } else {
                        if (field[0][0] == sign && field[1][1] == sign && field[2][2] == sign) {
                            return true; //return true;
                        } else {

                            if (field[0][2] == sign && field[1][1] == sign && field[2][0] == sign) {
                                return true; // return true;
                            }
                        }
                    }

                }

            }
        }

      return false;
    }

   static boolean isDraw () {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }
}
