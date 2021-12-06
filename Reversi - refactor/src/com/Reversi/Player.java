package com.Reversi;

public class Player {
    private char piece;
    private boolean isMyTurn;
    private int x, y;

    public char getPiece() {
        return piece;
    }

    public Player(char piece, boolean isMyTurn)
    {
        this.piece = piece;
        this.isMyTurn = isMyTurn;
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

    public void setMyTurn(boolean myTurn) {
        isMyTurn = myTurn;
    }

    public int getScore(char[][] board){
        int score = 0;
        for (int x = 1; x < 9; x++){
            for (int y = 1; y < 9; y++) {
                if(board[x][y] == piece)
                    score++;
            }
        }
        return score;
    }

    public int getMoveCount(Board board)
    {
        int count = 0;
        for (int x = 1; x < 9; x++){
            for (int y = 1; y < 9; y++) {
                if (board.checkPlacement(x, y, piece) != 0){
                    count++;
                }
            }
        }
        return count;
    }

    public void makeTurn(Board board, Player nextPlayer){
        //GameHandler.saveBoard();
        if(getMoveCount(board) != 0){
            board.draw();
            GameHandler.printTurnInfo(this);
            x = GameHandler.getInputX();
            y = GameHandler.getInputY();
            int direction = board.checkPlacement(x, y, piece);
            while(direction == 0){
                board.draw();
                System.out.println("Invalid disk placement coordinates. Please try again.");
                x = GameHandler.getInputX();
                y = GameHandler.getInputY();
                direction = board.checkPlacement(x, y, piece);
            }
            board.setDisk(x, y, piece);
            board.flipDisk(x, y, piece, direction);
        }
        else {
            System.out.println("No moves are available");
            GameHandler.endTurn(this, nextPlayer);
        }
    }
}
