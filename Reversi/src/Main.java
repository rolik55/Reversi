import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //initialize board
        char[][] board = new char[9][9];
        int index = 1;
        board[0][0] = ' ';
        for (char j = 'a'; j <= 'h'; j++) {
            board[0][index] = j;
            index++;
        }
        for (int i = 1; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j == 0) {
                    board[i][j] = (char) i;
                } else board[i][j] = '□';
            }
        }
        //For the specific game of Othello, the game begins with four disks placed in a square in the middle of the grid, two facing white-side-up, two dark-side-up, so that the same-colored disks are on a diagonal. Convention has this such that the dark-side-up disks are to the north-east and south-west (from both players' perspectives), though this is only marginally consequential: where sequential openings' memorization is preferred, such players benefit from this. The dark player moves first.
        board[4][5] = 'X';
        board[5][4] = 'X';
        board[4][4] = '0';
        board[5][5] = '0';

        int turn = 1;
        while (!isGameOver(board)) {
            if(getMoveCount(board, turn) != 0){
                drawBoard(board);
                System.out.print("Player " + turn + "'s turn");
                if (turn == 1) {
                    System.out.println(" (X)");
                } else System.out.println(" (0)");
                //input
                Scanner input = new Scanner(System.in);
                System.out.println("Select X coordinates (a-h)");
                String inputX = input.next();
                int X = findCoordinateX(inputX);
                if (!isValidCoordinate(X)) {
                    while (!isValidCoordinate(X)) {
                        System.out.println("Invalid input. Please input another value (a-h).");
                        inputX = input.next();
                        X = findCoordinateX(inputX);
                    }
                }
                System.out.println("Select Y coordinates (1-8)");
                int Y = input.nextInt();
                if (!isValidCoordinate(Y)){
                    while (!isValidCoordinate(Y)) {
                        System.out.println("Invalid input. Please input another value (1-8).");
                        Y = input.nextInt();
                    }
                }
                if (isValidPlacement(board, X, Y, turn)) {
                    setDisk(board, X, Y, turn);
                    if (turn == 1) {
                        turn = 2;
                    } else {
                        turn = 1;
                    }
                } else System.out.println("Invalid disk placement coordinates. Please try again.");
            }
            else{
                System.out.println("No moves are available");
                if (turn == 1) {
                    turn = 2;
                } else {
                    turn = 1;
                }
            }
        }
        drawBoard(board);
        int scoreX = getScore(board, 1);
        int scoreO = getScore(board, 2);
        if(scoreX > scoreO){
            System.out.println("Player 1 won!");
        }
        else if(scoreO > scoreX){
            System.out.println("Player 2 won!");
        }
        else if(scoreO == scoreX){
            System.out.println("Tie!");
        }
    }

    public static int findCoordinateX(String inputX) {
        int X;
        switch (inputX) {
            case "a":
                X = 1;
                break;
            case "b":
                X = 2;
                break;
            case "c":
                X = 3;
                break;
            case "d":
                X = 4;
                break;
            case "e":
                X = 5;
                break;
            case "f":
                X = 6;
                break;
            case "g":
                X = 7;
                break;
            case "h":
                X = 8;
                break;
            default:
                X = -1;
        }
        return X;
    }

    public static boolean isValidCoordinate(int coordinate) {
        if (coordinate < 1 || coordinate > 8) {
            return false;
        }
        return true;
    }

    public static void drawBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j == 0 && i != 0) {
                    System.out.print((int) board[i][j]);
                } else {
                    System.out.print(board[i][j]);
                }
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    public static void setDisk(char[][] board, int X, int Y, int turn) {
        char disk;
        if (turn == 1) {
            disk = 'X';
        } else {
            disk = '0';
        }
        board[Y][X] = disk;
    }

    public static boolean isValidPlacement(char[][] board, int X, int Y, int turn) {
        char currentPlayersDisk;
        char opponentPlayersDisk;
        if (turn == 1) {
            currentPlayersDisk = 'X';
            opponentPlayersDisk = '0';
        } else {
            currentPlayersDisk = '0';
            opponentPlayersDisk = 'X';
        }

        if (board[Y][X] != '□') {
            return false;
        }
        if (Y - 2 > 0) { // down
            if (board[Y - 2][X] == currentPlayersDisk && board[Y - 1][X] == opponentPlayersDisk) {
                board[Y - 1][X] = currentPlayersDisk;
                return true;
            }
        }
        if (Y + 2 < 9) { // up
            if (board[Y + 2][X] == currentPlayersDisk && board[Y + 1][X] == opponentPlayersDisk) {
                board[Y + 1][X] = currentPlayersDisk;
                return true;
            }
        }
        if (X + 2 < 9) { // left
            if (board[Y][X + 2] == currentPlayersDisk && board[Y][X + 1] == opponentPlayersDisk) {
                board[Y][X + 1] = currentPlayersDisk;
                return true;
            }
        }
        if (X - 2 > 0) { // right
            if (board[Y][X - 2] == currentPlayersDisk && board[Y][X - 1] == opponentPlayersDisk){
                board[Y][X - 1] = currentPlayersDisk;
                return true;
            }
        }
        if (Y - 2 > 0 && X - 2 > 0){ // up-left
            if(board[Y - 2][X - 2] == currentPlayersDisk && board[Y - 1][X - 1] == opponentPlayersDisk){
                board[Y - 1][X - 1] = currentPlayersDisk;
                return true;
            }
        }
        if (Y - 2 > 0 && X + 2 < 9){ // up-right
            if(board[Y - 2][X + 2] == currentPlayersDisk && board[Y - 1][X + 1] == opponentPlayersDisk){
                board[Y - 1][X + 1] = currentPlayersDisk;
                return true;
            }
        }
        if (Y + 2 < 9 && X + 2 < 9){ // down-right
            if(board[Y + 2][X + 2] == currentPlayersDisk && board[Y + 1][X + 1] == opponentPlayersDisk){
                board[Y + 1][X + 1] = currentPlayersDisk;
                return true;
            }
        }
        if (Y + 2 < 9 && X - 2 > 0){ // down-left
            if(board[Y + 2][X - 2] == currentPlayersDisk && board[Y + 1][X - 1] == opponentPlayersDisk){
                board[Y + 1][X - 1] = currentPlayersDisk;
                return true;
            }
        }
        return false;
    }

    public static boolean checkPlacements(char[][] board, int X, int Y, int turn) {
        char currentPlayersDisk;
        char opponentPlayersDisk;
        if (turn == 1) {
            currentPlayersDisk = 'X';
            opponentPlayersDisk = '0';
        } else {
            currentPlayersDisk = '0';
            opponentPlayersDisk = 'X';
        }

        if (board[Y][X] != '□') {
            return false;
        }
        if (Y - 2 > 0) { // down
            if (board[Y - 2][X] == currentPlayersDisk && board[Y - 1][X] == opponentPlayersDisk) {
                return true;
            }
        }
        if (Y + 2 < 9) { // up
            if (board[Y + 2][X] == currentPlayersDisk && board[Y + 1][X] == opponentPlayersDisk) {
                return true;
            }
        }
        if (X + 2 < 9) { // left
            if (board[Y][X + 2] == currentPlayersDisk && board[Y][X + 1] == opponentPlayersDisk) {
                return true;
            }
        }
        if (X - 2 > 0) { // right
            if (board[Y][X - 2] == currentPlayersDisk && board[Y][X - 1] == opponentPlayersDisk){
                return true;
            }
        }
        if (Y - 2 > 0 && X - 2 > 0){ // up-left
            if(board[Y - 2][X - 2] == currentPlayersDisk && board[Y - 1][X - 1] == opponentPlayersDisk){
                return true;
            }
        }
        if (Y - 2 > 0 && X + 2 < 9){ // up-right
            if(board[Y - 2][X + 2] == currentPlayersDisk && board[Y - 1][X + 1] == opponentPlayersDisk){
                return true;
            }
        }
        if (Y + 2 < 9 && X + 2 < 9){ // down-right
            if(board[Y + 2][X + 2] == currentPlayersDisk && board[Y + 1][X + 1] == opponentPlayersDisk){
                return true;
            }
        }
        if (Y + 2 < 9 && X - 2 > 0){ // down-left
            if(board[Y + 2][X - 2] == currentPlayersDisk && board[Y + 1][X - 1] == opponentPlayersDisk){
                return true;
            }
        }
        return false;
    }

    public static int getMoveCount(char[][] board,  int turn)
    {
        int numMoves = 0;
        for (int x = 1; x < 9; x++){
            for (int y = 1; y < 9; y++) {
                if (checkPlacements(board, x, y, turn)){
                    numMoves++;
                }
            }
        }
        return numMoves;
    }

    public static boolean isGameOver(char[][] board) {
        int moveCountX = getMoveCount(board, 1);
        int moveCountY = getMoveCount(board, 2);
        if ((moveCountX == 0) && (moveCountY == 0))
            return true;
        return false;

    }

    public static int getScore(char[][] board, int turn){
        int score = 0;
        char disk = 'X';
        if(turn == 2){
            disk = '0';
        }
        for (int x = 1; x < 9; x++){
            for (int y = 1; y < 9; y++) {
                if(board[x][y] == disk)
                    score++;
            }
        }
        return score;
        }

    }