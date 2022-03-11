package com.teamthirty.buyhighselllow.Utilities;

import android.content.Context;
import android.widget.Toast;

public final class Util {

    /**
     * This method sanitizes the name input for the start menu when you enter your name.
     * It checks for null, empty, and whitespace only inputs
     *
     * @param input   the string you are checking
     * @param context the context of the application
     *
     * @return true if name follows proper formatting, false otherwise
     */
    public static boolean sanitizeNameInput(String input, Context context) {

        if (input == null) {
            //null input check
            displayError(context, "Invalid Name: You cannot input null values for your name");
            return false;
        } else if (input.equals("") || input.trim().isEmpty()) {
            //empty and whitespace only input check
            displayError(context, "Invalid Name: Your name cannot be empty");
            return false;
        }

        return true;
    }

    public static void displayError(Context context, String errorMessage) {
        if (context != null) {
            int popUpDuration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, errorMessage, popUpDuration);
            toast.show();
        }
    }
}
