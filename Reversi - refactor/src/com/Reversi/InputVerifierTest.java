package com.Reversi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InputVerifierTest {

    @Test
    void isValidCoordinate() {
        Assertions.assertEquals(false, InputVerifier.isValidCoordinate(47));
    }
}