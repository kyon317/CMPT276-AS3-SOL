package ca.cmpt276.as3_minesweeper_sol;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Display the help screen
 */
public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setTitle(getString(R.string.help));
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, HelpActivity.class);
    }
}
