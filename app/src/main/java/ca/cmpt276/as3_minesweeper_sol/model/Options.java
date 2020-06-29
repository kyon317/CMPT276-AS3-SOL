package ca.cmpt276.as3_minesweeper_sol.model;

import android.content.Context;
import android.content.SharedPreferences;

import ca.cmpt276.as3_minesweeper_sol.R;

/**
 * Singleton options class for model: Store configuration data from the user interface.
 */
public class Options {

    private static final int DEFAULT_NUM_ROWS = 5;
    private static final int DEFAULT_NUM_COLS = 10;
    private static final int DEFAULT_NUM_MINES = 10;

    private static final String KEY_GAME_NUM_COLS = "ca.cmpt276.as3.GameWidth";
    private static final String KEY_GAME_NUM_ROWS = "ca.cmpt276.as3.GameHeight";
    private static final String KEY_GAME_NUM_MINES = "ca.cmpt276.as3.NumMines";

    private int numBoardRows = DEFAULT_NUM_ROWS;
    private int numBoardCols = DEFAULT_NUM_COLS;
    private int numBoardMines = DEFAULT_NUM_MINES;

    // -------------------------------------
    // Singleton Support
    // -------------------------------------
    private static Options instance;
    private Options(Context context) {
        numBoardRows = readSavedInt(context, KEY_GAME_NUM_ROWS, R.integer.default_board_size_rows);
        numBoardCols = readSavedInt(context, KEY_GAME_NUM_COLS, R.integer.default_board_size_cols);
        numBoardMines = readSavedInt(context, KEY_GAME_NUM_MINES, R.integer.default_num_mines);
    }

    public static Options getInstance(Context context) {
        if (instance == null) {
            instance = new Options(context);
        }
        return instance;
    }


    // -------------------------------------
    // Options Support
    // -------------------------------------
    public int getNumBoardRows() {
        return numBoardRows;
    }
    public int getNumBoardCols() {
        return numBoardCols;
    }
    public int getNumBoardMines() {
        return numBoardMines;
    }

    // Change the values (and store them)
    public void setNumBoardRows(int numBoardRows, Context context) {
        this.numBoardRows = numBoardRows;
        writeSavedInt(context, KEY_GAME_NUM_ROWS, numBoardRows);
    }
    public void setNumBoardCols(int numBoardCols, Context context) {
        this.numBoardCols = numBoardCols;
        writeSavedInt(context, KEY_GAME_NUM_COLS, numBoardCols);
    }
    public void setNumBoardMines(int numBoardMines, Context context) {
        this.numBoardMines = numBoardMines;
        writeSavedInt(context, KEY_GAME_NUM_MINES, numBoardMines);
    }


    // Get the full array of options
    public int[] getOptionsForNumMines(Context context) {
        return context.getResources().getIntArray(R.array.num_mines);
    }
    public int[] getOptionsForNumBoardRows(Context context) {
        return context.getResources().getIntArray(R.array.board_size_rows);
    }
    public int[] getOptionsForNumBoardCols(Context context) {
        return context.getResources().getIntArray(R.array.board_size_cols);
    }


    // -------------------------------------
    // Persistent Storage
    // -------------------------------------
    private static int readSavedInt(Context context, String key, int defaultValueId) {
        int defaultValue = context.getResources().getInteger(defaultValueId);
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        return prefs.getInt(key, defaultValue);
    }
    private void writeSavedInt(Context context, String key, int value) {
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

}
