package com.demo.terminal;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

public class Main {


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Player 1, Insert your name ");
        String player1 = input.nextLine();
        System.out.println("Player 2, Insert your name ");
        String player2 = input.nextLine();
        System.out.println("Insert the size of columns and rows the game should have: ");
        int n = input.nextInt();
        char[][] board = new char[n][n];

        System.out.println("Board length:" + n);

        // i for rows, j for columns
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                board[i][j] = '-';
            }
        }


        // to check the turn of the player
        boolean isPlayer1 = true;

        boolean gameEnded = false;


        while(!gameEnded) {
            // draw the board
           drawBoard(board);


            char symbol = isPlayer1 ? 'O' : 'X';

            if (isPlayer1) {
                System.out.println("It's " + player1 + "'s turn (O)");
            } else {
                System.out.println("It's " + player2 + "'s turn(X)");
            }

            int row;
            int col;

            while (true) {
                System.out.println("Enter a row and a column between (0 and " + (n - 1)  + ")");
                row = input.nextInt();
                col = input.nextInt();

                if (row >= n|| col >= n || col < 0 || row < 0) {
                    System.out.println("Position not on the board");
                } else if (board[row][col] != '-') {
                    System.out.println("The position is already taken");
                } else {

                    break;
                }
            }

            board[row][col] = symbol;

            // checking for draw or win
            if (hasWon(board) == 'X') {
                System.out.println("The player " + player2 + " has won the game ( X )");
                gameEnded = true;
            } else if (hasWon(board) == 'O') {
                System.out.println("The player " + player1 + " has won the game ( O )");
                gameEnded = true;
            } else {
                if (isATie(board)) {
                    System.out.println("The game ended into a tie!");
                    gameEnded = true;
                }
                else {
                    // toggles the player turn from player1 true to false
                    isPlayer1 = !isPlayer1;
                }
            }
        }

        drawBoard(board);
    }

    // the initial board
    public static void drawBoard (char[][] board) {
        for (char[] chars : board) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }
    }

    public static char hasWon(char[][] board) {
        // 3 in ro, col or diagonal

        // for a row
        for (char[] chars : board) {
            boolean inARow = true;
            char value = chars[0];
            if (value == '-') {
                inARow = false;
            } else {
                for (int j = 1; j < chars.length; ++j) {
                    if (chars[j] != value) {
                        inARow = false;
                        break;
                    }
                }
            }
            if (inARow) {
                return value;
            }
        }

        // for a column
        for(int j = 0; j < board[0].length; ++j) {
            boolean inAColumn = true;
            char value = board[0][j];
            if(value == '-') {
                inAColumn = false;
            } else {
                for(int i = 1; i < board.length; ++i) {
                    if(board[i][j] != value) {
                        inAColumn = false;
                        break;
                    }
                }
            }
            if(inAColumn) {
                return value;
            }
        }

        // for diagonal left to right
        for(int j = 0; j < board[0].length; ++j) {
            boolean inDiagonal1 = true;
            char value = board[0][0];
            if(value == '-') {
                inDiagonal1 = false;
            } else {
                for (int i = 1; i < board.length; ++i) {
                    if(board[i][i] != value) {
                        inDiagonal1 = false;
                        break;
                    }
                }
            }
            if(inDiagonal1) {
                return value;
            }
        }
        // for diagonal right to left
        for(int j = 0; j < board[0].length; ++j) {
            boolean inDiagonal2 = true;
            char value = board[0][board[0].length-1];
            if(value == '-') {
                inDiagonal2 = false;
            } else {
                for (int i = 1; i < board.length; ++i) {
                    if(board[i][board[0].length-1-i] != value) {
                        inDiagonal2 = false;
                        break;
                    }
                }
            }
            if(inDiagonal2) {
                return value;
            }
        }
        // nobody wins
        return '-';

    }

    // checking for a tie
    public static boolean isATie(char[][] board) {
        // for rows
        for (char[] chars : board) {
            // for columns
            for (char aChar : chars) {
                if (aChar == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}