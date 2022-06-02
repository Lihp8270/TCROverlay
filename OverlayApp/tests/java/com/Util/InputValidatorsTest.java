package com.Util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorsTest {
    InputValidators validator = new InputValidators();

    @Test
    void checkCharactersTest() {
        assertTrue(validator.checkCharactersForDigitsOnly("0123456789123456789"));
        assertFalse(validator.checkCharactersForDigitsOnly("asdf1234asdf12347851234"));
    }
}