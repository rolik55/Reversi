package com.Reversi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void getScore() {
        Player player = new Player('0', false);
        Board board = Board.getInstance('X', player.getPiece());
        board.setDisk(1, 1, player.getPiece());
        board.setDisk(5, 8, player.getPiece());
        int score = player.getScore(board.getBoard());
        Assertions.assertEquals(4, score);
    }
}