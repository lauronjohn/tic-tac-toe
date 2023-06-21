package TicTacToe;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//Tic-Tac-Toe Game made by j0hN lAur0N
//11.25.2019

public class TicTacToe {

    //main display of the board
    public static final String[][] a = {
            {"□", "□", "□"},
            {"□", "□", "□"},
            {"□", "□", "□"}};

    public static final Random rand = new Random();
    public static final Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        //main method
        while (true) {
            System.out.println("Welcome to John's Tic Tac Toe! (Human: O, CPU: X )");
            System.out.println("--------------------------");
            printArray(a);
            play(a);
            if (tryAgain()) {
                newArray(a);
            } else break;
        }

    }

    public static void play(String[][] board) {
        //selects random turn at the start
        String[] choice = new String[2];
        choice[0] = "O";
        choice[1] = "X";
        String turn = choice[rand.nextInt(2)];

        while (true) {
            if (turn.equals("O")) {
                System.out.println("What is your next move?");
                System.out.print("> ");
                String answer = userInput.nextLine();

                //intake the answer
                int[] splitInput = parseInput(answer);
                if (splitInput == null) {
                    System.out.println("Invalid move."); //if answer is not in the board, then return invalid move and ta
                    continue;
                }
                int col = splitInput[0];
                int row = splitInput[1];


                //once an input has been set, you cant change it
                if (cantReplace(board, col, row)) {
                    System.out.println("Invalid Move. Try Again.");
                    continue;
                }

                //switches from X and O
                board[col][row] = turn;
                if (isWinning(board, turn)) {
                    printArray(board);
                }
            }

            //AI choice
            if (turn.equals("X")) {
                AIChoice(board, turn);
                printArray(board);
            }

            // Game Over(s)
            if (isWinning(board, turn)) {
                System.out.println("Game Over! " + turn + " wins!");
                break;
            }

            //condition if the board is full / tie
            if (BoardFull(board)) {
                System.out.println("Game over! It's a tie! :)");
                break;
            }

            //switches from X and O
            if (turn.equals("O")) {
                turn = "X";
            } else {
                turn = "O";
            }
        }
    }

    //Winning situations
    public static boolean isWinning(String[][] board, String turn) {
        //Row wins
        if (board[0][0].equals(board[0][1]) && (board[0][0].equals(board[0][2])) && !board[0][0].equals("□")) {
            return true;
        } else if (board[1][0].equals(board[1][1]) && (board[1][0].equals(board[1][2])) && !board[1][0].equals("□")) {
            return true;
        } else if (board[2][0].equals(board[2][1]) && (board[2][0].equals(board[2][2])) && !board[2][0].equals("□")) {
            return true;
        }

        //Column wins
        else if (board[0][0].equals(board[1][0]) && (board[0][0].equals(board[2][0])) && !board[0][0].equals("□")) {
            return true;
        } else if (board[0][1].equals(board[1][1]) && (board[0][1].equals(board[2][1])) && !board[0][1].equals("□")) {
            return true;
        } else if (board[0][2].equals(board[1][2]) && (board[0][2].equals(board[2][2])) && !board[0][2].equals("□")) {
            return true;
        }

        //Diagonal wins
        else if (board[0][0].equals(board[1][1]) && (board[1][1].equals(board[2][2])) && !board[1][1].equals("□")) {
            return true;
        } else return board[0][2].equals(board[1][1]) && (board[1][1].equals(board[2][0])) && !board[1][1].equals("□");
    }

    //Passes the input answer into the array board
    public static int[] parseInput(String answer) {
        switch (answer.toUpperCase()) {
            case "A1":
                return new int[]{0, 0};
            case "A2":
                return new int[]{1, 0};
            case "A3":
                return new int[]{2, 0};
            case "B1":
                return new int[]{0, 1};
            case "B2":
                return new int[]{1, 1};
            case "B3":
                return new int[]{2, 1};
            case "C1":
                return new int[]{0, 2};
            case "C2":
                return new int[]{1, 2};
            case "C3":
                return new int[]{2, 2};
            default:
                return null;
        }
    }

    //Checks whether or not the Board is full
    public static boolean BoardFull(String[][] Board) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (Board[i][j].equals("□")) {
                    return false;
                }
            }
        }
        return true;
    }


    //Prevents Player or AI to replace a turn
    public static boolean cantReplace(String[][] Board, int col, int row) {
        return (Board[col][row].equals("X") || Board[col][row].equals("O"));
    }

    //AI plays
    public static void AIChoice(String[][] Board, String turn) {
        int col;
        int row;
        do {
            col = rand.nextInt(3);
            row = rand.nextInt(3);
        } while (cantReplace(Board, col, row));
        Board[col][row] = turn;
    }


    //method to prompt the user to play new game
    public static boolean tryAgain() {
        System.out.println("Do you want to try again? (YES/NO)");
        String reply = userInput.nextLine();
        if (reply.toUpperCase().equals("YES")) {
            return true;
        } else return !reply.toUpperCase().equals("NO");
    }


    //initiates the board
    public static void printArray(String[][] a) {
        int counterA = 0;
        char[] arr = new char[]{'A', 'B', 'C'};
        System.out.print(" ");
        for (int k = 0; k < arr.length; k++) {
            System.out.print(" " + arr[counterA]);
            counterA++;
        }
        System.out.println();
        System.out.println("--------");
        int counterB = 1;
        for (String[] strings : a) {
            for (int j = 0; j < strings.length; j++) {
                if (j == 0) {
                    System.out.print(counterB + " ");
                    counterB++;
                }
                System.out.print(strings[j] + " ");
            }
            System.out.println();
        }
    }

    //prints out a new board if a new game
    public static void newArray(String[][] b) {
        for (int i = 0; i < b.length; i++) {
            Arrays.fill(b[i], "□");
        }
    }
}