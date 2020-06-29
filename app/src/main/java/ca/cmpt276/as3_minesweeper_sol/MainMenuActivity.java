package ca.cmpt276.as3_minesweeper_sol;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setTitle(getString(R.string.main_menu));

        setupButton(R.id.menu_game, GameActivity.makeIntent(this));
        setupButton(R.id.menu_options, OptionActivity.makeIntent(this));
        setupButton(R.id.menu_help, HelpActivity.makeIntent(this));
    }

    private void setupButton(int buttonId, final Intent intent) {
        Button btn = (Button) findViewById(buttonId);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, MainMenuActivity.class);
    }
}
