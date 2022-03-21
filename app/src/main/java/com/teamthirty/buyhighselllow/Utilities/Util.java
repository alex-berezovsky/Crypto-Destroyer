package com.teamthirty.buyhighselllow.Utilities;

import android.content.Context;
import android.widget.Toast;
import androidx.core.util.Pair;

public final class Util {

    /**
     * This method sanitizes the name input for the start menu when you enter your name.
     * It checks for null, empty, and whitespace only inputs
     *
     * @param context the context of the application
     *
     * @param input   the string you are checking
     * @return true if name follows proper formatting, false otherwise
     */
    public static boolean sanitizeNameInput(Context context, String input) {

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


    /**
     * Returns button location in grid based on id
     * @param id of button
     * @return button location
     */
    public static Pair<Integer, Integer> towerLocation(int id) {
        String idString = id + "";
        int row;
        int column;

        if (idString.contains("999")) {
            row = 0;
            column = Integer.parseInt(idString.substring(3));
        } else {
            int endOfRowIndex = idString.indexOf('0');
            row = Integer.parseInt(idString.substring(0, endOfRowIndex));
            column = Integer.parseInt(idString.substring(endOfRowIndex + 3));
        }
        return new Pair<>(row, column);
    }
}
