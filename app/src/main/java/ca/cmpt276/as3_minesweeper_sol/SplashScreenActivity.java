package ca.cmpt276.as3_minesweeper_sol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        setupMainMenuButton();
    }

    private void setupMainMenuButton() {
        Button btn = (Button) findViewById(R.id.doneSplash);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainMenuActivity.makeIntent(SplashScreenActivity.this));
                finish();
            }
        });
    }
}
