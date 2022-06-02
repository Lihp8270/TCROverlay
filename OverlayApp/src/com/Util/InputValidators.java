package com.Util;

public class InputValidators {

    public InputValidators() {

    }

    /**
     * Checks an input string, returning false if a non-digit is found, and true if input is valid
     * @param input
     * @return
     */
    public boolean checkCharactersForDigitsOnly(String input) {
        char[] inputString = input.toCharArray();

        for (char c : inputString) {
            if (!(Character.isDigit(c))) {
                return false;
            }
        }

        return true;
    }

}
