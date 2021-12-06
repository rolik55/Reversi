package com.Reversi;

public class Board {
    private final int SIZE = 9;
    private final int BLACK_DISK_1_X = 4;
    private final int BLACK_DISK_1_Y = 5;
    private final int BLACK_DISK_2_X = 5;
    private final int BLACK_DISK_2_Y = 4;
    private final int WHITE_DISK_1_X = 4;
    private final int WHITE_DIST_1_Y = 4;
    private final int WHITE_DISK_2_X = 5;
    private final int WHITE_DISK_2_Y = 5;

    private static Board instance;
    private char[][] board;

    public char[][] getBoard() {
        return board;
    }

//    public BoardMemento save() {
//        return new BoardMemento(board);
//    }
//
//    public void restore(BoardMemento save) {
//        board = save.getBoard();
//    }

    public static Board getInstance(char black, char white){
        if(instance == null){
            instance = new Board(black, white);
        }
        return instance;
    }

    private Board(char black, char white) {
        board = new char[SIZE][SIZE];
        board[0][0] = ' ';
        int i = 1;
        for (char j = 'a'; j <= 'h'; j++) {
            board[0][i] = j;
            i++;
        }
        for (i = 1; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j == 0) {
                    board[i][j] = (char) i;
                } else board[i][j] = '□';
            }
        }
        board[BLACK_DISK_1_X][BLACK_DISK_1_Y] = black;
        board[BLACK_DISK_2_X][BLACK_DISK_2_Y] = black;
        board[WHITE_DISK_1_X][WHITE_DIST_1_Y] = white;
        board[WHITE_DISK_2_X][WHITE_DISK_2_Y] = white;
    }

    public void draw(){
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

    public int checkPlacement(int X, int Y, char piece) {
        if (board[Y][X] != '□') {
            return 0;
        }
        if (Y - 2 > 0) { // down
            if (board[Y - 2][X] == piece && board[Y - 1][X] != piece && board[Y - 1][X] != '□') {
                return 5;
            }
        }
        if (Y + 2 < 9) { // up
            if (board[Y + 2][X] == piece && board[Y + 1][X] != piece  && board[Y + 1][X] != '□') {
                return 1;
            }
        }
        if (X + 2 < 9) { // left
            if (board[Y][X + 2] == piece && board[Y][X + 1] != piece && board[Y][X + 1] != '□') {
                return 7;
            }
        }
        if (X - 2 > 0) { // right
            if (board[Y][X - 2] == piece && board[Y][X - 1] != piece && board[Y][X - 1] != '□'){
                return 3;
            }
        }
        if (Y - 2 > 0 && X - 2 > 0){ // up-left
            if(board[Y - 2][X - 2] == piece && board[Y - 1][X - 1] != piece && board[Y - 1][X - 1] != '□'){
                return 8;
            }
        }
        if (Y - 2 > 0 && X + 2 < 9){ // up-right
            if(board[Y - 2][X + 2] == piece && board[Y - 1][X + 1] != piece && board[Y - 1][X + 1] != '□'){
                return 2;
            }
        }
        if (Y + 2 < 9 && X + 2 < 9){ // down-right
            if(board[Y + 2][X + 2] == piece && board[Y + 1][X + 1] != piece && board[Y + 1][X + 1] != '□'){
                return 4;
            }
        }
        if (Y + 2 < 9 && X - 2 > 0){ // down-left
            if(board[Y + 2][X - 2] == piece && board[Y + 1][X - 1] != piece && board[Y + 1][X - 1] != '□'){
                return 6;
            }
        }
        return 0;
    }

    public void setDisk(int X, int Y, char piece) {
        board[Y][X] = piece;
    }

    public void flipDisk(int X, int Y, char piece, int direction) {
        switch (direction) {
            case 1:
                Y += 1;
                break;
            case 2:
                X += 1;
                Y -= 1;
                break;
            case 3:
                X -= 1;
                break;
            case 4:
                X += 1;
                Y += 1;
                break;
            case 5:
                Y -= 1;
                break;
            case 6:
                X -= 1;
                Y += 1;
                break;
            case 7:
                X += 1;
                break;
            case 8:
                X -= 1;
                Y -= 1;
                break;
        }
        setDisk(X,Y,piece);
    }
}
