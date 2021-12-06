package com.Reversi;

public class GameHandler extends InputVerifier{
    private static Board board;
//    private static BoardMemento boardMemento;

//    public static void saveBoard() {
//        boardMemento = board.save();
//    }
//
//    public static void restoreBoard() {
//        board.restore(boardMemento);
//    }

    public static boolean isGameOver(Board board, Player p1, Player p2) {
        if ((p1.getMoveCount(board) == 0) && (p2.getMoveCount(board) == 0))
            return true;
        return false;
    }

    public static void endTurn(Player currentTurn, Player nextTurn){
        currentTurn.setMyTurn(false);
        nextTurn.setMyTurn(true);
    }

    public static void printTurnInfo(Player p){
        System.out.println("Player " + p.getPiece() + "'s turn");
    }

    public static void play(){
        Player player1 = new Player('X', true);
        Player player2 = new Player('0', false);
        board = Board.getInstance(player1.getPiece(), player2.getPiece());
        while(!isGameOver(board, player1, player2)){
            if(player1.isMyTurn()){
                player1.makeTurn(board, player2);
                endTurn(player1, player2);
            }
            else {
                player2.makeTurn(board, player1);
                endTurn(player2, player1);
            }
        }
        endGame(board, player1, player2);
    }

    public static void endGame(Board board, Player p1, Player p2) {
        board.draw();

        int score1 = p1.getScore(board.getBoard());
        int score2 = p2.getScore(board.getBoard());
        if(score1 > score2){
            System.out.println("Player " + p1.getPiece() + " won!");
        }
        else if(score2 > score1){
            System.out.println("Player " + p2.getPiece() + "won!");
        }
        else if(score1 == score2){
            System.out.println("Tie!");
        }
    }
}
