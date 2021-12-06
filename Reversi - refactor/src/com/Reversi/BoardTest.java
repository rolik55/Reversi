package com.Reversi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void setDisk() {
        Board board = Board.getInstance('X', '0');
        board.setDisk(8,8, 'X');
        Assertions.assertEquals('X', board.getBoard()[8][8]);
    }
}