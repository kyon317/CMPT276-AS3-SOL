package ca.cmpt276.as3_minesweeper_sol;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import ca.cmpt276.as3_minesweeper_sol.model.MineField;
import ca.cmpt276.as3_minesweeper_sol.model.Options;

/**
 * Main game activity to play mine-sweeper game.
 */
public class GameActivity extends AppCompatActivity {
    private static final String TAG = "MineSeeker";

    private MineField mMineField;
    private Button[][] mButtons;

    public static Intent makeIntent(Context context) {
        return new Intent(context, GameActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setTitle(getString(R.string.game));

        Options options = Options.getInstance(this);
        mMineField = new MineField(
                options.getNumBoardRows(),
                options.getNumBoardCols(),
                options.getNumBoardMines()
        );
        createButtons();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        redrawGameBoard();
    }

    private void createButtons() {
        int cols = mMineField.getNumCols();
        int rows = mMineField.getNumRows();
        mButtons = new Button[rows][cols];

        // Find the table to put content in:
        TableLayout table = (TableLayout) findViewById(R.id.table_for_cells);

        for (int row = 0; row < rows; row++) {
            // Create the rows:
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            table.addView(tableRow);

            // Populate the row:
            for (int col = 0; col < cols; col++) {
                final int FIN_COL = col;
                final int FIN_ROW = row;

                Button btn = new Button(this);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gameCellClicked(FIN_ROW, FIN_COL);
                    }
                });

                btn.setLayoutParams(new TableRow.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
                mButtons[row][col] = btn;

                tableRow.addView(btn);
            }
        }
    }

    private void gameCellClicked(int row, int col) {
        // TODO: Add animation effects here?
        // TODO: Add sound effects here?

        mMineField.investigateCell(row, col);
        redrawGameBoard();

        if (mMineField.hasUserWon()) {
            FragmentManager manager = getFragmentManager();
            WonFragment dialog = new WonFragment();
            dialog.show(manager, "won dialog");
        }
    }


    private void redrawGameBoard() {
        Log.d(TAG, "Redrawing board...");
        int cols = mMineField.getNumCols();
        int rows = mMineField.getNumRows();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Button btn = mButtons[row][col];
                if (mMineField.hasVisibleMine(row, col)) {
                    setImageOnButton(R.mipmap.cookie, btn);
                } else {
                    setImageOnButton(R.mipmap.field, btn);
                }
                if (mMineField.hasBeenScanned(row, col)) {
                    String message = "" + mMineField.getNumMinesScanned(row, col);
                    btn.setText(message);
                }

            }
        }

        // Update titles:
        TextView foundMines = (TextView) findViewById(R.id.found_mines);
        foundMines.setText(getString(R.string.found_mines,
                mMineField.getNumMinesFound(),
                mMineField.getNumMinesTotal()
                ));

        TextView scansUsed = (TextView) findViewById(R.id.scans_used);
        scansUsed.setText(getString(R.string.scans_used,
                mMineField.getNumScans()
                ));
    }

    private void setImageOnButton(int imageID, Button button) {
        // Requires high API: Jelly Bean (min SDK 16)
        // MUST NOT CALL FROM onCreate(), onStart(), or onResume() because values will be 0
        // Instead, call from on onWindowFocusChanged()
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();

        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), imageID);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));
    }
}
