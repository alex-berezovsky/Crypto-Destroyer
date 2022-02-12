package com.example.buyhighselllow;

import java.util.InputMismatchException;

public class Util {

    /*
    This method sanitizes the name input for the start menu when you enter your name. It checks for null,
    empty, and whitespace only inputs and throws the appropriate exceptions
     */
    public static String sanitizeNameInput(String input) {
        //checks for null input
        if (input == null) {

            throw new NullPointerException("Cannot input null value for name");

        }
        //checks for empty or whitespace only inputs
        if (input.equals("") || input.trim().isEmpty()) {

            throw new InputMismatchException("Your name cannot be empty");

        }
        //returns the input if it passes all checks
        return input;

    }
}
