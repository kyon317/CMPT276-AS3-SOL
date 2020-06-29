package ca.cmpt276.as3_minesweeper_sol;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import ca.cmpt276.as3_minesweeper_sol.model.Options;

/**
 * Options activity main class.
 */
public class OptionActivity extends AppCompatActivity {
    private Options options;

    public static Intent makeIntent(Context context) {
        return new Intent(context, OptionActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        setTitle(getString(R.string.options));

        options = Options.getInstance(this);

        createNumMineRadioButtons();
        setupBoardSizeRadioButtons();
    }


    private void createNumMineRadioButtons() {
        int[] optionsForNumMines = options.getOptionsForNumMines(this);
        for (int numMinesOption : optionsForNumMines) {
            boolean isSelected = (options.getNumBoardMines() == numMinesOption);
            createOneNumMinesRadioButton(numMinesOption, isSelected);
        }
    }

    private void createOneNumMinesRadioButton(final int numMines, boolean isSelected) {
        RadioButton rb = new RadioButton(this);
        rb.setText(getString(R.string.options_mines, numMines));
        rb.setTextColor(Color.WHITE);
        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                options.setNumBoardMines(numMines, OptionActivity.this);
            }
        });

        // Add
        RadioGroup group = (RadioGroup) findViewById(R.id.num_mines_group);
        group.addView(rb);

        // Selected?
        if (isSelected) {
            rb.setChecked(true);
        }
    }



    private void setupBoardSizeRadioButtons() {
        int[] rows = options.getOptionsForNumBoardRows(this);
        int[] cols = options.getOptionsForNumBoardCols(this);
        int selectedRow = options.getNumBoardRows();

        for (int i = 0; i < rows.length; i++) {
            int row = rows[i];
            int col = cols[i];
            boolean isSelected = (selectedRow == row);
            createOneGameSizeRadioButton(row, col, isSelected);
        }
    }
    private void createOneGameSizeRadioButton(final int rows, final int cols, boolean isSelected) {
        RadioButton rb = new RadioButton(this);
        rb.setText(getString(R.string.options_board_size, rows, cols));
        rb.setTextColor(Color.WHITE);
        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                options.setNumBoardRows(rows, OptionActivity.this);
                options.setNumBoardCols(cols, OptionActivity.this);
            }
        });

        // Add
        RadioGroup group = (RadioGroup) findViewById(R.id.board_size_group);
        group.addView(rb);

        // Selected?
        if (isSelected) {
            rb.setChecked(true);
        }
    }
}
