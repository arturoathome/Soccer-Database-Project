package com370.sjc.ambNet.core;

import java.io.InputStream;
import java.util.Scanner;

public class InputManager {
    private Scanner input;
    private byte mode = 0;
    private String query;

    public InputManager(InputStream inputStream){
        this(new Scanner(inputStream));
    }
    public InputManager(Scanner input) {
        this.input = input;
    }

    /**
     * Gets a line of user input using this object's scanner, then updates the mode and query values
     */
    public String nextLine() {
        query = input.nextLine();
        mode = verifyInput(query);
        return query;
    }

    /**
     * Ensures that the passed string is alphanumeric(with spaces allowed)
     * This method can have 3 different return values
     * 0: Failure
     * 1: Pass, only numeric characters
     * 3: Pass, alphabetic characters present
     * a pass/fail check can simply look to see if the return value is equal to zero
     */
    private static byte verifyInput(String s) {
        byte mode = 0;
        for (char c : s.toCharArray()) {
            boolean alpha = isAlphabeticOrSpace(c);
            if (alpha)
                mode = 1;
            if (!(isAlphabeticOrSpace(c) || Character.isDigit(c)))
                return 0;
        }
        return (byte) (1 + (mode << 1));
    }

    /**
     * @param c passed character
     * @return true if the character is a space or alphabetic, false otherwise
     */
    private static boolean isAlphabeticOrSpace(char c) {
        return Character.isAlphabetic(c) || c == ' ';
    }

    //Getter methods below

    /**
     * 0 means that the query is unsafe to use
     * 1 means that the query is safe and only contains numbers
     * 3 means that the query is safe and contains letters(or spaces)
     * @return the "mode" of the query
     */
    public byte getMode() {
        return mode;
    }

    public void resetMode(){
        this.mode = 0;
    }


    public String getQuery() {
        return query;
    }

    public int getQueryInt(){
        return Integer.parseInt(query);
    }
}
